package com.group18.asdc.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.group18.asdc.SystemConfig;
import com.group18.asdc.entities.User;
import com.group18.asdc.errorhandling.EnrollingStudentException;
import com.group18.asdc.errorhandling.FileProcessingException;
import com.group18.asdc.service.CourseRolesService;
import com.group18.asdc.service.UserService;

@Controller
public class CourseRolesController {

	private static final UserService userService = SystemConfig.getSingletonInstance().getServiceAbstractFactory()
			.getUserService(SystemConfig.getSingletonInstance().getUtilAbstractFactory().getQueryVariableToArrayList());
	private static final CourseRolesService courseRolesService = SystemConfig.getSingletonInstance()
			.getServiceAbstractFactory().getCourseRolesService();

	@RequestMapping(value = "/allocateTA", method = RequestMethod.POST)
	public String allocateTA(HttpServletRequest request, Model theModel) {
		String bannerId = request.getParameter("TA");
		String courseId = request.getParameter("courseid");
		String courseName = request.getParameter("coursename");
		theModel.addAttribute("courseId", courseId);
		theModel.addAttribute("coursename", courseName);
		User user = userService.getUserById(bannerId);
		if (null == user) {
			theModel.addAttribute("result", "User doesnt exists or given id is invalid");
			return "instrcutorcoursehome";
		} else {
			boolean isAllocated = courseRolesService.allocateTa(Integer.parseInt(courseId),
					userService.getUserById(bannerId));
			if (isAllocated) {
				theModel.addAttribute("result", "TA Allocated");
			} else {
				theModel.addAttribute("result", "User is already a part of this course");
			}
			return "instrcutorcoursehome";
		}
	}

	@RequestMapping(value = "/uploadstudents", method = RequestMethod.POST)
	public String uploadStudentsToCourse(@RequestParam(name = "file") MultipartFile file, Model theModel,
			HttpServletRequest request) {
		String courseId = request.getParameter("courseid");
		String courseName = request.getParameter("coursename");
		theModel.addAttribute("courseId", courseId);
		theModel.addAttribute("coursename", courseName);
		List<User> validUsers = null;
		try {
			validUsers = courseRolesService.extraxtValidStudentsFromFile(file);
			if (validUsers.size() > 0) {
				try {
					boolean status = courseRolesService.enrollStuentsIntoCourse(validUsers, Integer.parseInt(courseId));
					if (status) {
						theModel.addAttribute("resultEnrolling", "All Students enrolled");
					} else {
						theModel.addAttribute("resultEnrolling",
								"Students are not Enrolled into course, Kinldy try again");
					}
				} catch (EnrollingStudentException e) {
					theModel.addAttribute("resultEnrolling", e.getMessage());
				}

			} else {
				theModel.addAttribute("resultEnrolling",
						"Students in the file has invalid details, Kindly Correct them and try again");
			}
		} catch (FileProcessingException e) {
			theModel.addAttribute("resultEnrolling", e.getMessage());
		}

		return "instrcutorcoursehome";
	}
}