package com.group18.asdc.entities;

import java.util.ArrayList;
import java.util.List;

public class Course {

	private int courseId;
	private String CourseName;
	private User instructorName = new User();
	private List<User> taList = new ArrayList<User>();
	private List<User> studentList = new ArrayList<User>();
	
	public Course() {
		super();
	}

	public Course(int courseId, String courseName, User instructorName, List<User> taList, List<User> studentList) {
		super();
		this.courseId = courseId;
		CourseName = courseName;
		this.instructorName = instructorName;
		this.taList = taList;
		this.studentList = studentList;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return CourseName;
	}

	public void setCourseName(String courseName) {
		CourseName = courseName;
	}

	public User getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(User instructorName) {
		this.instructorName = instructorName;
	}

	public List<User> getTaName() {
		return taList;
	}

	public void setTaName(List<User> taList) {
		this.taList = taList;
	}

	public List<User> getTaList() {
		return taList;
	}

	public void setTaList(List<User> taList) {
		this.taList = taList;
	}

	public List<User> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<User> studentList) {
		this.studentList = studentList;
	}

}
