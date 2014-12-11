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


public class CreateAdvisorFormPDF {
	private static final Logger logger = Logger.getLogger(AdvisorRegistrationDAO.class);
	private static String FILE = "c:/temp/FirstPdf.pdf";
	private static Font catFont = new Font(Font.FontFamily.HELVETICA, 12,
		      Font.UNDEFINED);
	private static Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
		      Font.UNDERLINE);
	public void createPDF(HttpServletResponse response,String name,String gender,String age,String email,String city,String nationality,
			String phone,String industry,String organisation,String job,String experience,String ug,
			String pg,String others,String achievements,String keyskills,String hobbies,String funfact ,
			List<ProfessionalBackgroundDTO> list,List<AdvisorServiceDTO> list1,List<AdvisorModeDTO> list2,String image){
		
		BasicConfigurator.configure();
		logger.info("Entered createPDF method of CreateAdvisorFormPDF");
		//browser will open the document only if this is set
	    response.setContentType("application/pdf");
		Document document = new Document();
	    try {
	    	OutputStream out=response.getOutputStream();
			PdfWriter.getInstance(document, out);
			document.open();
			addTitlePage(document);
			addImage(document,email,image);
			addProfileDetails(document,name,gender,age,email,city,nationality,phone,industry,organisation,job,experience,ug,pg,others,achievements
					,keyskills,hobbies,funfact);
			addProfessionalBackground(document,list);
			addServicesAndModes(document,list1,list2);
			document.close();
			logger.info("Exit createPDF method of CreateAdvisorFormPDF");
		} catch (FileNotFoundException e) {
			logger.error("createPDF method of CreateAdvisorFormPDF threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (DocumentException e) {
			logger.error("createPDF method of CreateAdvisorFormPDF threw error:"+e.getMessage());
			e.printStackTrace();
		}catch (IOException e1) {
			logger.error("createPDF method of CreateAdvisorFormPDF threw error:"+e1.getMessage());
			e1.printStackTrace();
		}
	}
	

	private static void addTitlePage(Document document)
		      throws DocumentException {
			logger.info("Entered addTitlePage method of CreateAdvisorFormPDF");

		    Paragraph preface = new Paragraph();
		    // We add one empty line
		    document.add( Chunk.NEWLINE );
		    // Lets write a big header
		    preface.add(new Paragraph("Advisor Registration Form"+"\n", titleFont));
		    document.add(preface);
			logger.info("Entered addTitlePage method of CreateAdvisorFormPDF");
		  }
	
	 private static void addProfileDetails(Document document,String name,String gender,String age,String email,String city,String nationality,String phone,
			 String industry,String organisation,String job,String experience,String ug,String pg,String others,
			 String achievements,String keyskills,String hobbies,String funfact) {
		 
		 logger.info("Entered addProfileDetails method of CreateAdvisorFormPDF");
		 Paragraph preface = new Paragraph();
		 try {
		 preface.add(new Paragraph("Name :"+name+"\n", catFont));
		 preface.add(new Paragraph("Gender :"+gender+"\n", catFont));
		 preface.add(new Paragraph("Age :"+age+"\n", catFont));
		 preface.add(new Paragraph("Email :"+email+"\n", catFont));
		 preface.add(new Paragraph("City :"+city+"\n", catFont));
		 preface.add(new Paragraph("Nationality :"+nationality+"\n", catFont));
		 preface.add(new Paragraph("Phone :"+phone+"\n", catFont));
		 preface.add(new Paragraph("Industry :"+industry+"\n", catFont));
		 preface.add(new Paragraph("Name Of The Organization :"+organisation+"\n", catFont));
		 preface.add(new Paragraph("Job Title :"+job+"\n", catFont));
		 preface.add(new Paragraph("Experience in Relevant Industry :"+experience+"\n", catFont));
		 preface.add(new Paragraph("Undergraduate :"+ug+"\n", catFont));
		 preface.add(new Paragraph("Post Graduate :"+pg+"\n", catFont));
		 preface.add(new Paragraph("Any other :"+others+"\n", catFont));
		 preface.add(new Paragraph("Achievements and Awards :"+achievements+"\n", catFont));
		 preface.add(new Paragraph("Key Skills :"+keyskills+"\n", catFont));
		 preface.add(new Paragraph("Hobbies and interests :"+hobbies+"\n", catFont));
		 preface.add(new Paragraph("A fun fact about yourself :"+funfact+"\n", catFont));
		 document.add(preface);
		 logger.info("Exit addProfileDetails method of CreateAdvisorFormPDF");
		} catch (DocumentException e) {
			logger.error("addProfileDetails method of CreateAdvisorFormPDF threw error:"+e.getMessage());
			e.printStackTrace();
		}
	 }
	 
	 private static void addProfessionalBackground(Document document,List<ProfessionalBackgroundDTO> list){
		 
		 logger.info("Entered addProfessionalBackground method of CreateAdvisorFormPDF");
		 try {
			 if(list.size() > 0) {
				 document.newPage();
				 Paragraph preface = new Paragraph();
				 preface.add(new Paragraph("Professional Background : \n", catFont));
				 for (ProfessionalBackgroundDTO obj : list) {
					 preface.add(new Paragraph("Company :"+obj.getCompany()+ "\n", catFont));
					 preface.add(new Paragraph("Designation :"+obj.getDesignation()+ "\n", catFont));
					 preface.add(new Paragraph("Duration :"+obj.getDuration()+ "\n", catFont));
					 preface.add(new Paragraph("Description :"+obj.getDescription()+ "\n", catFont));
				}
				 document.add(preface);

				}
			 logger.info("Exit addProfessionalBackground method of CreateAdvisorFormPDF");
		 }catch (DocumentException e) {
				logger.error("addProfessionalBackground method of CreateAdvisorFormPDF threw error:"+e.getMessage());
				e.printStackTrace();
			}catch (Exception e) {
				logger.error("addProfessionalBackground method of CreateAdvisorFormPDF threw error:"+e.getMessage());
				e.printStackTrace();
			}
		 }
	 

	 private static void addServicesAndModes(Document document,List<AdvisorServiceDTO> list1,List<AdvisorModeDTO> list2){
		 
		 logger.info("Entered addServicesAndModes method of CreateAdvisorFormPDF");
		 try {
			 if(list1.size() > 0 && list1.size() > 0) {
				 document.newPage();
				 Paragraph preface = new Paragraph();
				 preface.add(new Paragraph("Services And Modes : \n", catFont));
				 for (AdvisorServiceDTO service : list1) {
					 preface.add(new Paragraph("Service :"+service.getService()+ "\n", catFont));
					 preface.add(new Paragraph("Description :"+service.getDescription()+ "\n", catFont));
					 for (AdvisorModeDTO modes : list2) {
						 if(service.getAdvisorId() == modes.getAdvisorId()){
							 preface.add(new Paragraph("Mode :"+modes.getModeOfCommunication()+ "\n", catFont)); 
							 preface.add(new Paragraph("Price :"+modes.getPrice()+ "\n", catFont)); 

						 }
						
					}
					
				}
				 document.add(preface);

				}
			 logger.info("Exit addServicesAndModes method of CreateAdvisorFormPDF");
		 }catch (DocumentException e) {
				logger.error("addServicesAndModes method of CreateAdvisorFormPDF threw error:"+e.getMessage());
				e.printStackTrace();
			}catch (Exception e) {
				logger.error("addServicesAndModes method of CreateAdvisorFormPDF threw error:"+e.getMessage());
				e.printStackTrace();
			}
	 }
	 
	 
private static void addImage(Document document,String email,String imageURL){
		 
		 logger.info("Entered addImage method of CreateAdvisorFormPDF");
		 try {
			 if(!email.isEmpty() && !imageURL.isEmpty()) {
				        document.open();
				        //imageURL1 = imageURL.replaceAll('\', "\\");
				        Image image2 = Image.getInstance(imageURL);
				        image2.setAbsolutePosition(300, 600);
		                document.add(image2);
					}
			 logger.info("Exit addImage method of CreateAdvisorFormPDF");
		 }catch (DocumentException e) {
				logger.error("addImage method of CreateAdvisorFormPDF threw error:"+e.getMessage());
				e.printStackTrace();
			}catch (Exception e) {
				logger.error("addImage method of CreateAdvisorFormPDF threw error:"+e.getMessage());
				e.printStackTrace();
			}
			
	}
}
	 
