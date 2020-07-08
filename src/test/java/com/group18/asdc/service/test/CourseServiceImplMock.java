package com.group18.asdc.service.test;

import java.util.List;
import com.group18.asdc.dao.CourseDetailsDao;
import com.group18.asdc.dao.test.CourseDaoImplMock;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;
import com.group18.asdc.service.CourseDetailsService;

public class CourseServiceImplMock implements CourseDetailsService {

	@Override
	public List<Course> getAllCourses() {
		CourseDaoImplMock theCourseDaoImplMock=new CourseDaoImplMock();
		return theCourseDaoImplMock.getAllCourses();
	}
	
	@Override
	public List<Course> getCoursesWhereUserIsStudent(User user) {
		CourseDaoImplMock theCourseDaoImplMock=new CourseDaoImplMock();
		return theCourseDaoImplMock.getCoursesWhereUserIsStudent(user);
	}

	@Override
	public List<Course> getCoursesWhereUserIsInstrcutor(User user) {
		CourseDaoImplMock theCourseDaoImplMock=new CourseDaoImplMock();
		return theCourseDaoImplMock.getCoursesWhereUserIsInstrcutor(user);
	}

	@Override
	public List<Course> getCoursesWhereUserIsTA(User user) {
		CourseDaoImplMock theCourseDaoImplMock=new CourseDaoImplMock();
		return theCourseDaoImplMock.getCoursesWhereUserIsTA(user);
	}

	@Override
	public boolean isCourseExists(Course course) {
		final CourseDetailsDao theCourseDetailsDao=new CourseDaoImplMock();
		return theCourseDetailsDao.isCourseExists(course);
	}

	@Override
	public List<User> filterEligibleUsersForCourse(List<User> studentList, int courseId) {
		final CourseDetailsDao theCourseDetailsDao=new CourseDaoImplMock();
		return theCourseDetailsDao.filterEligibleUsersForCourse(studentList, courseId);
	}

	@Override
	public Course getCourseById(int courseId) {
		Course course=new Course();
		course.setCourseId(courseId);
		course.getCourseId();
		return course;
	}
}