package com.group18.asdc.dao.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.group18.asdc.dao.CourseRolesDao;
import com.group18.asdc.entities.User;

public class CourseRolesDaoImplTest {

	@Test
	public void enrollStudentsIntoCourseTestOne() {

		CourseRolesDao theCourseRolesDaoMock = new CourseRolesDaoMock();
		User user = new User("Rahul", "Dravid", "B09896157", "dravid@dal.ca");
		User userOne = new User("Rahul", "Chahar", "B09898157", "chahar@dal.ca");
		List<User> studentsList = new ArrayList<User>();
		studentsList.add(user);
		studentsList.add(userOne);
		boolean isEnrolled = theCourseRolesDaoMock.enrollStudentsIntoCourse(studentsList, 5);
		assertTrue(isEnrolled);
	}

	@Test
	public void enrollStudentsIntoCourseTestTwo() {

		CourseRolesDao theCourseRolesDaoMock = new CourseRolesDaoMock();
		List<User> studentsList = new ArrayList<User>();
		boolean isEnrolled = theCourseRolesDaoMock.enrollStudentsIntoCourse(studentsList, 9);
		assertFalse(isEnrolled);
	}

	@Test
	public void allocateTaTestOne() {

		CourseRolesDao theCourseRolesDaoMock = new CourseRolesDaoMock();
		User studentsList = null;
		boolean isEnrolled = theCourseRolesDaoMock.allocateTa(2, studentsList);
		assertFalse(isEnrolled);
	}

	@Test
	public void allocateTaTestTwo() {

		CourseRolesDao theCourseRolesDaoMock = new CourseRolesDaoMock();
		User studentsList = new User("Rahul", "Chahar", "B09898157", "chahar@dal.ca");
		boolean isEnrolled = theCourseRolesDaoMock.allocateTa(2, studentsList);
		assertTrue(isEnrolled);
	}
}
