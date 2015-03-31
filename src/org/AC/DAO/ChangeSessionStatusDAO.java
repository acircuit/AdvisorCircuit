package org.AC.DAO;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import org.AC.JDBC.ConnectionFactory;
import org.AC.JDBC.Util;
import org.AC.Util.ConvertStringToDate;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class ChangeSessionStatusDAO {


	Connection conn = null;
	Statement stmt = null;
	private static final Logger logger = Logger.getLogger(ChangeSessionStatusDAO.class);
	
	//This function  will set the session status in the "session_table" table
	public Boolean  setStatus(String status,  String sessionId ) { 
		logger.info("Entered setStatus method of ChangeSessionStatusDAO");
		Boolean isStatusCommit = false;
		int result = 0;
			
			try {
				conn =ConnectionFactory.getConnection();
				conn.setAutoCommit(false);
				String query = "UPDATE session_table  SET STATUS=? WHERE SESSION_ID = ?";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1,status);
				pstmt.setString(2, sessionId);
				result = pstmt.executeUpdate();
			    if(result > 0){
			    	conn.commit();
			    	isStatusCommit = true;
			    }
				logger.info("Exit setStatus method of ChangeSessionStatusDAO");
			} catch (SQLException e) {
				logger.error("setStatus method of ChangeSessionStatusDAO threw error:"+e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				logger.error("setStatus method of ChangeSessionStatusDAO threw error:"+e.getMessage());
				e.printStackTrace();
			} catch (PropertyVetoException e) {
				logger.error("setStatus method of ChangeSessionStatusDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("setStatus method of ChangeSessionStatusDAO threw error:"+e.getMessage());
					e.printStackTrace();
				}
			}
			return isStatusCommit;		
	}
	//This function  will set the session status in the "session_table" table
		public Boolean  setStatus(String status,  String sessionId ,String acceptedDate) { 
			
			
			logger.info("Entered setStatus method of ChangeSessionStatusDAO");
			Boolean isStatusCommit = false;
			int result = 0;
				
				try {
					conn =ConnectionFactory.getConnection();
					conn.setAutoCommit(false);
					String query = "UPDATE session_table  SET STATUS=?, ACCEPTED_DATE=? WHERE SESSION_ID = ?";
					PreparedStatement pstmt = conn.prepareStatement(query);
					pstmt.setString(1,status);
					pstmt.setString(2, acceptedDate);
					pstmt.setString(3, sessionId);
					result = pstmt.executeUpdate();
				    if(result > 0){
				    	conn.commit();
				    	isStatusCommit = true;
				    }
					logger.info("Exit setStatus method of ChangeSessionStatusDAO");
				} catch (SQLException e) {
					logger.error("setStatus method of ChangeSessionStatusDAO threw error:"+e.getMessage());
					e.printStackTrace();
				} catch (IOException e) {
					logger.error("setStatus method of ChangeSessionStatusDAO threw error:"+e.getMessage());
					e.printStackTrace();
				} catch (PropertyVetoException e) {
					logger.error("setStatus method of ChangeSessionStatusDAO threw error:"+e.getMessage());
					e.printStackTrace();
				}finally{
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("setStatus method of ChangeSessionStatusDAO threw error:"+e.getMessage());
						e.printStackTrace();
					}
				}
				return isStatusCommit;		
		}
		
		public Boolean  UpdateSessionPayment(String sId, int requestId,double amount,double advisorPayment,double fee,String trackingId,String paymentMode,String order_status) { 
			logger.info("Entered UpdateSessionPayment method of ChangeSessionStatusDAO");
			Boolean isStatusCommit = false;
			int result = 0;
				
				try {
					conn =ConnectionFactory.getConnection();
					conn.setAutoCommit(false);
					String query = "insert into payment"+"(SESSION_ID,REQUEST_ID,USER_PAYMENT,ADVISOR_PAYMENT,FEE,TRACKING_ID,PAYMENT_MODE,DATE_OF_PAYMENT) values" + "(?, ?, ?,?,?,?,?,?)";
					PreparedStatement pstmt = conn.prepareStatement(query);
					pstmt.setString(1, sId);
					pstmt.setInt(2, requestId);
					pstmt.setDouble(3, amount);
					pstmt.setDouble(4, advisorPayment);
					pstmt.setDouble(5, fee);
					pstmt.setString(6, trackingId);
					pstmt.setString(7, paymentMode);
					pstmt.setTimestamp(8, new java.sql.Timestamp(new Date().getTime()));
					result = pstmt.executeUpdate();
					if(result >0) {
						conn.commit();
						isStatusCommit = true;
					}
				} catch (SQLException e) {
					try {
						conn.rollback();
						e.printStackTrace();
					} catch (SQLException e1) {
						logger.error("setAdvisorServiceDetails method of AdvisorRegistrationDAO threw error:"+e.getMessage());
						e1.printStackTrace();
					}
					logger.info("Exit UpdateSessionPayment method of ChangeSessionStatusDAO");
				} catch (IOException e) {
					logger.error("UpdateSessionPayment method of ChangeSessionStatusDAO threw error:"+e.getMessage());
					e.printStackTrace();
				} catch (PropertyVetoException e) {
					logger.error("UpdateSessionPayment method of ChangeSessionStatusDAO threw error:"+e.getMessage());
					e.printStackTrace();
				}finally{
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("UpdateSessionPayment method of ChangeSessionStatusDAO threw error:"+e.getMessage());
						e.printStackTrace();
					}
				}
				return isStatusCommit;		
		}
		
		public Boolean  setStatus(String sessionId) { 
			logger.info("Entered setStatus method of ChangeSessionStatusDAO");
			Boolean isStatusCommit = false;
			int result = 0;
				
				try {
					conn =ConnectionFactory.getConnection();
					conn.setAutoCommit(false);
					String query = "UPDATE session_table  SET STATUS=?,CCAV_STATUS=? WHERE SESSION_ID = ?";
					PreparedStatement pstmt = conn.prepareStatement(query);
					pstmt.setString(1,"WAITING FOR SESSION");
					pstmt.setString(2,"SUCCESS");
					pstmt.setString(3, sessionId);
					result = pstmt.executeUpdate();
				    if(result > 0){
				    	conn.commit();
				    	isStatusCommit = true;
				    }
					logger.info("Exit setStatus method of ChangeSessionStatusDAO");
				} catch (SQLException e) {
					logger.error("setStatus method of ChangeSessionStatusDAO threw error:"+e.getMessage());
					e.printStackTrace();
				} catch (IOException e) {
					logger.error("setStatus method of ChangeSessionStatusDAO threw error:"+e.getMessage());
					e.printStackTrace();
				} catch (PropertyVetoException e) {
					logger.error("setStatus method of ChangeSessionStatusDAO threw error:"+e.getMessage());
					e.printStackTrace();
				}finally{
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("setStatus method of ChangeSessionStatusDAO threw error:"+e.getMessage());
						e.printStackTrace();
					}
				}
				return isStatusCommit;		
		}
		
		public Boolean  UpdateStatus(String sId,  String accDate ) { 
			logger.info("Entered UpdateStatus method of ChangeSessionStatusDAO");
			Boolean isStatusCommit = false;
			int result = 0;
			String query = "";
			PreparedStatement pstmt;
				try {
					conn =ConnectionFactory.getConnection();
					conn.setAutoCommit(false);
					if(!accDate.equals("")){
						query = "UPDATE session_table  SET STATUS=?,ACCEPTED_DATE=?,CCAV_STATUS=? WHERE SESSION_ID = ?";
						pstmt = conn.prepareStatement(query);
						pstmt.setString(1,"WAITING FOR SESSION");
						pstmt.setString(2, accDate);
						pstmt.setString(3,"SUCCESS");
						pstmt.setString(4, sId);
					}else{
						query = "UPDATE session_table  SET STATUS=?,CCAV_STATUS=? WHERE SESSION_ID = ?";
						pstmt = conn.prepareStatement(query);
						pstmt.setString(1,"WAITING FOR SESSION");
						pstmt.setString(2,"SUCCESS");
						pstmt.setString(3, sId);
					}
			
					result = pstmt.executeUpdate();
				    if(result > 0){
				    	conn.commit();
				    	isStatusCommit = true;
				    }
					logger.info("Exit UpdateStatus method of ChangeSessionStatusDAO");
				} catch (SQLException e) {
					logger.error("UpdateStatus method of ChangeSessionStatusDAO threw error:"+e.getMessage());
					e.printStackTrace();
				} catch (IOException e) {
					logger.error("UpdateStatus method of ChangeSessionStatusDAO threw error:"+e.getMessage());
					e.printStackTrace();
				} catch (PropertyVetoException e) {
					logger.error("UpdateStatus method of ChangeSessionStatusDAO threw error:"+e.getMessage());
					e.printStackTrace();
				}finally{
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("UpdateStatus method of ChangeSessionStatusDAO threw error:"+e.getMessage());
						e.printStackTrace();
					}
				}
				return isStatusCommit;		
		}
}
