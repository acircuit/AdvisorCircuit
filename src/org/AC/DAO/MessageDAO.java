package org.AC.DAO;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.AC.JDBC.ConnectionFactory;
import org.AC.JDBC.Util;
import org.AC.dto.MessageDTO;
import org.AC.dto.UserDetailsDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class MessageDAO {

	Connection conn = null;
	Statement stmt = null;
	private static final Logger logger = Logger.getLogger(MessageDAO.class);
	
public Timestamp  setAdvisorMessage(String message,  int advisorId ) { 
		
		
		logger.info("Entered setAdvisorMessage method of MessageDAO");
		int result = 0;
		Boolean isMessageCommit = false;
		Timestamp time = null;
		try{
			if(message != null && !("").equals(message) && advisorId != 0) {
				
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
		         conn =ConnectionFactory.getConnection();
					conn.setAutoCommit(false);
				String query = "insert into advisormessageadmin"+"(ADVISOR_MESSAGE,ADVISOR_MESSAGE_TIME,ADVISOR_ID) values" + "(?,?,?)";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1,message);
				time = new java.sql.Timestamp(date.getTime());
				pstmt.setTimestamp(2,time );
				pstmt.setInt(3,advisorId);
			    result = pstmt.executeUpdate();
			    if(result > 0){
			    	conn.commit();
			    	isMessageCommit = true;
			    }
			}
			logger.info("Exit setAdvisorMessage method of MessageDAO");
	} catch (SQLException e) {
		try {
			conn.rollback();
		} catch (SQLException e1) {
			logger.error("setAdvisorMessage method of MessageDAO threw error:"+e.getMessage());
			e1.printStackTrace();
		}
		logger.error("setAdvisorMessage method of MessageDAO threw error:"+e.getMessage());
		e.printStackTrace();
	} catch (IOException e) {
		logger.error("setAdvisorMessage method of MessageDAO threw error:"+e.getMessage());
		e.printStackTrace();
	} catch (PropertyVetoException e) {
		logger.error("setAdvisorMessage method of MessageDAO threw error:"+e.getMessage());
		e.printStackTrace();
	}finally{
		try {
			conn.close();
		} catch (SQLException e) {
			logger.error("setAdvisorMessage method of MessageDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}
	}	
		if(isMessageCommit){
			return time;
		}else{
			return null;
		}
}

public List<MessageDTO> getAdvisorMessages( int advisorId){
	
	
	logger.info("Entered getAdvisorMessages method of MessageDAO");
	List<MessageDTO> list = new ArrayList<MessageDTO>();

	PreparedStatement pstmt;
	try {
		conn =ConnectionFactory.getConnection();
		conn.setAutoCommit(false);
		String query ="SELECT * FROM advisormessageadmin WHERE ADVISOR_ID = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, advisorId);
	    ResultSet results = pstmt.executeQuery();
	    while(results.next()){
	    	MessageDTO message = new MessageDTO();
	    	message.setMessageId(results.getInt("MESSAGE_ID")); 
	    	message.setAdvisorId(results.getInt("ADVISOR_ID"));
	    	message.setAdvisorMessage(results.getString("ADVISOR_MESSAGE"));
	    	message.setAdvisorMessageTime(results.getTimestamp("ADVISOR_MESSAGE_TIME"));
	    	message.setAdvisorId(advisorId);
	    	if( results.getString("ADMIN_MESSAGE") != null && results.getTimestamp("ADMIN_MESSAGE_TIME")!= null && !("").equals(results.getString("ADMIN_MESSAGE")) &&  !("0000-00-00 00:00:00").equals(results.getTimestamp("ADMIN_MESSAGE_TIME").toString())){
		    	message.setAdminMessage(results.getString("ADMIN_MESSAGE"));
		    	message.setAdminMessageTime(results.getTimestamp("ADMIN_MESSAGE_TIME"));
	    	}
	    	list.add(message);
	    }
	    conn.commit();
	logger.info("Exit getAdvisorMessages method of MessageDAO");
	} catch (SQLException e) {
		try {
			conn.rollback();
		} catch (SQLException e1) {
			logger.error("getAdvisorMessages method of MessageDAO threw error:"+e.getMessage());
			e1.printStackTrace();
		}
		logger.error("getAdvisorMessages method of MessageDAO threw error:"+e.getMessage());
		e.printStackTrace();
	} catch (IOException e) {
		logger.error("getAdvisorMessages method of MessageDAO threw error:"+e.getMessage());
		e.printStackTrace();
	} catch (PropertyVetoException e) {
		logger.error("getAdvisorMessages method of MessageDAO threw error:"+e.getMessage());
		e.printStackTrace();
	}finally{
		try {
			conn.close();
		} catch (SQLException e) {
			logger.error("getAdvisorMessages method of MessageDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}
	}	

	
	logger.info("Exit getAdvisorMessages method of MessageDAO");
	return list;
}

public Boolean  setAdminMessage(String message,  String messageId ) { 
	
	
	logger.info("Entered setAdvisorMessage method of MessageDAO");
	int result = 0;
	Boolean isMessageCommit = false;
	Timestamp time = null;
	try{
		if(message != null && !("").equals(message) && messageId != null && !("").equals(messageId)) {
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
	         conn =ConnectionFactory.getConnection();
				conn.setAutoCommit(false);
			String query = "UPDATE advisormessageadmin SET ADMIN_MESSAGE = ?, ADMIN_MESSAGE_TIME = ? WHERE MESSAGE_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1,message);
			time = new java.sql.Timestamp(date.getTime());
			pstmt.setTimestamp(2,time );
			pstmt.setString(3,messageId);
		    result = pstmt.executeUpdate();
		    if(result > 0){
		    	conn.commit();
		    	isMessageCommit = true;
		    }
		}
		logger.info("Exit setAdvisorMessage method of MessageDAO");
} catch (SQLException e) {
	try {
		conn.rollback();
	} catch (SQLException e1) {
		logger.error("setAdvisorMessage method of MessageDAO threw error:"+e.getMessage());
		e1.printStackTrace();
	}
	logger.error("setAdvisorMessage method of MessageDAO threw error:"+e.getMessage());
	e.printStackTrace();
	} catch (IOException e) {
		logger.error("setAdvisorMessage method of MessageDAO threw error:"+e.getMessage());
	e.printStackTrace();
} catch (PropertyVetoException e) {
	logger.error("setAdvisorMessage method of MessageDAO threw error:"+e.getMessage());
	e.printStackTrace();
}finally{
		try {
			conn.close();
		} catch (SQLException e) {
			logger.error("setAdvisorMessage method of MessageDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}
	}	
	return isMessageCommit;
}
public Boolean  InsertAdminMessage(String message,  int advisorId ) { 
	logger.info("Entered InsertAdminMessage method of MessageDAO");
	int result = 0;
	Boolean isMessageCommit = false;
	Timestamp time = null;
	try{
		if(message != null && !("").equals(message) && advisorId != 0) {
			
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
	         conn =ConnectionFactory.getConnection();
				conn.setAutoCommit(false);
			String query = "insert into advisormessageadmin"+"(ADMIN_MESSAGE,ADMIN_MESSAGE_TIME,ADVISOR_ID) values" + "(?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1,message);
			time = new java.sql.Timestamp(date.getTime());
			pstmt.setTimestamp(2,time );
			pstmt.setInt(3,advisorId);
		    result = pstmt.executeUpdate();
		    if(result > 0){
		    	conn.commit();
		    	isMessageCommit = true;
		    }
		}
		logger.info("Exit InsertAdminMessage method of MessageDAO");
} catch (SQLException e) {
	try {
		conn.rollback();
	} catch (SQLException e1) {
		logger.error("InsertAdminMessage method of MessageDAO threw error:"+e.getMessage());
		e1.printStackTrace();
	}
	logger.error("InsertAdminMessage method of MessageDAO threw error:"+e.getMessage());
	e.printStackTrace();
} catch (IOException e) {
	logger.error("InsertAdminMessage method of MessageDAO threw error:"+e.getMessage());
	e.printStackTrace();
} catch (PropertyVetoException e) {
	logger.error("InsertAdminMessage method of MessageDAO threw error:"+e.getMessage());
	e.printStackTrace();
}finally{
	try {
		conn.close();
	} catch (SQLException e) {
		logger.error("InsertAdminMessage method of MessageDAO threw error:"+e.getMessage());
		e.printStackTrace();
	}
}
	return isMessageCommit;	

}
}
