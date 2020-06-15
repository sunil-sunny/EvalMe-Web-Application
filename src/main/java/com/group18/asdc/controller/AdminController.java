package com.group18.asdc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.CourseAdmin;
import com.group18.asdc.service.AdminService;

@Controller
public class AdminController {

	// ADMIN DASHBOARD

	@GetMapping("/adminhome")
	public String adminHome() {
		return "adminhome";
	}

	// ADD COURSE

	@GetMapping("/adminadd")
	public String adminAddDisplay(Model model) {
		model.addAttribute("courseadmin", new CourseAdmin());
		return "adminaddcourse";
	}

	@PostMapping("/adminadd")
	public String adminAddForm(@ModelAttribute("courseadmin") CourseAdmin courseadmin, BindingResult bindingresult) {

		// If any errors occur because of type-mismatch or any other reason, return the
		// same view to add courses
		 AdminService theAdminService=SystemConfig.getSingletonInstance().getTheAdminService();
		if (bindingresult.hasErrors()) {
			return "redirect:/adminadd?error";
		}

		System.out.println(" id is : " + courseadmin.getCourseId());
		System.out.println(("name is :" + courseadmin.getCourseName()));
		System.out.println("instructor id is: " + courseadmin.getInstructorId());
		// send CourseAdmin object to adminservice for processing user input

		// store the string returned in result, to identify the error and display
		// appropriate alert message
		String result = theAdminService.createCourse(courseadmin);

		// check type of error string returned and return view accordingly

		if (result == "invalidid") {
			return "redirect:/adminadd?invalidid";
		} else if (result == "shortname") {
			return "redirect:/adminadd?shortname";
		} else if (result == "invalidinstid") {
			return "redirect:/adminadd?invalidinstid";
		} else if (result == "idexists") {
			return "redirect:/adminaddcourse?idexists";
		} else if (result == "nameexists") {
			return "redirect:/adminaddcourse?nameexists";
		} else if (result == "invalidinst") {
			return "redirect:/adminaddcourse?invalidinst";
		} else if (result == "coursenotcreated") {
			return "redirect:/adminaddcourse?coursenotcreated";
		} else {
			return "adminaddcourseresult";
		}
	}

	// DELETE COURSE

	@GetMapping("/admindelete")
	public String adminDeleteDisplay(Model model) {
		model.addAttribute("course", new Course());
		return "admindeletecourse";
	}

	@PostMapping("/admindelete")
	public String adminDeleteForm(@ModelAttribute("course") Course course, BindingResult bindingresult,
			Model theModel) {

		 AdminService theAdminService=SystemConfig.getSingletonInstance().getTheAdminService();
		// If any errors occur because of type-mismatch or any other reason, return the
		// same view to delete courses
		if (bindingresult.hasErrors()) {
			return "redirect:/admindelete?error";
		}

		// store the string returned in result, to identify the error and display
		// appropriate alert message
		String result = theAdminService.deleteCourse(course.getCourseId());

		System.out.println("result from co   " + result);
		theModel.addAttribute("deleteresult", result);

		return "admindeletecourseresult";
	}
}
