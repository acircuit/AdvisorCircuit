package org.AC.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.AC.JDBC.Util;
import org.AC.dto.AdvisorProfileDTO;
import org.AC.dto.SessionDTO;
import org.AC.dto.UserDetailsDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class AdvisorMyAccountSessionDAO {
	private static final Logger logger = Logger.getLogger(AdvisorMyAccountRequestViewDetailsDAO.class);
	Connection conn = null;
	Statement stmt = null; 
	
	public List<SessionDTO> getSessionDetails(int aId, String status){
		
		BasicConfigurator.configure();
		logger.info("Entered setSessionDetails method of AdvisorMyAccountRequestViewDetailsDAO");
		java.util.List<SessionDTO> list = new ArrayList<SessionDTO>();
		if( aId != 0 && status != null && !("").equals(status)){	
			try {
				conn =Util.connect();
				String query ="SELECT * FROM session_table WHERE ADVISOR_ID=? AND STATUS = ?";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, aId);
				pstmt.setString(2, status);
				ResultSet results = pstmt.executeQuery();
				while(results.next()){
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
			}
		return list;
	}
public List<SessionDTO> getSessionDetails(String rId){
		
		BasicConfigurator.configure();
		logger.info("Entered setSessionDetails method of AdvisorMyAccountRequestViewDetailsDAO");
		java.util.List<SessionDTO> list = new ArrayList<SessionDTO>();
		if(  rId != null && !("").equals(rId)){	
			try {
				conn =Util.connect();
				String query ="SELECT * FROM session_table WHERE REQUEST_ID = ?";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1, rId);
				ResultSet results = pstmt.executeQuery();
				while(results.next()){
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
			}
		return list;
	}
	
public List<SessionDTO> getSessionDetailsUsingUserId(int uId, String status){
	
	BasicConfigurator.configure();
	logger.info("Entered getSessionDetailsUsingUserId method of AdvisorMyAccountRequestViewDetailsDAO");
	java.util.List<SessionDTO> list = new ArrayList<SessionDTO>();
	if( uId != 0 && status != null && !("").equals(status)){	
		try {
			conn =Util.connect();
			String query ="SELECT * FROM session_table WHERE USER_ID=? AND STATUS = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, uId);
			pstmt.setString(2, status);
			ResultSet results = pstmt.executeQuery();
			while(results.next()){
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
			logger.info("Exit getSessionDetailsUsingUserId method of UserDetailsDAO");
			} catch (SQLException e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					logger.error("getSessionDetailsUsingUserId method of UserDetailsDAO threw error:"+e.getMessage());
					e1.printStackTrace();
				}
				logger.error("getSessionDetailsUsingUserId method of UserDetailsDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
	return list;
}

	/**************************************COMMENTS***************************************************
 * This method will fetch all the session details where advisorId = aId and  status1 = "SESSION CANCELLED DUE TO ADVISOR UNAVAILABILITY";
 *			 status2 = "SESSION CANCELLED DUE TO ADVISOR NO SHOW";
 *			 status3 = "SESSION CANCELLED DUE TO USER UNAVAILABILITY";
 *			 status4 = "SESSION CANCELLED DUE TO NO USER PAYMENT";
 *			 status5 = "SESSION REJECTED BY USER"; 
 *   
 *   @return :List<SessionDTO> list
 *   @param : HttpServletRequest request
 *   		  HttpServletResponse response
 *   		  
 *
 ***************************************************************************************************/
public List<SessionDTO> getSessionDetails(int aId, String status1, String status2,String status3,String status4,String status5){
	
	BasicConfigurator.configure();
	logger.info("Entered setSessionDetails method of AdvisorMyAccountRequestViewDetailsDAO");
	java.util.List<SessionDTO> list = new ArrayList<SessionDTO>();
	if( aId != 0 ){	
		try {
			conn =Util.connect();
			String query ="SELECT * FROM session_table WHERE ADVISOR_ID=? AND STATUS = ? OR STATUS = ? OR STATUS = ? OR STATUS = ? OR STATUS = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, aId);
			pstmt.setString(2, status1);
			pstmt.setString(3, status2);
			pstmt.setString(4, status3);
			pstmt.setString(5, status4);
			pstmt.setString(6, status5);
			ResultSet results = pstmt.executeQuery();
			while(results.next()){
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
		}
	return list;
}

/**************************************COMMENTS***************************************************
* This method will fetch all the session details where advisorId = aId and  status1 = "SESSION CANCELLED DUE TO ADVISOR UNAVAILABILITY";
*			 status2 = "SESSION CANCELLED DUE TO ADVISOR NO SHOW";
*			 status3 = "SESSION CANCELLED DUE TO USER UNAVAILABILITY";
*			 status4 = "SESSION CANCELLED DUE TO NO USER PAYMENT";
*			 status5 = "SESSION REJECTED BY USER"; 
*   
*   @return :List<SessionDTO> list
*   @param : HttpServletRequest request
*   		  HttpServletResponse response
*   		  
*
***************************************************************************************************/
public List<SessionDTO> getSessionDetailsUsingUserId(int uId, String status1, String status2,String status3,String status4,String status5){

BasicConfigurator.configure();
logger.info("Entered setSessionDetails method of AdvisorMyAccountRequestViewDetailsDAO");
java.util.List<SessionDTO> list = new ArrayList<SessionDTO>();
if( uId != 0 ){	
	try {
		conn =Util.connect();
		String query ="SELECT * FROM session_table WHERE USER_ID=? AND STATUS = ? OR STATUS = ? OR STATUS = ? OR STATUS = ? OR STATUS = ?";
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, uId);
		pstmt.setString(2, status1);
		pstmt.setString(3, status2);
		pstmt.setString(4, status3);
		pstmt.setString(5, status4);
		pstmt.setString(6, status5);
		ResultSet results = pstmt.executeQuery();
		while(results.next()){
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
		logger.info("Exit setUserDetails method of AdvisorMyAccountRequestViewDetailsDAO");
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("setUserDetails method of AdvisorMyAccountRequestViewDetailsDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}
			logger.error("setUserDetails method of AdvisorMyAccountRequestViewDetailsDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}
	}
return list;
}
public List<AdvisorProfileDTO> getAdvisorDetailsUsingAdvisorId( List<Integer> advisorId){
	
	BasicConfigurator.configure();
	logger.info("Entered getAdvisorDetailsUsingAdvisorId method of AdvisorMyAccountRequestViewDetailsDAO");
	List<AdvisorProfileDTO> list = new ArrayList<AdvisorProfileDTO>();
	conn =Util.connect();
	try {
		String q4in = generateQsForIn(advisorId.size());			
		String query ="SELECT * FROM advisordetails WHERE ADVISOR_ID IN ( " + q4in + " )";
		PreparedStatement pstmt;
		pstmt = conn.prepareStatement(query);
		int i = 1;
		  for (Integer item : advisorId) {
			  pstmt.setInt(i++, item);
		  }
	    ResultSet results = pstmt.executeQuery();
	    while(results.next()){
	    	AdvisorProfileDTO profile = new AdvisorProfileDTO();
	    	profile.setName(results.getString("NAME"));
	    	profile.setAdvisorId(results.getInt("ADVISOR_ID"));  	
	    	list.add(profile);
	    }
	    conn.commit();
	logger.info("Exit getAdvisorDetailsUsingAdvisorId method of AdvisorMyAccountRequestViewDetailsDAO");
	} catch (SQLException e) {
		try {
			conn.rollback();
		} catch (SQLException e1) {
			logger.error("getAdvisorDetailsUsingAdvisorId method of AdvisorMyAccountRequestViewDetailsDAO threw error:"+e1.getMessage());
			e1.printStackTrace();
		}
		logger.error("getAdvisorDetailsUsingAdvisorId method of AdvisorMyAccountRequestViewDetailsDAO threw error:"+e.getMessage());
		e.printStackTrace();
	}

	
	logger.info("Exit getUserDetailsUsingAdvisorId method of AdvisorMyAccountRequestViewDetailsDAO");
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
}
