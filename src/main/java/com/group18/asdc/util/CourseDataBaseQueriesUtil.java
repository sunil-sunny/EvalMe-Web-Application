package com.group18.asdc.util;

public class CourseDataBaseQueriesUtil {

	public final static String getCoursesById = "SELECT * FROM course where courseid=?;";
	public final static String getCourseDetails = "SELECT a.courseid,a.bannerid,b.rolename FROM courserole as a inner join role as b\r\n"
			+ "on a.roleid=b.roleid where a.courseid=?;";
}
