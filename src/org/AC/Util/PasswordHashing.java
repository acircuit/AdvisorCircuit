package org.AC.Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import org.AC.DAO.BookASessionDAO;
import org.apache.log4j.Logger;

public class PasswordHashing {
	private static final Logger logger = Logger.getLogger(PasswordHashing.class);
	
	
	//This method is used to hash the password entered by the user
	 public  String doHash(String pass) throws NoSuchAlgorithmException, NoSuchProviderException 
	    {	
		 	logger.info("Entered doHash method of PasswordHashing");
	        String passwordToHash = pass;
	        String salt = "[B@692ec047";
	         
	        String securePassword = getSecurePassword(passwordToHash, salt);
	        logger.info("Exit doHash method of PasswordHashing");
	        return securePassword;
	    }
		    
		   
		//This method is used add the extra salt for more security
		 private static String getSecurePassword(String passwordToHash, String salt)
		    {	
			 	logger.info("Entered getSecurePassword method of PasswordHashing");
		        String generatedPassword = null;
		        try {
		            // Create MessageDigest instance for MD5
		            MessageDigest md = MessageDigest.getInstance("MD5");
		            //Add password bytes to digest
		            md.update(salt.getBytes());
		            //Get the hash's bytes 
		            byte[] bytes = md.digest(passwordToHash.getBytes());
		            //This bytes[] has bytes in decimal format;
		            //Convert it to hexadecimal format
		            StringBuilder sb = new StringBuilder();
		            for(int i=0; i< bytes.length ;i++)
		            {
		                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		            }
		            //Get complete hashed password in hex format
		            generatedPassword = sb.toString();
		            logger.info("Exit getSecurePassword method of PasswordHashing");		        } 
		        catch (NoSuchAlgorithmException e) {
		        	logger.error("getSecurePassword method of PasswordHashing threw error:"+e.getMessage());
		            e.printStackTrace();
		        }
		        return generatedPassword;
		    }
}
