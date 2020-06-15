package com.group18.asdc.database;

public enum SQLQueries {
	
    USER_AUTH_BY_EMAIL_PASSWORD("select * from user where bannerid = ? and password = ?"),
    USER_AUTH_BY_EMAIL("select bannerid, password, true from user where bannerid = ?"),
    GET_USER_ROLES("select s2.bannerid,rolename from role inner join (select s.roleid,s.bannerid from (select roleid,bannerid from courserole union select roleid,bannerid from systemrole) as s where bannerid = ? ) as s2 on s2.roleid = role.roleid order by role.roleid asc"),
    GET_USER_WITH_BANNER_ID("select * from user where bannerid = ?"),
    UPDATE_PASSWORD_FOR_USER("update user set password = ? where bannerid = ?");

    private final String sqlQuery;

    private SQLQueries(String sqlQuery){
        this.sqlQuery = sqlQuery;
    }

    @Override
    public String toString(){
        return sqlQuery;
    }
    
}