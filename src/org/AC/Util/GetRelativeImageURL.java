package org.AC.Util;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class GetRelativeImageURL {
	
	private static final Logger logger = Logger.getLogger(GetTimeLeftForReply.class);     

	
	public String  getImageURL(String image){
		
		BasicConfigurator.configure();
		logger.info("Entered getImageURL method of GetRelativeImageURL");
		String relImage = "";
		try{
			if(!("").equals(image)){
				relImage = image.substring(17, image.length());
			}		
		}catch(Exception e){
			logger.error("getImageURL method of GetRelativeImageURL threw error:"+e.getMessage());
			e.printStackTrace();
		}
		logger.info("Exit getImageURL method of GetRelativeImageURL");
		return relImage;

	}
}
