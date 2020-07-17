package com.group18.asdc.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.dao.RegisterDao;
import com.group18.asdc.entities.User;
import com.group18.asdc.entities.UserRegistartionDetails;
import com.group18.asdc.errorhandling.PasswordPolicyException;
import com.group18.asdc.util.ConstantStringUtil;
import com.group18.asdc.util.RegistrationStatus;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Repository;

@Repository
public class RegisterServiceImpl implements RegisterService {

	private static final Logger log = Logger.getLogger(RegisterServiceImpl.class.getName());
	private static final RegisterDao registerDao = SystemConfig.getSingletonInstance().getDaoAbstractFactory()
			.getRegisterDao();

	@Override
	public JSONObject registeruser(UserRegistartionDetails userDetails) {
		JSONObject resultObj = new JSONObject();
		try {
			resultObj.put("STATUS", RegistrationStatus.UNSUCCESSFUL.value());
			boolean isError = Boolean.FALSE;
			if (userDetails.getBannerid().matches(ConstantStringUtil.BANNER_ID_CHECK.toString())) {
				if (9 == userDetails.getBannerid().length()) {
					isError = Boolean.FALSE;
				} else {
					isError = Boolean.TRUE;
					resultObj.put("STATUS", RegistrationStatus.INVALID_BANNER_LENGTH);
					log.log(Level.WARNING, "BannerId invalid");
				}
			} else {
				isError = Boolean.TRUE;
				resultObj.put("STATUS", RegistrationStatus.INVALID_BANNER_PATTERN);
				log.log(Level.WARNING, "BannerId does not follow the specified pattern");
			}
			if (userDetails.getEmailid().matches(ConstantStringUtil.EMAIL_PATTERN_CHECK.toString())) {
				log.log(Level.INFO, "Validating password for the user="+userDetails.getBannerid());
				User.validatePassword(userDetails.getPassword(),
						SystemConfig.getSingletonInstance().getBasePasswordPolicyManager());

				if (isError) {
					return resultObj;
				}
				boolean isEmailExits = registerDao.checkUserWithEmail(userDetails.getEmailid());
				boolean isBannerIdExists = registerDao.checkUserWithEmail(userDetails.getBannerid());
				if (isBannerIdExists) {
					resultObj.put("STATUS", RegistrationStatus.EXISTING_BANNER_ID);
					log.log(Level.WARNING, "BannerId already registered");
				}
				if (isEmailExits) {
					resultObj.put("STATUS", RegistrationStatus.EXISTING_EMAIL_ID);
					log.log(Level.WARNING, "Email is already registered");
				}
				boolean registerResult = Boolean.FALSE;
				if (isBannerIdExists && isEmailExits) {
					if (registerResult) {
						resultObj.put("STATUS", RegistrationStatus.SUCCESS);
					}
				} else {
					registerResult = registerDao.registeruser(userDetails);
					resultObj.put("STATUS", RegistrationStatus.SUCCESS);
				}
			} else {
				isError = Boolean.TRUE;
				resultObj.put("STATUS", RegistrationStatus.INVALID_EMAIL_PATTERN);
				log.log(Level.WARNING, "EmailId does not follow the correct pattern");
			}

		} catch (PasswordPolicyException e) {
			try {
				log.log(Level.SEVERE, "Error updating password for the user=" + userDetails.getBannerid() + " password="
						+ userDetails.getPassword());
				resultObj.put("STATUS", RegistrationStatus.PASSWORD_POLICY_ERROR);
				resultObj.put("MESSAGE", e.getMessage());
			} catch (JSONException e1) {
				log.log(Level.SEVERE, "JSON exception, should analyze further on usage of JSON values", e1);

			}
		} catch (JSONException e) {
			log.log(Level.SEVERE, "JSON Exception while registering the user with id " + userDetails.getBannerid());
		}
		return resultObj;
	}

	@Override
	public boolean registerStudents(List<User> studentList) {
		UserService userService = SystemConfig.getSingletonInstance().getServiceAbstractFactory().getUserService(
				SystemConfig.getSingletonInstance().getUtilAbstractFactory().getQueryVariableToArrayList());
		EmailService emailService = null;
		boolean isAllStudentsRegistered = Boolean.FALSE;
		for (User user : studentList) {
			if (userService.isUserExists(user)) {
				isAllStudentsRegistered = Boolean.FALSE;
			} else {
				JSONObject resultObject = this.registeruser(SystemConfig.getSingletonInstance()
						.getModelAbstractFactory().getIUserRegistartionDetails(user));
				if (resultObject.optInt("STATUS") == RegistrationStatus.SUCCESS.value()) {
					isAllStudentsRegistered = Boolean.TRUE;
					emailService = SystemConfig.getSingletonInstance().getServiceAbstractFactory().getEmailService();
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
