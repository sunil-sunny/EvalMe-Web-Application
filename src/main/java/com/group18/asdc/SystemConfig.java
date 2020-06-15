package com.group18.asdc;

import com.group18.asdc.dao.AdminDao;
import com.group18.asdc.dao.AdminDaoImpl;
import com.group18.asdc.dao.CourseDetailsDao;
import com.group18.asdc.dao.CourseDetailsDaoImpl;
import com.group18.asdc.dao.CreateQuestionDao;
import com.group18.asdc.dao.CreateQuestionDaoImpl;
import com.group18.asdc.dao.RegisterDao;
import com.group18.asdc.dao.RegisterDaoImpl;
import com.group18.asdc.dao.UserDao;
import com.group18.asdc.dao.UserDaoImpl;
import com.group18.asdc.database.DefaultDatabaseConfiguration;
import com.group18.asdc.database.IDatabaseConfiguration;
import com.group18.asdc.security.BCryptPasswordEncryption;
import com.group18.asdc.security.IPasswordEncryption;
import com.group18.asdc.service.AdminService;
import com.group18.asdc.service.AdminServiceImpl;
import com.group18.asdc.service.CourseDetailsService;
import com.group18.asdc.service.CourseDetailsServiceImpl;
import com.group18.asdc.service.CreateQuestionService;
import com.group18.asdc.service.CreateQuestionServiceImpl;
import com.group18.asdc.service.EmailService;
import com.group18.asdc.service.EmailServiceImpl;
import com.group18.asdc.service.RegisterService;
import com.group18.asdc.service.RegisterServiceImpl;
import com.group18.asdc.service.UserService;
import com.group18.asdc.service.UserServiceImpl;

public class SystemConfig {

	private static SystemConfig singletonInstance = null;

	// Below are the instance objects for service layer
	private AdminService theAdminService;
	private CourseDetailsService theCourseDetailsService;
	private EmailService theEmailService;
	private RegisterService theRegisterService;
	private UserService theUserService;
	private CreateQuestionService theCreateQuestionService;

	// Below are the instance objects for Dao layer
	private AdminDao theAdminDao;
	private CourseDetailsDao theCourseDetailsDao;
	private RegisterDao theRegisterDao;
	private UserDao theUserDao;
	private IPasswordEncryption passwordEncryption;
	private IDatabaseConfiguration databaseConfiguration;
	private CreateQuestionDao theCreateQuestionDao;

	private SystemConfig() {
		
		//Instantiating Service Objects
		this.theAdminService=new AdminServiceImpl();
		this.theCourseDetailsService=new CourseDetailsServiceImpl();
		this.theEmailService=new EmailServiceImpl();
		this.theRegisterService=new RegisterServiceImpl();
		this.theUserService=new UserServiceImpl();
		this.theCreateQuestionService=new CreateQuestionServiceImpl();
		
		
		//Instantiating Dao objects
		this.theAdminDao=new AdminDaoImpl();
		this.theCourseDetailsDao=new CourseDetailsDaoImpl();
		this.theUserDao=new UserDaoImpl();
		this.theRegisterDao=new RegisterDaoImpl();
		this.passwordEncryption = new BCryptPasswordEncryption();
		this.databaseConfiguration = new DefaultDatabaseConfiguration();
		this.theCreateQuestionDao=new CreateQuestionDaoImpl();
	}

	public static SystemConfig getSingletonInstance() {
		
		if (null == singletonInstance)
		{
			singletonInstance = new SystemConfig();
		}

		return singletonInstance;
	}

	

	public AdminService getTheAdminService() {
		return theAdminService;
	}

	public void setTheAdminService(AdminService theAdminService) {
		this.theAdminService = theAdminService;
	}

	public CourseDetailsService getTheCourseDetailsService() {
		return theCourseDetailsService;
	}

	public void setTheCourseDetailsService(CourseDetailsService theCourseDetailsService) {
		this.theCourseDetailsService = theCourseDetailsService;
	}

	public EmailService getTheEmailService() {
		return theEmailService;
	}

	public void setTheEmailService(EmailService theEmailService) {
		this.theEmailService = theEmailService;
	}

	public RegisterService getTheRegisterservice() {
		return theRegisterService;
	}

	public void setTheRegisterservice(RegisterServiceImpl theRegisterservice) {
		this.theRegisterService = theRegisterservice;
	}

	public UserService getTheUserService() {
		return theUserService;
	}

	public void setTheUserService(UserService theUserService) {
		this.theUserService = theUserService;
	}

	public AdminDao getTheAdminDao() {
		return theAdminDao;
	}

	public void setTheAdminDao(AdminDao theAdminDao) {
		this.theAdminDao = theAdminDao;
	}

	public CourseDetailsDao getTheCourseDetailsDao() {
		return theCourseDetailsDao;
	}

	public void setTheCourseDetailsDao(CourseDetailsDao theCourseDetailsDao) {
		this.theCourseDetailsDao = theCourseDetailsDao;
	}

	public UserDao getTheUserDao() {
		return theUserDao;
	}

	public void setTheUserDao(UserDao theUserDao) {
		this.theUserDao = theUserDao;
	}

	public IPasswordEncryption getPasswordEncryption(){
		return passwordEncryption;
	}

	public IDatabaseConfiguration getDatabaseConfiguration()
	{
		return databaseConfiguration;
	}

	public RegisterService getTheRegisterService() {
		return theRegisterService;
	}

	public void setTheRegisterService(RegisterService theRegisterService) {
		this.theRegisterService = theRegisterService;
	}

	public RegisterDao getTheRegisterDao() {
		return theRegisterDao;
	}

	public void setTheRegisterDao(RegisterDao theRegisterDao) {
		this.theRegisterDao = theRegisterDao;
	}

	public void setPasswordEncryption(IPasswordEncryption passwordEncryption) {
		this.passwordEncryption = passwordEncryption;
	}

	public void setDatabaseConfiguration(IDatabaseConfiguration databaseConfiguration) {
		this.databaseConfiguration = databaseConfiguration;
	}

	public CreateQuestionService getTheCreateQuestionService() {
		return theCreateQuestionService;
	}

	public void setTheCreateQuestionService(CreateQuestionService theCreateQuestionService) {
		this.theCreateQuestionService = theCreateQuestionService;
	}

	public CreateQuestionDao getTheCreateQuestionDao() {
		return theCreateQuestionDao;
	}

	public void setTheCreateQuestionDao(CreateQuestionDao theCreateQuestionDao) {
		this.theCreateQuestionDao = theCreateQuestionDao;
	}
}
