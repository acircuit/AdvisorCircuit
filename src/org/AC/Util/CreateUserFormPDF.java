package org.AC.Util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdvisorRegistrationDAO;
import org.AC.dto.AdvisorModeDTO;
import org.AC.dto.AdvisorServiceDTO;
import org.AC.dto.ProfessionalBackgroundDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Header;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.HeaderFooter;


public class CreateUserFormPDF {
	private static final Logger logger = Logger.getLogger(CreateUserFormPDF.class);
	private static Font catFont = new Font(Font.FontFamily.HELVETICA, 12,
		      Font.UNDEFINED);
	private static Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
		      Font.UNDERLINE);
	public void createPDF(HttpServletResponse response,int userId,String email,String name,String phone,String age,String nationality,String occupation,String image){
		
		BasicConfigurator.configure();
		logger.info("Entered createPDF method of CreateUserFormPDF");
		//browser will open the document only if this is set
	    response.setContentType("application/pdf");
		Document document = new Document();
	    try {
	    	OutputStream out=response.getOutputStream();
			PdfWriter.getInstance(document, out);
			document.open();
			addTitlePage(document);
			addImage(document,image);
			addProfileDetails(document,userId,email,name,phone,age,nationality,occupation);
			document.close();
			logger.info("Exit createPDF method of CreateUserFormPDF");
		} catch (FileNotFoundException e) {
			logger.error("createPDF method of CreateUserFormPDF threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (DocumentException e) {
			logger.error("createPDF method of CreateUserFormPDF threw error:"+e.getMessage());
			e.printStackTrace();
		}catch (IOException e1) {
			logger.error("createPDF method of CreateUserFormPDF threw error:"+e1.getMessage());
			e1.printStackTrace();
		}
	}
	

	private static void addTitlePage(Document document)
		      throws DocumentException {
			logger.info("Entered addTitlePage method of CreateUserFormPDF");

		    Paragraph preface = new Paragraph();
		    // We add one empty line
		    document.add( Chunk.NEWLINE );
		    // Lets write a big header
		    preface.add(new Paragraph("User Form"+"\n", titleFont));
		    document.add(preface);
			logger.info("Entered addTitlePage method of CreateUserFormPDF");
		  }
	
	 private static void addProfileDetails(Document document,int userId,String email,String name,String phone,String age,String nationality,String occupation) {
		 
		 logger.info("Entered addProfileDetails method of CreateUserFormPDF");
		 Paragraph preface = new Paragraph();
		 try {
		 preface.add(new Paragraph("UserId :"+userId+"\n", catFont));
		 preface.add(new Paragraph("Name :"+name+"\n", catFont));
		 preface.add(new Paragraph("Age :"+age+"\n", catFont));
		 preface.add(new Paragraph("Email :"+email+"\n", catFont));
		 preface.add(new Paragraph("Nationality :"+nationality+"\n", catFont));
		 preface.add(new Paragraph("Phone :"+phone+"\n", catFont));
		 preface.add(new Paragraph("Occupation :"+occupation+"\n", catFont));
		 document.add(preface);
		 logger.info("Exit addProfileDetails method of CreateUserFormPDF");
		} catch (DocumentException e) {
			logger.error("addProfileDetails method of CreateUserFormPDF threw error:"+e.getMessage());
			e.printStackTrace();
		}
	 }
	 
	 
private static void addImage(Document document,String imageURL){
		 
		 logger.info("Entered addImage method of CreateUserFormPDF");
		 try {
			 if( !imageURL.isEmpty()) {
				        document.open();
				        //imageURL1 = imageURL.replaceAll('\', "\\");
				        Image image2 = Image.getInstance(imageURL);
				        image2.setAbsolutePosition(300, 600);
		                document.add(image2);
					}
			 logger.info("Exit addImage method of CreateUserFormPDF");
		 }catch (DocumentException e) {
				logger.error("addImage method of CreateUserFormPDF threw error:"+e.getMessage());
				e.printStackTrace();
			}catch (Exception e) {
				logger.error("addImage method of CreateUserFormPDF threw error:"+e.getMessage());
				e.printStackTrace();
			}
			
	}
}
	 
