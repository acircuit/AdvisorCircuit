package org.AC.DAO;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.AC.JDBC.Util;
import org.AC.controller.User_RegistrationController;
import org.AC.dto.AdvisorNewDatesDTO;
import org.AC.dto.SessionDTO;
import org.AC.dto.UserDetailsDTO;
import org.AC.dto.UserRequestDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class UserDetailsDAO {
	
	private static final Logger logger = Logger.getLogger(UserDetailsDAO.class);
	Connection conn = null;
	Statement stmt = null; 
	
	//This method will put the user details retrieved from the form in the userdetails table
	public Boolean setUserDetails(String email,String hashPassword,String fullname,String phone,String age,String occupation,String nationality,String absolutePath){
		
		BasicConfigurator.configure();
		logger.info("Entered setUserDetails method of UserDetailsDAO");
		int result = 0;
		Boolean isDetailsCommit = false;
		try {
		conn =Util.connect();
		String query = "insert into userdetails"+"(EMAIL,PASSWORD,FULL_NAME,PHONE_NUMBER,AGE,NATIONALITY,OCCUPATION,IMAGE) values" + "(?,?,?,?,?,?,?,?)";
			 PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1,email);
			pstmt.setString(2,hashPassword);
			pstmt.setString(3,fullname);
			pstmt.setString(4,phone);
			pstmt.setString(5,age);
			pstmt.setString(6,occupation);
			pstmt.setString(7,nationality);
			pstmt.setString(8,absolutePath);
		    result = pstmt.executeUpdate();
		    if(result > 0){
		    	conn.commit();
		    	isDetailsCommit = true;
		    }
		logger.info("Exit setUserDetails method of UserDetailsDAO");
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("setUserDetails method of UserDetailsDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}
			logger.error("setUserDetails method of UserDetailsDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}
		return isDetailsCommit;
	}
	
	
	public List<UserDetailsDTO> getUserDetails( List<Integer> userId){
		
		BasicConfigurator.configure();
		logger.info("Entered getUserDetails method of UserDetailsDAO");
		List<UserDetailsDTO> list = new ArrayList<UserDetailsDTO>();
		conn =Util.connect();
		try {
			String q4in = generateQsForIn(userId.size());			
			String query ="SELECT * FROM userdetails WHERE USER_ID IN ( " + q4in + " )";
			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(query);
			int i = 1;
			  for (Integer item : userId) {
				  pstmt.setInt(i++, item);
			  }
		    ResultSet results = pstmt.executeQuery();
		    while(results.next()){
		    	UserDetailsDTO user = new UserDetailsDTO();
		    	user.setUserId(results.getInt("USER_ID"));
		    	user.setEmail(results.getString("EMAIL"));
		    	user.setFullName(results.getString("FULL_NAME"));
		    	user.setPhone(results.getString("PHONE_NUMBER"));
		    	user.setAge(results.getString("AGE"));
		    	user.setNationality(results.getString("NATIONALITY"));
		    	user.setOccupation(results.getString("OCCUPATION"));
		    	user.setImage(results.getString("IMAGE"));   	
		    	list.add(user);
		    }
		    conn.commit();
		logger.info("Exit getUserDetails method of UserDetailsDAO");
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("getUserDetails method of MyAccountRequestDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}
			logger.error("getUserDetails method of MyAccountRequestDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}

		
		logger.info("Exit getUserDetails method of MyAccountRequestDAO");
		return list;
	}
	
	private String generateQsForIn(int numQs) {
	    String items = "";
	    for (int i = 0; i < numQs; i++) {
	        if (i != 0) items += ", ";
	        items += "?";
	    }
	    return items;
	}
	
	/**************************************COMMENTS***************************************************
	 * This function will get the user request details using status
	 *   @return :None
	 *   @param : HttpServletRequest request
	 *   		  HttpServletResponse response
	 *   		  
	 *
	 ***************************************************************************************************/
	
	public List<UserRequestDTO> getUserRequestDetails( String status , int advisorId){
		
		BasicConfigurator.configure();
		logger.info("Entered getUserRequestDetails method of UserDetailsDAO");
		List<UserRequestDTO> list = new ArrayList<UserRequestDTO>();
		conn =Util.connect();
		try {
			String query ="SELECT * FROM userrequest WHERE STATUS = ? AND ADVISOR_ID = ?";
			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, status);
			pstmt.setInt(2, advisorId);
		    ResultSet results = pstmt.executeQuery();
		    while(results.next()){
		    	UserRequestDTO user =  new UserRequestDTO();
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
		    	user.setTime5(results.getTimestamp("DATE_TIME5"));
		    	user.setTime6(results.getTimestamp("DATE_TIME6"));
		    	user.setStatus(results.getString("STATUS"));	    	
		    	list.add(user);
		    }
		    conn.commit();
		logger.info("Exit getUserRequestDetails method of UserDetailsDAO");
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("getUserRequestDetails method of MyAccountRequestDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}
			logger.error("getUserRequestDetails method of MyAccountRequestDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}

		
		logger.info("Exit getUserRequestDetails method of MyAccountRequestDAO");
		return list;
	}
	
	public List<SessionDTO> getSessionDetails( String requestId ){
		
		BasicConfigurator.configure();
		logger.info("Entered getSessionDetails method of UserDetailsDAO");
		List<SessionDTO> list = new ArrayList<SessionDTO>();
		conn =Util.connect();
		try {
			String query ="SELECT * FROM session_table WHERE REQUEST_ID = ?";
			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, requestId);
		    ResultSet results = pstmt.executeQuery();
		    if(results.next()){
		    	conn.commit();
		    	SessionDTO session = new SessionDTO();
		    	session.setSessionId(results.getInt("SESSION_ID"));
		    	session.setRequestId(results.getInt("REQUEST_ID"));
		    	session.setAdvisorId(results.getInt("ADVISOR_ID"));
		    	session.setUserId(results.getInt("USER_ID"));
		    	session.setSessionPlan(results.getString("SESSION_PLAN"));
		    	session.setAcceptedDate(results.getTimestamp("ACCEPTED_DATE"));
		    	session.setStatus(results.getString("STATUS"));
		    	list.add(session);
		    }
		    conn.commit();
		logger.info("Exit getSessionDetails method of UserDetailsDAO");
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("getSessionDetails method of MyAccountRequestDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}
			logger.error("getSessionDetails method of MyAccountRequestDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}

		
		logger.info("Exit getSessionDetails method of MyAccountRequestDAO");
		return list;
	}
	
	public List<AdvisorNewDatesDTO> getNewDates( int sessionId ){
		
		BasicConfigurator.configure();
		logger.info("Entered getNewDates method of UserDetailsDAO");
		List<AdvisorNewDatesDTO> list = new ArrayList<AdvisorNewDatesDTO>();
		conn =Util.connect();
		try {
			String query ="SELECT * FROM advisor_new_dates WHERE SESSION_ID = ?";
			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, sessionId);
		    ResultSet results = pstmt.executeQuery();
		    if(results.next()){
		    	conn.commit();
		    	AdvisorNewDatesDTO dates = new AdvisorNewDatesDTO();
		    	dates.setSessionId(results.getInt("SESSION_ID"));
		    	dates.setNewDate1(results.getTimestamp("NEW_DATE1"));
		    	dates.setNewDate2(results.getTimestamp("NEW_DATE2"));
		    	dates.setNewDate3(results.getTimestamp("NEW_DATE3"));
		    	list.add(dates);
		    }
		    conn.commit();
		logger.info("Exit getNewDates method of UserDetailsDAO");
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("getNewDates method of MyAccountRequestDAO threw error:"+e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("getNewDates method of MyAccountRequestDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}

		
		logger.info("Exit getNewDates method of MyAccountRequestDAO");
		return list;
	}
	
	/**************************************COMMENTS***************************************************
	 * This function will get the user request details using status
	 *   @return :List<UserRequestDTO> list
	 *   @param : HttpServletRequest request
	 *   		  HttpServletResponse response
	 *   		  
	 *
	 ***************************************************************************************************/
	
	public List<UserRequestDTO> getUserRequestDetailsUsingUserId( String status , int userId){
		
		BasicConfigurator.configure();
		logger.info("Entered getUserRequestDetailsUsingUserId method of UserDetailsDAO");
		List<UserRequestDTO> list = new ArrayList<UserRequestDTO>();
		conn =Util.connect();
		try {
			String query ="SELECT * FROM userrequest WHERE STATUS = ? AND USER_ID = ?";
			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, status);
			pstmt.setInt(2, userId);
		    ResultSet results = pstmt.executeQuery();
		    while(results.next()){
		    	UserRequestDTO user =  new UserRequestDTO();
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
		    	user.setTime5(results.getTimestamp("DATE_TIME5"));
		    	user.setTime6(results.getTimestamp("DATE_TIME6"));
		    	user.setStatus(results.getString("STATUS"));	    	
		    	list.add(user);
		    }
		    conn.commit();
		logger.info("Exit getUserRequestDetailsUsingUserId method of UserDetailsDAO");
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("getUserRequestDetailsUsingUserId method of UserDetailsDAO threw error:"+e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("getUserRequestDetailsUsingUserId method of UserDetailsDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}

		
		logger.info("Exit getUserRequestDetailsUsingUserId method of UserDetailsDAO");
		return list;
	}
}

