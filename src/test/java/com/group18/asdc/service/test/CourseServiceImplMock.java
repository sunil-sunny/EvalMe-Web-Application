package com.group18.asdc.service.test;

import java.util.List;

import com.group18.asdc.TestConfig;
import com.group18.asdc.dao.CourseDetailsDao;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;
import com.group18.asdc.service.CourseDetailsService;

public class CourseServiceImplMock implements CourseDetailsService {

	private final static CourseDetailsDao theCourseDaoImplMock = TestConfig.getTestSingletonIntance()
			.getDaoTestAbstractFactory().getCourseDaoTest();

	@Override
	public List<Course> getAllCourses() {

		return theCourseDaoImplMock.getAllCourses();
	}

	@Override
	public List<Course> getCoursesWhereUserIsStudent(User user) {
		
		return theCourseDaoImplMock.getCoursesWhereUserIsStudent(user);
	}

	@Override
	public List<Course> getCoursesWhereUserIsInstrcutor(User user) {
		
		return theCourseDaoImplMock.getCoursesWhereUserIsInstrcutor(user);
	}

	@Override
	public List<Course> getCoursesWhereUserIsTA(User user) {
		
		return theCourseDaoImplMock.getCoursesWhereUserIsTA(user);
	}

	@Override
	public boolean isCourseExists(Course course) {
		
		return theCourseDaoImplMock.isCourseExists(course);
	}

	@Override
	public List<User> filterEligibleUsersForCourse(List<User> studentList, int courseId) {
		
		return theCourseDaoImplMock.filterEligibleUsersForCourse(studentList, courseId);
	}

	@Override
	public Course getCourseById(int courseId) {
		Course course = new Course();
		course.setCourseId(courseId);
		course.getCourseId();
		return course;
	}
}