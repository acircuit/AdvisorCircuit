/*************************************************************************************************
 * ********************************ADVISOR CIRCUIT*************************************************
 * ************************************************************************************************
 * @author AdvisorCircuit
 * @Date 18/12/2014
 * ************************************************************************************************/
package org.AC.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.AC.JDBC.ConnectionFactory;
import org.AC.JDBC.Util;
import org.AC.dto.AdvisorMessageDTO;
import org.AC.dto.FilesDTO;
import org.AC.dto.MessageDTO;
import org.AC.dto.UserAdvisorMessageDTO;
import org.AC.dto.UserMessageDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/********************************
 * CLASS SUMMARY*****************************************************
 * 
 * This class do all the DB Calls for the messages and files exchanges between
 * user and advisor
 * 
 *
 ***************************************************************************************************/

public class SessionMssagesDAO {
	private static final Logger logger = Logger
			.getLogger(SessionMssagesDAO.class);
	Connection conn = null;
	Statement stmt = null;

	/**************************************
	 * COMMENTS*************************************************** This function
	 * will retrieve the messages for a particular sessionId.
	 * 
	 * @return :List<UserMessageDTO> messages
	 * @param : String sId
	 * 
	 *
	 ***************************************************************************************************/

	public List<UserMessageDTO> GetMessages(String sId, String flag) {

		logger.info("Entered GetMessages method of SessionMssagesDAO");
		ResultSet results = null;
		String query = "";
		List<UserMessageDTO> usermessages = new ArrayList<UserMessageDTO>();
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			if (("advisor").equals(flag)) {
				query = "SELECT * FROM usermessage WHERE SESSION_ID = ? AND STATUS = ?";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1, sId);
				pstmt.setString(2, "APPROVED");
				results = pstmt.executeQuery();
			} else {
				query = "SELECT * FROM usermessage WHERE SESSION_ID = ?";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1, sId);
				results = pstmt.executeQuery();
			}
			while (results.next()) {
				UserMessageDTO message = new UserMessageDTO();
				message.setMessageId(results.getInt("MESSAGE_ID"));
				message.setSessionId(results.getInt("SESSION_ID"));
				message.setUserMessage(results.getString("USER_MESSAGE"));
				message.setUserMessageTime(results
						.getTimestamp("USER_MESSAGE_TIME"));
				message.setStatus(results.getString("STATUS"));
				usermessages.add(message);
			}
			conn.commit();
			logger.info("Exit GetMessages method of SessionMssagesDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("GetMessages method of SessionMssagesDAO threw error:"
							+ e.getMessage());
					e2.printStackTrace();
				}
				logger.error("GetMessages method of SessionMssagesDAO threw error:"
						+ e.getMessage());
				e1.printStackTrace();
			}
			logger.error("GetMessages method of SessionMssagesDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetMessages method of SessionMssagesDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return usermessages;
	}

	public Boolean SetMessage(String sId, String message, String type) {

		logger.info("Entered SetAdvisorMessage method of SessionMssagesDAO");
		Boolean isMessageCommit = false;
		String query = "";
		try {
			Calendar mbCal = new GregorianCalendar(TimeZone.getTimeZone("IST"));
			mbCal.setTimeInMillis(new Date().getTime());
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, mbCal.get(Calendar.YEAR));
			cal.set(Calendar.MONTH, mbCal.get(Calendar.MONTH));
			cal.set(Calendar.DAY_OF_MONTH, mbCal.get(Calendar.DAY_OF_MONTH));
			cal.set(Calendar.HOUR_OF_DAY, mbCal.get(Calendar.HOUR_OF_DAY));
			cal.set(Calendar.MINUTE, mbCal.get(Calendar.MINUTE));
			cal.set(Calendar.SECOND, mbCal.get(Calendar.SECOND));
			cal.set(Calendar.MILLISECOND, mbCal.get(Calendar.MILLISECOND));
			Date date = cal.getTime();
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			if (("advisor").equals(type)) {
				query = "insert into advisormessage"
						+ "(SESSION_ID,ADVISOR_MESSAGE,ADVISOR_MESSAGE_TIME) values"
						+ "(?,?,?)";
			} else {
				query = "insert into usermessage"
						+ "(SESSION_ID,USER_MESSAGE,USER_MESSAGE_TIME) values"
						+ "(?,?,?)";
			}
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, sId);
			pstmt.setString(2, message);
			pstmt.setTimestamp(3, new java.sql.Timestamp(date.getTime()));
			int result = pstmt.executeUpdate();
			if (result > 0) {
				conn.commit();
				isMessageCommit = true;
			}
			logger.info("Exit SetAdvisorMessage method of SessionMssagesDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("SetAdvisorMessage method of SessionMssagesDAO threw error:"
							+ e2.getMessage());
					e2.printStackTrace();
				}
				logger.error("SetAdvisorMessage method of SessionMssagesDAO threw error:"
						+ e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("SetAdvisorMessage method of SessionMssagesDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("SetAdvisorMessage method of SessionMssagesDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return isMessageCommit;
	}

	public List<AdvisorMessageDTO> GetAdvisorMessages(String sId, String flag) {

		logger.info("Entered GetAdvisorMessages method of SessionMssagesDAO");
		ResultSet results = null;
		String query = "";
		List<AdvisorMessageDTO> advisormessages = new ArrayList<AdvisorMessageDTO>();
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			if (("advisor").equals(flag)) {
				query = "SELECT * FROM advisormessage WHERE SESSION_ID = ?";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1, sId);
				results = pstmt.executeQuery();

			} else {
				query = "SELECT * FROM advisormessage WHERE SESSION_ID = ? AND STATUS = ?";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1, sId);
				pstmt.setString(2, "APPROVED");
				results = pstmt.executeQuery();
			}
			while (results.next()) {
				AdvisorMessageDTO message = new AdvisorMessageDTO();
				message.setMessageId(results.getInt("MESSAGE_ID"));
				message.setSessionId(results.getInt("SESSION_ID"));
				message.setAdvisorMessage(results.getString("ADVISOR_MESSAGE"));
				message.setAdvisorMessageTime(results
						.getTimestamp("ADVISOR_MESSAGE_TIME"));
				message.setStatus(results.getString("STATUS"));
				advisormessages.add(message);
			}
			conn.commit();
			logger.info("Exit GetAdvisorMessages method of SessionMssagesDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("GetAdvisorMessages method of SessionMssagesDAO threw error:"
							+ e.getMessage());
					e2.printStackTrace();
				}
				logger.error("GetAdvisorMessages method of SessionMssagesDAO threw error:"
						+ e.getMessage());
				e1.printStackTrace();
			}
			logger.error("GetAdvisorMessages method of SessionMssagesDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetAdvisorMessages method of SessionMssagesDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return advisormessages;
	}

	public Boolean SetFiles(String sId, String url, String comment,
			String fromUser) {

		logger.info("Entered SetFiles method of SessionMssagesDAO");
		Boolean isFileCommit = false;
		try {
			Calendar mbCal = new GregorianCalendar(TimeZone.getTimeZone("IST"));
			mbCal.setTimeInMillis(new Date().getTime());
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, mbCal.get(Calendar.YEAR));
			cal.set(Calendar.MONTH, mbCal.get(Calendar.MONTH));
			cal.set(Calendar.DAY_OF_MONTH, mbCal.get(Calendar.DAY_OF_MONTH));
			cal.set(Calendar.HOUR_OF_DAY, mbCal.get(Calendar.HOUR_OF_DAY));
			cal.set(Calendar.MINUTE, mbCal.get(Calendar.MINUTE));
			cal.set(Calendar.SECOND, mbCal.get(Calendar.SECOND));
			cal.set(Calendar.MILLISECOND, mbCal.get(Calendar.MILLISECOND));
			Date date = cal.getTime();
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "insert into files"
					+ "(SESSION_ID,FILE,TIME,COMMENT,UPLOADED_BY) values"
					+ "(?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, sId);
			pstmt.setString(2, url);
			pstmt.setTimestamp(3, new java.sql.Timestamp(date.getTime()));
			pstmt.setString(4, comment);
			if (("false").equals(fromUser)) {
				pstmt.setString(5, "ADVISOR");
			} else {
				pstmt.setString(5, "USER");
			}
			int result = pstmt.executeUpdate();
			if (result > 0) {
				conn.commit();
				isFileCommit = true;
			}
			logger.info("Exit SetFiles method of SessionMssagesDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("SetFiles method of SessionMssagesDAO threw error:"
							+ e2.getMessage());
					e2.printStackTrace();
				}
				logger.error("SetFiles method of SessionMssagesDAO threw error:"
						+ e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("SetFiles method of SessionMssagesDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("SetFiles method of SessionMssagesDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return isFileCommit;
	}

	public List<FilesDTO> GetFilesList(String sId) {

		logger.info("Entered GetFilesList method of SessionMssagesDAO");
		ResultSet results = null;
		List<FilesDTO> files = new ArrayList<FilesDTO>();
		String query = "";
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			query = "SELECT * FROM files WHERE SESSION_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, sId);
			results = pstmt.executeQuery();
			while (results.next()) {
				FilesDTO file = new FilesDTO();
				file.setId(results.getInt("ID"));
				file.setSessionId(results.getInt("SESSION_ID"));
				file.setFileURL(results.getString("FILE"));
				file.setTime(results.getTimestamp("TIME"));
				file.setComment(results.getString("COMMENT"));
				file.setUploadedBy(results.getString("UPLOADED_BY"));
				file.setStatus(results.getString("STATUS"));
				files.add(file);
			}
			conn.commit();
			logger.info("Exit GetFilesList method of SessionMssagesDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("GetFilesList method of SessionMssagesDAO threw error:"
							+ e.getMessage());
					e2.printStackTrace();
				}
				logger.error("GetFilesList method of SessionMssagesDAO threw error:"
						+ e.getMessage());
				e1.printStackTrace();
			}
			logger.error("GetFilesList method of SessionMssagesDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetFilesList method of SessionMssagesDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return files;
	}

	public String getFilePath(String id) {

		logger.info("Entered getFilePath method of SessionMssagesDAO");
		ResultSet results = null;
		String path = "";
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "SELECT * FROM files WHERE ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			results = pstmt.executeQuery();
			while (results.next()) {
				path = results.getString("FILE");
			}
			conn.commit();
			logger.info("Exit getFilePath method of SessionMssagesDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("getFilePath method of SessionMssagesDAO threw error:"
							+ e2.getMessage());
					e2.printStackTrace();
				}
				logger.error("getFilePath method of SessionMssagesDAO threw error:"
						+ e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("getFilePath method of SessionMssagesDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("getFilePath method of SessionMssagesDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return path;
	}

	public List<UserMessageDTO> GetMessages(String sId) {

		logger.info("Entered GetMessages method of SessionMssagesDAO");
		ResultSet results = null;
		String query = "";
		List<UserMessageDTO> usermessages = new ArrayList<UserMessageDTO>();
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			query = "SELECT * FROM usermessage WHERE SESSION_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, sId);
			results = pstmt.executeQuery();
			while (results.next()) {
				UserMessageDTO message = new UserMessageDTO();
				message.setMessageId(results.getInt("MESSAGE_ID"));
				message.setSessionId(results.getInt("SESSION_ID"));
				message.setUserMessage(results.getString("USER_MESSAGE"));
				message.setUserMessageTime(results
						.getTimestamp("USER_MESSAGE_TIME"));
				message.setStatus(results.getString("STATUS"));
				usermessages.add(message);
			}
			conn.commit();
			logger.info("Exit GetMessages method of SessionMssagesDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("GetMessages method of SessionMssagesDAO threw error:"
							+ e.getMessage());
					e2.printStackTrace();
				}
				logger.error("GetMessages method of SessionMssagesDAO threw error:"
						+ e.getMessage());
				e1.printStackTrace();
			}
			logger.error("GetMessages method of SessionMssagesDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetMessages method of SessionMssagesDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return usermessages;
	}

	public List<AdvisorMessageDTO> GetAdvisorMessages(String sId) {

		logger.info("Entered GetAdvisorMessages method of SessionMssagesDAO");
		ResultSet results = null;
		String query = "";
		List<AdvisorMessageDTO> advisormessages = new ArrayList<AdvisorMessageDTO>();
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			query = "SELECT * FROM advisormessage WHERE SESSION_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, sId);
			results = pstmt.executeQuery();
			while (results.next()) {
				AdvisorMessageDTO message = new AdvisorMessageDTO();
				message.setMessageId(results.getInt("MESSAGE_ID"));
				message.setSessionId(results.getInt("SESSION_ID"));
				message.setAdvisorMessage(results.getString("ADVISOR_MESSAGE"));
				message.setAdvisorMessageTime(results
						.getTimestamp("ADVISOR_MESSAGE_TIME"));
				message.setStatus(results.getString("STATUS"));
				advisormessages.add(message);
			}
			conn.commit();
			logger.info("Exit GetAdvisorMessages method of SessionMssagesDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("GetAdvisorMessages method of SessionMssagesDAO threw error:"
							+ e.getMessage());
					e2.printStackTrace();
				}
				logger.error("GetAdvisorMessages method of SessionMssagesDAO threw error:"
						+ e.getMessage());
				e1.printStackTrace();
			}
			logger.error("GetAdvisorMessages method of SessionMssagesDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetAdvisorMessages method of SessionMssagesDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return advisormessages;
	}

	public Boolean SetFileStatus(String Id, String status) {

		logger.info("Entered SetFileStatus method of SessionMssagesDAO");
		Boolean isStatusCommit = false;
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "UPDATE files SET STATUS = ? WHERE ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, status);
			;
			pstmt.setString(2, Id);
			int result = pstmt.executeUpdate();
			if (result > 0) {
				conn.commit();
				isStatusCommit = true;
			}
			logger.info("Exit SetFileStatus method of SessionMssagesDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("SetFileStatus method of SessionMssagesDAO threw error:"
							+ e2.getMessage());
					e2.printStackTrace();
				}
				logger.error("SetFileStatus method of SessionMssagesDAO threw error:"
						+ e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("SetFileStatus method of SessionMssagesDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("SetFileStatus method of SessionMssagesDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return isStatusCommit;
	}

	public Boolean SetUserMessage(String Id, String status) {

		logger.info("Entered SetUserMessage method of SessionMssagesDAO");
		Boolean isStatusCommit = false;
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "UPDATE usermessage SET STATUS = ? WHERE MESSAGE_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, status);
			;
			pstmt.setString(2, Id);
			int result = pstmt.executeUpdate();
			if (result > 0) {
				conn.commit();
				isStatusCommit = true;
			}
			logger.info("Exit SetUserMessage method of SessionMssagesDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("SetUserMessage method of SessionMssagesDAO threw error:"
							+ e2.getMessage());
					e2.printStackTrace();
				}
				logger.error("SetUserMessage method of SessionMssagesDAO threw error:"
						+ e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("SetUserMessage method of SessionMssagesDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("checkKeyWord method of SearchDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return isStatusCommit;
	}

	public Boolean SetAdvisorMessage(String Id, String status) {

		logger.info("Entered SetAdvisorMessage method of SessionMssagesDAO");
		Boolean isStatusCommit = false;
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "UPDATE advisormessage SET STATUS = ? WHERE MESSAGE_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, status);
			;
			pstmt.setString(2, Id);
			int result = pstmt.executeUpdate();
			if (result > 0) {
				conn.commit();
				isStatusCommit = true;
			}
			logger.info("Exit SetAdvisorMessage method of SessionMssagesDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("SetAdvisorMessage method of SessionMssagesDAO threw error:"
							+ e2.getMessage());
					e2.printStackTrace();
				}
				logger.error("SetAdvisorMessage method of SessionMssagesDAO threw error:"
						+ e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("SetAdvisorMessage method of SessionMssagesDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("SetAdvisorMessage method of SessionMssagesDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return isStatusCommit;
	}

	public FilesDTO GetPurpose(String fileId) {

		logger.info("Entered GetPurpose method of SessionMssagesDAO");
		ResultSet results = null;
		String query = "";
		FilesDTO file = null;
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			query = "SELECT COMMENT,SESSION_ID,UPLOADED_BY FROM files WHERE ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, fileId);
			results = pstmt.executeQuery();
			while (results.next()) {
				file = new FilesDTO();
				file.setComment(results.getString("COMMENT"));
				file.setSessionId(results.getInt("SESSION_ID"));
				file.setUploadedBy(results.getString("UPLOADED_BY"));
			}
			conn.commit();
			logger.info("Exit GetMessages method of SessionMssagesDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("GetMessages method of SessionMssagesDAO threw error:"
							+ e.getMessage());
					e2.printStackTrace();
				}
				logger.error("GetMessages method of SessionMssagesDAO threw error:"
						+ e.getMessage());
				e1.printStackTrace();
			}
			logger.error("GetMessages method of SessionMssagesDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetMessages method of SessionMssagesDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return file;
	}

	public Integer GetId(int sessionId, String uploadedBy) {

		logger.info("Entered GetId method of SessionMssagesDAO");
		ResultSet results = null;
		String query = "";
		FilesDTO file = null;
		int id = 0;
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			if (("ADVISOR").equals(uploadedBy)) {
				query = "SELECT ADVISOR_ID FROM session_table WHERE SESSION_ID = ?";
			} else if (("USER").equals(uploadedBy)) {
				query = "SELECT USER_ID FROM session_table WHERE SESSION_ID = ?";
			}
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, sessionId);
			results = pstmt.executeQuery();
			while (results.next()) {
				if (("ADVISOR").equals(uploadedBy)) {
					id = results.getInt("ADVISOR_ID");
				} else if (("USER").equals(uploadedBy)) {
					id = results.getInt("USER_ID");
				}
			}
			conn.commit();
			logger.info("Exit GetId method of SessionMssagesDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("GetId method of SessionMssagesDAO threw error:"
							+ e.getMessage());
					e2.printStackTrace();
				}
				logger.error("GetId method of SessionMssagesDAO threw error:"
						+ e.getMessage());
				e1.printStackTrace();
			}
			logger.error("GetId method of SessionMssagesDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetId method of SessionMssagesDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return id;
	}

	public String GetEmail(int id, String uploadedBy) {

		logger.info("Entered GetEmail method of SessionMssagesDAO");
		ResultSet results = null;
		String query = "";
		FilesDTO file = null;
		String email = "";
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			if (("ADVISOR").equals(uploadedBy)) {
				query = "SELECT EMAIL FROM advisordetails WHERE ADVISOR_ID = ?";
			} else if (("USER").equals(uploadedBy)) {
				query = "SELECT EMAIL FROM userdetails WHERE USER_ID = ?";
			}
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			results = pstmt.executeQuery();
			while (results.next()) {
				email = results.getString("EMAIL");
			}
			conn.commit();
			logger.info("Exit GetEmail method of SessionMssagesDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("GetEmail method of SessionMssagesDAO threw error:"
							+ e.getMessage());
					e2.printStackTrace();
				}
				logger.error("GetEmail method of SessionMssagesDAO threw error:"
						+ e.getMessage());
				e1.printStackTrace();
			}
			logger.error("GetEmail method of SessionMssagesDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetEmail method of SessionMssagesDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return email;
	}

	public MessageDTO GetMessage(String messageId, String type) {

		logger.info("Entered GetMessage method of SessionMssagesDAO");
		ResultSet results = null;
		String query = "";
		FilesDTO file = null;
		MessageDTO messages = new MessageDTO();
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			if (("ADVISOR").equals(type)) {
				query = "SELECT SESSION_ID,ADVISOR_MESSAGE FROM advisormessage WHERE MESSAGE_ID = ?";
			} else if (("USER").equals(type)) {
				query = "SELECT SESSION_ID,USER_MESSAGE FROM usermessage WHERE MESSAGE_ID = ?";
			}
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, messageId);
			results = pstmt.executeQuery();
			while (results.next()) {
				messages.setSessionId(results.getInt("SESSION_ID"));
				if (("ADVISOR").equals(type)) {
					messages.setAdvisorMessage(results
							.getString("ADVISOR_MESSAGE"));
				} else if (("USER").equals(type)) {
					messages.setAdvisorMessage(results
							.getString("USER_MESSAGE"));
				}
			}
			conn.commit();
			logger.info("Exit GetMessage method of SessionMssagesDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("GetMessage method of SessionMssagesDAO threw error:"
							+ e.getMessage());
					e2.printStackTrace();
				}
				logger.error("GetMessage method of SessionMssagesDAO threw error:"
						+ e.getMessage());
				e1.printStackTrace();
			}
			logger.error("GetMessage method of SessionMssagesDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetMessage method of SessionMssagesDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return messages;
	}
}
