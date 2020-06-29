package com.group18.asdc.service.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.group18.asdc.entities.User;

public class CourseRolesServiceImplTest {

	/*
	 * Below test send set of students and ideally the result will be true if they
	 * are added.
	 */
	

	/*
	 * Below test send set of empty students and ideally the result will be false if
	 * they are added.
	 */
	@Test
	public void enrollStudentsIntoCourseTestTwo() {

		CourseRolesServiceMock theCourseRolesServiceMock = new CourseRolesServiceMock();

		List<User> studentsList = new ArrayList<User>();

		boolean isEnrolled = theCourseRolesServiceMock.enrollStuentsIntoCourse(studentsList, 9);
		assertFalse(isEnrolled);
	}

	/*
	 * Below test passes the invalid users and get false from method
	 */
	@Test
	public void allocateTaTestOne() {

		CourseRolesServiceMock theCourseRolesServiceMock = new CourseRolesServiceMock();
		User studentsList = new User();
		boolean isEnrolled = theCourseRolesServiceMock.allocateTa(2, studentsList);
		assertTrue(isEnrolled);

	}

	/*
	 * Below test passes the valid users and get true from method
	 */
	@Test
	public void allocateTaTestTwo() {

		CourseRolesServiceMock theCourseRolesServiceMock = new CourseRolesServiceMock();
		User studentsList = new User("Rahul", "Chahar", "B09898157", "chahar@dal.ca");
		boolean isEnrolled = theCourseRolesServiceMock.allocateTa(2, studentsList);
		assertTrue(isEnrolled);

	}
}
