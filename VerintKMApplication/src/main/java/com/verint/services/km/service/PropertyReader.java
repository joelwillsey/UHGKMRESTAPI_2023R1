package com.verint.services.km.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.verint.services.km.model.PropertyResponse;

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
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@GET
	public PropertyResponse getPropertyResponse(@Context HttpServletRequest httpRequest,
			@QueryParam("address") String propertyAddress,
			@QueryParam("property") String propertyName){
		LOGGER.info("Entering getPropertyResponse()");
		PropertyResponse propertyResponse = new PropertyResponse();
		
		Properties prop = new Properties();
		
		try {
			prop.load(new FileInputStream(propertyAddress));
			
			propertyResponse.setPropertiesString(prop.getProperty(propertyName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		LOGGER.info("Exiting getPropertyResponse()");
		return propertyResponse;
	}

}
