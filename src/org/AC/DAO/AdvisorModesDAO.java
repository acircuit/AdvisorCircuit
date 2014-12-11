package org.AC.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.AC.JDBC.Util;
import org.AC.controller.SearchController;
import org.AC.dto.AdvisorModeDTO;
import org.apache.log4j.Logger;

public class AdvisorModesDAO {
	Connection conn = null;
	Statement stmt = null;
	private static final Logger logger = Logger.getLogger(AdvisorModesDAO.class);
	/*This function is to fetch all Services,Modes and prices offered by the advisor.*/
	
public List<AdvisorModeDTO> getAdvisorModesDetails(String advisorId) { 
	
		logger.info("Entered getAdvisorModesDetails method of AdvisorModesDAO");
		List<AdvisorModeDTO> list = new ArrayList<AdvisorModeDTO>();
		conn =Util.connect();
		String query ="SELECT * FROM advisormodes WHERE ADVISOR_ID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1,advisorId);
			ResultSet results = pstmt.executeQuery();
			while(results.next()){
				AdvisorModeDTO mode = new AdvisorModeDTO();
				mode.setService(results.getString("SERVICE"));
				mode.setModeOfCommunication(results.getString("MODE_OF_COMMUNICATION"));
				mode.setPrice(results.getString("PRICE"));
				list.add(mode);
			}
			logger.info("Exit getAdvisorModesDetails method of AdvisorModesDAO");
		} catch (SQLException e) {
			logger.error("getAdvisorModesDetails method of AdvisorModesDAO threw error:"+e.getMessage());
			e.printStackTrace();
		}
		return list;
		
	}	
}
