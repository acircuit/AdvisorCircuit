package org.AC.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.AC.JDBC.Util;
import org.AC.Util.Utility;
import org.AC.dto.ProfessionalBackgroundDTO;

public class ProfessionalBackgroundDAO {
	
	Connection conn = null;
	Statement stmt = null;
	   
	
	public void addProfessionalBackground(List<ProfessionalBackgroundDTO> professionalBackgrounds, int advisorId){
		
		if(!professionalBackgrounds.isEmpty() && (advisorId != 0)) {
			for (ProfessionalBackgroundDTO profession : professionalBackgrounds) {
				conn =Util.connect();
				String query = "insert into professionalbackground"+"(ADVISOR_ID,COMPANY,DESIGNATION,DESCRIPTION) values" + "(?, ?, ?, ?)";
				try {
					PreparedStatement pstmt = conn.prepareStatement(query);
					pstmt.setInt(1,advisorId);
					pstmt.setString(2,profession.getCompany());
					pstmt.setString(3,profession.getDesignation());
					pstmt.setString(4,profession.getDescription());
					int result = pstmt.executeUpdate();
					if(result > 0) {
						conn.commit();
						continue;
					}else{
						break;
					}
				} catch (SQLException e) {
					try {
						conn.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
			}

		}
	}
	
	public List<ProfessionalBackgroundDTO> getProfessionalBackground( String advisorId){
		

		List<ProfessionalBackgroundDTO> list = new ArrayList<ProfessionalBackgroundDTO>();
		try {
			if(!advisorId.equals("0")){
				
			     conn= Util.connect();
				String query ="SELECT * FROM professionalbackground WHERE ADVISOR_ID= ?";
				PreparedStatement pstmt1 = conn.prepareStatement(query);
				pstmt1.setString(1, advisorId);
				ResultSet result = pstmt1.executeQuery();
				while(result.next()){
					ProfessionalBackgroundDTO profession = new ProfessionalBackgroundDTO();
					profession.setAdvisorId(result.getInt("ADVISOR_ID"));
					profession.setCompany(result.getString("COMPANY"));
					profession.setDesignation(result.getString("DESIGNATION"));
					String description = result.getString("DESCRIPTION");
					Utility util = new Utility();
					String[] descriptionStringArray = util.getDelimittedStringArray(description);
					profession.setDescriptionArray(descriptionStringArray);
					list.add(profession);
				}
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return list;
	
	
	
	}
	
	
}
	
