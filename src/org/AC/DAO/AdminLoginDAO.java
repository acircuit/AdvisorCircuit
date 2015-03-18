package org.AC.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.AC.JDBC.ConnectionFactory;
import org.AC.JDBC.Util;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class AdminLoginDAO {
	Connection conn = null;
	Statement stmt = null;
	private static final Logger logger = Logger.getLogger(AdminLoginDAO.class);
	
	public Boolean  CheckLoginDetails(String username , String securedPassword) { 
		
		
		logger.info("Entered CheckLoginDetails method of AdminLoginDAO");
		ResultSet results = null;
		Boolean isAutheticated = false;
		try {

			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT * FROM admin WHERE EMAIL = ? AND PASSWORD = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1,username);
			pstmt.setString(2,securedPassword);
		    results = pstmt.executeQuery();
		    if(results.first()){
		    	isAutheticated = true;
		    }
			conn.commit();
			
		logger.info("Exit CheckLoginDetails method of AdminLoginDAO");
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("CheckLoginDetails method of AdminLoginDAO threw error:"+e.getMessage());
					e2.printStackTrace();
				}
				logger.error("CheckLoginDetails method of AdminLoginDAO threw error:"+e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("CheckLoginDetails method of AdminLoginDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("CheckLoginDetails method of AdminLoginDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		return isAutheticated;
}
}
