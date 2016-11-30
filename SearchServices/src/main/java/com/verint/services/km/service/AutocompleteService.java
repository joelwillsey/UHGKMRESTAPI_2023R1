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

import com.verint.services.km.model.AutoSuggestResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
	
	// Services the auto suggest capabilities of the search bar.
	@Path("/suggest")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@GET
	public AutoSuggestResponse getAutoSuggestResponse(@Context HttpServletRequest hrrpRequest,
			@QueryParam("text") String suggestText) throws UnsupportedEncodingException {
		LOGGER.info("Entering getAutoSuggestResponse()");
		AutoSuggestResponse autoSuggestResponse = new AutoSuggestResponse();
		
		// Declaring the URI as empty to pull in the property from the properties file.
		String uri = "";
		try {
			File file = new File("/app_2/verint/projects/uhgiq/restapi/kmservices/connectionPool.properties");
			LOGGER.info(file.toString());
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();
			
			uri = properties.getProperty("connection.autosuggestURI");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Creating the URL to the SOLR Auto Suggest service
		//String uri = "http://localhost:8983/solr/KM/suggest?spellcheck.dictionary=AD_en&spellcheck.build=true&q=";
		String fullUri = uri + java.net.URLEncoder.encode(suggestText, "UTF-8");
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
			// Closes the stream to the url.
			inputStreamReader.close();
			
			// Bunches and bunches of GSON parsing to get the wanted data.
			JsonElement jsonElement = new JsonParser().parse(stringBuilder.toString());
			JsonObject jsonObject = jsonElement.getAsJsonObject();
			JsonObject spellcheck = jsonObject.getAsJsonObject("spellcheck");
			JsonArray suggestions = spellcheck.getAsJsonArray("suggestions");
			JsonArray collations = spellcheck.getAsJsonArray("collations");
			
			// Runs a check to see if the size of suggestions is 0, if it's not it populates it, else it returns the default.
			if( suggestions.size() != 0){
				JsonElement elementsObject = suggestions.get(1);
				JsonObject elements = elementsObject.getAsJsonObject();
				
				// Populates the response object.
				int numFound = elements.get("numFound").getAsInt();
				int startOffset = elements.get("startOffset").getAsInt();
				int endOffset = elements.get("endOffset").getAsInt();
				
				Set<String> suggestionSet = new HashSet<String>();
				
				for (int i = 1; i < collations.size() && i < 10; i+=2){
					JsonElement collation = collations.get(i);
					JsonObject collationSet = collation.getAsJsonObject();
					suggestionSet.add(collationSet.get("collationQuery").getAsString());
				}
				
				autoSuggestResponse.setNumFound(numFound);
				autoSuggestResponse.setStartOffset(startOffset);
				autoSuggestResponse.setEndOffset(endOffset);
				autoSuggestResponse.setSuggestion(suggestionSet);
			}
			
		} catch (Exception e) {
			throw new RuntimeException("Exception while calling URL " + fullUri, e);
		}
		LOGGER.info("Exiting getAutoSuggestResponse()");
		return autoSuggestResponse;
	}
}
