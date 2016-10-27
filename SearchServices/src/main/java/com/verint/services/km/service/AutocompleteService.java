package com.verint.services.km.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.MigratableReferenceId;
import com.verint.services.km.model.AutoSuggestResponse;

//import com.fasterxml.jackson.core.*;

@Path("/autocomplete")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Service
public class AutocompleteService extends BaseService{
	private final Logger LOGGER = LoggerFactory.getLogger(AutocompleteService.class);
	
	/**
	 * 
	 */
	public AutocompleteService(){
		super();
		LOGGER.debug("Entering AutocompleteService()");
		LOGGER.debug("Exiting AutocompleteService()");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args){
	}
	
	@Path("/suggest")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@GET
	public AutoSuggestResponse getAutoSuggestResponse(@Context HttpServletRequest hrrpRequest,
			@QueryParam("text") String suggestText) {
		LOGGER.info("Entering getAutoSuggestResponse()");
		AutoSuggestResponse autoSuggestResponse = new AutoSuggestResponse();
		
		// Creating the URL to the SOLR Auto Suggest service
		String uri = "http://localhost:8983/solr/KM/suggest?spellcheck.dictionary=AD_en&spellcheck.build=true&q=";
		String fullUri = uri + suggestText;
		LOGGER.info("Autosuggest suggestion url: "+ fullUri);
		
		// Declaring tools to pull in the JSON file provided by SOLR
		StringBuilder stringBuilder = new StringBuilder();
		URLConnection urlConnection = null;
		InputStreamReader inputStreamReader = null;
		
		try {
			// Creates open connection with the SOLR url
			URL url = new URL(fullUri);
			urlConnection = url.openConnection();
			
			if (urlConnection != null) {
				urlConnection.setReadTimeout(15000);
			}
			if (urlConnection != null && urlConnection.getInputStream() != null) {
				inputStreamReader = new InputStreamReader(urlConnection.getInputStream(),
						Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				
				if (bufferedReader != null) {
					int characterPointer;
					while ((characterPointer = bufferedReader.read()) != -1) {
						stringBuilder.append((char) characterPointer);
					}
					bufferedReader.close();
				}
			}
			
			inputStreamReader.close();
			
		} catch (Exception e) {
			throw new RuntimeException("Exception while calling URL" + fullUri, e);
		}
		
		
		LOGGER.info("\nOutput: \n" + stringBuilder.toString());
		LOGGER.info("Exiting getAutoSuggestResponse()");
		return autoSuggestResponse;
	}
}
