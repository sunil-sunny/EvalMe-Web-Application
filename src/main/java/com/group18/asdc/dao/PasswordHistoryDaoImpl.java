package com.group18.asdc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.database.ISQLMethods;
import com.group18.asdc.database.UserManagementDataBaseQueriesUtil;

public class PasswordHistoryDaoImpl implements PasswordHistoryDao {

	private Logger logger = Logger.getLogger(PasswordHistoryDao.class.getName());

	@Override
	public Object insertPasswordHistory(ArrayList valuesList) {
		logger.log(Level.INFO, "Inserting password history for user=" + valuesList.get(0));
		ISQLMethods sqlImplementation = null;
		try (Connection connection = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
				.getConnectionManager().getDBConnection();) {

			sqlImplementation = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
					.getSqlMethods(connection);
			Object primaryKey = sqlImplementation
					.insertQuery(UserManagementDataBaseQueriesUtil.INSERT_PASSWORD_HISTORY.toString(), valuesList);
			return primaryKey;
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQL Exception while inserting password history",e);
		} finally {
			/*
			 * Had a discussion with Professor Rob and this cannot be avoided without
			 * complicating the code
			 */
			if (sqlImplementation != null) {
				sqlImplementation.cleanup();
			}
		}
		return null;
	}

	@Override
	public ArrayList<HashMap> getPasswordHistory(ArrayList criteriaList) {
		logger.log(Level.INFO, "Fetching password history for user=" + criteriaList.get(0));
		ISQLMethods sqlImplementation = null;
		try (Connection connection = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
				.getConnectionManager().getDBConnection();) {
			
			sqlImplementation = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
					.getSqlMethods(connection);
			ArrayList resultList = sqlImplementation
					.selectQuery(UserManagementDataBaseQueriesUtil.GET_PASSWORD_HISTORY.toString(), criteriaList);
			return resultList;
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQL Exception while fetching password history", e);
		} finally {
			/*
			 * Had a discussion with Professor Rob and this cannot be avoided without
			 * complicating the code
			 */
			if (sqlImplementation != null) {
				sqlImplementation.cleanup();
			}
		}
		return null;
	}
}