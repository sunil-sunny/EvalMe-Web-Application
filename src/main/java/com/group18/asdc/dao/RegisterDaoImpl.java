package com.group18.asdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.database.UserManagementDataBaseQueriesUtil;
import com.group18.asdc.entities.UserRegistartionDetails;

public class RegisterDaoImpl implements RegisterDao {

	private Logger log = Logger.getLogger(RegisterDaoImpl.class.getName());

	@Override
	public boolean registeruser(UserRegistartionDetails registerDetails) {
		boolean isUserRegisterd = Boolean.FALSE;
		boolean isGuestRoleAssigned = Boolean.FALSE;
		log.log(Level.INFO,"Registering new user into the database user="+registerDetails.getBannerid());
		try (Connection connection = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
		.getConnectionManager().getDBConnection();
				PreparedStatement registerUserStatement = connection
						.prepareStatement(UserManagementDataBaseQueriesUtil.INSERT_USER.toString());
				PreparedStatement assignRoleStatement = connection
						.prepareStatement(UserManagementDataBaseQueriesUtil.ALLOCATE_SYSTEM_ROLE.toString());){
			
			connection.setAutoCommit(Boolean.FALSE);
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
	
			assignRoleStatement.setInt(1, 2);
			assignRoleStatement.setString(2, registerDetails.getBannerid());
			int assignRoleResult = assignRoleStatement.executeUpdate();
			if (assignRoleResult > 0) {
				isGuestRoleAssigned = Boolean.TRUE;
			}
			if (isGuestRoleAssigned && isUserRegisterd) {
				log.log(Level.INFO,
						"User with id=" + registerDetails.getBannerid() + " has been successfully registered");
				connection.commit();
			} else {
				log.log(Level.WARNING, "User with id=" + registerDetails.getBannerid() + " has not been registered");
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE,
					"SQL Exception occured while Registering the user with id=" + registerDetails.getBannerid());
		} 
		return isUserRegisterd && isGuestRoleAssigned;
	}

	@Override
	public boolean checkUserWithEmail(String email) {

		boolean isUserExists = Boolean.FALSE;
		
		try (Connection connection = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
				.getConnectionManager().getDBConnection();
				PreparedStatement thePreparedStatement = connection
						.prepareStatement(UserManagementDataBaseQueriesUtil.CHECK_USER_WITH_EMAIL.toString());){
			
			thePreparedStatement.setString(1, email);
			ResultSet theResultSet = thePreparedStatement.executeQuery();
			if (theResultSet.next()) {
				isUserExists = Boolean.TRUE;
			} else {
				isUserExists = Boolean.FALSE;
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL Exception while checking the user with email=" + email);
		} 
		return isUserExists;
	}

	@Override
	public boolean checkUserWithBannerId(String bannerId) {

		boolean isUserExists = Boolean.FALSE;
	
		try (Connection connection = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
				.getConnectionManager().getDBConnection();
				PreparedStatement thePreparedStatement = connection
						.prepareStatement(UserManagementDataBaseQueriesUtil.CHECK_USER_WITH_BANNERID.toString());){
			
			thePreparedStatement.setString(1, bannerId);
			ResultSet theResultSet = thePreparedStatement.executeQuery();
			if (theResultSet.next()) {
				isUserExists = Boolean.TRUE;
			} else {
				isUserExists = Boolean.FALSE;
			}
		} catch (SQLException e) {

			log.log(Level.SEVERE, "SQL Exception occured while checking the user with banner id=" + bannerId);

		}
		return isUserExists;
	}
}