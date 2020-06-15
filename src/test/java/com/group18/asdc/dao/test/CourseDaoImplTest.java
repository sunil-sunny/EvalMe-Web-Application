package com.group18.asdc.dao.test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;

@SpringBootTest
public class CourseDaoImplTest {

	/*
	 * simple test to get all courses
	 */
	@Test
	public void getAllCoursesTestOne() {
		CourseDaoImplMock courseDaoImplMock = new CourseDaoImplMock();
		List<Course> courseList = courseDaoImplMock.getAllCourses();
		assertNotEquals(0, courseList.size());

	}

	/*
	 * Below test is to pass the valid student and get the courses
	 */

	@Test
	public void getCoursesWhereUserIsStudentTestOne() {
		CourseDaoImplMock courseDaoImplMock = new CourseDaoImplMock();
		User studentFive = new User("Shane", "Warne", "B00654194", "shane@dal.ca");
		List<Course> courseList = courseDaoImplMock.getCoursesWhereUserIsStudent(studentFive);
		assertNotEquals(0, courseList.size());

	}

	/*
	 * Below test is to pass the invalid student and get the courses count as zero
	 */

	@Test
	public void getCoursesWhereUserIsStudentTestTwo() {
		CourseDaoImplMock courseDaoImplMock = new CourseDaoImplMock();
		User studentFive = new User("Shane", "Watson", "B00222222", "shane@dal.ca");
		List<Course> courseList = courseDaoImplMock.getCoursesWhereUserIsStudent(studentFive);
		assertEquals(0, courseList.size());

	}

	/*
	 * passing user who is instructor and the result should be more than zero
	 */
	@Test
	public void getCoursesWhereUserIsInstrcutorTestOne() {

		CourseDaoImplMock courseDaoImplMock = new CourseDaoImplMock();
		User instructorThree = new User("Michel", "Bevan", "B00675984", "bevan@dal.com");
		List<Course> instructorCourses = courseDaoImplMock.getCoursesWhereUserIsInstrcutor(instructorThree);
		assertNotEquals(0, instructorCourses.size());

	}
	/*
	 * below test passes non instructor and we should get zero as courses length
	 */

	@Test
	public void getCoursesWhereUserIsInstrcutorTestTwo() {

		CourseDaoImplMock courseDaoImplMock = new CourseDaoImplMock();
		User studentFive = new User("Shane", "Warne", "B00654194", "shane@dal.ca");
		List<Course> instructorCourses = courseDaoImplMock.getCoursesWhereUserIsInstrcutor(studentFive);
		assertEquals(0, instructorCourses.size());

	}

	/*
	 * below test passes a non TA user and should get ideally it should return zero
	 */
	@Test
	public void getCoursesWhereUserIsTATestOne() {

		CourseDaoImplMock courseDaoImplMock = new CourseDaoImplMock();
		User instructorTwo = new User("Don", "Bradman", "B00741399", "don@dal.com");
		List<Course> instructorCourses = courseDaoImplMock.getCoursesWhereUserIsTA(instructorTwo);
		assertEquals(0, instructorCourses.size());

	}

	/*
	 * Below test passes a valid TA user and ideally course count should be greater
	 * than zero
	 */

	@Test
	public void getCoursesWhereUserIsTATestTwo() {

		CourseDaoImplMock courseDaoImplMock = new CourseDaoImplMock();
		User taTwo = new User("Ricky", "Ponting", "B00951789", "ricky@dal.ca");
		List<Course> instructorCourses = courseDaoImplMock.getCoursesWhereUserIsTA(taTwo);
		assertNotEquals(0, instructorCourses.size());

	}

	/*
	 * Below test passes valid course id and idelly the course wont be null
	 */

	@Test
	public void getCourseByIdTestOne() {

		CourseDaoImplMock courseDaoImplMock = new CourseDaoImplMock();
		Course course = courseDaoImplMock.getCourseById(1);
		assertNotNull(course);

	}
	/*
	 * Below test passes invalid course and ideally we should get null object
	 */

	@Test
	public void getCourseByIdTestTwo() {

		CourseDaoImplMock courseDaoImplMock = new CourseDaoImplMock();
		Course course = courseDaoImplMock.getCourseById(6);
		assertNull(course);

	}

	/*
	 * Below test send set of students and ideally the result will be true if they
	 * are added.
	 */
	@Test
	public void enrollStudentsIntoCourseTestOne() {

		CourseDaoImplMock theCourseDaoImplMock = new CourseDaoImplMock();
		User user = new User("Rahul", "Dravid", "B09896157", "dravid@dal.ca");
		User userOne = new User("Rahul", "Chahar", "B09898157", "chahar@dal.ca");
		List<User> studentsList = new ArrayList<User>();
		studentsList.add(user);
		studentsList.add(userOne);
		boolean isEnrolled = theCourseDaoImplMock.enrollStudentsIntoCourse(studentsList, 5);
		assertTrue(isEnrolled);
	}

	/*
	 * Below test send set of empty students and ideally the result will be false if
	 * they are added.
	 */
	@Test
	public void enrollStudentsIntoCourseTestTwo() {

		CourseDaoImplMock theCourseDaoImplMock = new CourseDaoImplMock();

		List<User> studentsList = new ArrayList<User>();

		boolean isEnrolled = theCourseDaoImplMock.enrollStudentsIntoCourse(studentsList, 9);
		assertFalse(isEnrolled);
	}

	/*
	 * Below test passes the invalid users and get false from method
	 */
	@Test
	public void allocateTaTestOne() {

		CourseDaoImplMock theCourseDaoImplMock = new CourseDaoImplMock();
		User studentsList = null;
		boolean isEnrolled = theCourseDaoImplMock.allocateTa(2, studentsList);
		assertFalse(isEnrolled);

	}
	
	/*
	 * Below test passes the valid users and get true from method
	 */
	@Test
	public void allocateTaTestTwo() {

		CourseDaoImplMock theCourseDaoImplMock = new CourseDaoImplMock();
		User studentsList = new User("Rahul", "Chahar", "B09898157", "chahar@dal.ca");;
		boolean isEnrolled = theCourseDaoImplMock.allocateTa(2, studentsList);
		assertTrue(isEnrolled);

	}

}
