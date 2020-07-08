package com.group18.asdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import com.group18.asdc.database.ConnectionManager;
import com.group18.asdc.entities.User;
import com.group18.asdc.util.CourseDataBaseQueriesUtil;

public class CourseRolesDaoImpl implements CourseRolesDao {

	private Logger log = Logger.getLogger(CourseRolesDaoImpl.class.getName());

	@Override
	public boolean allocateTa(int courseId, User user) {
		Connection connection = null;
		PreparedStatement statement = null;
		boolean isAllocated = false;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			log.info("In Course Dao allocating TA");
			statement = connection.prepareStatement(CourseDataBaseQueriesUtil.ALLOCATE_TA_FOR_COURSE.toString());
			statement.setInt(1, courseId);
			statement.setString(2, user.getBannerId());
			int taAllocated = statement.executeUpdate();
			if (taAllocated > 0) {
				isAllocated = true;
			} else {
				isAllocated = false;
			}
		} catch (SQLException e) {
			log.info("SQL Exception while allocating user as TA");
		} finally {
			try {
				if (null != connection) {
					connection.close();
				}
				if (null != statement) {
					statement.close();
				}
				log.info("Closing the connections after allocating TA");
			} catch (SQLException e) {
				log.info("SQL Exception while closing connection after allocating user as TA");
			}
		}
		return isAllocated;
	}

	@Override
	public boolean enrollStudentsIntoCourse(List<User> studentList, int courseId) {
		Connection connection = null;
		PreparedStatement queryToEnrollStudent = null;
		boolean enrollStatus = false;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			for (User user : studentList) {
				queryToEnrollStudent = connection
						.prepareStatement(CourseDataBaseQueriesUtil.ENROLL_STUDENTS_INTO_COURSE.toString());
				log.info("In Course Controller for enrolling students into course");
				queryToEnrollStudent.setInt(1, courseId);
				queryToEnrollStudent.setString(2, user.getBannerId());
				int isEnrolled = queryToEnrollStudent.executeUpdate();
				if (isEnrolled == 1) {
					enrollStatus = true;
				}
				queryToEnrollStudent.close();
			}
		} catch (SQLException e) {
			log.info("SQL Exception occuered while enrolling the students into course");
		} finally {
			try {
				if (null != connection) {
					connection.close();
				}
				if (null != queryToEnrollStudent) {
					queryToEnrollStudent.close();
				}
				log.info("Closed after enrolling students into course");
			} catch (SQLException e) {
				log.info("SQL Exception while closing connection and statements after enrolling students into course");
			}
		}
		return enrollStatus;
	}
}
