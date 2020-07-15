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
		valueList.add(bannerId);
		return theDaoImplMock.loadUserWithBannerId(valueList, userObj);
	}

	@Override
	public Boolean updatePassword(User userObj, IPasswordEncryption passwordEncryption) {
		return null;
	}

	@Override
	public ArrayList getUserRoles(User userObj) {
		return null;
	}

	@Override
	public User getCurrentUser() {
		User current = new User();
		return current;
	}

	@Override
	public boolean isUserInstructor(Course course) {
		UserDao theUserDao = new UserDaoImplMock();
		return theUserDao.isUserInstructor(new Course());
	}
}