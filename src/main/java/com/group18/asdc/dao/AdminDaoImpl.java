package com.group18.asdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import org.springframework.stereotype.Repository;
import com.group18.asdc.database.ConnectionManager;
import com.group18.asdc.entities.Course;
import com.group18.asdc.util.DataBaseQueriesUtil;

@Repository
public class AdminDaoImpl implements AdminDao {

	private Logger log = Logger.getLogger(AdminDaoImpl.class.getName());

	public AdminDaoImpl() {
		super();
	}

	@Override
	public boolean addCourse(Course course) {
		boolean returnValue = true;
		int courseId = course.getCourseId();
		String courseName = course.getCourseName();
		String instructorId = course.getInstructorName().getBannerId();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			statement = connection.prepareStatement(DataBaseQueriesUtil.createCourse);
			statement.setInt(1, courseId);
			statement.setString(2, courseName);
			statement.execute();
			statement.close();
			statement = connection.prepareStatement(DataBaseQueriesUtil.allocateCourseInstructor);
			statement.setInt(1, courseId);
			statement.setString(2, instructorId);
			statement.execute();
			statement = connection.prepareStatement(DataBaseQueriesUtil.isInstructorAssigned);
			statement.setInt(1, courseId);
			statement.setString(2, instructorId);
			resultset = statement.executeQuery();
			if (null == resultset) {
				returnValue = false;
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
	public boolean deleteCourse(Course course) {
		boolean returnValue = false;
		int courseId = course.getCourseId();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			statement = connection.prepareStatement(DataBaseQueriesUtil.deleteCourse);
			statement.setInt(1, courseId);
			statement.execute();
			statement.close();
			statement = connection.prepareStatement(DataBaseQueriesUtil.isCourseIdExists);
			statement.setInt(1, courseId);
			resultset = statement.executeQuery();
			if (resultset.next()) {
				returnValue = false;
			} else {
				returnValue = true;
			}
		} catch (SQLException e) {
			log.info("SQL Exception. Check connection.");
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