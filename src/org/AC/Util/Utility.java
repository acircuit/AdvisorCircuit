package org.AC.Util;

public class Utility {

	public String[] getDelimittedStringArray(String str) {
		String[] str1 = null;
		try {
			if (str.length() > 0) {
				str1 = str.split("##");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str1;
	}
}
