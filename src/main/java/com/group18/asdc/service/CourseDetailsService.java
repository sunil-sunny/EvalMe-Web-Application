package com.group18.asdc.service;

import java.util.List;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;

public interface CourseDetailsService {

	public List<Course> getAllCourses();

	public List<Course> getCoursesWhereUserIsStudent(User user);

	public List<Course> getCoursesWhereUserIsInstrcutor(User user);

	public List<Course> getCoursesWhereUserIsTA(User user);

	public boolean isCourseExists(Course course);

	public List<User> filterEligibleUsersForCourse(List<User> studentList, int courseId);
}