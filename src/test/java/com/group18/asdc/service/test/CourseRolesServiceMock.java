package com.group18.asdc.service.test;

import java.util.List;
import com.group18.asdc.dao.test.CourseRolesDaoMock;
import com.group18.asdc.entities.User;
import com.group18.asdc.service.CourseRolesService;

public class CourseRolesServiceMock implements CourseRolesService {

	@Override
	public boolean allocateTa(int courseId, User user) {
		CourseRolesDaoMock theCourseRolesDaoMock = new CourseRolesDaoMock();
		return theCourseRolesDaoMock.allocateTa(courseId, user);
	}

	@Override
	public boolean enrollStuentsIntoCourse(List<User> studentList, int courseId) {
		CourseRolesDaoMock theCourseRolesDaoMock = new CourseRolesDaoMock();
		return theCourseRolesDaoMock.enrollStudentsIntoCourse(studentList, courseId);
	}
}
