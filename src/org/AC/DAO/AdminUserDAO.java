/*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 10/12/2014
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
import org.AC.dto.AdvisorProfileDTO;
import org.AC.dto.ContactUsDTO;
import org.AC.dto.HereToHelpDTO;
import org.AC.dto.UserDetailsDTO;
import org.AC.dto.UserReferralDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/********************************
 * CLASS SUMMARY*****************************************************
 * 
 * This class do all the DB Calls for the admin user tab.
 * 
 *
 ***************************************************************************************************/

public class AdminUserDAO {
	private static final Logger logger = Logger
			.getLogger(AdminAdvisorDAO.class);
	Connection conn = null;
	Statement stmt = null;

	/**************************************
	 * COMMENTS*************************************************** This function
	 * will retrieve the user details using email Id.
	 * 
	 * @return :
	 * @param : String email
	 * 
	 *
	 ***************************************************************************************************/

	public UserDetailsDTO GetUserDetails(String email) {

		logger.info("Entered GetUserDetails method of AdminUserDAO");
		ResultSet results = null;
		UserDetailsDTO user = new UserDetailsDTO();
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "SELECT * FROM userdetails WHERE EMAIL = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, email);
			results = pstmt.executeQuery();
			if (results.first()) {
				user.setUserId(results.getInt("USER_ID"));
				user.setEmail(results.getString("EMAIL"));
				user.setFullName(results.getString("FULL_NAME"));
				user.setPhone(results.getString("PHONE_NUMBER"));
				user.setAge(results.getString("AGE"));
				user.setOccupation(results.getString("OCCUPATION"));
				user.setImage(results.getString("IMAGE"));
				user.setDateOfRegistration(results
						.getTimestamp("DATE_OF_REGISTRATION"));
				user.setIsActive(results.getBoolean("ISACTIVE"));
				conn.commit();
			}
			logger.info("Exit GetUserDetails method of AdminUserDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("GetUserDetails method of AdminUserDAO threw error:"
							+ e.getMessage());
					e2.printStackTrace();
				}
				logger.error("GetUserDetails method of AdminUserDAO threw error:"
						+ e.getMessage());
				e1.printStackTrace();
			}
			logger.error("GetUserDetails method of AdminUserDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetUserDetails method of AdminUserDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return user;
	}

	/**************************************
	 * COMMENTS*************************************************** This function
	 * will deactivate the user by setting the isActive flag of the user to
	 * false
	 * 
	 * @return :Boolean isFlagCommit
	 * @param : String uId
	 * 
	 *
	 ***************************************************************************************************/

	public Boolean SetUserFlag(String uId, Boolean isActive) {

		logger.info("Entered SetUserFlag method of AdminUserDAO");
		Boolean isFlagCommit = false;
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "UPDATE userdetails SET ISACTIVE = ? WHERE USER_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1, isActive);
			pstmt.setString(2, uId);
			int result = pstmt.executeUpdate();
			if (result > 0) {
				conn.commit();
				isFlagCommit = true;
			}
			logger.info("Exit SetUserFlag method of AdminUserDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("SetUserFlag method of AdminUserDAO threw error:"
							+ e2.getMessage());
					e2.printStackTrace();
				}
				logger.error("SetUserFlag method of AdminUserDAO threw error:"
						+ e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("SetUserFlag method of AdminUserDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("SetUserFlag method of AdminUserDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return isFlagCommit;
	}

	public List<UserDetailsDTO> GetUsers() {

		logger.info("Entered GetUsers method of AdminUserDAO");
		ResultSet results = null;
		int advisorId = 0;
		Connection conn = null;
		List<UserDetailsDTO> users = new ArrayList<UserDetailsDTO>();
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "SELECT USER_ID,FULL_NAME,EMAIL,PHONE_NUMBER,DATE_OF_REGISTRATION,ISACTIVE FROM userdetails";
			PreparedStatement pstmt = conn.prepareStatement(query);
			results = pstmt.executeQuery();
			while (results.next()) {
				UserDetailsDTO user = new UserDetailsDTO();
				user.setUserId(results.getInt("USER_ID"));
				user.setEmail(results.getString("EMAIL"));
				user.setFullName(results.getString("FULL_NAME"));
				user.setPhone(results.getString("PHONE_NUMBER"));
				user.setDateOfRegistration(results
						.getTimestamp("DATE_OF_REGISTRATION"));
				user.setIsActive(results.getBoolean("ISACTIVE"));
				users.add(user);
			}
			conn.commit();
			logger.info("Exit GetAdvisors method of AdminUserDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("GetUsers method of AdminUserDAO threw error:"
							+ e2.getMessage());
					e2.printStackTrace();
				}
				logger.error("GetUsers method of AdminUserDAO threw error:"
						+ e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("GetUsers method of AdminUserDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetUsers method of UserForgotPasswordDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return users;
	}

	public List<ContactUsDTO> GetContactUsDetails() {
		logger.info("Entered GetUserDetails method of AdminUserDAO");
		ResultSet results = null;
		List<ContactUsDTO> contactList = new ArrayList<ContactUsDTO>();
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "SELECT * FROM contactus";
			PreparedStatement pstmt = conn.prepareStatement(query);
			results = pstmt.executeQuery();
			while (results.next()) {
				ContactUsDTO contact = new ContactUsDTO();
				contact.setId(results.getInt("ID"));
				contact.setName(results.getString("NAME"));
				contact.setEmail(results.getString("EMAIL"));
				contact.setPhone(results.getString("PHONE_NUMBER"));
				contact.setMessage(results.getString("MESSAGE"));
				contactList.add(contact);
			}
			logger.info("Exit GetUserDetails method of AdminUserDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("GetUserDetails method of AdminUserDAO threw error:"
							+ e.getMessage());
					e2.printStackTrace();
				}
				logger.error("GetUserDetails method of AdminUserDAO threw error:"
						+ e.getMessage());
				e1.printStackTrace();
			}
			logger.error("GetUserDetails method of AdminUserDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetUserDetails method of AdminUserDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return contactList;
	}

	public List<HereToHelpDTO> GetHereToHelpDetails() {
		logger.info("Entered GetHereToHelpDetails method of AdminUserDAO");
		ResultSet results = null;
		List<HereToHelpDTO> list = new ArrayList<HereToHelpDTO>();
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "SELECT * FROM heretohelp";
			PreparedStatement pstmt = conn.prepareStatement(query);
			results = pstmt.executeQuery();
			while (results.next()) {
				HereToHelpDTO here = new HereToHelpDTO();
				here.setHelpId(results.getInt("HELP_ID"));
				here.setEmail(results.getString("EMAIL"));
				here.setPhone(results.getString("PHONE"));
				list.add(here);
			}

			logger.info("Exit GetHereToHelpDetails method of AdminUserDAO");
		} catch (Exception e) {
			logger.error("GetHereToHelpDetails method of AdminUserDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetHereToHelpDetails method of AdminUserDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return list;
	}

	public UserDetailsDTO GetUserDetails(int userId) {

		logger.info("Entered GetUserDetails method of AdminUserDAO");
		ResultSet results = null;
		UserDetailsDTO user = new UserDetailsDTO();
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "SELECT * FROM userdetails WHERE USER_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, userId);
			results = pstmt.executeQuery();
			if (results.first()) {
				user.setUserId(results.getInt("USER_ID"));
				user.setEmail(results.getString("EMAIL"));
				user.setFullName(results.getString("FULL_NAME"));
				user.setPhone(results.getString("PHONE_NUMBER"));
				user.setAge(results.getString("AGE"));
				user.setOccupation(results.getString("OCCUPATION"));
				user.setImage(results.getString("IMAGE"));
				user.setDateOfRegistration(results
						.getTimestamp("DATE_OF_REGISTRATION"));
				user.setIsActive(results.getBoolean("ISACTIVE"));
				conn.commit();
			}
			logger.info("Exit GetUserDetails method of AdminUserDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("GetUserDetails method of AdminUserDAO threw error:"
							+ e.getMessage());
					e2.printStackTrace();
				}
				logger.error("GetUserDetails method of AdminUserDAO threw error:"
						+ e.getMessage());
				e1.printStackTrace();
			}
			logger.error("GetUserDetails method of AdminUserDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetUserDetails method of AdminUserDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return user;
	}
	
	public List<UserReferralDTO> GetReferral() {

		logger.info("Entered GetReferral method of AdminUserDAO");
		ResultSet results = null;
		List<UserReferralDTO> ref = new ArrayList<UserReferralDTO>();
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "SELECT * FROM user_referral";
			PreparedStatement pstmt = conn.prepareStatement(query);
			results = pstmt.executeQuery();
			while(results.next()){
				UserReferralDTO user = new UserReferralDTO();
				user.setUserId(results.getInt("USER_ID"));
				user.setRefCode(results.getString("REFERRAL_CODE"));
				user.setRefCount(results.getInt("REFERRAL_COUNT"));
				user.setRefMessage(results.getBoolean("REFERRAL_MESSAGE"));
				ref.add(user);
			}
			logger.info("Exit GetReferral method of AdminUserDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("GetReferral method of AdminUserDAO threw error:"
							+ e.getMessage());
					e2.printStackTrace();
				}
				logger.error("GetReferral method of AdminUserDAO threw error:"
						+ e.getMessage());
				e1.printStackTrace();
			}
			logger.error("GetReferral method of AdminUserDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetReferral method of AdminUserDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return ref;
	}
	
	public Boolean UpdateReferralCount(String uId, String count) {

		logger.info("Entered UpdateReferralCount method of AdminUserDAO");
		Boolean isFlagCommit = false;
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "UPDATE user_referral SET REFERRAL_COUNT = ? WHERE USER_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, count);
			pstmt.setString(2, uId);
			int result = pstmt.executeUpdate();
			if (result > 0) {
				conn.commit();
				isFlagCommit = true;
			}
			logger.info("Exit UpdateReferralCount method of AdminUserDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("UpdateReferralCount method of AdminUserDAO threw error:"
							+ e2.getMessage());
					e2.printStackTrace();
				}
				logger.error("UpdateReferralCount method of AdminUserDAO threw error:"
						+ e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("UpdateReferralCount method of AdminUserDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("UpdateReferralCount method of AdminUserDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return isFlagCommit;
	}
	
	public Boolean ToggleRefMessage(String uId, Boolean val) {
		logger.info("Entered ToggleRefMessage method of AdminUserDAO");
		Boolean isFlagCommit = false;
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "UPDATE user_referral SET REFERRAL_MESSAGE = ? WHERE USER_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1, val);
			pstmt.setString(2, uId);
			int result = pstmt.executeUpdate();
			if (result > 0) {
				conn.commit();
				isFlagCommit = true;
			}
			logger.info("Exit ToggleRefMessage method of AdminUserDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("ToggleRefMessage method of AdminUserDAO threw error:"
							+ e2.getMessage());
					e2.printStackTrace();
				}
				logger.error("ToggleRefMessage method of AdminUserDAO threw error:"
						+ e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("ToggleRefMessage method of AdminUserDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("ToggleRefMessage method of AdminUserDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return isFlagCommit;
	}

}
