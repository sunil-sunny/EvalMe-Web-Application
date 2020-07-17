package com.group18.asdc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.dao.UserDao;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;
import com.group18.asdc.security.IPasswordEncryption;
import com.group18.asdc.util.IQueryVariableToArrayList;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserServiceImpl implements UserService {

	private static final UserDao theUserDao = SystemConfig.getSingletonInstance().getDaoAbstractFactory().getUserDao();
	private IQueryVariableToArrayList queryVariableToArrayList;
	private Logger logger = Logger.getLogger(UserService.class.getName());

	public UserServiceImpl(IQueryVariableToArrayList queryVariableToArrayList) {
		super();
		this.queryVariableToArrayList = queryVariableToArrayList;
	}

	@Override
	public boolean isUserExists(User user) {
		return theUserDao.isUserExists(user);
	}

	@Override
	public User getUserById(String bannerId) {
		return theUserDao.getUserById(bannerId);
	}

	@Override
	public List<User> getAllUsersByCourse(int courseId) {
		return theUserDao.getAllUsersByCourse(courseId);
	}

	@Override
	public int loadUserWithBannerId(String bannerId, User userObj) {
		logger.log(Level.INFO, "Fetching user data from database for user=" + bannerId);
		ArrayList<Object> valuesList = queryVariableToArrayList.convertQueryVariablesToArrayList(bannerId);
		return theUserDao.loadUserWithBannerId(valuesList, userObj);
	}

	@Override
	public Boolean updatePassword(User userObj, IPasswordEncryption passwordEncryption) {
		logger.log(Level.INFO, "Updating password for user=" + userObj.getBannerId());
		ArrayList<Object> criteriaList = queryVariableToArrayList
				.convertQueryVariablesToArrayList(userObj.getBannerId());
		ArrayList<Object> valueList = queryVariableToArrayList
				.convertQueryVariablesToArrayList(passwordEncryption.encryptPassword(userObj.getPassword()));
		return theUserDao.updatePassword(criteriaList, valueList);
	}

	@Override
	public ArrayList getUserRoles(User userObj) {
		logger.log(Level.INFO, "Fetching user roles for user=" + userObj.getBannerId());
		ArrayList<Object> criteriaList = queryVariableToArrayList
				.convertQueryVariablesToArrayList(userObj.getBannerId());
		return theUserDao.getUserRoles(criteriaList);
	}

	@Override
	public User getCurrentUser() {
		logger.log(Level.INFO, "Fetching logged in user information");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String bannerid = "";
		if (principal instanceof UserDetails) {
			bannerid = ((UserDetails) principal).getUsername();
		} else {
			bannerid = principal.toString();
		}
		User currentUser = null;
		if (null == bannerid) {
			return currentUser;
		}
		currentUser = this.getUserById(bannerid);
		return currentUser;
	}

	@Override
	public boolean isUserInstructor(Course course) {

		return theUserDao.isUserInstructor(course);
	}
}