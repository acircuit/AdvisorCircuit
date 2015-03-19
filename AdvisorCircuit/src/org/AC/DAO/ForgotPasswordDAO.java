package org.AC.DAO;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
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
import org.AC.Util.PasswordGenerator;
import org.AC.Util.PasswordHashing;
import org.AC.dto.AdvisorProfileDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class ForgotPasswordDAO {
	
	Connection conn = null;
	Statement stmt = null;
	private static final Logger logger = Logger.getLogger(ForgotPasswordDAO.class);
	
	//This function  will put the Book a Session form details in the "userrequest" table
	public Boolean  setForgotPasswordDetails(int  advisorId,String email) { 
		
		
		logger.info("Entered setForgotPasswordDetails method of ForgotPasswordDAO");
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
	         conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "INSERT INTO forgotpassword_admin(ADVISOR_ID,TIME,EMAIL)" + "VALUES(?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,advisorId );
			pstmt.setTimestamp(2, new java.sql.Timestamp(date.getTime()));
			pstmt.setString(3, email);
			int result = pstmt.executeUpdate(); 
			if(result >0) {
				conn.commit();
				isInsertComplete = true;
			}
		logger.info("Exit setForgotPasswordDetails method of ForgotPasswordDAO");
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("setForgotPasswordDetails method of ForgotPasswordDAO threw error:"+e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("setForgotPasswordDetails method of ForgotPasswordDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("setForgotPasswordDetails method of ForgotPasswordDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		return isInsertComplete;
}
	
	//This function will get the advisor details from the database depending upon the username entered by the advisor in the forgot password form
	public AdvisorProfileDTO getForgotPasswordDetails(String userName,String register){
		
		logger.info("Entered getForgotPasswordDetails method of ForgotPasswordDAO");
		
		PreparedStatement pstmt;
		ResultSet results = null;
		String query="";
		AdvisorProfileDTO profile = new AdvisorProfileDTO();
		Boolean flag = true;
			try {
				conn =ConnectionFactory.getConnection();
				conn.setAutoCommit(false);
				if(register.equals("true")){
					query ="SELECT ADVISOR_ID,EMAIL FROM advisordetails WHERE EMAIL = ?";
				}else{
					query ="SELECT ADVISOR_ID,EMAIL FROM advisordetails WHERE EMAIL = ? AND ISACTIVE= ?";
				}
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1,userName);
				if(!register.equals("true")){
					pstmt.setBoolean(2, flag);
				}
			    results = pstmt.executeQuery();
			    if(results.first()){
			    	profile.setAdvisorId(results.getInt("ADVISOR_ID"));
			    	profile.setEmail(results.getString("EMAIL"));
			    }
			    logger.info("Entered getForgotPasswordDetails method of ForgotPasswordDAO");
			} catch (SQLException e) {
				logger.error("getForgotPasswordDetails method of ForgotPasswordDAO threw error:"+e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				logger.error("getForgotPasswordDetails method of ForgotPasswordDAO threw error:"+e.getMessage());
				e.printStackTrace();
			} catch (PropertyVetoException e) {
				logger.error("getForgotPasswordDetails method of ForgotPasswordDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("getForgotPasswordDetails method of ForgotPasswordDAO threw error:"+e.getMessage());
					e.printStackTrace();
				}
			}
			
		return profile;
		
	}
	//This function returns the timestamp of the advisor to determine whether the link on 
	// the mail of the advisor is valid or not
	public Timestamp getAdvisorTimestamp( String advisorId){
		
		logger.info("Entered getAdvisorTimestamp method of ForgotPasswordDAO");

		PreparedStatement pstmt;
		ResultSet results = null;
		Timestamp time = null;
		Boolean exist = false;
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT * FROM forgotpassword_admin WHERE ADVISOR_ID = ?";
		    pstmt = conn.prepareStatement(query);
			pstmt.setString(1,advisorId);
			results = pstmt.executeQuery();
		    exist = results.next();
			if(exist){
				time	=results.getTimestamp("TIME");
			}
		    logger.info("Entered getAdvisorTimestamp method of ForgotPasswordDAO");
		} catch (SQLException e) {
			logger.error("getAdvisorTimestamp method of ForgotPasswordDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("getAdvisorTimestamp method of ForgotPasswordDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("getAdvisorTimestamp method of ForgotPasswordDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("getAdvisorTimestamp method of ForgotPasswordDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
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
				conn =ConnectionFactory.getConnection();
				conn.setAutoCommit(false);
				String query = "UPDATE advisordetails SET PASSWORD = ? WHERE ADVISOR_ID = ?";
				PreparedStatement pstmt = conn.prepareStatement(query);
			    pstmt.setString(1, newPassword);
			    pstmt.setString(2, advisorId);
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
				logger.error("updatePassword method of ForgotPasswordDAO threw error:"+e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("updatePassword method of ForgotPasswordDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}catch (Exception e) {
				logger.error("updatePassword method of ForgotPasswordDAO threw error:"+e.getMessage());
				e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("updatePassword method of ForgotPasswordDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}	
		logger.info("Exit updatePassword method of ForgotPasswordDAO");
		return isInsertComplete;	
	}
}
