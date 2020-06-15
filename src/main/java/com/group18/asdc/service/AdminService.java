package com.group18.asdc.service;

import com.group18.asdc.entities.CourseAdmin;

public interface AdminService {
	
	public String createCourse(CourseAdmin courseadmin);
	public String deleteCourse(int courseId);
}
