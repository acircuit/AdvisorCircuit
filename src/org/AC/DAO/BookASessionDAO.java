 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 29/11/2014
 * ************************************************************************************************/
package org.AC.DAO;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.AC.JDBC.ConnectionFactory;
import org.AC.JDBC.Util;
import org.AC.Util.ConvertStringToDate;
import org.AC.dto.AdvisorModeDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;


	/* *************************CLASS SUMMARY***********************************************************
	* 
	*  This DAO class will put the Book A Session form details ino the required folder
	* 
	* 
	*
	***************************************************************************************************/
public class BookASessionDAO {
	
	Connection conn = null;
	Statement stmt = null;
	private static final Logger logger = Logger.getLogger(BookASessionDAO.class);
	
	 /**************************************COMMENTS***************************************************
	 * This function  will put the Book a Session form details in the "userrequest" table 
	 *   
	 *   @return :int requestId
	 *   @param : String advisorId,String service,String mode,String duration ,String datetimepicker1,
	 *   		  String datetimepicker2,String datetimepicker3,String datetimepicker4,String datetimepicker5,
	 *            String datetimepicker6,String userQuery
	 *
	 ***************************************************************************************************/

	public int  setBookASessionDetails(String advisorId,String service,String mode,String duration ,String datetimepicker1,String datetimepicker2,String datetimepicker3,String datetimepicker4,String userQuery,int userId,String price,String isFree, String registrationPrice, String discount,String userIsFree) { 
		logger.info("Entered setBookASessionDetails method of BookASessionDAO");
		int rId =0;
		int result = 0;
		if(!service.isEmpty() && !advisorId.isEmpty() && !mode.isEmpty() && !duration.isEmpty()
				&& datetimepicker1 !=null && datetimepicker2 != null && datetimepicker3 != null && datetimepicker4 != null 
				 && !userQuery.isEmpty() && userId != 0){
		
			Date date1 = null;
			Date date2 = null;
			Date date3 = null;
			Date date4 = null;
			PreparedStatement pstmt;
			if(mode.equals("email")){
				//This code is to convert the string format to Date format
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				try {
					date1 =  (Date) formatter.parse(datetimepicker1);
				} catch (ParseException e) {
					logger.error("setBookASessionDetails method of BookASessionDAO threw error:"+e.getMessage());
					e.printStackTrace();
				}
                   // timestamp now
				Calendar cal = Calendar.getInstance();       // get calendar instance
				cal.setTime(date1);                           // set cal to date
				cal.set(Calendar.HOUR_OF_DAY, 23);            // set hour to midnight
				cal.set(Calendar.MINUTE, 59);                 // set minute in hour
				cal.set(Calendar.SECOND, 59); 
				date1 = cal.getTime(); // set second in minute
			}else{
				//This code is to convert the string format to Date format
				ConvertStringToDate formattedDate1 = new ConvertStringToDate();
				date1 = formattedDate1.convertToDate(datetimepicker1);
				ConvertStringToDate formattedDate2 = new ConvertStringToDate();
				date2 = formattedDate2.convertToDate(datetimepicker2);
				ConvertStringToDate formattedDate3 = new ConvertStringToDate();
				date3 = formattedDate3.convertToDate(datetimepicker3);
				ConvertStringToDate formattedDate4 = new ConvertStringToDate();
				date4 = formattedDate4.convertToDate(datetimepicker4);
			}
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
			String status = "PENDING FOR ADMIN APPROVAL";
			try {
				conn =ConnectionFactory.getConnection();
				conn.setAutoCommit(false);
				if(mode.equals("email")){
					String query = "insert into userrequest"+"(ADVISOR_ID,SERVICE,MODE_OF_COMMUNICATION,QUERY,DURATION,BOOKING_TIME,DATE_TIME1,STATUS,USER_ID,AMOUNT,IS_FREE_FROM_ADVISOR,PRICE,DISCOUNT,IS_FREE_USER) values" + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					pstmt = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
					pstmt.setString(1,advisorId);
					pstmt.setString(2,service);
					pstmt.setString(3,mode);
					pstmt.setString(4,userQuery);
					pstmt.setString(5,duration);
					pstmt.setTimestamp(6, new java.sql.Timestamp(date.getTime()));
					pstmt.setTimestamp(7, new java.sql.Timestamp(date1.getTime()));
					pstmt.setString(8,status);
					pstmt.setInt(9, userId);
					pstmt.setString(10, price);
					if(isFree.equals("true")){
						pstmt.setBoolean(11,true);
					}else{
						pstmt.setBoolean(11,false);
					}
					pstmt.setDouble(12, Double.parseDouble(registrationPrice));
					if(isFree.equals("true")){
						pstmt.setDouble(13, 100.00);
					}else{
						pstmt.setDouble(13, Double.parseDouble(discount));
					}
					pstmt.setBoolean(14,false);
					result = pstmt.executeUpdate(); 
				}else{
					String query = "insert into userrequest"+"(ADVISOR_ID,SERVICE,MODE_OF_COMMUNICATION,QUERY,DURATION,BOOKING_TIME,DATE_TIME1,DATE_TIME2,DATE_TIME3,DATE_TIME4,STATUS,USER_ID,AMOUNT,IS_FREE_FROM_ADVISOR,PRICE,DISCOUNT,IS_FREE_USER) values" + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					pstmt = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
					pstmt.setString(1,advisorId);
					pstmt.setString(2,service);
					pstmt.setString(3,mode);
					pstmt.setString(4,userQuery);
					pstmt.setString(5,duration);
					pstmt.setTimestamp(6, new java.sql.Timestamp(date.getTime()));
					pstmt.setTimestamp(7, new java.sql.Timestamp(date1.getTime()));
					pstmt.setTimestamp(8, new java.sql.Timestamp(date2.getTime()));
					pstmt.setTimestamp(9, new java.sql.Timestamp(date3.getTime()));
					pstmt.setTimestamp(10, new java.sql.Timestamp(date4.getTime()));
					pstmt.setString(11,status);
					pstmt.setInt(12, userId);
					pstmt.setString(13, price);
					if(isFree.equals("true")){
						pstmt.setBoolean(14,true);
					}else{
						pstmt.setBoolean(14,false);
					}
					pstmt.setDouble(15, Double.parseDouble(registrationPrice));
					pstmt.setDouble(16,  Double.parseDouble(discount));
					if(userIsFree.equals("true")){
						pstmt.setBoolean(17,true);
					}else{
						pstmt.setBoolean(17,false);
					}
					result = pstmt.executeUpdate(); 
					
				}
				if(result >0) {
					ResultSet generatedKeys = pstmt.getGeneratedKeys();
					if (null != generatedKeys && generatedKeys.next()) {
					      rId = generatedKeys.getInt(1);
					}
					conn.commit();
				}
				logger.info("Exit setBookASessionDetails method of BookASessionDAO");
			} catch (SQLException e) {
				logger.error("setBookASessionDetails method of BookASessionDAO threw error:"+e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				logger.error("setBookASessionDetails method of BookASessionDAO threw error:"+e.getMessage());
				e.printStackTrace();
			} catch (PropertyVetoException e) {
				logger.error("setBookASessionDetails method of BookASessionDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("setBookASessionDetails method of BookASessionDAO threw error:"+e.getMessage());
					e.printStackTrace();
				}
			}
		}
		return rId;
		
	}
	
	 /**************************************COMMENTS***************************************************
	 * This function  will put the CV Path from the  Book a Session form  in user_cv table.
	 *   
	 *   @return :int requestId
	 *   @param : String absoluteURL
	 *
	 ***************************************************************************************************/
	
	public Boolean  setCV(String absoluteURL,int requestId,int userId) { 
		
		
		logger.info("Entered setCV method of BookASessionDAO");
		Boolean isCvCommit = false;
		if(!("").equals(absoluteURL)){
			try {
				conn =ConnectionFactory.getConnection();
				conn.setAutoCommit(false);
				String query = "insert into user_cv"+"(REQUEST_ID,USER_ID,CV) values" + "(?,?,?)";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, requestId);
				pstmt.setInt(2, userId);
				pstmt.setString(3,absoluteURL);
				int result = pstmt.executeUpdate(); 
				if(result >0) {
					conn.commit();
					isCvCommit = true;
				}
				logger.info("Exit setCV method of BookASessionDAO");
			} catch (SQLException e) {
				logger.error("setCV method of BookASessionDAO threw error:"+e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				logger.error("setCV method of BookASessionDAO threw error:"+e.getMessage());
				e.printStackTrace();
			} catch (PropertyVetoException e) {
				logger.error("setCV method of BookASessionDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("setCV method of BookASessionDAO threw error:"+e.getMessage());
					e.printStackTrace();
				}
			}
		}
		return isCvCommit;
		
	}
	public Boolean  DecrementIsFree(String aId, String service) { 
		logger.info("Entered DecrementIsFree method of BookASessionDAO");
		Boolean isFlagCommit = false ;
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="UPDATE advisorservices SET ISFREE = ISFREE-1 WHERE ADVISOR_ID = ? AND SERVICE=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, aId);
			pstmt.setString(2, service);
			int result = pstmt.executeUpdate(); 
			if(result >0) {
				conn.commit();
				isFlagCommit = true;
			}
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("DecrementIsFree method of BookASessionDAO threw error:"+e2.getMessage());
					e2.printStackTrace();
				}
				logger.error("DecrementIsFree method of BookASessionDAO threw error:"+e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("DecrementIsFree method of BookASessionDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("DecrementIsFree method of BookASessionDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		logger.info("Exit DecrementIsFree method of BookASessionDAO");
		return isFlagCommit;
	}
	public Boolean  ToggleUserIsFree(int userId) { 
		logger.info("Entered ToggleUserIsFree method of BookASessionDAO");
		Boolean isFlagCommit = false ;
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="UPDATE userdetails SET ISFREE = ? WHERE USER_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1,false);
			pstmt.setInt(2, userId);
			int result = pstmt.executeUpdate(); 
			if(result >0) {
				conn.commit();
				isFlagCommit = true;
			}
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("DecrementIsFree method of BookASessionDAO threw error:"+e2.getMessage());
					e2.printStackTrace();
				}
				logger.error("DecrementIsFree method of BookASessionDAO threw error:"+e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("ToggleUserIsFree method of BookASessionDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("ToggleUserIsFree method of BookASessionDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		logger.info("Exit ToggleUserIsFree method of BookASessionDAO");
		return isFlagCommit;
	}
}