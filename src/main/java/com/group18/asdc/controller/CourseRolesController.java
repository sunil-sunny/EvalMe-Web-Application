package com.group18.asdc.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.entities.User;
import com.group18.asdc.service.CourseRolesService;
import com.group18.asdc.service.UserService;
import com.group18.asdc.util.ConstantStringUtil;

@Controller
public class CourseRolesController {

	private Logger log = Logger.getLogger(CourseRolesController.class.getName());

	@RequestMapping(value = "/allocateTA", method = RequestMethod.POST)
	public String allocateTA(HttpServletRequest request, Model theModel) {
		String bannerId = request.getParameter("TA");
		String courseId = request.getParameter("courseid");
		String courseName = request.getParameter("coursename");
		theModel.addAttribute("courseId", courseId);
		theModel.addAttribute("coursename", courseName);
		UserService userService = SystemConfig.getSingletonInstance().getTheUserService();
		CourseRolesService courseRolesService = SystemConfig.getSingletonInstance().getTheCourseRolesService();
		User user = userService.getUserById(bannerId);
		if (null == user) {
			log.info("User doesnt exists or given id is invalid");
			theModel.addAttribute("result", "User doesnt exists or given id is invalid");
			return "instrcutorcoursehome";
		} else {
			boolean isAllocated = courseRolesService.allocateTa(Integer.parseInt(courseId),
					userService.getUserById(bannerId));
			if (isAllocated) {
				log.info("User has been allocated as TA role for the course");
				theModel.addAttribute("result", "TA Allocated");
			} else {
				log.info("User is already realted to the course "
						+ "i.e user might be already a instructor or TA or Student for the course");
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
		CourseRolesService courseRolesService = SystemConfig.getSingletonInstance().getTheCourseRolesService();
		if (0 == courseId.length()) {
			log.info("Error in loading file !! user will be prompted to upload file again");
			theModel.addAttribute("resultEnrolling", "Error in loading file !! please try again");
		} else {
			if (file.isEmpty()) {
				log.info("The uploaded file is empty and user will be propmted to upload again");
				theModel.addAttribute("resultEnrolling", "Upload file to continue");
			} else {
				try {
					byte[] bytes = file.getBytes();
					ByteArrayInputStream inputFilestream = new ByteArrayInputStream(bytes);
					BufferedReader br = new BufferedReader(new InputStreamReader(inputFilestream));
					String line = "";
					br.readLine();
					List<User> validUsers = new ArrayList<User>();
					List<User> inValidUsers = new ArrayList<User>();
					User user = null;
					while ((line = br.readLine()) != null) {
						user = new User();
						String userDetails[] = line.split(",");
						if (userDetails.length == 4) {
							String firstName = userDetails[0];
							String lastName = userDetails[1];
							String bannerId = userDetails[2];
							String email = userDetails[3];
							if (!bannerId.matches(ConstantStringUtil.getBanneridpatterncheck())
									|| bannerId.length() != 9
									|| !email.matches(ConstantStringUtil.getEmailpatterncheck())) {
								inValidUsers.add(user);
							} else {
								user.setFirstName(firstName);
								user.setLastName(lastName);
								user.setBannerId(bannerId);
								user.setEmail(email);
								validUsers.add(user);
							}
						} else {
							log.info("Rows which has invalid details are ignored " + "while reading the student list");
							theModel.addAttribute("fileDetailsErrors", "Rows which has invalid details are ignored");
						}
					}
					if (validUsers.size() > 0) {
						boolean status = courseRolesService.enrollStuentsIntoCourse(validUsers,
								Integer.parseInt(courseId));
						if (status) {
							log.info("All the student enrolled in the course");
							theModel.addAttribute("resultEnrolling", "All Students enrolled");
						} else {
							log.info("Students has been enrolled to course and "
									+ "Users who are already related to course are ignored");
							theModel.addAttribute("resultEnrolling",
									"Success!! " + "Users who are already related to course are ignored");
						}
					}
					br.close();
				} catch (IOException e) {
					log.info(
							"IO Exception while reading the multi part file file while enrolling students in particular course");
				}
			}
		}
		return "instrcutorcoursehome";
	}
}