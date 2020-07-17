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
import com.group18.asdc.database.SurveyDataBaseQueries;
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

public class SurveyDaoImpl implements SurveyDao {

	private final Logger log = Logger.getLogger(SurveyDaoImpl.class.getName());

	private static final ViewQuestionsService theViewQuestionsService = SystemConfig.getSingletonInstance()
			.getServiceAbstractFactory().getViewQuestionsService();

	@Override
	public SurveyMetaData getSavedSurvey(Course course) {

		SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantStringUtil.DATE_FORMAT.toString());
		List<SurveyQuestion> allSavedQuestions = new ArrayList<SurveyQuestion>();
		SurveyMetaData theSurvey = SystemConfig.getSingletonInstance().getModelAbstractFactory().getSurveyMetaData();

		try (Connection connection = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
				.getConnectionManager().getDBConnection();
				PreparedStatement thePreparedStatement = connection
						.prepareStatement(SurveyDataBaseQueries.GET_ALL_SAVED_QUESTIONS.toString());
				PreparedStatement thePreparedStatementGetOptions = connection
						.prepareStatement(SurveyDataBaseQueries.GET_SURVEYQUESTION_OPTIONS.toString());
				PreparedStatement thePreparedStatementGetData = connection
						.prepareStatement(SurveyDataBaseQueries.GET_SURVEYQUESTION_DATA.toString());) {

			thePreparedStatement.setInt(1, course.getCourseId());

			ResultSet theResultSet = thePreparedStatement.executeQuery();
			SurveyQuestion theSurveyQuestion = null;
			theSurvey.setTheCourse(course);
			QuestionMetaData theQuestionMetaData = null;

			while (theResultSet.next()) {
				theSurveyQuestion = SystemConfig.getSingletonInstance().getModelAbstractFactory().getSurveyQuestion();
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

			for (int i = 0; i < allSavedQuestions.size(); i++) {

				int surveyQuestionId = allSavedQuestions.get(i).getSurveyQuestionId();
				thePreparedStatementGetOptions.setInt(1, surveyQuestionId);
				theResultSet = thePreparedStatementGetOptions.executeQuery();
				List<Option> options = new ArrayList<Option>();
				Option option = null;
				while (theResultSet.next()) {
					option = SystemConfig.getSingletonInstance().getModelAbstractFactory().getOption();
					option.setDisplayText(theResultSet.getString(3));
					option.setStoredData(theResultSet.getInt(4));
					options.add(option);
				}
				theSurvey.getSurveyQuestions().get(i).setOptions(options);
				QuestionMetaData questionMetaData = SystemConfig.getSingletonInstance().getModelAbstractFactory()
						.getQuestionMetaData();
				BasicQuestionData basicQuestionData = SystemConfig.getSingletonInstance().getModelAbstractFactory()
						.getBasicQuestionData();
				thePreparedStatementGetData.setInt(1, surveyQuestionId);
				theResultSet = thePreparedStatementGetData.executeQuery();
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
								"Can not able to get the survey data for the course=" + course.getCourseId());
					}
				}
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL Exception and Can not able to get the survey data for the course="
					+ course.getCourseId());
		}
		return theSurvey;
	}

	@Override
	public boolean saveSurvey(SurveyMetaData surveyData) throws SavingSurveyException {

		boolean isSurveySaved = Boolean.FALSE;
		boolean isSurveyDeleted = Boolean.FALSE;
		try (Connection connection = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
				.getConnectionManager().getDBConnection();
				PreparedStatement thePreparedStatement = connection
						.prepareStatement(SurveyDataBaseQueries.DELETE_ALL_SAVED_QUESTIONS.toString());
				PreparedStatement updateSize = connection
						.prepareStatement(SurveyDataBaseQueries.UPDATE_GROUP_SIZE.toString());
				PreparedStatement saveQuestions = connection
						.prepareStatement(SurveyDataBaseQueries.SAVE_SURVEY_QUESTION.toString());) {

			connection.setAutoCommit(Boolean.FALSE);
			thePreparedStatement.setInt(1, surveyData.getSurveyId());
			try {
				thePreparedStatement.executeUpdate();
				isSurveyDeleted = Boolean.TRUE;
			} catch (SQLException e) {
				log.log(Level.SEVERE,
						"SQL Exception occured while saving survey with id value=" + surveyData.getSurveyId());
				throw new SavingSurveyException("Failure while Saving survey!! Try again");
			}
			try {
				updateSize.setInt(1, surveyData.getGroupSize());
				updateSize.setInt(2, surveyData.getSurveyId());
				updateSize.execute();
			} catch (SQLException e) {
				log.log(Level.SEVERE, "SQL Exception occured while saving group size of survey with id value="
						+ surveyData.getSurveyId());
				throw new SavingSurveyException("Failure while saving survey!! Try again");

			}
			if (isSurveyDeleted) {
				for (SurveyQuestion surveyQuestion : surveyData.getSurveyQuestions()) {
					saveQuestions.setInt(1, surveyData.getSurveyId());
					saveQuestions.setInt(2, surveyQuestion.getQuestionData().getQuestionId());
					Timestamp currentTimestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
					saveQuestions.setTimestamp(3, currentTimestamp);
					String logicType = surveyQuestion.getLogicDetail();
					if (0 == logicType.compareToIgnoreCase(ConstantStringUtil.GREATER_THAN.toString())) {
						saveQuestions.setInt(4, Integer.parseInt(LogicDetail.Greater_Than.toString()));
						saveQuestions.setInt(5, surveyQuestion.getLogicConstraint());
					} else if (0 == logicType.compareToIgnoreCase(ConstantStringUtil.LESS_THAN.toString())) {
						saveQuestions.setInt(4, Integer.parseInt(LogicDetail.Less_Than.toString()));
						saveQuestions.setInt(5, surveyQuestion.getLogicConstraint());
					} else if (0 == logicType.compareToIgnoreCase(ConstantStringUtil.GROUP_DISIMILAR.toString())) {
						saveQuestions.setInt(4, Integer.parseInt(LogicDetail.Group_Disimilar.toString()));
						saveQuestions.setInt(5, 0);
					} else {
						saveQuestions.setInt(4, Integer.parseInt(LogicDetail.Group_Similar.toString()));
						saveQuestions.setInt(5, 0);
					}
					saveQuestions.setInt(6, surveyQuestion.getPriority());
					saveQuestions.addBatch();
				}

				if (surveyData.getSurveyQuestions().size() > 0) {
					try {
						int surveyQuestionSaveStatus[] = saveQuestions.executeBatch();
						if (surveyQuestionSaveStatus.length > 0) {
							isSurveySaved = Boolean.TRUE;
						}
					} catch (BatchUpdateException e) {
						log.log(Level.SEVERE, "Batch exception which saving survey questions for survey id="
								+ surveyData.getSurveyId());
						throw new SavingSurveyException("An error occured while saving survey!! Please try again.");
					}
				}
			}
			if (isSurveySaved) {
				connection.commit();
			} else {
				log.log(Level.WARNING, "Survey with id=" + surveyData.getSurveyId() + " has not been saved");
				throw new SavingSurveyException("An error occured while saving survey!! Please try again");
			}

		} catch (SQLException e) {
			log.log(Level.SEVERE,
					"SQL Exception occuered while saving survey with id value " + surveyData.getSurveyId());
		}
		return isSurveySaved;
	}

	@Override
	public boolean isSurveyExists(Course course) {

		boolean isSurveyExists = Boolean.FALSE;
		try (Connection connection = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
				.getConnectionManager().getDBConnection();
				PreparedStatement thePreparedStatement = connection
						.prepareStatement(SurveyDataBaseQueries.IS_SURVEY_EXISTS.toString());) {

			ResultSet theResultSet = null;
			thePreparedStatement.setInt(1, course.getCourseId());
			theResultSet = thePreparedStatement.executeQuery();
			if (theResultSet.next()) {
				isSurveyExists = Boolean.TRUE;
				log.log(Level.INFO, "Survey exists for Course with id=" + course.getCourseId());
			} else {
				log.log(Level.FINE, "Survey doesnt exists for the course with id=" + course.getCourseId());
				isSurveyExists = Boolean.FALSE;
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL Exception which checking if survey exists or not for course with id="
					+ course.getCourseId());
		}
		return isSurveyExists;
	}

	@Override
	public int createSurvey(Course course) {

		int surveyId = 0;
		try (Connection connection = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
				.getConnectionManager().getDBConnection();
				PreparedStatement thePreparedStatement = connection.prepareStatement(
						SurveyDataBaseQueries.CREATE_SURVEY.toString(), Statement.RETURN_GENERATED_KEYS);) {

			ResultSet theResultSet = null;
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
			log.log(Level.SEVERE, "SQL Exception while Creating Survey for the course with id=" + course.getCourseId());
		}
		return surveyId;
	}

	@Override
	public boolean isSurveyPublished(Course course) {

		boolean isSurveyPublished = Boolean.FALSE;
		try (Connection connection = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
				.getConnectionManager().getDBConnection();
				PreparedStatement thePreparedStatement = connection
						.prepareStatement(SurveyDataBaseQueries.IS_SURVEY_PUBLISHED.toString());) {

			ResultSet theResultSet = null;
			thePreparedStatement.setInt(1, course.getCourseId());
			theResultSet = thePreparedStatement.executeQuery();
			if (theResultSet.next()) {
				if (theResultSet.getBoolean("state")) {
					isSurveyPublished = Boolean.TRUE;
				}
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE,
					"SQL Exception while checking status of Survey for course with id=" + course.getCourseId());
		}
		return isSurveyPublished;
	}

	@Override
	public boolean publishSurvey(SurveyMetaData surveyMetaData) throws PublishSurveyException {

		boolean isSurveyPublished = Boolean.FALSE;
		try (Connection connection = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
				.getConnectionManager().getDBConnection();
				PreparedStatement thePreparedStatement = connection.prepareStatement(
						SurveyDataBaseQueries.PUBLICH_SURVEY.toString(), Statement.RETURN_GENERATED_KEYS);) {
			thePreparedStatement.setBoolean(1, Boolean.TRUE);
			thePreparedStatement.setInt(2, surveyMetaData.getSurveyId());
			int result = thePreparedStatement.executeUpdate();
			if (result > 0) {
				log.log(Level.INFO, "Survey for course with course id=" + surveyMetaData.getTheCourse().getCourseId()
						+ " has been published");
				isSurveyPublished = Boolean.TRUE;
			} else {
				log.log(Level.WARNING, "Survey for course with course id=" + surveyMetaData.getTheCourse().getCourseId()
						+ " has not been published");
				isSurveyPublished = Boolean.FALSE;
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL Exception occuered while publish survey for the course with id="
					+ surveyMetaData.getTheCourse().getCourseId());
			throw new PublishSurveyException("Survey is not published ! Try again");
		}
		return isSurveyPublished;
	}
}
