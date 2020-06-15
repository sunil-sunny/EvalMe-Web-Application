package com.group18.asdc.dao;

import com.group18.asdc.entities.CourseAdmin;

public interface AdminDao {
	
	public boolean checkCourseId(int courseid);
	public boolean checkCourseName(String coursename);
	public String checkInstructorId(String instructorid);
	public boolean addCourse(int courseid, String coursename);
	public boolean addCourse(CourseAdmin courseadmin);
	public boolean deleteCourse(int courseid);
	public boolean addInstructor(int courseid, String bannerid);

}
