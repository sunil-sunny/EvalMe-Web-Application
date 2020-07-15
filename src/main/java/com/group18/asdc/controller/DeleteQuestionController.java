package com.group18.asdc.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.group18.asdc.SystemConfig;
import com.group18.asdc.service.DeleteQuestionService;

@Controller
public class DeleteQuestionController {

	private static final DeleteQuestionService theDeleteQuestionService = SystemConfig.getSingletonInstance()
			.getServiceAbstractFactory().getDeleteQuestionService();

	@GetMapping("/deleteQuestion")
	public String deleteQuestion(HttpServletRequest request) {
		String questionId = request.getParameter("id");
		boolean isDeleted = theDeleteQuestionService.deleteQuestion(Integer.parseInt(questionId));
		if (isDeleted) {
			return "redirect:/viewQuestions/getHome";
		} else {
			return "error";
		}
	}
}
