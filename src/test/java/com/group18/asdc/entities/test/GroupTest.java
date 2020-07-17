package com.group18.asdc.entities.test;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.group18.asdc.TestConfig;
import com.group18.asdc.entities.Group;
import com.group18.asdc.entities.User;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class GroupTest {

	@Test
	public void getGroupId() {
		Group group = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getGroupTest();
		group.setGroupId(1);
		assertTrue(group.getGroupId() == 1);
	}

	@Test
	public void setGroupId() {
		Group group = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getGroupTest();
		group.setGroupId(1);
		assertTrue(group.getGroupId() == 1);
	}

	@Test
	public void getGroupMembers() {
		User user = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest();
		user.setBannerId("B00842470");
		user.setEmail("lukeskywalker@dal.ca");
		user.setFirstName("Luke");
		user.setLastName("Skywalker");
		List<User> userList = new ArrayList<User>();
		userList.add(user);
		Group group = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getGroupTest();
		group.setGroupMembers(userList);
		assertTrue(group.getGroupMembers().equals(userList));
	}

	@Test
	public void setGroupMembers() {
		User user = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest();
		user.setBannerId("B00842470");
		user.setEmail("lukeskywalker@dal.ca");
		user.setFirstName("Luke");
		user.setLastName("Skywalker");
		List<User> userList = new ArrayList<User>();
		userList.add(user);
		Group group = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getGroupTest();
		group.setGroupMembers(userList);
		assertTrue(group.getGroupMembers().equals(userList));
	}

	@Test
	public void getGroupName() {
		Group group = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getGroupTest();
		group.setGroupName("First Group");
		assertTrue(group.getGroupName().equals("First Group"));
	}

	@Test
	public void setGroupName() {
		Group group = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getGroupTest();
		group.setGroupName("First Group");
		assertTrue(group.getGroupName().equals("First Group"));
	}
}