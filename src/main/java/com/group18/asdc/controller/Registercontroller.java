package com.group18.asdc.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.group18.asdc.SystemConfig;
import com.group18.asdc.entities.Registerbean;
import com.group18.asdc.service.RegisterService;


@Controller
@RequestMapping("/registration")
public class Registercontroller {

	@ModelAttribute("user")
	public Registerbean bean() {
		return new Registerbean();
	}

	@GetMapping
	public String showRegistrationForm(Model model) {
		return "registration";
	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") Registerbean bean, BindingResult result) {
		
		RegisterService num=SystemConfig.getSingletonInstance().getTheRegisterservice();

		if (result.hasErrors()) {
			return "registration";
		}
		
		String s = num.registeruser(bean);
		if (s.equals("alreadycreated")) {
			System.out.println("already exists");
			return "redirect:/registration?alreadyregistered";
		} else if (s.equals("passwordmismatch")) {
			return "redirect:/registration?passwordmismatch";
		} else if (s.equals("invalidbannerid")) {
			return "redirect:/registration?invalidbannerid";
		} else if (s.equals("invalidbannerid2")) {
			return "redirect:/registration?invalidbannerid2";
		} else if (s.equals("alreadycreatedemail")) {
			return "redirect:/registration?alreadycreatedemail";
		} else if (s.equals("invalidemailid")) {
			return "redirect:/registration?invalidemailid";
		} else if (s.equals("shortpassword")) {
			return "redirect:/registration?shortpassword";
		}

		else {
			System.out.println("successfully created the acc" + s);

			return "redirect:/login?accountcreatedsuccessfully";
		}

	}
}
