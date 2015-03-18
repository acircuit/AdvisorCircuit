package org.AC.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;



import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.AC.DAO.BookASessionDAO;
import org.apache.log4j.Logger;

public class ConvertStringToDate {
	private static final Logger logger = Logger.getLogger(BookASessionDAO.class);

	
	
	//This method is used to convert String Date to a Date type.
	public Date convertToDate( String date) {
		logger.info("Entered convertToDate method of ConvertStringToDate");
		Date formattedDate = null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		try {
			formattedDate =  (Date) formatter.parse(date);
			 System.out.println(formattedDate);
			logger.info("Exit convertToDate method of ConvertStringToDate");
		} catch (ParseException e) {
			logger.error("convertToDate method of ConvertStringToDate threw error:"+e.getMessage());
			e.printStackTrace();
		}
		return formattedDate;
		
	}
}
