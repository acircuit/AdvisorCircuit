package org.AC.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.AC.DAO.UserDetailsDAO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class SetFormImage {
	private static final Logger logger = Logger.getLogger(SetFormImage.class);
	
	
	//This function will retrieve the image uploaded by the user and put the image in a specified location
	
	public String putImage(HttpServletRequest request, HttpServletResponse response,String email){
	
	BasicConfigurator.configure();
	logger.info("Entered putImage method of SetFormImage");			
	String filePath = "E:\\AC\\WebContent\\assets\\UserImages\\";
    OutputStream out1 = null;
    InputStream filecontent = null;
     String absolutePath = "";
    try {
	if (request.getPart("file") != null) {
        if (request.getPart("file").getSize() != 0) {
            final Part filePart = request.getPart("file");
            final String folderName = filePath + email;//path;
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
	logger.info("Exit putImage method of SetFormImage");			
	} catch (FileNotFoundException e) {
			logger.error("putImage method of SetFormImage threw error:"+e.getMessage());
			e.printStackTrace();
  } catch (IOException e) {
		logger.error("putImage method of SetFormImage threw error:"+e.getMessage());
		e.printStackTrace();
	} catch (ServletException e) {
		logger.error("putImage method of SetFormImage threw error:"+e.getMessage());
		e.printStackTrace();
	} finally {
	      if (out1 != null) {
	          try {
					out1.close();
				} catch (IOException e) {
					logger.error("putImage method of SetFormImage threw error:"+e.getMessage());
					e.printStackTrace();
				}
	      }
	      if (filecontent != null) {
	          try {
					filecontent.close();
				} catch (IOException e) {
					logger.error("putImage method of SetFormImage threw error:"+e.getMessage());
					e.printStackTrace();
				}
	      }
	}
	return absolutePath;
    }

//This function is to retrieve the name of the file uploaded by the user 
	private String getFileName(final Part part) {
		logger.info("Entered getFileName method of SetFormImage");			
		try{
		    for (String content : part.getHeader("content-disposition").split(";")) {
		        if (content.trim().startsWith("filename")) {
		            return content.substring(
		                    content.indexOf('=') + 1).trim().replace("\"", "");
		        }
		    }
			logger.info("Exit getFileName method of SetFormImage");			
		}catch(Exception e){
			logger.error("getFileName method of SetFormImage threw error:"+e.getMessage());
			e.printStackTrace();
		}
	    return null;
	}
}

