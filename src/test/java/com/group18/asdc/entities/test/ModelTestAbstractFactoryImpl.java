package com.group18.asdc.entities.test;

import java.util.List;

import com.group18.asdc.entities.Answer;
import com.group18.asdc.entities.BasicQuestionData;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.Group;
import com.group18.asdc.entities.MultipleChoiceQuestion;
import com.group18.asdc.entities.Option;
import com.group18.asdc.entities.PasswordHistory;
import com.group18.asdc.entities.QuestionMetaData;
import com.group18.asdc.entities.SurveyGroups;
import com.group18.asdc.entities.SurveyList;
import com.group18.asdc.entities.SurveyMetaData;
import com.group18.asdc.entities.SurveyQuestion;
import com.group18.asdc.entities.User;
import com.group18.asdc.entities.UserRegistartionDetails;
import com.group18.asdc.handlingformsubmission.ResetPassword;

public class ModelTestAbstractFactoryImpl implements ModelTestAbstractFactory {

	@Override
	public BasicQuestionData getBasicQuestionDataTest() {
		return new BasicQuestionData();
	}

	@Override
	public Option getOptionTest() {
		return new Option();
	}

	@Override
	public Course getCourseTest() {
		return new Course();
	}

	@Override
	public MultipleChoiceQuestion getMultipleChoiceQuestionTest() {
		return new MultipleChoiceQuestion();
	}

	@Override
	public PasswordHistory getPasswordHistoryTest() {
		return new PasswordHistory();
	}

	@Override
	public QuestionMetaData getQuestionMetaDataTest() {
		return new QuestionMetaData();
	}

	@Override
	public SurveyMetaData getSurveyMetaDataTest() {
		return new SurveyMetaData();
	}

	@Override
	public UserRegistartionDetails getIUserRegistartionDetailsTest() {
		return new UserRegistartionDetails();
	}

	@Override
	public User getUserTest() {
		return new User();
	}

	@Override
	public SurveyQuestion getSurveyQuestionTest() {
		return new SurveyQuestion();
	}

	@Override
	public SurveyList getSurveyListTest() {

		return new SurveyList();
	}

	@Override
	public Answer getAnswerTest() {
		return new Answer();
	}

	@Override
	public Group getGroupTest() {
		return new Group();
	}

	@Override
	public SurveyGroups getSurveyGroupsTest() {
		return new SurveyGroups();
	}

	@Override
	public Course getCourseTest(int courseId, String courseName, User instructorName, List<User> taList,
			List<User> studentList) {

		return new Course(courseId, courseName, instructorName, taList, studentList);
	}

	@Override
	public User getUserTest(String firstName, String lastName, String bannerId, String email) {
		return new User(firstName, lastName, bannerId, email);
	}

	@Override
	public UserRegistartionDetails getUserRegistartionDetailsTest() {

		return new UserRegistartionDetails();
	}

	@Override
	public ResetPassword getResetPasswordTest() {
		return new ResetPassword();
	}
}
