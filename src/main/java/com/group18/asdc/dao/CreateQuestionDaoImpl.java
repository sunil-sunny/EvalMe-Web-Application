package com.group18.asdc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Logger;

import com.group18.asdc.database.ConnectionManager;
import com.group18.asdc.entities.BasicQuestionData;
import com.group18.asdc.entities.FreeTextQuestion;
import com.group18.asdc.entities.MultipleChoiceChooseMore;
import com.group18.asdc.entities.MultipleChoiceChooseOne;
import com.group18.asdc.entities.NumericQuestion;
import com.group18.asdc.entities.User;
import com.group18.asdc.util.DataBaseQueriesUtil;

public class CreateQuestionDaoImpl implements CreateQuestionDao {

	Logger log = Logger.getLogger(CreateQuestionDaoImpl.class.getName());

	@Override
	public boolean isQuestionTitleExists(BasicQuestionData theBasicQuestionData) {

		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		ResultSet theResultSet = null;
		boolean isQuestionTitleExists = false;

		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			thePreparedStatement = connection.prepareStatement(DataBaseQueriesUtil.isQuestionTitle);
			thePreparedStatement.setString(1, theBasicQuestionData.getQuestionTitle());
			theResultSet = thePreparedStatement.executeQuery();
			if (theResultSet.next()) {
				isQuestionTitleExists = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
				log.info("closing connection after having a check if question title exists or not");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return isQuestionTitleExists;
	}

	@Override
	public boolean createQuestionTitle(BasicQuestionData basicQuestionData) {

		Connection connection = null;
		PreparedStatement thePreparedStatement = null;

		boolean isQuestionTitleCreated = false;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			thePreparedStatement = connection.prepareStatement(DataBaseQueriesUtil.createQuestionTitle);
			thePreparedStatement.setString(1, basicQuestionData.getQuestionTitle());
			int theResultSet = thePreparedStatement.executeUpdate();
			if (theResultSet != 0) {
				isQuestionTitleCreated = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
				if (thePreparedStatement != null) {
					thePreparedStatement.close();
				}
				log.info("closing connection after creating question title");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return isQuestionTitleCreated;
	}

	@Override
	public boolean createNumericQuestion(NumericQuestion theNumericQuestion, User theUser) {
		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		boolean isNumericQuestionCreated = false;

		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			thePreparedStatement = connection.prepareStatement(DataBaseQueriesUtil.createQuestion);
			thePreparedStatement.setString(1, theUser.getBannerId());
			int questionTypeId = this.getIdForQuestionType(theNumericQuestion.getQuestionType());
			int questionTitleId = this.getIdForQuestionTitle(theNumericQuestion.getQuestionTitle());
			if ((questionTypeId == 0) || (questionTitleId == 0)) {
				isNumericQuestionCreated = false;
			} else {
				thePreparedStatement.setInt(2, questionTypeId);
				thePreparedStatement.setInt(3, questionTitleId);
				thePreparedStatement.setString(4, theNumericQuestion.getQuestionText());
				Date currentDate = new Date(Calendar.getInstance().getTime().getTime());
				thePreparedStatement.setDate(5, currentDate);
				int createQuestionResult = thePreparedStatement.executeUpdate();
				if (createQuestionResult > 0) {
					isNumericQuestionCreated=true;
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {

				if (connection != null) {
					connection.close();
				}
				if (thePreparedStatement != null) {
					thePreparedStatement.close();
				}
				log.info("closing connection after creating a numeric question");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return isNumericQuestionCreated;
	}

	@Override
	public boolean createFreeTextQuestion(FreeTextQuestion theFreeTextQuestion, User theUser) {
		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		boolean isFreeTextQuestionCreated = false;

		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			thePreparedStatement = connection.prepareStatement(DataBaseQueriesUtil.createQuestion);
			thePreparedStatement.setString(1, theUser.getBannerId());
			int questionTypeId = this.getIdForQuestionType(theFreeTextQuestion.getQuestionType());
			int questionTitleId = this.getIdForQuestionTitle(theFreeTextQuestion.getQuestionTitle());
			if ((questionTypeId == 0) || (questionTitleId == 0)) {
				isFreeTextQuestionCreated = false;
			} else {
				thePreparedStatement.setInt(2, questionTypeId);
				thePreparedStatement.setInt(3, questionTitleId);
				thePreparedStatement.setString(4, theFreeTextQuestion.getQuestionText());
				Date currentDate = new Date(Calendar.getInstance().getTime().getTime());
				thePreparedStatement.setDate(5, currentDate);
				int createQuestionResult = thePreparedStatement.executeUpdate();
				if (createQuestionResult > 0) {
					isFreeTextQuestionCreated=true;
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {

				if (connection != null) {
					connection.close();
				}
				if (thePreparedStatement != null) {
					thePreparedStatement.close();
				}
				log.info("closing connection after creating a freetext question");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return isFreeTextQuestionCreated;
	}

	@Override
	public boolean createMultipleChoiceChooseMoreQuestion(MultipleChoiceChooseMore theMultipleChoiceChooseMore,
			User theUser) {
		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		ResultSet theResultSet = null;

		try {
			connection = ConnectionManager.getInstance().getDBConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				log.info("closing connection after having a check if question title exists or not");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return false;
	}

	@Override
	public boolean createMultipleChoiceChooseOneQuestion(MultipleChoiceChooseOne theMultipleChoiceChooseOne,
			User theUser) {
		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		ResultSet theResultSet = null;

		try {
			connection = ConnectionManager.getInstance().getDBConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				log.info("closing connection after having a check if question title exists or not");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return false;
	}

	@Override
	public int getIdForQuestionTitle(String questionTitle) {
		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		ResultSet theResultSet = null;
		int titleId = 0;

		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			thePreparedStatement = connection.prepareStatement(DataBaseQueriesUtil.isQuestionTitle);
			thePreparedStatement.setString(1, questionTitle);
			theResultSet = thePreparedStatement.executeQuery();
			if (theResultSet.next()) {
				titleId = theResultSet.getInt("qtitleid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return titleId;
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
			e.printStackTrace();
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return typeId;
	}

}
