package com.group18.asdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.group18.asdc.database.ConnectionManager;
import com.group18.asdc.entities.QuestionMetaData;
import com.group18.asdc.entities.User;
import com.group18.asdc.util.DataBaseQueriesUtil;

public class ViewQuestionsDaoImpl implements ViewQuestionsDao {

	Logger log = Logger.getLogger(ViewQuestionsDaoImpl.class.getName());

	@Override
	public List<QuestionMetaData> getAllQuestions(User currentUser) {
		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		ResultSet theResultSet = null;
		List<QuestionMetaData> allQuestions = new ArrayList<QuestionMetaData>();
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			thePreparedStatement = connection.prepareStatement(DataBaseQueriesUtil.getAllQuestions);
			thePreparedStatement.setString(1, currentUser.getBannerId());
			theResultSet = thePreparedStatement.executeQuery();
			QuestionMetaData theQuestionMetaData = null;
			while (theResultSet.next()) {
				theQuestionMetaData = new QuestionMetaData();
				theQuestionMetaData.setQuestionId(theResultSet.getInt(1));
				theQuestionMetaData.setQuestionTitle(theResultSet.getString(2));
				theQuestionMetaData.setQuestionText(theResultSet.getString(3));
				theQuestionMetaData.setCreationDateTime(theResultSet.getTimestamp(4));
				allQuestions.add(theQuestionMetaData);
			}
		} catch (SQLException e) {
			log.info("SQL Exception while getting all the question");
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
				log.info("closing connection after getting all questions");
			} catch (SQLException e) {
				log.info("SQL Exception while closing the connection and statement after getting all the question");
			}
		}

		return allQuestions;
	}

	@Override
	public List<QuestionMetaData> getAllQuestionsSortByDate(User currentUser) {
		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		ResultSet theResultSet = null;
		List<QuestionMetaData> allQuestionsSortByDate = new ArrayList<QuestionMetaData>();
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			thePreparedStatement = connection.prepareStatement(DataBaseQueriesUtil.getAllQuestionsSortByDate);
			thePreparedStatement.setString(1, currentUser.getBannerId());
			theResultSet = thePreparedStatement.executeQuery();
			QuestionMetaData theQuestionMetaData = null;
			while (theResultSet.next()) {
				theQuestionMetaData = new QuestionMetaData();
				theQuestionMetaData.setQuestionId(theResultSet.getInt(1));
				theQuestionMetaData.setQuestionTitle(theResultSet.getString(2));
				theQuestionMetaData.setQuestionText(theResultSet.getString(3));
				theQuestionMetaData.setCreationDateTime(theResultSet.getTimestamp(4));
				allQuestionsSortByDate.add(theQuestionMetaData);
			}
		} catch (SQLException e) {
			log.info("SQL Exception while getting all the question sort by date");
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
				log.info("closing connection after getting all questions sort by date");
			} catch (SQLException e) {
				log.info(
						"SQL Exception while closing the connection and statement after getting all the question sort by date");

			}
		}

		return allQuestionsSortByDate;
	}

	@Override
	public List<QuestionMetaData> getAllQuestionsSortByTitle(User currentUser) {
		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		ResultSet theResultSet = null;
		List<QuestionMetaData> allQuestionsSortByTitle = new ArrayList<QuestionMetaData>();
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			thePreparedStatement = connection.prepareStatement(DataBaseQueriesUtil.getAllQuestionsSortByTitle);
			thePreparedStatement.setString(1, currentUser.getBannerId());
			theResultSet = thePreparedStatement.executeQuery();
			QuestionMetaData theQuestionMetaData = null;
			while (theResultSet.next()) {
				theQuestionMetaData = new QuestionMetaData();
				theQuestionMetaData.setQuestionId(theResultSet.getInt(1));
				theQuestionMetaData.setQuestionTitle(theResultSet.getString(2));
				theQuestionMetaData.setQuestionText(theResultSet.getString(3));
				theQuestionMetaData.setCreationDateTime(theResultSet.getTimestamp(4));
				allQuestionsSortByTitle.add(theQuestionMetaData);
			}
		} catch (SQLException e) {
			log.info("SQL Exception while getting all the question sort by title");
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
				log.info("closing connection after getting all questions sort by title");
			} catch (SQLException e) {
				log.info(
						"SQL Exception while closing the connection and statement after getting all the question sort by title");
			}
		}

		return allQuestionsSortByTitle;
	}
}
