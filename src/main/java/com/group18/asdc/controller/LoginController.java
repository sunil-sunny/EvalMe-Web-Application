package com.group18.asdc.controller;

import java.util.HashMap;

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

import com.group18.asdc.SystemConfig;
import com.group18.asdc.entities.Role;
import com.group18.asdc.entities.User;
import com.group18.asdc.handlingformsubmission.ResetPassword;
import com.group18.asdc.service.EmailService;
import com.group18.asdc.service.ResetPasswordService;
import com.group18.asdc.service.UserService;

@Controller
public class LoginController {

	private static final UserService userService = SystemConfig.getSingletonInstance().getServiceAbstractFactory()
			.getUserService(SystemConfig.getSingletonInstance().getUtilAbstractFactory().getQueryVariableToArrayList());
	private static final EmailService emailService = SystemConfig.getSingletonInstance().getServiceAbstractFactory()
			.getEmailService();
	private static final ResetPasswordService resetPasswordService = SystemConfig.getSingletonInstance()
			.getServiceAbstractFactory().getResetPasswordService();

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
		User userObj = SystemConfig.getSingletonInstance().getModelAbstractFactory().getUser();
		userService.loadUserWithBannerId(bannerId, userObj);
		if (null == userObj.getEmail() || userObj.getEmail().isEmpty()) {
			model.addAttribute("BANNER_ID_NOT_EXIST", Boolean.TRUE);
			return "forgot-password.html";
		} else {
			String genPassword = SystemConfig.getSingletonInstance().getUtilAbstractFactory().getRandomStringGenerator()
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
		HashMap resultMap = new HashMap<>();
		String reason = null;
		if (resetForm.getgeneratedPassword().equals(session.getAttribute("RESET_PASSWORD"))) {
			if (resetForm.getnewPassword().equals(resetForm.getconfirmNewPassword())) {

				resultMap = resetPasswordService.resetPassword(userService, resetForm.getbannerId(),
						resetForm.getconfirmNewPassword(),
						SystemConfig.getSingletonInstance().getServiceAbstractFactory()
								.getPasswordHistoryService(SystemConfig.getSingletonInstance().getUtilAbstractFactory()
										.getQueryVariableToArrayList()),
						SystemConfig.getSingletonInstance().getBasePasswordPolicyManager(),
						SystemConfig.getSingletonInstance().getPasswordPolicyFactory(),
						SystemConfig.getSingletonInstance().getSecurityAbstractFactory().getPasswordEncryption());
				isError = (Boolean) resultMap.get("IS_ERROR");
				reason = (String) resultMap.get("REASON");
			} else {
				reason = "New password and confirm password does not match";
				isError = Boolean.TRUE;
			}
		} else {
			reason = "Password sent in mail does not match";
			isError = Boolean.TRUE;
		}
		if (isError) {
			model.addAttribute("resetForm", new ResetPassword(resetForm.getbannerId()));
			model.addAttribute("isError", Boolean.TRUE);
			model.addAttribute("sentEmail", resultMap.get("USER_EMAIL"));
			model.addAttribute("reason", reason);
			return "resetPassword";
		} else {
			return "redirect:" + redirectURL;
		}
	}

	@GetMapping("/error")
	public String getErrorPage() {
		return "error";
	}

}