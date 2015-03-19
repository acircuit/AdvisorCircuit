 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 29/11/2014
 * ************************************************************************************************/
package org.AC.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.MyAccountRequestDAO;
import org.AC.DAO.SessionMssagesDAO;

/* *******************************CLASS SUMMARY****************************************************
* 
* This class will be called from the view details page , when the advisor would want to download 
* user cv.
* 
* 
*
***************************************************************************************************/
/**
 * Servlet implementation class DownloadFileServlet
 */
@WebServlet("/DownloadFileServlet")
public class DownloadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String rId = request.getParameter("rid");
		String id = request.getParameter("id");
		String path = "";
		if(rId != null){
			MyAccountRequestDAO cv = new MyAccountRequestDAO();
			path = cv.getCvPath(rId);	
		}
		if(id != null){
			SessionMssagesDAO file = new SessionMssagesDAO();
			path = file.getFilePath(id);	
		}
		if(rId == null && id ==null){
			path = request.getParameter("path");
		}
		if(!("").equals(path)){
		// reads input file from an absolute path
       // String filePath = "E:/Test/Download/MYPIC.JPG";
        File downloadFile = new File(path);
        FileInputStream inStream = new FileInputStream(downloadFile);
         
        // if you want to use a relative path to context root:
        String relativePath = getServletContext().getRealPath("");
        //System.out.println("relativePath = " + relativePath);
         
        // obtains ServletContext
        ServletContext context = getServletContext();
         
        // gets MIME type of the file
        String mimeType = context.getMimeType(path);
        if (mimeType == null) {        
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);
         
        // modifies response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
         
        // forces download
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);
         
        // obtains response's output stream
        OutputStream outStream = response.getOutputStream();
         
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
         
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
         
        inStream.close();
        outStream.close(); 
		}
    }
}
