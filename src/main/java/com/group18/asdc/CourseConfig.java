package com.group18.asdc;

import com.group18.asdc.dao.CourseDetailsDao;
import com.group18.asdc.dao.CourseDetailsDaoImpl;
import com.group18.asdc.dao.CourseRolesDao;
import com.group18.asdc.dao.CourseRolesDaoImpl;
import com.group18.asdc.service.CourseDetailsService;
import com.group18.asdc.service.CourseDetailsServiceImpl;
import com.group18.asdc.service.CourseRolesService;
import com.group18.asdc.service.CourseRolesServiceImpl;

public class CourseConfig {

	private static CourseConfig singleInstance = null;

	private CourseRolesService theCourseRolesService;
	private CourseDetailsService theCourseDetailsService;
	private CourseDetailsDao theCourseDetailsDao;
	private CourseRolesDao theCourseRolesDao;

	private CourseConfig() {
		this.theCourseDetailsService = new CourseDetailsServiceImpl();
		this.theCourseRolesService = new CourseRolesServiceImpl();
		this.theCourseDetailsDao = new CourseDetailsDaoImpl();
		this.theCourseRolesDao = new CourseRolesDaoImpl();
	}
	

	public static CourseConfig getSingletonInstance() {
		if (null == singleInstance) {
			singleInstance = new CourseConfig();
		}
		return singleInstance;
	}

	public CourseRolesService getTheCourseRolesService() {
		return theCourseRolesService;
	}

	public void setTheCourseRolesService(CourseRolesService theCourseRolesService) {
		this.theCourseRolesService = theCourseRolesService;
	}

	public CourseDetailsService getTheCourseDetailsService() {
		return theCourseDetailsService;
	}

	public void setTheCourseDetailsService(CourseDetailsService theCourseDetailsService) {
		this.theCourseDetailsService = theCourseDetailsService;
	}

	public CourseDetailsDao getTheCourseDetailsDao() {
		return theCourseDetailsDao;
	}

	public void setTheCourseDetailsDao(CourseDetailsDao theCourseDetailsDao) {
		this.theCourseDetailsDao = theCourseDetailsDao;
	}

	public CourseRolesDao getTheCourseRolesDao() {
		return theCourseRolesDao;
	}

	public void setTheCourseRolesDao(CourseRolesDao theCourseRolesDao) {
		this.theCourseRolesDao = theCourseRolesDao;
	}

}
