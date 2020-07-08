package com.group18.asdc.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.group18.asdc.QuestionManagerConfig;
import com.group18.asdc.SurveyConfig;
import com.group18.asdc.SystemConfig;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.QuestionMetaData;
import com.group18.asdc.entities.SurveyMetaData;
import com.group18.asdc.errorhandling.QuestionExitsException;
import com.group18.asdc.errorhandling.SavingSurveyException;
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
			SurveyMetaData surveyMetaData = theSurveyService.getSavedSurvey(theCourse);
			theModel.addAttribute("survey", surveyMetaData);
			theModel.addAttribute("existingQuestions", theViewQuestionsService.getAllQuestions());
			return "createSurvey";
		}
	}

	@GetMapping("/removeQuestion/{questionid}")
	public String removeQuestionFromList(@PathVariable("questionid") int questionId, Model theModel) {

		ViewQuestionsService theViewQuestionsService = QuestionManagerConfig.getSingletonInstance()
				.getTheViewQuestionsService();
		SurveyService theSurveyService = SurveyConfig.getSingletonInstance().getTheSurveyService();
		QuestionMetaData theQuestionMetaData = theViewQuestionsService.getQuestionById(questionId);
		if (null == theQuestionMetaData) {
			return "error";
		} else {
			boolean isDeleted = theSurveyService.removeQuestionFromSurvey(theQuestionMetaData);
			if (isDeleted) {
				SurveyMetaData surveyData = theSurveyService.getCurrentSurvey();
				theModel.addAttribute("survey", surveyData);
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
		SurveyService theSurveyService = SurveyConfig.getSingletonInstance().getTheSurveyService();
		ViewQuestionsService theViewQuestionsService = QuestionManagerConfig.getSingletonInstance()
				.getTheViewQuestionsService();
		try {
			QuestionMetaData theQuestionMetaData = theViewQuestionsService
					.getQuestionById(Integer.parseInt(questionId));
			if (null == theQuestionMetaData) {
				return "error";
			} else {
				boolean isAdded;
				try {
					isAdded = theSurveyService.addQuestionToSurvey(theQuestionMetaData);
					if (isAdded) {
						SurveyMetaData surveyData = theSurveyService.getCurrentSurvey();
						theModel.addAttribute("survey", surveyData);
						theModel.addAttribute("existingQuestions", theViewQuestionsService.getAllQuestions());
						return "createSurvey";
					} else {
						return "error";
					}
				} catch (QuestionExitsException e) {
					SurveyMetaData surveyData = theSurveyService.getCurrentSurvey();
					theModel.addAttribute("message", e.getMessage());
					theModel.addAttribute("survey", surveyData);
					theModel.addAttribute("existingQuestions", theViewQuestionsService.getAllQuestions());
					return "createSurvey";
				}
			}
		} catch (NumberFormatException e) {

			SurveyMetaData surveyData = theSurveyService.getCurrentSurvey();
			theModel.addAttribute("message", "Select Question to proceed");
			theModel.addAttribute("survey", surveyData);
			theModel.addAttribute("existingQuestions", theViewQuestionsService.getAllQuestions());
			return "createSurvey";
		}
	}

	@PostMapping("/saveSurvey")
	public String saveSurvey(@ModelAttribute("survey") SurveyMetaData surveyData, Model theModel) {

		SurveyService surveyService = SurveyConfig.getSingletonInstance().getTheSurveyService();
		ViewQuestionsService theViewQuestionsService = QuestionManagerConfig.getSingletonInstance()
				.getTheViewQuestionsService();
		boolean isSaved;
		try {
			isSaved = surveyService.saveSurvey(surveyData);
			if (isSaved) {
				theModel.addAttribute("success", "Survey Saved Success");
				SurveyMetaData savedSurvey = surveyService.getSavedSurvey(surveyData.getTheCourse());
				theModel.addAttribute("survey", savedSurvey);
				theModel.addAttribute("existingQuestions", theViewQuestionsService.getAllQuestions());
			}
			return "createSurvey";
		} catch (SavingSurveyException e) {
			return "error";
		}
	}
}
