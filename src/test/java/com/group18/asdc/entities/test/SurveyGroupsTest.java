package com.group18.asdc.entities.test;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.group18.asdc.TestConfig;
import com.group18.asdc.entities.Group;
import com.group18.asdc.entities.SurveyGroups;
import com.group18.asdc.entities.User;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SurveyGroupsTest {

	@Test
	public void getSurveyId() {
		SurveyGroups surveyGroups = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getSurveyGroupsTest();
		surveyGroups.setSurveyId(4);
		assertTrue(surveyGroups.getSurveyId() == 4);
	}

	@Test
	public void setSurveyId() {
		SurveyGroups surveyGroups = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getSurveyGroupsTest();
		surveyGroups.setSurveyId(4);
		assertTrue(surveyGroups.getSurveyId() == 4);
	}

	@Test
	public void getSurveyGroups() {

		Group group = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getGroupTest();
		List<Group> listGroup = new ArrayList<Group>();

		group.setGroupId(1);
		group.setGroupName("Group1");
		List<User> userList = new ArrayList<User>();
		User user = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest();
		user.setBannerId("B00842470");
		user.setEmail("lukeskywalker@dal.ca");
		user.setFirstName("Luke");
		user.setLastName("Skywalker");
		userList.add(user);
		group.setGroupMembers(userList);
		listGroup.add(group);
		SurveyGroups surveyGroup = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getSurveyGroupsTest();
		surveyGroup.setSurveyGroups(listGroup);
		assertTrue(surveyGroup.getSurveyGroups().equals(listGroup));
	}

	@Test
	public void setSurveyGroups() {
		Group group = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getGroupTest();
		List<Group> listGroup = new ArrayList<Group>();
		group.setGroupId(1);
		group.setGroupName("Group1");
		List<User> userList = new ArrayList<User>();
		User user = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest();
		user.setBannerId("B00842470");
		user.setEmail("lukeskywalker@dal.ca");
		user.setFirstName("Luke");
		user.setLastName("Skywalker");
		userList.add(user);
		group.setGroupMembers(userList);
		listGroup.add(group);
		SurveyGroups surveyGroup = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getSurveyGroupsTest();
		surveyGroup.setSurveyGroups(listGroup);
		assertTrue(surveyGroup.getSurveyGroups().equals(listGroup));
	}
}
