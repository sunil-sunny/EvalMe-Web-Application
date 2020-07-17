package com.group18.asdc.database;

public enum UserManagementDataBaseQueriesUtil {

	GET_ALL_USERS_RELATED_TO_COURSE(
			"SELECT c.bannerid,c.lastname,c.firstname,c.emailid FROM courserole as a inner join role as b on a.roleid=b.roleid inner join user as c on a.bannerid=c.bannerid where (b.rolename='STUDENT' || b.rolename='INSTRUCTOR' || b.rolename='TA') and a.courseid=?;"),
	INSERT_USER("insert into user values(?,?,?,?,?);"),
	ALLOCATE_SYSTEM_ROLE("insert into systemrole(roleid,bannerid) values(?,?);"),
	CHECK_USER_WITH_EMAIL("select * from user where emailid=?;"),
	CHECK_USER_WITH_BANNERID("select * from user where bannerid=?;"),
	GET_USER_BY_ID("SELECT * FROM user where bannerid=?;"), IS_USER_EXISTS("select * from user where bannerid= ?;"),
	IS_INSTRUCTOR_A_STUDENT(
			"SELECT bannerid from courserole where roleid in (select roleid from role where rolename in ('STUDENT','TA')) and bannerid=? and courseid=?;"),
	USER_AUTH_BY_EMAIL("select bannerid, password, true from user where bannerid = ?"),
	GET_USER_ROLES(
			"select s2.bannerid,rolename from role inner join (select s.roleid,s.bannerid from (select roleid,bannerid from courserole union select roleid,bannerid from systemrole) as s where bannerid = ? ) as s2 on s2.roleid = role.roleid order by role.roleid asc"),
	GET_USER_WITH_BANNER_ID("select * from user where bannerid = ?"),
	UPDATE_PASSWORD_FOR_USER("update user set password = ? where bannerid = ?"),
	INSERT_PASSWORD_HISTORY("insert into passwords(bannerid,password,createddate) values (?,?,?)"),
	GET_PASSWORD_HISTORY(
			"select bannerid, password from passwords where bannerid = ? order by createddate desc limit ?");

	private String sqlQuery;

	private UserManagementDataBaseQueriesUtil(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}

	@Override
	public String toString() {
		return sqlQuery;
	}
}
