package com.group18.asdc.entities;

import java.util.List;

public interface ICourse {

	public int getCourseId();

	public void setCourseId(int courseId);

	public String getCourseName();

	public void setCourseName(String courseName);

	public User getInstructorName();

	public void setInstructorName(User instructorName);

	public List<User> getTaName();

	public void setTaName(List<User> taList);

	public List<User> getTaList();

	public void setTaList(List<User> taList);

	public List<User> getStudentList();

	public void setStudentList(List<User> studentList);

}
