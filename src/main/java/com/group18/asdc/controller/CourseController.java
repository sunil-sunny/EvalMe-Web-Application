package com.group18.asdc.controller;

import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.group18.asdc.CourseConfig;
import com.group18.asdc.ProfileManagementConfig;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;
import com.group18.asdc.service.CourseDetailsService;
import com.group18.asdc.service.UserService;

@Controller
public class CourseController {

	private Logger log = Logger.getLogger(CourseController.class.getName());

	@GetMapping("/userhome")
	public String getHomePage(Model theModel) {

		log.info("in course controller");
		CourseDetailsService courseDetailsService = CourseConfig.getSingletonInstance().getTheCourseDetailsService();
		List<Course> coursesList = courseDetailsService.getAllCourses();
		theModel.addAttribute("coursesList", coursesList);
		return "guesthome";
	}

	@RequestMapping(value = "/enrolledcourses")
	public String getEnrolledCourses(Model theModel) {

		UserService userService = ProfileManagementConfig.getSingletonInstance().getTheUserService();
		CourseDetailsService courseDetailsService = CourseConfig.getSingletonInstance().getTheCourseDetailsService();
		User user = userService.getCurrentUser();
		if (null == user) {
			return "error";
		} else {
			List<Course> coursesList = courseDetailsService.getCoursesWhereUserIsStudent(user);
			theModel.addAttribute("coursesList", coursesList);
			return "enrolledcourses";
		}
	}

	@GetMapping("/tacourses")
	public String getTACourses(Model theModel) {
		UserService userService = ProfileManagementConfig.getSingletonInstance().getTheUserService();
		CourseDetailsService courseDetailsService = CourseConfig.getSingletonInstance().getTheCourseDetailsService();
		User user = userService.getCurrentUser();
		if (null == user) {
			return "error";
		} else {
			List<Course> coursesList = courseDetailsService.getCoursesWhereUserIsTA(user);
			theModel.addAttribute("coursesList", coursesList);
			return "tacourses";
		}
	}

	@RequestMapping(value = "/instructedcourses", method = RequestMethod.GET)
	public String getInstructedCourses(Model theModel) {
		UserService userService = ProfileManagementConfig.getSingletonInstance().getTheUserService();
		CourseDetailsService courseDetailsService = CourseConfig.getSingletonInstance().getTheCourseDetailsService();
		User user = userService.getCurrentUser();
		if (null == user) {
			return "error";
		} else {
			List<Course> coursesList = courseDetailsService.getCoursesWhereUserIsInstrcutor(user);
			theModel.addAttribute("coursesList", coursesList);
			return "teachingcourses";
		}
	}

	@RequestMapping(value = "/coursepage", method = RequestMethod.GET)
	public String getCoursePage(Model theModel, HttpServletRequest request) {
		String courseId = request.getParameter("id");
		String courseName = request.getParameter("name");
		theModel.addAttribute("courseId", courseId);
		theModel.addAttribute("coursename", courseName);
		return "studentcoursehome";
	}

	@RequestMapping(value = "/coursepageInstrcutor", method = RequestMethod.GET)
	public String getCoursePageForInstrcutorOrTA(Model theModel, HttpServletRequest request) {
		String courseId = request.getParameter("id");
		String courseName = request.getParameter("name");
		theModel.addAttribute("courseId", courseId);
		theModel.addAttribute("coursename", courseName);
		return "instrcutorcoursehome";
	}
}