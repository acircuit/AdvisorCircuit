package org.AC.DAO;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.AC.JDBC.ConnectionFactory;
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
	public Integer setUserDetails(String email,String hashPassword,String fullname,String phone,String age,String occupation,String absolutePath){
		logger.info("Entered setUserDetails method of UserDetailsDAO");
		int result = 0;
		int userId = 0;
		Boolean isDetailsCommit = false;
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
		try {
	        conn =ConnectionFactory.getConnection();
	 		conn.setAutoCommit(false);
	 		String query = "insert into userdetails"+"(EMAIL,PASSWORD,FULL_NAME,PHONE_NUMBER,AGE,OCCUPATION,IMAGE,DATE_OF_REGISTRATION) values" + "(?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS );
			pstmt.setString(1,email);
			pstmt.setString(2,hashPassword);
			pstmt.setString(3,fullname);
			pstmt.setString(4,phone);
			pstmt.setString(5,age);
			pstmt.setString(6,occupation);
			pstmt.setString(7,absolutePath);
			Timestamp time = new java.sql.Timestamp(date.getTime());
			pstmt.setTimestamp(8, time);
		    result = pstmt.executeUpdate();
		    if(result > 0){
		    	conn.commit();
		    	ResultSet generatedKeys = pstmt.getGeneratedKeys();
				if (null != generatedKeys && generatedKeys.next()) {
					userId = generatedKeys.getInt(1);
				}
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
		} catch (IOException e) {
			logger.error("setUserDetails method of UserDetailsDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("setUserDetails method of UserDetailsDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("setUserDetails method of UserDetailsDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}	
		return userId;
	}
	
	
	public List<UserDetailsDTO> getUserDetails( List<Integer> userId){
		
		
		logger.info("Entered getUserDetails method of UserDetailsDAO");
		List<UserDetailsDTO> list = new ArrayList<UserDetailsDTO>();
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
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
		} catch (IOException e) {
			logger.error("getUserDetails method of MyAccountRequestDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("getUserDetails method of MyAccountRequestDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("getUserDetails method of MyAccountRequestDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
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
	 *   @return :List<UserRequestDTO> list
	 *   @param : HttpServletRequest request
	 *   		  HttpServletResponse response
	 *   		  
	 *
	 ***************************************************************************************************/
	
	public List<UserRequestDTO> getUserRequestDetailsUsingUserId( ArrayList<String> requestStatus , int userId){
		
		
		logger.info("Entered getUserRequestDetailsUsingUserId method of UserDetailsDAO");
		List<UserRequestDTO> list = new ArrayList<UserRequestDTO>();
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			
			String q4in = generateQsForIn(requestStatus.size());
			String query ="SELECT * FROM userrequest WHERE USER_ID = ? AND STATUS IN ( " + q4in + " )";
			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, userId);
			int i1 = 2;
			  for (String item : requestStatus) {
				  pstmt.setString(i1++, item);
			  }
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
		} catch (IOException e) {
			logger.error("getUserRequestDetailsUsingUserId method of UserDetailsDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("getUserRequestDetailsUsingUserId method of UserDetailsDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("getUserRequestDetailsUsingUserId method of UserDetailsDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}	

		
		logger.info("Exit getUserRequestDetailsUsingUserId method of UserDetailsDAO");
		return list;
	}
	
	
	/**************************************COMMENTS***************************************************
	 * This function will get the user request details using status
	 *   @return :None
	 *   @param : HttpServletRequest request
	 *   		  HttpServletResponse response
	 *   		  
	 *
	 ***************************************************************************************************/
	
	public List<UserRequestDTO> getUserRequestDetails( ArrayList<String> requestStatus , int advisorId){
		logger.info("Entered getUserRequestDetails method of UserDetailsDAO");
		List<UserRequestDTO> list = new ArrayList<UserRequestDTO>();
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String q4in = generateQsForIn(requestStatus.size());
			String query ="SELECT * FROM userrequest WHERE ADVISOR_ID = ? AND STATUS IN ( " + q4in + " )";
			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, advisorId);
			int i1 = 2;
			  for (String item : requestStatus) {
				  pstmt.setString(i1++, item);
			  }
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
		} catch (IOException e) {
			logger.error("getUserRequestDetails method of MyAccountRequestDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("getUserRequestDetails method of MyAccountRequestDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("getUserRequestDetails method of MyAccountRequestDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}	

		
		logger.info("Exit getUserRequestDetails method of MyAccountRequestDAO");
		return list;
	}
	
	public List<SessionDTO> getSessionDetails( String requestId ){
		
		
		logger.info("Entered getSessionDetails method of UserDetailsDAO");
		List<SessionDTO> list = new ArrayList<SessionDTO>();
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
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
		} catch (IOException e) {
			logger.error("getSessionDetails method of MyAccountRequestDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("getSessionDetails method of MyAccountRequestDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("getSessionDetails method of MyAccountRequestDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}	

		
		logger.info("Exit getSessionDetails method of MyAccountRequestDAO");
		return list;
	}
	
	public List<AdvisorNewDatesDTO> getNewDates( int sessionId ){
		
		
		logger.info("Entered getNewDates method of UserDetailsDAO");
		List<AdvisorNewDatesDTO> list = new ArrayList<AdvisorNewDatesDTO>();
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT * FROM advisor_new_dates WHERE SESSION_ID = ?";
			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, sessionId);
		    ResultSet results = pstmt.executeQuery();
		    if(results.next()){
		    	AdvisorNewDatesDTO dates = new AdvisorNewDatesDTO();
		    	dates.setSessionId(results.getInt("SESSION_ID"));
		    	if(results.getTimestamp("NEW_DATE1") != null){
		    	dates.setNewDate1(results.getTimestamp("NEW_DATE1"));
		    	}
		    	if(results.getTimestamp("NEW_DATE2") != null){
		    	dates.setNewDate2(results.getTimestamp("NEW_DATE2"));
		    	}
		    	if(results.getTimestamp("NEW_DATE3") != null){
		    	dates.setNewDate3(results.getTimestamp("NEW_DATE3"));
		    	}
		    	list.add(dates);
		    }
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
		} catch (IOException e) {
			logger.error("getNewDates method of MyAccountRequestDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("getNewDates method of MyAccountRequestDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("getNewDates method of MyAccountRequestDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}	

		
		logger.info("Exit getNewDates method of MyAccountRequestDAO");
		return list;
	}
	
	
	
	/**************************************COMMENTS***************************************************
	 * This function will check if the user is new or not
	 *   @return :Boolean isNewUser
	 *   @param : String email
	 *   		  
	 *
	 ***************************************************************************************************/
	
	public Boolean CheckRegistraion( String email){
		
		
		logger.info("Entered CheckRegistraion method of UserDetailsDAO");
		List<UserRequestDTO> list = new ArrayList<UserRequestDTO>();
		Boolean isNewUser = true;
		int userId = 0;
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT USER_ID FROM userdetails WHERE EMAIL=?";
			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,email);
		    ResultSet results = pstmt.executeQuery();
		    if(results.next()){
		    	isNewUser = false;
		    }
		    conn.commit();
		logger.info("Exit CheckRegistraion method of UserDetailsDAO");
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("CheckRegistraion method of UserDetailsDAO threw error:"+e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("CheckRegistraion method of UserDetailsDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("CheckRegistraion method of UserDetailsDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("CheckRegistraion method of UserDetailsDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("CheckRegistraion method of UserDetailsDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}	

		
		logger.info("Exit CheckRegistraion method of UserDetailsDAO");
		return isNewUser;
	}
	
	
	public Boolean  ActivateUser(String aId) { 
			
			
			logger.info("Entered ActivateUser method of UserDetailsDAO");
			Boolean isFlagCommit = false ;
			Boolean flag = true;
			try {
				conn =ConnectionFactory.getConnection();
				conn.setAutoCommit(false);
				String query ="UPDATE userdetails SET ISACTIVE = ? WHERE USER_ID = ?";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setBoolean(1, flag);
				pstmt.setString(2,aId);
				int result = pstmt.executeUpdate(); 
				if(result >0) {
					conn.commit();
					isFlagCommit = true;
				}
			logger.info("Exit ActivateUser method of UserDetailsDAO");
			}catch(Exception e){
				try {
					conn.rollback();
				} catch (SQLException e1) {
					try {
						conn.rollback();
					} catch (SQLException e2) {
						logger.error("ActivateUser method of UserDetailsDAO threw error:"+e2.getMessage());
						e2.printStackTrace();
					}
					logger.error("ActivateUser method of UserDetailsDAO threw error:"+e1.getMessage());
					e1.printStackTrace();
				}
				logger.error("ActivateUser method of UserDetailsDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("ActivateUser method of UserDetailsDAO threw error:"+e.getMessage());
					e.printStackTrace();
				}
			}
			return isFlagCommit;
		}
	
	public Boolean SetHereToHelpDetails(String email,String phone){
		logger.info("Entered SetHereToHelpDetails method of UserDetailsDAO");
		int result = 0;
		Boolean isDetailsCommit = false;
		try {
	        conn =ConnectionFactory.getConnection();
	 		conn.setAutoCommit(false);
	 		String query1 = "insert into heretohelp"+"(EMAIL,PHONE) values" + "(?,?)";
			PreparedStatement pstmt = conn.prepareStatement(query1);
			pstmt.setString(1,email);
			pstmt.setString(2,phone);
		    result = pstmt.executeUpdate();
		    if(result > 0){
		    	conn.commit();
		    	isDetailsCommit= true;
		    }
		logger.info("Exit SetHereToHelpDetails method of UserDetailsDAO");
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("SetHereToHelpDetails method of UserDetailsDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}
			logger.error("SetHereToHelpDetails method of UserDetailsDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("SetHereToHelpDetails method of UserDetailsDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("SetHereToHelpDetails method of UserDetailsDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("SetHereToHelpDetails method of UserDetailsDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}	
		return isDetailsCommit;
	}
	
	public Boolean CheckUserIsFree( int uId){
		logger.info("Entered CheckUserIsFree method of UserDetailsDAO");
		List<UserRequestDTO> list = new ArrayList<UserRequestDTO>();
		Boolean isFree = false;
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT ISFREE FROM userdetails WHERE USER_ID=?";
			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,uId);
		    ResultSet results = pstmt.executeQuery();
		    if(results.next()){
		    	isFree = results.getBoolean("ISFREE");
		    }
		logger.info("Exit CheckUserIsFree method of UserDetailsDAO");
		} catch (SQLException e) {
				logger.error("CheckUserIsFree method of UserDetailsDAO threw error:"+e.getMessage());
				e.printStackTrace();
		} catch (IOException e) {
			logger.error("CheckUserIsFree method of UserDetailsDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("CheckUserIsFree method of UserDetailsDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("CheckUserIsFree method of UserDetailsDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}	

		
		logger.info("Exit CheckRegistraion method of UserDetailsDAO");
		return isFree;
	}
	
}

