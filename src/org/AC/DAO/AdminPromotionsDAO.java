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
import org.AC.dto.AdvisorRegistrationCheckEmailDTO;
import org.AC.dto.PromotionsDTO;
import org.apache.log4j.Logger;

public class AdminPromotionsDAO {
	private static final Logger logger = Logger
			.getLogger(AdminPromotionsDAO.class);
	Connection conn = null;
	Statement stmt = null;

	public List<PromotionsDTO> GetPromotions(){
		
		logger.info("Entered GetPromotions method of AdminPromotionsDAO");
		List<PromotionsDTO> promo = new ArrayList<PromotionsDTO>();		
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT * FROM promotions ";
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet results = pstmt.executeQuery();
			while(results.next()){
				PromotionsDTO pro = new PromotionsDTO();
				pro.setId(results.getInt("ID"));
				pro.setPromo(results.getString("PROMOTION"));
				pro.setIsActive(results.getBoolean("IS_ACTIVE"));
				promo.add(pro);
			}
		} catch (SQLException e) {
			logger.error("GetPromotions method of AdminPromotionsDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("GetPromotions method of AdminPromotionsDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("GetPromotions method of AdminPromotionsDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetPromotions method of AdminPromotionsDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		
		logger.info("Entered GetPromotions method of AdminPromotionsDAO");
		return promo;
	}
	
	public Boolean UpdatePromotion(String id,String activate){
		logger.info("Entered UpdatePromotion method of AdminPromotionsDAO");
		Boolean isCommit = false;
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "UPDATE promotions SET IS_ACTIVE= ? WHERE ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			if(activate.equals("true")){
				pstmt.setBoolean(1, true);
			}else{
				pstmt.setBoolean(1, false);
			}
			pstmt.setString(2,id );
			int result = pstmt.executeUpdate(); 
			if(result >0) {
				conn.commit();
				isCommit = true;
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("UpdatePromotion method of AdminPromotionsDAO threw error:"+e.getMessage());
				e1.printStackTrace();
			}	
			logger.error("UpdatePromotion method of AdminPromotionsDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("UpdatePromotion method of AdminPromotionsDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("UpdatePromotion method of AdminPromotionsDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("UpdatePromotion method of AdminPromotionsDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}		
		logger.info("Exit UpdatePromotion method of AdminPromotionsDAO");
		return isCommit;
	}
	
	public int CheckPromotionActive(int id){
		
		logger.info("Entered CheckPromotionActive method of AdminPromotionsDAO");
		int promoId = 0;		
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT ID FROM promotions WHERE ID=? AND IS_ACTIVE=? ";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.setBoolean(2, true);
			ResultSet results = pstmt.executeQuery();
			if(results.first()){
				promoId = results.getInt("ID");
			}
		} catch (SQLException e) {
			logger.error("CheckPromotionActive method of AdminPromotionsDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("CheckPromotionActive method of AdminPromotionsDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("CheckPromotionActive method of AdminPromotionsDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("CheckPromotionActive method of AdminPromotionsDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		
		logger.info("Entered CheckPromotionActive method of AdminPromotionsDAO");
		return promoId;
	}
	
	public int CheckPromoCode(String id){
		logger.info("Entered CheckPromoCode method of AdminPromotionsDAO");
		int userId = 0;		
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT USER_ID FROM user_referral WHERE REFERRAL_CODE=? ";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			ResultSet results = pstmt.executeQuery();
			if(results.first()){
				userId = results.getInt("USER_ID");
			}
		} catch (SQLException e) {
			logger.error("CheckPromoCode method of AdminPromotionsDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("CheckPromoCode method of AdminPromotionsDAO threw error:"+e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("CheckPromoCode method of AdminPromotionsDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("CheckPromoCode method of AdminPromotionsDAO threw error:"+e.getMessage());
				e.printStackTrace();
			}
		}
		
		logger.info("Entered CheckPromoCode method of AdminPromotionsDAO");
		return userId;
	}
}
