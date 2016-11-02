package com.verint.services.km.service;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verint.services.km.dao.NewOrChangedDAO;
import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.NewOrChangedRequest;
import com.verint.services.km.model.NewOrChangedResponse;

/**
 * @author ERaygorodetskiy
 *
 */

@Path("/neworchanged")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Service
public class NewOrChangedService extends BaseService {
	private final Logger LOGGER = LoggerFactory.getLogger(NewOrChangedService.class);

	@Autowired
	private NewOrChangedDAO newOrChangedDAO;
	
	/**
	 * 
	 */
	public NewOrChangedService() {
		super();
		LOGGER.debug("Entering NewOrChangedService()");
		LOGGER.debug("Exiting NewOrChangedService()");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}
	
	/**
	 * 
	 * @param query
	 * @param tags
	 * @param categories
	 * @param page
	 * @param size
	 * @param sort
	 * @param httpRequest
	 * @return
	 * @throws AppException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public NewOrChangedResponse newOrChangedSearch(@QueryParam("locale") String locale,
			@QueryParam("page") BigInteger page,
			@QueryParam("size") BigInteger size,
			@QueryParam("applicationID") String applicationID,
			@QueryParam("kbase_tags") String kbase_tags,
			@Context HttpServletRequest httpRequest) throws AppException {
		
		LOGGER.info("Entering search()");
		NewOrChangedResponse newOrChangedResponse = null;
		
		try {
			LOGGER.debug("locale: "+locale);
			LOGGER.debug("Page: "+page);
			LOGGER.debug("Size: "+size);
			LOGGER.debug("applicationID: "+applicationID);
			LOGGER.debug("kbase_tags: "+kbase_tags);
			
			// Check for valid fields
			if (locale == null || locale.length() == 0){
				locale = "en-US";
			}
			if (applicationID == null || applicationID.length() == 0){
				applicationID = "1";
			}
			if (kbase_tags == null || kbase_tags.length() == 0){
				kbase_tags = "";
			}
			
			// Get the authentication information
			String[] credentials = getAuthenticatinCredentials(httpRequest);
			
			// Create the new or changed request
			final NewOrChangedRequest newOrChangedRequest = new NewOrChangedRequest();
			newOrChangedRequest.setLocale(locale);
			newOrChangedRequest.setUsername(credentials[0]);
			newOrChangedRequest.setPassword(credentials[1]);
			newOrChangedRequest.setApplicationID(applicationID);
			newOrChangedRequest.setKBaseTags(kbase_tags);
			newOrChangedRequest.setPaginationStartIndex(page);
			newOrChangedRequest.setMaxNumberOfGroupResults(size);
			newOrChangedRequest.setMaxNumberOfUnitsPerGroup(size);
			
			LOGGER.debug("NewOrChangedRequest: " +newOrChangedRequest);
			
			// Do the search and get the response back
			newOrChangedResponse = newOrChangedDAO.newOrChangedQuery(newOrChangedRequest);
			if (newOrChangedResponse == null){
				newOrChangedResponse = new NewOrChangedResponse();
			}
			
			newOrChangedResponse.setPage(newOrChangedRequest.getPaginationStartIndex());
			newOrChangedResponse.setSize(newOrChangedRequest.getMaxNumberOfUnitsPerGroup());
			
			Double totalPages = new Double(0);
			if (newOrChangedResponse.getNumberOfResults() != null && newOrChangedRequest.getMaxNumberOfUnitsPerGroup() != null) {
				totalPages = Math.ceil(newOrChangedResponse.getNumberOfResults().doubleValue()/newOrChangedRequest.getMaxNumberOfUnitsPerGroup().doubleValue());
				newOrChangedResponse.setTotalPages(totalPages.intValue());
			}
			
			
		}catch (AppException ae) {
			LOGGER.error("AppException in search()", ae);
			throw ae;
		} catch (Throwable t) {
			LOGGER.error("Unexpected exception in search()", t);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
		}
		LOGGER.debug("NewOrChangedResponse: " + newOrChangedResponse);
		LOGGER.info("Exiting search()");
		return newOrChangedResponse;
	}
}
