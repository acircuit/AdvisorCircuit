package org.AC.DAO;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.AC.JDBC.ConnectionFactory;
import org.AC.dto.AdvisorRegistrationCheckEmailDTO;
import org.apache.log4j.Logger;

public class AdvisorNotificationDAO {
	private static final Logger logger = Logger.getLogger(AdvisorRegistrationDAO.class);
	Connection conn = null;
	
	public Boolean InsertRequestNotification(String comment,String aId){
		logger.info("Entered InsertRequestNotification method of AdvisorNotificationDAO");
		Boolean isNotification = false;
					try {
						conn =ConnectionFactory.getConnection();
						conn.setAutoCommit(false);
						String query = "insert into advisor_notification"+"(ADVISOR_ID,COMMENT) values" + "(?,?)";
						PreparedStatement pstmt = conn.prepareStatement(query);
						pstmt.setString(1,aId);
						pstmt.setString(2,comment);
						int result = pstmt.executeUpdate();
						if(result > 0) {
							conn.commit();
							isNotification = true;
							
						}
					} catch (SQLException e) {
						try {
							conn.rollback();
						} catch (SQLException e1) {
							logger.error("InsertRequestNotification method of AdvisorNotificationDAO threw error:"+e.getMessage());
							e1.printStackTrace();
						}	
						logger.error("InsertRequestNotification method of AdvisorNotificationDAO threw error:"+e.getMessage());
						e.printStackTrace();
					} catch (IOException e) {
						logger.error("InsertRequestNotification method of AdvisorNotificationDAO threw error:"+e.getMessage());
						e.printStackTrace();
					} catch (PropertyVetoException e) {
						logger.error("InsertRequestNotification method of AdvisorNotificationDAO threw error:"+e.getMessage());
						e.printStackTrace();
					}finally{
						try {
							conn.close();
						} catch (SQLException e) {
							logger.error("InsertRequestNotification method of AdvisorNotificationDAO threw error:"+e.getMessage());
							e.printStackTrace();
						}
					}	
			logger.info("Entered InsertRequestNotification method of AdvisorNotificationDAO");
			return isNotification;

		}
	
	public List<String> GetNotification(int aId){
		
		logger.info("Entered GetNotification method of AdvisorNotificationDAO");
		List<String> comment = new ArrayList<String>();
	 try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT COMMENT FROM advisor_notification WHERE ADVISOR_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, aId);
			ResultSet results = pstmt.executeQuery();
			while(results.next()){
				comment.add(results.getString("COMMENT"));
			}
		} catch (SQLException e) {
			logger.error("GetNotification method of AdvisorNotificationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("GetNotification method of AdvisorNotificationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("GetNotification method of AdvisorNotificationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetNotification method of AdvisorNotificationDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		
		logger.info("Entered GetNotification method of AdvisorNotificationDAO");
		return comment;
	}
}
