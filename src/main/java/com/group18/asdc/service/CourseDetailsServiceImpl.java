package com.group18.asdc.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.dao.CourseDetailsDao;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;

@Service
public class CourseDetailsServiceImpl implements CourseDetailsService {

	private static final CourseDetailsDao courseDetailsDao = SystemConfig.getSingletonInstance().getDaoAbstractFactory()
			.getCourseDetailsDao();
	private static final Logger log=Logger.getLogger(CourseDetailsServiceImpl.class.getName());

	@Override
	public List<Course> getAllCourses() {
		return courseDetailsDao.getAllCourses();
	}

	@Override
	public List<Course> getCoursesWhereUserIsStudent(User user) {
		return courseDetailsDao.getCoursesWhereUserIsStudent(user);
	}

	@Override
	public List<Course> getCoursesWhereUserIsInstrcutor(User user) {
		return courseDetailsDao.getCoursesWhereUserIsInstrcutor(user);
	}

	@Override
	public List<Course> getCoursesWhereUserIsTA(User user) {
		return courseDetailsDao.getCoursesWhereUserIsTA(user);
	}

	@Override
	public boolean isCourseExists(Course course) {
		if (null == course) {
			log.log(Level.SEVERE, "Course object received as null while checking course existance");
			return Boolean.FALSE;
		} else {
			return courseDetailsDao.isCourseExists(course);
		}
	}

	@Override
	public List<User> filterEligibleUsersForCourse(List<User> studentList, int courseId) {
		return courseDetailsDao.filterEligibleUsersForCourse(studentList, courseId);
	}

	@Override
	public Course getCourseById(int courseId) {
		return courseDetailsDao.getCourseById(courseId);
	}
}
