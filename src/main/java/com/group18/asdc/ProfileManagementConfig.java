package com.group18.asdc;

import com.group18.asdc.dao.AdminDao;
import com.group18.asdc.dao.AdminDaoImpl;
import com.group18.asdc.dao.RegisterDao;
import com.group18.asdc.dao.RegisterDaoImpl;
import com.group18.asdc.dao.UserDao;
import com.group18.asdc.dao.UserDaoImpl;
import com.group18.asdc.database.DefaultDatabaseConfiguration;
import com.group18.asdc.database.IDatabaseConfiguration;
import com.group18.asdc.database.IPasswordPolicyDB;
import com.group18.asdc.database.PasswordPolicyDB;
import com.group18.asdc.passwordpolicy.BasePasswordPolicyManager;
import com.group18.asdc.passwordpolicy.IBasePasswordPolicyManager;
import com.group18.asdc.passwordpolicy.IPasswordPolicyManager;
import com.group18.asdc.passwordpolicy.PasswordPolicyManager;
import com.group18.asdc.security.BCryptPasswordEncryption;
import com.group18.asdc.security.IPasswordEncryption;
import com.group18.asdc.service.AdminService;
import com.group18.asdc.service.AdminServiceImpl;
import com.group18.asdc.service.DefaultMailSenderConfiguration;
import com.group18.asdc.service.EmailService;
import com.group18.asdc.service.EmailServiceImpl;
import com.group18.asdc.service.IJavaMailSenderConfiguration;
import com.group18.asdc.service.PasswordHistoryService;
import com.group18.asdc.service.PasswordHistoryServiceImpl;
import com.group18.asdc.service.RegisterService;
import com.group18.asdc.service.RegisterServiceImpl;
import com.group18.asdc.service.UserService;
import com.group18.asdc.service.UserServiceImpl;
import com.group18.asdc.util.CustomStringUtils;
import com.group18.asdc.util.ICustomStringUtils;
import com.group18.asdc.util.IQueryVariableToArrayList;
import com.group18.asdc.util.IRandomStringGenerator;
import com.group18.asdc.util.QueryVariableToArraylist;
import com.group18.asdc.util.RandomStringGenerator;

public class ProfileManagementConfig {

	private static ProfileManagementConfig singletonInstance = null;
	private AdminService theAdminService;
	private IJavaMailSenderConfiguration javaMailSenderConfiguration;
	private EmailService theEmailService;
	private RegisterService theRegisterService;
	private UserService theUserService;
	private AdminDao theAdminDao;
	private RegisterDao theRegisterDao;
	private UserDao theUserDao;
	private IPasswordEncryption passwordEncryption;
	private IDatabaseConfiguration databaseConfiguration;
	private IPasswordPolicyDB passwordPolicyDB;
	private IBasePasswordPolicyManager basePasswordPolicyManager;
	private IPasswordPolicyManager passwordPolicyManager;
	private IQueryVariableToArrayList queryVariableToArrayList;
	private IRandomStringGenerator randomStringGenerator;
	private ICustomStringUtils customStringUtils;
	private PasswordHistoryService passwordHistoryService;

	private ProfileManagementConfig() {
		this.javaMailSenderConfiguration = new DefaultMailSenderConfiguration();
		this.customStringUtils = new CustomStringUtils();
		this.theAdminService = new AdminServiceImpl();
		this.theEmailService = new EmailServiceImpl(this.javaMailSenderConfiguration);
		this.theRegisterService = new RegisterServiceImpl();
		this.queryVariableToArrayList = new QueryVariableToArraylist();
		this.theUserService = new UserServiceImpl(this.queryVariableToArrayList);
		this.theAdminDao = new AdminDaoImpl();
		this.theUserDao = new UserDaoImpl();
		this.theUserDao = new UserDaoImpl();
		this.theRegisterDao = new RegisterDaoImpl();
		this.passwordEncryption = new BCryptPasswordEncryption();
		this.databaseConfiguration = new DefaultDatabaseConfiguration();
		this.theRegisterDao = new RegisterDaoImpl();
		this.passwordPolicyDB = new PasswordPolicyDB();
		this.basePasswordPolicyManager = new BasePasswordPolicyManager(this.passwordPolicyDB);
		this.passwordPolicyManager = new PasswordPolicyManager(this.passwordPolicyDB);
		this.randomStringGenerator = new RandomStringGenerator();
		this.passwordHistoryService = new PasswordHistoryServiceImpl(this.queryVariableToArrayList);
	}

	public static ProfileManagementConfig getSingletonInstance() {
		if (null == singletonInstance) {
			singletonInstance = new ProfileManagementConfig();
		}
		return singletonInstance;
	}

	public AdminService getTheAdminService() {
		return theAdminService;
	}

	public void setTheAdminService(AdminService theAdminService) {
		this.theAdminService = theAdminService;
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
