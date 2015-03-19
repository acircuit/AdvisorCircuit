 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date  10/12/2014
 * ************************************************************************************************/
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
import org.AC.dto.UserDetailsDTO;
import org.AC.dto.UserRequestDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/********************************CLASS SUMMARY*****************************************************
* 
* This class do all the DB Calls for the admin requesttab.
* 
*
***************************************************************************************************/

public class AdminRequestDAO {
	private static final Logger logger = Logger.getLogger(AdminRequestDAO.class);       
	Connection conn = null;
	Statement stmt = null;
	
	/**************************************COMMENTS***************************************************
	 * This function will retrieve all the requests with status : "PENDING FOR ADMIN APPROVAL"
	 *                                                          AND "REQUEST REJECTED BY ADVISOR"
	 *   
	 *   @return :List<UserRequestDTO> requests
	 *   @param : None
	 *   		  
	 *
	 ***************************************************************************************************/
	
	public List<UserRequestDTO>  GetRequests() { 
			
			
			logger.info("Entered GetRequests method of AdminRequestDAO");
			ResultSet results = null;
			List<UserRequestDTO> requests = new ArrayList<UserRequestDTO>();
			try {
				conn =ConnectionFactory.getConnection();
				conn.setAutoCommit(false);
				String query ="SELECT * FROM userrequest WHERE STATUS = ? OR STATUS = ? OR STATUS = ? OR STATUS = ?";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1,"PENDING FOR ADMIN APPROVAL");
				pstmt.setString(2,"PENDING FOR ADVISOR APPROVAL");
				pstmt.setString(3,"REQUEST ACCEPTED BY ADVISOR");
				pstmt.setString(4,"REQUEST ACCEPTED BY ADVISOR WITH NEW DATES");
			    results = pstmt.executeQuery();
			    while(results.next()){
			    	UserRequestDTO request = new UserRequestDTO();
			    	request.setRequestId(results.getInt("REQUEST_ID"));
			    	request.setUserId(results.getInt("USER_ID"));
			    	request.setAdvisorId(results.getInt("ADVISOR_ID"));
			    	request.setService(results.getString("SERVICE"));
			    	request.setMode(results.getString("MODE_OF_COMMUNICATION"));
			    	request.setQuery(results.getString("QUERY"));
			    	request.setDuration(results.getString("DURATION"));
			    	request.setBookingTime(results.getTimestamp("BOOKING_TIME"));
			    	request.setTime1(results.getTimestamp("DATE_TIME1"));
			    	request.setTime2(results.getTimestamp("DATE_TIME2"));
			    	request.setTime3(results.getTimestamp("DATE_TIME3"));
			    	request.setTime4(results.getTimestamp("DATE_TIME4"));
			    	request.setTime5(results.getTimestamp("DATE_TIME5"));
			    	request.setTime6(results.getTimestamp("DATE_TIME6"));
			    	request.setStatus(results.getString("STATUS"));
			    	requests.add(request);
			    }
			    conn.commit();
			logger.info("Exit GetRequests method of AdminRequestDAO");
			}catch(Exception e){
				try {
					conn.rollback();
				} catch (SQLException e1) {
					try {
						conn.rollback();
					} catch (SQLException e2) {
						logger.error("GetRequests method of AdminRequestDAO threw error:"+e2.getMessage());
						e2.printStackTrace();
					}
					logger.error("GetRequests method of AdminRequestDAO threw error:"+e1.getMessage());
					e1.printStackTrace();
				}
				logger.error("GetRequests method of AdminRequestDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("GetRequests method of AdminRequestDAO threw error:"+e.getMessage());
					e.printStackTrace();
				}
			}
			return requests;
		}
	
	public List<UserDetailsDTO> getUserDetails( List<Integer> userId){
		
		
		logger.info("Entered getUserDetails method of AdminRequestDAO");
		List<UserDetailsDTO> list = new ArrayList<UserDetailsDTO>();
		if(userId.size() >0){
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
			    	//user.setEmail(results.getString("EMAIL"));
			    	user.setFullName(results.getString("FULL_NAME"));
			    	//user.setPhone(results.getString("PHONE_NUMBER"));
			    	//user.setAge(results.getString("AGE"));
			    	//user.setNationality(results.getString("NATIONALITY"));
			    	//user.setOccupation(results.getString("OCCUPATION"));
			    	//user.setImage(results.getString("IMAGE"));   	
			    	list.add(user);
			    }
			    conn.commit();
			logger.info("Exit getUserDetails method of AdminRequestDAO");
			} catch (SQLException e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					logger.error("getUserDetails method of AdminRequestDAO threw error:"+e.getMessage());
					e1.printStackTrace();
				}
				logger.error("getUserDetails method of AdminRequestDAO threw error:"+e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				logger.error("getUserDetails method of AdminRequestDAO threw error:"+e.getMessage());
				e.printStackTrace();
			} catch (PropertyVetoException e) {
				logger.error("getUserDetails method of AdminRequestDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("getUserDetails method of AdminRequestDAO threw error:"+e.getMessage());
					e.printStackTrace();
				}
			}
		}

		
		logger.info("Exit getUserDetails method of AdminRequestDAO");
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
	
	public List<AdvisorProfileDTO> getAdvisorDetailsUsingAdvisorId( List<Integer> advisorId){
		
		
		logger.info("Entered getAdvisorDetailsUsingAdvisorId method of AdminRequestDAO");
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
		logger.info("Exit getAdvisorDetailsUsingAdvisorId method of AdminRequestDAO");
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("getAdvisorDetailsUsingAdvisorId method of AdminRequestDAO threw error:"+e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("getAdvisorDetailsUsingAdvisorId method of AdminRequestDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("getAdvisorDetailsUsingAdvisorId method of AdminRequestDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("getAdvisorDetailsUsingAdvisorId method of AdminRequestDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("getAdvisorDetailsUsingAdvisorId method of AdminRequestDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}

		
		logger.info("Exit getUserDetailsUsingAdvisorId method of AdminRequestDAO");
		return list;
	}
}
