package com.group18.asdc.service.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.group18.asdc.TestConfig;
import com.group18.asdc.entities.Course;
import com.group18.asdc.service.AdminService;

@SpringBootTest
public class AdminServiceImplTest {

	private static final AdminService theAdminServiceImplMock = TestConfig.getTestSingletonIntance()
			.getServiceTestAbstractFactory().getAdminServiceTest();

	@Test
	public void isCourseIdValid() {

		Course course = new Course();
		boolean isValid = theAdminServiceImplMock.isCourseIdValid(course);
		assertTrue(isValid);
	}

	@Test
	public void iscreateCourseParametersValid() {
		
		Course course = new Course();
		boolean isValid = theAdminServiceImplMock.iscreateCourseParametersValid(course);
		assertTrue(isValid);
	}

	@Test
	public void createCourse() {
		
		Course course = new Course();
		boolean isCreated = theAdminServiceImplMock.createCourse(course);
		assertTrue(isCreated);
	}

	@Test
	public void deleteCourse() {
		
		Course course = new Course();
		boolean isCreated = theAdminServiceImplMock.deleteCourse(course);
		assertTrue(isCreated);
	}
}
