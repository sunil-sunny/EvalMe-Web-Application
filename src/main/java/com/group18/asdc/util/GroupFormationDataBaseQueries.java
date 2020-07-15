package com.group18.asdc.util;

public enum GroupFormationDataBaseQueries {
	
	GET_SURVEY_GROUPS("select r.surveyid, s.courseid, r.groupid, r.groupname from survey s, groupformationresults r where s.surveyid = r.surveyid and courseid=?;"),
	GET_GROUP_MEMBERS("select m.groupid, u.bannerid, u.firstname, u.lastname, u.emailid from user u, groupmembers m where m.bannerid = u.bannerid and groupid=?;");
	
	private final String sqlQuery;

	private GroupFormationDataBaseQueries(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}
	
	@Override
	public String toString() {
		return sqlQuery;
	}

}
