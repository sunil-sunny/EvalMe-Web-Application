package com.group18.asdc.service.test;

import java.util.ArrayList;
import java.util.List;

import com.group18.asdc.TestConfig;
import com.group18.asdc.dao.UserDao;
import com.group18.asdc.dao.test.UserDaoImplMock;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;
import com.group18.asdc.security.IPasswordEncryption;
import com.group18.asdc.service.UserService;

public class UserServiceImplMock implements UserService {

	private final static UserDao theDaoImplMock = TestConfig.getTestSingletonIntance().getDaoTestAbstractFactory()
			.getUserDaoTest();

	private void getDefaultUserObj(User userObj) {
		userObj.setBannerId("B00838575");
		userObj.setEmail("kr630601@dal.ca");
		userObj.setFirstName("Karthikk");
		userObj.setLastName("Tamil");
		userObj.setPassword("karthikk");
	}

	@Override
	public boolean isUserExists(User user) {
		return theDaoImplMock.isUserExists(user);
	}

	@Override
	public User getUserById(String bannerId) {
		return theDaoImplMock.getUserById(bannerId);
	}

	@Override
	public List<User> getAllUsersByCourse(int courseId) {
		return theDaoImplMock.getAllUsersByCourse(courseId);
	}

	@Override
	public int loadUserWithBannerId(String bannerId, User userObj) {
		ArrayList valueList = new ArrayList<>();
		if (bannerId.equals("B00838575")) {
			getDefaultUserObj(userObj);
		}
		return theDaoImplMock.loadUserWithBannerId(valueList, userObj);
	}

	@Override
	public Boolean updatePassword(User userObj, IPasswordEncryption passwordEncryption) {
		return Boolean.TRUE;
	}

	@Override
	public ArrayList getUserRoles(User userObj) {
		ArrayList rolesList = new ArrayList<>();
		rolesList.add("ADMIN");
		rolesList.add("USER");
		return rolesList;
	}

	@Override
	public User getCurrentUser() {
		User current = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest();
		return current;
	}

	@Override
	public boolean isUserInstructor(Course course) {
		UserDao theUserDao = TestConfig.getTestSingletonIntance().getDaoTestAbstractFactory().getUserDaoTest();
		return theUserDao
				.isUserInstructor(TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getCourseTest());
	}
}