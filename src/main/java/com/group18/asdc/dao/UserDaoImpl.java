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

import org.springframework.stereotype.Repository;

import com.group18.asdc.database.ConnectionManager;
import com.group18.asdc.database.SQLMethods;
import com.group18.asdc.database.SQLQueries;
import com.group18.asdc.database.SQLStatus;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;
import com.group18.asdc.util.UserManagementDataBaseQueriesUtil;

@Repository
public class UserDaoImpl implements UserDao {

	private Logger logger = Logger.getLogger(UserDaoImpl.class.getName());

	@Override
	public boolean isUserExists(User user) {
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement checkUser = null;
		boolean isUserExits = Boolean.FALSE;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			checkUser = connection.prepareStatement(UserManagementDataBaseQueriesUtil.IS_USER_EXISTS.toString());
			checkUser.setString(1, user.getBannerId());
			resultSet = checkUser.executeQuery();
			if (resultSet.next()) {
				isUserExits = Boolean.TRUE;
				logger.log(Level.INFO, "User with id " + user.getBannerId() + " is exists");
			} else {
				isUserExits = Boolean.FALSE;
				logger.log(Level.FINE, "User with id " + user.getBannerId() + " is not exists");
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE,
					"SQL Exception occurred while checking if user exists or not for user id " + user.getBannerId());
		} finally {
			try {
				if (null != resultSet) {
					resultSet.close();
				}
				if (null != connection) {
					connection.close();
				}
				if (null != checkUser) {
					checkUser.close();
				}
			} catch (SQLException e) {
				logger.log(Level.SEVERE,
						"SQL Exception occurred while closing the connections and statements after checking if user exists or not");
			}
		}
		return isUserExits;
	}

	@Override
	public User getUserById(String bannerId) {
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement getUser = null;
		User user = null;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			String userSql = UserManagementDataBaseQueriesUtil.GET_USER_BY_ID.toString();
			getUser = connection.prepareStatement(userSql);
			getUser.setString(1, bannerId);
			resultSet = getUser.executeQuery();
			while (resultSet.next()) {
				user = new User();
				user.setBannerId(resultSet.getString("bannerid"));
				user.setEmail(resultSet.getString("emailid"));
				user.setFirstName(resultSet.getString("firstname"));
				user.setLastName(resultSet.getString("lastname"));
			}

		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQL Exception while getting user with banner id " + bannerId);
		} finally {
			try {
				if (null != resultSet) {
					resultSet.close();
				}
				if (null != connection) {
					connection.close();
				}
				if (null != getUser) {
					getUser.close();
				}
			} catch (SQLException e) {
				logger.log(Level.SEVERE,
						"SQL Exception while closing the connections and statements after getting user with banner id "
								+ bannerId);
			}
		}
		return user;
	}

	@Override
	public List<User> getAllUsersByCourse(int courseId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSetForStudentList = null;
		List<User> studentList = new ArrayList<User>();
		User user = null;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			preparedStatement = connection
					.prepareStatement(UserManagementDataBaseQueriesUtil.GET_ALL_USERS_RELATED_TO_COURSE.toString());
			preparedStatement.setInt(1, courseId);
			resultSetForStudentList = preparedStatement.executeQuery();
			while (resultSetForStudentList.next()) {
				user = new User();
				user.setBannerId(resultSetForStudentList.getString("bannerid"));
				user.setEmail(resultSetForStudentList.getString("emailid"));
				user.setFirstName(resultSetForStudentList.getString("firstname"));
				user.setLastName(resultSetForStudentList.getString("lastname"));
				studentList.add(user);
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQL Exception while getting all the users realted to course with id " + courseId);
		} finally {
			try {
				if (null != connection) {
					connection.close();
				}
				if (null != resultSetForStudentList) {
					resultSetForStudentList.close();
				}
				if (null != preparedStatement) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				logger.log(Level.SEVERE,
						"SQL Exception while closing the connections and statements after getting all the users related to course with id "
								+ courseId);
			}
		}
		return studentList;
	}

	@Override
	public int loadUserWithBannerId(ArrayList<Object> valueList, User userObj) {
		SQLMethods sqlImplementation = null;
		int sqlCodes = SQLStatus.NO_DATA_AVAILABLE;
		try {
			Connection connection = ConnectionManager.getInstance().getDBConnection();
			sqlImplementation = new SQLMethods(connection);
			ArrayList<HashMap<String, Object>> rowsList = sqlImplementation
					.selectQuery(SQLQueries.GET_USER_WITH_BANNER_ID.toString(), valueList);
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
			logger.log(Level.SEVERE, "SQL Exception", e);
			sqlCodes = SQLStatus.SQL_ERROR;
		} finally {
			if (null != sqlImplementation) {
				sqlImplementation.cleanup();
			}
		}
		return sqlCodes;
	}

	@Override
	public Boolean updatePassword(ArrayList<Object> criteriaList, ArrayList<Object> valueList) {
		SQLMethods sqlImplementation = null;
		boolean isUpdateSuccessful = Boolean.FALSE;
		try {
			Connection connection = ConnectionManager.getInstance().getDBConnection();
			sqlImplementation = new SQLMethods(connection);
			Integer rowCount = sqlImplementation.updateQuery(SQLQueries.UPDATE_PASSWORD_FOR_USER.toString(), valueList,
					criteriaList);
			isUpdateSuccessful = rowCount > 0;
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQL Exception", e);
		} finally {
			if (null != sqlImplementation) {
				sqlImplementation.cleanup();
			}
		}
		return isUpdateSuccessful;
	}

	@Override
	public ArrayList getUserRoles(ArrayList<Object> criteriaList) {
		ArrayList rolesList = new ArrayList<>();
		SQLMethods sqlImplementation = null;
		try {
			Connection connection = ConnectionManager.getInstance().getDBConnection();
			sqlImplementation = new SQLMethods(connection);
			ArrayList<HashMap<String, Object>> valuesList = sqlImplementation
					.selectQuery(SQLQueries.GET_USER_ROLES.toString(), criteriaList);
			if (valuesList != null && valuesList.size() > 0) {
				for (HashMap valueMap : valuesList) {
					rolesList.add(valueMap.get("rolename"));
				}
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQL Exception", e);
		} finally {
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
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			statement = connection.prepareStatement(UserManagementDataBaseQueriesUtil.IS_USER_EXISTS.toString());
			statement.setString(1, instructorId);
			resultset = statement.executeQuery();
			statement.close();
			if (null == resultset) {
				returnValue = Boolean.FALSE;
			} else {
				resultset.close();
				statement = connection
						.prepareStatement(UserManagementDataBaseQueriesUtil.IS_INSTRUCTOR_A_STUDENT.toString());
				statement.setString(1, instructorId);
				statement.setInt(2, courseId);
				resultset = statement.executeQuery();
				if (null != resultset) {
					returnValue = Boolean.FALSE;
				}
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE,
					"SQL Exception while Checking the user as instructor or not for course " + course.getCourseId());
		} finally {
			try {
				if (null != statement) {
					statement.close();
				}
				if (null != resultset) {
					resultset.close();
				}
				if (null != connection) {
					connection.close();
				}
			} catch (SQLException e) {
				logger.log(Level.SEVERE,
						"SQL Exception while closing connections Checking the user as instructor or not for course "
								+ course.getCourseId());
			}
		}
		return returnValue;
	}
}