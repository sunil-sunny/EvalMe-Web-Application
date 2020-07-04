package com.group18.asdc.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;
import com.group18.asdc.service.CourseDetailsService;

@SpringBootTest
public class CourseServiceImplTest {

	@Test
	public void getAllCoursesTestOne() {
		CourseDetailsService CourseServiceImplMock = new CourseServiceImplMock();
		List<Course> courseList = CourseServiceImplMock.getAllCourses();
		assertNotEquals(0, courseList.size());
	}
	
	@Test
	public void getCoursesWhereUserIsStudentTestOne() {
		CourseDetailsService CourseServiceImplMock = new CourseServiceImplMock();
		User studentFive = new User("Shane", "Warne", "B00654194", "shane@dal.ca");
		List<Course> courseList = CourseServiceImplMock.getCoursesWhereUserIsStudent(studentFive);
		assertNotEquals(0, courseList.size());
	}

	@Test
	public void getCoursesWhereUserIsStudentTestTwo() {
		CourseDetailsService CourseServiceImplMock = new CourseServiceImplMock();
		User studentFive = new User("Shane", "Watson", "B00222222", "shane@dal.ca");
		List<Course> courseList = CourseServiceImplMock.getCoursesWhereUserIsStudent(studentFive);
		assertEquals(0, courseList.size());
	}

	@Test
	public void getCoursesWhereUserIsInstrcutorTestOne() {
		CourseDetailsService CourseServiceImplMock = new CourseServiceImplMock();
		User instructorThree = new User("Michel", "Bevan", "B00675984", "bevan@dal.com");
		List<Course> instructorCourses = CourseServiceImplMock.getCoursesWhereUserIsInstrcutor(instructorThree);
		assertNotEquals(0, instructorCourses.size());
	}

	@Test
	public void getCoursesWhereUserIsInstrcutorTestTwo() {
		CourseDetailsService CourseServiceImplMock = new CourseServiceImplMock();
		User studentFive = new User("Shane", "Warne", "B00654194", "shane@dal.ca");
		List<Course> instructorCourses = CourseServiceImplMock.getCoursesWhereUserIsInstrcutor(studentFive);
		assertEquals(0, instructorCourses.size());
	}

	@Test
	public void getCoursesWhereUserIsTATestOne() {
		CourseDetailsService CourseServiceImplMock = new CourseServiceImplMock();
		User instructorTwo = new User("Don", "Bradman", "B00741399", "don@dal.com");
		List<Course> instructorCourses = CourseServiceImplMock.getCoursesWhereUserIsTA(instructorTwo);
		assertEquals(0, instructorCourses.size());
	}

	@Test
	public void getCoursesWhereUserIsTATestTwo() {
		CourseDetailsService CourseServiceImplMock = new CourseServiceImplMock();
		User taTwo = new User("Ricky", "Ponting", "B00951789", "ricky@dal.ca");
		List<Course> instructorCourses = CourseServiceImplMock.getCoursesWhereUserIsTA(taTwo);
		assertNotEquals(0, instructorCourses.size());
	}

	@Test
	public void filterEligibleUsersForCourseTest() {
		CourseDetailsService theCourseDetailsService = new CourseServiceImplMock();
		User studentTwo = new User("Glenn", "Maxwell", "B00753159", "glenn@dal.ca");
		User studentThree = new User("Brett", "Lee", "B00852693", "ricky@dal.ca");
		List<User> userList = Arrays.asList(studentTwo, studentThree);
		List<User> eligiList = theCourseDetailsService.filterEligibleUsersForCourse(userList, 1);
		assertEquals(2, eligiList.size());
	}
}