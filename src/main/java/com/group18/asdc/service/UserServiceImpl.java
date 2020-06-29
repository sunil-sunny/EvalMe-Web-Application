package com.group18.asdc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.group18.asdc.dao.UserDao;
import com.group18.asdc.dao.UserDaoImpl;
import com.group18.asdc.entities.User;
import com.group18.asdc.security.IPasswordEncryption;
import com.group18.asdc.util.IQueryVariableToArrayList;

public class UserServiceImpl implements UserService {

	private UserDao userDao;
	private IQueryVariableToArrayList queryVariableToArrayList;

	public UserServiceImpl(IQueryVariableToArrayList queryVariableToArrayList) {
		userDao = new UserDaoImpl();
		this.queryVariableToArrayList = queryVariableToArrayList;
	}

	@Override
	public boolean isUserExists(User user) {

		return userDao.isUserExists(user);
	}

	@Override
	public User getUserById(String bannerId) {

		return userDao.getUserById(bannerId);
	}

	@Override
	public List<User> filterEligibleUsersForCourse(List<User> studentList, int courseId) {

		return userDao.filterEligibleUsersForCourse(studentList, courseId);
	}

	@Override
	public List<User> getAllUsersByCourse(int courseId) {

		return userDao.getAllUsersByCourse(courseId);
	}

	@Override
	public void loadUserWithBannerId(String bannerId, User userObj) {
		ArrayList<Object> valuesList = queryVariableToArrayList.convertQueryVariablesToArrayList(bannerId);
		userDao.loadUserWithBannerId(valuesList, userObj);
	}

	@Override
	public Boolean updatePassword(User userObj, IPasswordEncryption passwordEncryption) {

		ArrayList<Object> criteriaList = queryVariableToArrayList
				.convertQueryVariablesToArrayList(userObj.getBannerId());
		ArrayList<Object> valueList = queryVariableToArrayList
				.convertQueryVariablesToArrayList(passwordEncryption.encryptPassword(userObj.getPassword()));
		return userDao.updatePassword(criteriaList, valueList);
	}

	@Override
	public ArrayList getUserRoles(User userObj) {

		ArrayList<Object> criteriaList = queryVariableToArrayList
				.convertQueryVariablesToArrayList(userObj.getBannerId());
		return userDao.getUserRoles(criteriaList);

	}

	@Override
	public User getCurrentUser() {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String bannerid = "";
		if (principal instanceof UserDetails) {
			bannerid = ((UserDetails) principal).getUsername();

		} else {
			bannerid = principal.toString();
		}
		User currentUser = null;
		if (bannerid != null) {
			currentUser = this.getUserById(bannerid);
		}
		return currentUser;
	}

}