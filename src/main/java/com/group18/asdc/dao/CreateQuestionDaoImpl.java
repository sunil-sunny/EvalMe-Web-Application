package com.group18.asdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.database.QuestionManagerDataBaseQueries;
import com.group18.asdc.entities.BasicQuestionData;
import com.group18.asdc.entities.MultipleChoiceQuestion;
import com.group18.asdc.entities.Option;
import com.group18.asdc.entities.User;
import com.group18.asdc.service.UserService;

public class CreateQuestionDaoImpl implements CreateQuestionDao {

	Logger log = Logger.getLogger(CreateQuestionDaoImpl.class.getName());

	@Override
	public boolean createNumericOrTextQuestion(BasicQuestionData theBasicQuestionData, User theUser) {

		boolean isQuestionCreated = Boolean.FALSE;

		try (Connection connection = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
				.getConnectionManager().getDBConnection();
				PreparedStatement thePreparedStatement = connection
						.prepareStatement(QuestionManagerDataBaseQueries.CREATE_QUESTION.toString());) {

			thePreparedStatement.setString(1, theUser.getBannerId());
			int questionTypeId = this.getIdForQuestionType(theBasicQuestionData.getQuestionType());
			if (0 == questionTypeId) {
				isQuestionCreated = Boolean.FALSE;
			} else {
				thePreparedStatement.setInt(2, questionTypeId);
				thePreparedStatement.setString(3, theBasicQuestionData.getQuestionTitle().toLowerCase());
				thePreparedStatement.setString(4, theBasicQuestionData.getQuestionText().toLowerCase());
				Timestamp currentTimestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
				thePreparedStatement.setTimestamp(5, currentTimestamp);
				int createQuestionResult = thePreparedStatement.executeUpdate();
				if (createQuestionResult > 0) {
					isQuestionCreated = Boolean.TRUE;
					log.log(Level.INFO, "Created " + theBasicQuestionData.getQuestionType() + " question with text "
							+ theBasicQuestionData.getQuestionText() + " for the user " + theUser.getBannerId());
				} else {
					isQuestionCreated = Boolean.FALSE;
				}
				log.log(Level.INFO,"Created question=" + theBasicQuestionData.getQuestionType() + " and text="
						+ theBasicQuestionData.getQuestionText() + " for the user=" + theUser.getBannerId());
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL Exception while creating the Numeric or Text Question for the user with id=",
					theUser.getBannerId());
		}
		return isQuestionCreated;
	}

	@Override
	public boolean createMultipleChoiceQuestion(MultipleChoiceQuestion theMultipleChoiceQuestion, User theUser) {

		boolean isQuestionCreated = Boolean.FALSE;

		try (Connection connection = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
				.getConnectionManager().getDBConnection();
				PreparedStatement preparedStatementForQuestionCreation = connection.prepareStatement(
						QuestionManagerDataBaseQueries.CREATE_QUESTION.toString(),
						PreparedStatement.RETURN_GENERATED_KEYS);
				PreparedStatement preparedStatementForOptionCreation = connection
						.prepareStatement(QuestionManagerDataBaseQueries.CREATE_OPTIONS.toString());) {

			connection.setAutoCommit(false);
			preparedStatementForQuestionCreation.setString(1, theUser.getBannerId());
			int questionTypeId = this.getIdForQuestionType(theMultipleChoiceQuestion.getQuestionType());
			if (0 == questionTypeId) {
				isQuestionCreated = Boolean.FALSE;
			} else {
				preparedStatementForQuestionCreation.setInt(2, questionTypeId);
				preparedStatementForQuestionCreation.setString(3,
						theMultipleChoiceQuestion.getQuestionTitle().toLowerCase());
				preparedStatementForQuestionCreation.setString(4,
						theMultipleChoiceQuestion.getQuestionText().toLowerCase());
				Timestamp currentTimestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
				preparedStatementForQuestionCreation.setTimestamp(5, currentTimestamp);
				preparedStatementForQuestionCreation.executeUpdate();
				ResultSet theResultSet = preparedStatementForQuestionCreation.getGeneratedKeys();
				if (theResultSet.next()) {
					long id = theResultSet.getLong(1);
					int questionId = (int) id;
					for (Option theOption : theMultipleChoiceQuestion.getOptionList()) {

						preparedStatementForOptionCreation.setInt(1, questionId);
						preparedStatementForOptionCreation.setString(2, theOption.getDisplayText());
						preparedStatementForOptionCreation.setInt(3, theOption.getStoredData());
						int createdResult = preparedStatementForOptionCreation.executeUpdate();
						if (0 == createdResult) {
							isQuestionCreated = Boolean.FALSE;
							break;
						} else {
							isQuestionCreated = Boolean.TRUE;
						}
					}
				}
			}
			if (isQuestionCreated) {
				connection.commit();
				log.log(Level.INFO,"Created question=" + theMultipleChoiceQuestion.getQuestionType() + " with text="
						+ theMultipleChoiceQuestion.getQuestionText() + " for the user=" + theUser.getBannerId());
			} else {
				log.log(Level.WARNING,
						"Not able to create multiple choice questionid for user with id=" + theUser.getBannerId());
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL Exception while creating Multiple choice question for user id="+
					theUser.getBannerId());
		}
		return isQuestionCreated;
	}

	@Override
	public int getIdForQuestionType(String questionType) {

		int typeId = 0;
		try (Connection connection = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
				.getConnectionManager().getDBConnection();
				PreparedStatement thePreparedStatement = connection
						.prepareStatement(QuestionManagerDataBaseQueries.GET_QUESTION_TYPE_ID.toString());) {

			thePreparedStatement.setString(1, questionType);
			ResultSet theResultSet = thePreparedStatement.executeQuery();
			if (theResultSet.next()) {
				typeId = theResultSet.getInt("questiontypeid");
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL Exception while getting ID for question type="+ questionType);
		} 
		return typeId;
	}

	@Override
	public boolean isQuestionExists(BasicQuestionData theBasicQuestionData) {

		UserService theUserService = SystemConfig.getSingletonInstance().getServiceAbstractFactory().getUserService(
				SystemConfig.getSingletonInstance().getUtilAbstractFactory().getQueryVariableToArrayList());
		boolean isQuestionExists = Boolean.FALSE;

		try (Connection connection = SystemConfig.getSingletonInstance().getDataBaseAbstractFactory()
				.getConnectionManager().getDBConnection();
				PreparedStatement thePreparedStatement = connection
						.prepareStatement(QuestionManagerDataBaseQueries.GET_QUESTION_ID.toString());) {

			thePreparedStatement.setString(1, theUserService.getCurrentUser().getBannerId());
			thePreparedStatement.setInt(2, this.getIdForQuestionType(theBasicQuestionData.getQuestionType()));
			thePreparedStatement.setString(3, theBasicQuestionData.getQuestionTitle().toLowerCase());
			thePreparedStatement.setString(4, theBasicQuestionData.getQuestionText().toLowerCase());

			ResultSet theResultSet = thePreparedStatement.executeQuery();
			if (theResultSet.next()) {
				isQuestionExists = Boolean.TRUE;
				log.log(Level.INFO,"Question with text=" + theBasicQuestionData.getQuestionText() + " is already exists");
			} else {
				log.log(Level.FINE,
						"Question with text=" + theBasicQuestionData.getQuestionText() + " doesn't exists");
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL Exception while checking whether the question exists or not for text "+
					theBasicQuestionData.getQuestionText());
		}
		return isQuestionExists;
	}
}
