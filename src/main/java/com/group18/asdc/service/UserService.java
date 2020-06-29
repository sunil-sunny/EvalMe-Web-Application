package com.group18.asdc.service;

import java.util.ArrayList;
import java.util.List;

import com.group18.asdc.entities.User;
import com.group18.asdc.security.IPasswordEncryption;

public interface UserService {

	public boolean isUserExists(User user);

	public User getUserById(String bannerId);

	public List<User> filterEligibleUsersForCourse(List<User> studentList, int courseId);

	public List<User> getAllUsersByCourse(int courseId);

	public void loadUserWithBannerId(String bannerId, User userObj);

	public Boolean updatePassword(User userObj, IPasswordEncryption passwordEncryption);

	public User getCurrentUser();

	public ArrayList getUserRoles(User userObj);
}