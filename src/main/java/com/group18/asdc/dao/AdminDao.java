package com.group18.asdc.dao;

import com.group18.asdc.entities.Course;

public interface AdminDao {

	public boolean addCourse(Course course);

	public boolean deleteCourse(Course course);
}
