package com.group18.asdc.service.test;

import java.util.List;

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

}
