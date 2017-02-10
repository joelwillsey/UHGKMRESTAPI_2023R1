package com.verint.services.km.service;

import java.io.FileInputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.verint.services.km.util.ConfigInfo;

@Path("/property")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Service
public class PropertyReader extends BaseService{
	private final Logger LOGGER = LoggerFactory.getLogger(PropertyReader.class);
	
	/**
	 * 
	 */
	public PropertyReader(){
		super();
		LOGGER.debug("Entering PropertyReader()");
		LOGGER.debug("Exiting PropertyReader()");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args){
	}
	
	// Returns the string
	@Path("/read")
	@Produces({MediaType.APPLICATION_JSON})
	@GET
	public String getPropertyResponse(@Context HttpServletRequest httpRequest,
			@QueryParam("property") String propertyName){
		LOGGER.debug("Entering getPropertyResponse()");
		
		Properties prop = new Properties();
		String propertyAddress, value = null;
		try {
			
			ConfigInfo kmConfiguration = new ConfigInfo();
			//LOGGER.debug("ConfigInfo: \n" + kmConfiguration.toString());
		
			//if(System.getProperty("os.name").startsWith("Windows")) {
			//	propertyAddress = "C:\\app_2\\verint\\projects\\uhgiq\\restapi\\kmservices\\propertyReader.properties";
			//} else {
			//	propertyAddress = "/app_2/verint/projects/uhgiq/restapi/kmservices/propertyReader.properties";
			//}
			
			propertyAddress = kmConfiguration.getReaderFile();
			
			prop.load(new FileInputStream(propertyAddress));
			
			value = prop.getProperty(propertyName);
			
			LOGGER.debug("getPropertyResponse: " + propertyName + "=" +  value);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		LOGGER.debug("Exiting getPropertyResponse()");
		return value;
	}

}
