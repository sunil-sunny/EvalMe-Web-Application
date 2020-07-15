package com.group18.asdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Repository;

import com.group18.asdc.ProfileManagementConfig;
import com.group18.asdc.database.ConnectionManager;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.Role;
import com.group18.asdc.entities.User;
import com.group18.asdc.util.CourseDataBaseQueriesUtil;

@Repository
public class CourseDetailsDaoImpl implements CourseDetailsDao {

	private Logger log = Logger.getLogger(CourseDetailsDaoImpl.class.getName());
	

	@Override
	public List<Course> getAllCourses() {

		UserDao userDao = ProfileManagementConfig.getSingletonInstance().getTheUserDao();
		Connection con = null;
		Statement getCourses = null;
		PreparedStatement getCourseRoles = null;
		ResultSet resultSetAllCourses = null;
		ResultSet resultSetAllCourseRoles = null;
		List<Course> allCourses = new ArrayList<Course>();
		try {
			con = ConnectionManager.getInstance().getDBConnection();
			getCourses = con.createStatement();
			resultSetAllCourses = getCourses.executeQuery(CourseDataBaseQueriesUtil.GET_ALL_COURSES.toString());
			getCourseRoles = con.prepareStatement(CourseDataBaseQueriesUtil.GET_COURSE_DETAILS.toString());
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
					if (role.equalsIgnoreCase(Role.INSTRUCTOR.toString())) {
						course.setInstructorName(userDao.getUserById(bannerId));
					} else if (role.equalsIgnoreCase(Role.STUDENT.toString())) {
						students.add(userDao.getUserById(bannerId));
					} else if (role.equalsIgnoreCase(Role.TA.toString())) {
						taList.add(userDao.getUserById(bannerId));
					}
				}
				course.setTaList(taList);
				course.setStudentList(students);
				resultSetAllCourseRoles.close();
				allCourses.add(course);
				log.info("Number of courses rereived is " + allCourses.size());
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL Exception while getting all the courses and the number of courses is ",
					allCourses.size());
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
				log.log(Level.SEVERE,
						"SQL Exception while closing the connection and statements after getting all courses");
			}
		}
		return allCourses;
	}

	@Override
	public List<Course> getCoursesWhereUserIsStudent(User user) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		List<Course> getCoursesAsStudent = new ArrayList<Course>();
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			preparedStatement = connection
					.prepareStatement(CourseDataBaseQueriesUtil.GET_COURSES_WHERE_USER_IS_STUDENT.toString());
			preparedStatement.setString(1, user.getBannerId());
			resultset = preparedStatement.executeQuery();
			Course course = null;
			while (resultset.next()) {
				course = new Course();
				int courseid = resultset.getInt("courseid");
				course.setCourseId(courseid);
				course.setCourseName(resultset.getString("coursename"));
				course.setInstructorName(this.getInstructorForCourse(courseid));
				getCoursesAsStudent.add(course);
			}
			log.info("Number of courses where user as Student is " + getCoursesAsStudent.size());
		} catch (SQLException e) {
			log.log(Level.SEVERE,
					"SQL Exception occured while getting courses where user as student and received count is ",
					getCoursesAsStudent.size());
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
				log.log(Level.SEVERE,
						"SQL Exception while closing the connection and statements after getting courses where user is student");
			}
		}
		return getCoursesAsStudent;
	}

	@Override
	public List<Course> getCoursesWhereUserIsInstrcutor(User user) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		List<Course> getCoursesAsInstructor = new ArrayList<Course>();
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			preparedStatement = connection
					.prepareStatement(CourseDataBaseQueriesUtil.GET_COURSES_WHERE_USER_IS_INSTRUCTOR.toString());
			preparedStatement.setString(1, user.getBannerId());
			resultset = preparedStatement.executeQuery();
			Course course = null;
			while (resultset.next()) {
				course = new Course();
				int courseid = resultset.getInt("courseid");
				course.setCourseId(courseid);
				course.setCourseName(resultset.getString("coursename"));
				course.setInstructorName(this.getInstructorForCourse(courseid));
				getCoursesAsInstructor.add(course);
			}
			log.info("Number of courses where user as Instructor is " + getCoursesAsInstructor.size());
		} catch (SQLException e) {
			log.log(Level.SEVERE,
					"SQL Exception occured while getting courses where user as instructor and received count is",
					getCoursesAsInstructor.size());
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
				log.log(Level.SEVERE,
						"Exception while closing connection after getting all courses where user is Instructor");
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
			preparedStatement = connection
					.prepareStatement(CourseDataBaseQueriesUtil.GET_COURSES_WHERE_USER_IS_TA.toString());
			preparedStatement.setString(1, user.getBannerId());
			resultset = preparedStatement.executeQuery();
			Course course = null;
			while (resultset.next()) {
				course = new Course();
				int courseid = resultset.getInt("courseid");
				course.setCourseId(courseid);
				course.setCourseName(resultset.getString("coursename"));
				course.setInstructorName(this.getInstructorForCourse(courseid));
				getCoursesAsTA.add(course);
			}
			log.info("Number of courses where user as TA is " + getCoursesAsTA.size());
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL Exception occured while getting courses where user as TA and received count is "
					+ getCoursesAsTA.size());
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
				log.log(Level.SEVERE, "Exception while Closing connection after getting all courses where user is TA");
			}
		}
		return getCoursesAsTA;
	}

	@Override
	public boolean isCourseExists(Course course) {
		boolean courseIdExists = Boolean.FALSE;
		boolean courseNameExists = Boolean.FALSE;
		boolean returnValue = Boolean.FALSE;
		int courseId = course.getCourseId();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			statement = connection.prepareStatement(CourseDataBaseQueriesUtil.IS_COURSE_ID_EXISTS.toString());
			statement.setInt(1, courseId);
			resultset = statement.executeQuery();
			if (resultset.next()) {
				courseIdExists = Boolean.TRUE;
				returnValue = Boolean.TRUE;
			}
			statement.close();
			resultset.close();
			if (null != course.getCourseName()) {
				String courseName = course.getCourseName();
				statement = connection.prepareStatement(CourseDataBaseQueriesUtil.IS_COURSE_NAME_EXISTS.toString());
				statement.setString(1, courseName);
				resultset = statement.executeQuery();
				if (resultset.next()) {
					courseNameExists = Boolean.TRUE;
				}
				if (courseIdExists && courseNameExists) {
					returnValue = Boolean.TRUE;
				}
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL Exception while checking if Course exists or not");
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
				log.log(Level.SEVERE,
						"SQL Exception while closing the connections and statements after checking if course exists or not");
			}
		}
		return returnValue;
	}

	@Override
	public User getInstructorForCourse(int courseId) {
		UserDao theUserDao = ProfileManagementConfig.getSingletonInstance().getTheUserDao();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User instructor = null;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			preparedStatement = connection
					.prepareStatement(CourseDataBaseQueriesUtil.GET_INSTRUCTOR_FOR_COURSE.toString());
			preparedStatement.setInt(1, courseId);
			resultSet = preparedStatement.executeQuery();
			String bannerId = null;
			while (resultSet.next()) {
				bannerId = resultSet.getString("bannerid");
			}
			if (null != bannerId) {
				instructor = theUserDao.getUserById(bannerId);
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL Exception while getting the instructor for course for courseid " + courseId);
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
			} catch (SQLException e) {
				log.log(Level.SEVERE,
						"SQL Exception while closing the connections and statements after getting the instructor for course");
			}
		}
		return instructor;
	}

	@Override
	public List<User> filterEligibleUsersForCourse(List<User> studentList, int courseId) {
		UserDao theUserDao = ProfileManagementConfig.getSingletonInstance().getTheUserDao();
		List<User> eligibleStudents = new ArrayList<User>();
		List<User> existingStudentsOfCourse = theUserDao.getAllUsersByCourse(courseId);
		log.info("Filtering the Eligible for the course id " + courseId);
		for (User student : studentList) {
			boolean isExists = Boolean.FALSE;
			for (User existingStudent : existingStudentsOfCourse) {
				if (student.getBannerId().equalsIgnoreCase(existingStudent.getBannerId())) {
					isExists = Boolean.TRUE;
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

	@Override
	public Course getCourseById(int courseId) {

		UserDao userDao = ProfileManagementConfig.getSingletonInstance().getTheUserDao();
		Connection con = null;
		PreparedStatement getCourseById = null;
		PreparedStatement getCourseRoles = null;
		ResultSet resultSet = null;
		ResultSet resultSetCourseRoles = null;
		Course course = null;
		try {
			con = ConnectionManager.getInstance().getDBConnection();
			getCourseById = con.prepareStatement(CourseDataBaseQueriesUtil.GET_COURSE_BY_ID.toString());
			getCourseById.setInt(1, courseId);
			resultSet = getCourseById.executeQuery();
			getCourseRoles = con.prepareStatement(CourseDataBaseQueriesUtil.GET_COURSE_DETAILS.toString());
			course = null;
			while (resultSet.next()) {
				course = new Course();
				List<User> students = new ArrayList<User>();
				List<User> taList = new ArrayList<User>();
				course.setCourseId(resultSet.getInt("courseid"));
				course.setCourseName(resultSet.getString("coursename"));
				getCourseRoles.setInt(1, resultSet.getInt("courseid"));
				resultSetCourseRoles = getCourseRoles.executeQuery();
				while (resultSetCourseRoles.next()) {
					String role = resultSetCourseRoles.getString("rolename");
					String bannerId = resultSetCourseRoles.getString("bannerid");
					if (role.equalsIgnoreCase(Role.INSTRUCTOR.toString())) {
						course.setInstructorName(userDao.getUserById(bannerId));
					} else if (role.equalsIgnoreCase(Role.STUDENT.toString())) {
						students.add(userDao.getUserById(bannerId));
					} else if (role.equalsIgnoreCase(Role.TA.toString())) {
						taList.add(userDao.getUserById(bannerId));
					}
				}
				course.setTaList(taList);
				course.setStudentList(students);
				resultSetCourseRoles.close();
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL Exception occured while getting course for id ", courseId);
		} finally {
			try {
				if (null != getCourseById) {
					getCourseById.close();
				}
				if (null != con) {
					con.close();
				}
				if (null != getCourseRoles) {
					getCourseRoles.close();
				}
				if (null != resultSet) {
					resultSet.close();
				}
				if (null != resultSetCourseRoles) {
					resultSetCourseRoles.close();
				}
				log.info("closing all the data connections in after getting course by Id");
			} catch (SQLException e) {
				log.log(Level.SEVERE,
						"Exception while closing the connection and statements after getting course for id ", courseId);
			}
		}
		return course;
	}
}
