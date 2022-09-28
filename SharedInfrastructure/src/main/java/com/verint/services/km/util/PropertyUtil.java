package com.verint.services.km.util;

import java.util.Properties;

public class PropertyUtil {

	public static String GetBasePath() {
		Properties property = System.getProperties();		
		String returnValue = (String)property.get("configLocation");
		
		return returnValue;
	}
	
	public static String getExternalFilesPath() {
		return GetBasePath() + "\\externalFiles.properties";
	}
	
	public static String getConnectionPoolPath() {
		return GetBasePath() + "\\connectionPool.properties";
	}
	
	public static String getConnectionPoolRSPath() {
		return GetBasePath() + "\\connectionPoolRS.properties";
	}
	
	public static String getSoapConnectionPath() {
		return GetBasePath() + "\\soapConnection.properties";
	}

}