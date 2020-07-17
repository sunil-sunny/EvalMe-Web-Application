package com.group18.asdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Repository;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.database.CourseDataBaseQueriesUtil;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.Role;
import com.group18.asdc.entities.User;

@Repository
public class CourseDetailsDaoImpl implements CourseDetailsDao {

	private Logger log = Logger.getLogger(CourseDetailsDaoImpl.class.getName());
	private static final UserDao userDao = SystemConfig.getSingletonInstance().getDaoAbstractFactory().getUserDao();

	@Override
	public List<Course> getAllCourses() {

		List<Course> allCourses = new ArrayList<Course>();
		try (Connection connection = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
				.getConnectionManager().getDBConnection();
				PreparedStatement getCourses = connection
						.prepareStatement(CourseDataBaseQueriesUtil.GET_ALL_COURSES.toString());
				PreparedStatement getCourseRoles = connection
						.prepareStatement(CourseDataBaseQueriesUtil.GET_COURSE_DETAILS.toString());) {
			ResultSet resultSetAllCourses = getCourses.executeQuery();
			ResultSet resultSetAllCourseRoles = null;
			Course course = null;
			while (resultSetAllCourses.next()) {
				course = SystemConfig.getSingletonInstance().getModelAbstractFactory().getCourse();
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
			}
			log.log(Level.INFO, "Number of courses retreived=" + allCourses.size());
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL Exception while getting all the courses and the number of courses="+
					allCourses.size());
		}
		return allCourses;
	}

	@Override
	public List<Course> getCoursesWhereUserIsStudent(User user) {

		List<Course> getCoursesAsStudent = new ArrayList<Course>();

		try (Connection connection = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
				.getConnectionManager().getDBConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(CourseDataBaseQueriesUtil.GET_COURSES_WHERE_USER_IS_STUDENT.toString());) {

			preparedStatement.setString(1, user.getBannerId());
			ResultSet resultset = preparedStatement.executeQuery();
			Course course = null;
			while (resultset.next()) {
				course = SystemConfig.getSingletonInstance().getModelAbstractFactory().getCourse();
				int courseid = resultset.getInt("courseid");
				course.setCourseId(courseid);
				course.setCourseName(resultset.getString("coursename"));
				course.setInstructorName(this.getInstructorForCourse(courseid));
				getCoursesAsStudent.add(course);
			}
			log.log(Level.INFO, "Number of courses where user with id=" + user.getBannerId() + " for a Student="
					+ getCoursesAsStudent.size());
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL Exception occured while getting courses where user with id=" + user.getBannerId()
					+ " as student and received count="+ getCoursesAsStudent.size());
		}
		return getCoursesAsStudent;
	}

	@Override
	public List<Course> getCoursesWhereUserIsInstrcutor(User user) {

		List<Course> getCoursesAsInstructor = new ArrayList<Course>();

		try (Connection connection = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
				.getConnectionManager().getDBConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(CourseDataBaseQueriesUtil.GET_COURSES_WHERE_USER_IS_INSTRUCTOR.toString());) {

			preparedStatement.setString(1, user.getBannerId());
			ResultSet resultset = preparedStatement.executeQuery();
			Course course = null;
			while (resultset.next()) {
				course = SystemConfig.getSingletonInstance().getModelAbstractFactory().getCourse();
				int courseid = resultset.getInt("courseid");
				course.setCourseId(courseid);
				course.setCourseName(resultset.getString("coursename"));
				course.setInstructorName(this.getInstructorForCourse(courseid));
				getCoursesAsInstructor.add(course);
			}
			log.log(Level.INFO, "Number of courses where user with id=" + user.getBannerId() + " for Instructor="
					+ getCoursesAsInstructor.size());
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL Exception occured while getting courses where user with id=" + user.getBannerId()
					+ " as instructor and received count="+ getCoursesAsInstructor.size());
		}
		return getCoursesAsInstructor;
	}

	@Override
	public List<Course> getCoursesWhereUserIsTA(User user) {

		List<Course> getCoursesAsTA = new ArrayList<Course>();

		try (Connection connection = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
				.getConnectionManager().getDBConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(CourseDataBaseQueriesUtil.GET_COURSES_WHERE_USER_IS_TA.toString());) {

			preparedStatement.setString(1, user.getBannerId());
			ResultSet resultset = preparedStatement.executeQuery();
			Course course = null;
			while (resultset.next()) {
				course = SystemConfig.getSingletonInstance().getModelAbstractFactory().getCourse();
				int courseid = resultset.getInt("courseid");
				course.setCourseId(courseid);
				course.setCourseName(resultset.getString("coursename"));
				course.setInstructorName(this.getInstructorForCourse(courseid));
				getCoursesAsTA.add(course);
			}
			log.log(Level.INFO, "Number of courses where user with id=" + user.getBannerId() + " for TA="
					+ getCoursesAsTA.size());
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL Exception occured while getting courses where user with id=" + user.getBannerId()
					+ " as TA and received count=" + getCoursesAsTA.size());
		} 
		return getCoursesAsTA;
	}

	@Override
	public boolean isCourseExists(Course course) {

		boolean courseIdExists = Boolean.FALSE;
		boolean courseNameExists = Boolean.FALSE;
		boolean returnValue = Boolean.FALSE;
		int courseId = course.getCourseId();

		try (Connection connection = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
				.getConnectionManager().getDBConnection();
				PreparedStatement statementID = connection
						.prepareStatement(CourseDataBaseQueriesUtil.IS_COURSE_ID_EXISTS.toString());
				PreparedStatement statementName = connection
						.prepareStatement(CourseDataBaseQueriesUtil.IS_COURSE_NAME_EXISTS.toString());) {

			statementID.setInt(1, courseId);
			ResultSet resultset = statementID.executeQuery();
			if (resultset.next()) {
				courseIdExists = Boolean.TRUE;
				returnValue = Boolean.TRUE;
			}

			if (null == course.getCourseName()) {
				returnValue = Boolean.FALSE;
			} else {
				String courseName = course.getCourseName();
				statementName.setString(1, courseName);
				resultset = statementName.executeQuery();
				if (resultset.next()) {
					courseNameExists = Boolean.TRUE;
				}
				if (courseIdExists && courseNameExists) {
					returnValue = Boolean.TRUE;
				}
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL Exception while checking if Course exists or not");
		}
		return returnValue;
	}

	@Override
	public User getInstructorForCourse(int courseId) {

		User instructor = null;
		try (Connection connection = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
				.getConnectionManager().getDBConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(CourseDataBaseQueriesUtil.GET_INSTRUCTOR_FOR_COURSE.toString());) {

			preparedStatement.setInt(1, courseId);
			ResultSet resultSet = preparedStatement.executeQuery();
			String bannerId = null;
			if (resultSet.next()) {
				bannerId = resultSet.getString("bannerid");
				instructor = userDao.getUserById(bannerId);
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL Exception while getting the instructor for course for courseid=" + courseId);
		} 
		return instructor;
	}

	@Override
	public List<User> filterEligibleUsersForCourse(List<User> studentList, int courseId) {
		List<User> eligibleStudents = new ArrayList<User>();
		List<User> existingStudentsOfCourse = userDao.getAllUsersByCourse(courseId);
		log.log(Level.INFO, "Filtering the Eligible for the course id=" + courseId);
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

		Course course = null;
		try (Connection connection = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
				.getConnectionManager().getDBConnection();
				PreparedStatement getCourseById = connection
						.prepareStatement(CourseDataBaseQueriesUtil.GET_COURSE_BY_ID.toString());
				PreparedStatement getCourseRoles = connection
						.prepareStatement(CourseDataBaseQueriesUtil.GET_COURSE_DETAILS.toString());) {

			getCourseById.setInt(1, courseId);
			ResultSet resultSet = getCourseById.executeQuery();
			while (resultSet.next()) {
				course = SystemConfig.getSingletonInstance().getModelAbstractFactory().getCourse();
				List<User> students = new ArrayList<User>();
				List<User> taList = new ArrayList<User>();
				course.setCourseId(resultSet.getInt("courseid"));
				course.setCourseName(resultSet.getString("coursename"));
				getCourseRoles.setInt(1, resultSet.getInt("courseid"));
				ResultSet resultSetCourseRoles = getCourseRoles.executeQuery();
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
			log.log(Level.SEVERE, "SQL Exception occured while getting course for id="+ courseId);
		} 
		return course;
	}
}
