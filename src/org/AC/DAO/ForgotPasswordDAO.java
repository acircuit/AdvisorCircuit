package org.AC.DAO;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import org.AC.JDBC.Util;
import org.AC.Util.PasswordGenerator;
import org.AC.Util.PasswordHashing;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class ForgotPasswordDAO {
	
	Connection conn = null;
	Statement stmt = null;
	private static final Logger logger = Logger.getLogger(ForgotPasswordDAO.class);
	
	//This function  will put the Book a Session form details in the "userrequest" table
	public Boolean  setForgotPasswordDetails(int  advisorId,String email, String userName,String hashPassword) { 
		
		BasicConfigurator.configure();
		logger.info("Entered setForgotPasswordDetails method of ForgotPasswordDAO");
		Boolean isInsertComplete = false;
		try {
			conn =Util.connect();
			String query = "INSERT INTO forgotpassword_admin(ADVISOR_ID,USERNAME,TIME,PASSWORD,EMAIL)" + "VALUES(?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,advisorId );
			pstmt.setString(2, userName);
			pstmt.setTimestamp(3, new java.sql.Timestamp(new Date().getTime()));
			pstmt.setString(4, hashPassword);
			pstmt.setString(5, email);
			int result = pstmt.executeUpdate(); 
			if(result >0) {
				isInsertComplete = true;
			}
		logger.info("Exit setForgotPasswordDetails method of ForgotPasswordDAO");
		}catch(Exception e){
			logger.error("setForgotPasswordDetails method of ForgotPasswordDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}
		return isInsertComplete;
}
	
	//This function will get the advisor details from the database depending upon the username entered by the advisor in the forgot password form
	public ResultSet getForgotPasswordDetails(String userName){
		BasicConfigurator.configure();
		logger.info("Entered getForgotPasswordDetails method of ForgotPasswordDAO");
		conn =Util.connect();
		String query ="SELECT ADVISOR_ID,EMAIL FROM advisordetails WHERE USERNAME = ?";
		PreparedStatement pstmt;
		ResultSet results = null;
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1,userName);
			    results = pstmt.executeQuery();
			    logger.info("Entered getForgotPasswordDetails method of ForgotPasswordDAO");
			} catch (SQLException e) {
				logger.error("getForgotPasswordDetails method of ForgotPasswordDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
			
		return results;
		
	}
	//This function returns the timestamp of the advisor to determine whether the link on 
	// the mail of the advisor is valid or not
	public Timestamp getAdvisorTimestamp( String advisorId){
		BasicConfigurator.configure();
		logger.info("Entered getAdvisorTimestamp method of ForgotPasswordDAO");
		conn =Util.connect();
		String query ="SELECT * FROM forgotpassword_admin WHERE ADVISOR_ID = ?";
		PreparedStatement pstmt;
		ResultSet results = null;
		Timestamp time = null;
		Boolean exist = false;
		try {
		    pstmt = conn.prepareStatement(query);
			//pstmt.setString(1,advisorId);
		    pstmt.setInt(1, 31);
			results = pstmt.executeQuery();
		    exist = results.next();
			if(exist){
				time	=results.getTimestamp("TIME");
			}
		    logger.info("Entered getAdvisorTimestamp method of ForgotPasswordDAO");
		} catch (SQLException e) {
			logger.error("getAdvisorTimestamp method of ForgotPasswordDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}		
		logger.info("Exit checkLinkValid method of ForgotPasswordDAO");
		return time;

	}
	
	//This function will update the new password entered by the advisor
	public Boolean updatePassword(String newPassword, String advisorId){
		logger.info("Entered getAdvisorTimestamp method of ForgotPasswordDAO");
		Boolean isInsertComplete = false;
		try{
			if(!newPassword.isEmpty() && !("").equals(newPassword)){
				conn =Util.connect();
				String query = "UPDATE advisordetails SET PASSWORD = ? WHERE ADVISOR_ID = ?";
				PreparedStatement pstmt = conn.prepareStatement(query);
			    pstmt.setString(1, newPassword);
			    pstmt.setString(2, advisorId);
			    int result = pstmt.executeUpdate(); 
				if(result >0) {
					isInsertComplete = true;
				}
			}
		}catch (SQLException e) {
			logger.error("updatePassword method of ForgotPasswordDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}catch (Exception e) {
				logger.error("updatePassword method of ForgotPasswordDAO threw error:"+e.getMessage());
				e.printStackTrace();
		}
		logger.info("Exit checkLinkValid method of ForgotPasswordDAO");
		return isInsertComplete;	
	}
}
