package org.AC.DAO;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.AC.JDBC.ConnectionFactory;
import org.apache.log4j.Logger;

public class UserNotificationDAO {
	
	private static final Logger logger = Logger.getLogger(UserNotificationDAO.class);
	Connection conn = null;
	
	public Boolean InsertNotification(String comment, String href, String uId){
		logger.info("Entered InsertNotification method of UserNotificationDAO");
		Boolean isNotification = false;
					try {
						conn =ConnectionFactory.getConnection();
						conn.setAutoCommit(false);
						String query = "insert into user_notification"+"(COMMENT,HREF,USER_ID) values" + "(?,?,?)";
						PreparedStatement pstmt = conn.prepareStatement(query);
						pstmt.setString(1,comment);
						pstmt.setString(2,href);
						pstmt.setString(3,uId);
						int result = pstmt.executeUpdate();
						if(result > 0) {
							conn.commit();
							isNotification = true;					
						}
					} catch (SQLException e) {
						try {
							conn.rollback();
						} catch (SQLException e1) {
							logger.error("InsertNotification method of UserNotificationDAO threw error:"+e.getMessage());
							e1.printStackTrace();
						}	
						logger.error("InsertNotification method of UserNotificationDAO threw error:"+e.getMessage());
						e.printStackTrace();
					} catch (IOException e) {
						logger.error("InsertNotification method of UserNotificationDAO threw error:"+e.getMessage());
						e.printStackTrace();
					} catch (PropertyVetoException e) {
						logger.error("InsertNotification method of UserNotificationDAO threw error:"+e.getMessage());
						e.printStackTrace();
					}finally{
						try {
							conn.close();
						} catch (SQLException e) {
							logger.error("InsertNotification method of UserNotificationDAO threw error:"+e.getMessage());
							e.printStackTrace();
						}
					}	
			logger.info("Entered InsertNotification method of UserNotificationDAO");
			return isNotification;

		}
	
	public int[] GetId(String rId){
		logger.info("Entered GetId method of UserNotificationDAO");
		int[] id = new int[2];
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT ADVISOR_ID,USER_ID FROM userrequest WHERE REQUEST_ID=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1,rId);
			ResultSet results = pstmt.executeQuery();
			if(results.first()){
				id[0] = results.getInt("USER_ID");
				id[1] = results.getInt("ADVISOR_ID");
			}
		} catch (SQLException e) {
			logger.error("GetId method of UserNotificationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("GetId method of UserNotificationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("GetId method of UserNotificationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetId method of UserNotificationDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		logger.info("Entered GetId method of UserNotificationDAO");
		return id;
	}
	
	public int[] GetAdvisorId(String rId){
		logger.info("Entered GetAdvisorId method of UserNotificationDAO");
		int[] id = new int[3];
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT ADVISOR_ID,USER_ID,REQUEST_ID FROM session_table WHERE SESSION_ID=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1,rId);
			ResultSet results = pstmt.executeQuery();
			if(results.first()){
				id[0] = results.getInt("USER_ID");
				id[1] = results.getInt("ADVISOR_ID");
				id[2] = results.getInt("REQUEST_ID");
			}
		} catch (SQLException e) {
			logger.error("GetAdvisorId method of UserNotificationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("GetAdvisorId method of UserNotificationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("GetAdvisorId method of UserNotificationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetAdvisorId method of UserNotificationDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		logger.info("Entered GetAdvisorId method of UserNotificationDAO");
		return id;
	}
}
