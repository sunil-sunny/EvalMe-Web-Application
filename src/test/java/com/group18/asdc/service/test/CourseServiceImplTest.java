package com.group18.asdc.service.test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;

@SpringBootTest
public class CourseServiceImplTest {

	/*
	 * simple test to get all courses
	 */
	@Test
	public void getAllCoursesTestOne() {
		CourseServiceImplMock CourseServiceImplMock = new CourseServiceImplMock();
		List<Course> courseList = CourseServiceImplMock.getAllCourses();
		assertNotEquals(0, courseList.size());

	}

	/*
	 * Below test is to pass the valid student and get the courses
	 */

	@Test
	public void getCoursesWhereUserIsStudentTestOne() {
		CourseServiceImplMock CourseServiceImplMock = new CourseServiceImplMock();
		User studentFive = new User("Shane", "Warne", "B00654194", "shane@dal.ca");
		List<Course> courseList = CourseServiceImplMock.getCoursesWhereUserIsStudent(studentFive);
		assertNotEquals(0, courseList.size());

	}

	/*
	 * Below test is to pass the invalid student and get the courses count as zero
	 */

	@Test
	public void getCoursesWhereUserIsStudentTestTwo() {
		CourseServiceImplMock CourseServiceImplMock = new CourseServiceImplMock();
		User studentFive = new User("Shane", "Watson", "B00222222", "shane@dal.ca");
		List<Course> courseList = CourseServiceImplMock.getCoursesWhereUserIsStudent(studentFive);
		assertEquals(0, courseList.size());

	}

	/*
	 * passing user who is instructor and the result should be more than zero
	 */
	@Test
	public void getCoursesWhereUserIsInstrcutorTestOne() {

		CourseServiceImplMock CourseServiceImplMock = new CourseServiceImplMock();
		User instructorThree = new User("Michel", "Bevan", "B00675984", "bevan@dal.com");
		List<Course> instructorCourses = CourseServiceImplMock.getCoursesWhereUserIsInstrcutor(instructorThree);
		assertNotEquals(0, instructorCourses.size());

	}
	/*
	 * below test passes non instructor and we should get zero as courses length
	 */

	@Test
	public void getCoursesWhereUserIsInstrcutorTestTwo() {

		CourseServiceImplMock CourseServiceImplMock = new CourseServiceImplMock();
		User studentFive = new User("Shane", "Warne", "B00654194", "shane@dal.ca");
		List<Course> instructorCourses = CourseServiceImplMock.getCoursesWhereUserIsInstrcutor(studentFive);
		assertEquals(0, instructorCourses.size());

	}

	/*
	 * below test passes a non TA user and should get ideally it should return zero
	 */
	@Test
	public void getCoursesWhereUserIsTATestOne() {

		CourseServiceImplMock CourseServiceImplMock = new CourseServiceImplMock();
		User instructorTwo = new User("Don", "Bradman", "B00741399", "don@dal.com");
		List<Course> instructorCourses = CourseServiceImplMock.getCoursesWhereUserIsTA(instructorTwo);
		assertEquals(0, instructorCourses.size());

	}

	/*
	 * Below test passes a valid TA user and ideally course count should be greater
	 * than zero
	 */

	@Test
	public void getCoursesWhereUserIsTATestTwo() {

		CourseServiceImplMock CourseServiceImplMock = new CourseServiceImplMock();
		User taTwo = new User("Ricky", "Ponting", "B00951789", "ricky@dal.ca");
		List<Course> instructorCourses = CourseServiceImplMock.getCoursesWhereUserIsTA(taTwo);
		assertNotEquals(0, instructorCourses.size());

	}

	
	/*
	 * Below test send set of students and ideally the result will be true if they
	 * are added.
	 */
	@Test
	public void enrollStudentsIntoCourseTestOne() {

		CourseServiceImplMock theCourseServiceImplMock = new CourseServiceImplMock();
		User user = new User("Rahul", "Dravid", "B09896157", "dravid@dal.ca");
		User userOne = new User("Rahul", "Chahar", "B09898157", "chahar@dal.ca");
		List<User> studentsList = new ArrayList<User>();
		studentsList.add(user);
		studentsList.add(userOne);
		boolean isEnrolled = theCourseServiceImplMock.enrollStuentsIntoCourse(studentsList, 5);
		assertTrue(isEnrolled);
	}

	/*
	 * Below test send set of empty students and ideally the result will be false if
	 * they are added.
	 */
	@Test
	public void enrollStudentsIntoCourseTestTwo() {

		CourseServiceImplMock theCourseServiceImplMock = new CourseServiceImplMock();

		List<User> studentsList = new ArrayList<User>();

		boolean isEnrolled = theCourseServiceImplMock.enrollStuentsIntoCourse(studentsList, 9);
		assertFalse(isEnrolled);
	}

	/*
	 * Below test passes the invalid users and get false from method
	 */
	@Test
	public void allocateTaTestOne() {

		CourseServiceImplMock theCourseServiceImplMock = new CourseServiceImplMock();
		User studentsList = null;
		boolean isEnrolled = theCourseServiceImplMock.allocateTa(2, studentsList);
		assertFalse(isEnrolled);

	}
	
	/*
	 * Below test passes the valid users and get true from method
	 */
	@Test
	public void allocateTaTestTwo() {

		CourseServiceImplMock theCourseServiceImplMock = new CourseServiceImplMock();
		User studentsList = new User("Rahul", "Chahar", "B09898157", "chahar@dal.ca");;
		boolean isEnrolled = theCourseServiceImplMock.allocateTa(2, studentsList);
		assertTrue(isEnrolled);

	}

}
