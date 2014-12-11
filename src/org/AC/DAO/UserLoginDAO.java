package org.AC.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.AC.JDBC.Util;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class UserLoginDAO {
	
	
	Connection conn = null;
	Statement stmt = null;
	private static final Logger logger = Logger.getLogger(UserLoginDAO.class);
	
	
		public ResultSet  CheckLoginDetails(String username , String securedPassword) { 
			
			BasicConfigurator.configure();
			logger.info("Entered CheckLoginDetails method of UserLoginDAO");
			ResultSet results = null;
			try {

				conn =Util.connect();
				String query ="SELECT USER_ID FROM userdetails WHERE EMAIL = ? AND PASSWORD = ? AND ISACTIVE=?";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1,username);
				pstmt.setString(2,securedPassword);
				pstmt.setBoolean(3, true);
			    results = pstmt.executeQuery();
				conn.commit();
			logger.info("Exit CheckLoginDetails method of UserLoginDAO");
			}catch(Exception e){
				try {
					conn.rollback();
					conn.close();
				} catch (SQLException e1) {
					try {
						conn.rollback();
					} catch (SQLException e2) {
						logger.error("CheckLoginDetails method of UserLoginDAO threw error:"+e.getMessage());
						e2.printStackTrace();
					}
					logger.error("CheckLoginDetails method of UserLoginDAO threw error:"+e.getMessage());
					e1.printStackTrace();
				}
				logger.error("CheckLoginDetails method of UserLoginDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
			return results;
	}
	
}
