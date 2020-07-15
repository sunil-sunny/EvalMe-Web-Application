package com.group18.asdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.group18.asdc.database.ConnectionManager;
import com.group18.asdc.util.QuestionManagerDataBaseQueries;

public class DeleteQuestionDaoImpl implements DeleteQuestionDao {

	private Logger log = Logger.getLogger(DeleteQuestionDaoImpl.class.getName());

	@Override
	public boolean deleteQuestion(int questionId) {
		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		boolean isQuestionDeleted = Boolean.FALSE;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			thePreparedStatement = connection
					.prepareStatement(QuestionManagerDataBaseQueries.DELETE_QUESTION.toString());
			thePreparedStatement.setInt(1, questionId);
			int deleteQuestionStatus = thePreparedStatement.executeUpdate();
			if (deleteQuestionStatus > 0) {
				isQuestionDeleted = Boolean.TRUE;
				log.info("Question with id " + questionId + "has been deleted");
			} else {
				isQuestionDeleted = Boolean.FALSE;
				log.log(Level.WARNING, "Question with id " + questionId + "has not been deleted");
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, "SQL Exception while deleting the question with id " + questionId);
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
						"SQL Exception while closing the connection and statements after deleting the question");
			}
		}
		return isQuestionDeleted;
	}
}
