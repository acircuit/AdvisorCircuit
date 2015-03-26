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
import org.AC.dto.AdvisorServiceDTO;
import org.AC.dto.ProfessionalBackgroundDTO;

public class AdvisorServicesDAO {
	
	Connection conn = null;
	Statement stmt = null;
	
	public void setAdvisorServiceDetails( String service , 	int advisorId , String description) { 
		
		if(!service.isEmpty() && advisorId!=0 && !description.isEmpty()){
			
			
			
			try {
				conn =ConnectionFactory.getConnection();
				conn.setAutoCommit(false);
				String query = "insert into services"+"(ADVISOR_ID,SERVICE,DESCRIPTION) values" + "(?, ?, ?)";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setInt(1,advisorId);
				pstmt.setString(2,service);
				pstmt.setString(3,description);
				pstmt.executeUpdate();
				conn.commit();
			} catch (SQLException e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (PropertyVetoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void setAdvisorModes( String service , 	int advisorId , String mode,String price) { 
		
		if(!service.isEmpty() && advisorId!=0 && !mode.isEmpty() && !price.isEmpty()){
			
			try {
				conn =ConnectionFactory.getConnection();
				conn.setAutoCommit(false);
				String query = "insert into advisormodes"+"(ADVISOR_ID,SERVICE,MODE_OF_COMMUNICATION,PRICE) values" + "(?, ?, ?,?)";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setInt(1,advisorId);
				pstmt.setString(2,service);
				pstmt.setString(3,mode);
				pstmt.setString(4,price);
				pstmt.executeUpdate();
				conn.commit();
			} catch (SQLException e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (PropertyVetoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public List<AdvisorServiceDTO> getAdvisorServiceDetails(String advisorId) { 
		
		List<AdvisorServiceDTO> list = new ArrayList<AdvisorServiceDTO>();
		
		try {
			conn =ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query ="SELECT * FROM advisorservices WHERE ADVISOR_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1,advisorId);
			ResultSet results = pstmt.executeQuery();
			while(results.next()){
				AdvisorServiceDTO service = new AdvisorServiceDTO();
				service.setAdvisorId(results.getInt("ADVISOR_ID"));
				service.setService(results.getString("SERVICE"));
				service.setDescription(results.getString("DESCRIPTION"));
				service.setIsFree(results.getInt("ISFREE"));
				service.setDiscount(results.getInt("DISCOUNT_AC"));
				list.add(service);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	
	}	
}
