package org.AC.Util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class GetRelativeImageURL {

	private static final Logger logger = Logger
			.getLogger(GetRelativeImageURL.class);

	public String getImageURL(String image) {
		logger.info("Entered getImageURL method of GetRelativeImageURL");
		String relImage = "";
		Properties prop = new Properties();
		InputStream resourceAsStream = Thread.currentThread()
				.getContextClassLoader()
				.getResourceAsStream("Resources/Path.properties");
		try {
			prop.load(resourceAsStream);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if (!("").equals(image)) {
				relImage = StringUtils.substringAfter(image,
						prop.getProperty("IMAGE_PATH"));
			}
		} catch (Exception e) {
			logger.error("getImageURL method of GetRelativeImageURL threw error:"
					+ e.getMessage());
			e.printStackTrace();
		}
		logger.info("Exit getImageURL method of GetRelativeImageURL");
		return relImage;

	}
}
