package com.group18.asdc.groupformation;

import java.util.List;

import com.group18.asdc.entities.ISurveyList;

public class GroupFormationDirector implements IGroupFormationDirector {

	private IGroupFormationBuilder groupBuilder;

	public GroupFormationDirector(IGroupFormationBuilder builder) {
		this.groupBuilder = builder;
	}

	public void createGroup(ISurveyList survey, Integer numberOfGroups) {
		List questions = survey.getQuestionList();
		List answers = survey.getAnswerList();
		List users = survey.getUserList();

		groupBuilder.computeDistance(answers, questions);
		groupBuilder.createGroups(users, numberOfGroups);
	}

}