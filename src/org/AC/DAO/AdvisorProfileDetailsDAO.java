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
import org.AC.Util.Utility;
import org.AC.controller.BookASessionServlet;
import org.AC.dto.AdvisorEducationDTO;
import org.AC.dto.AdvisorProfileDTO;
import org.AC.dto.RecommendationDTO;
import org.AC.dto.UserDetailsDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;


public class AdvisorProfileDetailsDAO {
	
	Connection conn = null;
	Statement stmt = null;
	private static final Logger logger = Logger.getLogger(AdvisorProfileDetailsDAO.class);
	
		public List<AdvisorProfileDTO> getAdvisorProfileDetails( String advisorId){

			java.util.List<AdvisorProfileDTO> list = new ArrayList<AdvisorProfileDTO>();
			try{
				if(advisorId != null && !advisorId.isEmpty()) {
					
					conn =ConnectionFactory.getConnection();
					conn.setAutoCommit(false);
					String query ="SELECT * FROM advisordetails WHERE ADVISOR_ID=?";
					PreparedStatement pstmt = conn.prepareStatement(query);
					pstmt.setString(1, advisorId);
					ResultSet results = pstmt.executeQuery();
					while(results.next()){
						AdvisorProfileDTO profile = new AdvisorProfileDTO();
						profile.setAdvisorId(results.getInt("ADVISOR_ID"));
						profile.setName(results.getString("NAME"));
						profile.setEmail(results.getString("EMAIL"));
						profile.setImage(results.getString("IMAGE"));
						profile.setAge(results.getString("AGE"));
						profile.setGender(results.getString("GENDER"));
						profile.setIntroduction(results.getString("INTRODUCTION"));
						profile.setIndustry(results.getString("INDUSTRY"));
						profile.setKnowYourAdvisor(results.getString("KNOW_YOUR_ADVISOR"));
						profile.setPhone(results.getString("PHONE_NUMBER"));
						profile.setCity(results.getString("CITY"));
						profile.setState(results.getString("STATE"));
						profile.setExperience(results.getString("EXPERIENCE"));
						profile.setRatings(results.getInt("RATING"));
						profile.setCharity(results.getString("CHARITY"));
						list.add(profile);
					}
						
				}
			}catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
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
			         se.printStackTrace();
			      }//end finally try
			   }//end try
			return list;
		}
		
		
		public int setAdvisorProfileDetails( AdvisorProfileDTO profile ){
			
			
			int auto_id = 0; 
			String query = "INSERT INTO advisordetails(NAME,GENDER,AGE,PHONE_NUMBER,EMAIL,CURRENT_CITY,NATIONALITY,INDUSTRY,NAME_OF_ORGANISATION,JOB_TITLE,EXPERIENCE,UG,PG,OTHERS,ACHIEVEMENTS,KEY_SKILL,HOBBIES,FUN_FACT,OCCUPATION,INTRODUCTION,KNOW_YOUR_ADVISOR,INTRO_PROFESSIONAL_BACKGROUND)" + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				conn =ConnectionFactory.getConnection();
				conn.setAutoCommit(false);
				PreparedStatement pstmt = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, profile.getName());
				pstmt.setString(2, profile.getGender());
				pstmt.setString(3, profile.getAge());
				pstmt.setString(4, profile.getPhone());
				pstmt.setString(5, profile.getEmail());
				pstmt.setString(6, profile.getCity());
				pstmt.setString(7, profile.getNationalty());
				pstmt.setString(8, profile.getIndustry());
				pstmt.setString(9, profile.getNameOfOrganisation());
				pstmt.setString(10, profile.getJobTitle());
				pstmt.setString(11, profile.getExperience());
				pstmt.setString(12, profile.getUg());
				pstmt.setString(13, profile.getPg());
				pstmt.setString(14, profile.getOthers());
				pstmt.setString(15, profile.getAchievements());
				pstmt.setString(16, profile.getKeyskill());
				pstmt.setString(17, profile.getHobbies());
				pstmt.setString(18, profile.getFunfact());
				pstmt.setString(19, profile.getOccupation());
				pstmt.setString(20, profile.getIntroduction());
				pstmt.setString(21, profile.getKnowYourAdvisor());
				pstmt.setString(22, profile.getProfessional_intro());
				int result = pstmt.executeUpdate(); 
				if(result >0) {
					conn.commit();
					ResultSet generatedKeys = pstmt.getGeneratedKeys();
					if (null != generatedKeys && generatedKeys.next()) {
					      auto_id = generatedKeys.getInt(1);
					}
				}
				conn.close();
			} catch (SQLException e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return auto_id;
		}
		
		public List<AdvisorProfileDTO> getAdvisorDetails(String email){
			logger.info("Entered getAdvisorDetails method of AdvisorProfileDetailsDAO");
			java.util.List<AdvisorProfileDTO> list = new ArrayList<AdvisorProfileDTO>();
			try{
				if(!email.isEmpty()) {
					
					conn =ConnectionFactory.getConnection();
					conn.setAutoCommit(false);
					String query ="SELECT * FROM advisordetails WHERE EMAIL=?";
					PreparedStatement pstmt = conn.prepareStatement(query);
					pstmt.setString(1, email);
					ResultSet results = pstmt.executeQuery();
					while(results.next()){
						AdvisorProfileDTO profile = new AdvisorProfileDTO();
						profile.setAdvisorId(results.getInt("ADVISOR_ID"));
						profile.setName(results.getString("NAME"));
						profile.setGender(results.getString("GENDER"));
						profile.setAge(results.getString("AGE"));
						profile.setPhone(results.getString("PHONE_NUMBER"));
						profile.setEmail(results.getString("EMAIL"));
						profile.setCity(results.getString("CURRENT_CITY"));
						profile.setNationalty(results.getString("NATIONALITY"));
						profile.setIndustry(results.getString("INDUSTRY"));
						profile.setNameOfOrganisation(results.getString("NAME_OF_ORGANISATION"));
						profile.setJobTitle(results.getString("JOB_TITLE"));
						profile.setExperience(results.getString("EXPERIENCE"));
						profile.setUg(results.getString("UG"));
						profile.setPg(results.getString("PG"));
						profile.setOthers(results.getString("OTHERS"));
						profile.setKeyskill(results.getString("KEY_SKILL"));
						profile.setAchievements(results.getString("ACHIEVEMENTS"));
						profile.setHobbies(results.getString("HOBBIES"));
						profile.setFunfact(results.getString("FUN_FACT"));
						list.add(profile);
					}
						
				}
				logger.info("Exit getAdvisorDetails method of AdvisorProfileDetailsDAO");
			}catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
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
			         se.printStackTrace();
			      }//end finally try
			   }//end try
			return list;
				
		}
		
		public List<RecommendationDTO> getAdvisorReviews( String advisorId){
			//List<String> reviews = null;
			List<RecommendationDTO> reviews = new ArrayList<RecommendationDTO>();
			try{
				if(!advisorId.isEmpty()) {
					
					conn =ConnectionFactory.getConnection();
					conn.setAutoCommit(false);
					String approve= "APPROVED";
					String query ="SELECT REVIEW_MESSAGE,USER_ID FROM session_table WHERE ADVISOR_ID=? AND REVIEW_MESSAGE_STATUS=?";
					PreparedStatement pstmt = conn.prepareStatement(query);
					pstmt.setString(1, advisorId);
					pstmt.setString(2, approve);
					ResultSet results = pstmt.executeQuery();
					while(results.next()){
						RecommendationDTO review = new RecommendationDTO();
						review.setUserId(results.getInt("USER_ID"));
						review.setReviewMessage(results.getString("REVIEW_MESSAGE"));
						reviews.add(review);
					}
				}
			}catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
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
			         se.printStackTrace();
			      }//end finally try
			   }//end try
			return reviews;
		}
		
		public List<AdvisorEducationDTO> GetEducation( String advisorId){
			List<AdvisorEducationDTO> edu = new ArrayList<AdvisorEducationDTO>();
			try{
				if(!advisorId.isEmpty()) {
					conn =ConnectionFactory.getConnection();
					conn.setAutoCommit(false);
					String query ="SELECT * FROM advisoreducation WHERE ADVISOR_ID=?";
					PreparedStatement pstmt = conn.prepareStatement(query);
					pstmt.setString(1, advisorId);
					ResultSet results = pstmt.executeQuery();
					while(results.next()){
						AdvisorEducationDTO education = new AdvisorEducationDTO();
						education.setAdvisorId(results.getInt("ADVISOR_ID"));
						education.setEducation(results.getString("EDUCATION"));
						edu.add(education);
					}
				}
			}catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
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
			         se.printStackTrace();
			      }//end finally try
			   }//end try
			return edu;
		}
		
		public List<AdvisorProfileDTO> GetKeySkill( String advisorId){
			logger.info("Entered GetKeySkill method of AdvisorProfileDetailsDAO");
			List<AdvisorProfileDTO> skills = new ArrayList<AdvisorProfileDTO>();
			try{
				if(!advisorId.isEmpty()) {
					conn =ConnectionFactory.getConnection();
					conn.setAutoCommit(false);
					String query ="SELECT * FROM advisorkeyskills WHERE ADVISOR_ID=?";
					PreparedStatement pstmt = conn.prepareStatement(query);
					pstmt.setString(1, advisorId);
					ResultSet results = pstmt.executeQuery();
					while(results.next()){
						AdvisorProfileDTO skill = new AdvisorProfileDTO();
						skill.setAdvisorId(results.getInt("ADVISOR_ID"));
						skill.setKeyskill(results.getString("KEY_SKILL"));
						skills.add(skill);
					}
				}
			}catch(SQLException se){
				logger.error("GetKeySkill method of AdvisorProfileDetailsDAO threw error:"+se.getMessage());
			      se.printStackTrace();
			   }catch(Exception e){
					logger.error("GetKeySkill method of AdvisorProfileDetailsDAO threw error:"+e.getMessage());
			      e.printStackTrace();
			   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            conn.close();
			      }catch(SQLException se){
						logger.error("GetKeySkill method of AdvisorProfileDetailsDAO threw error:"+se.getMessage());
			      }// do nothing
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
					logger.error("GetKeySkill method of AdvisorProfileDetailsDAO threw error:"+se.getMessage());
			         se.printStackTrace();
			      }//end finally try
			   }//end try
			
			logger.info("Entered GetKeySkill method of AdvisorProfileDetailsDAO");
			return skills;
		}
		
		public List<AdvisorProfileDTO> GetAchievements( String advisorId){
			logger.info("Entered GetAchievements method of AdvisorProfileDetailsDAO");
			List<AdvisorProfileDTO> awards = new ArrayList<AdvisorProfileDTO>();
			try{
				if(!advisorId.isEmpty()) {
					conn =ConnectionFactory.getConnection();
					conn.setAutoCommit(false);
					String query ="SELECT * FROM  advisorachievements WHERE ADVISOR_ID=?";
					PreparedStatement pstmt = conn.prepareStatement(query);
					pstmt.setString(1, advisorId);
					ResultSet results = pstmt.executeQuery();
					while(results.next()){
						AdvisorProfileDTO award = new AdvisorProfileDTO();
						award.setAdvisorId(results.getInt("ADVISOR_ID"));
						award.setAchievements(results.getString("ACHIEVEMENTS"));
						awards.add(award);
					}
				}
			}catch(SQLException se){
				logger.error("GetAchievements method of AdvisorProfileDetailsDAO threw error:"+se.getMessage());
			      se.printStackTrace();
			   }catch(Exception e){
					logger.error("GetAchievements method of AdvisorProfileDetailsDAO threw error:"+e.getMessage());
			      e.printStackTrace();
			   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            conn.close();
			      }catch(SQLException se){
						logger.error("GetAchievements method of AdvisorProfileDetailsDAO threw error:"+se.getMessage());
			      }// do nothing
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
					logger.error("GetAchievements method of AdvisorProfileDetailsDAO threw error:"+se.getMessage());
			         se.printStackTrace();
			      }//end finally try
			   }//end try
			
			logger.info("Entered GetAchievements method of AdvisorProfileDetailsDAO");
			return awards;
		}
		
		public List<UserDetailsDTO> getUserDetails( List<Integer> userId){
			logger.info("Entered getUserDetails method of AdvisorProfileDetailsDAO");
			List<UserDetailsDTO> list = new ArrayList<UserDetailsDTO>();
			if(userId.size() >0){
				try {
					conn =ConnectionFactory.getConnection();
					conn.setAutoCommit(false);
					String q4in = generateQsForIn(userId.size());			
					String query ="SELECT * FROM userdetails WHERE USER_ID IN ( " + q4in + " )";
					PreparedStatement pstmt;
					pstmt = conn.prepareStatement(query);
					int i = 1;
					  for (Integer item : userId) {
						  pstmt.setInt(i++, item);
					  }
				    ResultSet results = pstmt.executeQuery();
				    while(results.next()){
				    	UserDetailsDTO user = new UserDetailsDTO();
				    	user.setUserId(results.getInt("USER_ID"));
				    	user.setImage(results.getString("IMAGE"));
				    	user.setFullName(results.getString("FULL_NAME"));  	
				    	list.add(user);
				    }
				    conn.commit();
				logger.info("Exit getUserDetails method of AdvisorProfileDetailsDAO");
				} catch (SQLException e) {
					try {
						conn.rollback();
					} catch (SQLException e1) {
						logger.error("getUserDetails method of AdvisorProfileDetailsDAO threw error:"+e.getMessage());
						e1.printStackTrace();
					}
					logger.error("getUserDetails method of AdvisorProfileDetailsDAO threw error:"+e.getMessage());
					e.printStackTrace();
				} catch (IOException e) {
					logger.error("getUserDetails method of AdvisorProfileDetailsDAO threw error:"+e.getMessage());
					e.printStackTrace();
				} catch (PropertyVetoException e) {
					logger.error("getUserDetails method of AdvisorProfileDetailsDAO threw error:"+e.getMessage());
					e.printStackTrace();
				}finally{
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("getUserDetails method of AdvisorProfileDetailsDAO threw error:"+e.getMessage());
						e.printStackTrace();
					}
				}
			}

			
			logger.info("Exit getUserDetails method of AdvisorProfileDetailsDAO");
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
