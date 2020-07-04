package com.group18.asdc.dao;

import java.util.ArrayList;
import java.util.List;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;

public interface UserDao {

	public boolean isUserInstructor(Course course);

	public boolean isUserExists(User user);

	public User getUserById(String bannerId);

	public List<User> getAllUsersByCourse(int courseId);

	public void loadUserWithBannerId(ArrayList<Object> valueList, User userObj);

	public Boolean updatePassword(ArrayList<Object> criteriaList, ArrayList<Object> valuesList);

	public ArrayList getUserRoles(ArrayList<Object> criteriaList);
}