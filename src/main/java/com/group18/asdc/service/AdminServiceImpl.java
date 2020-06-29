package com.group18.asdc.service;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;
import com.group18.asdc.SystemConfig;
import com.group18.asdc.dao.AdminDao;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;

@Service
public class AdminServiceImpl implements AdminService {

	private AdminDao admindao;
	private Logger log = Logger.getLogger(AdminServiceImpl.class.getName());

	public AdminServiceImpl() {
		super();
	}

	@Override
	public boolean isCourseIdValid(Course course) {
		log.info("Acceesing admin service impl");
		admindao = SystemConfig.getSingletonInstance().getTheAdminDao();
		int courseId = course.getCourseId();
		if (0 >= courseId || 4 != String.valueOf(courseId).length()) {
			return false;
		}
		return true;
	}

	@Override
	public boolean iscreateCourseParametersValid(Course course) {

		UserService theUserService=SystemConfig.getSingletonInstance().getTheUserService();
		log.info("Acceesing admin service impl");
		admindao = SystemConfig.getSingletonInstance().getTheAdminDao();
		if (!isCourseIdValid(course)) {
			return false;
		}
		if (admindao.isCourseExists(course)) {
			return false;
		}
		String instructorId = course.getInstructorName().getBannerId();
		User instructor=theUserService.getUserById(instructorId);
		if(instructor==null) {
			return false;
		}
		if (instructorId.length() != 9 || !instructorId.matches("B00(.*)")) {
			return false;
		}
		if (true == admindao.isUserInstructor(course)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean createCourse(Course course) {

		admindao = SystemConfig.getSingletonInstance().getTheAdminDao();
		if (iscreateCourseParametersValid(course)) {
			return admindao.addCourse(course);
		}
		return false;
	}

	@Override
	public boolean deleteCourse(Course course) {

		admindao = SystemConfig.getSingletonInstance().getTheAdminDao();
		if (isCourseIdValid(course)) {
			return admindao.deleteCourse(course);
		}
		return false;
	}
}
