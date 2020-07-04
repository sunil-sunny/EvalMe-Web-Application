package com.group18.asdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.stereotype.Repository;
import com.group18.asdc.SystemConfig;
import com.group18.asdc.controller.CourseController;
import com.group18.asdc.database.ConnectionManager;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.Role;
import com.group18.asdc.entities.User;
import com.group18.asdc.util.DataBaseQueriesUtil;

@Repository
public class CourseDetailsDaoImpl implements CourseDetailsDao {

	private Logger log = Logger.getLogger(CourseController.class.getName());

	@Override
	public List<Course> getAllCourses() {

		UserDao userDao = SystemConfig.getSingletonInstance().getTheUserDao();
		Connection con = null;
		Statement getCourses = null;
		PreparedStatement getCourseRoles = null;
		ResultSet resultSetAllCourses = null;
		ResultSet resultSetAllCourseRoles = null;
		List<Course> allCourses = new ArrayList<Course>();
		try {
			log.info("In CourseDao to get all courses");
			con = ConnectionManager.getInstance().getDBConnection();
			getCourses = con.createStatement();
			resultSetAllCourses = getCourses.executeQuery(DataBaseQueriesUtil.getAllCourses);
			getCourseRoles = con.prepareStatement(DataBaseQueriesUtil.getCourseDetails);
			Course course = null;
			while (resultSetAllCourses.next()) {
				course = new Course();
				List<User> students = new ArrayList<User>();
				List<User> taList = new ArrayList<User>();
				course.setCourseId(resultSetAllCourses.getInt("courseid"));
				course.setCourseName(resultSetAllCourses.getString("coursename"));
				getCourseRoles.setInt(1, resultSetAllCourses.getInt("courseid"));
				resultSetAllCourseRoles = getCourseRoles.executeQuery();
				while (resultSetAllCourseRoles.next()) {
					String role = resultSetAllCourseRoles.getString("rolename");
					String bannerId = resultSetAllCourseRoles.getString("bannerid");
					if (role.equalsIgnoreCase(Role.instructor)) {
						course.setInstructorName(userDao.getUserById(bannerId));
					} else if (role.equalsIgnoreCase(Role.student)) {
						students.add(userDao.getUserById(bannerId));
					} else if (role.equalsIgnoreCase(Role.ta)) {
						taList.add(userDao.getUserById(bannerId));
					}
				}
				course.setTaList(taList);
				course.setStudentList(students);
				resultSetAllCourseRoles.close();
				allCourses.add(course);
			}
		} catch (SQLException e) {
			log.info("SQL Exception occured while getting all courses");
		} finally {
			try {
				if (null != getCourses) {
					getCourses.close();
				}
				if (null != con) {
					con.close();
				}
				if (null != getCourseRoles) {
					getCourseRoles.close();
				}
				if (null != resultSetAllCourses) {
					resultSetAllCourses.close();
				}
				if (null != resultSetAllCourseRoles) {
					resultSetAllCourseRoles.close();
				}
				log.info("closing all the data connections in after getting all courses");
			} catch (SQLException e) {
				log.info("Error while closing the connection and statements after getting all courses");
			}
		}
		return allCourses;
	}

	@Override
	public List<Course> getCoursesWhereUserIsStudent(User user) {
		CourseDetailsDao theCourseDetailsDao = SystemConfig.getSingletonInstance().getTheCourseDetailsDao();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		List<Course> getCoursesAsStudent = new ArrayList<Course>();
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			preparedStatement = connection.prepareStatement(DataBaseQueriesUtil.getCoursesWhereUserIsStudent);
			preparedStatement.setString(1, user.getBannerId());
			log.info("In course Dao after getting all courses where user is Student");
			resultset = preparedStatement.executeQuery();
			Course course = null;
			while (resultset.next()) {
				course = new Course();
				int courseid = resultset.getInt("courseid");
				course.setCourseId(courseid);
				course.setCourseName(resultset.getString("coursename"));
				course.setInstructorName(theCourseDetailsDao.getInstructorForCourse(courseid));
				getCoursesAsStudent.add(course);
			}
		} catch (SQLException e) {
			log.info("SQL Exception occured while getting courses where user is student");
		} finally {
			try {
				if (null != connection) {
					connection.close();
				}
				if (null != preparedStatement) {
					preparedStatement.close();
				}
				if (null != resultset) {
					resultset.close();
				}
				log.info("Closing connection after getting all courses where user is Student");
			} catch (SQLException e) {
				log.info(" SQL Exception while closing the connection and statements");
			}
		}
		return getCoursesAsStudent;
	}

	@Override
	public List<Course> getCoursesWhereUserIsInstrcutor(User user) {
		CourseDetailsDao theCourseDetailsDao = SystemConfig.getSingletonInstance().getTheCourseDetailsDao();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		List<Course> getCoursesAsInstructor = new ArrayList<Course>();
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			preparedStatement = connection.prepareStatement(DataBaseQueriesUtil.getCoursesWhereUserIsInstructor);
			preparedStatement.setString(1, user.getBannerId());
			resultset = preparedStatement.executeQuery();
			log.info("In course Dao after getting all courses where user is Instructor");
			Course course = null;
			while (resultset.next()) {
				course = new Course();
				int courseid = resultset.getInt("courseid");
				course.setCourseId(courseid);
				course.setCourseName(resultset.getString("coursename"));
				course.setInstructorName(theCourseDetailsDao.getInstructorForCourse(courseid));
				getCoursesAsInstructor.add(course);
			}
		} catch (SQLException e) {
			log.info("SQL Exception while getting courses where user is instructor");
		} finally {
			try {
				if (null != connection) {
					connection.close();
				}
				if (null != preparedStatement) {
					preparedStatement.close();
				}
				if (null != resultset) {
					resultset.close();
				}
			} catch (SQLException e) {
				log.info("closing connection after getting all courses where user is Instructor");
			}
		}
		return getCoursesAsInstructor;
	}

	@Override
	public List<Course> getCoursesWhereUserIsTA(User user) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		List<Course> getCoursesAsTA = new ArrayList<Course>();
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			preparedStatement = connection.prepareStatement(DataBaseQueriesUtil.getCoursesWhereUserIsTA);
			preparedStatement.setString(1, user.getBannerId());
			resultset = preparedStatement.executeQuery();
			log.info("In course Dao after getting all courses where user is TA");
			Course course = null;
			while (resultset.next()) {
				course = new Course();
				int courseid = resultset.getInt("courseid");
				course.setCourseId(courseid);
				course.setCourseName(resultset.getString("coursename"));
				course.setInstructorName(this.getInstructorForCourse(courseid));
				getCoursesAsTA.add(course);
			}
		} catch (SQLException e) {
			log.info("SQL Exception occuered while getting the courses where user is TA");
		} finally {
			try {
				if (null != connection) {
					connection.close();
				}
				if (null != preparedStatement) {
					preparedStatement.close();
				}
				if (null != resultset) {
					resultset.close();
				}
			} catch (SQLException e) {
				log.info("Closed connection after getting all courses where user is TA");
			}
		}
		return getCoursesAsTA;
	}

	@Override
	public boolean isCourseExists(Course course) {
		boolean courseIdExists = false;
		boolean courseNameExists = false;
		boolean returnValue = false;
		int courseId = course.getCourseId();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			statement = connection.prepareStatement(DataBaseQueriesUtil.isCourseIdExists);
			statement.setInt(1, courseId);
			resultset = statement.executeQuery();
			if (resultset.next()) {
				courseIdExists = true;
				returnValue = true;
			}
			statement.close();
			resultset.close();
			if (null != course.getCourseName()) {
				String courseName = course.getCourseName();
				statement = connection.prepareStatement(DataBaseQueriesUtil.isCourseNameExists);
				statement.setString(1, courseName);
				resultset = statement.executeQuery();
				if (resultset.next()) {
					courseNameExists = true;
				}
				if (courseIdExists && courseNameExists) {
					returnValue = true;
				}
			}
		} catch (SQLException e) {
			log.info("SQL Exception. Please check connection");
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

	@Override
	public User getInstructorForCourse(int courseId) {
		UserDao theUserDao = SystemConfig.getSingletonInstance().getTheUserDao();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User instructor = null;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			preparedStatement = connection.prepareStatement(DataBaseQueriesUtil.getInstructorForCourse);
			preparedStatement.setInt(1, courseId);
			resultSet = preparedStatement.executeQuery();
			String bannerId = null;
			log.info("In user dao for getting Instructor for course id");
			while (resultSet.next()) {
				bannerId = resultSet.getString("bannerid");
			}
			if (null != bannerId) {
				instructor = theUserDao.getUserById(bannerId);
			}
		} catch (SQLException e) {
			log.info("SQL Exception while getting the instructor for course");
		} finally {
			try {
				if (null != connection) {
					connection.close();
				}
				if (null != preparedStatement) {
					preparedStatement.close();
				}
				if (null != resultSet) {
					preparedStatement.close();
				}
				log.info("closing connection after getting instructor for a course");
			} catch (SQLException e) {
				log.info("SQL Exception while closing the connections and "
						+ "statements after getting the instructor for course");
			}
		}
		return instructor;
	}

	@Override
	public List<User> filterEligibleUsersForCourse(List<User> studentList, int courseId) {
		UserDao theUserDao = SystemConfig.getSingletonInstance().getTheUserDao();
		List<User> eligibleStudents = new ArrayList<User>();
		List<User> existingStudentsOfCourse = theUserDao.getAllUsersByCourse(courseId);
		log.info("In User Dao to get filterEligible ");
		for (User student : studentList) {
			boolean isExists = false;
			for (User existingStudent : existingStudentsOfCourse) {
				if (student.getBannerId().equalsIgnoreCase(existingStudent.getBannerId())) {
					isExists = true;
					break;
				}
			}
			if (isExists) {
				continue;
			} else {
				eligibleStudents.add(student);
			}
		}
		return eligibleStudents;
	}
}
