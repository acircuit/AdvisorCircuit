package org.AC.DAO;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.AC.JDBC.ConnectionFactory;
import org.apache.log4j.Logger;

public class AdvisorEditProfileDAO {
	private static final Logger logger = Logger.getLogger(AdvisorEditProfileDAO.class);
	Connection conn = null;

	//This function sets the General info of the advisor in the table.
		public Boolean setGeneralInfo(String name,String gender,String age,String city,String nationality,String phone,String industry,String intro,int advisorId,String state){
			
		
		logger.info("Entered setGeneralInfo method of AdvisorEditProfileDAO");
		String status = "EducationInfo.jsp"; 
		Boolean isGeneralInfoCommit = false;
		
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "UPDATE advisordetails  SET NAME=?,GENDER = ?,AGE = ?,PHONE_NUMBER = ?,CITY = ?,NATIONALITY = ?,INDUSTRY = ?,INTRODUCTION = ?,STATE = ? WHERE ADVISOR_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1,name );
			pstmt.setString(2, gender);
			pstmt.setString(3, age);
			pstmt.setString(4, phone);
			pstmt.setString(5, city);
			pstmt.setString(6, nationality);
			pstmt.setString(7, industry);
			pstmt.setString(8, intro);
			pstmt.setString(9, state);
			pstmt.setInt(10, advisorId);
			int result = pstmt.executeUpdate(); 
			if(result >0) {
				conn.commit();
				isGeneralInfoCommit = true;
				}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("setGeneralInfo method of AdvisorEditProfileDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}	
			logger.error("setGeneralInfo method of AdvisorEditProfileDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("setGeneralInfo method of AdvisorEditProfileDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("setGeneralInfo method of AdvisorEditProfileDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("setGeneralInfo method of AdvisorEditProfileDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}	
		logger.info("Entered setGeneralInfo method of AdvisorEditProfileDAO");
		return isGeneralInfoCommit;
		}
		
		public Boolean setImageURL(int advisorId,String  url){
			
			
			logger.info("Entered setImageURL method of AdvisorEditProfileImageController");
			Boolean isImageCommit = false;
			try {
				conn =ConnectionFactory.getConnection();
				conn.setAutoCommit(false);
				String query = "UPDATE advisordetails SET IMAGE= ? WHERE ADVISOR_ID = ?";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1,url );
				pstmt.setInt(2, advisorId);
				int result = pstmt.executeUpdate(); 
				if(result >0) {
					conn.commit();
					isImageCommit = true;
				}
			} catch (SQLException e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					logger.error("setImageURL method of AdvisorEditProfileImageController threw error:"+e.getMessage());
					e1.printStackTrace();
				}	
				logger.error("setImageURL method of AdvisorEditProfileImageController threw error:"+e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				logger.error("setImageURL method of AdvisorEditProfileImageController threw error:"+e.getMessage());
				e.printStackTrace();
			} catch (PropertyVetoException e) {
				logger.error("setImageURL method of AdvisorEditProfileImageController threw error:"+e.getMessage());
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("setImageURL method of AdvisorEditProfileImageController threw error:"+e.getMessage());
					e.printStackTrace();
				}
			}		
			logger.info("Exit setImageURL method of AdvisorEditProfileImageController");
			return isImageCommit;
		}

}
