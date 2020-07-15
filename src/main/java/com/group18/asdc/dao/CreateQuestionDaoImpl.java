package com.group18.asdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.group18.asdc.ProfileManagementConfig;
import com.group18.asdc.database.ConnectionManager;
import com.group18.asdc.entities.BasicQuestionData;
import com.group18.asdc.entities.MultipleChoiceQuestion;
import com.group18.asdc.entities.Option;
import com.group18.asdc.entities.User;
import com.group18.asdc.service.UserService;
import com.group18.asdc.util.QuestionManagerDataBaseQueries;

public class CreateQuestionDaoImpl implements CreateQuestionDao {

	Logger log = Logger.getLogger(CreateQuestionDaoImpl.class.getName());

	@Override
	public boolean createNumericOrTextQuestion(BasicQuestionData theBasicQuestionData, User theUser) {
		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		boolean isQuestionCreated = Boolean.FALSE;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			thePreparedStatement = connection
					.prepareStatement(QuestionManagerDataBaseQueries.CREATE_QUESTION.toString());
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
				} else {
					isQuestionCreated = Boolean.FALSE;
				}
				log.log(Level.INFO,"Created " + theBasicQuestionData.getQuestionType() + " question with text "
						+ theBasicQuestionData.getQuestionText() + " for the user " + theUser.getBannerId());
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL Exception while creating the Numeric or Text Question for the user with id ",
					theUser.getBannerId());
		} finally {
			try {
				if (null != connection) {
					connection.close();
				}
				if (null != thePreparedStatement) {
					thePreparedStatement.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE,
						"SQL Exception while closing connections and statements after creating Numeric or Text Question");
			}
		}
		return isQuestionCreated;
	}

	@Override
	public boolean createMultipleChoiceQuestion(MultipleChoiceQuestion theMultipleChoiceQuestion, User theUser) {
		Connection connection = null;
		PreparedStatement preparedStatementForQuestionCreation = null;
		PreparedStatement preparedStatementForOptionCreation = null;
		ResultSet theResultSet = null;
		boolean isQuestionCreated = Boolean.FALSE;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			connection.setAutoCommit(false);
			preparedStatementForQuestionCreation = connection.prepareStatement(
					QuestionManagerDataBaseQueries.CREATE_QUESTION.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
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
				theResultSet = preparedStatementForQuestionCreation.getGeneratedKeys();
				if (theResultSet.next()) {
					long id = theResultSet.getLong(1);
					int questionId = (int) id;
					for (Option theOption : theMultipleChoiceQuestion.getOptionList()) {
						preparedStatementForOptionCreation = connection
								.prepareStatement(QuestionManagerDataBaseQueries.CREATE_OPTIONS.toString());
						preparedStatementForOptionCreation.setInt(1, questionId);
						preparedStatementForOptionCreation.setString(2, theOption.getDisplayText());
						preparedStatementForOptionCreation.setInt(3, theOption.getStoredData());
						int createdResult = preparedStatementForOptionCreation.executeUpdate();
						if (0 == createdResult) {
							isQuestionCreated = Boolean.FALSE;
							break;
						} else {
							isQuestionCreated = Boolean.TRUE;
							if (null != preparedStatementForOptionCreation) {
								preparedStatementForOptionCreation.close();
							}
						}
					}
				}
			}
			if (isQuestionCreated) {
				connection.commit();
				log.log(Level.INFO,"Created " + theMultipleChoiceQuestion.getQuestionType() + " question with text "
						+ theMultipleChoiceQuestion.getQuestionText() + " for the user " + theUser.getBannerId());
			} else {
				log.log(Level.WARNING,
						"Not able to create nultiple choice questiond for user with id " + theUser.getBannerId());
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL Exception while creating Multiple choice question for user id ",
					theUser.getBannerId());
		} finally {
			try {
				if (null != theResultSet) {
					theResultSet.close();
				}
				if (null != connection) {
					connection.close();
				}
				if (null != preparedStatementForQuestionCreation) {
					preparedStatementForQuestionCreation.close();
				}
				if (null != preparedStatementForOptionCreation) {
					preparedStatementForOptionCreation.close();
				}
				log.log(Level.INFO,"closing connection after creating multiple choice question");
			} catch (SQLException e) {
				log.log(Level.INFO,
						"SQL Exception while closing the connection and statement after creating the multiple choice question");
			}
		}
		return isQuestionCreated;
	}

	@Override
	public int getIdForQuestionType(String questionType) {

		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		ResultSet theResultSet = null;
		int typeId = 0;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			thePreparedStatement = connection
					.prepareStatement(QuestionManagerDataBaseQueries.GET_QUESTION_TYPE_ID.toString());
			thePreparedStatement.setString(1, questionType);
			theResultSet = thePreparedStatement.executeQuery();
			if (theResultSet.next()) {
				typeId = theResultSet.getInt("questiontypeid");
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL Exception while getting ID for question type ", questionType);
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
				log.log(Level.SEVERE, "SQL Exception while closing connection and statements after getting the id for ",
						questionType);
			}
		}
		return typeId;
	}

	@Override
	public boolean isQuestionExists(BasicQuestionData theBasicQuestionData) {
		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		ResultSet theResultSet = null;
		UserService theUserService = ProfileManagementConfig.getSingletonInstance().getTheUserService();
		boolean isQuestionExists = Boolean.FALSE;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			thePreparedStatement = connection
					.prepareStatement(QuestionManagerDataBaseQueries.GET_QUESTION_ID.toString());
			thePreparedStatement.setString(1, theUserService.getCurrentUser().getBannerId());
			thePreparedStatement.setInt(2, this.getIdForQuestionType(theBasicQuestionData.getQuestionType()));
			thePreparedStatement.setString(3, theBasicQuestionData.getQuestionTitle().toLowerCase());
			thePreparedStatement.setString(4, theBasicQuestionData.getQuestionText().toLowerCase());
			theResultSet = thePreparedStatement.executeQuery();
			if (theResultSet.next()) {
				isQuestionExists = Boolean.TRUE;
				log.log(Level.INFO,"Question with text " + theBasicQuestionData.getQuestionText() + " is already exists");
			} else {
				log.log(Level.FINE,
						"Question with text " + theBasicQuestionData.getQuestionText() + " is doesn't exists");
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL Exception while checking whether the question exists or not for text ",
					theBasicQuestionData.getQuestionText());
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
						"SQL Exception while closing the connection and statements after checking whether the question exists or not");
			}
		}
		return isQuestionExists;
	}
}
