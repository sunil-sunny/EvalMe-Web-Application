package com.group18.asdc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.group18.asdc.database.ConnectionManager;
import com.group18.asdc.database.SQLMethods;
import com.group18.asdc.database.SQLQueries;

public class PasswordHistoryDaoImpl implements PasswordHistoryDao {

	@Override
	public Object insertPasswordHistory(ArrayList valuesList) {
		SQLMethods sqlImplementation = null;
		try {
			Connection connection = ConnectionManager.getInstance().getDBConnection();
			sqlImplementation = new SQLMethods(connection);
			Object primaryKey = sqlImplementation.insertQuery(SQLQueries.INSERT_PASSWORD_HISTORY.toString(),
					valuesList);
			return primaryKey;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (sqlImplementation != null) {
				sqlImplementation.cleanup();
			}
		}
		return null;
	}

	@Override
	public ArrayList<HashMap> getPasswordHistory(ArrayList criteriaList) {
		SQLMethods sqlImplementation = null;
		try {
			Connection connection = ConnectionManager.getInstance().getDBConnection();
			sqlImplementation = new SQLMethods(connection);
			ArrayList resultList = sqlImplementation.selectQuery(SQLQueries.GET_PASSWORD_HISTORY.toString(),
					criteriaList);
			return resultList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (sqlImplementation != null) {
				sqlImplementation.cleanup();
			}
		}
		return null;
	}
}