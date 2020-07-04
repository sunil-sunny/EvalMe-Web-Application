package com.group18.asdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.stereotype.Repository;
import com.group18.asdc.database.ConnectionManager;
import com.group18.asdc.database.SQLMethods;
import com.group18.asdc.database.SQLQueries;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;
import com.group18.asdc.util.DataBaseQueriesUtil;

@Repository
public class UserDaoImpl implements UserDao {

	private Logger log = Logger.getLogger(UserDaoImpl.class.getName());

	@Override
	public boolean isUserExists(User user) {
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement checkUser = null;
		boolean isUserExits = false;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			checkUser = connection.prepareStatement(DataBaseQueriesUtil.isUserExists);
			checkUser.setString(1, user.getBannerId());
			resultSet = checkUser.executeQuery();
			log.info("In User Dao to check if user exists or not");
			if (resultSet.next()) {
				isUserExits = true;
			} else {
				isUserExits = false;
			}
		} catch (SQLException e) {
			log.info("SQL Exception occured while checking if user exists or not");
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
				log.info("closing connection after having a check if user exists or not");
			} catch (SQLException e) {
				log.info("SQL Exception occured while closing the connections "
						+ "and statements after checking if user exists or not");
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
			String userSql = DataBaseQueriesUtil.getUserById;
			getUser = connection.prepareStatement(userSql);
			getUser.setString(1, bannerId);
			log.info("In User Dao to get the user for given banner id");
			resultSet = getUser.executeQuery();
			while (resultSet.next()) {
				user = new User();
				user.setBannerId(resultSet.getString("bannerid"));
				user.setEmail(resultSet.getString("emailid"));
				user.setFirstName(resultSet.getString("firstname"));
				user.setLastName(resultSet.getString("lastname"));
			}

		} catch (SQLException e) {
			log.info("SQL Exception while getting user by banner id");
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
				log.info("closing the connection after getting user by banner id");
			} catch (SQLException e) {
				log.info("SQL Exception while closing the connections and statements after getting user by banner id");
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
			preparedStatement = connection.prepareStatement(DataBaseQueriesUtil.getAlluserRelatedToCourse);
			log.info("In users dao getting all users based on course id");
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
			log.info("SQL Exception while getting all the users realted to course");
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
				log.info("Closing connections after getting users based on course");
			} catch (SQLException e) {
				log.info("SQL Exception while closing the connections "
						+ "and statements after getting all the users realted to course");
			}
		}
		return studentList;
	}

	@Override
	public void loadUserWithBannerId(ArrayList<Object> valueList, User userObj) {
		SQLMethods sqlImplementation = null;
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != sqlImplementation) {
				sqlImplementation.cleanup();
			}
		}
	}

	@Override
	public Boolean updatePassword(ArrayList<Object> criteriaList, ArrayList<Object> valueList) {
		SQLMethods sqlImplementation = null;
		try {
			Connection connection = ConnectionManager.getInstance().getDBConnection();
			sqlImplementation = new SQLMethods(connection);
			Integer rowCount = sqlImplementation.updateQuery(SQLQueries.UPDATE_PASSWORD_FOR_USER.toString(), valueList,
					criteriaList);
			return rowCount > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != sqlImplementation) {
				sqlImplementation.cleanup();
			}
		}
		return Boolean.FALSE;
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
			e.printStackTrace();
		} finally {
			if (null != sqlImplementation) {
				sqlImplementation.cleanup();
			}
		}
		return rolesList;
	}

	@Override
	public boolean isUserInstructor(Course course) {
		boolean returnValue = true;
		String instructorId = course.getInstructorName().getBannerId();
		int courseId = course.getCourseId();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			statement = connection.prepareStatement(DataBaseQueriesUtil.isUserExists);
			statement.setString(1, instructorId);
			resultset = statement.executeQuery();
			statement.close();
			if (null == resultset) {
				returnValue = false;
			} else {
				resultset.close();
				statement = connection.prepareStatement(DataBaseQueriesUtil.isInstructorStudent);
				statement.setString(1, instructorId);
				statement.setInt(2, courseId);
				resultset = statement.executeQuery();
				if (null != resultset) {
					returnValue = false;
				}
			}
		} catch (SQLException e) {
			log.info("Error closing connection.");
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
				log.info("Error closing connection.");
			}
		}
		return returnValue;
	}
}