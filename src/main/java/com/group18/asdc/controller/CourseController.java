package com.group18.asdc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.SurveyMetaData;
import com.group18.asdc.entities.SurveyQuestion;
import com.group18.asdc.entities.User;
import com.group18.asdc.service.CourseDetailsService;
import com.group18.asdc.service.SurveyService;
import com.group18.asdc.service.UserService;

@Controller
public class CourseController {

	private static final UserService userService = SystemConfig.getSingletonInstance().getServiceAbstractFactory()
			.getUserService(SystemConfig.getSingletonInstance().getUtilAbstractFactory().getQueryVariableToArrayList());
	private static final CourseDetailsService courseDetailsService = SystemConfig.getSingletonInstance()
			.getServiceAbstractFactory().getCourseDetailsService();
	private static final SurveyService surveyService = SystemConfig.getSingletonInstance().getServiceAbstractFactory()
			.getSurveyService();

	@GetMapping("/userhome")
	public String getHomePage(Model theModel) {
		List<Course> coursesList = courseDetailsService.getAllCourses();
		theModel.addAttribute("coursesList", coursesList);
		return "guesthome";
	}

	@RequestMapping(value = "/enrolledcourses")
	public String getEnrolledCourses(Model theModel) {

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

		Course course = SystemConfig.getSingletonInstance().getModelAbstractFactory().getCourse();
		SurveyMetaData surveyMetaData = SystemConfig.getSingletonInstance().getModelAbstractFactory()
				.getSurveyMetaData();
		List<SurveyQuestion> questionList = new ArrayList<SurveyQuestion>();
		String courseId = request.getParameter("id");
		int courseID = Integer.parseInt(courseId);
		String courseName = request.getParameter("name");
		theModel.addAttribute("courseId", courseId);
		theModel.addAttribute("coursename", courseName);
		course.setCourseId(courseID);
		course.setCourseName(courseName);
		boolean isSurveyPublished = surveyService
				.isSurveyPublishedForCourse(courseDetailsService.getCourseById(courseID));
		if (isSurveyPublished) {
			surveyMetaData = surveyService.getSavedSurvey(course);
			questionList = surveyMetaData.getSurveyQuestions();
			if (null == questionList) {
				return "error";
			} else {
				theModel.addAttribute("questionlist", questionList);
				return "studentcoursehomesurveypublished";
			}
		} else {
			return "studentcoursehomesurveynotpublished";
		}
	}

	@RequestMapping(value = "/coursepage", method = RequestMethod.POST)
	public String submitSurveyAnswers(Model model) {
		return "surveyanswersubmitresult";
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