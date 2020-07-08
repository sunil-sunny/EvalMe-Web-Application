package com.group18.asdc.service;

import java.util.ArrayList;
import java.util.List;

import com.group18.asdc.CourseConfig;
import com.group18.asdc.ProfileManagementConfig;
import com.group18.asdc.dao.CourseRolesDao;
import com.group18.asdc.entities.User;

public class CourseRolesServiceImpl implements CourseRolesService {

	@Override
	public boolean allocateTa(int courseId, User user) {
		CourseDetailsService theCourseDetailsService = CourseConfig.getSingletonInstance().getTheCourseDetailsService();
		List<User> taAsList = new ArrayList<User>();
		List<User> eligibleUser = null;
		CourseRolesDao courseRolesDao = CourseConfig.getSingletonInstance().getTheCourseRolesDao();
		if (null != user) {
			taAsList.add(user);
			eligibleUser = theCourseDetailsService.filterEligibleUsersForCourse(taAsList, courseId);
		}
		if (null != eligibleUser && 0 != eligibleUser.size()) {
			return courseRolesDao.allocateTa(courseId, user);
		}
		return false;
	}

	@Override
	public boolean enrollStuentsIntoCourse(List<User> studentList, int courseId) {
		CourseRolesDao courseRolesDao = CourseConfig.getSingletonInstance().getTheCourseRolesDao();
		CourseDetailsService theCourseDetailsService = CourseConfig.getSingletonInstance().getTheCourseDetailsService();
		RegisterService theRegisterService = ProfileManagementConfig.getSingletonInstance().getTheRegisterservice();
		boolean isStudentsRegistered = theRegisterService.registerStudents(studentList);
		if (isStudentsRegistered) {
			List<User> eligibleStudents = theCourseDetailsService.filterEligibleUsersForCourse(studentList, courseId);
			if (0 == eligibleStudents.size()) {
				return false;
			} else {
				return courseRolesDao.enrollStudentsIntoCourse(eligibleStudents, courseId);
			}
		} else {
			return false;
		}
	}
}