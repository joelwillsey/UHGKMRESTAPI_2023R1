/**
 * 
 */
package com.verint.services.km.dao;

import java.rmi.RemoteException;
import java.time.Duration;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.kana.contactcentre.services.model.ContentV1Service_wsdl.GetContentDetailsResponseBodyType;
import com.kana.contactcentre.services.model.FeedbackV1Service_wsdl.FeedbackRequestBodyType;
import com.kana.contactcentre.services.model.FeedbackV1Service_wsdl.FeedbackResponseBodyType;
import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.FeedbackRequest;
import com.verint.services.km.model.FeedbackResponse;

/**
 * @author jmiller
 *
 */
@Repository
public class FeedbackDAOImpl extends BaseDAOImpl implements FeedbackDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackDAOImpl.class);

	/**
	 * 
	 */
	public FeedbackDAOImpl() {
		super();
		LOGGER.info("Entering FeedbackDAOImpl()");
		LOGGER.info("Exiting FeedbackDAOImpl()");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

	/*
	 * (non-Javadoc)
	 * @see com.verint.services.km.dao.FeedbackDAO#feedback(com.verint.services.km.model.FeedbackRequest)
	 */
	@Override
	public FeedbackResponse feedback(FeedbackRequest feedbackRequest) throws RemoteException, AppException {
		LOGGER.info("Entering feedback()");
		LOGGER.debug("FeedbackRequest: " + feedbackRequest);
		final FeedbackResponse feedbackResponse = new FeedbackResponse();

		// Setup the request object to the SOAP call
		final FeedbackRequestBodyType request = new FeedbackRequestBodyType();
		request.setComment(feedbackRequest.getComment());
		request.setContentID(feedbackRequest.getContentId());
		request.setEmail(feedbackRequest.getEmail());
		request.setFeedbackCodeID(feedbackRequest.getFeedbackCodeID());
		request.setName(feedbackRequest.getName());
		request.setNotification(feedbackRequest.getNotification());
		request.setRating(feedbackRequest.getRating());
		request.setViewID(feedbackRequest.getViewID());
		request.setLocale(Locale);
		request.setUsername(feedbackRequest.getUsername());
		request.setPassword(feedbackRequest.getPassword());

		// Call the service
		Instant start = Instant.now();
		final FeedbackResponseBodyType response = FeedbackPortType.feedback(request);
		Instant end = Instant.now();
		LOGGER.debug("Service Call Performance("+feedbackRequest.getUsername()+") - feedback() duration: " + Duration.between(start, end).toMillis() + "ms");
		
		LOGGER.debug("FeedbackResponseBodyType: " + response);
		if (response != null && response.getReturnResponse() != null) {
			feedbackResponse.setFeedbackCode(response.getReturnResponse().getFeedbackCode());
			feedbackResponse.setOptionalComments(response.getReturnResponse().getOptionalComments());
			feedbackResponse.setOutcomeCode(response.getReturnResponse().getOutcomeCode());

			// Check if notification info is available
			if (response.getReturnResponse().getNotificationEmail() != null && response.getReturnResponse().getNotificationName() != null) {
				feedbackResponse.setNotification(true);
				feedbackResponse.setNotificationEmail(response.getReturnResponse().getNotificationEmail());
				feedbackResponse.setNotificationName(response.getReturnResponse().getNotificationName());
			}
		} else {
			// We have a problem with the service
			throw new AppException(500, AppErrorCodes.FEEDBACK_ERROR,
				AppErrorMessage.FEEDBACK_ERROR);
		}
		LOGGER.debug("FeedbackResponse: " + feedbackResponse);
		return feedbackResponse;
	}
}