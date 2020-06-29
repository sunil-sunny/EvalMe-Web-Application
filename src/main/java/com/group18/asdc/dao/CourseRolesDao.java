package com.group18.asdc.dao;

import java.util.List;

import com.group18.asdc.entities.User;

public interface CourseRolesDao {

	public boolean allocateTa(int courseId, User user);

	public boolean enrollStudentsIntoCourse(List<User> studentList, int courseId);
}
