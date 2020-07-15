package com.group18.asdc.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.entities.QuestionMetaData;
import com.group18.asdc.service.ViewQuestionsService;

@RequestMapping("/viewQuestions")
@Controller
public class ViewQuestionsController {

	private final static ViewQuestionsService theViewQuestionsService = SystemConfig.getSingletonInstance()
			.getServiceAbstractFactory().getViewQuestionsService();

	@GetMapping("/getHome")
	public String getAllQuestions(Model theModel) {
		List<QuestionMetaData> allQuestions = theViewQuestionsService.getAllQuestions();
		if (null == allQuestions) {
			return "error";
		} else {
			theModel.addAttribute("allquestions", allQuestions);
			return "QuestionPageHome";
		}
	}

	@GetMapping("/sortByTitle")
	public String getAllQuestionSortedByTitle(Model theModel) {
		
		List<QuestionMetaData> allQuestionsSortByTitle = theViewQuestionsService.getAllQuestionsSortByTitle();
		if (null == allQuestionsSortByTitle) {
			return "error";
		} else {
			theModel.addAttribute("allquestions", allQuestionsSortByTitle);
			return "QuestionPageHome";
		}
	}

	@GetMapping("/sortByDate")
	public String getAllQuestionsSortedByDate(Model theModel) {
		
		List<QuestionMetaData> allQuestionsSortByDate = theViewQuestionsService.getAllQuestionsSortByDate();
		if (null == allQuestionsSortByDate) {
			return "error";
		} else {
			theModel.addAttribute("allquestions", allQuestionsSortByDate);
			return "QuestionPageHome";
		}
	}
}