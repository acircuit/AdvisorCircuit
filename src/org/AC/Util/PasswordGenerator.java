package org.AC.Util;

import org.apache.log4j.Logger;

public class PasswordGenerator {
	private static final Logger logger = Logger.getLogger(PasswordGenerator.class);

	
	
	//This method is used to generate a  password entered by the user using username and advisor id.
		 public  String generatePassword(String userName, int advisorId)  
		    {	
			 	logger.info("Entered generatePassword method of PasswordGenerator");
			 	String password = "";
			 	try{
				 	if(!userName.isEmpty() && advisorId != 0){
				 		
				 		 password = userName+advisorId;
				 	}
			 	}catch(Exception e){
					logger.error("generatePassword method of PasswordGenerator threw error:"+e.getMessage());
			 		e.printStackTrace();
			 	}
		       
		        logger.info("Exit generatePassword method of PasswordGenerator");
				return password;
		    }
}
