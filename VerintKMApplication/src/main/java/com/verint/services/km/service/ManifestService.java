package com.verint.services.km.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.verint.services.km.model.ManifestResponse;

@Path("/manifest")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Service
public class ManifestService extends BaseService{
	private final Logger LOGGER = LoggerFactory.getLogger(ManifestService.class);

	/**
	 * 
	 */
	public ManifestService(){
		super();
		LOGGER.debug("Entering ManifestService()");
		LOGGER.debug("Exiting ManifestService()");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args){
	}
	
	@Path("/version")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@GET
	public ManifestResponse getVersionNumberResponse() throws IOException{
		LOGGER.info("Entering getVersionNumberResponse()");
		ManifestResponse manifestResponse = new ManifestResponse();
		
		ManifestVars.getValue("Build-Version");
		
		manifestResponse.setVersionNumber("");		
		
		return manifestResponse;
	}
}
