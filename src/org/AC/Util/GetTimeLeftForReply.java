package org.AC.Util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.AC.controller.AdvisorMyAccountRequestController;
import org.AC.dto.TimeDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class GetTimeLeftForReply {
	private static final Logger logger = Logger.getLogger(GetTimeLeftForReply.class);     

	
	public List<TimeDTO> getHoursAndMinutes(Timestamp bookingTime){
		
		
		logger.info("Entered getHoursAndMinutes method of GetTimeLeftForReply");
		List<TimeDTO> list = new ArrayList<TimeDTO>();
		try{
			if(bookingTime != null ){
				Calendar c = Calendar.getInstance(); 
				c.setTime(new Date(bookingTime.getTime())); 
				c.add(Calendar.HOUR, 72);
				Date date1= c.getTime();
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
		         Date date2 = cal.getTime();
				System.out.println(date1);
				System.out.println(date2);
				if(date1.compareTo(date2) >= 0){
			        long diff = date1.getTime() - date2.getTime();
			       // long diffSeconds = diff / 1000 % 60;
		        	long diffMinutes = diff / (60 * 1000) % 60;
			        
			        int diffInDays = (int) ((date1.getTime() - date2.getTime()) / (1000 * 60 * 60 * 24));
			        int denominator= diffInDays * 24;
			        long diffHours = diff / (60 * 60 * 1000) % denominator;
			        TimeDTO time = new TimeDTO();
			        time.setDay(diffInDays);
			        time.setHours(diffHours);
			        time.setMinutes(diffMinutes);
			        list.add(time);
				}
			}
		}catch(Exception e){
			logger.error("getHoursAndMinutes method of GetTimeLeftForReply threw error:"+e.getMessage());
			e.printStackTrace();
		}
		logger.info("Exit getHoursAndMinutes method of GetTimeLeftForReply");
		return list;
		
		
	}
	
public List<TimeDTO> getTimeLeftForSession(Timestamp bookingTime){
		
		
		logger.info("Entered getHoursAndMinutes method of GetTimeLeftForReply");
		List<TimeDTO> list = new ArrayList<TimeDTO>();
		try{
			if(bookingTime != null ){
				Calendar c = Calendar.getInstance(); 
				c.setTime(new Date(bookingTime.getTime())); 
				Date date1= c.getTime();
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
		         Date date2 = cal.getTime();
				if(date1.compareTo(date2) >= 0){
			        long diff = date1.getTime() - date2.getTime();
			       // long diffSeconds = diff / 1000 % 60;	
			       // long diffSeconds = diff / 1000 % 60;
			        long diffMinutes = diff / (60 * 1000) % 60;
			        
			        int diffInDays = (int) ((date1.getTime() - date2.getTime()) / (1000 * 60 * 60 * 24));
			        int denominator= diffInDays * 24;
			        long diffHours = diff / (60 * 60 * 1000) % denominator;
			        TimeDTO time = new TimeDTO();
			        time.setDay(diffInDays);
			        time.setHours(diffHours);
			        time.setMinutes(diffMinutes);
			        list.add(time);
				}
			}
		}catch(Exception e){
			logger.error("getHoursAndMinutes method of GetTimeLeftForReply threw error:"+e.getMessage());
			e.printStackTrace();
		}
		logger.info("Exit getHoursAndMinutes method of GetTimeLeftForReply");
		return list;
		
		
	}
}
