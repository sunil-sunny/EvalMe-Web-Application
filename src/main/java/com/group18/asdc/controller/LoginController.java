package com.group18.asdc.controller;

import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import com.group18.asdc.ProfileManagementConfig;
import com.group18.asdc.SystemConfig;
import com.group18.asdc.database.IPasswordPolicyDB;
import com.group18.asdc.entities.PasswordHistory;
import com.group18.asdc.entities.Role;
import com.group18.asdc.entities.User;
import com.group18.asdc.errorhandling.PasswordPolicyException;
import com.group18.asdc.handlingformsubmission.ResetPassword;
import com.group18.asdc.passwordpolicy.BasePasswordPolicyManager;
import com.group18.asdc.passwordpolicy.PasswordPolicyManager;
import com.group18.asdc.service.EmailService;
import com.group18.asdc.service.PasswordHistoryService;
import com.group18.asdc.service.UserService;

@Controller
public class LoginController {

	private static final UserService userService = SystemConfig.getSingletonInstance().getServiceAbstractFactory()
			.getUserService();
	private static final EmailService emailService = SystemConfig.getSingletonInstance().getServiceAbstractFactory()
			.getEmailService();

	@RequestMapping("/")
	public RedirectView redirectPage() {
		return new RedirectView("/login-success");
	}

	@RequestMapping(value = { "/login", "/home" })
	public String login() {
		return "login.html";
	}

	@RequestMapping("/login-success")
	public RedirectView loginSuccess(Authentication authentication) {
		String systemRoleForCurrentUser = authentication.getAuthorities().iterator().next().toString();
		String redirectURL;
		if (systemRoleForCurrentUser.equals(Role.ADMIN.toString())) {
			redirectURL = "/adminhome";
		} else {
			redirectURL = "/userhome";
		}
		return new RedirectView(redirectURL);
	}

	@RequestMapping("/forgot-password")
	public String forgotPasswordPage() {
		return "forgot-password.html";
	}

	@GetMapping("/resetPassword")
	public String sendResetRequest(@RequestParam(name = "username", required = true) String bannerId, Model model,
			HttpSession session) {
		User userObj = new User();
		userService.loadUserWithBannerId(bannerId, userObj);
		if (userObj.getEmail() == null || userObj.getEmail().isEmpty()) {
			model.addAttribute("BANNER_ID_NOT_EXIST", Boolean.TRUE);
			return "forgot-password.html";
		} else {
			String genPassword = ProfileManagementConfig.getSingletonInstance().getRandomStringGenerator()
					.generateRandomString();
			session.setAttribute("RESET_PASSWORD", genPassword);
			model.addAttribute("resetForm", new ResetPassword(bannerId));
			model.addAttribute("sentEmail", userObj.getEmail());
			emailService.sendSimpleMessage(userObj.getEmail(), "Reset Password",
					"Your reset password is: " + genPassword);
			return "resetPassword.html";
		}
	}

	@PostMapping("/resetPassword")
	public String resetPassword(@ModelAttribute ResetPassword resetForm, Model model, HttpSession session) {

		String redirectURL = "login-success";
		Boolean isError = false;
		User userObj = new User();
		userService.loadUserWithBannerId(resetForm.getbannerId(), userObj);
		if (resetForm.getgeneratedPassword().equals(session.getAttribute("RESET_PASSWORD"))) {
			if (resetForm.getnewPassword().equals(resetForm.getconfirmNewPassword())) {
				try {
					userObj.setPassword(resetForm.getconfirmNewPassword());
					userObj.validatePassword(ProfileManagementConfig.getSingletonInstance().getPasswordPolicyManager());
					if (userService.updatePassword(userObj,
							ProfileManagementConfig.getSingletonInstance().getPasswordEncryption())) {

						PasswordHistory passwordHistory = new PasswordHistory();
						passwordHistory.setBannerID(userObj.getBannerId());
						passwordHistory.setPassword(userObj.getPassword());
						passwordHistory.setDate(System.currentTimeMillis());
						PasswordHistoryService passwordHistoryService = ProfileManagementConfig.getSingletonInstance()
								.getPasswordHistoryService();
						passwordHistoryService.insertPassword(passwordHistory,
								ProfileManagementConfig.getSingletonInstance().getPasswordEncryption());

					} else {

						model.addAttribute("passwordResetError", Boolean.TRUE);
						model.addAttribute("reason", "Error resetting password.");
						isError = Boolean.TRUE;

					}
				} catch (PasswordPolicyException e) {
					model.addAttribute("historyConstrainError", Boolean.TRUE);
					model.addAttribute("reason", e.getMessage());
					isError = Boolean.TRUE;
				}
			} else {
				model.addAttribute("confirmPasswordError", Boolean.TRUE);
				model.addAttribute("reason", "New password and confirm password does not match");
				isError = Boolean.TRUE;
			}
		} else {
			model.addAttribute("genPasswordError", Boolean.TRUE);
			model.addAttribute("reason", "Password sent in mail does not match");
			isError = Boolean.TRUE;
		}

		if (isError) {
			model.addAttribute("resetForm", new ResetPassword(resetForm.getbannerId()));
			model.addAttribute("isError", Boolean.TRUE);
			model.addAttribute("sentEmail", userObj.getEmail());
			return "resetPassword";
		} else {
			return "redirect:" + redirectURL;
		}
	}

	@GetMapping("/error")
	public String getErrorPage() {
		return "error";
	}

	@GetMapping("/resetPasswordPolicies")
	public String resetPasswordPolicies() {
		IPasswordPolicyDB passwordPolicyDB = ProfileManagementConfig.getSingletonInstance().getPasswordPolicyDB();
		ProfileManagementConfig.getSingletonInstance()
				.setBasePasswordPolicyManager(new BasePasswordPolicyManager(passwordPolicyDB));
		ProfileManagementConfig.getSingletonInstance()
				.setPasswordPolicyManager(new PasswordPolicyManager(passwordPolicyDB));
		return "policyReset";
	}
}