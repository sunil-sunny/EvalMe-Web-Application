package com.group18.asdc.service.test;

import java.util.ArrayList;
import java.util.List;

import com.group18.asdc.dao.test.UserDaoImplMock;
import com.group18.asdc.entities.User;
import com.group18.asdc.service.UserService;

public class UserServiceImplMock implements UserService {

	@Override
	public boolean isUserExists(User user) {
		UserDaoImplMock theDaoImplMock = new UserDaoImplMock();
		return theDaoImplMock.isUserExists(user);
	}

	@Override
	public User getUserById(String bannerId) {
		UserDaoImplMock theDaoImplMock = new UserDaoImplMock();
		return theDaoImplMock.getUserById(bannerId);
	}

	@Override
	public List<User> filterEligibleUsersForCourse(List<User> studentList, int courseId) {
		UserDaoImplMock theDaoImplMock = new UserDaoImplMock();
		return theDaoImplMock.filterEligibleUsersForCourse(studentList, courseId);
	}

	@Override
	public List<User> getAllUsersByCourse(int courseId) {
		UserDaoImplMock theDaoImplMock = new UserDaoImplMock();
		return theDaoImplMock.getAllUsersByCourse(courseId);
	}

	@Override
	public void loadUserWithBannerId(String bannerId, User userObj) {
		UserDaoImplMock theDaoImplMock = new UserDaoImplMock();
		ArrayList valueList = new ArrayList<>();
		valueList.add(bannerId);
		
		theDaoImplMock.loadUserWithBannerId(valueList, userObj);
		

	}

	@Override
	public Boolean updatePassword(User userObj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList getUserRoles(User userObj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getCurrentUser() {
		// TODO Auto-generated method stub
		return null;
	}


}
