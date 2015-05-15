package org.AC.Util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.AdvisorRegistrationDAO;
import org.AC.dto.AdvisorEducationDTO;
import org.AC.dto.AdvisorModeDTO;
import org.AC.dto.AdvisorProfileDTO;
import org.AC.dto.AdvisorServiceDTO;
import org.AC.dto.ProfessionalBackgroundDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class AdvisorRegistrationPDF {
	private static final Logger logger = Logger
			.getLogger(AdvisorRegistrationPDF.class);
	private static String FILE = "c:/temp/FirstPdf.pdf";
	private static Font catFont = new Font(Font.FontFamily.HELVETICA, 12,
			Font.UNDEFINED);
	private static Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
			Font.UNDERLINE);

	public void createPDF(HttpServletResponse response,
			List<AdvisorProfileDTO> advisor,
			List<ProfessionalBackgroundDTO> professionalBackground,
			List<AdvisorServiceDTO> services, List<AdvisorModeDTO> modes,
			List<AdvisorEducationDTO> education,
			List<AdvisorProfileDTO> keySkills, List<AdvisorProfileDTO> awards) {
		logger.info("Entered createPDF method of AdvisorRegistrationPDF");
		// browser will open the document only if this is set
		response.setContentType("application/pdf");
		Document document = new Document();
		try {
			OutputStream out = response.getOutputStream();
			PdfWriter.getInstance(document, out);
			document.open();
			addTitlePage(document);
			addImage(document, advisor);
			addGeneralInfo(document, advisor);
			addEducationInfo(document, education);
			addProfessionalBackground(document, professionalBackground, advisor);
			addOtherInfo(document, keySkills, awards, advisor);
			addServicesAndModes(document, services, modes);
			document.close();
			logger.info("Exit createPDF method of AdvisorRegistrationPDF");
		} catch (FileNotFoundException e) {
			logger.error("createPDF method of AdvisorRegistrationPDF threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} catch (DocumentException e) {
			logger.error("createPDF method of AdvisorRegistrationPDF threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} catch (IOException e1) {
			logger.error("createPDF method of AdvisorRegistrationPDF threw error:"
					+ e1.getMessage());
			e1.printStackTrace();
		}
	}

	private static void addTitlePage(Document document)
			throws DocumentException {
		logger.info("Entered addTitlePage method of AdvisorRegistrationPDF");

		Paragraph preface = new Paragraph();
		// We add one empty line
		document.add(Chunk.NEWLINE);
		// Lets write a big header
		preface.add(new Paragraph("Advisor Registration Form" + "\n", titleFont));
		document.add(preface);
		logger.info("Entered addTitlePage method of AdvisorRegistrationPDF");
	}

	private static void addEducationInfo(Document document,
			List<AdvisorEducationDTO> education) {

		logger.info("Entered addEducationInfo method of AdvisorRegistrationPDF");
		Paragraph preface = new Paragraph();
		try {
			preface.add(new Paragraph("Education Information" + "\n", titleFont));
			for (AdvisorEducationDTO edu : education) {
				preface.add(new Paragraph("Education :" + edu.getEducation()
						+ "\n", catFont));
			}
			document.add(preface);
			logger.info("Exit addEducationInfo method of AdvisorRegistrationPDF");
		} catch (DocumentException e) {
			logger.error("addEducationInfo method of AdvisorRegistrationPDF threw error:"
					+ e.getMessage());
			e.printStackTrace();
		}
	}

	private static void addGeneralInfo(Document document,
			List<AdvisorProfileDTO> advisor) {

		logger.info("Entered addGeneralInfo method of AdvisorRegistrationPDF");
		Paragraph preface = new Paragraph();
		try {
			preface.add(new Paragraph("General Information" + "\n", titleFont));
			for (AdvisorProfileDTO profile : advisor) {
				preface.add(new Paragraph("Name :" + profile.getName() + "\n",
						catFont));
				preface.add(new Paragraph(
						"Email :" + profile.getEmail() + "\n", catFont));
				preface.add(new Paragraph("Gender :" + profile.getGender()
						+ "\n", catFont));
				preface.add(new Paragraph("Age :" + profile.getAge() + "\n",
						catFont));
				preface.add(new Paragraph(
						"Phone :" + profile.getPhone() + "\n", catFont));
				preface.add(new Paragraph("City :" + profile.getCity() + "\n",
						catFont));
				preface.add(new Paragraph(
						"State :" + profile.getState() + "\n", catFont));
				preface.add(new Paragraph("Industry :" + profile.getIndustry()
						+ "\n", catFont));
				preface.add(new Paragraph("Introduction :"
						+ profile.getIntroduction() + "\n", catFont));
				document.add(preface);
			}
			logger.info("Exit addGeneralInfo method of AdvisorRegistrationPDF");
		} catch (DocumentException e) {
			logger.error("addGeneralInfo method of AdvisorRegistrationPDF threw error:"
					+ e.getMessage());
			e.printStackTrace();
		}
	}

	private static void addOtherInfo(Document document,
			List<AdvisorProfileDTO> keySkills, List<AdvisorProfileDTO> awards,
			List<AdvisorProfileDTO> advisor) {

		logger.info("Entered addOtherInfo method of AdvisorRegistrationPDF");
		Paragraph preface = new Paragraph();
		try {
			preface.add(new Paragraph("Other Information" + "\n", titleFont));
			for (AdvisorProfileDTO profile : awards) {
				preface.add(new Paragraph("Achievements and Awards :"
						+ profile.getAchievements() + "\n", catFont));
			}
			for (AdvisorProfileDTO skills : keySkills) {
				preface.add(new Paragraph("KeySkills :" + skills.getKeyskill()
						+ "\n", catFont));
			}
			for (AdvisorProfileDTO hobby : advisor) {
				preface.add(new Paragraph(
						"Know Your Advisor Better(Hobbies & Interests) :"
								+ hobby.getKnowYourAdvisor() + "\n", catFont));
			}
			document.add(preface);
			logger.info("Exit addOtherInfo method of AdvisorRegistrationPDF");
		} catch (DocumentException e) {
			logger.error("addOtherInfo method of AdvisorRegistrationPDF threw error:"
					+ e.getMessage());
			e.printStackTrace();
		}
	}

	private static void addProfessionalBackground(Document document,
			List<ProfessionalBackgroundDTO> prof,
			List<AdvisorProfileDTO> advisor) {

		logger.info("Entered addProfessionalBackground method of AdvisorRegistrationPDF");
		try {
			if (prof.size() > 0) {
				document.newPage();
				Paragraph preface = new Paragraph();
				preface.add(new Paragraph("Professional Background" + "\n",
						titleFont));
				for (AdvisorProfileDTO adv : advisor) {
					preface.add(new Paragraph("Experience :"
							+ adv.getExperience() + "\n", catFont));
				}
				for (ProfessionalBackgroundDTO obj : prof) {
					preface.add(new Paragraph("Company :" + obj.getCompany()
							+ "\n", catFont));
					preface.add(new Paragraph("Designation :"
							+ obj.getDesignation() + "\n", catFont));
					preface.add(new Paragraph("Duration :" + obj.getDuration()
							+ "\n", catFont));
					for (String description : obj.getDescriptionArray()) {
						if (description != null && !("").equals(description)) {
							preface.add(new Paragraph("Description :"
									+ description + "\n", catFont));
						}
					}
				}
				document.add(preface);

			}
			logger.info("Exit addProfessionalBackground method of AdvisorRegistrationPDF");
		} catch (DocumentException e) {
			logger.error("addProfessionalBackground method of AdvisorRegistrationPDF threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("addProfessionalBackground method of CreateAdvisorFormPDF threw error:"
					+ e.getMessage());
			e.printStackTrace();
		}
	}

	private static void addServicesAndModes(Document document,
			List<AdvisorServiceDTO> services, List<AdvisorModeDTO> modes) {

		logger.info("Entered addServicesAndModes method of AdvisorRegistrationPDF");
		try {
			if (services.size() > 0 && modes.size() > 0) {
				document.newPage();
				Paragraph preface = new Paragraph();
				preface.add(new Paragraph("Services And Modes : \n", catFont));
				for (AdvisorServiceDTO service : services) {
					preface.add(new Paragraph("Service :"
							+ service.getService() + "\n", catFont));
					preface.add(new Paragraph("Description :"
							+ service.getDescription() + "\n", catFont));
					for (AdvisorModeDTO mode : modes) {
						if (service.getAdvisorId() == mode.getAdvisorId()) {
							preface.add(new Paragraph("Mode :"
									+ mode.getModeOfCommunication() + "\n",
									catFont));
							preface.add(new Paragraph("Price :"
									+ mode.getPrice() + "\n", catFont));

						}

					}

				}
				document.add(preface);

			}
			logger.info("Exit addServicesAndModes method of AdvisorRegistrationPDF");
		} catch (DocumentException e) {
			logger.error("addServicesAndModes method of AdvisorRegistrationPDF threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("addServicesAndModes method of AdvisorRegistrationPDF threw error:"
					+ e.getMessage());
			e.printStackTrace();
		}
	}

	private static void addImage(Document document,
			List<AdvisorProfileDTO> advisor) {

		logger.info("Entered addImage method of AdvisorRegistrationPDF");
		try {
			for (AdvisorProfileDTO advisorProfileDTO : advisor) {
				if (!advisorProfileDTO.getImage().isEmpty()) {
					document.open();
					// imageURL1 = imageURL.replaceAll('\', "\\");
					Image image2 = Image.getInstance(advisorProfileDTO
							.getImage());
					image2.setAbsolutePosition(300, 600);
					image2.scaleAbsolute(159f, 159f);
					document.add(image2);
				}
			}
			logger.info("Exit addImage method of AdvisorRegistrationPDF");
		} catch (DocumentException e) {
			logger.error("addImage method of AdvisorRegistrationPDF threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("addImage method of AdvisorRegistrationPDF threw error:"
					+ e.getMessage());
			e.printStackTrace();
		}
	}
}
