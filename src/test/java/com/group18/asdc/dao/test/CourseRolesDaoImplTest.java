package com.group18.asdc.dao.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import com.group18.asdc.TestConfig;
import com.group18.asdc.dao.CourseRolesDao;
import com.group18.asdc.entities.User;

public class CourseRolesDaoImplTest {

	private static final CourseRolesDao theCourseRolesDaoMock = TestConfig.getTestSingletonIntance()
			.getDaoTestAbstractFactory().getCourseRolesDaoTest();

	@Test
	public void enrollStudentsIntoCourseTestOne() {

		User user = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest("Rahul", "Dravid", "B09896157", "dravid@dal.ca");
		User userOne = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest("Rahul", "Chahar", "B09898157", "chahar@dal.ca");
		List<User> studentsList = new ArrayList<User>();
		studentsList.add(user);
		studentsList.add(userOne);
		boolean isEnrolled = theCourseRolesDaoMock.enrollStudentsIntoCourse(studentsList, 5);
		assertTrue(isEnrolled);
	}

	@Test
	public void enrollStudentsIntoCourseTestTwo() {

		List<User> studentsList = new ArrayList<User>();
		boolean isEnrolled = theCourseRolesDaoMock.enrollStudentsIntoCourse(studentsList, 9);
		assertFalse(isEnrolled);
	}

	@Test
	public void allocateTaTestOne() {

		User studentsList = null;
		boolean isEnrolled = theCourseRolesDaoMock.allocateTa(2, studentsList);
		assertFalse(isEnrolled);
	}

	@Test
	public void allocateTaTestTwo() {

		User studentsList = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest("Rahul", "Chahar", "B09898157", "chahar@dal.ca");
		boolean isEnrolled = theCourseRolesDaoMock.allocateTa(2, studentsList);
		assertTrue(isEnrolled);
	}
}
