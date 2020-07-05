package com.group18.asdc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group18.asdc.QuestionManagerConfig;
import com.group18.asdc.SurveyConfig;
import com.group18.asdc.SystemConfig;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.QuestionMetaData;
import com.group18.asdc.entities.SurveyQuestion;
import com.group18.asdc.errorhandling.QuestionExitsException;
import com.group18.asdc.service.CourseDetailsService;
import com.group18.asdc.service.SurveyService;
import com.group18.asdc.service.ViewQuestionsService;

@Controller
@RequestMapping("/survey")
public class SurveyController {

	@GetMapping("/getSurveyPage")
	public String getSurveyPage(Model theModel, HttpServletRequest theHttpServletRequest) {
		String courseId = theHttpServletRequest.getParameter("courseid");
		CourseDetailsService theCourseDetailsService = SystemConfig.getSingletonInstance().getTheCourseDetailsService();
		ViewQuestionsService theViewQuestionsService = QuestionManagerConfig.getSingletonInstance()
				.getTheViewQuestionsService();
		Course theCourse = theCourseDetailsService.getCourseById(Integer.parseInt(courseId));
		if (null == theCourse) {
			return "error";
		} else {
			SurveyService theSurveyService = SurveyConfig.getSingletonInstance().getTheSurveyService();
			List<SurveyQuestion> savedQuestions = theSurveyService.getAllSavedSurveyQuestions(theCourse);
			theModel.addAttribute("allquestions", savedQuestions);
			theModel.addAttribute("existingQuestions", theViewQuestionsService.getAllQuestions());
			return "createSurvey";
		}
	}

	@PostMapping("/removeQuestion")
	public String removeQuestionFromList(Model theModel, HttpServletRequest httpServletRequest) {

		String questionId = httpServletRequest.getParameter("questionid");
		ViewQuestionsService theViewQuestionsService = QuestionManagerConfig.getSingletonInstance()
				.getTheViewQuestionsService();
		SurveyService theSurveyService = SurveyConfig.getSingletonInstance().getTheSurveyService();
		QuestionMetaData theQuestionMetaData = theViewQuestionsService.getQuestionById(Integer.parseInt(questionId));
		if (null == theQuestionMetaData) {
			return "error";
		} else {
			boolean isDeleted = theSurveyService.removeQuestionFromSurvey(theQuestionMetaData);
			if (isDeleted) {
				List<SurveyQuestion> savedQuestions = theSurveyService.getCurrentListOfSurveyQuestions();
				theModel.addAttribute("allquestions", savedQuestions);
				theModel.addAttribute("existingQuestions", theViewQuestionsService.getAllQuestions());
				return "createSurvey";
			} else {
				return "error";
			}
		}
	}

	@PostMapping("/addQuestion")
	public String addQuestionToList(Model theModel, HttpServletRequest httpServletRequest) {

		String questionId = httpServletRequest.getParameter("selectedQuestion");
		System.out.println("id is "+questionId);
		ViewQuestionsService theViewQuestionsService = QuestionManagerConfig.getSingletonInstance()
				.getTheViewQuestionsService();
		SurveyService theSurveyService = SurveyConfig.getSingletonInstance().getTheSurveyService();
		QuestionMetaData theQuestionMetaData = theViewQuestionsService.getQuestionById(Integer.parseInt(questionId));
		if (null == theQuestionMetaData) {
			return "error";
		} else {
			boolean isAdded;
			try {
				isAdded = theSurveyService.addQuestionToSurvey(theQuestionMetaData);
				if (isAdded) {
					List<SurveyQuestion> savedQuestions = theSurveyService.getCurrentListOfSurveyQuestions();
					theModel.addAttribute("allquestions", savedQuestions);
					theModel.addAttribute("existingQuestions", theViewQuestionsService.getAllQuestions());
					return "createSurvey";
				} else {
					return "error";
				}
			} catch (QuestionExitsException e) {
				List<SurveyQuestion> savedQuestions = theSurveyService.getCurrentListOfSurveyQuestions();
				theModel.addAttribute("message", e.getMessage());
				theModel.addAttribute("allquestions", savedQuestions);
				theModel.addAttribute("existingQuestions", theViewQuestionsService.getAllQuestions());
				return "createSurvey";
			}
		}
	}
}
