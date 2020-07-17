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

public interface ModelTestAbstractFactory {

	public BasicQuestionData getBasicQuestionDataTest();

	public Option getOptionTest();

	public Course getCourseTest();

	public MultipleChoiceQuestion getMultipleChoiceQuestionTest();

	public PasswordHistory getPasswordHistoryTest();

	public QuestionMetaData getQuestionMetaDataTest();

	public SurveyMetaData getSurveyMetaDataTest();

	public UserRegistartionDetails getIUserRegistartionDetailsTest();

	public User getUserTest();

	public SurveyQuestion getSurveyQuestionTest();

	public SurveyList getSurveyListTest();

	public Answer getAnswerTest();

	public Group getGroupTest();

	public SurveyGroups getSurveyGroupsTest();

	public Course getCourseTest(int courseId, String courseName, User instructorName, List<User> taList,
			List<User> studentList);

	public User getUserTest(String firstName, String lastName, String bannerId, String email);
	
	public UserRegistartionDetails getUserRegistartionDetailsTest();
	
	public ResetPassword getResetPasswordTest();
}
