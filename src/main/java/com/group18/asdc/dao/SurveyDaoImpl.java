package com.group18.asdc.dao;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.database.ConnectionManager;
import com.group18.asdc.entities.BasicQuestionData;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.LogicDetail;
import com.group18.asdc.entities.Option;
import com.group18.asdc.entities.QuestionMetaData;
import com.group18.asdc.entities.SurveyMetaData;
import com.group18.asdc.entities.SurveyQuestion;
import com.group18.asdc.errorhandling.PublishSurveyException;
import com.group18.asdc.errorhandling.SavingSurveyException;
import com.group18.asdc.service.ViewQuestionsService;
import com.group18.asdc.util.ConstantStringUtil;
import com.group18.asdc.util.SurveyDataBaseQueries;

public class SurveyDaoImpl implements SurveyDao {

	private final Logger log = Logger.getLogger(SurveyDaoImpl.class.getName());

	private static final ViewQuestionsService theViewQuestionsService = SystemConfig.getSingletonInstance()
			.getServiceAbstractFactory().getViewQuestionsService();

	@Override
	public SurveyMetaData getSavedSurvey(Course course) {

		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		ResultSet theResultSet = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantStringUtil.DATE_FORMAT.toString());
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
					theSurveyQuestion.setPriority(theResultSet.getInt("priority"));
					theSurveyQuestion.setQuestionData(theQuestionMetaData);
					allSavedQuestions.add(theSurveyQuestion);
				}
			}
			theSurvey.setSurveyQuestions(allSavedQuestions);
			if (null != thePreparedStatement) {
				thePreparedStatement.close();
			}
			if (null != theResultSet) {
				theResultSet.close();
			}
			for (int i = 0; i < allSavedQuestions.size(); i++) {

				int surveyQuestionId = allSavedQuestions.get(i).getSurveyQuestionId();
				thePreparedStatement = connection
						.prepareStatement(SurveyDataBaseQueries.GET_SURVEYQUESTION_OPTIONS.toString());
				thePreparedStatement.setInt(1, surveyQuestionId);
				theResultSet = thePreparedStatement.executeQuery();
				List<Option> options = new ArrayList<Option>();
				Option option = null;
				while (theResultSet.next()) {
					option = new Option();
					option.setDisplayText(theResultSet.getString(3));
					option.setStoredData(theResultSet.getInt(4));
					options.add(option);
				}
				theSurvey.getSurveyQuestions().get(i).setOptions(options);
				if (null != thePreparedStatement) {
					thePreparedStatement.close();
				}
				if (null != theResultSet) {
					theResultSet.close();
				}
				QuestionMetaData questionMetaData = new QuestionMetaData();
				BasicQuestionData basicQuestionData = new BasicQuestionData();
				thePreparedStatement = connection
						.prepareStatement(SurveyDataBaseQueries.GET_SURVEYQUESTION_DATA.toString());
				thePreparedStatement.setInt(1, surveyQuestionId);
				theResultSet = thePreparedStatement.executeQuery();
				while (theResultSet.next()) {
					try {
						Date date = new Date();
						date = dateFormat.parse(theResultSet.getString(5));
						Timestamp dateTimeStamp = new Timestamp(date.getTime());
						questionMetaData.setCreationDateTime(dateTimeStamp);
						questionMetaData.setQuestionId(theResultSet.getInt(2));
						basicQuestionData.setQuestionTitle(theResultSet.getString(3));
						basicQuestionData.setQuestionText(theResultSet.getString(4));
						basicQuestionData.setQuestionType(theResultSet.getString(6));
						questionMetaData.setBasicQuestionData(basicQuestionData);
						theSurvey.getSurveyQuestions().get(i).setQuestionData(questionMetaData);

					} catch (ParseException e) {
						log.log(Level.SEVERE,
								"Can not able to get the survey data for the course is = " + course.getCourseId());
					}
				}
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL Exception and Can not able to get the survey data for the course is = "
					+ course.getCourseId());
		} finally {
			try {
				if (null != theResultSet) {
					theResultSet.close();
				}
				if (null != thePreparedStatement) {
					thePreparedStatement.close();
				}
				if (null != connection) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE,
						"SQL Exception while closing the connection and statement after getting all the question");
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
				thePreparedStatement = connection.prepareStatement(SurveyDataBaseQueries.UPDATE_GROUP_SIZE.toString());
				thePreparedStatement.setInt(1, surveyData.getGroupSize());
				thePreparedStatement.setInt(2, surveyData.getSurveyId());
				thePreparedStatement.execute();
			} catch (SQLException e) {
				log.log(Level.SEVERE,
						"SQL Exception occured while saving survey with id value" + surveyData.getSurveyId());
				throw new SavingSurveyException("Failure while Saving survey!! Try again");
			} finally {
				if (null != thePreparedStatement) {
					thePreparedStatement.close();
				}
			}
			try {
				thePreparedStatement = connection.prepareStatement(SurveyDataBaseQueries.UPDATE_GROUP_SIZE.toString());
				thePreparedStatement.setInt(1, surveyData.getGroupSize());
				thePreparedStatement.setInt(2, surveyData.getSurveyId());
			} catch (SQLException e) {
				log.log(Level.SEVERE, "SQL Exception occured while saving group size of survey with id value"
						+ surveyData.getSurveyId());
				throw SystemConfig.getSingletonInstance().getExceptionAbstractFactory()
						.getSavingSurveyException("Failure while saving survey!! Try again");

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
					if (0 == logicType.compareToIgnoreCase(ConstantStringUtil.GREATER_THAN.toString())) {
						thePreparedStatement.setInt(4, Integer.parseInt(LogicDetail.Greater_Than.toString()));
						thePreparedStatement.setInt(5, surveyQuestion.getLogicConstraint());
					} else if (0 == logicType.compareToIgnoreCase(ConstantStringUtil.LESS_THAN.toString())) {
						thePreparedStatement.setInt(4, Integer.parseInt(LogicDetail.Less_Than.toString()));
						thePreparedStatement.setInt(5, surveyQuestion.getLogicConstraint());
					} else if (0 == logicType.compareToIgnoreCase(ConstantStringUtil.GROUP_DISIMILAR.toString())) {
						thePreparedStatement.setInt(4, Integer.parseInt(LogicDetail.Group_Disimilar.toString()));
						thePreparedStatement.setInt(5, 0);
					} else {
						thePreparedStatement.setInt(4, Integer.parseInt(LogicDetail.Group_Similar.toString()));
						thePreparedStatement.setInt(5, 0);
					}
					thePreparedStatement.setInt(6, surveyQuestion.getPriority());
					thePreparedStatement.addBatch();
				}

				if (surveyData.getSurveyQuestions().size() > 0) {
					try {
						int surveyQuestionSaveStatus[] = thePreparedStatement.executeBatch();
						if (surveyQuestionSaveStatus.length > 0) {
							isSurveySaved = Boolean.TRUE;
						}
					} catch (BatchUpdateException e) {
						log.log(Level.SEVERE, "Batch exception which saving survey questions for survey id "
								+ surveyData.getSurveyId());
						throw SystemConfig.getSingletonInstance().getExceptionAbstractFactory()
								.getSavingSurveyException("An error occured while saving survey!! Please try again.");
					}
				}
			}
			if (isSurveySaved) {
				connection.commit();
			} else {
				log.log(Level.WARNING, "Survey with id " + surveyData.getSurveyId() + " has not bee saved");
				throw SystemConfig.getSingletonInstance().getExceptionAbstractFactory().getSavingSurveyException("");
			}

		} catch (SQLException e) {
			log.log(Level.SEVERE,
					"SQL Exception occuered while saving survey with id value " + surveyData.getSurveyId());
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
			} catch (SQLException e) {
				log.log(Level.SEVERE,
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
				log.log(Level.INFO, "Survey exists for Course with id " + course.getCourseId());
			} else {
				log.log(Level.FINE, "Survey doesnt exists for the course with id " + course.getCourseId());
				isSurveyExists = Boolean.FALSE;
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL Exception which checking if survey exists or not for course with id value "
					+ course.getCourseId());
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
			} catch (SQLException e) {
				log.log(Level.SEVERE,
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
			if (result > 0) {
				theResultSet = thePreparedStatement.getGeneratedKeys();
				while (theResultSet.next()) {
					surveyId = theResultSet.getInt(1);
				}
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL Exception while Creating Survey for the course with id " + course.getCourseId());
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
			} catch (SQLException e) {
				log.log(Level.SEVERE,
						"SQL Exception while closing the connection and statement after Creating Survey with course id "
								+ course.getCourseId());
			}
		}
		return surveyId;
	}

	@Override
	public boolean isSurveyPublished(Course course) {

		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		ResultSet theResultSet = null;
		boolean isSurveyPublished = Boolean.FALSE;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			thePreparedStatement = connection.prepareStatement(SurveyDataBaseQueries.IS_SURVEY_PUBLISHED.toString());
			thePreparedStatement.setInt(1, course.getCourseId());
			theResultSet = thePreparedStatement.executeQuery();
			if (theResultSet.next()) {
				if (theResultSet.getBoolean("1")) {
					isSurveyPublished = Boolean.TRUE;
				}
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE,
					"SQL Exception while checking status of Survey for course with id " + course.getCourseId());
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
			} catch (SQLException e) {
				log.log(Level.SEVERE,
						"SQL Exception while closing the connection and statement after checking status of Survey for the course with id "
								+ course.getCourseId());
			}
		}
		return isSurveyPublished;
	}

	@Override
	public boolean publishSurvey(SurveyMetaData surveyMetaData) throws PublishSurveyException {
		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		ResultSet theResultSet = null;
		boolean isSurveyPublished = Boolean.FALSE;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			thePreparedStatement = connection.prepareStatement(SurveyDataBaseQueries.PUBLICH_SURVEY.toString(),
					Statement.RETURN_GENERATED_KEYS);
			thePreparedStatement.setBoolean(1, Boolean.TRUE);
			thePreparedStatement.setInt(2, surveyMetaData.getSurveyId());
			int result = thePreparedStatement.executeUpdate();
			theResultSet = thePreparedStatement.getGeneratedKeys();
			if (result > 0) {
				log.log(Level.INFO, "Survey for course with course id " + surveyMetaData.getTheCourse().getCourseId()
						+ " has been published");
				isSurveyPublished = Boolean.TRUE;
			} else {
				log.log(Level.WARNING, "Survey for course with course id " + surveyMetaData.getTheCourse().getCourseId()
						+ " has not been published");
				isSurveyPublished = Boolean.FALSE;
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL Exception occuered while publish survey for the course with id "
					+ surveyMetaData.getTheCourse().getCourseId());
			throw SystemConfig.getSingletonInstance().getExceptionAbstractFactory()
					.getPublishSurveyException("Survey is not published ! Try again");
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
			} catch (SQLException e) {
				log.log(Level.SEVERE,
						"SQL Exception while closing the connection and statement after checking status of Survey for the course with id "
								+ surveyMetaData.getTheCourse().getCourseId());
			}
		}
		return isSurveyPublished;
	}
}
