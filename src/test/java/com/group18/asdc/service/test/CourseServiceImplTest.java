package com.group18.asdc.service.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.group18.asdc.TestConfig;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;
import com.group18.asdc.service.CourseDetailsService;

@SpringBootTest
public class CourseServiceImplTest {

	private final static CourseDetailsService courseServiceImplMock = TestConfig.getTestSingletonIntance()
			.getServiceTestAbstractFactory().getCourseDetailsServiceTest();

	@Test
	public void getAllCoursesTestOne() {

		List<Course> courseList = courseServiceImplMock.getAllCourses();
		assertNotEquals(0, courseList.size());
	}

	@Test
	public void getCoursesWhereUserIsStudentTestOne() {
		User studentFive = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest("Shane",
				"Warne", "B00654194", "shane@dal.ca");
		List<Course> courseList = courseServiceImplMock.getCoursesWhereUserIsStudent(studentFive);
		assertNotEquals(0, courseList.size());
	}

	@Test
	public void getCoursesWhereUserIsStudentTestTwo() {
		User studentFive = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest("Shane",
				"Watson", "B00222222", "shane@dal.ca");
		List<Course> courseList = courseServiceImplMock.getCoursesWhereUserIsStudent(studentFive);
		assertEquals(0, courseList.size());
	}

	@Test
	public void getCoursesWhereUserIsInstrcutorTestOne() {
		User instructorThree = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest("Michel",
				"Bevan", "B00675984", "bevan@dal.com");
		List<Course> instructorCourses = courseServiceImplMock.getCoursesWhereUserIsInstrcutor(instructorThree);
		assertNotEquals(0, instructorCourses.size());
	}

	@Test
	public void getCoursesWhereUserIsInstrcutorTestTwo() {
		User studentFive = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest("Shane",
				"Warne", "B00654194", "shane@dal.ca");
		List<Course> instructorCourses = courseServiceImplMock.getCoursesWhereUserIsInstrcutor(studentFive);
		assertEquals(0, instructorCourses.size());
	}

	@Test
	public void getCoursesWhereUserIsTATestOne() {
		User instructorTwo = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest("Don",
				"Bradman", "B00741399", "don@dal.com");
		List<Course> instructorCourses = courseServiceImplMock.getCoursesWhereUserIsTA(instructorTwo);
		assertEquals(0, instructorCourses.size());
	}

	@Test
	public void getCoursesWhereUserIsTATestTwo() {
		User taTwo = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest("Ricky", "Ponting",
				"B00951789", "ricky@dal.ca");
		List<Course> instructorCourses = courseServiceImplMock.getCoursesWhereUserIsTA(taTwo);
		assertNotEquals(0, instructorCourses.size());
	}

	@Test
	public void filterEligibleUsersForCourseTest() {
		User studentTwo = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest("Glenn",
				"Maxwell", "B00753159", "glenn@dal.ca");
		User studentThree = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest("Brett",
				"Lee", "B00852693", "ricky@dal.ca");
		List<User> userList = Arrays.asList(studentTwo, studentThree);
		List<User> eligiList = courseServiceImplMock.filterEligibleUsersForCourse(userList, 1);
		assertEquals(2, eligiList.size());
	}

	@Test
	public void getCourseByIdTest() {
		Course theCourse = courseServiceImplMock.getCourseById(1);
		assertNotNull(theCourse);
	}
}