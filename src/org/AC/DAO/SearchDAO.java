 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 30/11/2014
 * ************************************************************************************************/
package org.AC.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.AC.JDBC.Util;
import org.AC.controller.AdvisorMyAccountRequestController;
import org.AC.dto.AdvisorProfileDTO;
import org.AC.dto.SearchDTO;
import org.AC.dto.UserDetailsDTO;
import org.AC.dto.UserRequestDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/* *******************************CLASS SUMMARY****************************************************
* 
* This class will make all the DB Calls related to search.
* 
*
***************************************************************************************************/

public class SearchDAO {
	private static final Logger logger = Logger.getLogger(SearchDAO.class);     
	Connection conn = null;
	Statement stmt = null;
	
 	/**************************************COMMENTS***************************************************
	 * This method will check the keyword in "KEYWORD" column and get the advisor details
	 *   @return :List<AdvisorProfileDTO> list
	 *   @param : String Keywords
	 *   		  
	 *
	 ***************************************************************************************************/
	
	public List<AdvisorProfileDTO> checkKeyWord(String keywords){
		
		BasicConfigurator.configure();
		logger.info("Entered checkKeyWord method of SearchDAO");
		List<AdvisorProfileDTO> list = new ArrayList<AdvisorProfileDTO>();
		if( keywords != null && !("").equals(keywords)){
			conn =Util.connect();
			String query ="SELECT ADVISOR_ID,NAME,INDUSTRY,NAME_OF_ORGANISATION,JOB_TITLE,IMAGE FROM advisordetails WHERE KEYWORDS LIKE ? AND ISACTIVE=?";
			PreparedStatement pstmt;
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, '%' + keywords.toUpperCase() +'%');
				pstmt.setBoolean(2, true);
			    ResultSet results = pstmt.executeQuery();
			    if(results.next()){
			    	conn.commit();
			    	AdvisorProfileDTO profile =  new AdvisorProfileDTO();
			    	profile.setAdvisorId(results.getInt("ADVISOR_ID"));
			    	profile.setName(results.getString("NAME"));
			    	profile.setIndustry("INDUsTRY");
			    	profile.setNameOfOrganisation(results.getString("NAME_OF_ORGANISATION"));
			    	profile.setJobTitle(results.getString("JOB_TITLE"));
			    	profile.setImage(results.getString("IMAGE"));
			    	list.add(profile);
			    }
			} catch (SQLException e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					logger.error("checkKeyWord method of SearchDAO threw error:"+e.getMessage());
					e1.printStackTrace();
				}
				logger.error("checkKeyWord method of SearchDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}

		}
			logger.info("Exit checkKeyWord method of SearchDAO");
			return list;
	}
	
 	/**************************************COMMENTS***************************************************
	 * This method will get the service details of the advisor present in advisorId list.
	 *   @return :List<Integer> advisorId
	 *   @param : String Keywords
	 *   		  
	 *
	 ***************************************************************************************************/
	
	
	
	public List<SearchDTO> getAdvisorService( List<Integer> advisorId){
		
		BasicConfigurator.configure();
		logger.info("Entered getAdvisorService method of SearchDAO");
		List<SearchDTO> list = new ArrayList<SearchDTO>();
		conn =Util.connect();
		try {
			String q4in = generateQsForIn(advisorId.size());			
			String query ="SELECT ADVISOR_ID,SERVICE FROM services WHERE ADVISOR_ID IN ( " + q4in + " )";
			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(query);
			int i = 1;
			  for (Integer item : advisorId) {
				  pstmt.setInt(i++, item);
			  }
		    ResultSet results = pstmt.executeQuery();
		    while(results.next()){
		    	SearchDTO advisorService = new SearchDTO();
		    	advisorService.setAdvisorId(results.getInt("ADVISOR_ID"));
		    	advisorService.setServices(results.getString("SERVICE"));
		    	list.add(advisorService);
		    }
		    conn.commit();
		logger.info("Exit getAdvisorService method of UserDetailsDAO");
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("getAdvisorService method of SearchDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}
			logger.error("getAdvisorService method of SearchDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}

		
		logger.info("Exit getUserDetails method of SearchDAO");
		return list;
	}
	
	private String generateQsForIn(int numQs) {
	    String items = "";
	    for (int i = 0; i < numQs; i++) {
	        if (i != 0) items += ", ";
	        items += "?";
	    }
	    return items;
	}
}
