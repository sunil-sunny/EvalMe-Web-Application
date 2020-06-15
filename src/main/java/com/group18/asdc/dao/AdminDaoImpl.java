package com.group18.asdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.group18.asdc.database.ConnectionManager;
import com.group18.asdc.entities.CourseAdmin;
import com.group18.asdc.util.DataBaseQueriesUtil;

@Repository
public class AdminDaoImpl implements AdminDao {


	public AdminDaoImpl() {
		super();
	}

	@Override
	public boolean checkCourseId(int courseid) {

		// check if courseId exists in the database
		// return true if it does

		int courseidps = courseid;
		boolean returnType = false;

		// set variables to null
		Connection connection = null;
		PreparedStatement pstatement = null;
		ResultSet resultset = null;

		// get connection

		try {

			// open connection to database

			connection = ConnectionManager.getInstance().getDBConnection();
			

			// statement to check if courseid exists in the database
			pstatement = connection.prepareStatement(DataBaseQueriesUtil.checkCourseId);
			// set values in prepared statement
			pstatement.setInt(1, courseidps);

			// execute query
			resultset = pstatement.executeQuery();

			// store courseid in String courseidrs

			if (resultset.next()) {
				returnType = true;
			} else {
				returnType = false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (pstatement != null) {
				try {
					pstatement.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}

			// close resultset if not null
			if (resultset != null) {
				try {
					resultset.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			// close connection if not null
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}

		}

		return returnType;
	}

	@Override
	public boolean checkCourseName(String coursename) {

		// check if courseId exists in the database
		// return true if it does

		String coursenameps = coursename;
		boolean returnType = false;
		String coursenamers = "";

		// set variables to null
		Connection connection = null;
		PreparedStatement pstatement = null;
		ResultSet resultset = null;
		// get connection

		try {

			// open connection to database

			connection = ConnectionManager.getInstance().getDBConnection();

			// statement to check if coursename exists in the database
			pstatement = connection.prepareStatement(DataBaseQueriesUtil.checkCourseName);
			// set values in prepared statement
			pstatement.setString(1, coursenameps);

			// execute query
			resultset = pstatement.executeQuery();

			// store coursename in String coursenamers
			while (resultset.next()) {
				coursenamers = resultset.getString("coursename");
			}

			// if coursenamers is null, coursename does not exist, return false
			if (coursenamers.equals(null) || coursenamers.equals("") || coursenamers.equals(" ")) {
				returnType = false;

			}
			// if coursenamers is not null, coursename exists, return true
			else {
				returnType = true;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (pstatement != null) {
				try {
					pstatement.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}

			// close resultset if not null
			if (resultset != null) {
				try {
					resultset.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			// close connection if not null
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}

		}

		return returnType;

	}

	@Override
	public String checkInstructorId(String instructorid) {

		// check if instructor is registered as a user in the database
		// return true if they are

		String instructidps = instructorid;
		String returnType = "";
		String instructidrs = "";
		String instructstudentidrs = "";

		// set variables to null
		Connection connection = null;
		PreparedStatement pstatement = null;
		ResultSet resultset = null;
		// get connection

		try {

			// open connection to database

			connection = ConnectionManager.getInstance().getDBConnection();

			// statement to check if instructorid exists in the database
			pstatement = connection.prepareStatement(DataBaseQueriesUtil.checkInstructorId);
			// set values in prepared statement
			pstatement.setString(1, instructidps);

			// execute query
			resultset = pstatement.executeQuery();

			// store result in String instructidrs
			while (resultset.next()) {
				instructidrs = resultset.getString("bannerid");
			}

			// if instructidrs is null, instructidrs does not exist, return false
			if (instructidrs.equals(null) || instructidrs.equals("") || instructidrs.equals(" ")) {
				returnType = "invalidinst";

			}

			// if instructidrs is not null, instructidrs exists
			// then, check if the user is registered as a student
			else {
				returnType = "invalidinst";

				// statement to check if instructor is registered as a student or TA
				pstatement = connection.prepareStatement(DataBaseQueriesUtil.checkInstructorStudent);

				// set values in prepared statement
				pstatement.setString(1, instructidps);

				// execute query
				resultset = pstatement.executeQuery();

				// store resultset value - bannerid, in a string
				while (resultset.next()) {
					instructstudentidrs = resultset.getString("bannerid");
				}

				// if instructstudentidrs is not empty, instructor is also a student or TA
				if (!instructstudentidrs.equals(null)) {
					returnType = "invalidinst";

				}
				// if instructstudentidrs is empty, instructor is not a student or TA
				else {
					returnType = "success";
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (pstatement != null) {
				try {
					pstatement.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}

			// close resultset if not null
			if (resultset != null) {
				try {
					resultset.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			// close connection if not null
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}

		return returnType;
	}

	@Override
	public boolean addCourse(int courseid, String coursename) {

		// insert new course in course table
		System.out.println("im in dao");
		System.out.println(courseid);
		System.out.println(coursename);

		boolean returnType = true;

		// set variables to null
		Connection connection = null;
		PreparedStatement pstatement = null;
		ResultSet resultset = null;

		// get connection

		try {
			// open connection to database

			connection = ConnectionManager.getInstance().getDBConnection();

			// statement to use database

			// statement to insert new course in the database
			pstatement = connection.prepareStatement(DataBaseQueriesUtil.createCourse1);
			// set values in prepared statement
			pstatement.setInt(1, courseid);
			pstatement.setString(2, coursename);

			// execute statement to insert new course in the table course
			pstatement.execute();

			// check if the newly added course is in the course table
			pstatement = connection.prepareStatement(DataBaseQueriesUtil.checkCourseId);
			pstatement.setInt(1, courseid);

			// execute query
			resultset = pstatement.executeQuery();

			// if resultset is null, query is not executed, course is not added
			if (resultset.equals(null)) {
				returnType = false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (pstatement != null) {
				try {
					pstatement.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}

			// close resultset if not null
			if (resultset != null) {
				try {
					resultset.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			// close connection if not null
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}

		return returnType;

	}

	@Override
	public boolean addCourse(CourseAdmin courseadmin) {

		int courseid = courseadmin.getCourseId();
		String coursename = courseadmin.getCourseName();
		String instructorid = courseadmin.getInstructorId();

		// insert new course in course table

		boolean returnType = true;

		// set variables to null
		Connection connection = null;
		PreparedStatement pstatement = null;
		ResultSet resultset = null;

		// get connection

		try {
			// open connection to database

			connection = ConnectionManager.getInstance().getDBConnection();

			// statement to insert new course in the database - course table
			pstatement = connection.prepareStatement(DataBaseQueriesUtil.createCourse1);
			// set values in prepared statement
			pstatement.setInt(1, courseid);
			pstatement.setString(2, coursename);

			// execute statement to insert new course in the table course
			pstatement.execute();

			// statement to insert new course in the database - courserole table
			pstatement = connection.prepareStatement(DataBaseQueriesUtil.createCourse2);
			// set values in prepared statement
			pstatement.setInt(1, courseid);
			pstatement.setString(2, instructorid);

			// execute statement to insert new course in the table courserole
			pstatement.execute();

			// 1.check if the newly added course is in the course table
			pstatement = connection.prepareStatement(DataBaseQueriesUtil.checkCourseId);
			pstatement.setInt(1, courseid);

			// execute query
			resultset = pstatement.executeQuery();

			// if resultset is null, course is not added
			if (resultset.equals(null)) {
				returnType = false;
			}

			// 2.check if the newly added course is in the courserole table
			pstatement = connection.prepareStatement(DataBaseQueriesUtil.checkcreateCourse2);
			pstatement.setInt(1, courseid);
			pstatement.setString(2, instructorid);

			// execute query
			resultset = pstatement.executeQuery();

			// if resultset is null, course is not added
			if (resultset.equals(null)) {
				returnType = false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (pstatement != null) {
				try {
					pstatement.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}

			// close resultset if not null
			if (resultset != null) {
				try {
					resultset.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			// close connection if not null
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}

		return returnType;

	}

	@Override
	public boolean deleteCourse(int courseid) {

		// delete course in course table

		boolean returnType = true;

		// set variables to null
		Connection connection = null;
		PreparedStatement pstatement = null;
		ResultSet resultset = null;

		// get connection

		try {
			// open connection to database

			connection = ConnectionManager.getInstance().getDBConnection();

			// statement to delete course in the database - courserole table
			pstatement = connection.prepareStatement(DataBaseQueriesUtil.deleteCourse1);
			// set values in prepared statement
			pstatement.setInt(1, courseid);

			// execute statement to delete course in the table courserole
			pstatement.execute();

			// statement to delete course in the database - course table
			pstatement = connection.prepareStatement(DataBaseQueriesUtil.deleteCourse2);
			// set values in prepared statement
			pstatement.setInt(1, courseid);

			// execute statement to delete course in the table course
			pstatement.execute();

			// check if the deleted course is in the course table
			pstatement = connection.prepareStatement(DataBaseQueriesUtil.checkCourseId);
			pstatement.setInt(1, courseid);

			// execute query
			resultset = pstatement.executeQuery();

			// if resultset is not null, course is not deleted
			if (resultset.next()) {
				returnType = true;
			}
			else {
				returnType=false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (pstatement != null) {
				try {
					pstatement.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}

			// close resultset if not null
			if (resultset != null) {
				try {
					resultset.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			// close connection if not null
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}

		return returnType;

	}

	@Override
	public boolean addInstructor(int courseid, String bannerid) {

		boolean returnType = true;

		// set variables to null
		Connection connection = null;
		PreparedStatement pstatement = null;
		ResultSet resultset = null;
		// get connection

		try {
			// open connection to database

			connection = ConnectionManager.getInstance().getDBConnection();

			// it is assumed that the course exists in the course table.
			// Values will only be added in course role table.

			// we delete any existing instructors from course role table

			pstatement = connection.prepareStatement(DataBaseQueriesUtil.deletecreateCourse2);
			// set values in prepared statement
			pstatement.setInt(1, courseid);

			// execute statement to delete existing instructor in the table courserole
			pstatement.execute();

			// statement to insert new instructor in the database - courserole table
			pstatement = connection.prepareStatement(DataBaseQueriesUtil.createCourse2);
			// set values in prepared statement
			pstatement.setInt(1, courseid);
			pstatement.setString(2, bannerid);

			// execute statement to insert new course in the table courserole
			pstatement.execute();

			// 1.check if the newly added course is in the course table
			pstatement = connection.prepareStatement(DataBaseQueriesUtil.checkCourseId);
			pstatement.setInt(1, courseid);

			// execute query
			resultset = pstatement.executeQuery();

			// if resultset is null, course is not added
			if (resultset.equals(null)) {
				returnType = false;
			}

			// 2.check if the newly added course is in the courserole table
			pstatement = connection.prepareStatement(DataBaseQueriesUtil.checkcreateCourse2);
			pstatement.setInt(1, courseid);
			pstatement.setString(2, bannerid);

			// execute query
			resultset = pstatement.executeQuery();

			// if resultset is null, course is not added
			if (resultset.equals(null)) {
				returnType = false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (pstatement != null) {
				try {
					pstatement.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}

			// close resultset if not null
			if (resultset != null) {
				try {
					resultset.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			// close connection if not null
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}

		return returnType;
	}

}
