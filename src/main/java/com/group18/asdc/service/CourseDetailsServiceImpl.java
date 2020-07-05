package com.group18.asdc.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.group18.asdc.SystemConfig;
import com.group18.asdc.dao.CourseDetailsDao;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;

@Service
public class CourseDetailsServiceImpl implements CourseDetailsService {

	@Override
	public List<Course> getAllCourses() {
		CourseDetailsDao courseDetailsDao = SystemConfig.getSingletonInstance().getTheCourseDetailsDao();
		return courseDetailsDao.getAllCourses();
	}

	@Override
	public List<Course> getCoursesWhereUserIsStudent(User user) {
		CourseDetailsDao courseDetailsDao = SystemConfig.getSingletonInstance().getTheCourseDetailsDao();
		return courseDetailsDao.getCoursesWhereUserIsStudent(user);
	}

	@Override
	public List<Course> getCoursesWhereUserIsInstrcutor(User user) {
		CourseDetailsDao courseDetailsDao = SystemConfig.getSingletonInstance().getTheCourseDetailsDao();
		return courseDetailsDao.getCoursesWhereUserIsInstrcutor(user);
	}

	@Override
	public List<Course> getCoursesWhereUserIsTA(User user) {
		CourseDetailsDao courseDetailsDao = SystemConfig.getSingletonInstance().getTheCourseDetailsDao();
		return courseDetailsDao.getCoursesWhereUserIsTA(user);
	}

	@Override
	public boolean isCourseExists(Course course) {
		if (null == course) {
			return false;
		} else {
			CourseDetailsDao courseDetailsDao = SystemConfig.getSingletonInstance().getTheCourseDetailsDao();
			return courseDetailsDao.isCourseExists(course);
		}
	}

	@Override
	public List<User> filterEligibleUsersForCourse(List<User> studentList, int courseId) {
		CourseDetailsDao courseDetailsDao = SystemConfig.getSingletonInstance().getTheCourseDetailsDao();
		return courseDetailsDao.filterEligibleUsersForCourse(studentList, courseId);
	}

	@Override
	public Course getCourseById(int courseId) {
		CourseDetailsDao courseDetailsDao = SystemConfig.getSingletonInstance().getTheCourseDetailsDao();
		return courseDetailsDao.getCourseById(courseId);
	}
}
