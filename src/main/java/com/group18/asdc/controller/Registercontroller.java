package com.group18.asdc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.entities.PasswordHistory;
import com.group18.asdc.entities.UserRegistartionDetails;
import com.group18.asdc.service.PasswordHistoryService;
import com.group18.asdc.service.RegisterService;

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
		RegisterService theRegisterService = SystemConfig.getSingletonInstance().getTheRegisterservice();
		if (result.hasErrors()) {
			return "registration";
		}
		String registrationStatus = theRegisterService.registeruser(bean);
		if (registrationStatus.equals("alreadycreated")) {
			System.out.println("already exists");
			return "redirect:/registration?alreadyregistered";
		} else if (registrationStatus.equals("passwordmismatch")) {
			return "redirect:/registration?passwordmismatch";
		} else if (registrationStatus.equals("invalidbannerid")) {
			return "redirect:/registration?invalidbannerid";
		} else if (registrationStatus.equals("invalidbannerid2")) {
			return "redirect:/registration?invalidbannerid2";
		} else if (registrationStatus.equals("alreadycreatedemail")) {
			return "redirect:/registration?alreadycreatedemail";
		} else if (registrationStatus.equals("invalidemailid")) {
			return "redirect:/registration?invalidemailid";
		} else if (registrationStatus.equals("shortpassword")) {
			return "redirect:/registration?shortpassword";
		} else if (registrationStatus.contains("passwordPolicyException")) {
			return "redirect:/registration?" + registrationStatus;
		} else {
			PasswordHistory passwordHistory = new PasswordHistory();
			passwordHistory.setBannerID(bean.getBannerid());
			passwordHistory.setPassword(bean.getPassword());
			passwordHistory.setDate(System.currentTimeMillis());
			PasswordHistoryService passwordHistoryService = SystemConfig.getSingletonInstance()
					.getPasswordHistoryService();
			passwordHistoryService.insertPassword(passwordHistory,
					SystemConfig.getSingletonInstance().getPasswordEncryption());
			return "redirect:/login?accountcreatedsuccessfully";
		}
	}
}
