package com.group18.asdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.database.CourseDataBaseQueriesUtil;
import com.group18.asdc.entities.User;

public class CourseRolesDaoImpl implements CourseRolesDao {

	private Logger log = Logger.getLogger(CourseRolesDaoImpl.class.getName());

	@Override
	public boolean allocateTa(int courseId, User user) {

		boolean isAllocated = Boolean.FALSE;

		try (Connection connection = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
				.getConnectionManager().getDBConnection();
				PreparedStatement statement = connection
						.prepareStatement(CourseDataBaseQueriesUtil.ALLOCATE_TA_FOR_COURSE.toString());) {
			statement.setInt(1, courseId);
			statement.setString(2, user.getBannerId());
			int taAllocated = statement.executeUpdate();
			if (taAllocated > 0) {
				isAllocated = Boolean.TRUE;
			} else {
				isAllocated = Boolean.FALSE;
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL Exception while allocating user=" + user.getBannerId() + " as TA for course="+
					courseId);
		}
		return isAllocated;
	}

	@Override
	public boolean enrollStudentsIntoCourse(List<User> studentList, int courseId) {

		boolean enrollStatus = Boolean.FALSE;

		try (Connection connection = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
				.getConnectionManager().getDBConnection();
				PreparedStatement queryToEnrollStudent = connection
						.prepareStatement(CourseDataBaseQueriesUtil.ENROLL_STUDENTS_INTO_COURSE.toString());) {

			for (User user : studentList) {
				queryToEnrollStudent.setInt(1, courseId);
				queryToEnrollStudent.setString(2, user.getBannerId());
				int isEnrolled = queryToEnrollStudent.executeUpdate();
				if (isEnrolled == 1) {
					enrollStatus = Boolean.TRUE;
				}
			}
			log.log(Level.INFO,"Enrolled number of students=" + studentList.size() + " into course id=" + courseId);
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL Exception occuered while enrolling the students into course id="+ courseId);
		} 
		return enrollStatus;
	}
}
