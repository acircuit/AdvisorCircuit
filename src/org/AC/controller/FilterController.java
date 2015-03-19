 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 24/12/2014
 * ************************************************************************************************/
package org.AC.controller;

/* *******************************CLASS SUMMARY****************************************************
* 
* This class will fetch  the filter string from the advisorLanding page then retrieve the advisors
* 
* 
*
***************************************************************************************************/

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.FilterDAO;
import org.AC.DAO.SearchDAO;
import org.AC.Util.GetRelativeImageURL;
import org.AC.dto.AdvisorModeDTO;
import org.AC.dto.AdvisorProfileDTO;
import org.AC.dto.AdvisorServiceDTO;
import org.AC.dto.ProfessionalBackgroundDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;


/**
 * Servlet implementation class FilterController
 */
@WebServlet("/FilterController")
public class FilterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(FilterController.class);     

    
 	/**************************************COMMENTS***************************************************
	 * This method will fetch the filter string from the advisorLanding page then retrieve the advisors
	 *   
	 *   @return :None
	 *   @param : HttpServletRequest request
	 *   		  HttpServletResponse response
	 *   		  
	 *   
	 *
	 ***************************************************************************************************/

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doPost method of FilterController");
		String filter = request.getParameter("filter");
		String ids = request.getParameter("ids");
		List<AdvisorProfileDTO> advisors = new ArrayList<AdvisorProfileDTO>();
		List<AdvisorServiceDTO> advisorServices = new ArrayList<AdvisorServiceDTO>();
		List<ProfessionalBackgroundDTO> profession = new ArrayList<ProfessionalBackgroundDTO>();
		String query ="";
		List<Integer> advisorIds1 = new ArrayList<Integer>();
		java.util.List<AdvisorServiceDTO> advisorService = new ArrayList<AdvisorServiceDTO>();
		if (filter != null && !("").equals(ids)) {
			List<AdvisorProfileDTO> advisorProfile = new ArrayList<AdvisorProfileDTO>();
			String[] filters = filter.split(":");
			String[] advisorIds = ids.split(",");
			//Getting advisor profile details
			FilterDAO advisor = new FilterDAO();
			advisorProfile = advisor.getAdvisorDetailsUsingAdvisorId(advisorIds);
			if(advisorProfile.size() > 0){
				for (AdvisorProfileDTO advisorProfileDTO : advisorProfile) {
					advisorIds1.add(advisorProfileDTO.getAdvisorId());
			}	
			if(advisorIds1.size() > 0){
				SearchDAO service = new SearchDAO();
				advisorService = service.getAdvisorService(advisorIds1);
			}
			
			//Getting advisorModes
			List<AdvisorModeDTO> advisorMode = new ArrayList<AdvisorModeDTO>();
			FilterDAO advisormode = new FilterDAO();
			advisorMode = advisormode.getAdvisorMode(advisorIds1);
			int counter = 0;
			for (AdvisorProfileDTO profile : advisorProfile) {
				int stringCounter = 0;
				int serviceCounter = 0;
				int modeCounter = 0;
				int industryCounter=0;
				int experienceCounter=0;
				int serCounter =0;
				int moCounter =0;
				int indusCounter =0;
				int exCounter =0;
				for (String string : filters) {
					if(string.indexOf("Industry") != -1){
						indusCounter++;
						String industryType = string.replaceFirst("Industry", "");
						if(("Teaching").equals(industryType) && ("Teaching").equalsIgnoreCase(profile.getIndustry())){
							counter++;
							industryCounter++;
						}else if (("Architecture").equals(industryType) && ("Architecture").equalsIgnoreCase(profile.getIndustry())) {
							counter++;
							industryCounter++;
						}else if (("TV").equals(industryType) && ("TV, Media & Film").equalsIgnoreCase(profile.getIndustry())) {
							counter++;
							industryCounter++;
						}else if (("Law").equals(industryType) && ("Law").equalsIgnoreCase(profile.getIndustry())) {
							counter++;
							industryCounter++;
						}else if (("ManagementConsulting").equals(industryType) && ("Management Consulting").equalsIgnoreCase(profile.getIndustry())) {
							counter++;
							industryCounter++;
						}else if (("Medicine").equals(industryType) && ("Medicine").equalsIgnoreCase(profile.getIndustry())) {
							counter++;
							industryCounter++;
						}else if (("LanguageLearning").equals(industryType) && ("Language Learning").equalsIgnoreCase(profile.getIndustry())) {
							counter++;
							industryCounter++;
						}						
					}else if (string.indexOf("Service") != -1) {
						String serviceType = string.replaceFirst("Service", "");
						serCounter++;
						for (AdvisorServiceDTO advisorServiceDTO : advisorService) {
							if(advisorServiceDTO.getAdvisorId() == profile.getAdvisorId()){
								if(("careertalk").equals(serviceType) && ("careertalk").equals(advisorServiceDTO.getService())){
									counter++;
									serviceCounter++;
								}else if (("cvcritique").equals(serviceType) && ("cvcritique").equals(advisorServiceDTO.getService())) {
									counter++;
									serviceCounter++;
								}else if (("mockinterview").equals(serviceType) && ("mockinterview").equals(advisorServiceDTO.getService())) {
									counter++;
									serviceCounter++;
								}else if (("personalworkshops").equals(serviceType) && ("personalworkshops").equals(advisorServiceDTO.getService())) {
									counter++;
									serviceCounter++;
								}
							}
						}
						
					}else if (string.indexOf("Experience") != -1) {
						exCounter++;
						String experienceYears = string.replaceFirst("Experience", "");
						if(("5").equals(experienceYears) &&  Integer.parseInt(profile.getExperience()) >= 0 && Integer.parseInt(profile.getExperience()) <  5){
							counter++;
							experienceCounter++;
						}else if (("10").equals(experienceYears) &&  Integer.parseInt(profile.getExperience()) >= 5 && Integer.parseInt(profile.getExperience()) < 10){
							counter++;
							experienceCounter++;
						}else if (("15").equals(experienceYears) && Integer.parseInt(profile.getExperience()) >= 10 && Integer.parseInt(profile.getExperience()) < 100) {
							counter++;
							experienceCounter++;
						}
					}else if (string.indexOf("Mode") != -1) {
						    moCounter++;
							String modeType = string.replaceFirst("Mode", "");
							for (AdvisorModeDTO advisorModeDTO : advisorMode) {
								if(advisorModeDTO.getAdvisorId() == profile.getAdvisorId()){
									if(("phone").equals(modeType) && ("phone").equals(advisorModeDTO.getModeOfCommunication())){
										counter++;
										modeCounter++;
									}else if (("email").equals(modeType) && ("email").equals(advisorModeDTO.getModeOfCommunication())) {
										counter++;
										modeCounter++;
									}else if (("webchat").equals(modeType) && ("webchat").equals(advisorModeDTO.getModeOfCommunication())) {
										counter++;
										modeCounter++;
									}
								}
							}
						}
					}
					Boolean isService = true;
					Boolean isMode = true;
					Boolean isExperience = true;
					Boolean isIndustry = true;
					if(serCounter > 0 && serviceCounter > 0){
						stringCounter++;
						counter = counter-serviceCounter+1;
					}else if (serCounter > 0 && serviceCounter == 0) {
						isService = false;
					}
					
					if(moCounter > 0 && modeCounter > 0){
						stringCounter++;
						counter = counter-modeCounter+1;
					}else if (moCounter > 0 && modeCounter == 0) {
						isMode = false;
					}
					
					if(indusCounter > 0 && industryCounter > 0){
						stringCounter++;
						counter = counter-industryCounter+1;
					}else if (indusCounter > 0 && industryCounter == 0) {
						isIndustry = false;
					}
					
					
					if(exCounter > 0 && experienceCounter > 0){
						stringCounter++;
						counter = counter-experienceCounter+1;
					}else if (exCounter > 0 && experienceCounter == 0) {
						isExperience = false;
					}				
					if(counter == stringCounter && stringCounter != 0 && isService && isMode && isIndustry && isExperience){
						advisors.add(profile);
					}
					counter =0;

				}
			List<Integer> advisorId = new ArrayList<Integer>();
			if(advisors.size() > 0){
				for (AdvisorProfileDTO advisorProfileDTO : advisors) {
					advisorId.add(advisorProfileDTO.getAdvisorId());
					String picture = advisorProfileDTO.getImage();
					if(!("").equals(picture)){
						GetRelativeImageURL image = new GetRelativeImageURL();
						String relImage = image.getImageURL(picture);
						advisorProfileDTO.setImage(relImage);
					}
					if(advisorProfileDTO.getIntroduction().length() > 200){
						advisorProfileDTO.setIntroduction(advisorProfileDTO.getIntroduction().substring(0, 200));
					}
				}
			}
			if(advisorId.size() > 0){
				//Fetching the service details of the advisors
				SearchDAO service = new SearchDAO();
				advisorService = service.getAdvisorService(advisorId);
				//Getting the name_of_organisation and the job title
				SearchDAO company = new SearchDAO();
				profession = company.GetProfessionalDetails(advisorId);
			}
			
			
		}
		
		//HTML RESPONSE:
				String data = "";
				int counter = 0;
				List<AdvisorProfileDTO> advisorDetail = new ArrayList<AdvisorProfileDTO>();
				//removing duplicates
//				for (AdvisorProfileDTO advisorDetails : advisors) {
//					if(!advisorDetail.contains(advisorDetails)){
//						advisorDetail.add(advisorDetails);
//					}
//				}
				String id = ":::";
				for (AdvisorProfileDTO advisorDetails : advisors) {
					counter++;
					id=id.concat(Integer.toString(advisorDetails.getAdvisorId())+",");
					if(counter == 1){
						data= data.concat("<li>");
					}
					String str ="<div class='col-md-6' id='advisor'><div class='pattern clearfix'><div class='row'>"
								+"<div class=col-md-6 col-xs-6 user-pic'><a href='AdvisorProfile?aId="+advisorDetails.getAdvisorId()+"'><img src='"+advisorDetails.getImage()+"' alt='' height='165'></a></div>"
								+"<div class='col-md-6 col-xs-6 user-detail'><div class='prof'><h4 class='name'><a href='AdvisorProfile?aId="+advisorDetails.getAdvisorId()+"'>"+advisorDetails.getName()+"</a></h4>"
								+"<h4 class='industry'><a href='AdvisorProfile?aId="+advisorDetails.getAdvisorId()+"'>"+advisorDetails.getIndustry()+"</a></h4>";
				
					data = data.concat(str);
					Boolean prof = true;
					String str1="";
					for (ProfessionalBackgroundDTO advisorProf : profession) {
						if(advisorProf.getAdvisorId() == advisorDetails.getAdvisorId() && prof){
							str1="<p><a href='AdvisorProfile?aId="+advisorDetails.getAdvisorId()+"'>"+advisorProf.getDesignation()+"</a><br>"
								+"<a href='AdvisorProfile?aId="+advisorDetails.getAdvisorId()+"'>"+advisorProf.getCompany()+"</a></p>";
							prof=false;
						}
					}
					data = data.concat(str1);
					data = data.concat("</div></div></div>");
					String intro = "<div class='row'><div class='col-md-12 desc'>"
									+"<p><a href='AdvisorProfile?aId="+advisorDetails.getAdvisorId()+"'>"+advisorDetails.getIntroduction()+"...</a></p></div>";
					
					data = data.concat(intro);
					data = data.concat("<a href='AdvisorProfile?aId="+advisorDetails.getAdvisorId()+"'><div class='col-md-12 brief'><div class='col-md-9 col-xs-8'>");
					String service="";
					for (AdvisorServiceDTO advService : advisorService) {
						if(advService.getAdvisorId() == advisorDetails.getAdvisorId()){
							if(advService.getService().equals("careertalk")){
								service = "<div class='col-md-3 col-xs-3 text-center' id='image'>"
										+"<img src='assets/img/WebMail/HomePage/Panel_2_Icon_1.png' width='45' alt=''>"
										+"<p>Career Talk</p></div>";
							}else if(advService.getService().equals("mockinterview")){
								service = "<div class='col-md-3 col-xs-3 text-center' id='image'>"
										+"<img src='assets/img/WebMail/HomePage/Panel_2_Icon_2.png' width='45' alt=''>"
										+"<p>Mock Interview</p></div>";
							}else if(advService.getService().equals("cvcritique")){
								service = "<div class='col-md-3 col-xs-3 text-center' id='image'>"
										+"<img src='assets/img/WebMail/HomePage/Panel_2_Icon_3.png' width='45' alt=''>"
										+"<p>Resume Critique</p></div>";
							}else if(advService.getService().equals("personalworkshops")){
								service = "<div class='col-md-3 col-xs-3 text-center' id='image'>"
										+"<img src='assets/img/WebMail/HomePage/Panel_2_Icon_4.png' width='45' alt=''>"
										+"<p>Personal Workshop</p></div>";
							}
							
						}
						data = data.concat(service);
						service="";
					}
					data = data.concat("</div>");
					
					String review = "<div class='review text-center pull-right' id='image'>"
									+"<a id='"+advisorDetails.getAdvisorId()+"' href='#' onclick='GetAdvisorReview(this)' data-toggle='modal' data-target='#datepay'>"
									+"<img src='assets/img/services/Icon_Reviews.png' style='width:45px;' alt=''><p>Reviews</p></a></div></div></a></div></div></div>";
					data=data.concat(review);
					if(counter == 6){
						data=data.concat("</li>");
						counter =0;
					}
				}
				data =data.concat(id);
				response.getWriter().write(data);
		logger.info("Exit doPost method of FilterController");
	}
	}
}
