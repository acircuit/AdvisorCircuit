package org.AC.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.AC.JDBC.Util;
import org.AC.Util.Utility;
import org.AC.dto.AdvisorModeDTO;
import org.AC.dto.AdvisorProfileDTO;
import org.AC.dto.AdvisorServiceDTO;
import org.AC.dto.ProfessionalBackgroundDTO;
import org.apache.log4j.Logger;

public class AdvisorPDFDetailsDAO {
	private static final Logger logger = Logger.getLogger(AdvisorPDFDetailsDAO.class);
	Connection conn = null;
	Statement stmt = null;
	
	public List<AdvisorProfileDTO> getAdvisorProfileDetails( String email){

		java.util.List<AdvisorProfileDTO> list = new ArrayList<AdvisorProfileDTO>();
		logger.info("Entered getAdvisorProfileDetails method of AdvisorPDFDetailsDAO");
		try{
			if(!email.isEmpty()) {
				
				conn =Util.connect();
				String query ="SELECT * FROM advisorregistrationprofiledetails WHERE EMAIL=?";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1, email);
				ResultSet results = pstmt.executeQuery();
					while(results.next()){
						AdvisorProfileDTO profile = new AdvisorProfileDTO();
						profile.setAdvisorId(results.getInt("ADVISOR_ID"));
						profile.setName(results.getString("NAME"));
						profile.setGender(results.getString("GENDER"));
						profile.setAge(results.getString("AGE"));
						profile.setCity(results.getString("CURRENT_CITY"));
						profile.setNationalty(results.getString("NATIONALITY"));
						profile.setIndustry(results.getString("INDUSTRY"));
						profile.setPhone(results.getString("PHONE"));
						profile.setEmail(results.getString("EMAIL"));
						profile.setImage(results.getString("IMAGE"));
						profile.setNameOfOrganisation(results.getString("ORGANISATION"));
						profile.setJobTitle(results.getString("JOB_TITLE"));
						profile.setExperience(results.getString("EXPERIENCE"));
						profile.setUg(results.getString("UG"));
						profile.setPg(results.getString("PG"));
						profile.setOthers(results.getString("OTHERS"));
						profile.setAchievements(results.getString("ACHIEVEMENTS"));
						profile.setKeyskill(results.getString("KEY_SKILLS"));
						profile.setFunfact(results.getString("HOBBIES"));
						profile.setHobbies(results.getString("FUNFACT"));
						list.add(profile);
					}
					conn.commit();
				logger.info("Exit getAdvisorProfileDetails method of AdvisorPDFDetailsDAO");
			}
		}catch(SQLException se){
			try {
				conn.rollback();
			} catch (SQLException e) {
				logger.error("getAdvisorProfileDetails method of AdvisorPDFDetailsDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
			logger.error("getAdvisorProfileDetails method of AdvisorPDFDetailsDAO threw error:"+se.getMessage());
		      se.printStackTrace();
		   }catch(Exception e){
			   try {
					conn.rollback();
				} catch (SQLException e1) {
					logger.error("getAdvisorProfileDetails method of AdvisorPDFDetailsDAO threw error:"+e1.getMessage());
					e.printStackTrace();
				}
				logger.error("getAdvisorProfileDetails method of AdvisorPDFDetailsDAO threw error:"+e.getMessage());
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
					logger.error("getAdvisorProfileDetails method of AdvisorPDFDetailsDAO threw error:"+se.getMessage());
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		return list;

	}
	
	
	public List<ProfessionalBackgroundDTO> getAdvisorProfessionalBackground( int advisorId){

		List<ProfessionalBackgroundDTO> list = new ArrayList<ProfessionalBackgroundDTO>();
		logger.info("Entered getAdvisorProfessionalBackground method of AdvisorPDFDetailsDAO");
		try{
			if(advisorId != 0) {
				
				conn =Util.connect();
				String query ="SELECT * FROM advisorregistrationprofessionalbackground WHERE ADVISOR_ID=?";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setInt(1,advisorId);
				ResultSet results = pstmt.executeQuery();
				while(results.next()){
						ProfessionalBackgroundDTO dto = new ProfessionalBackgroundDTO();
						dto.setCompany(results.getString("COMPANY"));
						dto.setDesignation(results.getString("DESIGNATION"));
						dto.setDuration(results.getString("DURATION"));
						dto.setDescription(results.getString("DESCRIPTION"));
						list.add(dto);
				}
				conn.commit();
				logger.info("Exit getAdvisorProfessionalBackground method of AdvisorPDFDetailsDAO");
			}
		}catch(SQLException se){
			try {
				conn.rollback();
			} catch (SQLException e) {
				logger.error("getAdvisorProfessionalBackground method of AdvisorPDFDetailsDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
			logger.error("getAdvisorProfessionalBackground method of AdvisorPDFDetailsDAO threw error:"+se.getMessage());
		      se.printStackTrace();
		   }catch(Exception e){
			   try {
					conn.rollback();
				} catch (SQLException e1) {
					logger.error("getAdvisorProfessionalBackground method of AdvisorPDFDetailsDAO threw error:"+e1.getMessage());
					e.printStackTrace();
				}
				logger.error("getAdvisorProfessionalBackground method of AdvisorPDFDetailsDAO threw error:"+e.getMessage());
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
					logger.error("getAdvisorProfessionalBackground method of AdvisorPDFDetailsDAO threw error:"+se.getMessage());
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		return list;
	}
	
	public List<AdvisorServiceDTO> getAdvisorServices( int advisorId){

		List<AdvisorServiceDTO> list = new ArrayList<AdvisorServiceDTO>();
		logger.info("Entered getAdvisorServices method of AdvisorPDFDetailsDAO");
		try{
			if(advisorId != 0) {
				
				conn =Util.connect();
				String query ="SELECT * FROM advisorregistrationservices WHERE ADVISOR_ID=?";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setInt(1,advisorId);
				ResultSet results = pstmt.executeQuery();
				while(results.next()){
						AdvisorServiceDTO dto = new AdvisorServiceDTO();
						dto.setAdvisorId(advisorId);
						dto.setService(results.getString("SERVICE"));
						dto.setDescription(results.getString("DESCRIPTION"));
						list.add(dto);
				}
				conn.commit();
				logger.info("Exit getAdvisorServices method of AdvisorPDFDetailsDAO");
			}
		}catch(SQLException se){
			try {
				conn.rollback();
			} catch (SQLException e) {
				logger.error("getAdvisorServices method of AdvisorPDFDetailsDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
			logger.error("getAdvisorServices method of AdvisorPDFDetailsDAO threw error:"+se.getMessage());
		      se.printStackTrace();
		   }catch(Exception e){
			   try {
					conn.rollback();
				} catch (SQLException e1) {
					logger.error("getAdvisorServices method of AdvisorPDFDetailsDAO threw error:"+e1.getMessage());
					e.printStackTrace();
				}
				logger.error("getAdvisorServices method of AdvisorPDFDetailsDAO threw error:"+e.getMessage());
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
					logger.error("getAdvisorServices method of AdvisorPDFDetailsDAO threw error:"+se.getMessage());
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		return list;
	}
	
	
	public List<AdvisorModeDTO> getAdvisorModes( int advisorId){

		List<AdvisorModeDTO> list = new ArrayList<AdvisorModeDTO>();
		logger.info("Entered getAdvisorModes method of AdvisorPDFDetailsDAO");
		try{
			if(advisorId != 0) {
				
				conn =Util.connect();
				String query ="SELECT * FROM advisorregistrationmodes WHERE ADVISOR_ID=?";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setInt(1,advisorId);
				ResultSet results = pstmt.executeQuery();
				while(results.next()){
						AdvisorModeDTO dto = new AdvisorModeDTO();
						dto.setAdvisorId(advisorId);
						dto.setModeOfCommunication(results.getString("MODE_OF_COMMUNICATION"));
						dto.setPrice(results.getString("PRICE"));
						dto.setService(results.getString("SERVICE"));
						list.add(dto);
				}
				conn.commit();
				logger.info("Exit getAdvisorServices method of AdvisorPDFDetailsDAO");
			}
		}catch(SQLException se){
			try {
				conn.rollback();
			} catch (SQLException e) {
				logger.error("getAdvisorServices method of AdvisorPDFDetailsDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
			logger.error("getAdvisorServices method of AdvisorPDFDetailsDAO threw error:"+se.getMessage());
		      se.printStackTrace();
		   }catch(Exception e){
			   try {
					conn.rollback();
				} catch (SQLException e1) {
					logger.error("getAdvisorServices method of AdvisorPDFDetailsDAO threw error:"+e1.getMessage());
					e.printStackTrace();
				}
				logger.error("getAdvisorServices method of AdvisorPDFDetailsDAO threw error:"+e.getMessage());
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
					logger.error("getAdvisorServices method of AdvisorPDFDetailsDAO threw error:"+se.getMessage());
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		return list;
	}

}

