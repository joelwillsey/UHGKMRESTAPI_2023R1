/**
 * 
 */
package com.verint.services.km.dao;

import java.rmi.RemoteException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.kana.contactcentre.services.model.SearchV1Service_wsdl.MarkAsViewedRequestBodyType;
import com.kana.contactcentre.services.model.SearchV1Service_wsdl.MarkAsViewedResponseBodyType;
import com.kana.contactcentre.services.model.SearchV1Service_wsdl.RateRequestBodyType;
import com.kana.contactcentre.services.model.SearchV1Service_wsdl.RateResponseBodyType;
import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.RateRequest;

/**
 * @author jmiller
 *
 */
@Repository
public class SearchDAOImpl extends BaseDAOImpl implements SearchDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(SearchDAOImpl.class);

	/**
	 * 
	 */
	public SearchDAOImpl() {
		super();
		LOGGER.info("Entering SearchDAOImpl()");
		LOGGER.info("Exiting SearchDAOImpl()");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

	/*
	 * (non-Javadoc)
	 * @see com.verint.services.km.dao.SearchDAO#rateContent(com.verint.services.km.model.RateRequest)
	 */
	@Override
	public String rateContent(RateRequest rateRequest) throws RemoteException, AppException {
		LOGGER.info("Entering rateContent()");
		LOGGER.debug("RateRequest: " + rateRequest);
		final RateRequestBodyType request = new RateRequestBodyType();
		request.setApplicationID(AppID);
		request.setLocale(Locale);
		request.setUsername(rateRequest.getUsername());
		request.setPassword(rateRequest.getPassword());
		request.setContentID(rateRequest.getContentId());
		request.setRating(rateRequest.getRating().floatValue());
		request.setSiteName(rateRequest.getSiteName());

		// Call the service
		final RateResponseBodyType response = SearchPortType.rate(request);
		LOGGER.debug("RateResponseBodyType: " + response);

		// Check for a valid response
		if (response != null && response.getRatingUUID() != null) {
			LOGGER.debug("RatingUUID: " + response.getRatingUUID());
		} else {
			// We have a problem with the service
			throw new AppException(500, AppErrorCodes.RATE_CONTENT_ERROR,
					AppErrorMessage.RATE_CONTENT_ERROR);
		}
		LOGGER.info("Exiting rateContent()");
		return response.getRatingUUID();
	}

	/*
	 * (non-Javadoc)
	 * @see com.verint.services.km.dao.SearchDAO#markAsViewed(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String markAsViewed(String contentID, String username, String password, String siteName) throws RemoteException, AppException {
		LOGGER.info("Entering markAsViewed()");
		LOGGER.debug("ContentID: " + contentID);
		final MarkAsViewedRequestBodyType request = new MarkAsViewedRequestBodyType();
		request.setApplicationID(AppID);
		request.setLocale(Locale);
		request.setContentID(contentID);
		request.setUsername(username);
		request.setPassword(password);
		request.setSiteName(siteName);

		// Call the service
		final MarkAsViewedResponseBodyType response = SearchPortType.markAsViewed(request);
		LOGGER.debug("MarkAsViewedResponseBodyType: " + response);

		// Check for a valid response
		if (response != null && response.getViewUUID() != null) {
			LOGGER.debug("ViewUUID: " + response.getViewUUID());
		} else {
			// We have a problem with the service
			throw new AppException(500, AppErrorCodes.VIEW_CONTENT_ERROR,
					AppErrorMessage.VIEW_CONTENT_ERROR);
		}
		LOGGER.info("Exiting markAsViewed()");
		return response.getViewUUID();
	}
}