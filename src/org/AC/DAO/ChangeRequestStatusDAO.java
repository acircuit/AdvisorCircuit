package org.AC.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.AC.JDBC.Util;
import org.AC.Util.ConvertStringToDate;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class ChangeRequestStatusDAO {
	
	Connection conn = null;
	Statement stmt = null;
	private static final Logger logger = Logger.getLogger(ChangeRequestStatusDAO.class);
	
	//This function  will set the request status in the "userrequest" table
	public Boolean  setStatus(String status, int requestId ) { 
		
		BasicConfigurator.configure();
		logger.info("Entered setStatus method of ChangeRequestStatusDAO");
		Boolean isStatusCommit = false;
		int result = 0;
			conn =Util.connect();
			String query = "UPDATE userrequest  SET STATUS=? WHERE REQUEST_ID = ?";
			try {
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1,status);
				pstmt.setInt(2, requestId);
				result = pstmt.executeUpdate();
			    if(result > 0){
			    	conn.commit();
			    	isStatusCommit = true;
			    }
				logger.info("Exit setBookASessionDetails method of BookASessionDAO");
			} catch (SQLException e) {
				logger.error("setBookASessionDetails method of BookASessionDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("setBookASessionDetails method of BookASessionDAO threw error:"+e.getMessage());
					e.printStackTrace();
				}
			}
			return isStatusCommit;		
	}
}
