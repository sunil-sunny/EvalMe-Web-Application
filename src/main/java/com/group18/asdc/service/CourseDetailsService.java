package com.group18.asdc.service;

import java.util.List;

import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;

public interface CourseDetailsService {
	
	public List<Course> getAllCourses();
	public boolean allocateTa(int courseId,User user);
	public boolean enrollStuentsIntoCourse(List<User> studentList,int courseId);
	public void registerStudents(List<User> studentList);
	public List<Course> getCoursesWhereUserIsStudent(User user);
	public List<Course> getCoursesWhereUserIsInstrcutor(User user);
	public List<Course> getCoursesWhereUserIsTA(User user);
	

}
