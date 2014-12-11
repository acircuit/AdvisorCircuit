package org.AC.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.AC.JDBC.Util;
import org.AC.Util.ConvertStringToDate;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class AdvisorMyAccountRequestViewDetailsDAO {
	private static final Logger logger = Logger.getLogger(AdvisorMyAccountRequestViewDetailsDAO.class);
	Connection conn = null;
	Statement stmt = null; 
	
	public int setSessionDetails(int aId, String rId, String uId, String acceptedTime,String sessionPlan,String status){
		
		BasicConfigurator.configure();
		logger.info("Entered setSessionDetails method of AdvisorMyAccountRequestViewDetailsDAO");
		int result = 0;
		int session_id = 0;
		if(uId != null && !("").equals(uId) && rId != null && !("").equals(rId) && aId != 0
				&&  sessionPlan != null && !("").equals(sessionPlan) && status != null && !("").equals(status)){
			
			try {
				conn =Util.connect();
				String query = "insert into session_table"+"(REQUEST_ID,ADVISOR_ID,USER_ID,SESSION_PLAN,ACCEPTED_DATE,STATUS) values" + "(?,?,?,?,?,?)";
					 PreparedStatement pstmt = conn.prepareStatement(query);
					pstmt.setString(1,rId);
					pstmt.setInt(2,aId);
					pstmt.setString(3,uId);
					pstmt.setString(4,sessionPlan);
					pstmt.setString(5,acceptedTime);
					pstmt.setString(6,status);
				    result = pstmt.executeUpdate();
				    if(result >0) {
				    	conn.commit();
						ResultSet generatedKeys = pstmt.getGeneratedKeys();
						if (null != generatedKeys && generatedKeys.next()) {
							session_id = generatedKeys.getInt(1);
						}
					}
					conn.close();
				logger.info("Exit setUserDetails method of UserDetailsDAO");
				} catch (SQLException e) {
					try {
						conn.rollback();
					} catch (SQLException e1) {
						logger.error("setUserDetails method of UserDetailsDAO threw error:"+e.getMessage());
						e1.printStackTrace();
					}
					logger.error("setUserDetails method of UserDetailsDAO threw error:"+e.getMessage());
					e.printStackTrace();
				}
			}
		return session_id;
	}
	
	
public Boolean setNewDates(int sId, String newDate1,String newDate2,String newDate3){
		
		BasicConfigurator.configure();
		logger.info("Entered setSessionDetails method of AdvisorMyAccountRequestViewDetailsDAO");
		int result = 0;
		Boolean isNewDatesCommit = false;
		if(sId != 0 && newDate1 != null && !("").equals(newDate1) && newDate2 != null && !("").equals(newDate2)
				&&  newDate3 != null && !("").equals(newDate3)){
			
			ConvertStringToDate formattedDate1 = new ConvertStringToDate();
			Date date1 = formattedDate1.convertToDate(newDate1);
			ConvertStringToDate formattedDate2 = new ConvertStringToDate();
			Date date2 = formattedDate2.convertToDate(newDate2);
			ConvertStringToDate formattedDate3 = new ConvertStringToDate();
			Date date3 = formattedDate3.convertToDate(newDate3);
			try {
				conn =Util.connect();
				String query = "insert into advisor_new_dates"+"(SESSION_ID,NEW_DATE1,NEW_DATE2,NEW_DATE3) values" + "(?,?,?,?)";
					 PreparedStatement pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, sId);
					pstmt.setTimestamp(2,new java.sql.Timestamp(date1.getTime()));
					pstmt.setTimestamp(3,new java.sql.Timestamp(date2.getTime()));
					pstmt.setTimestamp(4,new java.sql.Timestamp(date3.getTime()));
					result = pstmt.executeUpdate();
				    if(result >0) {
				    	conn.commit();
				    	isNewDatesCommit = true;
						}
					conn.close();
				logger.info("Exit setSessionDetails method of AdvisorMyAccountRequestViewDetailsDAO");
				} catch (SQLException e) {
					try {
						conn.rollback();
					} catch (SQLException e1) {
						logger.error("setSessionDetails method of AdvisorMyAccountRequestViewDetailsDAO threw error:"+e.getMessage());
						e1.printStackTrace();
					}
					logger.error("setSessionDetails method of AdvisorMyAccountRequestViewDetailsDAO threw error:"+e.getMessage());
					e.printStackTrace();
				}
			}
		return isNewDatesCommit;
	}
}
