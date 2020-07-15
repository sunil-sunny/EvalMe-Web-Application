package com.group18.asdc.util;

public enum UserManagementDataBaseQueriesUtil {

	GET_ALL_USERS_RELATED_TO_COURSE(
			"SELECT c.bannerid,c.lastname,c.firstname,c.emailid FROM courserole as a inner join role as b on a.roleid=b.roleid inner join user as c on a.bannerid=c.bannerid where (b.rolename='STUDENT' || b.rolename='INSTRUCTOR' || b.rolename='TA') and a.courseid=?;"),
	INSERT_USER("insert into user values(?,?,?,?,?);"),
	ALLOCATE_SYSTEM_ROLE("insert into systemrole(roleid,bannerid) values(?,?);"),
	CHECK_USER_WITH_EMAIL("select * from user where emailid=?;"),
	CHECK_USER_WITH_BANNERID("select * from user where bannerid=?;"),
	GET_USER_BY_ID("SELECT * FROM user where bannerid=?;"), IS_USER_EXISTS("select * from user where bannerid= ?;"),
	IS_INSTRUCTOR_A_STUDENT(
			"SELECT bannerid from courserole where roleid in (select roleid from role where rolename in ('STUDENT','TA')) and bannerid=? and courseid=?;");

	private String sqlQuery;

	private UserManagementDataBaseQueriesUtil(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}

	@Override
	public String toString() {
		return sqlQuery;
	}
}
