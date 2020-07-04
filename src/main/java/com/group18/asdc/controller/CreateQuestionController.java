package com.group18.asdc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.entities.BasicQuestionData;
import com.group18.asdc.entities.MultipleChoiceQuestion;
import com.group18.asdc.entities.Option;
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

	@ModelAttribute("multiplequestion")
	public MultipleChoiceQuestion setMultipleChoiceQuestion() {
		return new MultipleChoiceQuestion();
	}

	@GetMapping("/getCreateQuestionHome")
	public String getcreateQuestionPage(Model model) {
		return "QuestionCreateHome";
	}

	@PostMapping("/getQuestionConfirm")
	public String getQuestionConfirmPage(@ModelAttribute("question") BasicQuestionData basicQuestionData, Model model) {
		log.info("confirming questions based on type");
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

	@RequestMapping(value = "/createNumericOrTextQuestion", method = RequestMethod.POST)
	public String createNumericOrQuestion(@ModelAttribute("question") BasicQuestionData basicQuestionData, Model model,
			RedirectAttributes theRedirectAttributes) {
		log.info("creating Numeric question");
		CreateQuestionService theCreateQuestionService = SystemConfig.getSingletonInstance()
				.getTheCreateQuestionService();
		boolean isQuestionCreated = theCreateQuestionService.createNumericOrTextQuestion(basicQuestionData);
		if (isQuestionCreated) {
			log.info("Numeric or text question created");
			return "QuestionCreateSuccess";
		} else {
			log.info("Error creating numeric or text question");
			return "error";
		}
	}

	@RequestMapping(value = "/createMultipleChoiceQuestion", method = RequestMethod.POST)
	public String createMultipleChoiceQuestion(@ModelAttribute("question") BasicQuestionData theBasicQuestionData,
			HttpServletRequest request, Model model, RedirectAttributes theRedirectAttributes) {
		CreateQuestionService theCreateQuestionService = SystemConfig.getSingletonInstance()
				.getTheCreateQuestionService();
		MultipleChoiceQuestion theMultipleChoiceQuestion = new MultipleChoiceQuestion();
		theMultipleChoiceQuestion.setQuestionTitle(theBasicQuestionData.getQuestionTitle());
		theMultipleChoiceQuestion.setQuestionText(theBasicQuestionData.getQuestionText());
		theMultipleChoiceQuestion.setQuestionType(theBasicQuestionData.getQuestionType());
		List<Option> optionList = new ArrayList<Option>();
		String displayOption = null;
		String storedOption = null;
		Option theOption = null;
		int iterativeNumber = 1;
		while (true) {
			theOption = new Option();
			displayOption = request.getParameter("optiontext-" + iterativeNumber + "");
			storedOption = request.getParameter("optionstored-" + iterativeNumber + "");
			if ((null == displayOption) || (null == storedOption)) {
				break;
			}
			if (displayOption.length() > 0) {
				theOption.setDisplayText(displayOption);
				theOption.setStoredData(Integer.parseInt(storedOption));
				optionList.add(theOption);
			}
			iterativeNumber++;
		}
		if (optionList.size() == 0) {
			model.addAttribute("BasicQuestion", theBasicQuestionData);
			model.addAttribute("error", "Enter Options to proceed");
			return "MultipleChoiceQuestion";
		} else {
			theMultipleChoiceQuestion.setOptionList(optionList);
		}
		boolean isQuestionCreated = theCreateQuestionService.createMultipleQuestion(theMultipleChoiceQuestion);
		if (isQuestionCreated) {
			log.info("Created multiple choice questions success");
			return "QuestionCreateSuccess";
		} else {
			log.info("Error in Created multiple choice questions");
			return "error";
		}
	}
}
