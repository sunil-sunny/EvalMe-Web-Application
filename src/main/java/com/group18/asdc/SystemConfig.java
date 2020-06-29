package com.group18.asdc;

import com.group18.asdc.dao.AdminDao;

import com.group18.asdc.dao.AdminDaoImpl;
import com.group18.asdc.dao.CourseDetailsDao;
import com.group18.asdc.dao.CourseDetailsDaoImpl;
import com.group18.asdc.dao.CourseRolesDao;
import com.group18.asdc.dao.CourseRolesDaoImpl;
import com.group18.asdc.dao.CreateQuestionDao;
import com.group18.asdc.dao.CreateQuestionDaoImpl;
import com.group18.asdc.dao.DeleteQuestionDao;
import com.group18.asdc.dao.DeleteQuestionDaoImpl;
import com.group18.asdc.dao.RegisterDao;
import com.group18.asdc.dao.RegisterDaoImpl;
import com.group18.asdc.dao.UserDao;
import com.group18.asdc.dao.UserDaoImpl;
import com.group18.asdc.dao.ViewQuestionsDao;
import com.group18.asdc.dao.ViewQuestionsDaoImpl;
import com.group18.asdc.database.DefaultDatabaseConfiguration;
import com.group18.asdc.database.IDatabaseConfiguration;
import com.group18.asdc.passwordpolicy.BasePasswordPolicyManager;
import com.group18.asdc.passwordpolicy.IBasePasswordPolicyManager;
import com.group18.asdc.passwordpolicy.IPasswordPolicyDB;
import com.group18.asdc.passwordpolicy.IPasswordPolicyManager;
import com.group18.asdc.passwordpolicy.PasswordPolicyDB;
import com.group18.asdc.passwordpolicy.PasswordPolicyManager;
import com.group18.asdc.security.BCryptPasswordEncryption;
import com.group18.asdc.security.IPasswordEncryption;
import com.group18.asdc.service.AdminService;
import com.group18.asdc.service.AdminServiceImpl;
import com.group18.asdc.service.CourseDetailsService;
import com.group18.asdc.service.CourseDetailsServiceImpl;
import com.group18.asdc.service.CourseRolesService;
import com.group18.asdc.service.CourseRolesServiceImpl;
import com.group18.asdc.service.CreateQuestionService;
import com.group18.asdc.service.CreateQuestionServiceImpl;
import com.group18.asdc.service.DeleteQuestionService;
import com.group18.asdc.service.DeleteQuestionServiceImpl;
import com.group18.asdc.service.EmailService;
import com.group18.asdc.service.EmailServiceImpl;
import com.group18.asdc.service.PasswordHistoryService;
import com.group18.asdc.service.PasswordHistoryServiceImpl;
import com.group18.asdc.service.RegisterService;
import com.group18.asdc.service.RegisterServiceImpl;
import com.group18.asdc.service.UserService;
import com.group18.asdc.service.UserServiceImpl;
import com.group18.asdc.service.ViewQuestionsService;
import com.group18.asdc.service.ViewQuestionsServiceImpl;
import com.group18.asdc.util.CustomStringUtils;
import com.group18.asdc.util.DefaultMailSenderConfiguration;
import com.group18.asdc.util.ICustomStringUtils;
import com.group18.asdc.util.IJavaMailSenderConfiguration;
import com.group18.asdc.util.IQueryVariableToArrayList;
import com.group18.asdc.util.IRandomStringGenerator;
import com.group18.asdc.util.QueryVariableToArraylist;
import com.group18.asdc.util.RandomStringGenerator;

public class SystemConfig {

	private static SystemConfig singletonInstance = null;

	private AdminService theAdminService;
	private CourseDetailsService theCourseDetailsService;
	private IJavaMailSenderConfiguration javaMailSenderConfiguration;
	private EmailService theEmailService;
	private RegisterService theRegisterService;
	private UserService theUserService;
	private CreateQuestionService theCreateQuestionService;
	private ViewQuestionsService theViewQuestionsService;
	private DeleteQuestionService theDeleteQuestionService;
	private CourseRolesService theCourseRolesService;

	private AdminDao theAdminDao;
	private CourseDetailsDao theCourseDetailsDao;
	private RegisterDao theRegisterDao;
	private UserDao theUserDao;
	private IPasswordEncryption passwordEncryption;
	private IDatabaseConfiguration databaseConfiguration;
	private CreateQuestionDao theCreateQuestionDao;
	private ViewQuestionsDao theViewQuestionsDao;
	private DeleteQuestionDao theDeleteQuestionDao;
	private IPasswordPolicyDB passwordPolicyDB;
	private IBasePasswordPolicyManager basePasswordPolicyManager;
	private IPasswordPolicyManager passwordPolicyManager;
	private IQueryVariableToArrayList queryVariableToArrayList;
	private IRandomStringGenerator randomStringGenerator;
	private ICustomStringUtils customStringUtils;
	private PasswordHistoryService passwordHistoryService;
	private CourseRolesDao theCourseRolesDao;

	
	private SystemConfig() {

		this.javaMailSenderConfiguration = new DefaultMailSenderConfiguration();
		this.customStringUtils = new CustomStringUtils();
		//Instantiating Service Objects
		this.theAdminService=new AdminServiceImpl();
		this.theCourseDetailsService=new CourseDetailsServiceImpl();
		this.theEmailService=new EmailServiceImpl(this.javaMailSenderConfiguration);
		this.theRegisterService=new RegisterServiceImpl();
		this.theCreateQuestionService=new CreateQuestionServiceImpl();
		this.theViewQuestionsService=new ViewQuestionsServiceImpl();
		this.theDeleteQuestionService=new DeleteQuestionServiceImpl();
		this.theCourseRolesService=new CourseRolesServiceImpl();
		
		//Instantiating Dao objects
		this.queryVariableToArrayList = new QueryVariableToArraylist();
		this.theUserService = new UserServiceImpl(this.queryVariableToArrayList);
		this.theAdminDao = new AdminDaoImpl();
		this.theUserDao = new UserDaoImpl();
		this.theCourseDetailsDao = new CourseDetailsDaoImpl();
		this.theUserDao = new UserDaoImpl();
		this.theRegisterDao = new RegisterDaoImpl();
		this.passwordEncryption = new BCryptPasswordEncryption();
		this.databaseConfiguration = new DefaultDatabaseConfiguration();
		this.theCreateQuestionDao=new CreateQuestionDaoImpl();
		this.theViewQuestionsDao=new ViewQuestionsDaoImpl();
		this.theDeleteQuestionDao=new DeleteQuestionDaoImpl();
		this.theRegisterDao=new RegisterDaoImpl();
		this.passwordPolicyDB = new PasswordPolicyDB();
		this.basePasswordPolicyManager = new BasePasswordPolicyManager(this.passwordPolicyDB);
		this.passwordPolicyManager = new PasswordPolicyManager(this.passwordPolicyDB);
		this.randomStringGenerator = new RandomStringGenerator();
		this.passwordHistoryService = new PasswordHistoryServiceImpl(this.queryVariableToArrayList);
		this.theCourseRolesDao=new CourseRolesDaoImpl();

	}

	public static SystemConfig getSingletonInstance() {

		if (null == singletonInstance) {
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

	public IPasswordEncryption getPasswordEncryption() {
		return passwordEncryption;
	}

	public IDatabaseConfiguration getDatabaseConfiguration() {
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

	public CourseRolesService getTheCourseRolesService() {
		return theCourseRolesService;
	}

	public void setTheCourseRolesService(CourseRolesService theCourseRolesService) {
		this.theCourseRolesService = theCourseRolesService;
	}

	public void setTheCreateQuestionService(CreateQuestionService theCreateQuestionService) {
		this.theCreateQuestionService = theCreateQuestionService;
	}

	public CreateQuestionDao getTheCreateQuestionDao() {
		return theCreateQuestionDao;
	}

	public IJavaMailSenderConfiguration getJavaMailSenderConfiguration() {
		return javaMailSenderConfiguration;
	}

	public void setJavaMailSenderConfiguration(IJavaMailSenderConfiguration javaMailSenderConfiguration) {
		this.javaMailSenderConfiguration = javaMailSenderConfiguration;
	}

	public IQueryVariableToArrayList getQueryVariableToArrayList() {
		return queryVariableToArrayList;
	}

	public void setQueryVariableToArrayList(IQueryVariableToArrayList queryVariableToArrayList) {
		this.queryVariableToArrayList = queryVariableToArrayList;
	}

	public CourseRolesDao getTheCourseRolesDao() {
		return theCourseRolesDao;
	}

	public void setTheCourseRolesDao(CourseRolesDao theCourseRolesDao) {
		this.theCourseRolesDao = theCourseRolesDao;
	}

	public void setPasswordPolicyDB(IPasswordPolicyDB passwordPolicyDB) {
		this.passwordPolicyDB = passwordPolicyDB;
	}

	public void setRandomStringGenerator(IRandomStringGenerator randomStringGenerator) {
		this.randomStringGenerator = randomStringGenerator;
	}

	public void setCustomStringUtils(ICustomStringUtils customStringUtils) {
		this.customStringUtils = customStringUtils;
	}

	public void setPasswordHistoryService(PasswordHistoryService passwordHistoryService) {
		this.passwordHistoryService = passwordHistoryService;
	}

	public DeleteQuestionService getTheDeleteQuestionService() {
		return theDeleteQuestionService;
	}

	public void setTheDeleteQuestionService(DeleteQuestionService theDeleteQuestionService) {
		this.theDeleteQuestionService = theDeleteQuestionService;
	}

	public DeleteQuestionDao getTheDeleteQuestionDao() {
		return theDeleteQuestionDao;
	}

	public void setTheDeleteQuestionDao(DeleteQuestionDao theDeleteQuestionDao) {
		this.theDeleteQuestionDao = theDeleteQuestionDao;
	}

	public void setTheCreateQuestionDao(CreateQuestionDao theCreateQuestionDao) {
		this.theCreateQuestionDao = theCreateQuestionDao;
	}

	public ViewQuestionsService getTheViewQuestionsService() {
		return theViewQuestionsService;
	}

	public void setTheViewQuestionsService(ViewQuestionsService theViewQuestionsService) {
		this.theViewQuestionsService = theViewQuestionsService;
	}

	public ViewQuestionsDao getTheViewQuestionsDao() {
		return theViewQuestionsDao;
	}

	public void setTheViewQuestionsDao(ViewQuestionsDao theViewQuestionsDao) {
		this.theViewQuestionsDao = theViewQuestionsDao;
	}

	public IPasswordPolicyDB getPasswordPolicyDB() {
		return passwordPolicyDB;
	}

	public void setBasePasswordPolicyManager(IBasePasswordPolicyManager basePasswordPolicyManager) {
		this.basePasswordPolicyManager = basePasswordPolicyManager;
	}

	public IBasePasswordPolicyManager getBasePasswordPolicyManager() {
		return basePasswordPolicyManager;
	}

	public void setPasswordPolicyManager(IPasswordPolicyManager passwordPolicyManager) {
		this.passwordPolicyManager = passwordPolicyManager;
	}

	public IPasswordPolicyManager getPasswordPolicyManager() {
		return passwordPolicyManager;
	}

	public IQueryVariableToArrayList getQueryVariableToArrayListConverter() {
		return queryVariableToArrayList;
	}

	public IRandomStringGenerator getRandomStringGenerator() {
		return randomStringGenerator;
	}

	public ICustomStringUtils getCustomStringUtils() {
		return customStringUtils;
	}

	public PasswordHistoryService getPasswordHistoryService() {
		return this.passwordHistoryService;
	}
}
