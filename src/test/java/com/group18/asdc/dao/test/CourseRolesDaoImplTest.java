package com.group18.asdc.dao.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.group18.asdc.entities.User;

public class CourseRolesDaoImplTest {

	/*
	 * Below test send set of students and ideally the result will be true if they
	 * are added.
	 */
	@Test
	public void enrollStudentsIntoCourseTestOne() {

		CourseRolesDaoMock theCourseRolesDaoMock = new CourseRolesDaoMock();
		User user = new User("Rahul", "Dravid", "B09896157", "dravid@dal.ca");
		User userOne = new User("Rahul", "Chahar", "B09898157", "chahar@dal.ca");
		List<User> studentsList = new ArrayList<User>();
		studentsList.add(user);
		studentsList.add(userOne);
		boolean isEnrolled = theCourseRolesDaoMock.enrollStudentsIntoCourse(studentsList, 5);
		assertTrue(isEnrolled);
	}

	/*
	 * Below test send set of empty students and ideally the result will be false if
	 * they are added.
	 */
	@Test
	public void enrollStudentsIntoCourseTestTwo() {

		CourseRolesDaoMock theCourseRolesDaoMock = new CourseRolesDaoMock();

		List<User> studentsList = new ArrayList<User>();

		boolean isEnrolled = theCourseRolesDaoMock.enrollStudentsIntoCourse(studentsList, 9);
		assertFalse(isEnrolled);
	}

	/*
	 * Below test passes the invalid users and get false from method
	 */
	@Test
	public void allocateTaTestOne() {

		CourseRolesDaoMock theCourseRolesDaoMock = new CourseRolesDaoMock();
		User studentsList = null;
		boolean isEnrolled = theCourseRolesDaoMock.allocateTa(2, studentsList);
		assertFalse(isEnrolled);

	}

	/*
	 * Below test passes the valid users and get true from method
	 */
	@Test
	public void allocateTaTestTwo() {

		CourseRolesDaoMock theCourseRolesDaoMock = new CourseRolesDaoMock();
		User studentsList = new User("Rahul", "Chahar", "B09898157", "chahar@dal.ca");
		;
		boolean isEnrolled = theCourseRolesDaoMock.allocateTa(2, studentsList);
		assertTrue(isEnrolled);

	}
}
