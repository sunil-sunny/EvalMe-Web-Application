package com.group18.asdc.dao.test;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.group18.asdc.dao.AdminDao;
import com.group18.asdc.entities.Course;

@SpringBootTest
public class AdminDaoImplTest {

	@Test
	public void addCourseTest() {
		Course course  = new Course();
		AdminDao theAdminDaoImplMock = new AdminDaoImplMock();
		boolean isCourseCreated = theAdminDaoImplMock.addCourse(course);
		assertTrue(isCourseCreated);
	}

	@Test
	public void deleteCourseTest() {
		Course course = new Course();
		AdminDao theAdminDaoImplMock = new AdminDaoImplMock();
		boolean isCourseDeleted = theAdminDaoImplMock.deleteCourse(course);
		assertTrue(isCourseDeleted);	
	}
}