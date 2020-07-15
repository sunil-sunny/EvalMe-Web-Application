package com.group18.asdc.service.test;

import com.group18.asdc.TestConfig;
import com.group18.asdc.dao.AdminDao;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;
import com.group18.asdc.service.AdminService;

public class AdminServiceImplMock implements AdminService {

	private static User user;
	private static final AdminDao theAdminDaoImplMock = TestConfig.getTestSingletonIntance()
			.getDaoTestAbstractFactory().getAdminDaoTest();

	public AdminServiceImplMock() {
		user = new User();
	}

	@Override
	public boolean isCourseIdValid(Course course) {
		course.setCourseId(7382);
		course.setCourseName("Software Development");
		user.setBannerId("B00842470");
		course.setInstructorName(user);
		return Boolean.TRUE;
	}

	@Override
	public boolean iscreateCourseParametersValid(Course course) {
		course.setCourseId(7382);
		course.setCourseName("Software Development");
		user.setBannerId("B00842470");
		course.setInstructorName(user);
		return Boolean.TRUE;
	}

	@Override
	public boolean createCourse(Course course) {
		return theAdminDaoImplMock.addCourse(course);
	}

	@Override
	public boolean deleteCourse(Course course) {
		return theAdminDaoImplMock.deleteCourse(course);
	}
}
