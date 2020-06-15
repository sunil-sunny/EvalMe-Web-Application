package com.group18.asdc.controller;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.entities.BasicQuestionData;
import com.group18.asdc.entities.FreeTextQuestion;
import com.group18.asdc.entities.NumericQuestion;
import com.group18.asdc.entities.QuestionType;
import com.group18.asdc.service.CreateQuestionService;

@RequestMapping("/questionpage")
@Controller
public class CreateQuestionController {

	private Logger log = Logger.getLogger(CreateQuestionController.class.getName());

	@ModelAttribute("question")
	public BasicQuestionData setBasicQuestionBean() {
		return new BasicQuestionData();
	}

	@ModelAttribute("nquestion")
	public NumericQuestion setNumericQuestion() {
		return new NumericQuestion();
	}

	@ModelAttribute("freetextquestion")
	public FreeTextQuestion setFreeTextQuestion() {

		return new FreeTextQuestion();
	}

	@GetMapping("/getHome")
	public String getCourseQuestionPage() {
		return "QuestionPageHome";
	}

	@GetMapping("/getCreateQuestionHome")
	public String getcreateQuestionPage(Model model) {
		return "QuestionCreateHome";
	}

	@PostMapping("/getQuestionConfirm")
	public String getQuestionConfirmPage(@ModelAttribute("question") BasicQuestionData basicQuestionData, Model model) {

		log.info("confirming questions based on type");

		System.out.println(basicQuestionData.getQuestionType());
		model.addAttribute("BasicQuestion", basicQuestionData);

		if (basicQuestionData.getQuestionType().equalsIgnoreCase(QuestionType.numericType)
				|| basicQuestionData.getQuestionType().equalsIgnoreCase(QuestionType.freeText)) {
			return "NumericOrTextQuestion";
		}
		if (basicQuestionData.getQuestionType().equalsIgnoreCase(QuestionType.multipleChooseMore)
				|| basicQuestionData.getQuestionType().equalsIgnoreCase(QuestionType.multipleChooseOne)) {
			return "MultipleChoiceQuestion";
		}

		return "error";
	}

	@RequestMapping(value = "/createNumericQuestion", method = RequestMethod.POST)
	public String createNumericQuestion(@ModelAttribute("question") BasicQuestionData basicQuestionData, Model model) {

		log.info("creating Numeric question");
		
		CreateQuestionService theCreateQuestionService=SystemConfig.getSingletonInstance().getTheCreateQuestionService();
		boolean isQuestionCreated=theCreateQuestionService.createNumericQuestion(basicQuestionData);
		if(isQuestionCreated) {
			return "/QuestionCreateSuccess";
		}
		else {
			return "/error";
		}
	}

	@RequestMapping(value = "/createFreeTextQuestion", method = RequestMethod.POST)
	public String createFreeTextQuestion(@ModelAttribute("question") BasicQuestionData basicQuestionData, Model model) {
		CreateQuestionService theCreateQuestionService=SystemConfig.getSingletonInstance().getTheCreateQuestionService();
		boolean isQuestionCreated=theCreateQuestionService.createFreeTextQuestion(basicQuestionData);
		if(isQuestionCreated) {
			return "/QuestionCreateSuccess";
		}
		else {
			return "/error";
		}
	}

}
