package com.group18.asdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Repository;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.database.CourseDataBaseQueriesUtil;
import com.group18.asdc.entities.Course;

@Repository
public class AdminDaoImpl implements AdminDao {

	private Logger log = Logger.getLogger(AdminDaoImpl.class.getName());

	@Override
	public boolean addCourse(Course course) {
		boolean returnValue = Boolean.TRUE;
		int courseId = course.getCourseId();
		String courseName = course.getCourseName();
		String instructorId = course.getInstructorName().getBannerId();
		try (Connection connection = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
				.getConnectionManager().getDBConnection();
				PreparedStatement createStatement = connection
						.prepareStatement(CourseDataBaseQueriesUtil.CREATE_COURSE.toString());
				PreparedStatement allocateCourseStatement = connection
						.prepareStatement(CourseDataBaseQueriesUtil.ALLOCATE_COURSE_INSTRUCTOR.toString());
				PreparedStatement checkAssignmentStatement = connection
						.prepareStatement(CourseDataBaseQueriesUtil.IS_INSTRUCTOR_ASSIGNED.toString());) {

			createStatement.setInt(1, courseId);
			createStatement.setString(2, courseName);
			createStatement.execute();
			allocateCourseStatement.setInt(1, courseId);
			allocateCourseStatement.setString(2, instructorId);
			allocateCourseStatement.execute();
			checkAssignmentStatement.setInt(1, courseId);
			checkAssignmentStatement.setString(2, instructorId);
			ResultSet resultset = checkAssignmentStatement.executeQuery();
			if (null == resultset) {
				returnValue = Boolean.FALSE;
			} else {
				returnValue = Boolean.TRUE;
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL Exception while adding the course="+
					course.getCourseId() + " " + course.getCourseName());
		}
		return returnValue;
	}

	@Override
	public boolean deleteCourse(Course course) {
		boolean returnValue = Boolean.FALSE;
		int courseId = course.getCourseId();

		try (Connection connection = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
				.getConnectionManager().getDBConnection();
				PreparedStatement deleteStatement = connection
						.prepareStatement(CourseDataBaseQueriesUtil.DELETE_COURSE.toString());
				PreparedStatement checkIdExistanceStatement = connection
						.prepareStatement(CourseDataBaseQueriesUtil.IS_COURSE_ID_EXISTS.toString());) {

			deleteStatement.setInt(1, courseId);
			deleteStatement.execute();
			checkIdExistanceStatement.setInt(1, courseId);
			ResultSet resultset = checkIdExistanceStatement.executeQuery();

			if (resultset.next()) {
				returnValue = Boolean.FALSE;
			} else {
				returnValue = Boolean.TRUE;
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL Exception while deleting the course="+
					course.getCourseId() + " " + course.getCourseName());
		}
		return returnValue;
	}
}