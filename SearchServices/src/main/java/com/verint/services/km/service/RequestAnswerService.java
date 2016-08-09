/**
 * 
 */
package com.verint.services.km.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verint.services.km.dao.RequestAnswerDAO;
import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.RequestAnswer;
import com.verint.services.km.model.RequestAnswerRequest;


/**
 * @author jmiller
 *
 */
@Path("/suggestcontent")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Service
public class RequestAnswerService extends BaseService {
	private final Logger LOGGER = LoggerFactory.getLogger(RequestAnswerService.class);

	@Autowired
	private RequestAnswerDAO requestAnswerDAO;

	/**
	 * 
	 */
	public RequestAnswerService() {
		super();
		LOGGER.debug("Entering RequestAnswerService()");
		LOGGER.debug("Exiting RequestAnswerService()");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

	/**
	 * 
	 * @param request
	 * @param httpRequest
	 * @throws AppException
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void suggestContent(RequestAnswer request, 
    		@Context HttpServletRequest httpRequest) throws AppException {
		LOGGER.info("Entering suggestContent()");
		LOGGER.debug("RequestAnswer: " + request);

		try {
			// Check for valid fields
			if (request == null || 
					request.getKeyword() == null || request.getKeyword().length() == 0 || 
					request.getExpectation() == null || request.getExpectation().length() == 0) {
				LOGGER.error("Keyword or Expectation is null");
				throw new AppException(500, AppErrorCodes.SUGGEST_CONTENT_ERROR,  
						AppErrorMessage.SUGGEST_CONTENT_ERROR);
			}
			// Get the authentication information
			final String[] credentials = getAuthenticatinCredentials(httpRequest);

			// Create the ManageBookmarkRequest
			final RequestAnswerRequest requestAnswerRequest = new RequestAnswerRequest();
			requestAnswerRequest.setExpectation(request.getExpectation());
			requestAnswerRequest.setKeyword(request.getKeyword());
			requestAnswerRequest.setSearchDate(request.getSearchDate());
			requestAnswerRequest.setSelectedFilter(request.getSelectedFilter());
			requestAnswerRequest.setUsername(credentials[0]);
			requestAnswerRequest.setPassword(credentials[1]);
			LOGGER.debug("RequestAnswerRequest: " + requestAnswerRequest);

			// Suggest Content
			requestAnswerDAO.suggestContent(requestAnswerRequest);
		} catch (AppException ae) {
			LOGGER.error("AppException in suggestContent()", ae);
			throw ae;
		} catch (Throwable t) {
			LOGGER.error("Unexpected exception in suggestContent()", t);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
		}
		LOGGER.info("Exiting suggestContent()");
	}
}