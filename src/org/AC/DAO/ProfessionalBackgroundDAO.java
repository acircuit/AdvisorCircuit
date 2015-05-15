package org.AC.DAO;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.AC.JDBC.ConnectionFactory;
import org.AC.JDBC.Util;
import org.AC.Util.Utility;
import org.AC.dto.ProfessionalBackgroundDTO;
import org.apache.log4j.Logger;

public class ProfessionalBackgroundDAO {
	private static final Logger logger = Logger
			.getLogger(ProfessionalBackgroundDAO.class);
	Connection conn = null;
	Statement stmt = null;

	public void addProfessionalBackground(
			List<ProfessionalBackgroundDTO> professionalBackgrounds,
			int advisorId) {

		if (!professionalBackgrounds.isEmpty() && (advisorId != 0)) {
			for (ProfessionalBackgroundDTO profession : professionalBackgrounds) {

				try {
					conn = ConnectionFactory.getConnection();
					conn.setAutoCommit(false);
					String query = "insert into professionalbackground"
							+ "(ADVISOR_ID,COMPANY,DESIGNATION,DESCRIPTION) values"
							+ "(?, ?, ?, ?)";
					PreparedStatement pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, advisorId);
					pstmt.setString(2, profession.getCompany());
					pstmt.setString(3, profession.getDesignation());
					pstmt.setString(4, profession.getDescription());
					int result = pstmt.executeUpdate();
					if (result > 0) {
						conn.commit();
						continue;
					} else {
						break;
					}
				} catch (SQLException e) {
					try {
						conn.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (PropertyVetoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

		}
	}

	public List<ProfessionalBackgroundDTO> getProfessionalBackground(
			String advisorId) {

		List<ProfessionalBackgroundDTO> list = new ArrayList<ProfessionalBackgroundDTO>();
		try {
			if (!advisorId.equals("0")) {

				conn = ConnectionFactory.getConnection();
				conn.setAutoCommit(false);
				String query = "SELECT * FROM advisorprofessionalbackground WHERE ADVISOR_ID= ?";
				PreparedStatement pstmt1 = conn.prepareStatement(query);
				pstmt1.setString(1, advisorId);
				ResultSet result = pstmt1.executeQuery();
				while (result.next()) {
					ProfessionalBackgroundDTO profession = new ProfessionalBackgroundDTO();
					profession.setProfId(result.getInt("PROF_ID"));
					profession.setAdvisorId(result.getInt("ADVISOR_ID"));
					profession.setCompany(result.getString("COMPANY"));
					profession.setDesignation(result.getString("DESIGNATION"));
					profession.setDuration(result.getString("DURATION"));

					list.add(profession);
				}
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
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public List<ProfessionalBackgroundDTO> GetProfessionalDescription(
			String advisorId) {
		logger.info("Entered GetProfessionalBackgroundDescription method of ProfessionalBackgroundDAO");
		List<ProfessionalBackgroundDTO> description = new ArrayList<ProfessionalBackgroundDTO>();
		try {
			conn = ConnectionFactory.getConnection();
			conn.setAutoCommit(false);
			String query = "SELECT PROF_ID,DESCRIPTION FROM advisorprofdescription WHERE ADVISOR_ID =?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, advisorId);
			ResultSet results = pstmt.executeQuery();
			while (results.next()) {
				ProfessionalBackgroundDTO prof = new ProfessionalBackgroundDTO();
				prof.setProfId(results.getInt("PROF_ID"));
				prof.setDescription(results.getString("DESCRIPTION"));
				description.add(prof);
			}
		} catch (SQLException e) {
			logger.error("GetProfessionalDescription method of ProfessionalBackgroundDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("GetProfessionalDescription method of ProfessionalBackgroundDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			logger.error("GetProfessionalDescription method of ProfessionalBackgroundDAO threw error:"
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("GetProfessionalDescription method of ProfessionalBackgroundDAO threw error:"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		logger.info("Entered GetProfessionalDescription method of ProfessionalBackgroundDAO");
		return description;
	}
}
