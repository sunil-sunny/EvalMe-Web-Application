package com.group18.asdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.group18.asdc.database.ConnectionManager;
import com.group18.asdc.entities.UserRegistartionDetails;
import com.group18.asdc.util.UserManagementDataBaseQueriesUtil;

public class RegisterDaoImpl implements RegisterDao {

	private Logger log = Logger.getLogger(RegisterDaoImpl.class.getName());

	@Override
	public boolean registeruser(UserRegistartionDetails registerDetails) {

		Connection connection = null;
		PreparedStatement registerUserStatement = null;
		PreparedStatement assignRoleStatement = null;
		boolean isUserRegisterd = Boolean.FALSE;
		boolean isGuestRoleAssigned = Boolean.FALSE;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			connection.setAutoCommit(Boolean.FALSE);
			registerUserStatement = connection
					.prepareStatement(UserManagementDataBaseQueriesUtil.INSERT_USER.toString());
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(registerDetails.getPassword());
			registerUserStatement.setString(1, registerDetails.getBannerid());
			registerUserStatement.setString(2, registerDetails.getLastname());
			registerUserStatement.setString(3, registerDetails.getFirstname());
			registerUserStatement.setString(4, registerDetails.getEmailid());
			registerUserStatement.setString(5, hashedPassword);
			int registerStatus = registerUserStatement.executeUpdate();
			if (registerStatus > 0) {
				isUserRegisterd = Boolean.TRUE;
			}
			assignRoleStatement = connection
					.prepareStatement(UserManagementDataBaseQueriesUtil.ALLOCATE_SYSTEM_ROLE.toString());
			assignRoleStatement.setInt(1, 2);
			assignRoleStatement.setString(2, registerDetails.getBannerid());
			int assignRoleResult = assignRoleStatement.executeUpdate();
			if (assignRoleResult > 0) {
				isGuestRoleAssigned = Boolean.TRUE;
			}
			if (isGuestRoleAssigned && isUserRegisterd) {
				log.info("User with id " + registerDetails.getBannerid() + " has been successfully registered");
				connection.commit();
			} else {
				log.log(Level.WARNING, "User with id " + registerDetails.getBannerid() + " has not been registered");
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE,
					"SQL Exception occured while Registering the user with id " + registerDetails.getBannerid());
		} finally {
			try {
				if (null != connection) {
					connection.close();
				}
				if (null != registerUserStatement) {
					registerUserStatement.close();
				}
				if (null != assignRoleStatement) {
					assignRoleStatement.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE,
						"SQL Exception occured while closing connections and statements after registering user");
			}
		}
		return isUserRegisterd && isGuestRoleAssigned;
	}

	@Override
	public boolean checkUserWithEmail(String email) {
		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		ResultSet theResultSet = null;
		boolean isUserExists = Boolean.FALSE;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			thePreparedStatement = connection
					.prepareStatement(UserManagementDataBaseQueriesUtil.CHECK_USER_WITH_EMAIL.toString());
			thePreparedStatement.setString(1, email);
			theResultSet = thePreparedStatement.executeQuery();
			if (theResultSet.next()) {
				isUserExists = Boolean.TRUE;
			} else {
				isUserExists = Boolean.FALSE;
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL Exception while checking the user with email " + email);
		} finally {
			try {
				if (null != connection) {
					connection.close();
				}
				if (null != thePreparedStatement) {
					thePreparedStatement.close();
				}
				if (null != theResultSet) {
					theResultSet.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE,
						"SQL Exception occured while closing the statement and connection after checking the user with email");
			}
		}
		return isUserExists;
	}

	@Override
	public boolean checkUserWithBannerId(String bannerId) {

		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		ResultSet theResultSet = null;
		boolean isUserExists = Boolean.FALSE;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			thePreparedStatement = connection
					.prepareStatement(UserManagementDataBaseQueriesUtil.CHECK_USER_WITH_BANNERID.toString());
			thePreparedStatement.setString(1, bannerId);
			theResultSet = thePreparedStatement.executeQuery();
			if (theResultSet.next()) {
				isUserExists = Boolean.TRUE;
			} else {
				isUserExists = Boolean.FALSE;
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL Exception occured while checking the user with banner id " + bannerId);
		} finally {
			try {
				if (null != connection) {
					connection.close();
				}
				if (null != thePreparedStatement) {
					thePreparedStatement.close();
				}
				if (null != theResultSet) {
					theResultSet.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE,
						"SQL Exception while closing connection and statements after checking user with banner id");
			}
		}
		return isUserExists;
	}
}
