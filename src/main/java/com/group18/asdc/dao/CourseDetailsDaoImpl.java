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
					if (role.equalsIgnoreCase("INSTRUCTOR")) {

						course.setInstructorName(userDao.getUserById(bannerId));
					} else if (role.equalsIgnoreCase("STUDENT")) {

						students.add(userDao.getUserById(bannerId));
					} else if (role.equalsIgnoreCase("TA")) {

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
				if (getCourses != null) {
					getCourses.close();
				}
				if (con != null) {
					con.close();
				}
				if (getCourseRoles != null) {
					getCourseRoles.close();
				}
				if (resultSetAllCourses != null) {
					resultSetAllCourses.close();
				}
				if (resultSetAllCourseRoles != null) {
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

		UserDao userDao = SystemConfig.getSingletonInstance().getTheUserDao();
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
				course.setInstructorName(userDao.getInstructorForCourse(courseid));
				getCoursesAsStudent.add(course);
			}
		} catch (SQLException e) {
			log.info("SQL Exception occured while getting courses where user is student");
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (resultset != null) {
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

		UserDao userDao = SystemConfig.getSingletonInstance().getTheUserDao();
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
				course.setInstructorName(userDao.getInstructorForCourse(courseid));
				getCoursesAsInstructor.add(course);
			}
		} catch (SQLException e) {
			log.info("SQL Exception while getting courses where user is instructor");
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (resultset != null) {
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
		UserDao userDao = SystemConfig.getSingletonInstance().getTheUserDao();
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
				course.setInstructorName(userDao.getInstructorForCourse(courseid));
				getCoursesAsTA.add(course);
			}
		} catch (SQLException e) {
			log.info("SQL Exception occuered while getting the courses where user is TA");
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (resultset != null) {
					resultset.close();
				}
			} catch (SQLException e) {
				log.info("Closed connection after getting all courses where user is TA");
			}
		}
		return getCoursesAsTA;
	}
}
