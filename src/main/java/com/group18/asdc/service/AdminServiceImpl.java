package com.group18.asdc.service;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.group18.asdc.CourseConfig;
import com.group18.asdc.ProfileManagementConfig;
import com.group18.asdc.dao.AdminDao;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;
import com.group18.asdc.util.ConstantStringUtil;

@Service
public class AdminServiceImpl implements AdminService {

	private AdminDao admindao;
	private Logger log = Logger.getLogger(AdminServiceImpl.class.getName());

	public AdminServiceImpl() {
		super();
	}

	@Override
	public boolean isCourseIdValid(Course course) {
		int courseId = course.getCourseId();
		if (0 >= courseId || 4 != String.valueOf(courseId).length()) {
			return false;
		}
		return true;
	}

	@Override
	public boolean iscreateCourseParametersValid(Course course) {
		UserService theUserService = ProfileManagementConfig.getSingletonInstance().getTheUserService();
		CourseDetailsService theCourseDetailsService = CourseConfig.getSingletonInstance().getTheCourseDetailsService();
		log.info("Acceesing Admin Service Impl");
		admindao = ProfileManagementConfig.getSingletonInstance().getTheAdminDao();
		if (isCourseIdValid(course)) {
			if (theCourseDetailsService.isCourseExists(course)) {
				return false;
			} else {
				String instructorId = course.getInstructorName().getBannerId();
				User instructor = theUserService.getUserById(instructorId);
				if (null == instructor) {
					return false;
				} else {
					if (9 == instructorId.length()
							|| instructorId.matches(ConstantStringUtil.BANNER_ID_CHECK.toString())) {
						if (theUserService.isUserInstructor(course)) {
							return false;
						}
					} else {
						return false;
					}
				}
			}
		} else {
			return false;
		}
		return true;
	}

	@Override
	public boolean createCourse(Course course) {
		admindao = ProfileManagementConfig.getSingletonInstance().getTheAdminDao();
		log.info("Acceesing Admin Service Impl");
		if (iscreateCourseParametersValid(course)) {
			return admindao.addCourse(course);
		}
		return false;
	}

	@Override
	public boolean deleteCourse(Course course) {
		admindao = ProfileManagementConfig.getSingletonInstance().getTheAdminDao();
		log.info("Accessing Admin Service Impl");
		if (isCourseIdValid(course)) {
			return admindao.deleteCourse(course);
		}
		return false;
	}
}