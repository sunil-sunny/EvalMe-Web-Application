package com.group18.asdc.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Repository;
import com.group18.asdc.SystemConfig;
import com.group18.asdc.dao.RegisterDao;
import com.group18.asdc.entities.User;
import com.group18.asdc.entities.UserRegistartionDetails;
import com.group18.asdc.errorhandling.PasswordPolicyException;
import com.group18.asdc.util.ConstantStringUtil;
import com.group18.asdc.util.RegistrationStatus;

@Repository
public class RegisterServiceImpl implements RegisterService {

	private Logger log = Logger.getLogger(RegisterServiceImpl.class.getName());

	@Override
	public JSONObject registeruser(UserRegistartionDetails userDetails) {
		JSONObject resultObj = new JSONObject();
		try {
			resultObj.put("STATUS", RegistrationStatus.UNSUCCESSFUL);
			boolean isError = false;
			if (userDetails.getBannerid().matches(ConstantStringUtil.getBanneridpatterncheck())) {
				if (9 != userDetails.getBannerid().length()) {
					isError = true;
					resultObj.put("STATUS", RegistrationStatus.INVALID_BANNER_LENGTH);
				}
			} else {
				isError = true;
				resultObj.put("STATUS", RegistrationStatus.INVALID_BANNER_PATTERN);
			}
			if (userDetails.getEmailid().matches(ConstantStringUtil.getEmailpatterncheck())) {

				try {
					User.isPasswordValid(userDetails.getPassword(),
							SystemConfig.getSingletonInstance().getBasePasswordPolicyManager());
				} catch (PasswordPolicyException e) {
					isError = true;
					resultObj.put("STATUS", RegistrationStatus.PASSWORD_POLICY_ERROR);
					resultObj.put("MESSAGE", e.getMessage());
				}
				if (isError) {
					return resultObj;
				}
				RegisterDao registerDao = SystemConfig.getSingletonInstance().getTheRegisterDao();
				boolean isEmailExits = registerDao.checkUserWithEmail(userDetails.getEmailid());
				boolean isBannerIdExists = registerDao.checkUserWithEmail(userDetails.getBannerid());
				if (isBannerIdExists) {
					resultObj.put("STATUS", RegistrationStatus.EXISTING_BANNER_ID);
				}
				if (isEmailExits) {
					resultObj.put("STATUS", RegistrationStatus.EXISTING_EMAIL_ID);
				}
				boolean registerResult = false;
				if (isBannerIdExists && isEmailExits) {
					if (registerResult) {
						resultObj.put("STATUS", RegistrationStatus.SUCCESS);
					}
				} else {
					registerResult = registerDao.registeruser(userDetails);
				}
			} else {
				isError = true;
				resultObj.put("STATUS", RegistrationStatus.INVALID_EMAIL_PATTERN);
			}

		} catch (JSONException e) {
			log.severe("user registration error");
		}
		return resultObj;
	}

	@Override
	public boolean registerStudents(List<User> studentList) {
		UserService userService = SystemConfig.getSingletonInstance().getTheUserService();
		EmailService emailService = null;
		boolean isAllStudentsRegistered = false;
		for (User user : studentList) {
			if (false == userService.isUserExists(user)) {
				JSONObject resultObject = this.registeruser(new UserRegistartionDetails(user));
				if (resultObject.optInt("STATUS") == RegistrationStatus.SUCCESS) {
					isAllStudentsRegistered = true;
					emailService = SystemConfig.getSingletonInstance().getTheEmailService();
					String messageText = ConstantStringUtil.getEmailmessageheader() + user.getBannerId() + " "
							+ user.getBannerId().concat(ConstantStringUtil.getPasswordtag());
					emailService.sendSimpleMessage(user.getEmail(), ConstantStringUtil.getEmailsubject(), messageText);

				} else {
					log.severe("user registration error");
				}
			}
		}
		return isAllStudentsRegistered;
	}
}
