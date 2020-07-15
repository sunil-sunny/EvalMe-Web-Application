package com.group18.asdc.util;

public enum CourseDataBaseQueriesUtil {

	GET_COURSE_BY_ID("SELECT * FROM course where courseid=?;"),
	GET_COURSE_DETAILS(
			"SELECT a.courseid,a.bannerid,b.rolename FROM courserole as a inner join role as b on a.roleid=b.roleid where a.courseid=?;"),
	CREATE_COURSE("insert into course(courseid,coursename) values (?,?);"),
	ALLOCATE_COURSE_INSTRUCTOR(
			"insert into courserole(roleid,courseid,bannerid) values ((select roleid from role where rolename='INSTRUCTOR'),?,?)"),
	DELETE_COURSE("delete from course where courseid=?"),
	IS_COURSE_ID_EXISTS("SELECT courseid from course where courseid=?"),
	IS_COURSE_NAME_EXISTS("SELECT coursename from course where coursename=?"),
	IS_INSTRUCTOR_ASSIGNED(
			"select bannerid from courserole where courseid = ? and bannerid = ? and roleid = (select roleid from role where rolename='INSTRUCTOR')"),
	GET_ALL_COURSES("SELECT * FROM course;"),
	ALLOCATE_TA_FOR_COURSE("insert into courserole (roleid,courseid,bannerid) values (4,?,?);"),
	ENROLL_STUDENTS_INTO_COURSE("insert into courserole (roleid,courseid,bannerid) values (5,?,?);"),
	GET_COURSES_WHERE_USER_IS_STUDENT(
			"SELECT b.* from courserole as a inner join course as b on a.courseid=b.courseid where a.roleid=5 and a.bannerid=?;"),
	GET_INSTRUCTOR_FOR_COURSE("select bannerid from courserole where roleid=3 and courseid=?;"),
	GET_COURSES_WHERE_USER_IS_INSTRUCTOR(
			"SELECT b.* from courserole as a inner join course as b on a.courseid=b.courseid where a.roleid=3 and a.bannerid=?;"),
	GET_COURSES_WHERE_USER_IS_TA(
			"SELECT b.* from courserole as a inner join course as b on a.courseid=b.courseid where a.roleid=4 and a.bannerid=?;");

	private final String sqlQuery;

	private CourseDataBaseQueriesUtil(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}

	@Override
	public String toString() {
		return sqlQuery;
	}
}
