package com.group18.asdc.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;
import com.group18.asdc.service.CourseDetailsService;
import com.group18.asdc.service.UserService;

@Controller
public class CourseController {

	private Logger log = Logger.getLogger(CourseController.class.getName());

	/*
	 * user home end point directs all the user except admin to the page where list
	 * of all courses are present
	 */
	@GetMapping("/userhome")
	public String getHomePage(Model theModel) {

		log.info("in course controller");
		CourseDetailsService courseDetailsService = SystemConfig.getSingletonInstance().getTheCourseDetailsService();
		List<Course> coursesList = courseDetailsService.getAllCourses();
		theModel.addAttribute("coursesList", coursesList);
		return "guesthome";
	}

	/*
	 * courpage redirects user to page which contain courses where he/she is
	 * enrolled as student
	 */

	@RequestMapping(value = "/enrolledcourses")
	public String getEnrolledCourses(Model theModel) {

		UserService userService = SystemConfig.getSingletonInstance().getTheUserService();
		CourseDetailsService courseDetailsService = SystemConfig.getSingletonInstance().getTheCourseDetailsService();
		User user = userService.getCurrentUser();
		List<Course> coursesList = courseDetailsService.getCoursesWhereUserIsStudent(user);
		theModel.addAttribute("coursesList", coursesList);
		return "enrolledcourses";
	}

	/*
	 * Below endpoint redirects users to page which contains courses where he/she is
	 * having a role as TA.
	 */

	@GetMapping("/tacourses")
	public String getTACourses(Model theModel) {
		UserService userService = SystemConfig.getSingletonInstance().getTheUserService();
		CourseDetailsService courseDetailsService = SystemConfig.getSingletonInstance().getTheCourseDetailsService();
		User user = userService.getCurrentUser();
		List<Course> coursesList = courseDetailsService.getCoursesWhereUserIsTA(user);
		theModel.addAttribute("coursesList", coursesList);
		return "tacourses";
	}

	/*
	 * Below endpoint redirects users to page which contains courses where he/she is
	 * having a role as Instructor
	 */

	@RequestMapping(value = "/instructedcourses", method = RequestMethod.GET)
	public String getInstructedCourses(Model theModel) {
		UserService userService = SystemConfig.getSingletonInstance().getTheUserService();
		CourseDetailsService courseDetailsService = SystemConfig.getSingletonInstance().getTheCourseDetailsService();
		User user = userService.getCurrentUser();
		List<Course> coursesList = courseDetailsService.getCoursesWhereUserIsInstrcutor(user);
		theModel.addAttribute("coursesList", coursesList);
		return "teachingcourses";
	}

	/*
	 * Below endpoint refers student version of course home page.
	 */

	@RequestMapping(value = "/coursepage", method = RequestMethod.GET)
	public String getCoursePage(Model theModel, HttpServletRequest request) {
		String courseId = request.getParameter("id");
		String courseName = request.getParameter("name");
		theModel.addAttribute("courseId", courseId);
		theModel.addAttribute("coursename", courseName);
		return "studentcoursehome";
	}

	/*
	 * Below endpoint refers TA or instructor version of course home page.
	 */
	@RequestMapping(value = "/coursepageInstrcutor", method = RequestMethod.GET)
	public String getCoursePageForInstrcutorOrTA(Model theModel, HttpServletRequest request) {
		String courseId = request.getParameter("id");
		String courseName = request.getParameter("name");
		theModel.addAttribute("courseId", courseId);
		theModel.addAttribute("coursename", courseName);
		return "instrcutorcoursehome";
	}
}
