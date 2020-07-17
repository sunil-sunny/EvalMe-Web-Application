package com.group18.asdc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.database.ISQLMethods;
import com.group18.asdc.util.PasswordPolicyQueries;

public class PasswordPolicyDB implements IPasswordPolicyDB {

	private Logger logger = Logger.getLogger(IPasswordPolicyDB.class.getName());

	@Override
	public ArrayList loadBasePoliciesFromDB() {
		logger.log(Level.INFO, "Loading base password policies from DB");
		ISQLMethods sqlImplementation = null;
		ArrayList policiesList = new ArrayList<>();
		try (Connection connection = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
				.getConnectionManager().getDBConnection();) {
			sqlImplementation = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
					.getSqlMethods(connection);
			policiesList = sqlImplementation.selectQuery(PasswordPolicyQueries.GET_BASEPASSWORD_POLICIES.toString(),
					new ArrayList<>());
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQL Exception while fetching base password policies ", e);
		} finally {
			/*
			 * Had a discussion with Professor Rob and this cannot be avoided without
			 * complicating the code
			 */
			if (sqlImplementation != null) {
				sqlImplementation.cleanup();
			}
		}
		return policiesList;
	}

	@Override
	public ArrayList loadPoliciesFromDB() {
		logger.log(Level.INFO, "Loading history password policies from DB");
		ISQLMethods sqlImplementation = null;
		ArrayList policiesList = new ArrayList<>();
		try (Connection connection = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
				.getConnectionManager().getDBConnection();) {
			
			sqlImplementation = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
					.getSqlMethods(connection);
			policiesList = sqlImplementation.selectQuery(PasswordPolicyQueries.GET_HISTORYPASSWORD_POLICIES.toString(),
					new ArrayList<>());
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQL Exception while loading history password policies ", e);
		} finally {
			/*
			 * Had a discussion with Professor Rob and this cannot be avoided without
			 * complicating the code
			 */
			if (sqlImplementation != null) {
				sqlImplementation.cleanup();
			}
		}
		return policiesList;
	}
}