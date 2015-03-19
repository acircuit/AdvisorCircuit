package org.AC.DAO;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.AC.JDBC.ConnectionFactory;
import org.AC.JDBC.Util;
import org.AC.Util.ProfessionalBackgroundObject;
import org.AC.controller.ForgotPasswordController;
import org.AC.dto.AdvisorEducationDTO;
import org.AC.dto.AdvisorModeDTO;
import org.AC.dto.AdvisorProfileDTO;
import org.AC.dto.AdvisorRegistrationCheckEmailDTO;
import org.AC.dto.AdvisorServiceDTO;
import org.AC.dto.ProfessionalBackgroundDTO;
import org.AC.dto.ServicesAndSkill;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class AdvisorRegistrationDAO {
	private static final Logger logger = Logger.getLogger(AdvisorRegistrationDAO.class);
	Connection conn = null;
	Savepoint SavepointServices = null;
	
	//This method checks if the email Id entered by the advisor is already present in the table.
	//If yes, it returens the registration status.
	public AdvisorRegistrationCheckEmailDTO checkEmail(String email,String pass){
		
		logger.info("Entered checkEmail method of AdvisorRegistrationDAO");
		AdvisorRegistrationCheckEmailDTO dto = new AdvisorRegistrationCheckEmailDTO();
		if(!email.isEmpty()){
		
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT REGISTRATION_STATUS,ADVISOR_ID,NAME FROM advisordetails WHERE EMAIL = ? AND PASSWORD=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1,email);
			pstmt.setString(2,pass);
			ResultSet results = pstmt.executeQuery();
			if(results.first()){
			dto.setAdvisorId(results.getInt("ADVISOR_ID"));
			dto.setRegistrationStatus(results.getString("REGISTRATION_STATUS"));
			dto.setName(results.getString("NAME"));
			}
		} catch (SQLException e) {
			logger.error("checkEmail method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("checkEmail method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("checkEmail method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("checkEmail method of AdvisorRegistrationDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		}
		
		logger.info("Entered checkEmail method of AdvisorRegistrationDAO");
		return dto;
	}
	public AdvisorRegistrationCheckEmailDTO checkEmail(String email){
		
		logger.info("Entered checkEmail method of AdvisorRegistrationDAO");
		AdvisorRegistrationCheckEmailDTO dto = new AdvisorRegistrationCheckEmailDTO();
		if(!email.isEmpty()){
		
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT REGISTRATION_STATUS,ADVISOR_ID,NAME FROM advisordetails WHERE EMAIL = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1,email);
			ResultSet results = pstmt.executeQuery();
			if(results.first()){
			dto.setAdvisorId(results.getInt("ADVISOR_ID"));
			dto.setRegistrationStatus(results.getString("REGISTRATION_STATUS"));
			dto.setName(results.getString("NAME"));
			}
		} catch (SQLException e) {
			logger.error("checkEmail method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("checkEmail method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("checkEmail method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("checkEmail method of AdvisorRegistrationDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		}
		
		logger.info("Entered checkEmail method of AdvisorRegistrationDAO");
		return dto;
	}
	
	
	
	//This function sets the General info of the advisor in the table.
	public Boolean setGeneralInfo(String name,String gender,String age,String city,String nationality,String phone,String industry,String intro,int advisorId,String state,String edit){
		
	
	logger.info("Entered setGeneralInfo method of AdvisorRegistrationDAO");
	String status = "EducationInfo.jsp"; 
	Boolean isGeneralInfoCommit = false;
	String query = "";
	PreparedStatement pstmt;
	try {
		conn =ConnectionFactory.getConnection();
		conn.setAutoCommit(false);
		if(!edit.equals("true")){
			query = "UPDATE advisordetails  SET NAME=?,GENDER = ?,AGE = ?,PHONE_NUMBER = ?,CITY = ?,NATIONALITY = ?,INDUSTRY = ?,INTRODUCTION = ?,REGISTRATION_STATUS = ?,STATE = ? WHERE ADVISOR_ID = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,name );
			pstmt.setString(2, gender);
			pstmt.setString(3, age);
			pstmt.setString(4, phone);
			pstmt.setString(5, city);
			pstmt.setString(6, nationality);
			pstmt.setString(7, industry);
			pstmt.setString(8, intro);
			pstmt.setString(9, status);
			pstmt.setString(10, state);
			pstmt.setInt(11, advisorId);
		}else{
			query = "UPDATE advisordetails  SET NAME=?,GENDER = ?,AGE = ?,PHONE_NUMBER = ?,CITY = ?,NATIONALITY = ?,INDUSTRY = ?,INTRODUCTION = ?,STATE = ? WHERE ADVISOR_ID = ?";
			pstmt = conn.prepareStatement(query);
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
		}
		
		int result = pstmt.executeUpdate(); 
		if(result >0) {
			conn.commit();
			isGeneralInfoCommit = true;
			}
	} catch (SQLException e) {
		try {
			conn.rollback();
		} catch (SQLException e1) {
			logger.error("setGeneralInfo method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e1.printStackTrace();
		}	
		logger.error("setGeneralInfo method of AdvisorRegistrationDAO threw error:"+e.getMessage());
		e.printStackTrace();
	} catch (IOException e) {
		logger.error("setGeneralInfo method of AdvisorRegistrationDAO threw error:"+e.getMessage());
		e.printStackTrace();
	} catch (PropertyVetoException e) {
		logger.error("setGeneralInfo method of AdvisorRegistrationDAO threw error:"+e.getMessage());
		e.printStackTrace();
	}finally{
		try {
			conn.close();
		} catch (SQLException e) {
			logger.error("setGeneralInfo method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}
	}	
	logger.info("Entered setGeneralInfo method of AdvisorRegistrationDAO");
	return isGeneralInfoCommit;
	}
	
	public Boolean setEducationInfo(List<AdvisorEducationDTO> education){
		logger.info("Entered setEducationInfo method of AdvisorRegistrationDAO");
		Boolean isEducationCommit = false;
			if(!education.isEmpty()) {
				for (AdvisorEducationDTO edu : education) {
					try {
						conn =ConnectionFactory.getConnection();
						conn.setAutoCommit(false);
						Savepoint SavepointEducation = conn.setSavepoint("SavepointEducation");
						String query = "insert into advisoreducation"+"(ADVISOR_ID,TYPE,EDUCATION) values" + "(?, ?, ?)";
						PreparedStatement pstmt = conn.prepareStatement(query);
						pstmt.setInt(1,edu.getAdvisorId());
						pstmt.setString(2,edu.getType());
						pstmt.setString(3,edu.getEducation());
						int result = pstmt.executeUpdate();
						if(result > 0) {
							conn.commit();
							isEducationCommit = true;
							continue;
						}else{
							isEducationCommit = false;
							conn.rollback(SavepointEducation);
							break;
						}
					} catch (SQLException e) {
						logger.error("setEducationInfo method of AdvisorRegistrationDAO threw error:"+e.getMessage());
						e.printStackTrace();
					} catch (IOException e) {
						logger.error("setEducationInfo method of AdvisorRegistrationDAO threw error:"+e.getMessage());
						e.printStackTrace();
					} catch (PropertyVetoException e) {
						logger.error("setEducationInfo method of AdvisorRegistrationDAO threw error:"+e.getMessage());
						e.printStackTrace();
					}finally{
						try {
							conn.close();
						} catch (SQLException e) {
							logger.error("setEducationInfo method of AdvisorRegistrationDAO threw error:"+e.getMessage());
							e.printStackTrace();
						}
					}	
				}

			}
			logger.info("Entered setEducationInfo method of AdvisorRegistrationDAO");
			return isEducationCommit;

		}
	
	public Boolean setHobbies(int advisorId,String hobbies){
		
		
		logger.info("Entered setHobbies method of AdvisorRegistrationDAO");
		String status = "Services.jsp"; 
		Boolean isOtherInfoCommit = false;
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "UPDATE advisordetails SET KNOW_YOUR_ADVISOR = ?,REGISTRATION_STATUS = ? WHERE ADVISOR_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1,hobbies );
			pstmt.setString(2, status);
			pstmt.setInt(3, advisorId);
			int result = pstmt.executeUpdate(); 
			if(result >0) {
				conn.commit();
				isOtherInfoCommit = true;
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("setHobbies method of AdvisorRegistrationDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}	
			logger.error("setHobbies method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("setHobbies method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("setHobbies method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("setHobbies method of AdvisorRegistrationDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}		
		logger.info("Entered setHobbies method of AdvisorRegistrationDAO");
		return isOtherInfoCommit;
	}
	
public List<Integer> addProfessionalBackground(List<ProfessionalBackgroundDTO> professionalBackgrounds, int advisorId){
		
	
	logger.info("Entered addProfessionalBackground method of AdvisorRegistrationDAO");
	Boolean isProfessionalBackgorundCommit = false;
	List<Integer> profId =new ArrayList<Integer>();
		if(!professionalBackgrounds.isEmpty() && (advisorId != 0)) {
			for (ProfessionalBackgroundDTO profession : professionalBackgrounds) {
				try {
					conn =ConnectionFactory.getConnection();
					conn.setAutoCommit(false);
					Savepoint savepoint1 = conn.setSavepoint("Savepoint1");
					String query = "insert into advisorprofessionalbackground"+"(ADVISOR_ID,COMPANY,DESIGNATION,DURATION) values" + "(?, ?,?, ?)";
					PreparedStatement pstmt = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
					pstmt.setInt(1,advisorId);
					pstmt.setString(2,profession.getCompany());
					pstmt.setString(3,profession.getDesignation());
					pstmt.setString(4,profession.getDuration());
					int result = pstmt.executeUpdate();
					if(result > 0) {
						conn.commit();
						ResultSet generatedKeys = pstmt.getGeneratedKeys();
						if (null != generatedKeys && generatedKeys.next()) {
							profId.add(generatedKeys.getInt(1));
						}
						isProfessionalBackgorundCommit = true;
						continue;
					}else{
						isProfessionalBackgorundCommit = false;
						conn.rollback(savepoint1);
						break;
					}
				} catch (SQLException e) {
					logger.error("addProfessionalBackground method of AdvisorRegistrationDAO threw error:"+e.getMessage());
					e.printStackTrace();
				} catch (IOException e) {
					logger.error("addProfessionalBackground method of AdvisorRegistrationDAO threw error:"+e.getMessage());
					e.printStackTrace();
				} catch (PropertyVetoException e) {
					logger.error("addProfessionalBackground method of AdvisorRegistrationDAO threw error:"+e.getMessage());
					e.printStackTrace();
				}finally{
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("addProfessionalBackground method of AdvisorRegistrationDAO threw error:"+e.getMessage());
						e.printStackTrace();
					}
				}	
			}

		}
		logger.info("Entered addProfessionalBackground method of AdvisorRegistrationDAO");
		return profId;

	}

public Boolean setRegistrationStatus(int advisorId,String  status){
	
	
	logger.info("Entered setRegistrationStatus method of AdvisorRegistrationDAO");
	Boolean isRegistrationStatusCommit = false;
	try {
		conn =ConnectionFactory.getConnection();
		conn.setAutoCommit(false);
		String query = "UPDATE advisordetails SET REGISTRATION_STATUS = ? WHERE ADVISOR_ID = ?";
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1,status );
		pstmt.setInt(2, advisorId);
		int result = pstmt.executeUpdate(); 
		if(result >0) {
			conn.commit();
			isRegistrationStatusCommit = true;
		}
	} catch (SQLException e) {
		try {
			conn.rollback();
		} catch (SQLException e1) {
			logger.error("setRegistrationStatus method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e1.printStackTrace();
		}	
		logger.error("setRegistrationStatus method of AdvisorRegistrationDAO threw error:"+e.getMessage());
		e.printStackTrace();
	} catch (IOException e) {
		logger.error("setRegistrationStatus method of AdvisorRegistrationDAO threw error:"+e.getMessage());
		e.printStackTrace();
	} catch (PropertyVetoException e) {
		logger.error("setRegistrationStatus method of AdvisorRegistrationDAO threw error:"+e.getMessage());
		e.printStackTrace();
	}finally{
		try {
			conn.close();
		} catch (SQLException e) {
			logger.error("setRegistrationStatus method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}
	}		
	logger.info("Exit setRegistrationStatus method of AdvisorRegistrationDAO");
	return isRegistrationStatusCommit;
}
public Boolean setAdvisorServiceDetails( String service , 	int advisorId , String description, Boolean isFree) { 
	
	
	logger.info("Entered setAdvisorServiceDetails method of AdvisorRegistrationDAO");	
	Boolean isServiceCommit = false;
	if(!service.isEmpty() && advisorId!=0 && !description.isEmpty()){		
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
		    SavepointServices = conn.setSavepoint("SavepointServices");
			String query = "insert into advisorservices"+"(ADVISOR_ID,SERVICE,DESCRIPTION,ISFREE) values" + "(?, ?, ?,?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,advisorId);
			pstmt.setString(2,service);
			pstmt.setString(3,description);
			pstmt.setBoolean(4, isFree);
			int result = pstmt.executeUpdate();
			if(result >0) {
				conn.commit();
				isServiceCommit = true;
			}
		} catch (SQLException e) {
			try {
				conn.rollback(SavepointServices);
			} catch (SQLException e1) {
				logger.error("setAdvisorServiceDetails method of AdvisorRegistrationDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}
			logger.error("setAdvisorServiceDetails method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("setAdvisorServiceDetails method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("setAdvisorServiceDetails method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("setAdvisorServiceDetails method of AdvisorRegistrationDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}	
		logger.info("Exit setAdvisorServiceDetails method of AdvisorRegistrationDAO");	
	}
	return isServiceCommit;
}

public void setAdvisorModes( String service , 	int advisorId , String mode,String price) { 
	
	
	logger.info("Entered setAdvisorModes method of AdvisorRegistrationDAO");
	if(!service.isEmpty() && advisorId!=0 && !mode.isEmpty() && !price.isEmpty()){
		String query = "insert into advisormodes"+"(ADVISOR_ID,SERVICE,MODE_OF_COMMUNICATION,PRICE) values" + "(?, ?, ?,?)";
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,advisorId);
			pstmt.setString(2,service);
			pstmt.setString(3,mode);
			pstmt.setString(4,price);
			int result = pstmt.executeUpdate();
			if(result >0) {
				conn.commit();
			}
		} catch (SQLException e) {
			try {
				conn.rollback(SavepointServices);
			} catch (SQLException e1) {
				logger.error("setAdvisorModes method of AdvisorRegistrationDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}
			logger.error("setAdvisorModes method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("setAdvisorModes method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("setAdvisorModes method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("setAdvisorModes method of AdvisorRegistrationDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}	
		logger.info("Exit setAdvisorModes method of AdvisorRegistrationDAO");	
	}
}
	public Boolean setImageURL(int advisorId,String  url, String status){
	
	
		logger.info("Entered setImageURL method of AdvisorRegistrationDAO");
		Boolean isImageCommit = false;
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "UPDATE advisordetails SET IMAGE= ?,REGISTRATION_STATUS= ? WHERE ADVISOR_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1,url );
			pstmt.setString(2,status );
			pstmt.setInt(3, advisorId);
			int result = pstmt.executeUpdate(); 
			if(result >0) {
				conn.commit();
				isImageCommit = true;
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("setImageURL method of AdvisorRegistrationDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}	
			logger.error("setImageURL method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("setImageURL method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("setImageURL method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("setImageURL method of AdvisorRegistrationDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}		
		logger.info("Exit setImageURL method of AdvisorRegistrationDAO");
		return isImageCommit;
	}

public int setEmail(String email,String pass){
	
	
	logger.info("Entered setEmail method of AdvisorRegistrationDAO");
	int auto_id = 0;
	String status = "GeneralInfo.jsp";
	
	try {
		conn =ConnectionFactory.getConnection();
		conn.setAutoCommit(false);
		String query = "INSERT INTO advisordetails(EMAIL,REGISTRATION_STATUS,PASSWORD)" + "VALUES(?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
		pstmt.setString(1,email );
		pstmt.setString(2,status );
		pstmt.setString(3, pass);
		int result = pstmt.executeUpdate(); 
		if(result >0) {
			conn.commit();
			ResultSet generatedKeys = pstmt.getGeneratedKeys();
			if (null != generatedKeys && generatedKeys.next()) {
			      auto_id = generatedKeys.getInt(1);
			}
		}
	} catch (SQLException e) {
		try {
			conn.rollback();
		} catch (SQLException e1) {
			logger.error("setEmail method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e1.printStackTrace();
		}	
		logger.error("setEmail method of AdvisorRegistrationDAO threw error:"+e.getMessage());
		e.printStackTrace();
	} catch (IOException e) {
		logger.error("setEmail method of AdvisorRegistrationDAO threw error:"+e.getMessage());
		e.printStackTrace();
	} catch (PropertyVetoException e) {
		logger.error("setEmail method of AdvisorRegistrationDAO threw error:"+e.getMessage());
		e.printStackTrace();
	}finally{
		try {
			conn.close();
		} catch (SQLException e) {
			logger.error("setEmail method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}
	}		
	logger.info("Exit setEmail method of AdvisorRegistrationDAO");
	return auto_id;
	}
	public AdvisorProfileDTO GetGeneralInfo(int aId){
		
		logger.info("Entered GetGeneralInfo method of AdvisorRegistrationDAO");
		AdvisorProfileDTO profile = new AdvisorProfileDTO();
		if( aId != 0){
		
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT NAME,GENDER,AGE,PHONE_NUMBER,CITY,NATIONALITY,INDUSTRY,INTRODUCTION,STATE,IMAGE,EMAIL FROM advisordetails WHERE ADVISOR_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,aId);
			ResultSet results = pstmt.executeQuery();
			if(results.first()){
			profile.setName(results.getString("NAME"));
			profile.setGender(results.getString("GENDER"));
			profile.setAge(results.getString("AGE"));
			profile.setPhone(results.getString("PHONE_NUMBER"));
			profile.setCity(results.getString("CITY"));
			profile.setNationalty(results.getString("NATIONALITY"));
			profile.setIndustry(results.getString("INDUSTRY"));
			profile.setIntroduction(results.getString("INTRODUCTION"));
			profile.setState(results.getString("STATE"));
			profile.setImage(results.getString("IMAGE"));
			profile.setEmail(results.getString("EMAIL"));
			}
		} catch (SQLException e) {
			logger.error("GetGeneralInfo method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("GetGeneralInfo method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("GetGeneralInfo method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetGeneralInfo method of AdvisorRegistrationDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		}
		
		logger.info("Entered GetGeneralInfo method of AdvisorRegistrationDAO");
		return profile;
	}
	
	public List<AdvisorEducationDTO> GetEducationInfo(int aId){
		
		logger.info("Entered GetEducationInfo method of AdvisorRegistrationDAO");
		List<AdvisorEducationDTO> educations = new ArrayList<AdvisorEducationDTO>();
		if( aId != 0){
		
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT ADVISOR_ID,TYPE,EDUCATION FROM advisoreducation WHERE ADVISOR_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,aId);
			ResultSet results = pstmt.executeQuery();
			while(results.next()){
				AdvisorEducationDTO education = new AdvisorEducationDTO();
				education.setAdvisorId(results.getInt("ADVISOR_ID"));
				education.setType(results.getString("TYPE"));
				education.setEducation(results.getString("EDUCATION"));
				educations.add(education);
			}
		} catch (SQLException e) {
			logger.error("GetEducationInfo method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("GetEducationInfo method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("GetEducationInfo method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetEducationInfo method of AdvisorRegistrationDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		}
		
		logger.info("Entered GetEducationInfo method of AdvisorRegistrationDAO");
		return educations;
	}
	
	
	public List<ProfessionalBackgroundDTO> GetProfessionalBackground(int aId){
		
		logger.info("Entered GetProfessionalBackground method of AdvisorRegistrationDAO");
		List<ProfessionalBackgroundDTO> list = new ArrayList<ProfessionalBackgroundDTO>();
		if( aId != 0){
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT PROF_ID,COMPANY,DESIGNATION,DURATION FROM advisorprofessionalbackground WHERE ADVISOR_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,aId);
			ResultSet results = pstmt.executeQuery();
			while(results.next()){
				ProfessionalBackgroundDTO prof = new ProfessionalBackgroundDTO();
				prof.setProfId(results.getInt("PROF_ID"));
				prof.setCompany(results.getString("COMPANY"));
				prof.setDesignation(results.getString("DESIGNATION"));
				prof.setDuration(results.getString("DURATION"));
				list.add(prof);
			}
		} catch (SQLException e) {
			logger.error("GetProfessionalBackground method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("GetProfessionalBackground method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("GetProfessionalBackground method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetProfessionalBackground method of AdvisorRegistrationDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		}
		
		logger.info("Entered GetProfessionalBackground method of AdvisorRegistrationDAO");
		return list;
	}
	
	
	public Boolean DeleteProfessionBackground(int aId){
		
		logger.info("Entered DeleteProfessionBackground method of AdvisorRegistrationDAO");
		List<ProfessionalBackgroundDTO> list = new ArrayList<ProfessionalBackgroundDTO>();
		Boolean isDeleted =false;
		if( aId != 0){
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="DELETE FROM advisorregistrationprofessionalbackground WHERE ADVISOR_ID =?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,aId);
			int result = pstmt.executeUpdate(); 
			if(result >0) {
				conn.commit();
				isDeleted = true;
			}

		} catch (SQLException e) {
			logger.error("DeleteProfessionBackground method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("DeleteProfessionBackground method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("DeleteProfessionBackground method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("DeleteProfessionBackground method of AdvisorRegistrationDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		}
		
		logger.info("Entered GetProfessionalBackground method of AdvisorRegistrationDAO");
		return isDeleted;
	}
	
	public List<AdvisorProfileDTO> GetAchievements(int aId){
		
		logger.info("Entered GetAchievements method of AdvisorRegistrationDAO");
		List<AdvisorProfileDTO> achievements = new ArrayList<AdvisorProfileDTO>();
		if( aId != 0){
		
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT * FROM advisorachievements WHERE ADVISOR_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,aId);
			ResultSet results = pstmt.executeQuery();
			while(results.next()){
				AdvisorProfileDTO profile = new AdvisorProfileDTO();
				profile.setAchievements(results.getString("ACHIEVEMENTS"));
				profile.setAdvisorId(results.getInt("ADVISOR_ID"));
				achievements.add(profile);
			}
		} catch (SQLException e) {
			logger.error("GetAchievements method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("GetAchievements method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("GetAchievements method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetAchievements method of AdvisorRegistrationDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		}
		
		logger.info("Entered GetAchievements method of AdvisorRegistrationDAO");
		return achievements;
	}
	public Boolean DeleteServices(int aId){
		
		logger.info("Entered DeleteServices method of AdvisorRegistrationDAO");
		List<ProfessionalBackgroundDTO> list = new ArrayList<ProfessionalBackgroundDTO>();
		Boolean isDeleted =false;
		if( aId != 0){
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="DELETE FROM advisorservices WHERE ADVISOR_ID =?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,aId);
			int result = pstmt.executeUpdate(); 
			if(result >0) {
				conn.commit();
				isDeleted = true;
			}

		} catch (SQLException e) {
			logger.error("DeleteServices method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("DeleteServices method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("DeleteServices method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("DeleteServices method of AdvisorRegistrationDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		}
		
		logger.info("Entered DeleteServices method of AdvisorRegistrationDAO");
		return isDeleted;
	}
	
	public Boolean DeleteModes(int aId){
		
		logger.info("Entered DeleteModes method of AdvisorRegistrationDAO");
		List<ProfessionalBackgroundDTO> list = new ArrayList<ProfessionalBackgroundDTO>();
		Boolean isDeleted =false;
		if( aId != 0){
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="DELETE FROM advisormodes WHERE ADVISOR_ID =?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,aId);
			int result = pstmt.executeUpdate(); 
			if(result >0) {
				conn.commit();
				isDeleted = true;
			}

		} catch (SQLException e) {
			logger.error("DeleteModes method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("DeleteModes method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("DeleteModes method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("DeleteModes method of AdvisorRegistrationDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		}
		
		logger.info("Entered DeleteModes method of AdvisorRegistrationDAO");
		return isDeleted;
	}
	
	public List<AdvisorServiceDTO> GetServices(int aId){
		
		logger.info("Entered GetServices method of AdvisorRegistrationDAO");
		List<AdvisorServiceDTO> list = new ArrayList<AdvisorServiceDTO>();
		if( aId != 0){
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT ADVISOR_ID,SERVICE,DESCRIPTION,ISFREE FROM advisorservices WHERE ADVISOR_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,aId);
			ResultSet results = pstmt.executeQuery();
			while(results.next()){
				AdvisorServiceDTO service = new AdvisorServiceDTO();
				service.setAdvisorId(results.getInt("ADVISOR_ID"));
				service.setService(results.getString("SERVICE"));
				service.setDescription(results.getString("DESCRIPTION"));
				service.setIsFree(results.getBoolean("ISFREE"));
				list.add(service);
			}
		} catch (SQLException e) {
			logger.error("GetServices method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("GetServices method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("GetServices method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetServices method of AdvisorRegistrationDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		}
		
		logger.info("Entered GetServices method of AdvisorRegistrationDAO");
		return list;
	}
	
	public List<AdvisorModeDTO> GetModes(int aId){
		
		logger.info("Entered GetModes method of AdvisorRegistrationDAO");
		List<AdvisorModeDTO> list = new ArrayList<AdvisorModeDTO>();
		if( aId != 0){
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT ADVISOR_ID,SERVICE,MODE_OF_COMMUNICATION,PRICE FROM advisormodes WHERE ADVISOR_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,aId);
			ResultSet results = pstmt.executeQuery();
			while(results.next()){
				AdvisorModeDTO mode = new AdvisorModeDTO();
				mode.setAdvisorId(results.getInt("ADVISOR_ID"));
				mode.setService(results.getString("SERVICE"));
				mode.setModeOfCommunication(results.getString("MODE_OF_COMMUNICATION"));
				mode.setPrice(results.getString("PRICE"));
				list.add(mode);
			}
		} catch (SQLException e) {
			logger.error("GetModes method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("GetModes method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("GetModes method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetModes method of AdvisorRegistrationDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		}
		
		logger.info("Entered GetModes method of AdvisorRegistrationDAO");
		return list;
	}
	
	public String getStatus(int aId){
		
		logger.info("Entered getStatus method of AdvisorRegistrationDAO");
		String status="";
		if( aId != 0){
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT REGISTRATION_STATUS FROM advisordetails WHERE ADVISOR_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,aId);
			ResultSet results = pstmt.executeQuery();
			if(results.first()){
				status = results.getString("REGISTRATION_STATUS");
			}
			
		} catch (SQLException e) {
			logger.error("getStatus method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("getStatus method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("getStatus method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("getStatus method of AdvisorRegistrationDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		}
		
		logger.info("Entered getStatus method of AdvisorRegistrationDAO");
		return status;
	}
	public Boolean setDescription(int aId,List<ProfessionalBackgroundDTO> descriptions){
		logger.info("Entered setDescription method of AdvisorRegistrationDAO");
		Boolean isProfessionalBackgorundCommit = false;
			if(!descriptions.isEmpty() && descriptions.size() > 0) {
				try{
					conn =ConnectionFactory.getConnection();
					conn.setAutoCommit(false);
					Savepoint savepoint1 = conn.setSavepoint("Savepoint1");
					for (ProfessionalBackgroundDTO professionalBackgroundDTO : descriptions) {
						for (String description : professionalBackgroundDTO.getDescriptionArray()) {
							if(!description.equals("")){
								String query = "insert into advisorprofdescription"+"(PROF_ID,ADVISOR_ID,DESCRIPTION) values" + "(?,?,?)";
								PreparedStatement pstmt = conn.prepareStatement(query);
								pstmt.setInt(1,professionalBackgroundDTO.getProfId());
								pstmt.setInt(2,aId);
								pstmt.setString(3,description);
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
							}
						}
					}
				}catch (SQLException e) {
					logger.error("setDescription method of AdvisorRegistrationDAO threw error:"+e.getMessage());
					e.printStackTrace();
				} catch (IOException e) {
					logger.error("setDescription method of AdvisorRegistrationDAO threw error:"+e.getMessage());
					e.printStackTrace();
				} catch (PropertyVetoException e) {
					logger.error("setDescription method of AdvisorRegistrationDAO threw error:"+e.getMessage());
					e.printStackTrace();
				}finally{
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("setDescription method of AdvisorRegistrationDAO threw error:"+e.getMessage());
						e.printStackTrace();
					}
				}
				
			}
			logger.info("Entered setDescription method of AdvisorRegistrationDAO");
			return isProfessionalBackgorundCommit;

		}
	public Boolean setAchievements(int aId, String[] achievement){
		logger.info("Entered setAchievements method of AdvisorRegistrationDAO");
		Boolean isAchievementCommit = false;
		try {
				conn =ConnectionFactory.getConnection();
				conn.setAutoCommit(false);
				for (String award : achievement) {
					
						if(!award.equals("")){

							Savepoint SavepointAchievement = conn.setSavepoint("SavepointAchievement");
							String query = "insert into advisorachievements"+"(ADVISOR_ID,ACHIEVEMENTS) values" + "(?, ?)";
							PreparedStatement pstmt = conn.prepareStatement(query);
							pstmt.setInt(1,aId);
							pstmt.setString(2,award);
							int result = pstmt.executeUpdate();
							if(result > 0) {
								conn.commit();
								isAchievementCommit = true;
							}else{
								isAchievementCommit = false;
								conn.rollback(SavepointAchievement);
								break;
							}
						}
					} 
		}catch (SQLException e) {
						logger.error("setAchievements method of AdvisorRegistrationDAO threw error:"+e.getMessage());
						e.printStackTrace();
					} catch (IOException e) {
						logger.error("setAchievements method of AdvisorRegistrationDAO threw error:"+e.getMessage());
						e.printStackTrace();
					} catch (PropertyVetoException e) {
						logger.error("setAchievements method of AdvisorRegistrationDAO threw error:"+e.getMessage());
						e.printStackTrace();
					}finally{
						try {
							conn.close();
						} catch (SQLException e) {
							logger.error("setAchievements method of AdvisorRegistrationDAO threw error:"+e.getMessage());
							e.printStackTrace();
						}
					}	
			logger.info("Entered setAchievements method of AdvisorRegistrationDAO");
			return isAchievementCommit;

		}	
	
	public Boolean setSkills(int aId, String[] skills){
		logger.info("Entered setSkills method of AdvisorRegistrationDAO");
		Boolean isSkillsCommit = false;
				for (String skill : skills) {
					try {
						if(!skill.equals("")){
							conn =ConnectionFactory.getConnection();
							conn.setAutoCommit(false);
							Savepoint SavepointSkills = conn.setSavepoint("SavepointSkills");
							String query = "insert into advisorkeyskills"+"(ADVISOR_ID,KEY_SKILL) values" + "(?, ?)";
							PreparedStatement pstmt = conn.prepareStatement(query);
							pstmt.setInt(1,aId);
							pstmt.setString(2,skill);
							int result = pstmt.executeUpdate();
							if(result > 0) {
								conn.commit();
								isSkillsCommit = true;
								continue;
							}else{
								isSkillsCommit = false;
								conn.rollback(SavepointSkills);
								break;
							}
						}
					} catch (SQLException e) {
						logger.error("setSkills method of AdvisorRegistrationDAO threw error:"+e.getMessage());
						e.printStackTrace();
					} catch (IOException e) {
						logger.error("setSkills method of AdvisorRegistrationDAO threw error:"+e.getMessage());
						e.printStackTrace();
					} catch (PropertyVetoException e) {
						logger.error("setSkills method of AdvisorRegistrationDAO threw error:"+e.getMessage());
						e.printStackTrace();
					}finally{
						try {
							conn.close();
						} catch (SQLException e) {
							logger.error("setSkills method of AdvisorRegistrationDAO threw error:"+e.getMessage());
							e.printStackTrace();
						}
					}	
				}
			logger.info("Entered setSkills method of AdvisorRegistrationDAO");
			return isSkillsCommit;

		}	
	public Boolean DeleteEducation(int aId){
		
		logger.info("Entered DeleteEducation method of AdvisorRegistrationDAO");
		Boolean isDeleted =false;
		if( aId != 0){
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="DELETE FROM advisoreducation WHERE ADVISOR_ID =?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,aId);
			int result = pstmt.executeUpdate(); 
			if(result >0) {
				conn.commit();
				isDeleted = true;
			}

		} catch (SQLException e) {
			logger.error("DeleteEducation method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("DeleteEducation method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("DeleteEducation method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("DeleteEducation method of AdvisorRegistrationDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		}
		
		logger.info("Entered DeleteEducation method of AdvisorRegistrationDAO");
		return isDeleted;
	}
	public List<ProfessionalBackgroundDTO> GetProfessionalBackgroundDescription(List<Integer> profIds){
		
		logger.info("Entered GetProfessionalBackgroundDescription method of AdvisorRegistrationDAO");
		List<ProfessionalBackgroundDTO> description = new ArrayList<ProfessionalBackgroundDTO>();
		if( profIds.size() > 0){
		for (Integer id : profIds) {
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String q4in = generateQuery(profIds.size());			
			String query ="SELECT PROF_ID,DESCRIPTION FROM advisorprofdescription WHERE PROF_ID =?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			ResultSet results = pstmt.executeQuery();
			while(results.next()){
				ProfessionalBackgroundDTO prof = new ProfessionalBackgroundDTO();
				prof.setProfId(results.getInt("PROF_ID"));
				prof.setDescription(results.getString("DESCRIPTION"));
				description.add(prof);
			}
		} catch (SQLException e) {
			logger.error("GetProfessionalBackgroundDescription method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("GetProfessionalBackgroundDescription method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("GetProfessionalBackgroundDescription method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetProfessionalBackgroundDescription method of AdvisorRegistrationDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		}
		}
		
		logger.info("Entered GetProfessionalBackgroundDescription method of AdvisorRegistrationDAO");
		return description;
	}
	
	public Boolean DeleteProfCompany(int aId){
		
		logger.info("Entered DeleteProfCompany method of AdvisorRegistrationDAO");
		Boolean isDeleted =false;
		if( aId != 0){
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="DELETE FROM advisorprofessionalbackground WHERE ADVISOR_ID =?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,aId);
			int result = pstmt.executeUpdate(); 
			if(result >0) {
				conn.commit();
				isDeleted = true;
			}

		} catch (SQLException e) {
			logger.error("DeleteProfCompany method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("DeleteProfCompany method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("DeleteProfCompany method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("DeleteProfCompany method of AdvisorRegistrationDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		}
		
		logger.info("Entered DeleteProfCompany method of AdvisorRegistrationDAO");
		return isDeleted;
	}
	public Boolean DeleteProfDescription(int aId){
		
		logger.info("Entered DeleteProfDescription method of AdvisorRegistrationDAO");
		Boolean isDeleted =false;
		if( aId != 0){
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="DELETE FROM advisorprofdescription WHERE ADVISOR_ID =?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,aId);
			int result = pstmt.executeUpdate(); 
			if(result >0) {
				conn.commit();
				isDeleted = true;
			}

		} catch (SQLException e) {
			logger.error("DeleteProfDescription method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("DeleteProfDescription method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("DeleteProfDescription method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("DeleteProfDescription method of AdvisorRegistrationDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		}
		
		logger.info("Entered DeleteProfDescription method of AdvisorRegistrationDAO");
		return isDeleted;
	}
	
	public List<AdvisorProfileDTO> GetKeySkills(int aId){
		
		logger.info("Entered GetKeySkills method of AdvisorRegistrationDAO");
		List<AdvisorProfileDTO> keyskills = new ArrayList<AdvisorProfileDTO>();
		if( aId != 0){
		
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT * FROM advisorkeyskills WHERE ADVISOR_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,aId);
			ResultSet results = pstmt.executeQuery();
			while(results.next()){
				AdvisorProfileDTO profile = new AdvisorProfileDTO();
				profile.setKeyskill(results.getString("KEY_SKILL"));
				profile.setAdvisorId(results.getInt("ADVISOR_ID"));
				keyskills.add(profile);
			}
		} catch (SQLException e) {
			logger.error("GetKeySkills method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("GetKeySkills method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("GetKeySkills method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetKeySkills method of AdvisorRegistrationDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		}
		
		logger.info("Entered GetKeySkills method of AdvisorRegistrationDAO");
		return keyskills;
	}
	
	public String GetKnowYourAdvisor(int aId){
		logger.info("Entered GetKnowYourAdvisor method of AdvisorRegistrationDAO");
		String knowYourAdvisor = "";
		if( aId != 0){
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT KNOW_YOUR_ADVISOR FROM advisordetails WHERE ADVISOR_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,aId);
			ResultSet results = pstmt.executeQuery();
			if(results.first()){
				knowYourAdvisor = results.getString("KNOW_YOUR_ADVISOR");
			}
		} catch (SQLException e) {
			logger.error("GetKnowYourAdvisor method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("GetKnowYourAdvisor method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("GetKnowYourAdvisor method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetKnowYourAdvisor method of AdvisorRegistrationDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		}
		
		logger.info("Entered GetKnowYourAdvisor method of AdvisorRegistrationDAO");
		return knowYourAdvisor;
	}
	
	
	public AdvisorRegistrationCheckEmailDTO Verify(String aId){
		
		logger.info("Entered Verify method of AdvisorRegistrationDAO");
		AdvisorRegistrationCheckEmailDTO dto = new AdvisorRegistrationCheckEmailDTO();
		if(aId != null && !aId.isEmpty()){
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT REGISTRATION_STATUS,ADVISOR_ID,EMAIL FROM advisordetails WHERE ADVISOR_ID=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1,aId);
			ResultSet results = pstmt.executeQuery();
			if(results.first()){
			dto.setAdvisorId(results.getInt("ADVISOR_ID"));
			dto.setRegistrationStatus(results.getString("REGISTRATION_STATUS"));
			dto.setEmail(results.getString("EMAIL"));
			}
		} catch (SQLException e) {
			logger.error("Verify method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("Verify method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("Verify method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("Verify method of AdvisorRegistrationDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		}
		
		logger.info("Entered checkEmail method of AdvisorRegistrationDAO");
		return dto;
	}
	
	public Integer VerifyEmail(String email){
		
		logger.info("Entered VerifyEmail method of AdvisorRegistrationDAO");
		AdvisorRegistrationCheckEmailDTO dto = new AdvisorRegistrationCheckEmailDTO();
		int aId = 0;
		if(email != null && !email.isEmpty()){
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT ADVISOR_ID FROM advisordetails WHERE EMAIL=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1,email);
			ResultSet results = pstmt.executeQuery();
			if(results.first()){
			aId = results.getInt("ADVISOR_ID");
			}
		} catch (SQLException e) {
			logger.error("VerifyEmail method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("VerifyEmail method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("VerifyEmail method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("VerifyEmail method of AdvisorRegistrationDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		}
		
		logger.info("Entered VerifyEmail method of AdvisorRegistrationDAO");
		return aId;
	}
	
	public Boolean SetExperience(int advisorId,String experience){
		logger.info("Entered SetExperience method of AdvisorRegistrationDAO");
		Boolean isExpCommit = false;
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "UPDATE advisordetails SET EXPERIENCE= ? WHERE ADVISOR_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, experience);
			pstmt.setInt(2, advisorId);
			int result = pstmt.executeUpdate(); 
			if(result >0) {
				conn.commit();
				isExpCommit = true;
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("SetExperience method of AdvisorRegistrationDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}	
			logger.error("SetExperience method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("SetExperience method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("SetExperience method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("SetExperience method of AdvisorRegistrationDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}		
		logger.info("Exit SetExperience method of AdvisorRegistrationDAO");
		return isExpCommit;
	}
	
	public String GetExperience(int aId){
		
		logger.info("Entered GetExperience method of AdvisorRegistrationDAO");
		AdvisorRegistrationCheckEmailDTO dto = new AdvisorRegistrationCheckEmailDTO();
		String experience = "";
		if(aId != 0){
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT EXPERIENCE FROM advisordetails WHERE ADVISOR_ID=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,aId);
			ResultSet results = pstmt.executeQuery();
			if(results.first()){
				experience = results.getString("EXPERIENCE");
			}
		} catch (SQLException e) {
			logger.error("GetExperience method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("GetExperience method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("GetExperience method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetExperience method of AdvisorRegistrationDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		}
		
		logger.info("Entered GetExperience method of AdvisorRegistrationDAO");
		return experience;
	}
	
	public Boolean RemoveAwards(int aId){
		logger.info("Entered RemoveAwards method of AdvisorRegistrationDAO");
		Boolean isDeleted =false;
		if( aId != 0){
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="DELETE FROM advisorachievements WHERE ADVISOR_ID =?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,aId);
			int result = pstmt.executeUpdate(); 
			if(result >0) {
				conn.commit();
				isDeleted = true;
			}

		} catch (SQLException e) {
			logger.error("RemoveAwards method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("RemoveAwards method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("RemoveAwards method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("RemoveAwards method of AdvisorRegistrationDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		}
		
		logger.info("Entered RemoveAwards method of AdvisorRegistrationDAO");
		return isDeleted;
	}
	
	public Boolean RemoveSkills(int aId){
		logger.info("Entered RemoveSkills method of AdvisorRegistrationDAO");
		Boolean isDeleted =false;
		if( aId != 0){
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="DELETE FROM advisorkeyskills WHERE ADVISOR_ID =?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,aId);
			int result = pstmt.executeUpdate(); 
			if(result >0) {
				conn.commit();
				isDeleted = true;
			}

		} catch (SQLException e) {
			logger.error("RemoveSkills method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("RemoveSkills method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("RemoveSkills method of AdvisorRegistrationDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("RemoveSkills method of AdvisorRegistrationDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		}
		
		logger.info("Entered RemoveAwards method of AdvisorRegistrationDAO");
		return isDeleted;
	}
	
	
	private String generateQsForIn(int numQs) {
	    String items = "";
	    for (int i = 0; i < numQs; i++) {
	        if (i != 0) items += ", ";
	        items += "?";
	    }
	    return items;
	}
	private String generateQuery(int prof ){
		String items = "";
		for (int i = 0; i < prof; i++) {
			items+="PROF_ID =? OR ";
		}
		items = items.substring(0, items.length()-3);
		return items;
	}
}
