package com.group18.asdc.dao.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.group18.asdc.TestConfig;
import com.group18.asdc.dao.AdminDao;
import com.group18.asdc.entities.Course;

@SpringBootTest
public class AdminDaoImplTest {
	
	private static final AdminDao theAdminDaoMock=TestConfig.getTestSingletonIntance().getDaoTestAbstractFactory().getAdminDaoTest();

	@Test
	public void addCourseTest() {
		Course course  = new Course();
		boolean isCourseCreated = theAdminDaoMock.addCourse(course);
		assertTrue(isCourseCreated);
	}

	@Test
	public void deleteCourseTest() {
		Course course = new Course();
		boolean isCourseDeleted = theAdminDaoMock.deleteCourse(course);
		assertTrue(isCourseDeleted);	
	}
}