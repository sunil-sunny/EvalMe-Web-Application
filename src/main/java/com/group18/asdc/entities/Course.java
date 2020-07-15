package com.group18.asdc.entities;

import java.util.ArrayList;
import java.util.List;

public class Course implements ICourse {

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

	@Override
	public int getCourseId() {
		return courseId;
	}

	@Override
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	@Override
	public String getCourseName() {
		return CourseName;
	}

	@Override
	public void setCourseName(String courseName) {
		CourseName = courseName;
	}

	@Override
	public User getInstructorName() {
		return instructorName;
	}

	@Override
	public void setInstructorName(User instructorName) {
		this.instructorName = instructorName;
	}

	@Override
	public List<User> getTaName() {
		return taList;
	}

	@Override
	public void setTaName(List<User> taList) {
		this.taList = taList;
	}

	@Override
	public List<User> getTaList() {
		return taList;
	}

	@Override
	public void setTaList(List<User> taList) {
		this.taList = taList;
	}

	@Override
	public List<User> getStudentList() {
		return studentList;
	}

	@Override
	public void setStudentList(List<User> studentList) {
		this.studentList = studentList;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", CourseName=" + CourseName + ", instructorName=" + instructorName
				+ ", taList=" + taList + ", studentList=" + studentList + "]";
	}
}