package com.group18.asdc.service.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.group18.asdc.entities.User;
import com.group18.asdc.service.CourseRolesService;

public class CourseRolesServiceImplTest {

	@Test
	public void enrollStudentsIntoCourseTestTwo() {
		CourseRolesService theCourseRolesServiceMock = new CourseRolesServiceMock();
		List<User> studentsList = new ArrayList<User>();
		boolean isEnrolled = theCourseRolesServiceMock.enrollStuentsIntoCourse(studentsList, 9);
		assertFalse(isEnrolled);
	}

	@Test
	public void allocateTaTestOne() {
		CourseRolesService theCourseRolesServiceMock = new CourseRolesServiceMock();
		User studentsList = new User();
		boolean isEnrolled = theCourseRolesServiceMock.allocateTa(2, studentsList);
		assertTrue(isEnrolled);
	}

	@Test
	public void allocateTaTestTwo() {
		CourseRolesService theCourseRolesServiceMock = new CourseRolesServiceMock();
		User studentsList = new User("Rahul", "Chahar", "B09898157", "chahar@dal.ca");
		boolean isEnrolled = theCourseRolesServiceMock.allocateTa(2, studentsList);
		assertTrue(isEnrolled);
	}
}
