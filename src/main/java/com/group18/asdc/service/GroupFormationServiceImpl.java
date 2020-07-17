package com.group18.asdc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.entities.Answer;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.ISurveyList;
import com.group18.asdc.entities.Option;
import com.group18.asdc.entities.SurveyMetaData;
import com.group18.asdc.entities.SurveyQuestion;
import com.group18.asdc.entities.User;
import com.group18.asdc.groupformation.IGroupFormationBuilder;
import com.group18.asdc.groupformation.IGroupFormationDirector;
import com.group18.asdc.util.IQueryVariableToArrayList;

public class GroupFormationServiceImpl implements GroupFormationService {

	private Logger logger = Logger.getLogger(GroupFormationService.class.getName());
	private static final String USER_DATA_MAP = "userDataMap", GROUP_LIST = "groupList", FIRST_NAME = "FIRST_NAME",
			LAST_NAME = "LAST_NAME", ANSWERS = "ANSWERS";
	private ISurveyList survey;
	private IGroupFormationBuilder groupBuilder;
	private IGroupFormationDirector director;

	public HashMap formGroupsForSurvey(Course course, SurveyAnswersService surveyAnswersService,
			SurveyService surveyService, IQueryVariableToArrayList queryVariableToArraylist) {
		HashMap<String, Object> resultMap = new HashMap<>();
		SurveyMetaData surveyQuestionData = surveyService.getSavedSurvey(course);
		ArrayList<Answer> answerList = surveyAnswersService.fetchAnswersForSurvey(surveyQuestionData.getSurveyId(),
				queryVariableToArraylist);
		survey = SystemConfig.getSingletonInstance().createSurveyAdapter(surveyQuestionData, answerList);
		groupBuilder = SystemConfig.getSingletonInstance().createGroupFormationBuilder();
		director = SystemConfig.getSingletonInstance().createGroupFormationDirector(groupBuilder);
		director.createGroup(survey, surveyQuestionData.getGroupSize());
		List clusteredGroups = groupBuilder.getGroups();
		resultMap.put(USER_DATA_MAP, fetchGroupDetails(survey.getUserList(), surveyQuestionData, answerList));
		resultMap.put(GROUP_LIST, clusteredGroups);
		return resultMap;
	}

	@Override
	public HashMap<String, HashMap> fetchGroupDetails(List<String> userIdList, SurveyMetaData surveyMetaData,
			ArrayList<Answer> answerList) {
		logger.log(Level.INFO,
				"Fetch user information for displaying survey results userList=" + userIdList.toString());
		HashMap<String, HashMap> userSurveyMap = new HashMap<>();
		UserService userService = SystemConfig.getSingletonInstance().getServiceAbstractFactory().getUserService(
				SystemConfig.getSingletonInstance().getUtilAbstractFactory().getQueryVariableToArrayList());
		User user = null;
		HashMap userMap = null;
		ArrayList<SurveyQuestion> surveyQuestionList = (ArrayList) surveyMetaData.getSurveyQuestions();
		for (String bannerId : userIdList) {
			userMap = new HashMap<>();
			user = SystemConfig.getSingletonInstance().getModelAbstractFactory().getUser();
			userService.loadUserWithBannerId(bannerId, user);
			if (user.isValidUser()) {
				userMap.put(FIRST_NAME, user.getFirstName());
				userMap.put(LAST_NAME, user.getLastName());
				ArrayList userAnswerList = new ArrayList<>();
				for (SurveyQuestion eachSurveyQuestion : surveyQuestionList) {
					for (Answer eachAnswer : answerList) {
						if (eachAnswer.getBannerId().equals(bannerId)
								&& eachAnswer.getSurveyQuestionId().equals(eachSurveyQuestion.getSurveyQuestionId())) {
							if (eachSurveyQuestion.getOptions().size() > 0) {
								String[] answersOption = eachAnswer.getAnswers().split(",");
								for (String eachOption : answersOption) {
									ArrayList<Option> optionsList = (ArrayList) eachSurveyQuestion.getOptions();
									for (Option eachOptionInQuestion : optionsList) {
										if (((Integer) eachOptionInQuestion.getStoredData())
												.equals(Integer.valueOf(eachOption))) {
											userAnswerList.add(eachOptionInQuestion.getDisplayText());
										}
									}
								}
							} else {
								userAnswerList.add(eachAnswer.getAnswers());
							}
						}
					}
				}
				userMap.put(ANSWERS, userAnswerList);
			}
			userSurveyMap.put(bannerId, userMap);
		}
		logger.log(Level.INFO, "Fetched user details for group formation");
		return userSurveyMap;
	}

}
