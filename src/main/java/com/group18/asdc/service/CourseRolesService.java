package com.group18.asdc.service;

import java.util.List;
import com.group18.asdc.entities.User;

public interface CourseRolesService {

	public boolean allocateTa(int courseId, User user);

	public boolean enrollStuentsIntoCourse(List<User> studentList, int courseId);
}
