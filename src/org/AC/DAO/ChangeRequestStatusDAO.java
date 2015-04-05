package org.AC.DAO;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import org.AC.JDBC.ConnectionFactory;
import org.AC.JDBC.Util;
import org.AC.Util.ConvertStringToDate;
import org.AC.dto.ProfessionalBackgroundDTO;
import org.AC.dto.UserRequestDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class ChangeRequestStatusDAO {
	
	Connection conn = null;
	Statement stmt = null;
	private static final Logger logger = Logger.getLogger(ChangeRequestStatusDAO.class);
	
	//This function  will set the request status in the "userrequest" table
	public Boolean  setStatus(String status, int requestId ) { 
		logger.info("Entered setStatus method of ChangeRequestStatusDAO");
		Boolean isStatusCommit = false;
		int result = 0;
			
			try {
				conn =ConnectionFactory.getConnection();
				conn.setAutoCommit(false);
				String query = "UPDATE userrequest  SET STATUS=? WHERE REQUEST_ID = ?";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1,status);
				pstmt.setInt(2, requestId);
				result = pstmt.executeUpdate();
			    if(result > 0){
			    	conn.commit();
			    	isStatusCommit = true;
			    }
				logger.info("Exit setStatus method of ChangeRequestStatusDAO");
			} catch (SQLException e) {
				logger.error("setStatus method of ChangeRequestStatusDAO threw error:"+e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				logger.error("setStatus method of ChangeRequestStatusDAO threw error:"+e.getMessage());
				e.printStackTrace();
			} catch (PropertyVetoException e) {
				logger.error("setStatus method of ChangeRequestStatusDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("setStatus method of ChangeRequestStatusDAO threw error:"+e.getMessage());
					e.printStackTrace();
				}
			}
			return isStatusCommit;		
	}
	public String  GetService(String rId) { 
			logger.info("Entered GetService method of ChangeRequestStatusDAO");
			ResultSet results = null;
			Connection conn = null;
			String service = "";
			try {
				conn =ConnectionFactory.getConnection();
				conn.setAutoCommit(false);
				String query ="SELECT SERVICE from userrequest WHERE REQUEST_ID = ?";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1,rId);
			    results = pstmt.executeQuery();
			    if(results.first()){
			    	service = results.getString("SERVICE");
			    }
			logger.info("Exit GetService method of ChangeRequestStatusDAO");
			}catch(Exception e){
				logger.error("GetService method of ChangeRequestStatusDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("GetService method of ChangeRequestStatusDAO threw error:"+e.getMessage());
					e.printStackTrace();
				}
			}
		return service;
	}
	public Boolean SetFeedBack(String sId,String service,String path, Boolean status){
		logger.info("Entered SetFeedBack method of ChangeRequestStatusDAO");
		Boolean isFeedbackFormCommit = false;
					try {
						conn =ConnectionFactory.getConnection();
						conn.setAutoCommit(false);
						String query = "insert into feedback"+"(SESSION_ID,SERVICE,FORM,APPROVED,SUBMITTED) values" + "(?,?,?,?,?)";
						PreparedStatement pstmt = conn.prepareStatement(query);
						pstmt.setString(1,sId);
						pstmt.setString(2, service);
						pstmt.setString(3, path);
						pstmt.setBoolean(4, status);
						pstmt.setBoolean(5, true);
						int result = pstmt.executeUpdate();
						if(result > 0) {
							conn.commit();
							isFeedbackFormCommit = true;
						}
					} catch (SQLException e) {
						logger.error("SetFeedBack method of ChangeRequestStatusDAO threw error:"+e.getMessage());
						e.printStackTrace();
					} catch (IOException e) {
						logger.error("SetFeedBack method of ChangeRequestStatusDAO threw error:"+e.getMessage());
						e.printStackTrace();
					} catch (PropertyVetoException e) {
						logger.error("SetFeedBack method of ChangeRequestStatusDAO threw error:"+e.getMessage());
						e.printStackTrace();
					}finally{
						try {
							conn.close();
						} catch (SQLException e) {
							logger.error("SetFeedBack method of ChangeRequestStatusDAO threw error:"+e.getMessage());
							e.printStackTrace();
						}
					}	
			logger.info("Entered SetFeedBack method of ChangeRequestStatusDAO");
			return isFeedbackFormCommit;

		}
	
		public int  GetRequestIdFromSessionId(String sId) { 
			logger.info("Entered GetRequestIdFromSessionId method of ChangeRequestStatusDAO");
			ResultSet results = null;
			Connection conn = null;
			String service = "";
			int requestId = 0;
			try {
				conn =ConnectionFactory.getConnection();
				conn.setAutoCommit(false);
				String query ="SELECT REQUEST_ID from session_table WHERE SESSION_ID = ?";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1,sId);
			    results = pstmt.executeQuery();
			    if(results.first()){
			    	requestId = results.getInt("REQUEST_ID");
			    }
			logger.info("Exit GetRequestIdFromSessionId method of ChangeRequestStatusDAO");
			}catch(Exception e){
				logger.error("GetRequestIdFromSessionId method of ChangeRequestStatusDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("GetRequestIdFromSessionId method of ChangeRequestStatusDAO threw error:"+e.getMessage());
					e.printStackTrace();
				}
			}
		return requestId;
		}
		
		public UserRequestDTO  GetPaymentInfo(int rId) { 
			logger.info("Entered GetPaymentInfo method of ChangeRequestStatusDAO");
			ResultSet results = null;
			Connection conn = null;
			String service = "";
			UserRequestDTO req = new UserRequestDTO();
			try {
				conn =ConnectionFactory.getConnection();
				conn.setAutoCommit(false);
				String query ="SELECT PRICE,DISCOUNT,IS_FREE_FROM_ADVISOR,AMOUNT from userrequest WHERE REQUEST_ID = ?";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, rId);
			    results = pstmt.executeQuery();
			    if(results.first()){
			    	req.setIsFree(results.getBoolean("IS_FREE_FROM_ADVISOR"));
			    	req.setDiscount(results.getInt("DISCOUNT"));
			    	req.setPrice(results.getDouble("PRICE"));
			    	req.setAmount(results.getDouble("PRICE"));
			    }
			logger.info("Exit GetPaymentInfo method of ChangeRequestStatusDAO");
			}catch(Exception e){
				logger.error("GetPaymentInfo method of ChangeRequestStatusDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("GetPaymentInfo method of ChangeRequestStatusDAO threw error:"+e.getMessage());
					e.printStackTrace();
				}
			}
		return req;
		}
}
