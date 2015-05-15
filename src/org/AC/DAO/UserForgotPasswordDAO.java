package org.AC.DAO;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.AC.JDBC.ConnectionFactory;
import org.AC.JDBC.Util;
import org.AC.dto.UserDetailsDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class UserForgotPasswordDAO {

	Connection conn = null;
	Statement stmt = null;
	private static final Logger logger = Logger
			.getLogger(UserForgotPasswordDAO.class);

	public Boolean setForgotPasswordDetails(int userId, String email) {

		logger.info("Entered setForgotPasswordDetails method of UserForgotPasswordDAO");
		Boolean isInsertComplete = false;
		try {
			Calendar mbCal = new GregorianCalendar(TimeZone.getTimeZone("IST"));
			mbCal.setTimeInMillis(new Date().getTime());
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, mbCal.get(Calendar.YEAR));
			cal.set(Calendar.MONTH, mbCal.get(Calendar.MONTH));
			cal.set(Calendar.DAY_OF_MONTH, mbCal.get(Calendar.DAY_OF_MONTH));
			cal.set(Calendar.HOUR_OF_DAY, mbCal.get(Calendar.HOUR_OF_DAY));
			cal.set(Calendar.MINUTE, mbCal.get(Calendar.MINUTE));
			cal.set(Calendar.SECOND, mbCal.get(Calendar.SECOND));
			cal.set(Calendar.MILLISECOND, mbCal.get(Calendar.MILLISECOND));
			Date date = cal.getTime();
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "INSERT INTO forgotpassword_user(USER_ID,TIME,EMAIL)"
					+ "VALUES(?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, userId);
			pstmt.setTimestamp(2, new java.sql.Timestamp(date.getTime()));
			pstmt.setString(3, email);
			int result = pstmt.executeUpdate();
			if (result > 0) {
				conn.commit();
				isInsertComplete = true;
			}
			logger.info("Exit setForgotPasswordDetails method of UserForgotPasswordDAO");
		} catch (Exception e) {
			try {
				conn.close();
			} catch (SQLException e1) {
				logger.error("setForgotPasswordDetails method of UserForgotPasswordDAO threw error:"
						+ e.getMessage());
				e1.printStackTrace();
			}
			logger.error("setForgotPasswordDetails method of UserForgotPasswordDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("setForgotPasswordDetails method of UserForgotPasswordDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return isInsertComplete;
	}

	// This function will get the user details from the database depending upon
	// the email entered by the user in the forgot password form
	public UserDetailsDTO getForgotPasswordDetails(String email) {

		logger.info("Entered getForgotPasswordDetails method of UserForgotPasswordDAO");
		String query = "SELECT USER_ID,EMAIL FROM userdetails WHERE EMAIL = ?";
		PreparedStatement pstmt;
		ResultSet results = null;
		UserDetailsDTO user = new UserDetailsDTO();
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, email);
			results = pstmt.executeQuery();
			if (results.first()) {
				user.setUserId(results.getInt("USER_ID"));
				user.setEmail(results.getString("EMAIL"));
				conn.commit();
			}
			logger.info("Entered getForgotPasswordDetails method of UserForgotPasswordDAO");
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("getForgotPasswordDetails method of UserForgotPasswordDAO threw error:"
						+ e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("getForgotPasswordDetails method of UserForgotPasswordDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("getForgotPasswordDetails method of UserForgotPasswordDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("getForgotPasswordDetails method of UserForgotPasswordDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("getForgotPasswordDetails method of UserForgotPasswordDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}

		return user;

	}

	// This function returns the timestamp of the user to determine whether the
	// link on
	// the mail of the user is valid or not
	public Timestamp getUserTimestamp(String userId) {

		logger.info("Entered getUserTimestamp method of UserForgotPasswordDAO");
		String query = "SELECT * FROM forgotpassword_user WHERE USER_ID = ?";
		PreparedStatement pstmt;
		ResultSet results = null;
		Timestamp time = null;
		Boolean exist = false;
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			results = pstmt.executeQuery();
			exist = results.next();
			if (exist) {
				conn.commit();
				time = results.getTimestamp("TIME");
			}
			logger.info("Entered getUserTimestamp method of UserForgotPasswordDAO");
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("getUserTimestamp method of UserForgotPasswordDAO threw error:"
						+ e.getMessage());
				e1.printStackTrace();
			}
			logger.error("getUserTimestamp method of UserForgotPasswordDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("getUserTimestamp method of UserForgotPasswordDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("getUserTimestamp method of UserForgotPasswordDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("getUserTimestamp method of UserForgotPasswordDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		logger.info("Exit getUserTimestamp method of UserForgotPasswordDAO");
		return time;

	}

	// This function will update the new password entered by the advisor
	public Boolean updatePassword(String newPassword, String userId) {

		logger.info("Entered updatePassword method of UserForgotPasswordDAO");
		Boolean isInsertComplete = false;
		try {
			if (!newPassword.isEmpty() && !("").equals(newPassword)) {
				conn = ConnectionFactory.getConnection();
				conn.setAutoCommit(false);
				String query = "UPDATE userdetails SET PASSWORD = ? WHERE USER_ID = ?";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1, newPassword);
				pstmt.setString(2, userId);
				int result = pstmt.executeUpdate();
				if (result > 0) {
					conn.commit();
					isInsertComplete = true;
				}
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("updatePassword method of UserForgotPasswordDAO threw error:"
						+ e.getMessage());
				e1.printStackTrace();
			}
			logger.error("updatePassword method of UserForgotPasswordDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("updatePassword method of UserForgotPasswordDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("updatePassword method of UserForgotPasswordDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		logger.info("Exit updatePassword method of UserForgotPasswordDAO");
		return isInsertComplete;
	}

}
