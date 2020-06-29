package com.group18.asdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.group18.asdc.database.ConnectionManager;
import com.group18.asdc.util.DataBaseQueriesUtil;

public class DeleteQuestionDaoImpl implements DeleteQuestionDao {

	private Logger log=Logger.getLogger(DeleteQuestionDaoImpl.class.getName());
	
	@Override
	public boolean deleteQuestion(int questionId) {
		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		boolean isQuestionDeleted = false;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			thePreparedStatement=connection.prepareStatement(DataBaseQueriesUtil.deleteQuestion);
			thePreparedStatement.setInt(1, questionId);
			int deleteQuestionStatus=thePreparedStatement.executeUpdate();
			if(deleteQuestionStatus>0) {
				isQuestionDeleted=true;
			}
			
		} catch (SQLException e) {
			log.info("SQL Exception while deleting the question");
		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
				if (thePreparedStatement != null) {
					thePreparedStatement.close();
				}
				log.info("closing connection after deleting the question");
			} catch (SQLException e) {
				log.info("SQL Exception while closing the connection and statements after deleting the question");
			}
		}
		return isQuestionDeleted;
	}

}
