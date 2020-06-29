package com.group18.asdc.dao;

import java.util.ArrayList;
import java.util.List;

import com.group18.asdc.entities.User;

public interface UserDao {


	// public Boolean authenticateByEmailAndPassword(ArrayList<Object> valueList)
	// throws SQLException;
	public boolean isUserExists(User user);

	public User getUserById(String bannerId);

	public List<User> filterEligibleUsersForCourse(List<User> studentList, int courseId);

	public List<User> getAllUsersByCourse(int courseId);

	public User getInstructorForCourse(int courseId);

	public void loadUserWithBannerId(ArrayList<Object> valueList, User userObj);

	public Boolean updatePassword(ArrayList<Object> criteriaList, ArrayList<Object> valuesList);

	public ArrayList getUserRoles(ArrayList<Object> criteriaList);
}