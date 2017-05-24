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

import com.verint.services.km.dao.FeaturedDAO;
import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.FeaturedRequest;
import com.verint.services.km.model.FeaturedResponse;

/**
 * @author Hbendale
 *
 */

@Path("/featured")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Service
public class FeaturedService extends BaseService {
	private final Logger LOGGER = LoggerFactory.getLogger(FeaturedService.class);

	@Autowired
	private FeaturedDAO featuredDAO;
	
	/**
	 * 
	 */
	public FeaturedService() {
		super();
		LOGGER.debug("Entering FeaturedService()");
		LOGGER.debug("Exiting FeaturedService()");
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
	public FeaturedResponse featuredSearch(@QueryParam("locale") String locale,
			@QueryParam("page") BigInteger page,
			@QueryParam("size") BigInteger size,
			@QueryParam("applicationID") String applicationID,
			@QueryParam("kbase_tags") String kbase_tags,
			@Context HttpServletRequest httpRequest) throws AppException {
		
		LOGGER.info("Entering search()");
		FeaturedResponse featuredResponse = null;
		
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
			final FeaturedRequest featuredRequest = new FeaturedRequest();
			featuredRequest.setLocale(locale);
			featuredRequest.setUsername(credentials[0]);
			featuredRequest.setPassword(credentials[1]);
			featuredRequest.setApplicationID(applicationID);
			featuredRequest.setKBaseTags(kbase_tags);
			featuredRequest.setPaginationStartIndex(page);
			featuredRequest.setMaxNumberOfGroupResults(new BigInteger("10"));
			featuredRequest.setMaxNumberOfUnitsPerGroup(size);
			LOGGER.debug("FeaturedRequest: " +featuredRequest);
			
			// Do the search and get the response back
			featuredResponse = featuredDAO.featuredQuery(featuredRequest);
			if (featuredResponse == null){
				featuredResponse = new FeaturedResponse();
			}
			
			featuredResponse.setPage(featuredRequest.getPaginationStartIndex());
			featuredResponse.setSize(featuredRequest.getMaxNumberOfUnitsPerGroup());
			
			Double totalPages = new Double(0);
			if (featuredResponse.getNumberOfResults() != null && featuredRequest.getMaxNumberOfUnitsPerGroup() != null) {
				totalPages = Math.ceil(featuredResponse.getNumberOfResults().doubleValue()/featuredRequest.getMaxNumberOfUnitsPerGroup().doubleValue());
				LOGGER.debug("totalPages: " + totalPages);
				featuredResponse.setTotalPages(totalPages.intValue());
			}
			
			
		}catch (AppException ae) {
			LOGGER.error("AppException in search()", ae);
			throw ae;
		} catch (Throwable t) {
			LOGGER.error("Unexpected exception in search()", t);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
		}
		LOGGER.debug("FeaturedResponse: " + featuredResponse);
		LOGGER.info("Exiting search()");
		return featuredResponse;
	}
}
