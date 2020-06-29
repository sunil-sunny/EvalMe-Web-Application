package com.group18.asdc.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Repository;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.dao.RegisterDao;
import com.group18.asdc.entities.User;
import com.group18.asdc.entities.UserRegistartionDetails;
import com.group18.asdc.errorhandling.PasswordPolicyException;
import com.group18.asdc.util.ConstantStringUtil;

@Repository
public class RegisterServiceImpl implements RegisterService {

	private Logger log = Logger.getLogger(RegisterServiceImpl.class.getName());

	@Override
	public String registeruser(UserRegistartionDetails userDetails) {

		if (!userDetails.getBannerid().matches(ConstantStringUtil.getBanneridpatterncheck())) {
			return "invalid bannerid";
		} else if (userDetails.getBannerid().length() != 9) {
			return "invalid bannerid2";
		}
		if (!userDetails.getEmailid().matches(ConstantStringUtil.getEmailpatterncheck())) {
			return "invalidemailid";
		}
		try {
			User.isPasswordValid(userDetails.getPassword(),
					SystemConfig.getSingletonInstance().getBasePasswordPolicyManager());
		} catch (PasswordPolicyException e) {
			return "passwordPolicyException=" + e.getMessage();
		}
		RegisterDao registerDao = SystemConfig.getSingletonInstance().getTheRegisterDao();
		boolean isEmailExits = registerDao.checkUserWithEmail(userDetails.getEmailid());
		boolean isBannerIdExists = registerDao.checkUserWithEmail(userDetails.getBannerid());
		if (isBannerIdExists) {
			return "Banner Id already exists";
		}
		if (isEmailExits) {
			return "Email already exists";
		}
		boolean registerResult = false;
		if (!isBannerIdExists && !isEmailExits) {
			registerResult = registerDao.registeruser(userDetails);
		}
		if (registerResult) {
			return "Success";
		}
		return "User not Registered";
	}

	@Override
	public boolean registerStudents(List<User> studentList) {

		UserService userService = SystemConfig.getSingletonInstance().getTheUserService();
		EmailService emailService = null;
		boolean isAllStudentsRegistered = false;
		for (User user : studentList) {
			if (!userService.isUserExists(user)) {
				String result = this.registeruser(new UserRegistartionDetails(user));
				if (result.equalsIgnoreCase("success")) {
					isAllStudentsRegistered = true;
					emailService = SystemConfig.getSingletonInstance().getTheEmailService();
					String messageText = ConstantStringUtil.getEmailmessageheader() + user.getBannerId() + " "
							+ user.getBannerId().concat(ConstantStringUtil.getPasswordtag());
					emailService.sendSimpleMessage(user.getEmail(), ConstantStringUtil.getEmailsubject(), messageText);

				} else {
					log.info("user registartion error");
				}
			}
		}
		return isAllStudentsRegistered;
	}
}
