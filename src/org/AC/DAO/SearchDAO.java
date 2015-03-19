 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 30/11/2014
 * ************************************************************************************************/
package org.AC.DAO;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.AC.JDBC.ConnectionFactory;
import org.AC.JDBC.Util;
import org.AC.dto.AdvisorProfileDTO;
import org.AC.dto.AdvisorServiceDTO;
import org.AC.dto.ProfessionalBackgroundDTO;
import org.AC.dto.SearchDTO;
import org.AC.dto.UserDetailsDTO;
import org.AC.dto.UserRequestDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.tomcat.jni.OS;

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
		
		
		logger.info("Entered checkKeyWord method of SearchDAO");
		List<AdvisorProfileDTO> list = new ArrayList<AdvisorProfileDTO>();
		if( keywords != null && !("").equals(keywords)){
			String query ="SELECT ADVISOR_ID,NAME,INDUSTRY,INTRODUCTION,IMAGE,RATING FROM advisordetails WHERE KEYWORDS LIKE ? AND ISACTIVE=?";
			PreparedStatement pstmt;
			try {
				conn =ConnectionFactory.getConnection();
				conn.setAutoCommit(false);
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, '%'+ keywords.toUpperCase() +'%');
				pstmt.setBoolean(2, true);
			    ResultSet results = pstmt.executeQuery();
			    while(results.next()){
			    	conn.commit();
			    	AdvisorProfileDTO profile =  new AdvisorProfileDTO();
			    	profile.setAdvisorId(results.getInt("ADVISOR_ID"));
			    	profile.setName(results.getString("NAME"));
			    	profile.setIndustry(results.getString("INDUSTRY"));
			    	profile.setIntroduction(results.getString("INTRODUCTION"));
			    	profile.setImage(results.getString("IMAGE"));
			    	profile.setRatings(results.getInt("RATING"));
			    	list.add(profile);
			    }
			} catch (SQLException e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					logger.error("checkKeyWord method of SearchDAO threw error:"+e1.getMessage());
					e1.printStackTrace();
				}
				logger.error("checkKeyWord method of SearchDAO threw error:"+e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				logger.error("checkKeyWord method of SearchDAO threw error:"+e.getMessage());
				e.printStackTrace();
			} catch (PropertyVetoException e) {
				logger.error("checkKeyWord method of SearchDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("checkKeyWord method of SearchDAO threw error:"+e.getMessage());
					e.printStackTrace();
				}
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
	
	public List<AdvisorServiceDTO> getAdvisorService( List<Integer> advisorId){
		
		
		logger.info("Entered getAdvisorService method of SearchDAO");
		List<AdvisorServiceDTO> list = new ArrayList<AdvisorServiceDTO>();
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String q4in = generateQsForIn(advisorId.size());			
			String query ="SELECT ADVISOR_ID,SERVICE FROM advisorservices WHERE ADVISOR_ID IN ( " + q4in + " )";
			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(query);
			int i = 1;
			  for (Integer item : advisorId) {
				  pstmt.setInt(i++, item);
			  }
		    ResultSet results = pstmt.executeQuery();
		    while(results.next()){
		    	AdvisorServiceDTO advisorService = new AdvisorServiceDTO();
		    	advisorService.setAdvisorId(results.getInt("ADVISOR_ID"));
		    	advisorService.setService(results.getString("SERVICE"));
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
		} catch (IOException e) {
			logger.error("getAdvisorService method of SearchDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("getAdvisorService method of SearchDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("getAdvisorService method of SearchDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}	

		
		logger.info("Exit getUserDetails method of SearchDAO");
		return list;
	}
	
	/**************************************COMMENTS***************************************************
	 * This method will get the advisor Id  who are offering the service
	 *   @return :String service
	 *   @param : List<AdvisorProfileDTO> advisor
	 *   		  
	 *
	 ***************************************************************************************************/	
	
	public List<AdvisorServiceDTO> getAdvisorUsingService( String service){		
		//
		logger.info("Entered getAdvisorUsingService method of SearchDAO");
		List<AdvisorServiceDTO> advisorService = new ArrayList<AdvisorServiceDTO>();
		try {
			PreparedStatement pstmt;
			conn =ConnectionFactory.getConnection();  
			conn.setAutoCommit(false);
			if(!("All").equals(service)){
				String query ="SELECT ADVISOR_ID,SERVICE FROM advisorservices WHERE SERVICE= ?";
				pstmt = conn.prepareStatement(query);
			    pstmt.setString(1, service);
			}else{
				String query ="SELECT ADVISOR_ID,SERVICE FROM advisorservices ";
				pstmt = conn.prepareStatement(query);
			}
		    ResultSet results = pstmt.executeQuery();
		    while(results.next()){
		    	AdvisorServiceDTO advisor = new AdvisorServiceDTO();
		    	advisor.setAdvisorId(results.getInt("ADVISOR_ID"));
		    	advisor.setService(results.getString("SERVICE"));
		    	advisorService.add(advisor);
		    }
		    conn.commit();
		logger.info("Exit getAdvisorUsingService method of SearchDAO");
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("getAdvisorUsingService method of SearchDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}
			logger.error("getAdvisorUsingService method of SearchDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("getAdvisorUsingService method of SearchDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("getAdvisorUsingService method of SearchDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("getAdvisorUsingService method of SearchDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}	

		
		logger.info("Exit getAdvisorUsingService method of SearchDAO");
		return advisorService;
	}
	
	/**************************************COMMENTS***************************************************
	 * This method will get the advisor details of the advisor present in advisorId list.
	 *   @return :List<AdvisorProfileDTO> list
	 *   @param : List<Integer> advisorId
	 *   		  
	 *
	 ***************************************************************************************************/	
	
	public List<AdvisorProfileDTO> getAdvisorDetails( List<Integer> advisorId){
		
		
		logger.info("Entered getAdvisorDetails method of SearchDAO");
		List<AdvisorProfileDTO> list = new ArrayList<AdvisorProfileDTO>();
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			if(advisorId.size() >0){
				String q4in = generateQsForIn(advisorId.size());			
				String query ="SELECT ADVISOR_ID,NAME,INDUSTRY,INTRODUCTION,IMAGE,RATING FROM advisordetails WHERE ADVISOR_ID IN ( " + q4in + " ) AND ISACTIVE=?";
				PreparedStatement pstmt;
				pstmt = conn.prepareStatement(query);
				int i = 1;
				  for (Integer item : advisorId) {
					  pstmt.setInt(i++, item);
				  }
				  pstmt.setBoolean(i++, true);
			    ResultSet results = pstmt.executeQuery();
			    while(results.next()){
			    	AdvisorProfileDTO advisor = new AdvisorProfileDTO();
			    	advisor.setAdvisorId(results.getInt("ADVISOR_ID"));
			    	advisor.setName(results.getString("NAME"));
			    	advisor.setIndustry(results.getString("INDUSTRY"));
			    	advisor.setIntroduction(results.getString("INTRODUCTION"));
			    	advisor.setImage(results.getString("IMAGE"));
			    	advisor.setRatings(results.getInt("RATING"));
			    	list.add(advisor);
			    }
			    conn.commit();
			}
		logger.info("Exit getAdvisorDetails method of UserDetailsDAO");
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("getAdvisorDetails method of SearchDAO threw error:"+e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("getAdvisorDetails method of SearchDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("getAdvisorDetails method of SearchDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("getAdvisorDetails method of SearchDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("getAdvisorDetails method of SearchDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}	

		
		logger.info("Exit getAdvisorDetails method of SearchDAO");
		return list;
	}
	
	/**************************************COMMENTS***************************************************
	 * This method will get the advisor details of the advisor present in advisorId list.
	 *   @return :List<AdvisorServiceDTO> list
	 *   @param : List<Integer> advisorId
	 *   		  
	 *
	 ***************************************************************************************************/	
	
	public List<AdvisorServiceDTO> getAdvisorServices( List<Integer> advisorId){
		
		
		logger.info("Entered getAdvisorServices method of SearchDAO");
		List<AdvisorServiceDTO> list = new ArrayList<AdvisorServiceDTO>();
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			if(advisorId.size() > 0){
				String q4in = generateQsForIn(advisorId.size());			
				String query ="SELECT ADVISOR_ID,SERVICE FROM advisorservices WHERE ADVISOR_ID IN ( " + q4in + " ) ";
				PreparedStatement pstmt;
				pstmt = conn.prepareStatement(query);
				int i = 1;
				for (Integer item : advisorId) {
					  pstmt.setInt(i++, item);
				  }
			    ResultSet results = pstmt.executeQuery();
			    while(results.next()){
			    	AdvisorServiceDTO service = new AdvisorServiceDTO();
			    	service.setAdvisorId(results.getInt("ADVISOR_ID"));
			    	service.setService(results.getString("SERVICE"));
			    	list.add(service);
			    }
			    conn.commit();
			}
		logger.info("Exit getAdvisorServices method of UserDetailsDAO");
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("getAdvisorServices method of SearchDAO threw error:"+e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("getAdvisorServices method of SearchDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("getAdvisorServices method of SearchDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("getAdvisorServices method of SearchDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("getAdvisorServices method of SearchDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}	

		
		logger.info("Exit getAdvisorServices method of SearchDAO");
		return list;
	}
	
	public List<ProfessionalBackgroundDTO> GetProfessionalDetails(List<Integer> advisorIds){
		logger.info("Entered GetProfessionalDetails method of SearchDAO");
		List<ProfessionalBackgroundDTO> list = new ArrayList<ProfessionalBackgroundDTO>();
			PreparedStatement pstmt;
			try {
					conn =ConnectionFactory.getConnection();
					conn.setAutoCommit(false);
					if(advisorIds.size() >0){
					String q4in = generateQsForIn(advisorIds.size());			
					String query ="SELECT COMPANY,DESIGNATION,ADVISOR_ID FROM advisorprofessionalbackground WHERE ADVISOR_ID IN ( " + q4in + " )";
					pstmt = conn.prepareStatement(query);
					int i = 1;
					  for (Integer item : advisorIds) {
						  pstmt.setInt(i++, item);
					  }
				    ResultSet results = pstmt.executeQuery();
				    while(results.next()){
				    	conn.commit();
				    	ProfessionalBackgroundDTO profile =  new ProfessionalBackgroundDTO();
				    	profile.setAdvisorId(results.getInt("ADVISOR_ID"));
				    	profile.setCompany(results.getString("COMPANY"));
				    	profile.setDesignation(results.getString("DESIGNATION"));
				    	list.add(profile);
				    }
				}
			} catch (SQLException e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					logger.error("GetProfessionalDetails method of SearchDAO threw error:"+e1.getMessage());
					e1.printStackTrace();
				}
				logger.error("GetProfessionalDetails method of SearchDAO threw error:"+e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				logger.error("GetProfessionalDetails method of SearchDAO threw error:"+e.getMessage());
				e.printStackTrace();
			} catch (PropertyVetoException e) {
				logger.error("GetProfessionalDetails method of SearchDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("GetProfessionalDetails method of SearchDAO threw error:"+e.getMessage());
					e.printStackTrace();
				}
			}	
			logger.info("Exit GetProfessionalDetails method of SearchDAO");
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
