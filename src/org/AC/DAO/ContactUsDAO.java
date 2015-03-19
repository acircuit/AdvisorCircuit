 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date  13/12/2014
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
import org.AC.dto.SessionDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/********************************CLASS SUMMARY*****************************************************
* 
* This class do all the DB Calls for the contact us functionality.
* 
*
***************************************************************************************************/

public class ContactUsDAO {
	private static final Logger logger = Logger.getLogger(ContactUsDAO.class);       
	Connection conn = null;
	Statement stmt = null;
	
	/**************************************COMMENTS***************************************************
	 * This function will set the contact us form details in the contact us table.
	 *                                                             
	 *   @return :String name,String email,String phone,String message
	 *   @param : Boolean isCommit
	 *   		  
	 *
	 ***************************************************************************************************/
	
public Boolean  SetContactUsDetails(String name,String email,String phone,String message) { 
		logger.info("Entered SetContactUsDetails method of ContactUsDAO");
		Boolean isCommit = false;
			
			try {
				conn =ConnectionFactory.getConnection();
				conn.setAutoCommit(false);
				String query = "insert into contactus"+"(NAME,EMAIL,PHONE_NUMBER,MESSAGE) values" + "(?,?,?,?)";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1,name);
				pstmt.setString(2,email);
				pstmt.setString(3,phone);
				pstmt.setString(4,message);
				int result = pstmt.executeUpdate(); 
				if(result >0) {
					conn.commit();
					isCommit = true;
				}
				logger.info("Exit SetContactUsDetails method of ContactUsDAO");
			} catch (SQLException e) {
				logger.error("SetContactUsDetails method of ContactUsDAO threw error:"+e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				logger.error("SetContactUsDetails method of ContactUsDAO threw error:"+e.getMessage());
				e.printStackTrace();
			} catch (PropertyVetoException e) {
				logger.error("SetContactUsDetails method of ContactUsDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("SetContactUsDetails method of ContactUsDAO threw error:"+e.getMessage());
					e.printStackTrace();
				}
			}
		return isCommit;
		
	}
	
}
