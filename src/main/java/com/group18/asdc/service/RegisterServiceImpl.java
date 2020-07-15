package com.group18.asdc.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Repository;
import com.group18.asdc.ProfileManagementConfig;
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
			boolean isError = Boolean.FALSE;
			if (userDetails.getBannerid().matches(ConstantStringUtil.BANNER_ID_CHECK.toString())) {
				if (9 != userDetails.getBannerid().length()) {
					isError = Boolean.TRUE;
					resultObj.put("STATUS", RegistrationStatus.INVALID_BANNER_LENGTH);
				}
			} else {
				isError = Boolean.TRUE;
				resultObj.put("STATUS", RegistrationStatus.INVALID_BANNER_PATTERN);
			}
			if (userDetails.getEmailid().matches(ConstantStringUtil.EMAIL_PATTERN_CHECK.toString())) {

				User.validatePassword(userDetails.getPassword(),
						ProfileManagementConfig.getSingletonInstance().getBasePasswordPolicyManager());

				if (isError) {
					return resultObj;
				}
				RegisterDao registerDao = ProfileManagementConfig.getSingletonInstance().getTheRegisterDao();
				boolean isEmailExits = registerDao.checkUserWithEmail(userDetails.getEmailid());
				boolean isBannerIdExists = registerDao.checkUserWithEmail(userDetails.getBannerid());
				if (isBannerIdExists) {
					resultObj.put("STATUS", RegistrationStatus.EXISTING_BANNER_ID);
				}
				if (isEmailExits) {
					resultObj.put("STATUS", RegistrationStatus.EXISTING_EMAIL_ID);
				}
				boolean registerResult = Boolean.FALSE;
				if (isBannerIdExists && isEmailExits) {
					if (registerResult) {
						resultObj.put("STATUS", RegistrationStatus.SUCCESS);
					}
				} else {
					registerResult = registerDao.registeruser(userDetails);
				}
			} else {
				isError = Boolean.TRUE;
				resultObj.put("STATUS", RegistrationStatus.INVALID_EMAIL_PATTERN);
			}

		} catch (PasswordPolicyException e) {
			try {
				resultObj.put("STATUS", RegistrationStatus.PASSWORD_POLICY_ERROR);
				resultObj.put("MESSAGE", e.getMessage());
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		} catch (JSONException e) {
			log.log(Level.SEVERE, "JSON Exception while registering the user with id " + userDetails.getBannerid());
		}
		return resultObj;
	}

	@Override
	public boolean registerStudents(List<User> studentList) {
		UserService userService = ProfileManagementConfig.getSingletonInstance().getTheUserService();
		EmailService emailService = null;
		boolean isAllStudentsRegistered = Boolean.FALSE;
		for (User user : studentList) {
			if (userService.isUserExists(user)) {
				isAllStudentsRegistered = Boolean.FALSE;
			} else {
				JSONObject resultObject = this.registeruser(new UserRegistartionDetails(user));
				if (resultObject.optInt("STATUS") == RegistrationStatus.SUCCESS) {
					isAllStudentsRegistered = Boolean.TRUE;
					emailService = ProfileManagementConfig.getSingletonInstance().getTheEmailService();
					String messageText = ConstantStringUtil.EMAIL_MESSAGE_HEADER.toString() + user.getBannerId() + " "
							+ user.getBannerId().concat(ConstantStringUtil.PASSWORD_TAG.toString());
					emailService.sendSimpleMessage(user.getEmail(), ConstantStringUtil.EMAIL_SUBJECT.toString(),
							messageText);

				}
			}
		}
		return isAllStudentsRegistered;
	}
}
