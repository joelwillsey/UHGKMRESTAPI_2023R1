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

import com.verint.services.km.dao.FeedbackDAO;
import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.Feedback;
import com.verint.services.km.model.FeedbackRequest;
import com.verint.services.km.model.FeedbackResponse;


/**
 * @author jmiller
 *
 */
@Path("/feedback")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Service
public class FeedbackService extends BaseService {
	private final Logger LOGGER = LoggerFactory.getLogger(FeedbackService.class);

	@Autowired
	private FeedbackDAO feedbackDAO;

	/**
	 * 
	 */
	public FeedbackService() {
		super();
		LOGGER.debug("Entering FeedbackService()");
		LOGGER.debug("Exiting FeedbackService()");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

	/**
	 * 
	 * @param feedback
	 * @param httpRequest
	 * @return
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public FeedbackResponse feedback(Feedback feedback,
    		@Context HttpServletRequest httpRequest) {
		LOGGER.info("Entering feedback()");
		LOGGER.debug("Feedback: " + feedback);
		FeedbackResponse feedbackResponse = null;
		try {
			// Get the authentication information
			final String[] credentials = getAuthenticatinCredentials(httpRequest);
			LOGGER.debug("Username: " + credentials[0]);
			LOGGER.debug("Password: " + credentials[1]);

			// Create the ContentRequest
			final FeedbackRequest feedbackRequest = new FeedbackRequest();
			feedbackRequest.setComment(feedback.getComment());
			feedbackRequest.setContentId(feedback.getContentId());
			feedbackRequest.setEmail(feedback.getEmail());
			feedbackRequest.setFeedbackCodeID(feedback.getFeedbackCodeID());
			feedbackRequest.setLocale(feedback.getLocale());
			feedbackRequest.setName(feedback.getName());
			feedbackRequest.setNotification(feedback.getNotification());
			feedbackRequest.setRating(feedback.getRating());
			feedbackRequest.setViewID(feedback.getViewID());
			feedbackRequest.setUsername(credentials[0]);
			feedbackRequest.setPassword(credentials[1]);
			LOGGER.debug("FeedbackRequest: " + feedbackRequest);
			
			// Call the service
			feedbackResponse = feedbackDAO.feedback(feedbackRequest);
		} catch (AppException ae) {
			LOGGER.error("AppException in feedback()", ae);
			throw ae;
		} catch (Throwable t) {
			LOGGER.error("Unexpected exception in feedback()", t);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
		}
		LOGGER.debug("FeedbackResponse: " + feedbackResponse);
		LOGGER.info("Exiting feedback()");
		return feedbackResponse;
	}
}