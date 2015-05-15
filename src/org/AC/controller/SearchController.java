/*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 30/11/2014
 * ************************************************************************************************/

package org.AC.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.AC.DAO.SearchDAO;
import org.AC.JDBC.Util;
import org.AC.Util.GetRelativeImageURL;
import org.AC.dto.AdvisorProfileDTO;
import org.AC.dto.AdvisorServiceDTO;
import org.AC.dto.ProfessionalBackgroundDTO;
import org.AC.dto.SearchDTO;
import org.apache.log4j.Logger;

/* *******************************CLASS SUMMARY****************************************************
 * 
 * This class will fetch the search keyword and search the key word in the "KEYWORD" COLUMN of advisor
 * details table .If there is a match then get the advisor details to show on the advisor landing page.
 * 
 *
 ***************************************************************************************************/
/**
 * Servlet implementation class SearchController
 */
@WebServlet(description = "Controller after the user searches for a particular advisor or skill", urlPatterns = { "/SearchController" })
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(SearchController.class);

	/**************************************
	 * COMMENTS*************************************************** This class
	 * will fetch the search keyword and search the key word in the "KEYWORD"
	 * COLUMN of advisor details table .If there is a match then get the advisor
	 * details to show on the advisor landing page.
	 * 
	 * @return :None
	 * @param : HttpServletRequest request HttpServletResponse response
	 * 
	 *
	 ***************************************************************************************************/
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("Enter doPost method of SearchController.java");
		String searchWord = request.getParameter("search");
		Connection conn = null;
		Statement stmt = null;
		String picture = "";
		List<AdvisorProfileDTO> advisorProfile = new ArrayList<AdvisorProfileDTO>();
		java.util.List<AdvisorServiceDTO> advisorService = new ArrayList<AdvisorServiceDTO>();
		java.util.List<SearchDTO> list1 = new ArrayList<SearchDTO>();
		List<Integer> advisorIds = new ArrayList<Integer>();
		Boolean isAddAdvisor = true;
		String ids = "";
		try {
			if (!searchWord.isEmpty()) {

				SearchDAO check = new SearchDAO();
				advisorProfile = check.checkKeyWord(searchWord);
				if (advisorProfile.size() > 0) {
					for (AdvisorProfileDTO advisorProfileDTO : advisorProfile) {
						advisorIds.add(advisorProfileDTO.getAdvisorId());
						ids = ids.concat(Integer.toString(advisorProfileDTO
								.getAdvisorId()) + ',');
						picture = advisorProfileDTO.getImage();
						if (!("").equals(picture)) {
							GetRelativeImageURL image = new GetRelativeImageURL();
							String relImage = image.getImageURL(picture);
							advisorProfileDTO.setImage(relImage);
						}
						if (advisorProfileDTO.getIntroduction().length() > 200) {
							advisorProfileDTO.setIntroduction(advisorProfileDTO
									.getIntroduction().substring(0, 200));
						}
					}
				}
				if (advisorIds.size() > 0) {
					SearchDAO service = new SearchDAO();
					advisorService = service.getAdvisorService(advisorIds);
				}
				if (!ids.equals("")) {
					ids = ids.substring(0, ids.lastIndexOf(','));
				}
				// Getting the name_of_organisation and the job title
				List<ProfessionalBackgroundDTO> profession = new ArrayList<ProfessionalBackgroundDTO>();
				SearchDAO company = new SearchDAO();
				profession = company.GetProfessionalDetails(advisorIds);
				/*
				 * for (ProfessionalBackgroundDTO professionalBackgroundDTO :
				 * profession) {
				 * if(professionalBackgroundDTO.getCompany().length() >= 19){
				 * professionalBackgroundDTO
				 * .setCompany(professionalBackgroundDTO
				 * .getCompany().substring(0, 18)); }
				 * if(professionalBackgroundDTO.getDesignation().length() >=
				 * 19){ professionalBackgroundDTO.setDesignation(
				 * professionalBackgroundDTO.getDesignation().substring(0, 18));
				 * } }
				 */
				List<String> industries = new ArrayList<String>();
				SearchDAO industry = new SearchDAO();
				industries = industry.GetIndustries();
				request.setAttribute("ids", ids);
				request.setAttribute("advisorProfile", advisorProfile);
				request.setAttribute("advisorService", advisorService);
				request.setAttribute("profession", profession);
				request.setAttribute("industries", industries);
				RequestDispatcher rd = getServletContext()
						.getRequestDispatcher("/AdvisorLandingPage.jsp");
				rd.forward(request, response);

				/*
				 * conn =Util.connect(); String query =
				 * "SELECT * FROM advisorskillview WHERE NAME LIKE ?  OR SERVICE LIKE ? OR KEY_SKILL LIKE  ? OR OCCUPATION LIKE ? OR RELEVANT_INDUSTRY LIKE ?"
				 * ; PreparedStatement pstmt = conn.prepareStatement(query);
				 * pstmt.setString(1,'%'+searchWord.toUpperCase()+'%');
				 * pstmt.setString(2,'%'+searchWord.toUpperCase()+'%');
				 * pstmt.setString(3,'%'+searchWord.toUpperCase()+'%');
				 * pstmt.setString(4,'%'+searchWord.toUpperCase()+'%');
				 * pstmt.setString(5,'%'+searchWord.toUpperCase()+'%');
				 * ResultSet results = pstmt.executeQuery();
				 * while(results.next()){ SearchDTO search = new SearchDTO();
				 * SearchDTO searchskill = new SearchDTO();
				 * searchskill.setAdvisorId(results.getInt("ADVISOR_ID"));
				 * search.setAdvisorId(results.getInt("ADVISOR_ID"));
				 * search.setId(results.getInt("ID"));
				 * search.setName(results.getString("NAME"));
				 * search.setOccupation(results.getString("OCCUPATION"));
				 * search.setWorkplace(results.getString("WORKPLACE"));
				 * search.setIntro(results.getString("INTRODUCTION"));
				 * search.setUg(results.getString("UG"));
				 * search.setPg(results.getString("PG"));
				 * search.setOthers(results.getString("OTHERS"));
				 * search.setWorkExperience
				 * (results.getString("WORK_EXPERIENCE"));
				 * search.setKeyskill(results.getString("KEY_SKILL"));
				 * search.setPicture(results.getString("PICTURE"));
				 * searchskill.setServices(results.getString("SERVICE"));
				 * search.
				 * setModeOfCommunication(results.getString("MODE_OF_COMMUNICATION"
				 * ));
				 * search.setRelevantIndustry(results.getString("RELEVANT_INDUSTRY"
				 * )); isAddAdvisor = true; for ( SearchDTO advisor : list ){
				 * if(advisor.getName().equals(search.getName()) ){ isAddAdvisor
				 * =false; } } if(isAddAdvisor){ list.add(search); }
				 * list1.add(searchskill); } if(list.size() > 0 && list1.size()
				 * > 0){ request.setAttribute("searchedadvisor", list);
				 * request.setAttribute("searchedadvisorskill", list1); }
				 * RequestDispatcher rd =
				 * getServletContext().getRequestDispatcher
				 * ("/AdvisorLanding.jsp"); rd.forward(request, response);
				 */
			}
			logger.info("Exit doPost method of SearchController.java");
		} catch (Exception e) {
			logger.error("Exception in doPost method of SearchController.java with following error: "
					+ e.getMessage());
			// Handle errors for Class.forName
			e.printStackTrace();
		}
	}
}
