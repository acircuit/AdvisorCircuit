package org.AC.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.AC.JDBC.Util;
import org.AC.controller.ForgotPasswordController;
import org.AC.dto.AdvisorProfileDTO;
import org.AC.dto.AdvisorRegistrationCheckEmailDTO;
import org.AC.dto.AdvisorServiceDTO;
import org.AC.dto.ProfessionalBackgroundDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class AdvisorRegistrationDAO {
	private static final Logger logger = Logger.getLogger(AdvisorRegistrationDAO.class);
	Connection conn = null;
	Savepoint SavepointServices = null;
	
	//This method checks if the email Id entered by the advisor is already present in the table.
	//If yes, it returens the registration status.
	public List<AdvisorRegistrationCheckEmailDTO> checkEmail(String email){
		BasicConfigurator.configure();
		logger.info("Entered checkEmail method of AdvisorRegistrationDAO");
		List<AdvisorRegistrationCheckEmailDTO> list = new ArrayList<AdvisorRegistrationCheckEmailDTO>();
		if(!email.isEmpty()){
		conn =Util.connect();
		String query ="SELECT REGISTRATION_STATUS,ADVISOR_ID,NAME FROM advisorregistrationprofiledetails WHERE EMAIL = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1,email);
			ResultSet results = pstmt.executeQuery();
			if(results.first()){
			AdvisorRegistrationCheckEmailDTO dto = new AdvisorRegistrationCheckEmailDTO();
			dto.setAdvisorId(results.getInt("ADVISOR_ID"));
			dto.setRegistrationStatus(results.getString("REGISTRATION_STATUS"));
			dto.setName(results.getString("NAME"));
			list.add(dto);
			}
		} catch (SQLException e) {
			logger.error("checkEmail method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}
		}
		
		logger.info("Entered checkEmail method of AdvisorRegistrationDAO");
		return list;
	}
	
	
	
	//This function sets the General info of the advisor in the table.
	public Boolean setGeneralInfo(String name,String gender,String age,String city,String nationality,String phone,String industry,String organisation,String job,String experience,int advisorId,String state){
		
	BasicConfigurator.configure();
	logger.info("Entered setGeneralInfo method of AdvisorRegistrationDAO");
	String status = "EducationInfo.jsp"; 
	Boolean isGeneralInfoCommit = false;
	conn =Util.connect();
	String query = "UPDATE advisorregistrationprofiledetails  SET NAME=?,GENDER = ?,AGE = ?,PHONE = ?,CURRENT_CITY = ?,NATIONALITY = ?,INDUSTRY = ?,ORGANISATION = ?,JOB_TITLE = ?,EXPERIENCE = ?,REGISTRATION_STATUS = ?,STATE = ? WHERE ADVISOR_ID = ?";
	try {
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1,name );
		pstmt.setString(2, gender);
		pstmt.setString(3, age);
		pstmt.setString(4, phone);
		pstmt.setString(5, city);
		pstmt.setString(6, nationality);
		pstmt.setString(7, industry);
		pstmt.setString(8, organisation);
		pstmt.setString(9, job);
		pstmt.setString(10, experience);
		pstmt.setString(11, status);
		pstmt.setString(12, state);
		pstmt.setInt(13, advisorId);
		int result = pstmt.executeUpdate(); 
		if(result >0) {
			conn.commit();
			isGeneralInfoCommit = true;
			}
		conn.close();
	} catch (SQLException e) {
		try {
			conn.rollback();
		} catch (SQLException e1) {
			logger.error("setGeneralInfo method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e1.printStackTrace();
		}	
		logger.error("setGeneralInfo method of AdvisorRegistrationDAO threw error:"+e.getMessage());
		e.printStackTrace();
	}	
	logger.info("Entered setGeneralInfo method of AdvisorRegistrationDAO");
	return isGeneralInfoCommit;
	}
	
	public Boolean setEducationInfo(int advisorId , String ug,String pg,String others){
		
		BasicConfigurator.configure();
		logger.info("Entered setEducationInfo method of AdvisorRegistrationDAO");
		String status = "OtherInfo.jsp"; 
		Boolean isEducationInfoCommit = false;
		conn =Util.connect();
		String query = "UPDATE advisorregistrationprofiledetails SET UG = ?,PG = ?,OTHERS = ?, REGISTRATION_STATUS = ? WHERE ADVISOR_ID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1,ug );
			pstmt.setString(2, pg);
			pstmt.setString(3, others);
			pstmt.setString(4, status);
			pstmt.setInt(5, advisorId);
			int result = pstmt.executeUpdate(); 
			if(result >0) {
				conn.commit();
				isEducationInfoCommit = true;
			}
			conn.close();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("setGeneralInfo method of AdvisorRegistrationDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}	
			logger.error("setGeneralInfo method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}	
		logger.info("Entered setGeneralInfo method of AdvisorRegistrationDAO");
		return isEducationInfoCommit;
		}
	
	public Boolean setOtherInfo(int advisorId,String  achievement,String hobbies,String keyskills,String funfact){
		
		BasicConfigurator.configure();
		logger.info("Entered setEducationInfo method of AdvisorRegistrationDAO");
		String status = "ProfessionalBackground.jsp"; 
		int auto_id = 0;
		Boolean isOtherInfoCommit = false;
		conn =Util.connect();
		String query = "UPDATE advisorregistrationprofiledetails SET ACHIEVEMENTS = ?,KEY_SKILLS = ?,HOBBIES = ?, FUNFACT = ?,REGISTRATION_STATUS = ? WHERE ADVISOR_ID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1,achievement );
			pstmt.setString(2, keyskills);
			pstmt.setString(3, hobbies);
			pstmt.setString(4, funfact);
			pstmt.setString(5, status);
			pstmt.setInt(6, advisorId);
			int result = pstmt.executeUpdate(); 
			if(result >0) {
				conn.commit();
				isOtherInfoCommit = true;
			}
			conn.close();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("setGeneralInfo method of AdvisorRegistrationDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}	
			logger.error("setGeneralInfo method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}	
		logger.info("Entered setGeneralInfo method of AdvisorRegistrationDAO");
		return isOtherInfoCommit;
	}
	
public Boolean addProfessionalBackground(List<ProfessionalBackgroundDTO> professionalBackgrounds, int advisorId){
		
	BasicConfigurator.configure();
	logger.info("Entered addProfessionalBackground method of AdvisorRegistrationDAO");
	Boolean isProfessionalBackgorundCommit = false;
		if(!professionalBackgrounds.isEmpty() && (advisorId != 0)) {
			for (ProfessionalBackgroundDTO profession : professionalBackgrounds) {
				conn =Util.connect();
				try {
					Savepoint savepoint1 = conn.setSavepoint("Savepoint1");
					String query = "insert into advisorregistrationprofessionalbackground"+"(ADVISOR_ID,COMPANY,DESIGNATION,DURATION,DESCRIPTION) values" + "(?, ?, ?,?, ?)";
					PreparedStatement pstmt = conn.prepareStatement(query);
					pstmt.setInt(1,advisorId);
					pstmt.setString(2,profession.getCompany());
					pstmt.setString(3,profession.getDesignation());
					pstmt.setString(4,profession.getDuration());
					pstmt.setString(5,profession.getDescription());
					int result = pstmt.executeUpdate();
					if(result > 0) {
						conn.commit();
						isProfessionalBackgorundCommit = true;
						continue;
					}else{
						isProfessionalBackgorundCommit = false;
						conn.rollback(savepoint1);
						break;
					}
				} catch (SQLException e) {
					logger.error("setGeneralInfo method of AdvisorRegistrationDAO threw error:"+e.getMessage());
					e.printStackTrace();
				}
			}

		}
		logger.info("Entered addProfessionalBackground method of AdvisorRegistrationDAO");
		return isProfessionalBackgorundCommit;

	}

public Boolean setRegistrationStatus(int advisorId,String  status){
	
	BasicConfigurator.configure();
	logger.info("Entered setRegistrationStatus method of AdvisorRegistrationDAO");
	Boolean isRegistrationStatusCommit = false;
	conn =Util.connect();
	String query = "UPDATE advisorregistrationprofiledetails SET REGISTRATION_STATUS = ? WHERE ADVISOR_ID = ?";
	try {
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1,status );
		pstmt.setInt(2, advisorId);
		int result = pstmt.executeUpdate(); 
		if(result >0) {
			conn.commit();
			isRegistrationStatusCommit = true;
		}
		conn.close();
	} catch (SQLException e) {
		try {
			conn.rollback();
		} catch (SQLException e1) {
			logger.error("setRegistrationStatus method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e1.printStackTrace();
		}	
		logger.error("setRegistrationStatus method of AdvisorRegistrationDAO threw error:"+e.getMessage());
		e.printStackTrace();
	}	
	logger.info("Exit setRegistrationStatus method of AdvisorRegistrationDAO");
	return isRegistrationStatusCommit;
}
public Boolean setAdvisorServiceDetails( String service , 	int advisorId , String description) { 
	
	BasicConfigurator.configure();
	logger.info("Entered setAdvisorServiceDetails method of AdvisorRegistrationDAO");	
	Boolean isServiceCommit = false;
	if(!service.isEmpty() && advisorId!=0 && !description.isEmpty()){		
		conn =Util.connect();
		try {
		    SavepointServices = conn.setSavepoint("SavepointServices");
			String query = "insert into AdvisorRegistrationServices"+"(ADVISOR_ID,SERVICE,DESCRIPTION) values" + "(?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,advisorId);
			pstmt.setString(2,service);
			pstmt.setString(3,description);
			int result = pstmt.executeUpdate();
			if(result >0) {
				conn.commit();
				isServiceCommit = true;
			}
			conn.close();
		} catch (SQLException e) {
			try {
				conn.rollback(SavepointServices);
			} catch (SQLException e1) {
				logger.error("setAdvisorServiceDetails method of AdvisorRegistrationDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}
			logger.error("setAdvisorServiceDetails method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}
		logger.info("Exit setAdvisorServiceDetails method of AdvisorRegistrationDAO");	
	}
	return isServiceCommit;
}

public void setAdvisorModes( String service , 	int advisorId , String mode,String price) { 
	
	BasicConfigurator.configure();
	logger.info("Entered setAdvisorModes method of AdvisorRegistrationDAO");
	if(!service.isEmpty() && advisorId!=0 && !mode.isEmpty() && !price.isEmpty()){
		conn =Util.connect();
		String query = "insert into AdvisorRegistrationModes"+"(ADVISOR_ID,SERVICE,MODE_OF_COMMUNICATION,PRICE) values" + "(?, ?, ?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,advisorId);
			pstmt.setString(2,service);
			pstmt.setString(3,mode);
			pstmt.setString(4,price);
			int result = pstmt.executeUpdate();
			if(result >0) {
				conn.commit();
			}
			conn.close();
		} catch (SQLException e) {
			try {
				conn.rollback(SavepointServices);
			} catch (SQLException e1) {
				logger.error("setAdvisorModes method of AdvisorRegistrationDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}
			logger.error("setAdvisorModes method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}
		logger.info("Exit setAdvisorModes method of AdvisorRegistrationDAO");	
	}
}
public Boolean setImageURL(int advisorId,String  url, String status){
	
	BasicConfigurator.configure();
	logger.info("Entered setImageURL method of AdvisorRegistrationDAO");
	Boolean isImageCommit = false;
	conn =Util.connect();
	String query = "UPDATE advisorregistrationprofiledetails SET IMAGE= ?,REGISTRATION_STATUS= ? WHERE ADVISOR_ID = ?";
	try {
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1,url );
		pstmt.setString(2,status );
		pstmt.setInt(3, advisorId);
		int result = pstmt.executeUpdate(); 
		if(result >0) {
			conn.commit();
			isImageCommit = true;
		}
		conn.close();
	} catch (SQLException e) {
		try {
			conn.rollback();
		} catch (SQLException e1) {
			logger.error("setImageURL method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e1.printStackTrace();
		}	
		logger.error("setImageURL method of AdvisorRegistrationDAO threw error:"+e.getMessage());
		e.printStackTrace();
	}	
	logger.info("Exit setImageURL method of AdvisorRegistrationDAO");
	return isImageCommit;
}

public int setEmail(String email){
	
	BasicConfigurator.configure();
	logger.info("Entered setEmail method of AdvisorRegistrationDAO");
	int auto_id = 0;
	String status = "GeneralInfo.jsp";
	conn =Util.connect();
	String query = "INSERT INTO advisorregistrationprofiledetails(EMAIL,REGISTRATION_STATUS)" + "VALUES(?,?)";
	try {
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1,email );
		pstmt.setString(2,status );
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
			logger.error("setEmail method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e1.printStackTrace();
		}	
		logger.error("setEmail method of AdvisorRegistrationDAO threw error:"+e.getMessage());
		e.printStackTrace();
	}	
	logger.info("Exit setEmail method of AdvisorRegistrationDAO");
	return auto_id;
	}
}
