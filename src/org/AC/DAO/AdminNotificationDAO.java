package org.AC.DAO;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.AC.JDBC.ConnectionFactory;
import org.AC.dto.AdvisorRegistrationCheckEmailDTO;
import org.AC.dto.NotificationDTO;
import org.AC.dto.ProfessionalBackgroundDTO;
import org.apache.log4j.Logger;

public class AdminNotificationDAO {
	private static final Logger logger = Logger.getLogger(UserNotificationDAO.class);
	Connection conn = null;
	
	public Boolean InsertNotification(String comment, String href){
		logger.info("Entered InsertNotification method of AdminNotificationDAO");
		Boolean isNotification = false;
		Calendar mbCal = new GregorianCalendar(TimeZone.getTimeZone("IST"));  
        mbCal.setTimeInMillis(new Date().getTime());      
        Calendar cal = Calendar.getInstance();  
        cal.set(Calendar.YEAR, mbCal.get(Calendar.YEAR));  
        cal.set(Calendar.MONTH, mbCal.get(Calendar.MONTH));  
        cal.set(Calendar.DAY_OF_MONTH, mbCal.get(Calendar.DAY_OF_MONTH));  
        cal.set(Calendar.HOUR_OF_DAY, mbCal.get(Calendar.HOUR_OF_DAY));  
        cal.set(Calendar.MINUTE, mbCal.get(Calendar.MINUTE));  
        cal.set(Calendar.SECOND, mbCal.get(Calendar.SECOND));  
        cal.set(Calendar.MILLISECOND, mbCal.get(Calendar.MILLISECOND));
        Date date = cal.getTime();
					try {
						conn =ConnectionFactory.getConnection();
						conn.setAutoCommit(false);
						String query = "insert into admin_notification"+"(COMMENT,HREF,DATE) values" + "(?,?,?)";
						PreparedStatement pstmt = conn.prepareStatement(query);
						pstmt.setString(1,comment);
						pstmt.setString(2,href);
						pstmt.setTimestamp(3, new java.sql.Timestamp(date.getTime()));
						int result = pstmt.executeUpdate();
						if(result > 0) {
							conn.commit();
							isNotification = true;
							
						}
					} catch (SQLException e) {
						try {
							conn.rollback();
						} catch (SQLException e1) {
							logger.error("InsertNotification method of AdminNotificationDAO threw error:"+e.getMessage());
							e1.printStackTrace();
						}	
						logger.error("InsertNotification method of AdminNotificationDAO threw error:"+e.getMessage());
						e.printStackTrace();
					} catch (IOException e) {
						logger.error("InsertNotification method of AdminNotificationDAO threw error:"+e.getMessage());
						e.printStackTrace();
					} catch (PropertyVetoException e) {
						logger.error("InsertNotification method of AdminNotificationDAO threw error:"+e.getMessage());
						e.printStackTrace();
					}finally{
						try {
							conn.close();
						} catch (SQLException e) {
							logger.error("InsertNotification method of AdminNotificationDAO threw error:"+e.getMessage());
							e.printStackTrace();
						}
					}	
			logger.info("Entered InsertNotification method of AdminNotificationDAO");
			return isNotification;

		}
	
	
	public String GetUserName(String uId){
		logger.info("Entered GetUserName method of AdminNotificationDAO");
		String userName = "";
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT FULL_NAME FROM userdetails WHERE USER_ID=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1,uId);
			ResultSet results = pstmt.executeQuery();
			if(results.first()){
				userName = results.getString("FULL_NAME");
			}
		} catch (SQLException e) {
			logger.error("GetUserName method of AdminNotificationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("GetUserName method of AdminNotificationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("GetUserName method of AdminNotificationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetUserName method of AdminNotificationDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		logger.info("Entered GetUserName method of AdminNotificationDAO");
		return userName;
	}
	
	public String GetAdvisorName(String aId){
		logger.info("Entered GetAdvisorName method of AdminNotificationDAO");
		String advisorName = "";
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT NAME FROM advisordetails WHERE ADVISOR_ID=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1,aId);
			ResultSet results = pstmt.executeQuery();
			if(results.first()){
				advisorName = results.getString("NAME");
			}
		} catch (SQLException e) {
			logger.error("GetAdvisorName method of AdminNotificationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("GetAdvisorName method of AdminNotificationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("GetAdvisorName method of AdminNotificationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetAdvisorName method of AdminNotificationDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		logger.info("Entered GetAdvisorName method of AdminNotificationDAO");
		return advisorName;
	}
	
	public List<NotificationDTO> GetNotifications(){
		logger.info("Entered GetNotifications method of AdminNotificationDAO");
		List<NotificationDTO> notify = new ArrayList<NotificationDTO>();
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT * FROM admin_notification";
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet results = pstmt.executeQuery();
			while(results.next()){
				NotificationDTO note = new NotificationDTO();
				note.setnId(results.getInt("N_ID"));
				note.setComment(results.getString("COMMENT"));
				note.setHref(results.getString("HREF"));
				note.setIsPrevious(results.getBoolean("IS_PREVIOUS"));
				note.setDate(results.getTimestamp("DATE"));
				notify.add(note);
			}

		} catch (SQLException e) {
			logger.error("GetNotifications method of AdminNotificationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("GetNotifications method of AdminNotificationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("GetNotifications method of AdminNotificationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetNotifications method of AdminNotificationDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		logger.info("Entered GetNotifications method of AdminNotificationDAO");
		return notify;
	}
	
	public Boolean SetNotificationRead(String url){
		logger.info("Entered SetNotificationRead method of AdminNotificationDAO");
		Boolean isCommit = false;
		String query = "";
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			query = "UPDATE admin_notification SET IS_PREVIOUS = ? WHERE HREF = ?"; 
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1,true );
			pstmt.setString(2, url);
			int result = pstmt.executeUpdate(); 
			if(result >0) {
				conn.commit();
				isCommit = true;
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("SetNotificationRead method of AdminNotificationDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}	
			logger.error("SetNotificationRead method of AdminNotificationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("SetNotificationRead method of AdminNotificationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("SetNotificationRead method of AdminNotificationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("SetNotificationRead method of AdminNotificationDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}		
		logger.info("Entered SetNotificationRead method of AdminNotificationDAO");
		return isCommit;
	}
}
