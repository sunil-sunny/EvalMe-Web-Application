package com.group18.asdc.controller;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.group18.asdc.ProfileManagementConfig;
import com.group18.asdc.entities.PasswordHistory;
import com.group18.asdc.entities.UserRegistartionDetails;
import com.group18.asdc.service.PasswordHistoryService;
import com.group18.asdc.service.RegisterService;
import com.group18.asdc.util.RegistrationStatus;

@Controller
@RequestMapping("/registration")
public class RegisterController {

	@ModelAttribute("user")
	public UserRegistartionDetails bean() {
		return new UserRegistartionDetails();
	}

	@GetMapping
	public String showRegistrationForm(Model model) {
		return "registration";
	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistartionDetails bean, BindingResult result) {
		RegisterService theRegisterService = ProfileManagementConfig.getSingletonInstance().getTheRegisterservice();
		if (result.hasErrors()) {
			return "registration";
		}
		JSONObject resultObject = theRegisterService.registeruser(bean);
		Integer registrationStatus = resultObject.optInt("STATUS");
		if (registrationStatus == RegistrationStatus.INVALID_BANNER_PATTERN) {
			return "redirect:/registration?invalidbannerid";
		} else if (registrationStatus == RegistrationStatus.INVALID_BANNER_LENGTH) {
			return "redirect:/registration?invalidbannerid2";
		} else if (registrationStatus == RegistrationStatus.EXISTING_EMAIL_ID) {
			return "redirect:/registration?alreadycreatedemail";
		} else if (registrationStatus == RegistrationStatus.INVALID_EMAIL_PATTERN) {
			return "redirect:/registration?invalidemailid";
		} else if (registrationStatus == RegistrationStatus.PASSWORD_POLICY_ERROR) {
			return "redirect:/registration?passwordPolicyException=" + resultObject.optString("MESSAGE");
		} else if (registrationStatus == RegistrationStatus.SUCCESS) {
			PasswordHistory passwordHistory = new PasswordHistory();
			passwordHistory.setBannerID(bean.getBannerid());
			passwordHistory.setPassword(bean.getPassword());
			passwordHistory.setDate(System.currentTimeMillis());
			PasswordHistoryService passwordHistoryService = ProfileManagementConfig.getSingletonInstance()
					.getPasswordHistoryService();
			passwordHistoryService.insertPassword(passwordHistory,
					ProfileManagementConfig.getSingletonInstance().getPasswordEncryption());
			return "redirect:/login?accountcreatedsuccessfully";
		} else {
			return "registration";
		}
	}
}