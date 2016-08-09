/**
 * 
 */
package com.verint.services.km.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
import com.verint.services.km.model.Rate;
import com.verint.services.km.model.RateRequest;
import com.verint.services.km.model.RateResponse;


/**
 * @author jmiller
 *
 */
@Path("/rate")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Service
public class RateService extends BaseService {
	private final Logger LOGGER = LoggerFactory.getLogger(RateService.class);

	@Autowired
	private SearchDAO searchDAO;

	/**
	 * 
	 */
	public RateService() {
		super();
		LOGGER.debug("Entering RateService()");
		LOGGER.debug("Exiting RateService()");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

	/**
	 * 
	 * @param rate
	 * @param authorization
	 * @return
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public RateResponse rate(Rate rate,
    		@Context HttpServletRequest httpRequest) {
		LOGGER.info("Entering rate()");
		LOGGER.debug("Rate: " + rate);
		final RateResponse rateResponse = new RateResponse();

		try {
			// Check for a valid request
			if (rate == null || 
				rate.getContentId() == null ||
				rate.getRating() == null) {
				LOGGER.error("No valid content ID or rating number");
				throw new AppException(500, AppErrorCodes.RATE_CONTENT_ERROR,
					AppErrorMessage.RATE_CONTENT_ERROR);
			}

			// Get the authentication information
			final String[] credentials = getAuthenticatinCredentials(httpRequest);
			LOGGER.debug("Username: " + credentials[0]);
			LOGGER.debug("Password: " + credentials[1]);

			// Create the ContentRequest
			final RateRequest rateRequest = new RateRequest();
			rateRequest.setContentId(rate.getContentId());
			rateRequest.setRating(rate.getRating());
			rateRequest.setUsername(credentials[0]);
			rateRequest.setPassword(credentials[1]);

			// Rate the content
			rateResponse.setRatingUUID(searchDAO.rateContent(rateRequest));
		} catch (Throwable t) {
			LOGGER.error("Rate() Exception", t);
		}
		LOGGER.debug("RateResponse: " + rateResponse);
		LOGGER.info("Exiting rate()");
		return rateResponse;
	}
}