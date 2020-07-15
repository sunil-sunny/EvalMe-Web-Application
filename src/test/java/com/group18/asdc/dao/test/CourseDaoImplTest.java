package com.group18.asdc.dao.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.group18.asdc.TestConfig;
import com.group18.asdc.dao.CourseDetailsDao;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;

@SpringBootTest
public class CourseDaoImplTest {

	private static final CourseDetailsDao courseDaoImplMock = TestConfig.getTestSingletonIntance()
			.getDaoTestAbstractFactory().getCourseDaoTest();

	@Test
	public void getAllCoursesTestOne() {
		List<Course> courseList = courseDaoImplMock.getAllCourses();
		assertNotEquals(0, courseList.size());
	}

	@Test
	public void getCoursesWhereUserIsStudentTestOne() {
		User studentFive = new User("Shane", "Warne", "B00654194", "shane@dal.ca");
		List<Course> courseList = courseDaoImplMock.getCoursesWhereUserIsStudent(studentFive);
		assertNotEquals(0, courseList.size());
	}

	@Test
	public void getCoursesWhereUserIsStudentTestTwo() {
		User studentFive = new User("Shane", "Watson", "B00222222", "shane@dal.ca");
		List<Course> courseList = courseDaoImplMock.getCoursesWhereUserIsStudent(studentFive);
		assertEquals(0, courseList.size());
	}

	@Test
	public void getCoursesWhereUserIsInstrcutorTestOne() {
		User instructorThree = new User("Michel", "Bevan", "B00675984", "bevan@dal.com");
		List<Course> instructorCourses = courseDaoImplMock.getCoursesWhereUserIsInstrcutor(instructorThree);
		assertNotEquals(0, instructorCourses.size());
	}

	@Test
	public void getCoursesWhereUserIsInstrcutorTestTwo() {
		User studentFive = new User("Shane", "Warne", "B00654194", "shane@dal.ca");
		List<Course> instructorCourses = courseDaoImplMock.getCoursesWhereUserIsInstrcutor(studentFive);
		assertEquals(0, instructorCourses.size());
	}

	@Test
	public void getCoursesWhereUserIsTATestOne() {
		User instructorTwo = new User("Don", "Bradman", "B00741399", "don@dal.com");
		List<Course> instructorCourses = courseDaoImplMock.getCoursesWhereUserIsTA(instructorTwo);
		assertEquals(0, instructorCourses.size());
	}

	@Test
	public void getCoursesWhereUserIsTATestTwo() {
		User taTwo = new User("Ricky", "Ponting", "B00951789", "ricky@dal.ca");
		List<Course> instructorCourses = courseDaoImplMock.getCoursesWhereUserIsTA(taTwo);
		assertNotEquals(0, instructorCourses.size());
	}

	@Test
	public void getCourseByIdTestOne() {
		Course course = ((CourseDaoImplMock) courseDaoImplMock).getCourseById(1);
		assertNotNull(course);
	}

	@Test
	public void getCourseByIdTestTwo() {

		Course course = ((CourseDaoImplMock) courseDaoImplMock).getCourseById(6);
		assertNull(course);
	}

	@Test
	public void isCourseExistsTest() {

		assertTrue(courseDaoImplMock.isCourseExists(new Course()));
	}

	@Test
	public void getInstructorForCourseTestOne() {

		User instructor = courseDaoImplMock.getInstructorForCourse(2);
		assertNotNull(instructor);
	}

	@Test
	public void getInstructorForCourseTestTwo() {

		User instructor = courseDaoImplMock.getInstructorForCourse(10);
		assertNull(instructor);
	}

	@Test
	public void filterEligibleUsersForCourseTestOne() {

		User instructorOne = new User("Justin", "Langer", "B00123456", "justin@dal.ca");
		User studentFive = new User("Shane", "Warne", "B00654194", "shane@dal.ca");
		List<User> userList = Arrays.asList(instructorOne, studentFive);
		List<User> eligiList = courseDaoImplMock.filterEligibleUsersForCourse(userList, 1);
		assertEquals(0, eligiList.size());
	}

	@Test
	public void filterEligibleUsersForCourseTestTwo() {

		User instructorOne = new User("Justin", "Langer", "B00123456", "justin@dal.ca");
		User studentThree = new User("Brett", "Lee", "B00852693", "ricky@dal.ca");
		List<User> userList = Arrays.asList(instructorOne, studentThree);
		List<User> eligiList = courseDaoImplMock.filterEligibleUsersForCourse(userList, 1);
		assertEquals(1, eligiList.size());
	}

	@Test
	public void filterEligibleUsersForCourseTestThree() {

		User studentTwo = new User("Glenn", "Maxwell", "B00753159", "glenn@dal.ca");
		User studentThree = new User("Brett", "Lee", "B00852693", "ricky@dal.ca");
		List<User> userList = Arrays.asList(studentTwo, studentThree);
		List<User> eligiList = courseDaoImplMock.filterEligibleUsersForCourse(userList, 1);
		assertEquals(2, eligiList.size());
	}
}
