package com.group18.asdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
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
		boolean isAllocated = Boolean.FALSE;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			statement = connection.prepareStatement(CourseDataBaseQueriesUtil.ALLOCATE_TA_FOR_COURSE.toString());
			statement.setInt(1, courseId);
			statement.setString(2, user.getBannerId());
			int taAllocated = statement.executeUpdate();
			if (taAllocated > 0) {
				isAllocated = Boolean.TRUE;
			} else {
				isAllocated = Boolean.FALSE;
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL Exception while allocating " + user.getBannerId() + " as TA for course",
					courseId);
		} finally {
			try {
				if (null != connection) {
					connection.close();
				}
				if (null != statement) {
					statement.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, "SQL Exception while closing connection after allocating user as TA");
			}
		}
		return isAllocated;
	}

	@Override
	public boolean enrollStudentsIntoCourse(List<User> studentList, int courseId) {
		Connection connection = null;
		PreparedStatement queryToEnrollStudent = null;
		boolean enrollStatus = Boolean.FALSE;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			for (User user : studentList) {
				queryToEnrollStudent = connection
						.prepareStatement(CourseDataBaseQueriesUtil.ENROLL_STUDENTS_INTO_COURSE.toString());
				log.log(Level.INFO,"In Course Controller for enrolling students into course");
				queryToEnrollStudent.setInt(1, courseId);
				queryToEnrollStudent.setString(2, user.getBannerId());
				int isEnrolled = queryToEnrollStudent.executeUpdate();
				if (isEnrolled == 1) {
					enrollStatus = Boolean.TRUE;
				}
				queryToEnrollStudent.close();
			}
			log.log(Level.INFO,"Enrolled a total of " + studentList.size() + " students into course id " + courseId);
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL Exception occuered while enrolling the students into course id ", courseId);
		} finally {
			try {
				if (null != connection) {
					connection.close();
				}
				if (null != queryToEnrollStudent) {
					queryToEnrollStudent.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE,
						"SQL Exception while closing connection and statements after enrolling students into course");
			}
		}
		return enrollStatus;
	}
}
