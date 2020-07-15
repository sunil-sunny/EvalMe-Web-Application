package com.group18.asdc.dao.test;

import java.util.ArrayList;
import java.util.List;
import com.group18.asdc.dao.AdminDao;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;

public class AdminDaoImplMock implements AdminDao {

	private static List<Course> courseList;
	private static User user;

	public AdminDaoImplMock() {
		courseList = new ArrayList<Course>();
		user = new User();
	}

	@Override
	public boolean addCourse(Course course) {
		course.setCourseId(0);
		course.setCourseName("Machine Learning");
		user.setBannerId("B00862344");
		course.setInstructorName(user);
		courseList.add(course);
		return Boolean.TRUE;
	}

	@Override
	public boolean deleteCourse(Course course) {
		course.setCourseId(0);
		course.setCourseName("Machine Learning");
		user.setBannerId("B00862344");
		course.setInstructorName(user);
		courseList.add(course);
		courseList.remove(course);
		return Boolean.TRUE;
	}
}