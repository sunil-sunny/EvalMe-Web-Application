package com.group18.asdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import com.group18.asdc.QuestionManagerConfig;
import com.group18.asdc.database.ConnectionManager;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.QuestionMetaData;
import com.group18.asdc.entities.SurveyQuestion;
import com.group18.asdc.service.ViewQuestionsService;
import com.group18.asdc.util.DataBaseQueriesUtil;
import com.group18.asdc.util.SurveyDataBaseQueries;

public class SurveyDaoImpl implements SurveyDao {

	private final Logger log = Logger.getLogger(SurveyDaoImpl.class.getName());

	@Override
	public List<SurveyQuestion> getAllSavedSurveyQuestions(Course course) {

		ViewQuestionsService theViewQuestionsService = QuestionManagerConfig.getSingletonInstance()
				.getTheViewQuestionsService();
		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		ResultSet theResultSet = null;
		List<SurveyQuestion> allSavedQuestions=new ArrayList<SurveyQuestion>();
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			thePreparedStatement = connection.prepareStatement(SurveyDataBaseQueries.getGetallsavedquestionsQuery());
			thePreparedStatement.setInt(1, course.getCourseId());
			theResultSet = thePreparedStatement.executeQuery();
			SurveyQuestion theSurveyQuestion = null;
			QuestionMetaData theQuestionMetaData = null;
			while (theResultSet.next()) {
				theSurveyQuestion = new SurveyQuestion();
				int questionId = theResultSet.getInt("questionid");
				theQuestionMetaData = theViewQuestionsService.getQuestionById(questionId);
				if (null == theQuestionMetaData) {
					break;
				} else {
					theSurveyQuestion.setQuestionData(theQuestionMetaData);
					theSurveyQuestion.setLogicConstraint(theResultSet.getInt("Xvalue"));
					theSurveyQuestion.setLogicDetail(theResultSet.getString("name"));
					theSurveyQuestion.setSurveyQuestionId(theResultSet.getInt("surveyquestionid"));
					allSavedQuestions.add(theSurveyQuestion);
				}
			}
		} catch (SQLException e) {
			log.info("SQL Exception while getting all the question");
		} finally {
			try {
				if (null != theResultSet) {
					theResultSet.close();
				}
				if (null != connection) {
					connection.close();
				}
				if (null != thePreparedStatement) {
					thePreparedStatement.close();
				}
				log.info("closing connection after getting all questions");
			} catch (SQLException e) {
				log.info("SQL Exception while closing the connection and statement after getting all the question");
			}
		}
		return allSavedQuestions;
	}

	@Override
	public boolean saveSurveyQuestions(List<SurveyQuestion> allSurveyQuestions) {
		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		ResultSet theResultSet = null;
		List<QuestionMetaData> allQuestions = new ArrayList<QuestionMetaData>();
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			thePreparedStatement = connection.prepareStatement(DataBaseQueriesUtil.getAllQuestions);
		} catch (SQLException e) {
			log.info("SQL Exception while getting all the question");
		} finally {
			try {
				if (null != theResultSet) {
					theResultSet.close();
				}
				if (null != connection) {
					connection.close();
				}
				if (null != thePreparedStatement) {
					thePreparedStatement.close();
				}
				log.info("closing connection after getting all questions");
			} catch (SQLException e) {
				log.info("SQL Exception while closing the connection and statement after getting all the question");
			}
		}
		return false;
	}

}
