/**
 * 
 */
package com.verint.services.km.dao;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import java.util.UUID;

import com.verint.services.km.model.ContentId;
import com.verint.services.km.util.ConfigInfo;
import com.verint.services.km.util.RestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

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

	private static String REST_CONTENT_URL;

	static {
		try {
			REST_CONTENT_URL = (new ConfigInfo()).getRestKmContentService();
		} catch (Throwable t) {
			LOGGER.error("Throwable Exception", t);
			System.exit(1);
		}
	}

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
	public String rateContent(RateRequest rateRequest) throws RemoteException, AppException, UnsupportedEncodingException {
		LOGGER.info("Entering rateContent()");
		LOGGER.debug("RateRequest: " + rateRequest);

		// Call the service
		String contentType = "vkm:AuthoredContent";
		if ("Spidered".equals(rateRequest.getContentType())) {
			contentType = "vkm:SpideredContent";
		}
		String rateUrl = REST_CONTENT_URL + "/default/content/" + contentType + "/" +
				URLEncoder.encode(rateRequest.getContentId(), StandardCharsets.UTF_8.toString()) +
				"/" + Locale;// + "?version="+"1.0";
		ResponseEntity<String> rateResponse = RestUtil.getRestResponse(rateUrl,
				"{\"@type\":\"vkm:AggregateRating\", \"vkm:ratingValue\": \"" + rateRequest.getRating().intValue() + "\"}",
				HttpMethod.POST, String.class, rateRequest.getOidcToken(), null, true);
		LOGGER.debug("RateResponseBodyType: " + rateResponse.getStatusCode() + ", " + rateResponse.getBody());

		if (!rateResponse.getStatusCode().is2xxSuccessful()) {
			// We have a problem with the service
			throw new AppException(500, AppErrorCodes.RATE_CONTENT_ERROR,
					AppErrorMessage.RATE_CONTENT_ERROR);
		}
		LOGGER.info("Exiting rateContent()");
		return "";
	}

	/*
	 * (non-Javadoc)
	 * @see com.verint.services.km.dao.SearchDAO#markAsViewed(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String markAsViewed(String contentID, String username, String password, String siteName,
							   String oidcToken, String externalSearchId) throws AppException, UnsupportedEncodingException {
		LOGGER.info("Entering markAsViewed()");
		LOGGER.debug("ContentID: " + contentID);

		// Call the service
		String viewUrl = REST_CONTENT_URL + "/default/content/vkm:AuthoredContent/" +
				URLEncoder.encode(contentID, StandardCharsets.UTF_8.toString()) +
				"/" + Locale;
		if (externalSearchId != null && !externalSearchId.equals("")) {
			viewUrl += "?externalSearchId=" + externalSearchId;
		}
	    ResponseEntity<String> restResponse = RestUtil.getRestResponse(viewUrl,"{\"@type\":\"vkm:View\"}",
				HttpMethod.POST, String.class, oidcToken, null, true);
		LOGGER.debug("MarkAsViewedResponseBodyType: " + restResponse.getStatusCode() + ", " + restResponse.getBody());

		// Check for a valid response
		if (!restResponse.getStatusCode().is2xxSuccessful()) {
			// We have a problem with the service
			throw new AppException(500, AppErrorCodes.VIEW_CONTENT_ERROR,
					AppErrorMessage.VIEW_CONTENT_ERROR);
		}
		LOGGER.info("Exiting markAsViewed()");
		return UUID.randomUUID().toString();
	}
}
