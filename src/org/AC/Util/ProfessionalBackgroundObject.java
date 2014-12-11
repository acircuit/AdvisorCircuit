package org.AC.Util;

import java.util.ArrayList;
import java.util.List;

import org.AC.dto.ProfessionalBackgroundDTO;

public class ProfessionalBackgroundObject {
	
	public List<ProfessionalBackgroundDTO> getProfessionalBackgroundObject(String[] company, String[] designation,String[] duration , String[] description ){
		List<ProfessionalBackgroundDTO> list = new ArrayList<ProfessionalBackgroundDTO>();
		try{
			if(company.length > 0 && designation.length > 0 && description.length > 0){
				int arrayLength = company.length; 
				for(int i=0 ; i< arrayLength ; i++){
					ProfessionalBackgroundDTO professionalBackgroundObject = new ProfessionalBackgroundDTO();
					professionalBackgroundObject.setCompany(company[i]);
					professionalBackgroundObject.setDescription(description[i]);
					professionalBackgroundObject.setDesignation(designation[i]);
					//professionalBackgroundObject.setDuration(duration[i]);
					list.add(professionalBackgroundObject);
				}		
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
}
