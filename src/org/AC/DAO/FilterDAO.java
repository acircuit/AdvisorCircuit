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
import org.AC.dto.AdvisorModeDTO;
import org.AC.dto.AdvisorProfileDTO;
import org.AC.dto.AdvisorServiceDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class FilterDAO {
	Connection conn = null;
	Statement stmt = null;
	private static final Logger logger = Logger.getLogger(FilterDAO.class);
	
	public List<AdvisorProfileDTO> GetAdvisors(String query , List<String> parameters){
		
		logger.info("Entered GetAdvisors method of FilterDAO");
		PreparedStatement pstmt;
		List<AdvisorProfileDTO> advisors = new ArrayList<AdvisorProfileDTO>();
		try{
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String queryString = query;
			pstmt = conn.prepareStatement(queryString);
			int i = 1;
			 for (String str : parameters) {
				 if(("5").equals(str) ){
					 pstmt.setInt(i++, 0);
					 pstmt.setInt(i++, 5);
				 }else if (("10").equals(str)) {
					 pstmt.setInt(i++, 5);
					 pstmt.setInt(i++, 10);
				}else if (("15").equals(str)) {
					pstmt.setInt(i++, 10);
					pstmt.setInt(i++, 100);
				}else{
					  pstmt.setString(i++,str);

				 }
			 }		
		    ResultSet results = pstmt.executeQuery();
		    System.out.println(results.getFetchSize());
		    while(results.next()){
		    	AdvisorProfileDTO advisor = new AdvisorProfileDTO();
		    	advisor.setAdvisorId(results.getInt("ADVISOR_ID"));
		    	advisor.setName(results.getString("NAME"));
		    	advisor.setIndustry(results.getString("INDUSTRY"));
		    	advisor.setNameOfOrganisation(results.getString("NAME_OF_ORGANISATION"));
		    	advisor.setJobTitle(results.getString("JOB_TITLE"));
		    	advisor.setIntroduction(results.getString("INTRODUCTION"));
		    	advisor.setImage(results.getString("IMAGE"));
		    	advisor.setRatings(results.getInt("RATING"));
		    	advisors.add(advisor);
		    }
		}catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("GetAdvisors method of FilterDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}
			logger.error("GetAdvisors method of FilterDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("GetAdvisors method of FilterDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("GetAdvisors method of FilterDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetAdvisors FilterDAO of FilterDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}	
		logger.info("Entered GetAdvisors FilterDAO of FilterDAO");
		return advisors;
	}
	
	public List<AdvisorServiceDTO> GetServices(List<Integer> advisorIds){
		
		logger.info("Entered GetServices method of FilterDAO");
		PreparedStatement pstmt;
		List<AdvisorServiceDTO> advisorServices = new ArrayList<AdvisorServiceDTO>();
		try{
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String q4in = generateQsForIn(advisorIds.size());			
			String query ="SELECT * FROM services WHERE ADVISOR_ID IN ( " + q4in + " )";
			pstmt = conn.prepareStatement(query);
			int i = 1;
			  for (Integer item : advisorIds) {
				  pstmt.setInt(i++, item);
			  }
		    ResultSet results = pstmt.executeQuery();
		    while(results.next()){
		    	AdvisorServiceDTO services = new AdvisorServiceDTO();
		    	services.setAdvisorId(results.getInt("ADVISOR_ID"));
		    	services.setService(results.getString("SERVICE"));
		    	advisorServices.add(services);
		    }
		}catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("GetServices method of FilterDAO threw error:"+e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("GetServices method of FilterDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("GetServices method of FilterDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("GetServices method of FilterDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetServices method of FilterDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}	
		logger.info("Entered GetServices method of FilterDAO");
		return advisorServices;
	}
	
	public List<AdvisorProfileDTO> getAdvisorDetailsUsingAdvisorId( String[] advisorId){
		
		
		logger.info("Entered getAdvisorDetailsUsingAdvisorId method of FilterDAO");
		List<AdvisorProfileDTO> list = new ArrayList<AdvisorProfileDTO>();
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String q4in = generateQsForIn(advisorId.length);			
			String query ="SELECT * FROM advisordetails WHERE ADVISOR_ID IN ( " + q4in + " )";
			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(query);
			int i = 1;
			  for (String item : advisorId) {
				  pstmt.setString(i++, item);
			  }
		    ResultSet results = pstmt.executeQuery();
		    while(results.next()){
		    	AdvisorProfileDTO profile =  new AdvisorProfileDTO();
		    	profile.setAdvisorId(results.getInt("ADVISOR_ID"));
		    	profile.setName(results.getString("NAME"));
		    	profile.setIndustry(results.getString("INDUSTRY"));
		    	profile.setIntroduction(results.getString("INTRODUCTION"));
		    	profile.setImage(results.getString("IMAGE"));
		    	profile.setRatings(results.getInt("RATING")); 	
		    	profile.setExperience(results.getString("EXPERIENCE"));
		    	list.add(profile);
		    }
		    conn.commit();
		logger.info("Exit getAdvisorDetailsUsingAdvisorId method of FilterDAO");
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("getAdvisorDetailsUsingAdvisorId method of FilterDAO threw error:"+e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("getAdvisorDetailsUsingAdvisorId method of FilterDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("getAdvisorDetailsUsingAdvisorId method of FilterDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("getAdvisorDetailsUsingAdvisorId method of FilterDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("getAdvisorDetailsUsingAdvisorId method of FilterDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}

		
		logger.info("Exit getUserDetailsUsingAdvisorId method of FilterDAO");
		return list;
	}
	
	public List<AdvisorServiceDTO> GetServices(String[] advisorIds){
		
		logger.info("Entered GetServices method of FilterDAO");
		PreparedStatement pstmt;
		List<AdvisorServiceDTO> advisorServices = new ArrayList<AdvisorServiceDTO>();
		try{
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String q4in = generateQsForIn(advisorIds.length);			
			String query ="SELECT * FROM services WHERE ADVISOR_ID IN ( " + q4in + " )";
			pstmt = conn.prepareStatement(query);
			int i = 1;
			  for (String item : advisorIds) {
				  pstmt.setString(i++, item);
			  }
		    ResultSet results = pstmt.executeQuery();
		    while(results.next()){
		    	AdvisorServiceDTO services = new AdvisorServiceDTO();
		    	services.setAdvisorId(results.getInt("ADVISOR_ID"));
		    	services.setService(results.getString("SERVICE"));
		    	advisorServices.add(services);
		    }
		}catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("GetServices method of FilterDAO threw error:"+e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("GetServices method of FilterDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("GetServices method of FilterDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("GetServices method of FilterDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetServices method of FilterDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}	
		logger.info("Entered GetServices method of FilterDAO");
		return advisorServices;
	}
	
	public List<AdvisorModeDTO> getAdvisorMode(List<Integer>  advisorId){
		
		
		logger.info("Entered getAdvisorMode method of FilterDAO");
		List<AdvisorModeDTO> list = new ArrayList<AdvisorModeDTO>();
		
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String q4in = generateQsForIn(advisorId.size());			
			String query ="SELECT * FROM advisormodes WHERE ADVISOR_ID IN ( " + q4in + " )";
			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(query);
			int i = 1;
			  for (Integer item : advisorId) {
				  pstmt.setInt(i++, item);
			  }
		    ResultSet results = pstmt.executeQuery();
		    while(results.next()){
		    	AdvisorModeDTO advisor = new AdvisorModeDTO();
		    	advisor.setAdvisorId(results.getInt("ADVISOR_ID"));
		    	advisor.setModeOfCommunication(results.getString("MODE_OF_COMMUNICATION"));	
		    	list.add(advisor);
		    }
		    conn.commit();
		logger.info("Exit getAdvisorMode method of FilterDAO");
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("getAdvisorMode method of FilterDAO threw error:"+e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("getAdvisorMode method of FilterDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("getAdvisorMode method of FilterDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("getAdvisorMode method of FilterDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("getAdvisorMode method of FilterDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}		
		logger.info("Exit getAdvisorMode method of FilterDAO");
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
