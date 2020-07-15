package com.group18.asdc.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.group18.asdc.SystemConfig;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.QuestionMetaData;
import com.group18.asdc.entities.SurveyMetaData;
import com.group18.asdc.errorhandling.PublishSurveyException;
import com.group18.asdc.errorhandling.QuestionExitsException;
import com.group18.asdc.errorhandling.SavingSurveyException;
import com.group18.asdc.service.CourseDetailsService;
import com.group18.asdc.service.SurveyService;
import com.group18.asdc.service.ViewQuestionsService;

@Controller
@RequestMapping("/survey")
public class SurveyController {

	private final static CourseDetailsService theCourseDetailsService = SystemConfig.getSingletonInstance()
			.getServiceAbstractFactory().getCourseDetailsService();
	private final static ViewQuestionsService theViewQuestionsService = SystemConfig.getSingletonInstance()
			.getServiceAbstractFactory().getViewQuestionsService();
	private final static SurveyService theSurveyService = SystemConfig.getSingletonInstance()
			.getServiceAbstractFactory().getSurveyService();

	@GetMapping("/getSurveyPage")
	public String getSurveyPage(Model theModel, HttpServletRequest theHttpServletRequest) {
		String courseId = theHttpServletRequest.getParameter("courseid");
		Course theCourse = theCourseDetailsService.getCourseById(Integer.parseInt(courseId));
		theModel.addAttribute("courseid", courseId);
		if (null == theCourse) {
			return "error";
		} else {
			SurveyMetaData surveyMetaData = theSurveyService.getSavedSurvey(theCourse);
			if (surveyMetaData.getSurveyQuestions().size() == 0) {
				theModel.addAttribute("survey", surveyMetaData);
				theModel.addAttribute("existingQuestions", theViewQuestionsService.getAllQuestions());
				theModel.addAttribute("message", "Survey is not yet created !! create survey");
			} else {
				theModel.addAttribute("survey", surveyMetaData);
				theModel.addAttribute("existingQuestions", theViewQuestionsService.getAllQuestions());
			}
			return "createSurvey";
		}
	}

	@GetMapping("/removeQuestion/{questionid}")
	public String removeQuestionFromList(@PathVariable("questionid") int questionId, Model theModel) {

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

		boolean isSaved;
		try {
			isSaved = theSurveyService.saveSurvey(surveyData);
			if (isSaved) {
				Course theCourse = theCourseDetailsService.getCourseById(surveyData.getTheCourse().getCourseId());
				theModel.addAttribute("success", "Survey Saved");
				SurveyMetaData savedSurvey = theSurveyService.getSavedSurvey(theCourse);
				theModel.addAttribute("survey", savedSurvey);
				theModel.addAttribute("existingQuestions", theViewQuestionsService.getAllQuestions());
			}
			return "createSurvey";
		} catch (SavingSurveyException e) {
			theModel.addAttribute("message", e.getMessage());
			SurveyMetaData savedSurvey = theSurveyService.getCurrentSurvey();
			theModel.addAttribute("survey", savedSurvey);
			theModel.addAttribute("existingQuestions", theViewQuestionsService.getAllQuestions());
			return "createSurvey";
		}
	}

	@GetMapping("/publishSurvey")
	public String publishSurvey(Model theModel) {

		try {
			boolean isPublished = theSurveyService.publishSurvey();
			if (isPublished) {
				SurveyMetaData savedSurvey = theSurveyService.getCurrentSurvey();
				theModel.addAttribute("survey", savedSurvey);
			} else {
				SurveyMetaData savedSurvey = theSurveyService.getCurrentSurvey();
				theModel.addAttribute("survey", savedSurvey);
				theModel.addAttribute("existingQuestions", theViewQuestionsService.getAllQuestions());
				theModel.addAttribute("message", "Issue while publishing survey !! Try again");
			}
			return "createSurvey";
		} catch (PublishSurveyException e) {
			SurveyMetaData savedSurvey = theSurveyService.getCurrentSurvey();
			theModel.addAttribute("survey", savedSurvey);
			theModel.addAttribute("existingQuestions", theViewQuestionsService.getAllQuestions());
			theModel.addAttribute("message", e.getMessage());
			return "createSurvey";
		}
	}
}
