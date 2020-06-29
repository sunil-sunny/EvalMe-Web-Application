package com.group18.asdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.logging.Logger;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.database.ConnectionManager;
import com.group18.asdc.entities.BasicQuestionData;
import com.group18.asdc.entities.MultipleChoiceQuestion;
import com.group18.asdc.entities.Option;
import com.group18.asdc.entities.User;
import com.group18.asdc.service.UserService;
import com.group18.asdc.util.DataBaseQueriesUtil;

public class CreateQuestionDaoImpl implements CreateQuestionDao {

	Logger log = Logger.getLogger(CreateQuestionDaoImpl.class.getName());

	@Override
	public boolean createNumericOrTextQuestion(BasicQuestionData theBasicQuestionData, User theUser) {
		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		boolean isQuestionCreated = false;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			thePreparedStatement = connection.prepareStatement(DataBaseQueriesUtil.createQuestion);
			thePreparedStatement.setString(1, theUser.getBannerId());
			int questionTypeId = this.getIdForQuestionType(theBasicQuestionData.getQuestionType());

			if (questionTypeId == 0) {
				isQuestionCreated = false;
			} else {
				thePreparedStatement.setInt(2, questionTypeId);
				thePreparedStatement.setString(3, theBasicQuestionData.getQuestionTitle().toLowerCase());
				thePreparedStatement.setString(4, theBasicQuestionData.getQuestionText().toLowerCase());
				Timestamp currentTimestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
				thePreparedStatement.setTimestamp(5, currentTimestamp);
				int createQuestionResult = thePreparedStatement.executeUpdate();
				if (createQuestionResult > 0) {
					isQuestionCreated = true;
				}
			}

		} catch (SQLException e) {
			log.info("SQL Exception while creating the Numeric or Text Question");
		} finally {

			try {

				if (connection != null) {
					connection.close();
				}
				if (thePreparedStatement != null) {
					thePreparedStatement.close();
				}
				log.info("closing connection after creating a numeric or text question");
			} catch (SQLException e) {
				log.info(
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
		boolean isQuestionCreated = false;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			connection.setAutoCommit(false);
			preparedStatementForQuestionCreation = connection.prepareStatement(DataBaseQueriesUtil.createQuestion,
					PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatementForQuestionCreation.setString(1, theUser.getBannerId());
			int questionTypeId = this.getIdForQuestionType(theMultipleChoiceQuestion.getQuestionType());
			if (questionTypeId == 0) {
				isQuestionCreated = false;
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
					System.out.println(questionId);
					for (Option theOption : theMultipleChoiceQuestion.getOptionList()) {
						preparedStatementForOptionCreation = connection
								.prepareStatement(DataBaseQueriesUtil.createOptions);
						preparedStatementForOptionCreation.setInt(1, questionId);
						preparedStatementForOptionCreation.setString(2, theOption.getDisplayText());
						preparedStatementForOptionCreation.setInt(3, theOption.getStoredData());
						int createdResult = preparedStatementForOptionCreation.executeUpdate();
						if (createdResult == 0) {
							isQuestionCreated = false;
							break;
						} else {
							isQuestionCreated = true;
							if (preparedStatementForOptionCreation != null) {
								preparedStatementForOptionCreation.close();
							}
						}
					}
				}
			}
			if (isQuestionCreated) {
				connection.commit();
			}
		} catch (SQLException e) {
			log.info("SQL Exception while creating Multiple choice question");
		} finally {

			try {
				if (theResultSet != null) {
					theResultSet.close();
				}
				if (connection != null) {
					connection.close();
				}
				if (preparedStatementForQuestionCreation != null) {
					preparedStatementForQuestionCreation.close();
				}
				if (preparedStatementForOptionCreation != null) {
					preparedStatementForOptionCreation.close();
				}
				log.info("closing connection after creating multiple choice question");
			} catch (SQLException e) {
				log.info(
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
			thePreparedStatement = connection.prepareStatement(DataBaseQueriesUtil.getQuestionTypeId);
			thePreparedStatement.setString(1, questionType);
			theResultSet = thePreparedStatement.executeQuery();
			if (theResultSet.next()) {
				typeId = theResultSet.getInt("questiontypeid");
			}
		} catch (SQLException e) {
			log.info("SQL Exception while getting ID for question type");
		} finally {

			try {
				if (theResultSet != null) {
					theResultSet.close();
				}
				if (connection != null) {
					connection.close();
				}
				if (thePreparedStatement != null) {
					thePreparedStatement.close();
				}
				log.info("closing connection after getting title id");
			} catch (SQLException e) {
				log.info("SQL Exception while closing connection and statements after getting the id for title");
			}
		}

		return typeId;
	}

	@Override
	public boolean isQuestionExists(BasicQuestionData theBasicQuestionData) {
		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		ResultSet theResultSet = null;
		UserService theUserService = SystemConfig.getSingletonInstance().getTheUserService();
		boolean isQuestionExists = false;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			thePreparedStatement = connection.prepareStatement(DataBaseQueriesUtil.getQuestionId);
			thePreparedStatement.setString(1, theUserService.getCurrentUser().getBannerId());
			thePreparedStatement.setInt(2, this.getIdForQuestionType(theBasicQuestionData.getQuestionType()));
			thePreparedStatement.setString(3, theBasicQuestionData.getQuestionTitle().toLowerCase());
			thePreparedStatement.setString(4, theBasicQuestionData.getQuestionText().toLowerCase());
			theResultSet = thePreparedStatement.executeQuery();
			if (theResultSet.next()) {
				isQuestionExists = true;
			}
		} catch (SQLException e) {
			log.info("SQL Exception while checking whether the question exists or not");
		} finally {

			try {
				if (theResultSet != null) {
					theResultSet.close();
				}
				if (connection != null) {
					connection.close();
				}
				if (thePreparedStatement != null) {
					thePreparedStatement.close();
				}
				log.info("closing connection after getting question id");
			} catch (SQLException e) {
				log.info("SQL Exception while closing the connection and statements after checking whether the question exists or not");
			}
		}
		return isQuestionExists;
	}

}
