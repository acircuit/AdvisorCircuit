 /*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 08/12/2014
 * ************************************************************************************************/
package org.AC.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.AC.JDBC.ConnectionFactory;
import org.AC.JDBC.Util;
import org.AC.controller.AdvisorProfileServlet;
import org.AC.dto.AdvisorModeDTO;
import org.AC.dto.AdvisorProfileDTO;
import org.AC.dto.AdvisorServiceDTO;
import org.AC.dto.ProfessionalBackgroundDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/********************************CLASS SUMMARY*****************************************************
* 
* This class do all the DB Calls for the admin advisor tab.
* 
*
***************************************************************************************************/

public class AdminAdvisorDAO {
	private static final Logger logger = Logger.getLogger(AdminAdvisorDAO.class);       
	Connection conn = null;
	Statement stmt = null;
	
	/**************************************COMMENTS***************************************************
	 * This function will retrieve the Advisor Id from the email Id
	 *   
	 *   @return :ResultSet results
	 *   @param : String email
	 *   		  
	 *
	 ***************************************************************************************************/
	
	public int  GetAdvisorId(String email) { 
		
		logger.info("Entered GetAdvisorId method of AdminAdvisorDAO");
		ResultSet results = null;
		int advisorId = 0 ;
		Connection conn = null;
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT ADVISOR_ID,NAME FROM advisordetails WHERE EMAIL = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1,email);
		    results = pstmt.executeQuery();
		    if(results.first()){
		    	conn.commit();
		    	advisorId = results.getInt("ADVISOR_ID");
		    }
		logger.info("Exit setForgotPasswordDetails method of AdvisorLoginDAO");
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("setForgotPasswordDetails method of AdvisorLoginDAO threw error:"+e.getMessage());
					e2.printStackTrace();
				}
				logger.error("setForgotPasswordDetails method of UserForgotPasswordDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}
			logger.error("setForgotPasswordDetails method of UserForgotPasswordDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("setForgotPasswordDetails method of UserForgotPasswordDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		return advisorId;
	}
	
	
	/**************************************COMMENTS***************************************************
	 * This function will deactivate the advisor by setting the isActive flag of the advisor to false  
	 *   
	 *   @return :Boolean isFlagCommit
	 *   @param : String aId
	 *   		  
	 *
	 ***************************************************************************************************/
	
	public Boolean  SetAdvisorFlag(String aId , Boolean flag) { 
		
		
		logger.info("Entered SetAdvisorFlag method of AdminAdvisorDAO");
		Boolean isFlagCommit = false ;
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="UPDATE advisordetails SET ISACTIVE = ? WHERE ADVISOR_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1, flag);
			pstmt.setString(2,aId);
			int result = pstmt.executeUpdate(); 
			if(result >0) {
				conn.commit();
				isFlagCommit = true;
			}
		logger.info("Exit SetAdvisorFlag method of AdminAdvisorDAO");
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("SetAdvisorFlag method of AdminAdvisorDAO threw error:"+e2.getMessage());
					e2.printStackTrace();
				}
				logger.error("SetAdvisorFlag method of AdminAdvisorDAO threw error:"+e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("SetAdvisorFlag method of AdminAdvisorDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("SetAdvisorFlag method of AdminAdvisorDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		return isFlagCommit;
	}
	public List<AdvisorProfileDTO>  GetAdvisors() { 
		
		logger.info("Entered GetAdvisors method of AdminAdvisorDAO");
		ResultSet results = null;
		int advisorId = 0 ;
		Connection conn = null;
		List<AdvisorProfileDTO> advisors = new ArrayList<AdvisorProfileDTO>();
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT ADVISOR_ID,NAME,EMAIL,PHONE_NUMBER,ISACTIVE FROM advisordetails";
			PreparedStatement pstmt = conn.prepareStatement(query);
		    results = pstmt.executeQuery();
		    while(results.next()){
		    	AdvisorProfileDTO advisor = new AdvisorProfileDTO();
		    	advisor.setAdvisorId(results.getInt("ADVISOR_ID"));
		    	advisor.setName(results.getString("NAME"));
		    	advisor.setEmail(results.getString("EMAIL"));
		    	advisor.setPhone(results.getString("PHONE_NUMBER"));
		    	advisor.setIsActive(results.getBoolean("ISACTIVE"));
		    	advisors.add(advisor);
		    }
	    	conn.commit();
		logger.info("Exit GetAdvisors method of AdvisorLoginDAO");
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("GetAdvisors method of AdvisorLoginDAO threw error:"+e.getMessage());
					e2.printStackTrace();
				}
				logger.error("GetAdvisors method of UserForgotPasswordDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}
			logger.error("GetAdvisors method of UserForgotPasswordDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetAdvisors method of UserForgotPasswordDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		return advisors;
	}
	
	public List<AdvisorProfileDTO>  GetAdvisorsFromRegistration() { 
		
		logger.info("Entered GetAdvisorsFromRegistration method of AdminAdvisorDAO");
		ResultSet results = null;
		int advisorId = 0 ;
		Connection conn = null;
		List<AdvisorProfileDTO> advisors = new ArrayList<AdvisorProfileDTO>();
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT ADVISOR_ID,NAME,EMAIL,PHONE_NUMBER,REGISTRATION_STATUS FROM advisordetails";
			PreparedStatement pstmt = conn.prepareStatement(query);
		    results = pstmt.executeQuery();
		    while(results.next()){
		    	AdvisorProfileDTO advisor = new AdvisorProfileDTO();
		    	advisor.setAdvisorId(results.getInt("ADVISOR_ID"));
		    	advisor.setName(results.getString("NAME"));
		    	advisor.setEmail(results.getString("EMAIL"));
		    	advisor.setPhone(results.getString("PHONE_NUMBER"));
		    	advisor.setStatus(results.getString("REGISTRATION_STATUS"));
		    	advisors.add(advisor);
		    }
		logger.info("Exit GetAdvisorsFromRegistration method of AdminAdvisorDAO");
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("GetAdvisorsFromRegistration method of AdminAdvisorDAO threw error:"+e.getMessage());
					e2.printStackTrace();
				}
				logger.error("GetAdvisorsFromRegistration method of AdminAdvisorDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}
			logger.error("GetAdvisorsFromRegistration method of AdminAdvisorDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetAdvisorsFromRegistration method of AdminAdvisorDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		return advisors;
	}
	
	public AdvisorProfileDTO  GetAdvisorProfile(String aId) { 
		
		logger.info("Entered GetAdvisorProfile method of AdminAdvisorDAO");
		ResultSet results = null;
		int advisorId = 0 ;
		Connection conn = null;
    	AdvisorProfileDTO advisor = new AdvisorProfileDTO();
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT * FROM advisorregistrationprofiledetails WHERE ADVISOR_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, aId);
		    results = pstmt.executeQuery();
		    if(results.first()){
		    	advisor.setAdvisorId(results.getInt("ADVISOR_ID"));
		    	advisor.setName(results.getString("NAME"));
		    	advisor.setGender(results.getString("GENDER"));
		    	advisor.setAge(results.getString("AGE"));
		    	advisor.setEmail(results.getString("EMAIL"));
		    	advisor.setPhone(results.getString("PHONE_NUMBER"));
		    	advisor.setImage(results.getString("IMAGE"));
		    	advisor.setCity(results.getString("CITY"));
		    	advisor.setState(results.getString("STATE"));
		    	advisor.setNationalty(results.getString("NATIONALITY"));
		    	advisor.setIndustry(results.getString("INDUSTRY"));
		    	advisor.setIntroduction(results.getString("INTRODUCTION"));
		    	advisor.setKnowYourAdvisor(results.getString("KNOW_YOUR_ADVISOR"));
		    	advisor.setStatus(results.getString("REGISTRATION_STATUS"));
		    }
		logger.info("Exit GetAdvisorProfile method of AdminAdvisorDAO");
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("GetAdvisorProfile method of AdminAdvisorDAO threw error:"+e.getMessage());
					e2.printStackTrace();
				}
				logger.error("GetAdvisorProfile method of AdminAdvisorDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}
			logger.error("GetAdvisorProfile method of AdminAdvisorDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetAdvisorProfile method of AdminAdvisorDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		return advisor;
	}
	
	public List<ProfessionalBackgroundDTO>  GetProfessionalBackground(String aId) { 
		
		logger.info("Entered GetProfessionalBackground method of AdminAdvisorDAO");
		ResultSet results = null;
		int advisorId = 0 ;
		Connection conn = null;
		List<ProfessionalBackgroundDTO> advisors = new ArrayList<ProfessionalBackgroundDTO>();
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT * FROM advisorprofessionalbackground WHERE ADVISOR_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, aId);
		    results = pstmt.executeQuery();
		    while(results.next()){
		    	ProfessionalBackgroundDTO advisor = new ProfessionalBackgroundDTO();
		    	advisor.setProfId(results.getInt("PROF_ID"));
		    	advisor.setAdvisorId(results.getInt("ADVISOR_ID"));
		    	advisor.setCompany(results.getString("COMPANY"));
		    	advisor.setDescription(results.getString("DESCRIPTION"));
		    	advisor.setDesignation(results.getString("DESIGNATION"));
		    	advisors.add(advisor);
		    }
		logger.info("Exit GetProfessionalBackground method of AdminAdvisorDAO");
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("GetProfessionalBackground method of AdminAdvisorDAO threw error:"+e.getMessage());
					e2.printStackTrace();
				}
				logger.error("GetProfessionalBackground method of AdminAdvisorDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}
			logger.error("GetProfessionalBackground method of AdminAdvisorDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetProfessionalBackground method of AdminAdvisorDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		return advisors;
	}
	
	public List<AdvisorServiceDTO>  GetServices(String aId) { 
		
		logger.info("Entered GetServices method of AdminAdvisorDAO");
		ResultSet results = null;
		Connection conn = null;
		List<AdvisorServiceDTO> advisors = new ArrayList<AdvisorServiceDTO>();
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT ADVISOR_ID,SERVICE,DESCRIPTION FROM advisorservices WHERE ADVISOR_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, aId);
		    results = pstmt.executeQuery();
		    while(results.next()){
		    	AdvisorServiceDTO advisor = new AdvisorServiceDTO();
		    	advisor.setAdvisorId(results.getInt("ADVISOR_ID"));
		    	advisor.setService(results.getString("SERVICE"));
		    	advisor.setDescription(results.getString("DESCRIPTION"));
		    	advisors.add(advisor);
		    }
		logger.info("Exit GetServices method of AdminAdvisorDAO");
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("GetServices method of AdminAdvisorDAO threw error:"+e.getMessage());
					e2.printStackTrace();
				}
				logger.error("GetServices method of AdminAdvisorDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}
			logger.error("GetServices method of AdminAdvisorDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetServices method of AdminAdvisorDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		return advisors;
	}
	public List<AdvisorModeDTO>  GetModes(String aId) { 
		
		logger.info("Entered GetModes method of AdminAdvisorDAO");
		ResultSet results = null;
		Connection conn = null;
		List<AdvisorModeDTO> advisors = new ArrayList<AdvisorModeDTO>();
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT * FROM advisormodes WHERE ADVISOR_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, aId);
		    results = pstmt.executeQuery();
		    while(results.next()){
		    	AdvisorModeDTO advisor = new AdvisorModeDTO();
		    	advisor.setAdvisorId(results.getInt("ADVISOR_ID"));
		    	advisor.setModeOfCommunication(results.getString("MODE_OF_COMMUNICATION"));
		    	advisor.setService(results.getString("SERVICE"));
		    	advisor.setPrice(results.getString("PRICE"));
		    	advisors.add(advisor);
		    }
		logger.info("Exit GetModes method of AdminAdvisorDAO");
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("GetModes method of AdminAdvisorDAO threw error:"+e.getMessage());
					e2.printStackTrace();
				}
				logger.error("GetModes method of AdminAdvisorDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}
			logger.error("GetModes method of AdminAdvisorDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetModes method of AdminAdvisorDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		return advisors;
	}
	
	public List<ProfessionalBackgroundDTO>  GetProfessionalDescription(String aId) { 
		logger.info("Entered GetProfessionalBackground method of AdminAdvisorDAO");
		ResultSet results = null;
		Connection conn = null;
		List<ProfessionalBackgroundDTO> advisors = new ArrayList<ProfessionalBackgroundDTO>();
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT * FROM advisorprofdescription WHERE ADVISOR_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, aId);
		    results = pstmt.executeQuery();
		    while(results.next()){
		    	ProfessionalBackgroundDTO advisor = new ProfessionalBackgroundDTO();
		    	advisor.setProfId(results.getInt("PROF_ID"));
		    	advisor.setDescription(results.getString("DESCRIPTION"));
		    	advisors.add(advisor);
		    }
		logger.info("Exit GetProfessionalBackground method of AdminAdvisorDAO");
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("GetProfessionalBackground method of AdminAdvisorDAO threw error:"+e.getMessage());
					e2.printStackTrace();
				}
				logger.error("GetProfessionalBackground method of AdminAdvisorDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}
			logger.error("GetProfessionalBackground method of AdminAdvisorDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetProfessionalBackground method of AdminAdvisorDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		return advisors;
	}
}
