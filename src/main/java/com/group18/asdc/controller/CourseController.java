package com.group18.asdc.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.User;
import com.group18.asdc.service.CourseDetailsService;
import com.group18.asdc.service.UserService;

@Controller
public class CourseController {
	
	private Logger log=Logger.getLogger(CourseController.class.getName());

	/*
	 * user home end point directs all the user except admin to the page where list
	 * of all courses are present
	 */
	@GetMapping("/userhome")
	public String getHomePage(Model theModel) {

		CourseDetailsService courseDetailsService=SystemConfig.getSingletonInstance().getTheCourseDetailsService();
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

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String bannerid;
		if (principal instanceof UserDetails) {
			bannerid = ((UserDetails) principal).getUsername();
			//System.out.println(((UserDetails) principal).getAuthorities());
		} else {
			bannerid = principal.toString();
			//System.out.println(principal.);
		}

		//String bannerid="B00896315";
		UserService userService=SystemConfig.getSingletonInstance().getTheUserService();
		CourseDetailsService courseDetailsService=SystemConfig.getSingletonInstance().getTheCourseDetailsService();
		User user=userService.getUserById(bannerid);
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

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String bannerid;
		if (principal instanceof UserDetails) {
			bannerid = ((UserDetails) principal).getUsername();
		} else {
			bannerid = principal.toString();
		}
		UserService userService=SystemConfig.getSingletonInstance().getTheUserService();
		CourseDetailsService courseDetailsService=SystemConfig.getSingletonInstance().getTheCourseDetailsService();
		User user=userService.getUserById(bannerid);
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
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String bannerid;
		if (principal instanceof UserDetails) {
			bannerid = ((UserDetails) principal).getUsername();
		} else {
			bannerid = principal.toString();
		}

		UserService userService=SystemConfig.getSingletonInstance().getTheUserService();
		CourseDetailsService courseDetailsService=SystemConfig.getSingletonInstance().getTheCourseDetailsService();
		User user=userService.getUserById(bannerid);
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

	/*
	 * Below endpoint allocates particulat user as TA
	 */
	@RequestMapping(value = "/allocateTA", method = RequestMethod.POST)
	public String allocateTA(HttpServletRequest request, Model theModel) {

		String bannerId = request.getParameter("TA");
		String courseId = request.getParameter("courseid");
		String courseName = request.getParameter("coursename");
		theModel.addAttribute("courseId", courseId);
		theModel.addAttribute("coursename", courseName);
		UserService userService=SystemConfig.getSingletonInstance().getTheUserService();
		CourseDetailsService courseDetailsService=SystemConfig.getSingletonInstance().getTheCourseDetailsService();
		User user = userService.getUserById(bannerId);
		if (user == null) {
			log.info("User doesnt exists or given id is invalid");
			theModel.addAttribute("result", "user not exists");
			return "instrcutorcoursehome";
		} else {
			boolean isAloocated=courseDetailsService.allocateTa(Integer.parseInt(courseId), userService.getUserById(bannerId));
			if(!isAloocated) {
				log.info("User is already realted to the course i.e user might be already a instructor or TA or Student for the course");
				theModel.addAttribute("result", "User is already realted to this course");
			}
			else {
				log.info("User has been allocated as TA role for the course");
				theModel.addAttribute("result", "TA Allocated");	
			}
			
			return "instrcutorcoursehome";
		}

	}

	/*
	 * Below endpoint uploads the student csv file and enroll them in particular
	 * course
	 */

	@RequestMapping(value = "/uploadstudents", method = RequestMethod.POST)
	public String uploadStudentsToCourse(@RequestParam(name = "file") MultipartFile file, Model theModel,
			HttpServletRequest request) {

		String courseId = request.getParameter("courseid");
		String courseName = request.getParameter("coursename");
		System.out.println("The Course id is "+courseId);
		theModel.addAttribute("courseId", courseId);
		theModel.addAttribute("coursename", courseName);
		//System.out.println("Course id: "+courseId);
		CourseDetailsService courseDetailsService=SystemConfig.getSingletonInstance().getTheCourseDetailsService();
		if(courseId.length()==0) {
			log.info("Error in loading file !! user will be prompted to upload file again");
			theModel.addAttribute("resultEnrolling", "Error in loading file !! please try again");
		}else {
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

							if (!bannerId.matches("B00(.*)") || bannerId.length() != 9 || !email.matches("(.*)@dal.ca")) {
								inValidUsers.add(user);

							} else {

								user.setFirstName(firstName);
								user.setLastName(lastName);
								user.setBannerId(bannerId);
								user.setEmail(email);
								validUsers.add(user);
							}

						} else {
							log.info("Rows which has invalid details are ignored");
							theModel.addAttribute("fileDetailsErrors", "Rows which has invalid details are ignored");
						}

					}
					
					if (validUsers.size() > 0) {

						boolean status = courseDetailsService.enrollStuentsIntoCourse(validUsers, Integer.parseInt(courseId));
						System.out.println("Student enrolled :" + status);
						if (status) {
							log.info("All the student enrolled in the course");
							theModel.addAttribute("resultEnrolling", "All Students enrolled");
						}
						else {
							log.info("Students has been enrolled to course and Users who are already related to course are ignored");
							theModel.addAttribute("resultEnrolling", "Success!! Users who are already related to course are ignored");
						}
					}
				
					br.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}

			}
		}
		return "instrcutorcoursehome";
	}

}
