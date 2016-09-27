package com.verint.services.km.util;

public class PropertyUtil {

	public static String GetBasePath() {
		final String OSName = System.getProperty("os.name");
		String returnValue = "/app_2/verint/projects/uhgiq/restapi/kmservices/";
		
		if (OSName != null && OSName.length() > 0 && OSName.startsWith("Windows")) {
			returnValue = "C:\\app_2\\verint\\projects\\uhgiq\\restapi\\kmservices\\";
		}
		
		return returnValue;
	}
	
	public static String getExternalFilesPath() {
		return GetBasePath() + "externalFiles.properties";
	}
	
	public static String getConnectionPoolPath() {
		return GetBasePath() + "connectionPool.properties";
	}
	
	public static String getSoapConnectionPath() {
		return GetBasePath() + "soapConnection.properties";
	}

}