package com.group18.asdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.database.ConnectionManager;
import com.group18.asdc.database.ISQLMethods;
import com.group18.asdc.database.SQLStatus;
import com.group18.asdc.database.UserManagementDataBaseQueriesUtil;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;

import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

	private Logger logger = Logger.getLogger(UserDaoImpl.class.getName());

	@Override
	public boolean isUserExists(User user) {

		boolean isUserExits = Boolean.FALSE;
		try (Connection connection = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
				.getConnectionManager().getDBConnection();
				PreparedStatement checkUser = connection
						.prepareStatement(UserManagementDataBaseQueriesUtil.IS_USER_EXISTS.toString());) {

			checkUser.setString(1, user.getBannerId());
			ResultSet resultSet = checkUser.executeQuery();
			if (resultSet.next()) {
				isUserExits = Boolean.TRUE;
				logger.log(Level.INFO, "User with id=" + user.getBannerId() + " exists");
			} else {
				isUserExits = Boolean.FALSE;
				logger.log(Level.FINE, "User with id=" + user.getBannerId() + " does not exist");
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE,
					"SQL Exception occurred while checking if user exists or not for user id=" + user.getBannerId());
		} 
		return isUserExits;
	}

	@Override
	public User getUserById(String bannerId) {

		User user = null;
		try (Connection connection = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
				.getConnectionManager().getDBConnection();
				PreparedStatement getUser = connection
						.prepareStatement(UserManagementDataBaseQueriesUtil.GET_USER_BY_ID.toString());) {

			getUser.setString(1, bannerId);
			ResultSet resultSet = getUser.executeQuery();
			while (resultSet.next()) {
				user = SystemConfig.getSingletonInstance().getModelAbstractFactory().getUser();
				user.setBannerId(resultSet.getString("bannerid"));
				user.setEmail(resultSet.getString("emailid"));
				user.setFirstName(resultSet.getString("firstname"));
				user.setLastName(resultSet.getString("lastname"));
			}

		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQL Exception while getting user with banner id=" + bannerId);
		} 
		return user;
	}

	@Override
	public List<User> getAllUsersByCourse(int courseId) {

		List<User> studentList = new ArrayList<User>();
		User user = null;

		try (Connection connection = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
				.getConnectionManager().getDBConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(
						UserManagementDataBaseQueriesUtil.GET_ALL_USERS_RELATED_TO_COURSE.toString());) {

			preparedStatement.setInt(1, courseId);
			ResultSet resultSetForStudentList = preparedStatement.executeQuery();
			while (resultSetForStudentList.next()) {
				user = SystemConfig.getSingletonInstance().getModelAbstractFactory().getUser();
				user.setBannerId(resultSetForStudentList.getString("bannerid"));
				user.setEmail(resultSetForStudentList.getString("emailid"));
				user.setFirstName(resultSetForStudentList.getString("firstname"));
				user.setLastName(resultSetForStudentList.getString("lastname"));
				studentList.add(user);
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQL Exception while getting all the users realted to course with id=" + courseId);
		} 
		return studentList;
	}

	@Override
	public int loadUserWithBannerId(ArrayList<Object> valueList, User userObj) {
		logger.log(Level.INFO, "Loading user object values from db for user=" + valueList.get(0));
		ISQLMethods sqlImplementation = null;
		int sqlCodes = SQLStatus.NO_DATA_AVAILABLE;
		try (Connection connection = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
				.getConnectionManager().getDBConnection();) {
			sqlImplementation = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
					.getSqlMethods(connection);
			ArrayList<HashMap<String, Object>> rowsList = sqlImplementation
					.selectQuery(UserManagementDataBaseQueriesUtil.GET_USER_WITH_BANNER_ID.toString(), valueList);
			if (rowsList.size() > 0) {
				HashMap<String, Object> valuesMap = rowsList.get(0);
				userObj.setBannerId((String) valuesMap.get("bannerid"));
				userObj.setEmail((String) valuesMap.get("emailid"));
				userObj.setFirstName((String) valuesMap.get("firstname"));
				userObj.setLastName((String) valuesMap.get("lastname"));
				userObj.setPassword((String) valuesMap.get("password"));
				sqlCodes = SQLStatus.SUCCESSFUL;
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQL Exception while loading user object ",e);
			sqlCodes = SQLStatus.SQL_ERROR;
		} finally {
			/*
			 * Had a discussion with Professor Rob and this cannot be avoided without
			 * complicating the code
			 */
			if (null != sqlImplementation) {
				sqlImplementation.cleanup();
			}
		}
		return sqlCodes;
	}

	@Override
	public Boolean updatePassword(ArrayList<Object> criteriaList, ArrayList<Object> valueList) {
		logger.log(Level.INFO, "Updating password in the database for user=" + criteriaList.get(0));
		ISQLMethods sqlImplementation = null;
		boolean isUpdateSuccessful = Boolean.FALSE;
		try (Connection connection = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
				.getConnectionManager().getDBConnection();) {

			sqlImplementation = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
					.getSqlMethods(connection);
			Integer rowCount = sqlImplementation.updateQuery(
					UserManagementDataBaseQueriesUtil.UPDATE_PASSWORD_FOR_USER.toString(), valueList, criteriaList);
			isUpdateSuccessful = rowCount > 0;
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQL Exception while updating password", e);
		} finally {
			/*
			 * Had a discussion with Professor Rob and this cannot be avoided without
			 * complicating the code
			 */
			if (null != sqlImplementation) {
				sqlImplementation.cleanup();
			}
		}
		return isUpdateSuccessful;
	}

	@Override
	public ArrayList getUserRoles(ArrayList<Object> criteriaList) {
		logger.log(Level.INFO, "Fetching user roles from the database for user=" + criteriaList.get(0));
		ArrayList rolesList = new ArrayList<>();
		ISQLMethods sqlImplementation = null;
		try (Connection connection = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
				.getConnectionManager().getDBConnection();) {

			sqlImplementation = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
					.getSqlMethods(connection);
			ArrayList<HashMap<String, Object>> valuesList = sqlImplementation
					.selectQuery(UserManagementDataBaseQueriesUtil.GET_USER_ROLES.toString(), criteriaList);
			if (valuesList == null || valuesList.size() == 0) {
				rolesList = new ArrayList<>();
			}
			else{
				for (HashMap valueMap : valuesList) {
					rolesList.add(valueMap.get("rolename"));
				}
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQL Exception", e);
		} finally {
			/*
			 * Had a discussion with Professor Rob and this cannot be avoided without
			 * complicating the code
			 */
			if (null != sqlImplementation) {
				sqlImplementation.cleanup();
			}
		}
		return rolesList;
	}

	@Override
	public boolean isUserInstructor(Course course) {

		boolean returnValue = Boolean.TRUE;
		String instructorId = course.getInstructorName().getBannerId();
		int courseId = course.getCourseId();

		try (Connection connection = ConnectionManager.getInstance().getDBConnection();
				PreparedStatement statement = connection
						.prepareStatement(UserManagementDataBaseQueriesUtil.IS_USER_EXISTS.toString());
				PreparedStatement statementInstructorStudent = connection
						.prepareStatement(UserManagementDataBaseQueriesUtil.IS_INSTRUCTOR_A_STUDENT.toString());) {

			statement.setString(1, instructorId);
			ResultSet resultset = statement.executeQuery();
			if (resultset.next()) {
				statementInstructorStudent.setString(1, instructorId);
				statementInstructorStudent.setInt(2, courseId);
				resultset = statementInstructorStudent.executeQuery();
				if (resultset.next()) {
					returnValue = Boolean.FALSE;
				} else {
					returnValue = Boolean.TRUE;
				}
			} else {
				returnValue = Boolean.FALSE;
			}

		} catch (SQLException e) {
			logger.log(Level.SEVERE,
					"SQL Exception while Checking the user as instructor or not for course=" + course.getCourseId());
		} 
		return returnValue;
	}
}