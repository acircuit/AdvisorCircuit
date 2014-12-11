package org.AC.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import org.AC.JDBC.Util;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class UserForgotPasswordDAO {
	
	
	Connection conn = null;
	Statement stmt = null;
	private static final Logger logger = Logger.getLogger(UserForgotPasswordDAO.class);
	
	
		public Boolean  setForgotPasswordDetails(int  userId,String email) { 
			
			BasicConfigurator.configure();
			logger.info("Entered setForgotPasswordDetails method of UserForgotPasswordDAO");
			Boolean isInsertComplete = false;
			try {
				conn =Util.connect();
				String query = "INSERT INTO forgotpassword_user(USER_ID,TIME,EMAIL)" + "VALUES(?,?,?)";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setInt(1,userId );
				pstmt.setTimestamp(2, new java.sql.Timestamp(new Date().getTime()));
				pstmt.setString(3, email);
				int result = pstmt.executeUpdate(); 
				if(result >0) {
					conn.commit();
					isInsertComplete = true;
				}
			logger.info("Exit setForgotPasswordDetails method of UserForgotPasswordDAO");
			}catch(Exception e){
				try {
					conn.close();
				} catch (SQLException e1) {
					logger.error("setForgotPasswordDetails method of UserForgotPasswordDAO threw error:"+e.getMessage());
					e1.printStackTrace();
				}
				logger.error("setForgotPasswordDetails method of UserForgotPasswordDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
			return isInsertComplete;
	}
	
	
	//This function will get the user details from the database depending upon the email entered by the user in the forgot password form
	public ResultSet getForgotPasswordDetails(String email){
		BasicConfigurator.configure();
		logger.info("Entered getForgotPasswordDetails method of UserForgotPasswordDAO");
		conn =Util.connect();
		String query ="SELECT USER_ID,EMAIL FROM userdetails WHERE EMAIL = ?";
		PreparedStatement pstmt;
		ResultSet results = null;
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1,email);
			    results = pstmt.executeQuery();
			    conn.commit();
			    logger.info("Entered getForgotPasswordDetails method of UserForgotPasswordDAO");
			} catch (SQLException e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					logger.error("getForgotPasswordDetails method of UserForgotPasswordDAO threw error:"+e1.getMessage());
					e1.printStackTrace();
				}
				logger.error("getForgotPasswordDetails method of UserForgotPasswordDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
			
		return results;
		
	}
	//This function returns the timestamp of the user to determine whether the link on 
	// the mail of the user is valid or not
	public Timestamp getUserTimestamp( String userId){
		BasicConfigurator.configure();
		logger.info("Entered getAdvisorTimestamp method of UserForgotPasswordDAO");
		conn =Util.connect();
		String query ="SELECT * FROM forgotpassword_user WHERE USER_ID = ?";
		PreparedStatement pstmt;
		ResultSet results = null;
		Timestamp time = null;
		Boolean exist = false;
		try {
		    pstmt = conn.prepareStatement(query);
		    pstmt.setString(1, userId);
			results = pstmt.executeQuery();
		    exist = results.next();
			if(exist){
				conn.commit();
				time	=results.getTimestamp("TIME");
			}
		    logger.info("Entered getAdvisorTimestamp method of UserForgotPasswordDAO");
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("getAdvisorTimestamp method of UserForgotPasswordDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}
			logger.error("getAdvisorTimestamp method of UserForgotPasswordDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}		
		logger.info("Exit getAdvisorTimestamp method of UserForgotPasswordDAO");
		return time;

	}
	
	//This function will update the new password entered by the advisor
	public Boolean updatePassword(String newPassword, String userId){
		BasicConfigurator.configure();
		logger.info("Entered updatePassword method of UserForgotPasswordDAO");
		Boolean isInsertComplete = false;
		try{
			if(!newPassword.isEmpty() && !("").equals(newPassword)){
				conn =Util.connect();
				String query = "UPDATE userdetails SET PASSWORD = ? WHERE USER_ID = ?";
				PreparedStatement pstmt = conn.prepareStatement(query);
			    pstmt.setString(1, newPassword);
			    pstmt.setString(2, userId);
			    int result = pstmt.executeUpdate(); 
				if(result >0) {
					conn.commit();
					isInsertComplete = true;
				}
			}
		}catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("updatePassword method of UserForgotPasswordDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}
			logger.error("updatePassword method of UserForgotPasswordDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}catch (Exception e) {
				logger.error("updatePassword method of UserForgotPasswordDAO threw error:"+e.getMessage());
				e.printStackTrace();
		}
		logger.info("Exit updatePassword method of UserForgotPasswordDAO");
		return isInsertComplete;	
	}

}
