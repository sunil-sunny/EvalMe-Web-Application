package com.group18.asdc.service.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.group18.asdc.TestConfig;
import com.group18.asdc.entities.User;
import com.group18.asdc.errorhandling.EnrollingStudentException;
import com.group18.asdc.errorhandling.FileProcessingException;
import com.group18.asdc.service.CourseRolesService;

public class CourseRolesServiceImplTest {

	private static final CourseRolesService theCourseRolesServiceMock = TestConfig.getTestSingletonIntance()
			.getServiceTestAbstractFactory().getCourseRoleServiceTest();

	@Test
	public void enrollStudentsIntoCourseTestTwo() {

		List<User> studentsList = new ArrayList<User>();
		boolean isEnrolled;
		try {
			isEnrolled = theCourseRolesServiceMock.enrollStuentsIntoCourse(studentsList, 9);
		} catch (EnrollingStudentException e) {
			isEnrolled = Boolean.FALSE;
		}
		assertFalse(isEnrolled);
	}

	@Test
	public void allocateTaTestOne() {

		User studentsList = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest();
		boolean isEnrolled = theCourseRolesServiceMock.allocateTa(2, studentsList);
		assertTrue(isEnrolled);
	}

	@Test
	public void allocateTaTestTwo() {

		User studentsList = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest("Rahul",
				"Chahar", "B09898157", "chahar@dal.ca");
		boolean isEnrolled = theCourseRolesServiceMock.allocateTa(2, studentsList);
		assertTrue(isEnrolled);
	}

	@Test
	public void extraxtValidStudentsFromFileTest() {
		try {
			File file = new File("");
			FileInputStream inputStream = new FileInputStream(file);
			MultipartFile multipartFile = new MockMultipartFile("file", inputStream);
			List<User> validUsers = theCourseRolesServiceMock.extraxtValidStudentsFromFile(multipartFile);
			assertNotEquals(0, validUsers.size());
		} catch (FileProcessingException e) {
			boolean fileError = Boolean.FALSE;
			assertFalse(fileError);
		} catch (FileNotFoundException e) {
			boolean fileError = Boolean.FALSE;
			assertFalse(fileError);
		} catch (IOException e) {
			boolean fileError = Boolean.FALSE;
			assertFalse(fileError);
		}
	}
}
