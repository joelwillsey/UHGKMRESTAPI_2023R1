/**
 * 
 */
package com.verint.services.km.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.verint.services.km.dao.NewAlertsDAO;
import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.AlertsResponse;
import com.verint.services.km.model.CrossTagResponse;


/**
 * @author jmiller
 *
 */
@Path("/crosstags")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Service
public class NewAlertsService extends BaseService {
	private final Logger LOGGER = LoggerFactory.getLogger(NewAlertsService.class);

	@Autowired
	private NewAlertsDAO newAlertsDAO;

	/**
	 * 
	 */
	public NewAlertsService() {
		super();
		LOGGER.debug("Entering NewAlertsService()");
		LOGGER.debug("Exiting NewAlertsService()");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

	/**
	 * 
	 * @param sourcetag
	 * @param targettagset
	 * @param httpRequest
	 * @return
	 */
	@Path("/getread")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public AlertsResponse getReadAlerts(
    		@Context HttpServletRequest httpRequest) {
		LOGGER.info("Entering getReadAlerts()");

		AlertsResponse newAlertsResponse = new AlertsResponse();

		try {
			// Get the authentication information
			getAuthenticatinCredentials(httpRequest);
			LOGGER.debug("Username: " + credentials[0]);
			LOGGER.debug("Password: " + credentials[1]);

					newAlertsResponse = newAlertsDAO.getReadAlerts(credentials[0]);
			
			
		} catch (SQLException sqle) {
			LOGGER.error("SQLException in getReadAlerts()", sqle);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);			
		} catch (IOException ioe) {
			LOGGER.error("IOException in getReadAlerts()", ioe);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
		} catch (Throwable t) {
			LOGGER.error("Unexpected exception in getReadAlerts()", t);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
		}
		LOGGER.debug("AlertsResponse: " + newAlertsResponse);
		LOGGER.info("Exiting getReadAlerts()");
		return newAlertsResponse;
	}
	
	/**
	 * 
	 * @param sourcetag
	 * @param targettagset
	 * @param httpRequest
	 * @return
	 */
	@Path("/setread")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void recordReadAlert(@QueryParam("contentId") String contentId,
    		@QueryParam("migRefId") String migRefId,
    		@Context HttpServletRequest httpRequest) {
		LOGGER.info("Entering setReadAlerts()");
//TODO: create response object
		AlertsResponse newAlertsResponse = new AlertsResponse();

		try {
			// Get the authentication information
			getAuthenticatinCredentials(httpRequest);
			LOGGER.debug("Username: " + credentials[0]);
			LOGGER.debug("Password: " + credentials[1]);
			
				// Check for a valid request
				if (contentId == null || migRefId == null) {
					LOGGER.error("Content ID or migRefId invalid");
					throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,
						AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
				}
					newAlertsDAO.recordReadStatus(contentId, migRefId, credentials[0]);
			
			
		} catch (SQLException sqle) {
			LOGGER.error("SQLException in setReadAlerts()", sqle);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);			
		} catch (IOException ioe) {
			LOGGER.error("IOException in setReadAlerts()", ioe);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
		} catch (Throwable t) {
			LOGGER.error("Unexpected exception in setReadAlerts()", t);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
		}
		LOGGER.debug("AlertsResponse: " + newAlertsResponse);
		LOGGER.info("Exiting setReadAlerts()");
		
	
	
		}
		
}