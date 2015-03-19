package org.AC.Util;

import java.util.ArrayList;
import java.util.List;

import org.AC.dto.ProfessionalBackgroundDTO;

public class ProfessionalBackgroundObject {
	
	public List<ProfessionalBackgroundDTO> getProfessionalBackgroundObject(String[] company, String[] designation,String[] duration){
		List<ProfessionalBackgroundDTO> list = new ArrayList<ProfessionalBackgroundDTO>();
		try{
			if(company.length > 0 && designation.length > 0 && duration.length >0){
				int arrayLength = company.length; 
				for(int i=0 ; i< arrayLength ; i++){
					ProfessionalBackgroundDTO professionalBackgroundObject = new ProfessionalBackgroundDTO();
					if(!company[i].equals("") && !designation[i].equals("") && !duration[i].equals("")){
						professionalBackgroundObject.setCompany(company[i]);
						professionalBackgroundObject.setDesignation(designation[i]);
						professionalBackgroundObject.setDuration(duration[i]);
						list.add(professionalBackgroundObject);
					}
				}		
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public List<ProfessionalBackgroundDTO> getProfDescription(List<Integer> profId,String[] description0,String[] description1,String[] description2,String[] description3,String[] description4,
			String[] description5,String[] description6,String[] description7,String[] description8,String[] description9,String[] description10){
		List<ProfessionalBackgroundDTO> list = new ArrayList<ProfessionalBackgroundDTO>();
		int i=0;
		try{
			if( description0 != null && description0.length > 0){
				ProfessionalBackgroundDTO prof = new ProfessionalBackgroundDTO();
				prof.setProfId(profId.get(0));
				prof.setDescriptionArray(description0);
				list.add(prof);
				i++;
			}
			if( description1 != null && description1.length > 0){
				ProfessionalBackgroundDTO prof = new ProfessionalBackgroundDTO();
				prof.setProfId(profId.get(1));
				prof.setDescriptionArray(description1);
				list.add(prof);
				i++;
			}
			if( description2 != null && description2.length > 0){
				ProfessionalBackgroundDTO prof = new ProfessionalBackgroundDTO();
				prof.setProfId(profId.get(2));
				prof.setDescriptionArray(description2);
				list.add(prof);
				i++;
			}
			if( description3 != null && description3.length > 0){
				ProfessionalBackgroundDTO prof = new ProfessionalBackgroundDTO();
				prof.setProfId(profId.get(3));
				prof.setDescriptionArray(description3);
				list.add(prof);
				i++;
			}
			if(description4 != null && description4.length > 0){
				ProfessionalBackgroundDTO prof = new ProfessionalBackgroundDTO();
				prof.setProfId(profId.get(4));
				prof.setDescriptionArray(description4);
				list.add(prof);
				i++;
			}
			if( description5 != null && description5.length > 0){
				ProfessionalBackgroundDTO prof = new ProfessionalBackgroundDTO();
				prof.setProfId(profId.get(5));
				prof.setDescriptionArray(description5);
				list.add(prof);
				i++;
			}
			if( description6 != null && description6.length > 0){
				ProfessionalBackgroundDTO prof = new ProfessionalBackgroundDTO();
				prof.setProfId(profId.get(6));
				prof.setDescriptionArray(description6);
				list.add(prof);
				i++;
			}
			if( description7 != null && description7.length > 0){
				ProfessionalBackgroundDTO prof = new ProfessionalBackgroundDTO();
				prof.setProfId(profId.get(7));
				prof.setDescriptionArray(description7);
				list.add(prof);
				i++;
			}
			if( description8 != null && description8.length > 0){
				ProfessionalBackgroundDTO prof = new ProfessionalBackgroundDTO();
				prof.setProfId(profId.get(8));
				prof.setDescriptionArray(description8);
				list.add(prof);
				i++;
			}
			if( description9 != null && description9.length > 0){
				ProfessionalBackgroundDTO prof = new ProfessionalBackgroundDTO();
				prof.setProfId(profId.get(9));
				prof.setDescriptionArray(description9);
				list.add(prof);
				i++;
			}
			if( description10 != null && description10.length > 0){
				ProfessionalBackgroundDTO prof = new ProfessionalBackgroundDTO();
				prof.setProfId(profId.get(10));
				prof.setDescriptionArray(description10);
				list.add(prof);
				i++;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
}
