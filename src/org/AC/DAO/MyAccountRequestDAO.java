 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 29/11/2014
 * ************************************************************************************************/
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
import org.AC.dto.AdvisorProfileDTO;
import org.AC.dto.UserDetailsDTO;
import org.AC.dto.UserRequestDTO;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;


/* *******************************CLASS SUMMARY****************************************************
* 
* This class will make all DB Class related to get the request details
* 
* 
*
***************************************************************************************************/
public class MyAccountRequestDAO {
	private static final Logger logger = Logger.getLogger(MyAccountRequestDAO.class);  
	Connection conn = null;
	
	public List<UserRequestDTO> getAdvisorRequestDetails(int advisorId){
		BasicConfigurator.configure();
		logger.info("Entered getAdvisorRequestDetails method of MyAccountRequestDAO");
		List<UserRequestDTO> list = new ArrayList<UserRequestDTO>();
		conn =Util.connect();
		String query ="SELECT * FROM userrequest WHERE ADVISOR_ID = ?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, advisorId);
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
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("getAdvisorRequestDetails method of MyAccountRequestDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}
			logger.error("getAdvisorRequestDetails method of MyAccountRequestDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}

		
		logger.info("Exit getAdvisorRequestDetails method of MyAccountRequestDAO");
		return list;
	}
	
	public List<UserRequestDTO> getUserRequestDetails(String requestId){
		BasicConfigurator.configure();
		logger.info("Entered getUserRequestDetails method of MyAccountRequestDAO");
		List<UserRequestDTO> list = new ArrayList<UserRequestDTO>();
		conn =Util.connect();
		String query ="SELECT * FROM userrequest WHERE REQUEST_ID = ?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, requestId);
		    ResultSet results = pstmt.executeQuery();
		    if(results.next()){
		    	conn.commit();
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
	
	public List<AdvisorProfileDTO> getAdvisorName(int advisorId){
		BasicConfigurator.configure();
		logger.info("Entered getAdvisorName method of MyAccountRequestDAO");
		List<AdvisorProfileDTO> list = new ArrayList<AdvisorProfileDTO>();
		String name = "";
		conn =Util.connect();
		String query ="SELECT NAME,IMAGE FROM advisordetails WHERE ADVISOR_ID = ?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, advisorId);
		    ResultSet results = pstmt.executeQuery();
		    if(results.next()){
		    	conn.commit();
		    	AdvisorProfileDTO advisor = new AdvisorProfileDTO();
		    	advisor.setName(results.getString("NAME"));
		    	advisor.setImage(results.getString("IMAGE"));
		    	list.add(advisor);
		    }
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("getAdvisorName method of MyAccountRequestDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}
			logger.error("getAdvisorName method of MyAccountRequestDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}

		
		logger.info("Exit getAdvisorName method of MyAccountRequestDAO");
		return list;
	}
	
	public List<UserDetailsDTO> getUserImage(int userId){
		BasicConfigurator.configure();
		logger.info("Entered getUserImage method of MyAccountRequestDAO");
		String image = "";
		List<UserDetailsDTO> list = new ArrayList<UserDetailsDTO>();
		conn =Util.connect();
		String query ="SELECT IMAGE,FULL_NAME FROM userdetails WHERE USER_ID = ?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, userId);
		    ResultSet results = pstmt.executeQuery();
		    if(results.next()){
		    	conn.commit();
		    	UserDetailsDTO user = new UserDetailsDTO();
		    	user.setImage(results.getString("IMAGE"));
		    	user.setFullName(results.getString("FULL_NAME"));
		    	list.add(user);
		    }
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("getUserImage method of MyAccountRequestDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}
			logger.error("getUserImage method of MyAccountRequestDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}

		
		logger.info("Exit getUserImage method of MyAccountRequestDAO");
		return list;
	}
	
	public List<UserRequestDTO> getRequestDetails(int userId){
		BasicConfigurator.configure();
		logger.info("Entered getRequestDetails method of MyAccountRequestDAO");
		List<UserRequestDTO> list = new ArrayList<UserRequestDTO>();
		conn =Util.connect();
		String query ="SELECT * FROM userrequest WHERE USER_ID = ?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, userId);
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
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("getRequestDetails method of MyAccountRequestDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}
			logger.error("getRequestDetails method of MyAccountRequestDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}

		
		logger.info("Exit getRequestDetails method of MyAccountRequestDAO");
		return list;
	}
	
	public List<AdvisorProfileDTO> getAdvisorImage(int advisorId){
		BasicConfigurator.configure();
		logger.info("Entered getAdvisorImage method of MyAccountRequestDAO");
		String image = "";
		List<AdvisorProfileDTO> list = new ArrayList<AdvisorProfileDTO>();
		conn =Util.connect();
		String query ="SELECT IMAGE,NAME FROM advisordetails WHERE ADVISOR_ID = ?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, advisorId);
		    ResultSet results = pstmt.executeQuery();
		    if(results.next()){
		    	conn.commit();
		    	AdvisorProfileDTO advisor = new AdvisorProfileDTO();
		    	advisor.setImage(results.getString("IMAGE"));
		    	advisor.setName(results.getString("NAME"));
		    	list.add(advisor);
		    }
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("getAdvisorImage method of MyAccountRequestDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}
			logger.error("getAdvisorImage method of MyAccountRequestDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}

		
		logger.info("Exit getAdvisorImage method of MyAccountRequestDAO");
		return list;
	}
	
	
	
 	/**************************************COMMENTS***************************************************
	 * Getting request Id from the session table where STATUS = "PENDING FOR USER PAYMENT" AND "PENDING FOR USER PAYMENT WITH NEW DATES"
	 *   
	 *   @return :ArrayList<Integer> id
	 *   @param : None
	 *   		  
	 *
	 ***************************************************************************************************/
	public List<Integer> getRequestId(int advisorId){
		BasicConfigurator.configure();
		logger.info("Entered getRequestId method of MyAccountRequestDAO");
    	int[] array = new int[1];
    	List<Integer> list = new ArrayList<Integer>();
    	int i=0;
		conn =Util.connect();
		String query ="SELECT REQUEST_ID FROM session_table WHERE STATUS = ? OR STATUS = ?  AND ADVISOR_ID = ?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "PENDING FOR USER PAYMENT");
			pstmt.setString(2, "PENDING FOR USER PAYMENT WITH NEW DATES");
			pstmt.setInt(3, advisorId);
		    ResultSet results = pstmt.executeQuery();
		    		while(results.next()){
		    			list.add(results.getInt("REQUEST_ID"));
		    		//array[i] =  ;
		    		//i++;
		    	}
		    	
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("getRequestId method of MyAccountRequestDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}
			logger.error("getRequestId method of MyAccountRequestDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}

		
		logger.info("Exit getRequestId method of MyAccountRequestDAO");
		return list;
	}
	

 	/**************************************COMMENTS***************************************************
	 * Getting request details from the request table where id IN int[] id
	 *   
	 *   @return :List<UserRequestDTO> list
	 *   @param : int[] id
	 *   		  
	 *
	 ***************************************************************************************************/
	public List<UserRequestDTO> getRequestDetails(List<Integer> id){
		BasicConfigurator.configure();
		logger.info("Entered getRequestDetails method of MyAccountRequestDAO");
		List<UserRequestDTO> list = new ArrayList<UserRequestDTO>();
		String userId = null;
		if(id.size() > 0){
			conn =Util.connect();
			/*for (int i = 0; i < id.length ; i++){
				String ids = id[i] +","+ 3;
				userId = ids;
			}*/
			//System.out.println(userId);
			try {
				//String parameters = StringUtils.join(id.iterator(),','); 
				//System.out.println(parameters);
				//Array array = conn.createArrayOf("INTEGER", id.toArray());
				String q4in = generateQsForIn(id.size());			
				String query ="SELECT * FROM userrequest WHERE REQUEST_ID IN ( " + q4in + " )";
				PreparedStatement pstmt;
				pstmt = conn.prepareStatement(query);
				//Array array = conn.createArrayOf("INT", list.toArray());
				//pstmt.setString(1, parameters);
				int i = 1;
				  for (Integer item : id) {
					  pstmt.setInt(i++, item);
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
				    	user.setTime5(results.getTimestamp("DATE_TIME5"));
				    	user.setTime6(results.getTimestamp("DATE_TIME6"));
				    	user.setStatus(results.getString("STATUS"));	    	
				    	list.add(user);
				    }
				   conn.commit();
			} catch (SQLException e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					logger.error("getRequestId method of MyAccountRequestDAO threw error:"+e1.getMessage());
					e1.printStackTrace();
				}
				logger.error("getRequestId method of MyAccountRequestDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}

		}
		logger.info("Exit getAdvisorImage method of MyAccountRequestDAO");
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

	public List<AdvisorProfileDTO> getAdvisorDetails(List<Integer> advisorId){
		BasicConfigurator.configure();
		logger.info("Entered getAdvisorDetails method of MyAccountRequestDAO");
		List<AdvisorProfileDTO> list = new ArrayList<AdvisorProfileDTO>();
		String userId = null;
		if(advisorId.size() > 0){
			conn =Util.connect();
			/*for (int i = 0; i < id.length ; i++){
				String ids = id[i] +","+ 3;
				userId = ids;
			}*/
			//System.out.println(userId);
			try {
				//String parameters = StringUtils.join(id.iterator(),','); 
				//System.out.println(parameters);
				//Array array = conn.createArrayOf("INTEGER", id.toArray());
				String q4in = generateQsForIn(advisorId.size());			
				String query ="SELECT * FROM advisordetails WHERE ADVISOR_ID IN ( " + q4in + " )";
				PreparedStatement pstmt;
				pstmt = conn.prepareStatement(query);
				//Array array = conn.createArrayOf("INT", list.toArray());
				//pstmt.setString(1, parameters);
				int i = 1;
				  for (Integer item : advisorId) {
					  pstmt.setInt(i++, item);
				  }
			    ResultSet results = pstmt.executeQuery();
			    	while(results.next()){
				    	AdvisorProfileDTO profile = new AdvisorProfileDTO();
				    	profile.setAdvisorId(results.getInt("ADVISOR_ID"));
				    	profile.setName(results.getString("NAME"));
				    	list.add(profile);
				    }
				   conn.commit();
			} catch (SQLException e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					logger.error("getAdvisorDetails method of MyAccountRequestDAO threw error:"+e1.getMessage());
					e1.printStackTrace();
				}
				logger.error("getAdvisorDetails method of MyAccountRequestDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}

		}
		logger.info("Exit getAdvisorDetails method of MyAccountRequestDAO");
		return list;
	}

 	/**************************************COMMENTS***************************************************
	 * Getting the CV Path
	 *   
	 *   @return :String requestId
	 *   @param : String cvPath
	 *   		  
	 *
	 ***************************************************************************************************/
	public String getCvPath(String requestId){
		BasicConfigurator.configure();
		logger.info("Entered getCvPath method of MyAccountRequestDAO");
		String cvPath = "";
		conn =Util.connect();
		String query ="SELECT CV FROM user_cv WHERE REQUEST_ID = ?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, requestId);
		    ResultSet results = pstmt.executeQuery();
		    if(results.next()) {
		    	conn.commit();
		    	cvPath = results.getString("CV");
		    }		    	
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("getRequestId method of MyAccountRequestDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}
			logger.error("getRequestId method of MyAccountRequestDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}

		
		logger.info("Exit getRequestId method of MyAccountRequestDAO");
		return cvPath;
	}
}
