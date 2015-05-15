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
import org.AC.dto.RecommendationDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class ReviewAndRecommendationDAO {
	Connection conn = null;
	Statement stmt = null;
	private static final Logger logger = Logger
			.getLogger(ReviewAndRecommendationDAO.class);

	public RecommendationDTO CheckIsRecommended(String sId) {

		logger.info("Entered CheckIsRecommended method of ReviewAndRecommendationDAO");
		ResultSet results = null;
		RecommendationDTO recommend = new RecommendationDTO();
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "SELECT RATING,REVIEW_MESSAGE,REVIEW_MESSAGE_STATUS FROM session_table WHERE SESSION_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, sId);
			results = pstmt.executeQuery();
			if (results.first()) {
				recommend.setRatings(results.getBoolean("RATING"));
				recommend.setReviewMessage(results.getString("REVIEW_MESSAGE"));
				recommend.setReviewMessageStatus(results
						.getString("REVIEW_MESSAGE_STATUS"));
			}
			logger.info("Exit CheckIsRecommended method of ReviewAndRecommendationDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("CheckIsRecommended method of ReviewAndRecommendationDAO threw error:"
							+ e2.getMessage());
					e2.printStackTrace();
				}
				logger.error("CheckIsRecommended method of ReviewAndRecommendationDAO threw error:"
						+ e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("CheckIsRecommended method of ReviewAndRecommendationDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("CheckIsRecommended method of ReviewAndRecommendationDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return recommend;
	}

	public Boolean UpdateRecommendationForSession(String sId) {

		logger.info("Entered UpdateRecommendationForSession method of ReviewAndRecommendationDAO");
		int results = 0;
		Boolean isRatingsCommit = false;
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "UPDATE session_table SET RATING = ? WHERE SESSION_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1, true);
			pstmt.setString(2, sId);
			results = pstmt.executeUpdate();
			if (results > 0) {
				conn.commit();
				isRatingsCommit = true;
			}
			logger.info("Exit UpdateRecommendationForSession method of ReviewAndRecommendationDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("UpdateRecommendationForSession method of ReviewAndRecommendationDAO threw error:"
							+ e2.getMessage());
					e2.printStackTrace();
				}
				logger.error("UpdateRecommendationForSession method of ReviewAndRecommendationDAO threw error:"
						+ e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("UpdateRecommendationForSession method of ReviewAndRecommendationDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("UpdateRecommendationForSession method of ReviewAndRecommendationDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return isRatingsCommit;
	}

	public Boolean UpdateReviewMessage(String sId, String message) {

		logger.info("Entered UpdateReviewMessage method of ReviewAndRecommendationDAO");
		int results = 0;
		Boolean isReviewCommit = false;
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "UPDATE session_table SET REVIEW_MESSAGE = ?,REVIEW_MESSAGE_STATUS=? WHERE SESSION_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, message);
			pstmt.setString(2, null);
			pstmt.setString(3, sId);
			results = pstmt.executeUpdate();
			if (results > 0) {
				conn.commit();
				isReviewCommit = true;
			}
			logger.info("Exit UpdateReviewMessage method of ReviewAndRecommendationDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("UpdateReviewMessage method of ReviewAndRecommendationDAO threw error:"
							+ e2.getMessage());
					e2.printStackTrace();
				}
				logger.error("UpdateReviewMessage method of ReviewAndRecommendationDAO threw error:"
						+ e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("UpdateReviewMessage method of ReviewAndRecommendationDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("UpdateReviewMessage method of ReviewAndRecommendationDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return isReviewCommit;
	}

	public Boolean ApproveReview(String sId, String status) {

		logger.info("Entered ApproveReview method of ReviewAndRecommendationDAO");
		int results = 0;
		Boolean isReviewCommit = false;
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "UPDATE session_table SET REVIEW_MESSAGE_STATUS = ? WHERE SESSION_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, status);
			pstmt.setString(2, sId);
			results = pstmt.executeUpdate();
			if (results > 0) {
				conn.commit();
				isReviewCommit = true;
			}
			logger.info("Exit ApproveReview method of ReviewAndRecommendationDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("ApproveReview method of ReviewAndRecommendationDAO threw error:"
							+ e2.getMessage());
					e2.printStackTrace();
				}
				logger.error("ApproveReview method of ReviewAndRecommendationDAO threw error:"
						+ e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("ApproveReview method of ReviewAndRecommendationDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("ApproveReview method of ReviewAndRecommendationDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return isReviewCommit;
	}

	public int GetAdvisorId(String sId) {

		logger.info("Entered GetAdvisorId method of ReviewAndRecommendationDAO");
		ResultSet results = null;
		int advisorId = 0;
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "SELECT ADVISOR_ID FROM session_table WHERE SESSION_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, sId);
			results = pstmt.executeQuery();
			if (results.first()) {
				advisorId = results.getInt("ADVISOR_ID");
			}
			conn.commit();

			logger.info("Exit GetAdvisorId method of ReviewAndRecommendationDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("GetAdvisorId method of ReviewAndRecommendationDAO threw error:"
							+ e2.getMessage());
					e2.printStackTrace();
				}
				logger.error("GetAdvisorId method of ReviewAndRecommendationDAO threw error:"
						+ e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("GetAdvisorId method of ReviewAndRecommendationDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetAdvisorId method of ReviewAndRecommendationDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return advisorId;
	}

	public Boolean UpdateRecommendationForAdvisor(int advisorId) {

		logger.info("Entered UpdateRecommendationForAdvisor method of ReviewAndRecommendationDAO");
		int results = 0;
		Boolean isRatingsCommit = false;
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "UPDATE advisordetails SET RATING = RATING +1 WHERE ADVISOR_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, advisorId);
			results = pstmt.executeUpdate();
			if (results > 0) {
				conn.commit();
				isRatingsCommit = true;
			}
			logger.info("Exit UpdateRecommendationForAdvisor method of ReviewAndRecommendationDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("UpdateRecommendationForAdvisor method of ReviewAndRecommendationDAO threw error:"
							+ e2.getMessage());
					e2.printStackTrace();
				}
				logger.error("UpdateRecommendationForAdvisor method of ReviewAndRecommendationDAO threw error:"
						+ e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("UpdateRecommendationForAdvisor method of ReviewAndRecommendationDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("UpdateRecommendationForAdvisor method of ReviewAndRecommendationDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return isRatingsCommit;
	}

	public List<RecommendationDTO> GetReviews(String aId) {

		logger.info("Entered GetReviews method of ReviewAndRecommendationDAO");
		ResultSet results = null;
		int rating = 0;
		List<RecommendationDTO> reviews = new ArrayList<RecommendationDTO>();
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "SELECT USER_ID,REVIEW_MESSAGE FROM session_table WHERE ADVISOR_ID = ? AND REVIEW_MESSAGE_STATUS = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, aId);
			pstmt.setString(2, "APPROVED");
			results = pstmt.executeQuery();
			while (results.next()) {
				RecommendationDTO review = new RecommendationDTO();
				review.setUserId(results.getInt("USER_ID"));
				review.setReviewMessage(results.getString("REVIEW_MESSAGE"));
				reviews.add(review);
			}
			conn.commit();

			logger.info("Exit GetReviews method of ReviewAndRecommendationDAO");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					logger.error("GetReviews method of ReviewAndRecommendationDAO threw error:"
							+ e2.getMessage());
					e2.printStackTrace();
				}
				logger.error("GetReviews method of ReviewAndRecommendationDAO threw error:"
						+ e1.getMessage());
				e1.printStackTrace();
			}
			logger.error("GetReviews method of ReviewAndRecommendationDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetReviews method of ReviewAndRecommendationDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return reviews;
	}
}
