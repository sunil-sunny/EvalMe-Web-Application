package com.group18.asdc.dao;

import java.util.List;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;

public interface CourseDetailsDao {

	public List<Course> getAllCourses();

	public List<Course> getCoursesWhereUserIsStudent(User user);

	public List<Course> getCoursesWhereUserIsInstrcutor(User user);

	public List<Course> getCoursesWhereUserIsTA(User user);
}
