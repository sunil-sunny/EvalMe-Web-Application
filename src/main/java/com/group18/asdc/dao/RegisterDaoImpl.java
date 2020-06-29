package com.group18.asdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.group18.asdc.database.ConnectionManager;
import com.group18.asdc.entities.UserRegistartionDetails;
import com.group18.asdc.util.DataBaseQueriesUtil;

public class RegisterDaoImpl implements RegisterDao {

	private Logger log = Logger.getLogger(RegisterDaoImpl.class.getName());

	@Override
	public boolean registeruser(UserRegistartionDetails registerDetails) {

		Connection connection = null;
		PreparedStatement registerUserStatement = null;
		PreparedStatement assignRoleStatement = null;
		boolean isUserRegisterd = false;
		boolean isGuestRoleAssigned = false;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			connection.setAutoCommit(false);
			registerUserStatement = connection.prepareStatement(DataBaseQueriesUtil.insertUser);
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(registerDetails.getPassword());
			registerUserStatement.setString(1, registerDetails.getBannerid());
			registerUserStatement.setString(2, registerDetails.getLastname());
			registerUserStatement.setString(3, registerDetails.getFirstname());
			registerUserStatement.setString(4, registerDetails.getEmailid());
			registerUserStatement.setString(5, hashedPassword);
			int registerStatus = registerUserStatement.executeUpdate();
			if (registerStatus > 0) {
				isUserRegisterd = true;
			}
			assignRoleStatement = connection.prepareStatement(DataBaseQueriesUtil.allocateSystemRole);
			assignRoleStatement.setInt(1, 2);
			assignRoleStatement.setString(2, registerDetails.getBannerid());
			int assignRoleResult = assignRoleStatement.executeUpdate();
			if (assignRoleResult > 0) {
				isGuestRoleAssigned = true;
			}
			if (isGuestRoleAssigned && isUserRegisterd) {
				connection.commit();
			}
		} catch (SQLException e) {
			log.info("SQL Exception occured while Registering the user");
		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
				if (registerUserStatement != null) {
					registerUserStatement.close();
				}
				if (assignRoleStatement != null) {
					assignRoleStatement.close();
				}
				log.info("Closng connections after registering the user");
			} catch (SQLException e) {
				log.info("SQL Exception occured while closing connections and statements after registering user ");
			}
		}
		return isUserRegisterd && isGuestRoleAssigned;
	}

	@Override
	public boolean checkUserWithEmail(String email) {
		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		ResultSet theResultSet = null;
		boolean isUserExists = false;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			thePreparedStatement = connection.prepareStatement(DataBaseQueriesUtil.checkUserWithEmail);
			thePreparedStatement.setString(1, email);
			theResultSet = thePreparedStatement.executeQuery();
			if (theResultSet.next()) {
				isUserExists = true;
			}
		} catch (SQLException e) {
			log.info("SQL Exception while checking the user with email");
		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
				if (thePreparedStatement != null) {
					thePreparedStatement.close();
				}
				if (theResultSet != null) {
					theResultSet.close();
				}
				log.info("Closed Connections after checking user with email");
			} catch (SQLException e) {
				log.info("SQL Exception occured while closing the statement and connection after checking the user with email");
			}

		}
		return isUserExists;
	}

	@Override
	public boolean checkUserWithBannerId(String bannerId) {

		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		ResultSet theResultSet = null;
		boolean isUserExists=false;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			thePreparedStatement = connection.prepareStatement(DataBaseQueriesUtil.checkUserWithBannerId);
			thePreparedStatement.setString(1, bannerId);
			theResultSet = thePreparedStatement.executeQuery();
			if (theResultSet.next()) {
				isUserExists= true;
			}
		} catch (SQLException e) {
			log.info("SQL Exception occured while checking the user with banner id");
		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
				if (thePreparedStatement != null) {
					thePreparedStatement.close();
				}
				if (theResultSet != null) {
					theResultSet.close();
				}
				log.info("Closing the connection after checking the user with banner id");
			} catch (SQLException e) {
				log.info("SQL Exception while closing connection and statements after checking user with banner id");
			}
		}
		return isUserExists;
	}
}
