package org.AC.Util;

import java.util.ArrayList;
import java.util.List;

import org.AC.dto.AdvisorEducationDTO;
import org.AC.dto.AdvisorProfileDTO;
import org.AC.dto.ProfessionalBackgroundDTO;

public class AdvisorEducationObject {
		
	public List<AdvisorEducationDTO> getEducationObject(int aId,String ug, String pg,String[] others ){
		List<AdvisorEducationDTO> education = new ArrayList<AdvisorEducationDTO>();
		try{
			if(ug.length() > 0){
				AdvisorEducationDTO educationUG = new AdvisorEducationDTO();
				educationUG.setAdvisorId(aId);
				educationUG.setEducation(ug);
				educationUG.setType("UG");
				education.add(educationUG);
			}
			if(pg != null &&  pg.length()>0 && !pg.equals("")){
				AdvisorEducationDTO educationPG = new AdvisorEducationDTO();
				educationPG.setAdvisorId(aId);
				educationPG.setEducation(pg);
				educationPG.setType("PG");
				education.add(educationPG);
			}
			if(others != null && others.length>0){
				for (String other : others) {
					if(!other.equals("")){
						AdvisorEducationDTO educationOthers = new AdvisorEducationDTO();
						educationOthers.setAdvisorId(aId);
						educationOthers.setEducation(other);
						educationOthers.setType("OTHERS");
						education.add(educationOthers);
					}
				}	
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
		return education;
	}
}
