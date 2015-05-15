package org.AC.DAO;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.AC.JDBC.ConnectionFactory;
import org.AC.dto.PaymentDTO;
import org.apache.log4j.Logger;

public class AdvisorPaymentHistoryDAO {
	private static final Logger logger = Logger
			.getLogger(AdvisorPaymentHistoryDAO.class);
	Connection conn = null;

	public List<PaymentDTO> GetSessionInfo(int userId) {

		logger.info("Entered GetSessionInfo method of AdvisorPaymentHistoryDAO");
		List<PaymentDTO> payment = new ArrayList<PaymentDTO>();
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "SELECT SESSION_ID,REQUEST_ID,ACCEPTED_DATE,ADVISOR_COMMENT,STATUS FROM session_table WHERE STATUS=? AND ADVISOR_ID=? OR STATUS=? AND ADVISOR_ID=? OR STATUS=? AND ADVISOR_ID=? OR STATUS=? AND ADVISOR_ID=? OR STATUS=? AND ADVISOR_ID=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "SESSION COMPLETE");
			pstmt.setInt(2, userId);
			pstmt.setString(3,
					"SESSION CANCELLED DUE TO ADVISOR UNAVAILABILITY");
			pstmt.setInt(4, userId);
			pstmt.setString(5, "SESSION CANCELLED DUE TO ADVISOR NO SHOW");
			pstmt.setInt(6, userId);
			pstmt.setString(7, "SESSION CANCELLED DUE TO USER UNAVAILABILITY");
			pstmt.setInt(8, userId);
			pstmt.setString(9, "SESSION CANCELLED DUE TO USER NO SHOW");
			pstmt.setInt(10, userId);
			ResultSet results = pstmt.executeQuery();
			while (results.next()) {
				PaymentDTO pay = new PaymentDTO();
				pay.setSessionId(results.getInt("SESSION_ID"));
				pay.setRequestId(results.getInt("REQUEST_ID"));
				pay.setAcceptedDate(results.getTimestamp("ACCEPTED_DATE"));
				pay.setAdvisorComment(results.getString("ADVISOR_COMMENT"));
				pay.setStatus(results.getString("STATUS"));
				payment.add(pay);
			}
		} catch (SQLException e) {
			logger.error("GetSessionInfo method of AdvisorPaymentHistoryDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("GetSessionInfo method of AdvisorPaymentHistoryDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("GetSessionInfo method of AdvisorPaymentHistoryDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetSessionInfo method of AdvisorPaymentHistoryDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return payment;

	}

	public List<PaymentDTO> GetRequestInfo(List<Integer> requestId) {
		logger.info("Entered GetRequestInfo method of AdvisorPaymentHistoryDAO");
		List<PaymentDTO> payment = new ArrayList<PaymentDTO>();
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String q4in = generateQsForIn(requestId.size());
			String query = "SELECT REQUEST_ID,SERVICE,MODE_OF_COMMUNICATION,PRICE,DISCOUNT,AMOUNT FROM userrequest WHERE REQUEST_ID IN ( "
					+ q4in + " )";
			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(query);
			int i = 1;
			for (Integer item : requestId) {
				pstmt.setInt(i++, item);
			}
			ResultSet results = pstmt.executeQuery();
			while (results.next()) {
				PaymentDTO pay = new PaymentDTO();
				pay.setRequestId(results.getInt("REQUEST_ID"));
				pay.setService(results.getString("SERVICE"));
				pay.setMode(results.getString("MODE_OF_COMMUNICATION"));
				pay.setPrice(results.getInt("PRICE"));
				pay.setDiscount(results.getInt("DISCOUNT"));
				pay.setAmount(results.getInt("AMOUNT"));
				payment.add(pay);
			}
		} catch (SQLException e) {
			logger.error("GetRequestInfo method of AdvisorPaymentHistoryDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("GetRequestInfo method of AdvisorPaymentHistoryDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("GetRequestInfo method of AdvisorPaymentHistoryDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetRequestInfo method of AdvisorPaymentHistoryDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return payment;

	}

	public List<PaymentDTO> GetPaymentInfo(List<Integer> sessionId) {
		logger.info("Entered GetPaymentInfo method of AdvisorPaymentHistoryDAO");
		List<PaymentDTO> payment = new ArrayList<PaymentDTO>();
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String q4in = generateQsForIn(sessionId.size());
			String query = "SELECT SESSION_ID,ADVISOR_PAYMENT,PAID_TO_ADVISOR FROM payment WHERE SESSION_ID IN ( "
					+ q4in + " )";
			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(query);
			int i = 1;
			for (Integer item : sessionId) {
				pstmt.setInt(i++, item);
			}
			ResultSet results = pstmt.executeQuery();
			while (results.next()) {
				PaymentDTO pay = new PaymentDTO();
				pay.setSessionId(results.getInt("SESSION_ID"));
				pay.setAmountPayable(results.getInt("ADVISOR_PAYMENT"));
				pay.setPaidToAdvisor(results.getBoolean("PAID_TO_ADVISOR"));
				payment.add(pay);
			}
		} catch (SQLException e) {
			logger.error("GetPaymentInfo method of AdvisorPaymentHistoryDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("GetPaymentInfo method of AdvisorPaymentHistoryDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("GetPaymentInfo method of AdvisorPaymentHistoryDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetPaymentInfo method of AdvisorPaymentHistoryDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		logger.info("Entered GetPaymentInfo method of AdvisorPaymentHistoryDAO");
		return payment;

	}

	public List<PaymentDTO> GetSessionInfo() {

		logger.info("Entered GetSessionInfo method of AdvisorPaymentHistoryDAO");
		List<PaymentDTO> payment = new ArrayList<PaymentDTO>();
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "SELECT SESSION_ID,REQUEST_ID,ACCEPTED_DATE,USER_COMMENT,ADVISOR_COMMENT FROM session_table WHERE STATUS=? OR STATUS = ? OR STATUS = ? OR STATUS = ? OR STATUS = ? OR STATUS = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "WAITING FOR SESSION");
			pstmt.setString(2, "SESSION COMPLETE");
			pstmt.setString(3,
					"SESSION CANCELLED DUE TO ADVISOR UNAVAILABILITY");
			pstmt.setString(4, "SESSION CANCELLED DUE TO ADVISOR NO SHOW");
			pstmt.setString(5, "SESSION CANCELLED DUE TO USER UNAVAILABILITY");
			pstmt.setString(6, "SESSION CANCELLED DUE TO USER NO SHOW");
			ResultSet results = pstmt.executeQuery();
			while (results.next()) {
				PaymentDTO pay = new PaymentDTO();
				pay.setSessionId(results.getInt("SESSION_ID"));
				pay.setRequestId(results.getInt("REQUEST_ID"));
				pay.setAcceptedDate(results.getTimestamp("ACCEPTED_DATE"));
				pay.setUserComment(results.getString("USER_COMMENT"));
				pay.setAdvisorComment(results.getString("ADVISOR_COMMENT"));
				payment.add(pay);
			}
		} catch (SQLException e) {
			logger.error("GetSessionInfo method of AdvisorPaymentHistoryDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("GetSessionInfo method of AdvisorPaymentHistoryDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("GetSessionInfo method of AdvisorPaymentHistoryDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetSessionInfo method of AdvisorPaymentHistoryDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return payment;

	}

	public List<PaymentDTO> GetPaymentInfoForAdmin(List<Integer> sessionId) {
		logger.info("Entered GetPaymentInfo method of AdvisorPaymentHistoryDAO");
		List<PaymentDTO> payment = new ArrayList<PaymentDTO>();
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String q4in = generateQsForIn(sessionId.size());
			String query = "SELECT SESSION_ID,TRACKING_ID,PAYMENT_MODE,DATE_OF_PAYMENT,FEE,ADVISOR_PAYMENT,PAID_TO_ADVISOR FROM payment WHERE SESSION_ID IN ( "
					+ q4in + " )";
			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(query);
			int i = 1;
			for (Integer item : sessionId) {
				pstmt.setInt(i++, item);
			}
			ResultSet results = pstmt.executeQuery();
			while (results.next()) {
				PaymentDTO pay = new PaymentDTO();
				pay.setSessionId(results.getInt("SESSION_ID"));
				pay.setTrackingId(results.getString("TRACKING_ID"));
				pay.setPaymentMode(results.getString("PAYMENT_MODE"));
				pay.setDateOfPurchase(results.getTimestamp("DATE_OF_PAYMENT"));
				pay.setAmountPayable(results.getInt("ADVISOR_PAYMENT"));
				pay.setPaidToAdvisor(results.getBoolean("PAID_TO_ADVISOR"));
				pay.setFee(results.getInt("FEE"));
				payment.add(pay);
			}
		} catch (SQLException e) {
			logger.error("GetPaymentInfo method of AdvisorPaymentHistoryDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("GetPaymentInfo method of AdvisorPaymentHistoryDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("GetPaymentInfo method of AdvisorPaymentHistoryDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetPaymentInfo method of AdvisorPaymentHistoryDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		logger.info("Exit GetPaymentInfo method of AdvisorPaymentHistoryDAO");
		return payment;

	}

	public Boolean UpdatePaidToAdvisor(String sessionId, String update) {

		logger.info("Entered UpdatePaidToAdvisor method of AdvisorPaymentHistoryDAO");
		String status = "Services.jsp";
		Boolean isOtherInfoCommit = false;
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "UPDATE payment SET PAID_TO_ADVISOR = ? WHERE SESSION_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			if (update.equals("true")) {
				pstmt.setBoolean(1, true);
			} else {
				pstmt.setBoolean(1, false);
			}

			pstmt.setString(2, sessionId);
			int result = pstmt.executeUpdate();
			if (result > 0) {
				conn.commit();
				isOtherInfoCommit = true;
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("UpdatePaidToAdvisor method of AdvisorPaymentHistoryDAO threw error:"
						+ e.getMessage());
				e1.printStackTrace();
			}
			logger.error("UpdatePaidToAdvisor method of AdvisorPaymentHistoryDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("UpdatePaidToAdvisor method of AdvisorPaymentHistoryDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("UpdatePaidToAdvisor method of AdvisorPaymentHistoryDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("UpdatePaidToAdvisor method of AdvisorPaymentHistoryDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		logger.info("Entered UpdatePaidToAdvisor method of AdvisorPaymentHistoryDAO");
		return isOtherInfoCommit;
	}

	public Boolean UpdateComment(String sessionId, String comment, String type) {
		logger.info("Entered UpdateComment method of AdvisorPaymentHistoryDAO");
		String query = "";
		Boolean isOtherInfoCommit = false;
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			if (type.equals("user")) {
				query = "UPDATE session_table SET USER_COMMENT = ? WHERE SESSION_ID = ?";
			} else {
				query = "UPDATE session_table SET ADVISOR_COMMENT = ? WHERE SESSION_ID = ?";
			}
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, comment);
			pstmt.setString(2, sessionId);
			int result = pstmt.executeUpdate();
			if (result > 0) {
				conn.commit();
				isOtherInfoCommit = true;
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("UpdateComment method of AdvisorPaymentHistoryDAO threw error:"
						+ e.getMessage());
				e1.printStackTrace();
			}
			logger.error("UpdateComment method of AdvisorPaymentHistoryDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("UpdateComment method of AdvisorPaymentHistoryDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("UpdateComment method of AdvisorPaymentHistoryDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("UpdateComment method of AdvisorPaymentHistoryDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		logger.info("Entered UpdateComment method of AdvisorPaymentHistoryDAO");
		return isOtherInfoCommit;
	}

	private String generateQsForIn(int numQs) {
		String items = "";
		for (int i = 0; i < numQs; i++) {
			if (i != 0)
				items += ", ";
			items += "?";
		}
		return items;
	}

}
