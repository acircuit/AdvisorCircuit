 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 29/11/2014
 * ************************************************************************************************/

package org.AC.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;



 /* *************************CLASS SUMMARY***********************************************************
 * 
 *  This function will will be called to put the CV of the user from the "BOOK A SESSION" form
 *  (if the services are moack interview or cvcritique) in the required folder.So that it can be retrieved
 *   by the advisor from the request form. 
 * 
 * 
 *
 ***************************************************************************************************/

public class SetCV {
	private static final Logger logger = Logger.getLogger(SetCV.class);
	
	 /**************************************COMMENTS***************************************************
	 * This function will will be called to put the CV of the user from the "BOOK A SESSION" form
	 *  (if the services are moack interview or cvcritique) in the required folder.So that it can be retrieved
	 *   by the advisor from the request form. 
	 *   @
	 *   @return :String absolutePath
	 *   @param : HttpServletRequest request
	 *   		  HttpServletResponse response
	 *   		  int uId
	 *
	 ***************************************************************************************************/
	
	public String putCV(HttpServletRequest request, HttpServletResponse response,int uId){
	
	BasicConfigurator.configure();
	logger.info("Entered putCV method of SetCV");			
	String filePath = "E:\\Tomcat7\\WebContent\\assets\\UserCV\\";
    OutputStream out1 = null;
    InputStream filecontent = null;
     String absolutePath = "";
    try {
	if (request.getPart("cv") != null) {
        if (request.getPart("cv").getSize() != 0) {
            final Part filePart = request.getPart("cv");
            final String folderName = filePath + uId;//path;
            final String fileName = getFileName(filePart);
            absolutePath = folderName+ File.separator + fileName;
            //
             File file = new File(folderName);
              if (!file.exists()) {
             file.mkdirs();
             }
                out1 = new FileOutputStream(new File(folderName + File.separator
                        + fileName));
                filecontent = filePart.getInputStream();
                StringBuilder sb = new StringBuilder();
                int read = 0;
                final byte[] bytes = new byte[1024];
            // byte[] byte1 = new byte[1024];
                //   byte1=IOUtils.toByteArray(filecontent);
                //  System.out.println(byte1.toString()); 
                while ((read = filecontent.read(bytes)) != -1) {
                    sb.append(bytes);
                    out1.write(bytes, 0, read);
                }
        }  
	}
	logger.info("Exit putCV method of SetCV");			
	} catch (FileNotFoundException e) {
			logger.error("putCV method of SetCV threw error:"+e.getMessage());
			e.printStackTrace();
  } catch (IOException e) {
		logger.error("putCV method of SetCV threw error:"+e.getMessage());
		e.printStackTrace();
	} catch (ServletException e) {
		logger.error("putCV method of SetCV threw error:"+e.getMessage());
		e.printStackTrace();
	} finally {
	      if (out1 != null) {
	          try {
					out1.close();
				} catch (IOException e) {
					logger.error("putCV method of SetCV threw error:"+e.getMessage());
					e.printStackTrace();
				}
	      }
	      if (filecontent != null) {
	          try {
					filecontent.close();
				} catch (IOException e) {
					logger.error("putCV method of SetCV threw error:"+e.getMessage());
					e.printStackTrace();
				}
	      }
	}
	return absolutePath;
    }
	
	 /**************************************COMMENTS***************************************************
	 * This function is to retrieve the name of the file uploaded by the user
	 *
	 ***************************************************************************************************/
	private String getFileName(final Part part) {
		logger.info("Entered getFileName method of SetCV");			
		try{
		    for (String content : part.getHeader("content-disposition").split(";")) {
		        if (content.trim().startsWith("filename")) {
		            return content.substring(
		                    content.indexOf('=') + 1).trim().replace("\"", "");
		        }
		    }
			logger.info("Exit getFileName method of SetCV");			
		}catch(Exception e){
			logger.error("getFileName method of SetCV threw error:"+e.getMessage());
			e.printStackTrace();
		}
	    return null;
	}
}
