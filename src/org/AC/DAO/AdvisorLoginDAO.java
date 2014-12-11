 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 29/11/2014
 * ************************************************************************************************/
package org.AC.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.AC.JDBC.Util;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/* *******************************CLASS SUMMARY****************************************************
* 
* This DAO class will make all the DB calls after the advisor Login
* 
* 
*
***************************************************************************************************/

public class AdvisorLoginDAO {
	

	Connection conn = null;
	Statement stmt = null;
	private static final Logger logger = Logger.getLogger(AdvisorLoginDAO.class);
	
	/**************************************COMMENTS***************************************************
	 * This function will retrieve the login credentials and check for valid credentials.Upon successfull validations
	 *   sets the cookie and session and then redirects it to the MyAccount Page
	 *   
	 *   @return :ResultSet results
	 *   @param : String username
	 *   		  String securedPassword
	 *   		  
	 *
	 ***************************************************************************************************/
		public ResultSet  CheckLoginDetails(String email , String securedPassword) { 
			
			BasicConfigurator.configure();
			logger.info("Entered setForgotPasswordDetails method of AdvisorLoginDAO");
			ResultSet results = null;
			try {

				conn =Util.connect();
				String query ="SELECT ADVISOR_ID,NAME FROM advisordetails WHERE EMAIL = ? AND PASSWORD = ? AND ISACTIVE = ?";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1,email);
				pstmt.setString(2,securedPassword);
				pstmt.setBoolean(3, true);
			    results = pstmt.executeQuery();
				conn.commit();
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
			return results;
	}
	
}
