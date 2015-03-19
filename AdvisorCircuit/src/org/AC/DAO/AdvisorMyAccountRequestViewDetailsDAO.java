package org.AC.DAO;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.AC.JDBC.ConnectionFactory;
import org.AC.JDBC.Util;
import org.AC.Util.ConvertStringToDate;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.eclipse.jdt.internal.compiler.ast.ContinueStatement;

public class AdvisorMyAccountRequestViewDetailsDAO {
	private static final Logger logger = Logger.getLogger(AdvisorMyAccountRequestViewDetailsDAO.class);
	Connection conn = null;
	Statement stmt = null; 
	
	public int setSessionDetails(int aId, String rId, String uId, String acceptedTime,String sessionPlan,String status,Boolean isEmail){
		
		
		logger.info("Entered setSessionDetails method of AdvisorMyAccountRequestViewDetailsDAO");
		int result = 0;
		int session_id = 0;
		if(uId != null && !("").equals(uId) && rId != null && !("").equals(rId) && aId != 0
				&&  sessionPlan != null && !("").equals(sessionPlan) && status != null && !("").equals(status)){
			Date date1 =null;
			Date date = null;
			Date datepicker1 = null;
			PreparedStatement pstmt;
			try {
				conn =ConnectionFactory.getConnection();
				conn.setAutoCommit(false);
				if(isEmail){
					if(acceptedTime != null){
						DateFormat  dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
						try {
							date1 = (Date)dateFormat1.parse(acceptedTime);
						} catch (ParseException e) {
							logger.error("doPost method of AdvisorMyAccountRequestViewDetailsFormController threw error:"+e.getMessage());
							e.printStackTrace();
						}
					}
				}else{
					if(acceptedTime != null){
						ConvertStringToDate formattedDate1 = new ConvertStringToDate();
						date1 = formattedDate1.convertToDate(acceptedTime);
					}
				}
					if(date1 == null){
						String query = "insert into session_table"+"(REQUEST_ID,ADVISOR_ID,USER_ID,SESSION_PLAN,STATUS) values" + "(?,?,?,?,?)";
						pstmt = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
						pstmt.setString(1,rId);
						pstmt.setInt(2,aId);
						pstmt.setString(3,uId);
						pstmt.setString(4,sessionPlan);
						pstmt.setString(5,status);
					}else{
						String query = "insert into session_table"+"(REQUEST_ID,ADVISOR_ID,USER_ID,SESSION_PLAN,ACCEPTED_DATE,STATUS) values" + "(?,?,?,?,?,?)";
						pstmt = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
						pstmt.setString(1,rId);
						pstmt.setInt(2,aId);
						pstmt.setString(3,uId);
						pstmt.setString(4,sessionPlan);
						pstmt.setTimestamp(5,new java.sql.Timestamp(date1.getTime()));
						pstmt.setString(6,status);
					}
				    result = pstmt.executeUpdate();
				    if(result >0) {
				    	conn.commit();
						ResultSet generatedKeys = pstmt.getGeneratedKeys();
						if (null != generatedKeys && generatedKeys.next()) {
							session_id = generatedKeys.getInt(1);
						}
					}
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
				} catch (IOException e) {
					logger.error("setSessionDetails method of AdvisorMyAccountRequestViewDetailsDAO threw error:"+e.getMessage());
					e.printStackTrace();
				} catch (PropertyVetoException e) {
					logger.error("setSessionDetails method of AdvisorMyAccountRequestViewDetailsDAO threw error:"+e.getMessage());
					e.printStackTrace();
				}finally{
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("setSessionDetails method of AdvisorMyAccountRequestViewDetailsDAO threw error:"+e.getMessage());
						e.printStackTrace();
					}
				}
			}
		logger.info("Entered setSessionDetails method of AdvisorMyAccountRequestViewDetailsDAO");
		return session_id;
	}
	
	
public Boolean setNewDates(int sId, String newDate1,String newDate2,String newDate3){
		
		
		logger.info("Entered setNewDates method of AdvisorMyAccountRequestViewDetailsDAO");
		int result = 0;
		Boolean isNewDatesCommit = false;
		Date date1 = null;
		Date date2 = null;
		Date date3 = null;
		PreparedStatement pstmt;
			try {
				conn =ConnectionFactory.getConnection();
				conn.setAutoCommit(false);
				
				if(!newDate1.equals("") && !newDate2.equals("") && !newDate3.equals("")){
					ConvertStringToDate formattedDate1 = new ConvertStringToDate();
					date1 = formattedDate1.convertToDate(newDate1);
					ConvertStringToDate formattedDate2 = new ConvertStringToDate();
					date2 = formattedDate2.convertToDate(newDate2);
					ConvertStringToDate formattedDate3 = new ConvertStringToDate();
					date3 = formattedDate3.convertToDate(newDate3);
					String query = "insert into advisor_new_dates"+"(SESSION_ID,NEW_DATE1,NEW_DATE2,NEW_DATE3) values" + "(?,?,?,?)";
					pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, sId);
					pstmt.setTimestamp(2,new java.sql.Timestamp(date1.getTime()));
					pstmt.setTimestamp(3,new java.sql.Timestamp(date2.getTime()));
					pstmt.setTimestamp(4,new java.sql.Timestamp(date3.getTime()));
				}else{
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					try {
						date1 =  (Date) formatter.parse(newDate1);
					} catch (ParseException e) {
						logger.error("setBookASessionDetails method of BookASessionDAO threw error:"+e.getMessage());
						e.printStackTrace();
					}
					String query = "insert into advisor_new_dates"+"(SESSION_ID,NEW_DATE1) values" + "(?,?)";
					pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, sId);
					pstmt.setTimestamp(2,new java.sql.Timestamp(date1.getTime()));
				}
					result = pstmt.executeUpdate();
				    if(result >0) {
				    	conn.commit();
				    	isNewDatesCommit = true;
						}
				logger.info("Exit setSessionDetails method of AdvisorMyAccountRequestViewDetailsDAO");
				} catch (SQLException e) {
					try {
						conn.rollback();
					} catch (SQLException e1) {
						logger.error("setNewDates method of AdvisorMyAccountRequestViewDetailsDAO threw error:"+e.getMessage());
						e1.printStackTrace();
					}
					logger.error("setNewDates method of AdvisorMyAccountRequestViewDetailsDAO threw error:"+e.getMessage());
					e.printStackTrace();
				} catch (IOException e) {
					logger.error("setNewDates method of AdvisorMyAccountRequestViewDetailsDAO threw error:"+e.getMessage());
					e.printStackTrace();
				} catch (PropertyVetoException e) {
					logger.error("setNewDates method of AdvisorMyAccountRequestViewDetailsDAO threw error:"+e.getMessage());
					e.printStackTrace();
				}finally{
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("setNewDates method of AdvisorMyAccountRequestViewDetailsDAO threw error:"+e.getMessage());
						e.printStackTrace();
					}
				}
		logger.info("Entered setNewDates method of AdvisorMyAccountRequestViewDetailsDAO");

		return isNewDatesCommit;
	}
}
