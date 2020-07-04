package com.group18.asdc.service.test;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import com.group18.asdc.entities.Course;
import com.group18.asdc.service.AdminService;


@SpringBootTest
public class AdminServiceImplTest {

	@Test
	public void isCourseIdValid(){
		AdminService theAdminServiceImplMock = new AdminServiceImplMock();
		Course course = new Course();
		boolean isValid = theAdminServiceImplMock.isCourseIdValid(course);
		assertTrue(isValid);
	}
	
	@Test
	public void iscreateCourseParametersValid() {
		AdminService theAdminServiceImplMock = new AdminServiceImplMock();
		Course course = new Course();
		boolean isValid = theAdminServiceImplMock.iscreateCourseParametersValid(course);
		assertTrue(isValid);
	}
	
	@Test 
	public void createCourse() {
		AdminService theAdminServiceImplMock = new AdminServiceImplMock();
		Course course = new Course();
		boolean isCreated = theAdminServiceImplMock.createCourse(course);
		assertTrue(isCreated);
	}
	
	@Test
	public void deleteCourse() {
		AdminService theAdminServiceImplMock = new AdminServiceImplMock();
		Course course = new Course();
		boolean isCreated = theAdminServiceImplMock.deleteCourse(course);
		assertTrue(isCreated);
	}
}
