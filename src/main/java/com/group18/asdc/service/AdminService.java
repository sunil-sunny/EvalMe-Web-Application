package com.group18.asdc.service;

import com.group18.asdc.entities.Course;

public interface AdminService {

	public boolean isCourseIdValid(Course course);

	public boolean iscreateCourseParametersValid(Course course);

	public boolean createCourse(Course course);

	public boolean deleteCourse(Course course);
}
