 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 22/12/2014
 * ************************************************************************************************/
package org.AC.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.SearchDAO;
import org.AC.Util.GetRelativeImageURL;
import org.AC.dto.AdvisorProfileDTO;
import org.AC.dto.AdvisorServiceDTO;
import org.AC.dto.ProfessionalBackgroundDTO;
import org.apache.log4j.Logger;

/********************************CLASS SUMMARY*****************************************************
* 
* This class will get all the advisor based upon services chosen from the navbar
* 
*
***************************************************************************************************/
/**
 * Servlet implementation class AdvisorsTabController
 */
@WebServlet("/AdvisorsTabController")
public class AdvisorsTabController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdvisorsTabController.class);     
	
 	/**************************************COMMENTS***************************************************
	 * This class will get all the advisor based upon services chosen from the navbar.
	 *   
	 *   @return :None
	 *   @param : HttpServletRequest request
	 *   		  HttpServletResponse response
	 *   		  
	 *
	 ***************************************************************************************************/

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered doGet method of AdvisorsTabController");
		String service = request.getParameter("service");
		List<Integer> advisorIds = new ArrayList<Integer>();
		List<AdvisorServiceDTO> advisorService = new ArrayList<AdvisorServiceDTO>();
		List<AdvisorProfileDTO> advisorProfile = new ArrayList<AdvisorProfileDTO>();
		if(("CVCritique").equals(service)){
			//Get the Details of all the advisor who are offering CVCritique as a service
			String services = "cvcritique";
			SearchDAO searchForCV= new SearchDAO();
			advisorService = searchForCV.getAdvisorUsingService(services);
			
		}else if (("MockInterview").equals(service)) {
			//Get the Details of all the advisor who are offering MockInterview as a service
			String services = "mockinterview";
			SearchDAO searchForCV= new SearchDAO();
			advisorService = searchForCV.getAdvisorUsingService(services);
		}else if (("PersonalWorkshop").equals(service)) {
			//Get the Details of all the advisor who are offering PersonalWorkshop as a service
			String services = "personalworkshops";
			SearchDAO searchForCV= new SearchDAO();
			advisorService = searchForCV.getAdvisorUsingService(services);		
		}else if (("CareerTalk").equals(service)) {
			//Get the Details of all the advisor who are offering CarrerTalk as a service
			String services = "careertalk";
			SearchDAO searchForCV= new SearchDAO();
			advisorService = searchForCV.getAdvisorUsingService(services);	
		}else{
			String services = "All";
			SearchDAO searchForCV= new SearchDAO();
			advisorService = searchForCV.getAdvisorUsingService(services);
		}
		
		for (AdvisorServiceDTO advisorServiceDTO : advisorService) {
			int counter =0;
			for (Integer id : advisorIds) {
				if(id == advisorServiceDTO.getAdvisorId()){
					counter++;
				}
			}
			if(counter == 0){
				advisorIds.add(advisorServiceDTO.getAdvisorId());
			}
		}
		//Getting advisor details using advisorId
		SearchDAO advisorInfo = new SearchDAO();
		advisorProfile = advisorInfo.getAdvisorDetails(advisorIds);
		
		String ids= "";
		if(advisorProfile.size() > 0){
			for (AdvisorProfileDTO advisorProfileDTO : advisorProfile) {
				advisorIds.add(advisorProfileDTO.getAdvisorId());
				ids = ids.concat( Integer.toString(advisorProfileDTO.getAdvisorId())+',');
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
		if(!ids.equals("")){
			ids = ids.substring(0, ids.lastIndexOf(','));
		}
		//Getting the name_of_organisation and the job title
		List<ProfessionalBackgroundDTO> profession = new ArrayList<ProfessionalBackgroundDTO>();
		SearchDAO company = new SearchDAO();
		profession = company.GetProfessionalDetails(advisorIds);
		/*for (ProfessionalBackgroundDTO professionalBackgroundDTO : profession) {
			if(professionalBackgroundDTO.getCompany().length() >= 19){
				professionalBackgroundDTO.setCompany(professionalBackgroundDTO.getCompany().substring(0, 18));
			}
			if(professionalBackgroundDTO.getDesignation().length() >= 19){
				professionalBackgroundDTO.setDesignation(professionalBackgroundDTO.getDesignation().substring(0, 18));
			}
		}*/
		
		// Sorting results for featured advisors
		Collections.sort(advisorProfile);
		
		//Getting the Services of the advisors
		List<AdvisorServiceDTO> services = new ArrayList<AdvisorServiceDTO>();
		SearchDAO advisorServices = new SearchDAO();
		services = advisorServices.getAdvisorServices(advisorIds);
		request.setAttribute("ids", ids);
		request.setAttribute("profession", profession);
		request.setAttribute("advisorProfile", advisorProfile);
		request.setAttribute("advisorService", services);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/AdvisorLandingPage.jsp");
        rd.forward(request, response);
		logger.info("Exit doGet method of AdvisorsTabController");
	}
}

