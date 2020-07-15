package com.group18.asdc.service;

import org.springframework.stereotype.Service;
import com.group18.asdc.SystemConfig;
import com.group18.asdc.dao.AdminDao;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;
import com.group18.asdc.util.ConstantStringUtil;

@Service
public class AdminServiceImpl implements AdminService {

	private static final AdminDao adminDao = SystemConfig.getSingletonInstance().getDaoAbstractFactory().getAdminDao();

	private static final UserService theUserService = SystemConfig.getSingletonInstance().getServiceAbstractFactory()
			.getUserService();
	private static final CourseDetailsService theCourseDetailsService = SystemConfig.getSingletonInstance()
			.getServiceAbstractFactory().getCourseDetailsService();

	@Override
	public boolean isCourseIdValid(Course course) {
		int courseId = course.getCourseId();
		if (0 >= courseId || 4 != String.valueOf(courseId).length()) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	@Override
	public boolean iscreateCourseParametersValid(Course course) {

		if (isCourseIdValid(course)) {
			if (theCourseDetailsService.isCourseExists(course)) {
				return Boolean.FALSE;
			} else {
				String instructorId = course.getInstructorName().getBannerId();
				User instructor = theUserService.getUserById(instructorId);
				if (null == instructor) {
					return Boolean.FALSE;
				} else {
					if (9 == instructorId.length()
							|| instructorId.matches(ConstantStringUtil.BANNER_ID_CHECK.toString())) {
						if (theUserService.isUserInstructor(course)) {
							return Boolean.FALSE;
						}
					} else {
						return Boolean.FALSE;
					}
				}
			}
		} else {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	@Override
	public boolean createCourse(Course course) {
		if (iscreateCourseParametersValid(course)) {
			return adminDao.addCourse(course);
		}
		return Boolean.FALSE;
	}

	@Override
	public boolean deleteCourse(Course course) {

		if (isCourseIdValid(course)) {
			return adminDao.deleteCourse(course);
		}
		return Boolean.FALSE;
	}
}