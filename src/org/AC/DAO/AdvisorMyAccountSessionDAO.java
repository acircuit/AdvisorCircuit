package org.AC.DAO;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.AC.JDBC.ConnectionFactory;
import org.AC.JDBC.Util;
import org.AC.dto.AdvisorProfileDTO;
import org.AC.dto.SessionDTO;
import org.AC.dto.UserDetailsDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class AdvisorMyAccountSessionDAO {
	private static final Logger logger = Logger.getLogger(AdvisorMyAccountSessionDAO.class);
	Connection conn = null;
	Statement stmt = null; 
	
	public List<SessionDTO> getSessionDetails(int aId, String status) {
		
		
		logger.info("Entered getSessionDetails method of AdvisorMyAccountSessionDAO");
		java.util.List<SessionDTO> list = new ArrayList<SessionDTO>();
		if( aId != 0 && status != null && !("").equals(status)){	
			try {
				conn =ConnectionFactory.getConnection();
				conn.setAutoCommit(false);
				String query ="SELECT * FROM session_table WHERE ADVISOR_ID=? AND STATUS = ? ORDER BY ACCEPTED_DATE";
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
				logger.info("Exit getSessionDetails method of AdvisorMyAccountSessionDAO");
				} catch (SQLException e) {
					try {
						conn.rollback();
					} catch (SQLException e1) {
						logger.error("getSessionDetails method of AdvisorMyAccountSessionDAO threw error:"+e.getMessage());
						e1.printStackTrace();
					}
					logger.error("getSessionDetails method of AdvisorMyAccountSessionDAO threw error:"+e.getMessage());
					e.printStackTrace();
				} catch (IOException e) {
					logger.error("getSessionDetails method of AdvisorMyAccountSessionDAO threw error:"+e.getMessage());
					e.printStackTrace();
				} catch (PropertyVetoException e) {
					logger.error("getSessionDetails method of AdvisorMyAccountSessionDAO threw error:"+e.getMessage());
					e.printStackTrace();
				}finally{
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("getSessionDetails method of AdvisorMyAccountSessionDAO threw error:"+e.getMessage());
						e.printStackTrace();
					}
				}
			}
		return list;
	}
public List<SessionDTO> getSessionDetails(String rId){
		
		
		logger.info("Entered getSessionDetails method of AdvisorMyAccountSessionDAO");
		java.util.List<SessionDTO> list = new ArrayList<SessionDTO>();
		if(  rId != null && !("").equals(rId)){	
			try {
				conn =ConnectionFactory.getConnection();
				conn.setAutoCommit(false);
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
					session.setModeDetails(results.getString("MODE_DETAILS"));
					list.add(session);
					}
					conn.commit();
				logger.info("Exit getSessionDetails method of AdvisorMyAccountSessionDAO");
				} catch (SQLException e) {
					try {
						conn.rollback();
					} catch (SQLException e1) {
						logger.error("getSessionDetails method of AdvisorMyAccountSessionDAO threw error:"+e.getMessage());
						e1.printStackTrace();
					}
					logger.error("getSessionDetails method of AdvisorMyAccountSessionDAO threw error:"+e.getMessage());
					e.printStackTrace();
				} catch (IOException e) {
					logger.error("getSessionDetails method of AdvisorMyAccountSessionDAO threw error:"+e.getMessage());
					e.printStackTrace();
				} catch (PropertyVetoException e) {
					logger.error("getSessionDetails method of AdvisorMyAccountSessionDAO threw error:"+e.getMessage());
					e.printStackTrace();
				}finally{
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("getSessionDetails method of AdvisorMyAccountSessionDAO threw error:"+e.getMessage());
						e.printStackTrace();
					}
				}
			}
		return list;
	}
	
public List<SessionDTO> getSessionDetailsUsingUserId(int uId, String status){
	
	
	logger.info("Entered getSessionDetailsUsingUserId method of AdvisorMyAccountSessionDAO");
	java.util.List<SessionDTO> list = new ArrayList<SessionDTO>();
	if( uId != 0 && status != null && !("").equals(status)){	
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT * FROM session_table WHERE USER_ID=? AND STATUS = ? ORDER BY ACCEPTED_DATE";
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
				session.setReviewMessage(results.getString("REVIEW_MESSAGE"));
				session.setIsRating(results.getBoolean("RATING"));
				session.setReviewMessageStatus(results.getString("REVIEW_MESSAGE_STATUS"));
				list.add(session);
				}
				conn.commit();
			logger.info("Exit  DESC method of AdvisorMyAccountSessionDAO");
			} catch (SQLException e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					logger.error("getSessionDetailsUsingUserId method of AdvisorMyAccountSessionDAO threw error:"+e.getMessage());
					e1.printStackTrace();
				}
				logger.error("getSessionDetailsUsingUserId method of AdvisorMyAccountSessionDAO threw error:"+e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				logger.error("getSessionDetailsUsingUserId method of AdvisorMyAccountSessionDAO threw error:"+e.getMessage());
				e.printStackTrace();
			} catch (PropertyVetoException e) {
				logger.error("getSessionDetailsUsingUserId method of AdvisorMyAccountSessionDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("getSessionDetailsUsingUserId method of AdvisorMyAccountSessionDAO threw error:"+e.getMessage());
					e.printStackTrace();
				}
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
 *			 status6 = "SESSION CANCELLED DUE TO USER NO SHOW";
 *   
 *   @return :List<SessionDTO> list
 *   @param : HttpServletRequest request
 *   		  HttpServletResponse response
 *   		  
 *
 ***************************************************************************************************/
public List<SessionDTO> getSessionDetails(int aId, ArrayList<String> status){
	
	
	logger.info("Entered getSessionDetails method of AdvisorMyAccountSessionDAO");
	java.util.List<SessionDTO> list = new ArrayList<SessionDTO>();
	if( aId != 0 ){	
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String q4in = generateQsForIn(status.size());
			String query ="SELECT * FROM session_table WHERE  ADVISOR_ID=? AND STATUS IN ( " + q4in + " )";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, aId);
			int i1 = 2;
			  for (String item : status) {
				  pstmt.setString(i1++, item);
			  }
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
			logger.info("Exit getSessionDetails method of AdvisorMyAccountSessionDAO");
			} catch (SQLException e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					logger.error("getSessionDetails method of AdvisorMyAccountSessionDAO threw error:"+e.getMessage());
					e1.printStackTrace();
				}
				logger.error("getSessionDetails method of AdvisorMyAccountSessionDAO threw error:"+e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				logger.error("getSessionDetails method of AdvisorMyAccountSessionDAO threw error:"+e.getMessage());
				e.printStackTrace();
			} catch (PropertyVetoException e) {
				logger.error("getSessionDetails method of AdvisorMyAccountSessionDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("getSessionDetails method of AdvisorMyAccountSessionDAO threw error:"+e.getMessage());
					e.printStackTrace();
				}
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
public List<SessionDTO> getSessionDetailsUsingUserId(int uId,ArrayList<String> status){


logger.info("Entered getSessionDetailsUsingUserId method of AdvisorMyAccountSessionDAO");
java.util.List<SessionDTO> list = new ArrayList<SessionDTO>();
if( uId != 0 ){	
	try {
		conn =ConnectionFactory.getConnection();
		conn.setAutoCommit(false);
		String q4in = generateQsForIn(status.size());
		String query ="SELECT * FROM session_table WHERE  USER_ID=? AND STATUS IN ( " + q4in + " )";
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, uId);
		int i1 = 2;
		  for (String item : status) {
			  pstmt.setString(i1++, item);
		  }
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
		logger.info("Exit getSessionDetailsUsingUserId method of AdvisorMyAccountSessionDAO");
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("getSessionDetailsUsingUserId method of AdvisorMyAccountSessionDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}
			logger.error("getSessionDetailsUsingUserId method of AdvisorMyAccountSessionDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("getSessionDetailsUsingUserId method of AdvisorMyAccountSessionDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("getSessionDetailsUsingUserId method of AdvisorMyAccountSessionDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("getSessionDetailsUsingUserId method of AdvisorMyAccountSessionDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
	}
return list;
}
public List<AdvisorProfileDTO> getAdvisorDetailsUsingAdvisorId( List<Integer> advisorId){
	
	
	logger.info("Entered getAdvisorDetailsUsingAdvisorId method of AdvisorMyAccountSessionDAO");
	List<AdvisorProfileDTO> list = new ArrayList<AdvisorProfileDTO>();
	
	try {
		conn =ConnectionFactory.getConnection();
		conn.setAutoCommit(false);
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
	logger.info("Exit getAdvisorDetailsUsingAdvisorId method of AdvisorMyAccountSessionDAO");
	} catch (SQLException e) {
		try {
			conn.rollback();
		} catch (SQLException e1) {
			logger.error("getAdvisorDetailsUsingAdvisorId method of AdvisorMyAccountSessionDAO threw error:"+e1.getMessage());
			e1.printStackTrace();
		}
		logger.error("getAdvisorDetailsUsingAdvisorId method of AdvisorMyAccountSessionDAO threw error:"+e.getMessage());
		e.printStackTrace();
	} catch (IOException e) {
		logger.error("getAdvisorDetailsUsingAdvisorId method of AdvisorMyAccountSessionDAO threw error:"+e.getMessage());
		e.printStackTrace();
	} catch (PropertyVetoException e) {
		logger.error("getAdvisorDetailsUsingAdvisorId method of AdvisorMyAccountSessionDAO threw error:"+e.getMessage());
		e.printStackTrace();
	}finally{
		try {
			conn.close();
		} catch (SQLException e) {
			logger.error("getAdvisorDetailsUsingAdvisorId method of AdvisorMyAccountSessionDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}
	}

	
	logger.info("Exit getUserDetailsUsingAdvisorId method of AdvisorMyAccountSessionDAO");
	return list;
}

public Boolean SetFormStatus(String sId){
		
	
	logger.info("Entered SetFormStatus method of AdvisorMyAccountSessionDAO");
	Boolean isCommit = false;
	
	try {
		conn =ConnectionFactory.getConnection();
		conn.setAutoCommit(false);
		String query = "UPDATE feedback  SET SUBMITTED = ? WHERE SESSION_ID = ?";
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setBoolean(1, true);
		pstmt.setString(2,sId);
		int result = pstmt.executeUpdate(); 
		if(result >0) {
			conn.commit();
			isCommit = true;
			}
	} catch (SQLException e) {
		try {
			conn.rollback();
		} catch (SQLException e1) {
			logger.error("SetFormStatus method of AdvisorMyAccountSessionDAO threw error:"+e.getMessage());
			e1.printStackTrace();
		}	
		logger.error("SetFormStatus method of AdvisorMyAccountSessionDAO threw error:"+e.getMessage());
		e.printStackTrace();
	} catch (IOException e) {
		logger.error("SetFormStatus method of AdvisorMyAccountSessionDAO threw error:"+e.getMessage());
		e.printStackTrace();
	} catch (PropertyVetoException e) {
		logger.error("SetFormStatus method of AdvisorMyAccountSessionDAO threw error:"+e.getMessage());
		e.printStackTrace();
	}finally{
		try {
			conn.close();
		} catch (SQLException e) {
			logger.error("SetFormStatus method of AdvisorMyAccountSessionDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}
	}	
	logger.info("Entered SetFormStatus method of AdvisorMyAccountSessionDAO");
	return isCommit;
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
