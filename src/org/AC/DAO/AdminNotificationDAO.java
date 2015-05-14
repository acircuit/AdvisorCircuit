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
import java.util.List;

import org.AC.JDBC.ConnectionFactory;
import org.AC.dto.ProfessionalBackgroundDTO;
import org.apache.log4j.Logger;

public class AdminNotificationDAO {
	private static final Logger logger = Logger.getLogger(AdvisorRegistrationDAO.class);
	Connection conn = null;
	
	public Boolean InsertAdvisorRegistrationNotification(String comment){
		logger.info("Entered InsertAdvisorRegistrationNotification method of AdminNotificationDAO");
		Boolean isNotification = false;
					try {
						conn =ConnectionFactory.getConnection();
						conn.setAutoCommit(false);
						String query = "insert into admin_notification"+"(COMMENT) values" + "(?)";
						PreparedStatement pstmt = conn.prepareStatement(query);
						pstmt.setString(1,comment);
						int result = pstmt.executeUpdate();
						if(result > 0) {
							conn.commit();
							isNotification = true;
							
						}
					} catch (SQLException e) {
						try {
							conn.rollback();
						} catch (SQLException e1) {
							logger.error("InsertAdvisorRegistrationNotification method of AdminNotificationDAO threw error:"+e.getMessage());
							e1.printStackTrace();
						}	
						logger.error("InsertAdvisorRegistrationNotification method of AdminNotificationDAO threw error:"+e.getMessage());
						e.printStackTrace();
					} catch (IOException e) {
						logger.error("InsertAdvisorRegistrationNotification method of AdminNotificationDAO threw error:"+e.getMessage());
						e.printStackTrace();
					} catch (PropertyVetoException e) {
						logger.error("InsertAdvisorRegistrationNotification method of AdminNotificationDAO threw error:"+e.getMessage());
						e.printStackTrace();
					}finally{
						try {
							conn.close();
						} catch (SQLException e) {
							logger.error("InsertAdvisorRegistrationNotification method of AdminNotificationDAO threw error:"+e.getMessage());
							e.printStackTrace();
						}
					}	
			logger.info("Entered InsertAdvisorRegistrationNotification method of AdminNotificationDAO");
			return isNotification;

		}
}
