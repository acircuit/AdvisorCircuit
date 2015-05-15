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
import org.AC.dto.NotificationDTO;
import org.AC.dto.ProfessionalBackgroundDTO;
import org.apache.log4j.Logger;

public class AdvisorNotificationDAO {
	private static final Logger logger = Logger.getLogger(AdvisorRegistrationDAO.class);
	Connection conn = null;
	
	public Boolean InsertRequestNotification(String comment,String aId,String href){
		logger.info("Entered InsertRequestNotification method of AdvisorNotificationDAO");
		Boolean isNotification = false;
					try {
						conn =ConnectionFactory.getConnection();
						conn.setAutoCommit(false);
						String query = "insert into advisor_notification"+"(ADVISOR_ID,COMMENT,HREF) values" + "(?,?,?)";
						PreparedStatement pstmt = conn.prepareStatement(query);
						pstmt.setString(1,aId);
						pstmt.setString(2,comment);
						pstmt.setString(3,href);
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
	
	public List<NotificationDTO> GetNotification(int aId){
		
		logger.info("Entered GetNotification method of AdvisorNotificationDAO");
		List<NotificationDTO> comment = new ArrayList<NotificationDTO>();
		
	 try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT N_ID,COMMENT,HREF FROM advisor_notification WHERE ADVISOR_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, aId);
			ResultSet results = pstmt.executeQuery();
			while(results.next()){
				NotificationDTO notify = new NotificationDTO();
				notify.setnId(results.getInt("N_ID"));
				notify.setComment(results.getString("COMMENT"));
				notify.setHref(results.getString("HREF"));
				comment.add(notify);
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
	
	public Boolean DeletNote(String nId){
		
		logger.info("Entered GetNotification method of AdvisorNotificationDAO");
		Boolean isDeleted =false;
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="DELETE FROM advisor_notification WHERE N_ID =?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, nId);
			int result = pstmt.executeUpdate(); 
			if(result >0) {
				conn.commit();
				isDeleted = true;
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
		return isDeleted;
	}
}
