/*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date  10/12/2014
 * ************************************************************************************************/
package org.AC.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.AC.JDBC.ConnectionFactory;
import org.AC.JDBC.Util;
import org.AC.dto.SessionDTO;
import org.AC.dto.UserRequestDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/********************************
 * CLASS SUMMARY*****************************************************
 * 
 * This class do all the DB Calls for the admin session tab.
 * 
 *
 ***************************************************************************************************/

public class AdminSessionDAO {
	private static final Logger logger = Logger
			.getLogger(AdminSessionDAO.class);
	Connection conn = null;
	Statement stmt = null;

	/**************************************
	 * COMMENTS*************************************************** This function
	 * will retrieve all the session with status : "WAITING FOR SESSION"
	 * 
	 * @return :List<SessionDTO> sessionList
	 * @param : String status
	 * 
	 *
	 ***************************************************************************************************/

	public List<SessionDTO> GetSessionDetails(String status) {

		logger.info("Entered GetSessionDetails method of AdminSessionDAO");
		ResultSet results = null;
		List<SessionDTO> sessionList = new ArrayList<SessionDTO>();
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "SELECT * FROM session_table WHERE STATUS = ? ORDER BY ACCEPTED_DATE";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, status);
			results = pstmt.executeQuery();
			while (results.next()) {
				SessionDTO session = new SessionDTO();
				session.setSessionId(results.getInt("SESSION_ID"));
				session.setRequestId(results.getInt("REQUEST_ID"));
				session.setAdvisorId(results.getInt("ADVISOR_ID"));
				session.setUserId(results.getInt("USER_ID"));
				session.setSessionPlan(results.getString("SESSION_PLAN"));
				session.setAcceptedDate(results.getTimestamp("ACCEPTED_DATE"));
				session.setStatus(results.getString("STATUS"));
				sessionList.add(session);
			}
			conn.commit();
			logger.info("Exit GetSessionDetails method of AdminSessionDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("GetSessionDetails method of AdminSessionDAO threw error:"
							+ e2.getMessage());
					e2.printStackTrace();
				}
				logger.error("GetSessionDetails method of AdminSessionDAO threw error:"
						+ e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("GetSessionDetails method of AdminRequestDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetSessionDetails method of AdminRequestDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return sessionList;
	}

	
	public List<SessionDTO> SearchSessionDetailsByDate(String status, String fromDate, String toDate) {

		logger.info("Entered GetSessionDetails method of AdminSessionDAO");
		ResultSet results = null;
		List<SessionDTO> sessionList = new ArrayList<SessionDTO>();
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "SELECT * FROM session_table WHERE STATUS = ? AND ACCEPTED_DATE >= ? AND ACCEPTED_DATE <= ?";
						
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, status);
			pstmt.setString(2, fromDate	+ " 00:00:00");
			pstmt.setString(3, toDate	+ " 23:59:59");
			
			results = pstmt.executeQuery();
			while (results.next()) {
				SessionDTO session = new SessionDTO();
				session.setSessionId(results.getInt("SESSION_ID"));
				session.setRequestId(results.getInt("REQUEST_ID"));
				session.setAdvisorId(results.getInt("ADVISOR_ID"));
				session.setUserId(results.getInt("USER_ID"));
				session.setSessionPlan(results.getString("SESSION_PLAN"));
				session.setAcceptedDate(results.getTimestamp("ACCEPTED_DATE"));
				session.setStatus(results.getString("STATUS"));
				sessionList.add(session);
			}
			conn.commit();
			logger.info("Exit GetSessionDetails method of AdminSessionDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("GetSessionDetails method of AdminSessionDAO threw error:"
							+ e2.getMessage());
					e2.printStackTrace();
				}
				logger.error("GetSessionDetails method of AdminSessionDAO threw error:"
						+ e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("GetSessionDetails method of AdminRequestDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetSessionDetails method of AdminRequestDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return sessionList;
	}
	
	
	
	
	/**************************************
	 * COMMENTS*************************************************** This function
	 * will retrieve all the session with status
	 * 
	 * @return :List<SessionDTO> sessionList
	 * @param : String status
	 * 
	 *
	 ***************************************************************************************************/

	public List<SessionDTO> GetSessionDetails(String status1, String status2,
			String status3, String status4, String status5, String status6) {

		logger.info("Entered GetSessionDetails method of AdminSessionDAO");
		ResultSet results = null;
		List<SessionDTO> sessionList = new ArrayList<SessionDTO>();
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "SELECT * FROM session_table WHERE STATUS = ? OR STATUS = ? OR STATUS = ? OR STATUS = ? OR STATUS = ? OR STATUS = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, status1);
			pstmt.setString(2, status2);
			pstmt.setString(3, status3);
			pstmt.setString(4, status4);
			pstmt.setString(5, status5);
			pstmt.setString(6, status6);
			results = pstmt.executeQuery();
			while (results.next()) {
				SessionDTO session = new SessionDTO();
				session.setSessionId(results.getInt("SESSION_ID"));
				session.setRequestId(results.getInt("REQUEST_ID"));
				session.setAdvisorId(results.getInt("ADVISOR_ID"));
				session.setUserId(results.getInt("USER_ID"));
				session.setSessionPlan(results.getString("SESSION_PLAN"));
				session.setAcceptedDate(results.getTimestamp("ACCEPTED_DATE"));
				session.setStatus(results.getString("STATUS"));
				sessionList.add(session);
			}
			conn.commit();
			logger.info("Exit GetSessionDetails method of AdminSessionDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("GetSessionDetails method of AdminSessionDAO threw error:"
							+ e2.getMessage());
					e2.printStackTrace();
				}
				logger.error("GetSessionDetails method of AdminSessionDAO threw error:"
						+ e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("GetSessionDetails method of AdminRequestDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetSessionDetails method of AdminRequestDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return sessionList;
	}

	public List<UserRequestDTO> getUserRequestDetails(String status,
			String status1, String status2) {
		logger.info("Entered getUserRequestDetails method of AdminSessionDAO");
		ResultSet results = null;
		List<UserRequestDTO> list = new ArrayList<UserRequestDTO>();
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "SELECT * FROM userrequest WHERE STATUS = ? OR STATUS = ? OR STATUS = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, status);
			pstmt.setString(2, status1);
			pstmt.setString(3, status2);
			results = pstmt.executeQuery();
			while (results.next()) {
				UserRequestDTO user = new UserRequestDTO();
				user.setRequestId(results.getInt("REQUEST_ID"));
				user.setUserId(results.getInt("USER_ID"));
				user.setAdvisorId(results.getInt("ADVISOR_ID"));
				user.setService(results.getString("SERVICE"));
				user.setMode(results.getString("MODE_OF_COMMUNICATION"));
				user.setQuery(results.getString("QUERY"));
				user.setDuration(results.getString("DURATION"));
				user.setBookingTime(results.getTimestamp("BOOKING_TIME"));
				user.setTime1(results.getTimestamp("DATE_TIME1"));
				user.setTime2(results.getTimestamp("DATE_TIME2"));
				user.setTime3(results.getTimestamp("DATE_TIME3"));
				user.setTime4(results.getTimestamp("DATE_TIME4"));
				user.setStatus(results.getString("STATUS"));
				list.add(user);
			}
			conn.commit();
			logger.info("Exit getUserRequestDetails method of AdminSessionDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("getUserRequestDetails method of AdminSessionDAO threw error:"
							+ e2.getMessage());
					e2.printStackTrace();
				}
				logger.error("getUserRequestDetails method of AdminSessionDAO threw error:"
						+ e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("getUserRequestDetails method of AdminRequestDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("getUserRequestDetails method of AdminSessionDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return list;
	}

	/**************************************
	 * COMMENTS*************************************************** This function
	 * will deactivate the advisor by setting the isActive flag of the advisor
	 * to false
	 * 
	 * @return :Boolean isFlagCommit
	 * @param : String aId
	 * 
	 *
	 ***************************************************************************************************/

	public Boolean SetAdvisorFlag(String aId) {

		logger.info("Entered SetAdvisorFlag method of AdminSessionDAO");
		Boolean isFlagCommit = false;
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "UPDATE advisordetails SET ISACTIVE = ? WHERE ADVISOR_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1, false);
			pstmt.setString(2, aId);
			int result = pstmt.executeUpdate();
			if (result > 0) {
				conn.commit();
				isFlagCommit = true;
			}
			logger.info("Exit SetAdvisorFlag method of AdminSessionDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("SetAdvisorFlag method of AdminSessionDAO threw error:"
							+ e2.getMessage());
					e2.printStackTrace();
				}
				logger.error("SetAdvisorFlag method of AdminSessionDAO threw error:"
						+ e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("SetAdvisorFlag method of AdminSessionDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("SetAdvisorFlag method of AdminSessionDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return isFlagCommit;
	}

	public List<SessionDTO> GetSessionDetails() {

		logger.info("Entered GetSessionDetails method of AdminSessionDAO");
		ResultSet results = null;
		List<SessionDTO> sessionList = new ArrayList<SessionDTO>();
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "SELECT * FROM session_table WHERE STATUS = ? OR STATUS = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "WAITING FOR SESSION");
			pstmt.setString(2, "SESSION COMPLETE");
			results = pstmt.executeQuery();
			while (results.next()) {
				SessionDTO session = new SessionDTO();
				session.setSessionId(results.getInt("SESSION_ID"));
				session.setRequestId(results.getInt("REQUEST_ID"));
				session.setAdvisorId(results.getInt("ADVISOR_ID"));
				session.setUserId(results.getInt("USER_ID"));
				session.setSessionPlan(results.getString("SESSION_PLAN"));
				session.setAcceptedDate(results.getTimestamp("ACCEPTED_DATE"));
				session.setStatus(results.getString("STATUS"));
				session.setIsRating(results.getBoolean("RATING"));
				session.setReviewMessage(results.getString("REVIEW_MESSAGE"));
				session.setReviewMessageStatus(results
						.getString("REVIEW_MESSAGE_STATUS"));
				sessionList.add(session);
			}
			conn.commit();
			logger.info("Exit GetSessionDetails method of AdminSessionDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("GetSessionDetails method of AdminSessionDAO threw error:"
							+ e2.getMessage());
					e2.printStackTrace();
				}
				logger.error("GetSessionDetails method of AdminSessionDAO threw error:"
						+ e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("GetSessionDetails method of AdminRequestDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetSessionDetails method of AdminRequestDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return sessionList;
	}

	public Boolean ApproveFeedback(String sId) {

		logger.info("Entered ApproveFeedback method of AdminSessionDAO");
		Boolean isFlagCommit = false;
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "UPDATE feedback SET APPROVED = ? WHERE SESSION_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1, true);
			pstmt.setString(2, sId);
			int result = pstmt.executeUpdate();
			if (result > 0) {
				conn.commit();
				isFlagCommit = true;
			}
			logger.info("Exit ApproveFeedback method of AdminSessionDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("ApproveFeedback method of AdminSessionDAO threw error:"
							+ e2.getMessage());
					e2.printStackTrace();
				}
				logger.error("ApproveFeedback method of AdminSessionDAO threw error:"
						+ e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("ApproveFeedback method of AdminSessionDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("ApproveFeedback method of AdminSessionDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return isFlagCommit;
	}

	public Boolean SetModeDetails(String sId, String details) {
		logger.info("Entered SetModeDetails method of AdminSessionDAO");
		Boolean isFlagCommit = false;
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "UPDATE session_table SET MODE_DETAILS = ? WHERE SESSION_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, details);
			pstmt.setString(2, sId);
			int result = pstmt.executeUpdate();
			if (result > 0) {
				conn.commit();
				isFlagCommit = true;
			}
			logger.info("Exit SetModeDetails method of AdminSessionDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("SetModeDetails method of AdminSessionDAO threw error:"
							+ e2.getMessage());
					e2.printStackTrace();
				}
				logger.error("SetModeDetails method of AdminSessionDAO threw error:"
						+ e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("SetModeDetails method of AdminSessionDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("SetModeDetails method of AdminSessionDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return isFlagCommit;
	}

	public Boolean DeleteFeedback(String sId) {
		logger.info("Entered DeleteFeedback method of AdminSessionDAO");
		int results = 0;
		Boolean isGiven = false;
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "DELETE FROM feedback WHERE SESSION_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, sId);
			results = pstmt.executeUpdate();
			if (results > 0) {
				conn.commit();
				isGiven = true;
			}
			logger.info("Exit DeleteFeedback method of AdminSessionDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("DeleteFeedback method of AdminSessionDAO threw error:"
							+ e2.getMessage());
					e2.printStackTrace();
				}
				logger.error("DeleteFeedback method of AdminSessionDAO threw error:"
						+ e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("DeleteFeedback method of AdminRequestDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("DeleteFeedback method of AdminRequestDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return isGiven;
	}

}
