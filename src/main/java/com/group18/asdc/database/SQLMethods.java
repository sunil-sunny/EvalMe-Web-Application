package com.group18.asdc.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class SQLMethods {

	private Connection connection;
	private ResultSet rs;

	public SQLMethods(Connection connection){
		rs = null;
		this.connection = connection;
	}

	private void replaceValuesInPreparedStmt(PreparedStatement preparedStmt, ArrayList values) throws SQLException {
		int iterator = 1;
		for (Object eachValue : values) {
			preparedStmt.setObject(iterator, eachValue);
			iterator++;
		}
	}

	private PreparedStatement constructPreparedStmt(String sqlQuery, ArrayList values) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
		replaceValuesInPreparedStmt(preparedStatement, values);
		return preparedStatement;
	}

	private ArrayList<String> getColumnNames(ResultSetMetaData metaData) throws SQLException {
		int columnIter = 1, columnCount = metaData.getColumnCount();
		ArrayList<String> columnNameList = new ArrayList<>();
		for (columnIter = 1; columnIter <= columnCount; columnIter++) {
			columnNameList.add(metaData.getColumnName(columnIter));
		}
		return columnNameList;
	}

	private void constructBatchInsertQuery(PreparedStatement preparedStmt, ArrayList<ArrayList> valuesList)
			throws SQLException {
		for (ArrayList values : valuesList) {
			replaceValuesInPreparedStmt(preparedStmt, values);
			preparedStmt.addBatch();
		}
	}

	public void cleanup() {
		try {
			if (null != connection) {
				if (!connection.isClosed()) {
					connection.close();
				}
			}
			if (null != rs) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object insertQuery(String sqlQuery, ArrayList<Object> values) throws SQLException {
		Object resultObj = null;
		PreparedStatement preparedStatement = constructPreparedStmt(sqlQuery, values);
		int rowAffected = preparedStatement.executeUpdate();
		if (rowAffected == 1) {
			rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				resultObj = rs.getObject(1);
			}
		}
		return resultObj;
	}

	public ArrayList<HashMap<String, Object>> selectQuery(String sqlQuery, ArrayList<Object> values)
			throws SQLException {
		PreparedStatement preparedStatement = constructPreparedStmt(sqlQuery, values);
		rs = preparedStatement.executeQuery();
		ResultSetMetaData metaData = rs.getMetaData();
		ArrayList<String> columnNamesList = getColumnNames(metaData);
		ArrayList<HashMap<String, Object>> resultRowList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> rowObjectMap = null;
		int columnCount = columnNamesList.size();
		while (rs.next()) {
			rowObjectMap = new HashMap<String, Object>();
			int columnIter = 1;
			for (columnIter = 1; columnIter <= columnCount; columnIter++) {
				rowObjectMap.put(columnNamesList.get(columnIter - 1), rs.getObject(columnIter));
			}
			resultRowList.add(rowObjectMap);
		}
		return resultRowList;
	}

	public Integer updateQuery(String sqlQuery, ArrayList updateValueList, ArrayList criteriaValueList)
			throws SQLException {
		updateValueList.addAll(criteriaValueList);
		PreparedStatement preparedStatement = constructPreparedStmt(sqlQuery, updateValueList);
		Integer rowCount = preparedStatement.executeUpdate();
		return rowCount;
	}

	public Integer deleteQuery(String sqlQuery, ArrayList criteriaList) throws SQLException {
		PreparedStatement preparedStatement = constructPreparedStmt(sqlQuery, criteriaList);
		Integer rowCount = preparedStatement.executeUpdate();
		return rowCount;
	}

	public Integer multipleInsertQuery(String sqlQuery, ArrayList<ArrayList> valuesList) throws SQLException {
		PreparedStatement preparedStatement = constructPreparedStmt(sqlQuery, new ArrayList<>());
		constructBatchInsertQuery(preparedStatement, valuesList);
		Integer rowCount = preparedStatement.executeBatch().length;
		return rowCount;
	}
}