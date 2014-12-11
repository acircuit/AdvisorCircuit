 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 10/12/2014
 * ************************************************************************************************/
package org.AC.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.AC.JDBC.Util;
import org.AC.dto.UserDetailsDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/********************************CLASS SUMMARY*****************************************************
* 
* This class do all the DB Calls for the admin user tab.
* 
*
***************************************************************************************************/

public class AdminUserDAO {
	private static final Logger logger = Logger.getLogger(AdminAdvisorDAO.class);       
	Connection conn = null;
	Statement stmt = null;
	
	/**************************************COMMENTS***************************************************
	 * This function will retrieve the user details using email Id.
	 *   
	 *   @return :
	 *   @param : String email
	 *   		  
	 *
	 ***************************************************************************************************/
	
public UserDetailsDTO  GetUserDetails(String email) { 
		
		BasicConfigurator.configure();
		logger.info("Entered GetUserDetails method of AdminUserDAO");
		ResultSet results = null;
    	UserDetailsDTO user = new UserDetailsDTO();
		try {
			conn =Util.connect();
			String query ="SELECT * FROM userdetails WHERE EMAIL = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1,email);
		    results = pstmt.executeQuery();
		    if(results.first()){
		    	user.setUserId(results.getInt("USER_ID"));
		    	user.setEmail(results.getString("EMAIL"));
		    	user.setFullName(results.getString("FULL_NAME"));
		    	user.setPhone(results.getString("PHONE_NUMBER"));
		    	user.setAge(results.getString("AGE"));
		    	user.setNationality(results.getString("NATIONALITY"));
		    	user.setOccupation(results.getString("OCCUPATION"));
		    	user.setImage(results.getString("IMAGE"));
		    	conn.commit();
		    }
		logger.info("Exit GetUserDetails method of AdminUserDAO");
		}catch(Exception e){
			try {
				conn.rollback();
				conn.close();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("GetUserDetails method of AdminUserDAO threw error:"+e.getMessage());
					e2.printStackTrace();
				}
				logger.error("GetUserDetails method of AdminUserDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}
			logger.error("GetUserDetails method of AdminUserDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}
		return user;
	}

/**************************************COMMENTS***************************************************
 * This function will deactivate the user by setting the isActive flag of the user to false  
 *   
 *   @return :Boolean isFlagCommit
 *   @param : String uId
 *   		  
 *
 ***************************************************************************************************/

public Boolean  SetUserFlag(String uId) { 
	
	BasicConfigurator.configure();
	logger.info("Entered SetUserFlag method of AdminUserDAO");
	Boolean isFlagCommit = false ;
	try {
		conn =Util.connect();
		String query ="UPDATE userdetails SET ISACTIVE = ? WHERE USER_ID = ?";
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setBoolean(1, false);
		pstmt.setString(2,uId);
		int result = pstmt.executeUpdate(); 
		if(result >0) {
			conn.commit();
			isFlagCommit = true;
		}
		conn.close();
	logger.info("Exit SetUserFlag method of AdminUserDAO");
	}catch(Exception e){
		try {
			conn.rollback();
			conn.close();
		} catch (SQLException e1) {
			try {
				conn.rollback();
			} catch (SQLException e2) {
				logger.error("SetUserFlag method of AdminUserDAO threw error:"+e2.getMessage());
				e2.printStackTrace();
			}
			logger.error("SetUserFlag method of AdminUserDAO threw error:"+e1.getMessage());
			e1.printStackTrace();
		}
		logger.error("SetUserFlag method of AdminUserDAO threw error:"+e.getMessage());
		e.printStackTrace();
	}
	return isFlagCommit;
}
}
