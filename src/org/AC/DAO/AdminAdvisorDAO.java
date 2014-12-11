 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 08/12/2014
 * ************************************************************************************************/
package org.AC.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.AC.JDBC.Util;
import org.AC.controller.AdvisorProfileServlet;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/********************************CLASS SUMMARY*****************************************************
* 
* This class do all the DB Calls for the admin advisor tab.
* 
*
***************************************************************************************************/

public class AdminAdvisorDAO {
	private static final Logger logger = Logger.getLogger(AdminAdvisorDAO.class);       
	Connection conn = null;
	Statement stmt = null;
	
	/**************************************COMMENTS***************************************************
	 * This function will retrieve the Advisor Id from the email Id
	 *   
	 *   @return :ResultSet results
	 *   @param : String email
	 *   		  
	 *
	 ***************************************************************************************************/
	
	public int  GetAdvisorId(String email) { 
		
		BasicConfigurator.configure();
		logger.info("Entered GetAdvisorId method of AdminAdvisorDAO");
		ResultSet results = null;
		int advisorId = 0 ;
		try {
			conn =Util.connect();
			String query ="SELECT ADVISOR_ID,NAME FROM advisordetails WHERE EMAIL = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1,email);
		    results = pstmt.executeQuery();
		    if(results.first()){
		    	conn.commit();
		    	advisorId = results.getInt("ADVISOR_ID");
		    }
		logger.info("Exit setForgotPasswordDetails method of AdvisorLoginDAO");
		}catch(Exception e){
			try {
				conn.rollback();
				conn.close();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("setForgotPasswordDetails method of AdvisorLoginDAO threw error:"+e.getMessage());
					e2.printStackTrace();
				}
				logger.error("setForgotPasswordDetails method of UserForgotPasswordDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}
			logger.error("setForgotPasswordDetails method of UserForgotPasswordDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}
		return advisorId;
	}
	
	
	/**************************************COMMENTS***************************************************
	 * This function will deactivate the advisor by setting the isActive flag of the advisor to false  
	 *   
	 *   @return :Boolean isFlagCommit
	 *   @param : String aId
	 *   		  
	 *
	 ***************************************************************************************************/
	
	public Boolean  SetAdvisorFlag(String aId) { 
		
		BasicConfigurator.configure();
		logger.info("Entered SetAdvisorFlag method of AdminAdvisorDAO");
		Boolean isFlagCommit = false ;
		try {
			conn =Util.connect();
			String query ="UPDATE advisordetails SET ISACTIVE = ? WHERE ADVISOR_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1, false);
			pstmt.setString(2,aId);
			int result = pstmt.executeUpdate(); 
			if(result >0) {
				conn.commit();
				isFlagCommit = true;
			}
			conn.close();
		logger.info("Exit SetAdvisorFlag method of AdvisorLoginDAO");
		}catch(Exception e){
			try {
				conn.rollback();
				conn.close();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("SetAdvisorFlag method of AdvisorLoginDAO threw error:"+e2.getMessage());
					e2.printStackTrace();
				}
				logger.error("SetAdvisorFlag method of UserForgotPasswordDAO threw error:"+e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("SetAdvisorFlag method of UserForgotPasswordDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}
		return isFlagCommit;
	}
}
