package com.group18.asdc.service.test;

import com.group18.asdc.dao.test.AdminDaoImplMock;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;
import com.group18.asdc.service.AdminService;

public class AdminServiceImplMock implements AdminService{
	
	private static User user;

	public AdminServiceImplMock() {
		user = new User();
	}
	
	@Override
	public boolean isCourseIdValid(Course course) {
		course.setCourseId(7382);
		course.setCourseName("Software Development");
		user.setBannerId("B00842470");
		course.setInstructorName(user);
		return true;
	}

	@Override
	public boolean iscreateCourseParametersValid(Course course) {
		course.setCourseId(7382);
		course.setCourseName("Software Development");
		user.setBannerId("B00842470");
		course.setInstructorName(user);
		return true;
	}

	@Override
	public boolean createCourse(Course course) {
		AdminDaoImplMock theAdminDaoImplMock = new AdminDaoImplMock();
		return theAdminDaoImplMock.addCourse(course);
	}

	@Override
	public boolean deleteCourse(Course course) {
		AdminDaoImplMock theAdminDaoImplMock = new AdminDaoImplMock();
		return theAdminDaoImplMock.deleteCourse(course);
		}
}
