package org.AC.DAO;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;

import org.AC.JDBC.ConnectionFactory;
import org.AC.dto.AdvisorRegistrationCheckEmailDTO;
import org.AC.dto.SessionFeedBackDTO;
import org.apache.log4j.Logger;

public class SessionFeedBackDAO {
	private static final Logger logger = Logger.getLogger(SessionFeedBackDAO.class);
	Connection conn = null;
	
	public Boolean SetUserFeedBack(String sId, String body, String subject,String path){
		logger.info("Entered SetUserFeedBack method of SessionFeedBackDAO");
		Boolean isFeedbackCommit = false;
					try {
						conn =ConnectionFactory.getConnection();
						conn.setAutoCommit(false);
						String query = "insert into sessionfeedback"+"(SESSION_ID,USER_BODY,USER_SUBJECT,USER_FILE) values" + "(?, ?, ?,?)";
						PreparedStatement pstmt = conn.prepareStatement(query);
						pstmt.setString(1, sId);
						pstmt.setString(2,body);
						pstmt.setString(3,subject);
						pstmt.setString(4,path);
						int result = pstmt.executeUpdate();
						if(result > 0) {
							conn.commit();
							isFeedbackCommit = true;
						}
					} catch (SQLException e) {
						try {
							conn.rollback();
						} catch (SQLException e1) {
							logger.error("SetUserFeedBack method of SessionFeedBackDAO threw error:"+e.getMessage());
							e1.printStackTrace();
						}
						logger.error("SetUserFeedBack method of SessionFeedBackDAO threw error:"+e.getMessage());
						e.printStackTrace();
					} catch (IOException e) {
						logger.error("SetUserFeedBack method of SessionFeedBackDAO threw error:"+e.getMessage());
						e.printStackTrace();
					} catch (PropertyVetoException e) {
						logger.error("SetUserFeedBack method of SessionFeedBackDAO threw error:"+e.getMessage());
						e.printStackTrace();
					}finally{
						try {
							conn.close();
						} catch (SQLException e) {
							logger.error("SetUserFeedBack method of SessionFeedBackDAO threw error:"+e.getMessage());
							e.printStackTrace();
						}
					}	
			logger.info("Entered SetUserFeedBack method of SessionFeedBackDAO");
			return isFeedbackCommit;

	}
	public Boolean CheckFeedBack(String sId){
		logger.info("Entered CheckFeedBack method of SessionFeedBackDAO");
		Boolean isFeedBackGiven = false;
		if(!sId.isEmpty()){
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT ID FROM sessionfeedback WHERE SESSION_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1,sId);
			ResultSet results = pstmt.executeQuery();
			if(results.first()){
				isFeedBackGiven = true;
			}
		} catch (SQLException e) {
			logger.error("CheckFeedBack method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("CheckFeedBack method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("CheckFeedBack method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("CheckFeedBack method of SessionFeedBackDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		}
		
		logger.info("Exit CheckFeedBack method of SessionFeedBackDAO");
		return isFeedBackGiven;
	}
	public SessionFeedBackDTO GetFeedBack(int sId){
		logger.info("Entered GetFeedBack method of SessionFeedBackDAO");
		Boolean isFeedBackGiven = false;
		SessionFeedBackDTO feed = new SessionFeedBackDTO();
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT * FROM sessionfeedback WHERE SESSION_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,sId);
			ResultSet results = pstmt.executeQuery();
			if(results.first()){
				feed.setId(results.getInt("ID"));
				feed.setSessionId(results.getInt("SESSION_ID"));
				feed.setUserBody(results.getString("USER_BODY"));
				feed.setUserSubject(results.getString("USER_SUBJECT"));
				feed.setUserFile(results.getString("USER_FILE"));
				feed.setAdvisorBody(results.getString("ADVISOR_BODY"));
				feed.setAdvisorSubject(results.getString("ADVISOR_SUBJECT"));
				feed.setAdvisorFile(results.getString("ADVISOR_FILE"));
				feed.setUserApproval(results.getBoolean("USER_APPROVAL"));
				feed.setAdvisorApproval(results.getBoolean("ADVISOR_APPROVAL"));
			}
		} catch (SQLException e) {
			logger.error("GetFeedBack method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("GetFeedBack method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("GetFeedBack method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetFeedBack method of SessionFeedBackDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		
		logger.info("Exit GetFeedBack method of SessionFeedBackDAO");
		return feed;
	}
	
	public Boolean SetFeedBackStatus(String sId, String type){
		logger.info("Entered SetFeedBackStatus method of SessionFeedBackDAO");
		String status = "EducationInfo.jsp"; 
		Boolean isStatusCommit = false;
		String query = "";
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			if(type.equals("user")){
				query = "UPDATE sessionfeedback  SET USER_APPROVAL=? WHERE SESSION_ID = ?";
			}else if (type.equals("advisor")) {
				query = "UPDATE sessionfeedback  SET ADVISOR_APPROVAL=? WHERE SESSION_ID = ?";
			}
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1, true);
			pstmt.setString(2,sId);
			int result = pstmt.executeUpdate(); 
			if(result >0) {
				conn.commit();
				isStatusCommit = true;
				}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("SetFeedBackStatus method of SessionFeedBackDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}	
			logger.error("SetFeedBackStatus method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("SetFeedBackStatus method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("SetFeedBackStatus method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("SetFeedBackStatus method of SessionFeedBackDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}	
		logger.info("Entered SetFeedBackStatus method of SessionFeedBackDAO");
		return isStatusCommit;
		}
	
	
	public Boolean SetFeedBackStatusRejected(String sId, String type){
		logger.info("Entered SetFeedBackStatusRejected method of SessionFeedBackDAO");
		Boolean isStatusCommit = false;
		String query = "";
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			if(type.equals("user")){
				query = "UPDATE sessionfeedback  SET USER_APPROVAL=? WHERE SESSION_ID = ?";
			}else if (type.equals("advisor")) {
				query = "UPDATE sessionfeedback  SET ADVISOR_APPROVAL=? WHERE SESSION_ID = ?";
			}
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1, false);
			pstmt.setString(2,sId);
			int result = pstmt.executeUpdate(); 
			if(result >0) {
				conn.commit();
				isStatusCommit = true;
				}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("SetFeedBackStatusRejected method of SessionFeedBackDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}	
			logger.error("SetFeedBackStatusRejected method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("SetFeedBackStatusRejected method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("SetFeedBackStatusRejected method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("SetFeedBackStatusRejected method of SessionFeedBackDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}	
		logger.info("Entered SetFeedBackStatusRejected method of SessionFeedBackDAO");
		return isStatusCommit;
		}
	
	public SessionFeedBackDTO GetUserFeedBackDetailsForAdvisor(int sId){
		logger.info("Entered GetUserFeedBackDetailsForAdvisor method of SessionFeedBackDAO");
		Boolean isFeedBackGiven = false;
		SessionFeedBackDTO feed = new SessionFeedBackDTO();
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT * FROM sessionfeedback WHERE SESSION_ID = ? AND USER_APPROVAL =?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,sId);
			pstmt.setBoolean(2, true);
			ResultSet results = pstmt.executeQuery();
			if(results.first()){
				feed.setId(results.getInt("ID"));
				feed.setSessionId(results.getInt("SESSION_ID"));
				feed.setUserBody(results.getString("USER_BODY"));
				feed.setUserSubject(results.getString("USER_SUBJECT"));
				feed.setUserFile(results.getString("USER_FILE"));
				feed.setUserApproval(results.getBoolean("USER_APPROVAL"));
			}
		} catch (SQLException e) {
			logger.error("GetUserFeedBackDetailsForAdvisor method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("GetUserFeedBackDetailsForAdvisor method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("GetUserFeedBackDetailsForAdvisor method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetUserFeedBackDetailsForAdvisor method of SessionFeedBackDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		logger.info("Exit GetUserFeedBackDetailsForAdvisor method of SessionFeedBackDAO");
		return feed;
	}
	
	public SessionFeedBackDTO GetAdvisorFeedbackDetailsForAdvisor(int sId){
		logger.info("Entered GetAdvisorFeedbackDetailsForAdvisor method of SessionFeedBackDAO");
		Boolean isFeedBackGiven = false;
		SessionFeedBackDTO feed = new SessionFeedBackDTO();
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT * FROM sessionfeedback WHERE SESSION_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,sId);
			ResultSet results = pstmt.executeQuery();
			if(results.first()){
				feed.setId(results.getInt("ID"));
				feed.setSessionId(results.getInt("SESSION_ID"));
				feed.setAdvisorBody(results.getString("ADVISOR_BODY"));
				feed.setAdvisorSubject(results.getString("ADVISOR_SUBJECT"));
				feed.setAdvisorFile(results.getString("ADVISOR_FILE"));
				feed.setAdvisorApproval(results.getBoolean("ADVISOR_APPROVAL"));
			}
		} catch (SQLException e) {
			logger.error("GetAdvisorFeedbackDetailsForAdvisor method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("GetAdvisorFeedbackDetailsForAdvisor method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("GetAdvisorFeedbackDetailsForAdvisor method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetAdvisorFeedbackDetailsForAdvisor method of SessionFeedBackDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		logger.info("Exit GetAdvisorFeedbackDetailsForAdvisor method of SessionFeedBackDAO");
		return feed;
	}
	
	public Boolean CheckAdvisorFeedBack(String sId){
		logger.info("Entered CheckAdvisorFeedBack method of SessionFeedBackDAO");
		Boolean isFeedBackGiven = false;
		if(!sId.isEmpty()){
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT ADVISOR_BODY FROM sessionfeedback WHERE SESSION_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1,sId);
			ResultSet results = pstmt.executeQuery();
			if(results.first() && results.getString("ADVISOR_BODY") != null){
				isFeedBackGiven = true;
			}
		} catch (SQLException e) {
			logger.error("CheckAdvisorFeedBack method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("CheckAdvisorFeedBack method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("CheckAdvisorFeedBack method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("CheckAdvisorFeedBack method of SessionFeedBackDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		}
		
		logger.info("Exit CheckAdvisorFeedBack method of SessionFeedBackDAO");
		return isFeedBackGiven;
	}
	public Boolean SetAdvisorFeedBack(String sId, String body, String subject,String path){
		logger.info("Entered SetAdvisorFeedBack method of SessionFeedBackDAO");
		Boolean isFeedbackCommit = false;
					try {
						conn =ConnectionFactory.getConnection();
						conn.setAutoCommit(false);
						String query = "UPDATE sessionfeedback  SET ADVISOR_BODY=?,ADVISOR_SUBJECT=?,ADVISOR_FILE=? WHERE SESSION_ID = ?";
						PreparedStatement pstmt = conn.prepareStatement(query);
						pstmt.setString(1,body);
						pstmt.setString(2,subject);
						pstmt.setString(3,path);
						pstmt.setString(4,sId);
						int result = pstmt.executeUpdate();
						if(result > 0) {
							conn.commit();
							isFeedbackCommit = true;
						}
					} catch (SQLException e) {
						try {
							conn.rollback();
						} catch (SQLException e1) {
							logger.error("SetAdvisorFeedBack method of SessionFeedBackDAO threw error:"+e.getMessage());
							e1.printStackTrace();
						}
						logger.error("SetAdvisorFeedBack method of SessionFeedBackDAO threw error:"+e.getMessage());
						e.printStackTrace();
					} catch (IOException e) {
						logger.error("SetAdvisorFeedBack method of SessionFeedBackDAO threw error:"+e.getMessage());
						e.printStackTrace();
					} catch (PropertyVetoException e) {
						logger.error("SetAdvisorFeedBack method of SessionFeedBackDAO threw error:"+e.getMessage());
						e.printStackTrace();
					}finally{
						try {
							conn.close();
						} catch (SQLException e) {
							logger.error("SetAdvisorFeedBack method of SessionFeedBackDAO threw error:"+e.getMessage());
							e.printStackTrace();
						}
					}	
			logger.info("Entered SetAdvisorFeedBack method of SessionFeedBackDAO");
			return isFeedbackCommit;

	}
	public SessionFeedBackDTO GetUserFeedBackDetailsForUser(int sId){
		logger.info("Entered GetUserFeedBackDetailsForUser method of SessionFeedBackDAO");
		Boolean isFeedBackGiven = false;
		SessionFeedBackDTO feed = new SessionFeedBackDTO();
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT * FROM sessionfeedback WHERE SESSION_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,sId);
			ResultSet results = pstmt.executeQuery();
			if(results.first()){
				feed.setId(results.getInt("ID"));
				feed.setSessionId(results.getInt("SESSION_ID"));
				feed.setUserBody(results.getString("USER_BODY"));
				feed.setUserSubject(results.getString("USER_SUBJECT"));
				feed.setUserFile(results.getString("USER_FILE"));
				feed.setUserApproval(results.getBoolean("USER_APPROVAL"));
			}
		} catch (SQLException e) {
			logger.error("GetUserFeedBackDetailsForUser method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("GetUserFeedBackDetailsForUser method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("GetUserFeedBackDetailsForUser method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetUserFeedBackDetailsForUser method of SessionFeedBackDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		logger.info("Exit GetUserFeedBackDetailsForUser method of SessionFeedBackDAO");
		return feed;
	}
	public SessionFeedBackDTO GetAdvisorFeedBackDetailsForUser(int sId){
		logger.info("Entered GetAdvisorFeedBackDetailsForUser method of SessionFeedBackDAO");
		Boolean isFeedBackGiven = false;
		SessionFeedBackDTO feed = new SessionFeedBackDTO();
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT * FROM sessionfeedback WHERE SESSION_ID = ? AND ADVISOR_APPROVAL=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,sId);
			pstmt.setBoolean(2, true);
			ResultSet results = pstmt.executeQuery();
			if(results.first()){
				feed.setId(results.getInt("ID"));
				feed.setSessionId(results.getInt("SESSION_ID"));
				feed.setAdvisorBody(results.getString("ADVISOR_BODY"));
				feed.setAdvisorSubject(results.getString("ADVISOR_SUBJECT"));
				feed.setAdvisorFile(results.getString("ADVISOR_FILE"));
			}
		} catch (SQLException e) {
			logger.error("GetAdvisorFeedBackDetailsForUser method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("GetAdvisorFeedBackDetailsForUser method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("GetAdvisorFeedBackDetailsForUser method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetAdvisorFeedBackDetailsForUser method of SessionFeedBackDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		logger.info("Exit GetAdvisorFeedBackDetailsForUser method of SessionFeedBackDAO");
		return feed;
	}
	
	public Boolean CheckForVisibility(int sId){
		logger.info("Entered CheckForVisibility method of SessionFeedBackDAO");
		Boolean isApproved = false;
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT * FROM session_table WHERE SESSION_ID = ? AND SHOW_FEEDBACK=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,sId);
			pstmt.setBoolean(2, true);
			ResultSet results = pstmt.executeQuery();
			if(results.next() && results.getInt("SESSION_ID") != 0){
				System.out.println(results.getInt("SESSION_ID"));
				isApproved = true;
			}
		} catch (SQLException e) {
			logger.error("CheckForVisibility method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("CheckForVisibility method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("CheckForVisibility method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("CheckForVisibility method of SessionFeedBackDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		
		logger.info("Exit CheckForVisibility method of SessionFeedBackDAO");
		return isApproved;
	}
	public Boolean SessionFeedback(String sId,Boolean action){
		logger.info("Entered SessionFeedback method of SessionFeedBackDAO");
		Boolean isFeedbackCommit = false;
				try {
					conn =ConnectionFactory.getConnection();
					conn.setAutoCommit(false);
					String query = "UPDATE session_table  SET SHOW_FEEDBACK=? WHERE SESSION_ID = ?";
					PreparedStatement pstmt = conn.prepareStatement(query);
					if(action){
						pstmt.setBoolean(1,true);
					}else{
						pstmt.setBoolean(1,false);
					}
					pstmt.setString(2,sId);
					int result = pstmt.executeUpdate();
					if(result > 0) {
						conn.commit();
						isFeedbackCommit = true;
					}
				} catch (SQLException e) {
					try {
						conn.rollback();
					} catch (SQLException e1) {
						logger.error("SessionFeedback method of SessionFeedBackDAO threw error:"+e.getMessage());
						e1.printStackTrace();
					}
					logger.error("SessionFeedback method of SessionFeedBackDAO threw error:"+e.getMessage());
					e.printStackTrace();
				} catch (IOException e) {
					logger.error("SessionFeedback method of SessionFeedBackDAO threw error:"+e.getMessage());
					e.printStackTrace();
				} catch (PropertyVetoException e) {
					logger.error("SessionFeedback method of SessionFeedBackDAO threw error:"+e.getMessage());
					e.printStackTrace();
				}finally{
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("SessionFeedback method of SessionFeedBackDAO threw error:"+e.getMessage());
						e.printStackTrace();
					}
				}	
			logger.info("Entered SessionFeedback method of SessionFeedBackDAO");
			return isFeedbackCommit;

	}
	public Boolean SetUserFeedBackAgain(String sId, String body, String subject, String fileURL){
		logger.info("Entered SetUserFeedBackAgain method of SessionFeedBackDAO");
		Boolean isStatusCommit = false;
		String query = "";
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			query = "UPDATE sessionfeedback  SET USER_BODY=?,USER_SUBJECT=?,USER_FILE=? WHERE SESSION_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, body);
			pstmt.setString(2, subject);
			pstmt.setString(3, fileURL);
			pstmt.setString(4,sId);
			int result = pstmt.executeUpdate(); 
			if(result >0) {
				conn.commit();
				isStatusCommit = true;
				}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("SetUserFeedBackAgain method of SessionFeedBackDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}	
			logger.error("SetUserFeedBackAgain method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("SetUserFeedBackAgain method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("SetUserFeedBackAgain method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("SetUserFeedBackAgain method of SessionFeedBackDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}	
		logger.info("Entered SetUserFeedBackAgain method of SessionFeedBackDAO");
		return isStatusCommit;
		}
	
	public Boolean SetUserEmail(String sId, String body, String subject,String path){
		logger.info("Entered SetUserEmail method of SessionFeedBackDAO");
		Boolean isFeedbackCommit = false;
					try {
						conn =ConnectionFactory.getConnection();
						conn.setAutoCommit(false);
						String query = "insert into email"+"(SESSION_ID,USER_BODY,USER_SUBJECT,USER_FILE) values" + "(?, ?, ?,?)";
						PreparedStatement pstmt = conn.prepareStatement(query);
						pstmt.setString(1, sId);
						pstmt.setString(2,body);
						pstmt.setString(3,subject);
						pstmt.setString(4,path);
						int result = pstmt.executeUpdate();
						if(result > 0) {
							conn.commit();
							isFeedbackCommit = true;
						}
					} catch (SQLException e) {
						try {
							conn.rollback();
						} catch (SQLException e1) {
							logger.error("SetUserEmail method of SessionFeedBackDAO threw error:"+e.getMessage());
							e1.printStackTrace();
						}
						logger.error("SetUserEmail method of SessionFeedBackDAO threw error:"+e.getMessage());
						e.printStackTrace();
					} catch (IOException e) {
						logger.error("SetUserEmail method of SessionFeedBackDAO threw error:"+e.getMessage());
						e.printStackTrace();
					} catch (PropertyVetoException e) {
						logger.error("SetUserEmail method of SessionFeedBackDAO threw error:"+e.getMessage());
						e.printStackTrace();
					}finally{
						try {
							conn.close();
						} catch (SQLException e) {
							logger.error("SetUserEmail method of SessionFeedBackDAO threw error:"+e.getMessage());
							e.printStackTrace();
						}
					}	
			logger.info("Entered SetUserEmail method of SessionFeedBackDAO");
			return isFeedbackCommit;

	}
	public Boolean CheckMail(String sId){
		logger.info("Entered CheckMail method of SessionFeedBackDAO");
		Boolean isFeedBackGiven = false;
		if(!sId.isEmpty()){
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT ID FROM email WHERE SESSION_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1,sId);
			ResultSet results = pstmt.executeQuery();
			if(results.first()){
				isFeedBackGiven = true;
			}
		} catch (SQLException e) {
			logger.error("CheckMail method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("CheckMail method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("CheckMail method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("CheckMail method of SessionFeedBackDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		}
		
		logger.info("Exit CheckMail method of SessionFeedBackDAO");
		return isFeedBackGiven;
	}
	public SessionFeedBackDTO GetUserMailForAdvisor(int sId){
		logger.info("Entered GetUserMailForAdvisor method of SessionFeedBackDAO");
		Boolean isFeedBackGiven = false;
		SessionFeedBackDTO feed = new SessionFeedBackDTO();
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT * FROM email WHERE SESSION_ID = ? AND USER_APPROVAL =?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,sId);
			pstmt.setBoolean(2, true);
			ResultSet results = pstmt.executeQuery();
			if(results.first()){
				feed.setId(results.getInt("ID"));
				feed.setSessionId(results.getInt("SESSION_ID"));
				feed.setUserBody(results.getString("USER_BODY"));
				feed.setUserSubject(results.getString("USER_SUBJECT"));
				feed.setUserFile(results.getString("USER_FILE"));
				feed.setUserApproval(results.getBoolean("USER_APPROVAL"));
			}
		} catch (SQLException e) {
			logger.error("GetUserMailForAdvisor method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("GetUserMailForAdvisor method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("GetUserMailForAdvisor method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetUserMailForAdvisor method of SessionFeedBackDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		logger.info("Exit GetUserFeedBackDetailsForAdvisor method of SessionFeedBackDAO");
		return feed;
	}
	public SessionFeedBackDTO GetAdvisorMailForAdvisor(int sId){
		logger.info("Entered GetAdvisorMailForAdvisor method of SessionFeedBackDAO");
		Boolean isFeedBackGiven = false;
		SessionFeedBackDTO feed = new SessionFeedBackDTO();
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT * FROM email WHERE SESSION_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,sId);
			ResultSet results = pstmt.executeQuery();
			if(results.first()){
				feed.setId(results.getInt("ID"));
				feed.setSessionId(results.getInt("SESSION_ID"));
				feed.setAdvisorBody(results.getString("ADVISOR_BODY"));
				feed.setAdvisorSubject(results.getString("ADVISOR_SUBJECT"));
				feed.setAdvisorFile(results.getString("ADVISOR_FILE"));
				feed.setAdvisorApproval(results.getBoolean("ADVISOR_APPROVAL"));
			}
		} catch (SQLException e) {
			logger.error("GetAdvisorMailForAdvisor method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("GetAdvisorMailForAdvisor method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("GetAdvisorMailForAdvisor method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetAdvisorMailForAdvisor method of SessionFeedBackDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		logger.info("Exit GetAdvisorMailForAdvisor method of SessionFeedBackDAO");
		return feed;
	}
	
	public Boolean SetAdvisorMail(String sId, String body, String subject,String path){
		logger.info("Entered SetAdvisorMail method of SessionFeedBackDAO");
		Boolean isFeedbackCommit = false;
					try {
						conn =ConnectionFactory.getConnection();
						conn.setAutoCommit(false);
						String query = "UPDATE email  SET ADVISOR_BODY=?,ADVISOR_SUBJECT=?,ADVISOR_FILE=? WHERE SESSION_ID = ?";
						PreparedStatement pstmt = conn.prepareStatement(query);
						pstmt.setString(1,body);
						pstmt.setString(2,subject);
						pstmt.setString(3,path);
						pstmt.setString(4,sId);
						int result = pstmt.executeUpdate();
						if(result > 0) {
							conn.commit();
							isFeedbackCommit = true;
						}
					} catch (SQLException e) {
						try {
							conn.rollback();
						} catch (SQLException e1) {
							logger.error("SetAdvisorMail method of SessionFeedBackDAO threw error:"+e.getMessage());
							e1.printStackTrace();
						}
						logger.error("SetAdvisorMail method of SessionFeedBackDAO threw error:"+e.getMessage());
						e.printStackTrace();
					} catch (IOException e) {
						logger.error("SetAdvisorMail method of SessionFeedBackDAO threw error:"+e.getMessage());
						e.printStackTrace();
					} catch (PropertyVetoException e) {
						logger.error("SetAdvisorMail method of SessionFeedBackDAO threw error:"+e.getMessage());
						e.printStackTrace();
					}finally{
						try {
							conn.close();
						} catch (SQLException e) {
							logger.error("SetAdvisorMail method of SessionFeedBackDAO threw error:"+e.getMessage());
							e.printStackTrace();
						}
					}	
			logger.info("Entered SetAdvisorMail method of SessionFeedBackDAO");
			return isFeedbackCommit;

	}
	
	public SessionFeedBackDTO GetUserMailForUser(int sId){
		logger.info("Entered GetUserMailForUser method of SessionFeedBackDAO");
		Boolean isFeedBackGiven = false;
		SessionFeedBackDTO feed = new SessionFeedBackDTO();
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT * FROM email WHERE SESSION_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,sId);
			ResultSet results = pstmt.executeQuery();
			if(results.first()){
				feed.setId(results.getInt("ID"));
				feed.setSessionId(results.getInt("SESSION_ID"));
				feed.setUserBody(results.getString("USER_BODY"));
				feed.setUserSubject(results.getString("USER_SUBJECT"));
				feed.setUserFile(results.getString("USER_FILE"));
				feed.setUserApproval(results.getBoolean("USER_APPROVAL"));
			}
		} catch (SQLException e) {
			logger.error("GetUserMailForUser method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("GetUserMailForUser method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("GetUserMailForUser method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetUserMailForUser method of SessionFeedBackDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		logger.info("Exit GetUserMailForUser method of SessionFeedBackDAO");
		return feed;
	}
	
	public SessionFeedBackDTO GetAdvisorMailForUser(int sId){
		logger.info("Entered GetAdvisorMailForUser method of SessionFeedBackDAO");
		Boolean isFeedBackGiven = false;
		SessionFeedBackDTO feed = new SessionFeedBackDTO();
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT * FROM email WHERE SESSION_ID = ? AND ADVISOR_APPROVAL=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,sId);
			pstmt.setBoolean(2, true);
			ResultSet results = pstmt.executeQuery();
			if(results.first()){
				feed.setId(results.getInt("ID"));
				feed.setSessionId(results.getInt("SESSION_ID"));
				feed.setAdvisorBody(results.getString("ADVISOR_BODY"));
				feed.setAdvisorSubject(results.getString("ADVISOR_SUBJECT"));
				feed.setAdvisorFile(results.getString("ADVISOR_FILE"));
			}
		} catch (SQLException e) {
			logger.error("GetAdvisorMailForUser method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("GetAdvisorMailForUser method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("GetAdvisorMailForUser method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetAdvisorMailForUser method of SessionFeedBackDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		logger.info("Exit GetAdvisorMailForUser method of SessionFeedBackDAO");
		return feed;
	}
	
	public Boolean SetUserMailAgain(String sId, String body, String subject, String fileURL){
		logger.info("Entered SetUserMailAgain method of SessionFeedBackDAO");
		Boolean isStatusCommit = false;
		String query = "";
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			query = "UPDATE email  SET USER_BODY=?,USER_SUBJECT=?,USER_FILE=? WHERE SESSION_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, body);
			pstmt.setString(2, subject);
			pstmt.setString(3, fileURL);
			pstmt.setString(4,sId);
			int result = pstmt.executeUpdate(); 
			if(result >0) {
				conn.commit();
				isStatusCommit = true;
				}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("SetUserMailAgain method of SessionFeedBackDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}	
			logger.error("SetUserMailAgain method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("SetUserMailAgain method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("SetUserMailAgain method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("SetUserMailAgain method of SessionFeedBackDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}	
		logger.info("Entered SetUserMailAgain method of SessionFeedBackDAO");
		return isStatusCommit;
		}
	
	public SessionFeedBackDTO GetMail(int sId){
		logger.info("Entered GetMail method of SessionFeedBackDAO");
		Boolean isFeedBackGiven = false;
		SessionFeedBackDTO feed = new SessionFeedBackDTO();
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT * FROM email WHERE SESSION_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,sId);
			ResultSet results = pstmt.executeQuery();
			if(results.first()){
				feed.setId(results.getInt("ID"));
				feed.setSessionId(results.getInt("SESSION_ID"));
				feed.setUserBody(results.getString("USER_BODY"));
				feed.setUserSubject(results.getString("USER_SUBJECT"));
				feed.setUserFile(results.getString("USER_FILE"));
				feed.setAdvisorBody(results.getString("ADVISOR_BODY"));
				feed.setAdvisorSubject(results.getString("ADVISOR_SUBJECT"));
				feed.setAdvisorFile(results.getString("ADVISOR_FILE"));
			}
		} catch (SQLException e) {
			logger.error("GetMail method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("GetMail method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("GetMail method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetMail method of SessionFeedBackDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		
		logger.info("Exit GetMail method of SessionFeedBackDAO");
		return feed;
	}
	
	public Boolean SetApproveMailStatus(String sId, String type){
		logger.info("Entered SetApproveMailStatus method of SessionFeedBackDAO");
		String status = "EducationInfo.jsp"; 
		Boolean isStatusCommit = false;
		String query = "";
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			if(type.equals("user")){
				query = "UPDATE email  SET USER_APPROVAL=? WHERE SESSION_ID = ?";
			}else if (type.equals("advisor")) {
				query = "UPDATE email  SET ADVISOR_APPROVAL=? WHERE SESSION_ID = ?";
			}
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1, true);
			pstmt.setString(2,sId);
			int result = pstmt.executeUpdate(); 
			if(result >0) {
				conn.commit();
				isStatusCommit = true;
				}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("SetApproveMailStatus method of SessionFeedBackDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}	
			logger.error("SetApproveMailStatus method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("SetApproveMailStatus method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("SetApproveMailStatus method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("SetApproveMailStatus method of SessionFeedBackDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}	
		logger.info("Entered SetApproveMailStatus method of SessionFeedBackDAO");
		return isStatusCommit;
		}
	
	public Boolean SetRejectMailStatus(String sId, String type){
		logger.info("Entered SetRejectMailStatus method of SessionFeedBackDAO");
		String status = "EducationInfo.jsp"; 
		Boolean isStatusCommit = false;
		String query = "";
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			if(type.equals("user")){
				query = "UPDATE email  SET USER_APPROVAL=? WHERE SESSION_ID = ?";
			}else if (type.equals("advisor")) {
				query = "UPDATE email  SET ADVISOR_APPROVAL=? WHERE SESSION_ID = ?";
			}
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1, false);
			pstmt.setString(2,sId);
			int result = pstmt.executeUpdate(); 
			if(result >0) {
				conn.commit();
				isStatusCommit = true;
				}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("SetRejectMailStatus method of SessionFeedBackDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}	
			logger.error("SetRejectMailStatus method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("SetRejectMailStatus method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("SetRejectMailStatus method of SessionFeedBackDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("SetRejectMailStatus method of SessionFeedBackDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}	
		logger.info("Entered SetRejectMailStatus method of SessionFeedBackDAO");
		return isStatusCommit;
		}
}
