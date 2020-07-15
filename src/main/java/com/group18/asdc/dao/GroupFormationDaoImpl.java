package com.group18.asdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import com.group18.asdc.database.ConnectionManager;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.Group;
import com.group18.asdc.entities.SurveyGroups;
import com.group18.asdc.entities.User;
import com.group18.asdc.util.GroupFormationDataBaseQueries;

public class GroupFormationDaoImpl implements GroupFormationDao{
	
	private final Logger log = Logger.getLogger(GroupFormationDaoImpl.class.getName());

	@Override
	public SurveyGroups getGroupFormationResults(Course course) {
		
		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		ResultSet theResultSet = null;
		SurveyGroups theSurveyGroup=null;
		
		try {
			
			theSurveyGroup = new SurveyGroups();
			Group group=null;
			User user=null;
			List<Group> surveyGroups = new ArrayList<Group>();
			List<User> groupMembers = new ArrayList<User>();
			
			connection = ConnectionManager.getInstance().getDBConnection();
			
			thePreparedStatement = connection.prepareStatement(GroupFormationDataBaseQueries.GET_SURVEY_GROUPS.toString());
			thePreparedStatement.setInt(1, course.getCourseId());
			theResultSet = thePreparedStatement.executeQuery();
			
			while(theResultSet.next()) {
				theSurveyGroup.setSurveyId(theResultSet.getInt(1));
				group = new Group();
				group.setGroupId(theResultSet.getInt(3));
				group.setGroupName(theResultSet.getString(4));
				surveyGroups.add(group);
			}
			theSurveyGroup.setSurveyGroups(surveyGroups);
			thePreparedStatement.close();
			theResultSet.close();
			
			for(int i=0;i<surveyGroups.size();i++) {
				
				thePreparedStatement = connection.prepareStatement(GroupFormationDataBaseQueries.GET_GROUP_MEMBERS.toString());
				thePreparedStatement.setInt(1, surveyGroups.get(i).getGroupId());
				theResultSet = thePreparedStatement.executeQuery();	
				
				while(theResultSet.next()) {
					user = new User();
					user.setBannerId(theResultSet.getString(2));
					user.setFirstName(theResultSet.getString(3));
					user.setLastName(theResultSet.getString(4));
					user.setEmail(theResultSet.getString(5));
					groupMembers.add(user);
				}
				theSurveyGroup.getSurveyGroups().get(i).setGroupMembers(groupMembers);
				theResultSet.close();
				thePreparedStatement.close();
			}
		} catch (SQLException e) {
			log.severe("SQL Exception while fetching Group Formation Results");
		} finally {
			try {
				if (null != theResultSet) {
					theResultSet.close();
				}
				if (null != connection) {
					connection.close();
				}
				if (null != thePreparedStatement) {
					thePreparedStatement.close();
				}
				log.info("Closing connection after fetching Group Formation Results");
			} catch (SQLException e) {
				log.severe("SQL Exception while closing the connection and statement after fetching Group Formation Results");
			}
		}
		return theSurveyGroup;
	}
}
