package com.group18.asdc.groupformation;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;

import com.group18.asdc.entities.LogicDetail;
import com.group18.asdc.entities.QuestionType;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ComputeDistanceTest {

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void computeDistanceTest() {

		ArrayList<HashMap> userAnswerList = new ArrayList<HashMap>();
		ArrayList<HashMap> questionsList = new ArrayList<HashMap>();
		HashMap userAnswerMap = new HashMap<>();

		ArrayList<String> answers = new ArrayList<>();

		answers.add("1");
		userAnswerMap.put(1, answers);

		answers = new ArrayList<>();
		answers.add("1");
		answers.add("2");

		userAnswerMap.put(2, answers);

		answers = new ArrayList<>();
		answers.add("65");

		userAnswerMap.put(3, answers);

		userAnswerList.add(userAnswerMap);

		answers = new ArrayList<>();
		userAnswerMap = new HashMap<>();

		answers.add("2");
		userAnswerMap.put(1, answers);

		answers = new ArrayList<>();
		answers.add("1");
		answers.add("2");

		userAnswerMap.put(2, answers);

		answers = new ArrayList<>();
		answers.add("60");

		userAnswerMap.put(3, answers);

		userAnswerList.add(userAnswerMap);

		HashMap questionMap = new HashMap<>();

		questionMap.put("QUESTION_ID", 1);
		questionMap.put("QUESTION_TYPE", QuestionType.MULTIPLE_CHOOSE_ONE.toString());
		questionMap.put("QUESTION_LOGIC", LogicDetail.Group_Disimilar.toString());
		questionMap.put("QUESTION_OPTIONS", 4);
		questionMap.put("QUESTION_PRIORITY", 4);

		questionsList.add(questionMap);

		questionMap = new HashMap<>();
		questionMap.put("QUESTION_ID", 2);
		questionMap.put("QUESTION_TYPE", QuestionType.MULTIPLE_CHOOSE_MORE.toString());
		questionMap.put("QUESTION_LOGIC", LogicDetail.Group_Similar.toString());
		questionMap.put("QUESTION_OPTIONS", 4);
		questionMap.put("QUESTION_PRIORITY", 4);

		questionsList.add(questionMap);

		questionMap = new HashMap<>();
		questionMap.put("QUESTION_ID", 3);
		questionMap.put("QUESTION_TYPE", QuestionType.NUMERIC_TYPE.toString());
		questionMap.put("QUESTION_LOGIC", LogicDetail.Group_Similar.toString());
		questionMap.put("QUESTION_OPTIONS", 4);
		questionMap.put("QUESTION_PRIORITY", 4);
		questionsList.add(questionMap);

		ComputeDistance computeDistance = new ComputeDistance(userAnswerList, questionsList);
		assertNotNull(computeDistance.compute());
	}

}