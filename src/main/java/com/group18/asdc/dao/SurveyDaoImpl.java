package com.group18.asdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import com.group18.asdc.QuestionManagerConfig;
import com.group18.asdc.database.ConnectionManager;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.LogicDetail;
import com.group18.asdc.entities.QuestionMetaData;
import com.group18.asdc.entities.SurveyMetaData;
import com.group18.asdc.entities.SurveyQuestion;
import com.group18.asdc.errorhandling.SavingSurveyException;
import com.group18.asdc.service.ViewQuestionsService;
import com.group18.asdc.util.ConstantStringUtil;
import com.group18.asdc.util.SurveyDataBaseQueries;

public class SurveyDaoImpl implements SurveyDao {

	private final Logger log = Logger.getLogger(SurveyDaoImpl.class.getName());

	@Override
	public SurveyMetaData getSavedSurvey(Course course) {

		ViewQuestionsService theViewQuestionsService = QuestionManagerConfig.getSingletonInstance()
				.getTheViewQuestionsService();
		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		ResultSet theResultSet = null;
		List<SurveyQuestion> allSavedQuestions = new ArrayList<SurveyQuestion>();
		SurveyMetaData theSurvey = new SurveyMetaData();
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			thePreparedStatement = connection
					.prepareStatement(SurveyDataBaseQueries.GET_ALL_SAVED_QUESTIONS.toString());
			thePreparedStatement.setInt(1, course.getCourseId());
			theResultSet = thePreparedStatement.executeQuery();
			SurveyQuestion theSurveyQuestion = null;
			theSurvey.setTheCourse(course);
			QuestionMetaData theQuestionMetaData = null;
			while (theResultSet.next()) {
				theSurveyQuestion = new SurveyQuestion();
				int questionId = theResultSet.getInt("questionid");
				theQuestionMetaData = theViewQuestionsService.getQuestionById(questionId);
				if (null == theQuestionMetaData) {
					break;
				} else {
					theSurvey.setSurveyId(theResultSet.getInt("surveyid"));
					theSurvey.setPublishedStatus(theResultSet.getBoolean("state"));
					theSurvey.setGroupSize(theResultSet.getInt("groupsize"));
					theSurveyQuestion.setLogicConstraint(theResultSet.getInt("logicvalue"));
					theSurveyQuestion.setLogicDetail(theResultSet.getString("logicname"));
					theSurveyQuestion.setSurveyQuestionId(theResultSet.getInt("surveyquestionid"));
					theSurveyQuestion.setQuestionData(theQuestionMetaData);
					allSavedQuestions.add(theSurveyQuestion);
				}
			}
			theSurvey.setSurveyQuestions(allSavedQuestions);
		} catch (SQLException e) {
			log.info("SQL Exception while getting all the question");
			e.printStackTrace();
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
		return theSurvey;
	}

	@Override
	public boolean saveSurvey(SurveyMetaData surveyData) throws SavingSurveyException {

		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		ResultSet theResultSet = null;
		boolean isSurveySaved = Boolean.FALSE;
		boolean isSurveyDeleted = Boolean.FALSE;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			connection.setAutoCommit(Boolean.FALSE);
			thePreparedStatement = connection
					.prepareStatement(SurveyDataBaseQueries.DELETE_ALL_SAVED_QUESTIONS.toString());
			thePreparedStatement.setInt(1, surveyData.getSurveyId());
			try {
				thePreparedStatement.executeUpdate();
				isSurveyDeleted = Boolean.TRUE;
				if (null != thePreparedStatement) {
					thePreparedStatement.close();
				}
				thePreparedStatement = connection.prepareStatement(SurveyDataBaseQueries.UPDARE_GROUP_SIZE.toString());
				thePreparedStatement.setInt(1, surveyData.getGroupSize());
				thePreparedStatement.setInt(2, surveyData.getSurveyId());
			} catch (SQLException e) {
				throw new SavingSurveyException("Failure while deleting survey Questions");
			} finally {
				if (null != thePreparedStatement) {
					thePreparedStatement.close();
				}
			}

			try {
				thePreparedStatement = connection.prepareStatement(SurveyDataBaseQueries.UPDARE_GROUP_SIZE.toString());
				thePreparedStatement.setInt(1, surveyData.getGroupSize());
				thePreparedStatement.setInt(2, surveyData.getSurveyId());
				thePreparedStatement.execute();
			} catch (SQLException e) {
				throw new SavingSurveyException("Failure while updating group size for survey");
			} finally {
				if (null != thePreparedStatement) {
					thePreparedStatement.close();
				}
			}

			if (isSurveyDeleted) {
				thePreparedStatement = connection
						.prepareStatement(SurveyDataBaseQueries.SAVE_SURVEY_QUESTION.toString());
				for (SurveyQuestion surveyQuestion : surveyData.getSurveyQuestions()) {
					thePreparedStatement.setInt(1, surveyData.getSurveyId());
					thePreparedStatement.setInt(2, surveyQuestion.getQuestionData().getQuestionId());
					Timestamp currentTimestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
					thePreparedStatement.setTimestamp(3, currentTimestamp);
					String logicType = surveyQuestion.getLogicDetail();
					if (0 == logicType.compareToIgnoreCase(ConstantStringUtil.greaterThan)) {
						thePreparedStatement.setInt(4, Integer.parseInt(LogicDetail.Greater_Than.toString()));
						thePreparedStatement.setInt(5, surveyQuestion.getLogicConstraint());
					} else if (0 == logicType.compareToIgnoreCase(ConstantStringUtil.lessThan)) {
						thePreparedStatement.setInt(4, Integer.parseInt(LogicDetail.Less_Than.toString()));
						thePreparedStatement.setInt(5, surveyQuestion.getLogicConstraint());
					} else if (0 == logicType.compareToIgnoreCase(ConstantStringUtil.groupDisimilar)) {
						thePreparedStatement.setInt(4, Integer.parseInt(LogicDetail.Group_Disimilar.toString()));
						thePreparedStatement.setInt(5, 0);
					} else {
						thePreparedStatement.setInt(4, Integer.parseInt(LogicDetail.Group_Similar.toString()));
						thePreparedStatement.setInt(5, 0);
					}
					thePreparedStatement.addBatch();
				}

				int surveyQuestionSaveStatus[] = thePreparedStatement.executeBatch();
				if (surveyQuestionSaveStatus.length > 0) {
					isSurveySaved = Boolean.TRUE;
				}
			}
			if (isSurveySaved) {
				connection.commit();
			} else {
				throw new SavingSurveyException("System Failure while saving survey questions");
			}

		} catch (SQLException e) {
			e.printStackTrace();
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
				log.info("closing connection after saving the questions");
			} catch (SQLException e) {
				log.severe(
						"SQL Exception while closing the connection and statement after saving the survey questions");
			}
		}
		return isSurveySaved;
	}

	@Override
	public boolean isSurveyExists(Course course) {
		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		ResultSet theResultSet = null;
		boolean isSurveyExists = Boolean.FALSE;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			thePreparedStatement = connection.prepareStatement(SurveyDataBaseQueries.IS_SURVEY_EXISTS.toString());
			thePreparedStatement.setInt(1, course.getCourseId());
			theResultSet = thePreparedStatement.executeQuery();
			if (theResultSet.next()) {
				isSurveyExists = Boolean.TRUE;
			}

		} catch (SQLException e) {
			log.severe("SQL Exception which checking if survey exists or not");
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
				log.info("closing connection after checking if survey exists or not");
			} catch (SQLException e) {
				log.severe(
						"SQL Exception while closing the connection and statement after checking if survey exists or not");
			}
		}
		return isSurveyExists;
	}

	@Override
	public int createSurvey(Course course) {
		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		ResultSet theResultSet = null;
		int surveyId = 0;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			thePreparedStatement = connection.prepareStatement(SurveyDataBaseQueries.CREATE_SURVEY.toString(),
					Statement.RETURN_GENERATED_KEYS);
			thePreparedStatement.setInt(1, course.getCourseId());
			thePreparedStatement.setBoolean(2, Boolean.FALSE);
			int result = thePreparedStatement.executeUpdate();
			System.out.println("created result" + result);
			if (result > 0) {
				theResultSet = thePreparedStatement.getGeneratedKeys();
				while (theResultSet.next()) {
					surveyId = theResultSet.getInt(1);
				}
			}
		} catch (SQLException e) {
			log.severe("SQL Exception which Creating Survey");
		} finally {
			try {
				if (null != connection) {
					connection.close();
				}
				if (null != thePreparedStatement) {
					thePreparedStatement.close();
				}
				log.info("closing connection after Creating Survey");
			} catch (SQLException e) {
				log.severe("SQL Exception while closing the connection and statement after Creating Survey");
			}
		}

		return surveyId;
	}

	@Override
	public boolean isSurveyPublished(Course course) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean publishSurvey(SurveyMetaData surveyMetaData) {
		// TODO Auto-generated method stub
		return false;
	}
}
