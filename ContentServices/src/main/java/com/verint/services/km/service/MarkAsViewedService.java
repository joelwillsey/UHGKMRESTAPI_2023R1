/**
 * 
 */
package com.verint.services.km.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verint.services.km.dao.SearchDAO;
import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.MarkAsViewedResponse;
import com.verint.services.km.model.ContentId;



/**
 * @author pseifert
 *
 */
@Path("/markasviewed")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Service
public class MarkAsViewedService extends BaseService {
	private final Logger LOGGER = LoggerFactory.getLogger(MarkAsViewedService.class);

	@Autowired
	private SearchDAO searchDAO;

	/**
	 * 
	 */
	public MarkAsViewedService() {
		super();
		LOGGER.debug("Entering MarkAsViewedService()");
		LOGGER.debug("Exiting MarkAsViewedService()");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

	/**
	 * 
	 * @param contentId
	 * @param httpRequest
	 * @return
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })	
    public MarkAsViewedResponse markAsViewed(ContentId contentId,
		    @QueryParam("externalSearchId") String externalSearchId,
    		@Context HttpServletRequest httpRequest) {
		LOGGER.info("Entering markAsViewed()");
		LOGGER.debug("Content Id: " + contentId);		

		final MarkAsViewedResponse markAsViewedResponse = new MarkAsViewedResponse();
		
		try {
			// Check for a valid request
			if (contentId == null || contentId.getContentId() == null) {
				LOGGER.error("No valid content ID");
				throw new AppException(500, AppErrorCodes.RATE_CONTENT_ERROR,
					AppErrorMessage.RATE_CONTENT_ERROR);
			}

			// Get the authentication information
			final String[] credentials = getAuthenticatinCredentials(httpRequest);
			LOGGER.debug("Username: " + credentials[0]);
			LOGGER.debug("Password: " + credentials[1]);
			LOGGER.debug("Content Id: " + contentId.getContentId());
			
			// Rate the content
			markAsViewedResponse.setViewUUID(searchDAO.markAsViewed(contentId.getContentId(), contentId.getContentVersion(),
					credentials[0], credentials[1], null, getOIDCToken(httpRequest), externalSearchId));
			
		} catch (Throwable t) {
			LOGGER.error("markAsViewed() Exception", t);
		}
		LOGGER.debug("MarkAsViewedResponse: " + markAsViewedResponse.toString());
		LOGGER.info("Exiting markAsViewed()");
		return markAsViewedResponse;
	}
}
