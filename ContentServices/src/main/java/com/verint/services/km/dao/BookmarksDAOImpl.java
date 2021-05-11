/**
 * 
 */
package com.verint.services.km.dao;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import java.time.Duration;
import java.time.Instant;

import com.verint.services.km.util.ConfigInfo;
import com.verint.services.km.util.RestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.ManageBookmarkRequestBodyType;
import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.ContentRequest;
import com.verint.services.km.model.ManageBookmarkRequest;
import com.verint.services.km.model.ManageBookmarkResponse;

/**
 * @author jmiller
 *
 */
@Repository
public class BookmarksDAOImpl extends BaseDAOImpl implements BookmarksDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(BookmarksDAOImpl.class);
	private static String REST_BOOKMARK_URL;

	static {
		try {
			REST_BOOKMARK_URL = (new ConfigInfo()).getRestKmBookmarkService();
		} catch (Throwable t) {
			LOGGER.error("Throwable Exception", t);
			System.exit(1);
		}
	}

	/**
	 * 
	 */
	public BookmarksDAOImpl() {
		super();
		LOGGER.info("Entering BookmarksDAOImpl()");
		LOGGER.info("Exiting BookmarksDAOImpl()");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

	/*
	 * (non-Javadoc)
	 * @see com.verint.services.km.dao.BookmarksDAO#addBookmark(com.verint.services.km.model.ManageBookmarkRequest)
	 */
	@Override
	public ManageBookmarkResponse addBookmark(ManageBookmarkRequest manageBookmarkRequest) throws RemoteException, AppException {
		LOGGER.info("Entering addBookmark()");
		LOGGER.debug("ManageBookmarkRequest: " + manageBookmarkRequest);
		final ManageBookmarkResponse manageBookmarkResponse = new ManageBookmarkResponse();

		// Make the service call
		Instant start = Instant.now();
		String abUrl = REST_BOOKMARK_URL + "/default/bookmark/" + manageBookmarkRequest.getUsername();
		ResponseEntity<String> restResponse = RestUtil.getRestResponse(abUrl,
				"{ \"@type\": \"vkm:Bookmark\", " +
						"\"vkm:contentID\":\"" + manageBookmarkRequest.getContentId() + "\", " +
						"\"vkm:inLanguage\":\"" + Locale + "\" }",
				HttpMethod.POST, String.class, manageBookmarkRequest.getOidcToken(), null, false);
		Instant end = Instant.now();
		LOGGER.debug("SERVICE_CALL_PERFORMANCE(" + manageBookmarkRequest.getUsername() + ") - addBookmark() duration: " + Duration.between(start, end).toMillis() + "ms");
		
		LOGGER.debug("ManageBookmarkResponseBodyType: " + restResponse.getStatusCode() + ", " + restResponse.getBody());
		if (!restResponse.getStatusCode().is2xxSuccessful()) {
			// We have a problem with the service
			throw new AppException(500, AppErrorCodes.ADD_BOOKMARK_ERROR,
					AppErrorMessage.ADD_BOOKMARK_ERROR);
		}
		LOGGER.debug("ManageBookmarkResponse: " + manageBookmarkResponse);
		LOGGER.info("Exiting addBookmark()");
		return manageBookmarkResponse;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.verint.services.km.dao.BookmarksDAO#addBookmark(com.verint.services.km.model.ManageBookmarkRequest)
	 */
	@Override
	public ManageBookmarkResponse removeBookmark(ManageBookmarkRequest manageBookmarkRequest) throws RemoteException, AppException, UnsupportedEncodingException {
		LOGGER.info("Entering addBookmark()");
		LOGGER.debug("ManageBookmarkRequest: " + manageBookmarkRequest);
		final ManageBookmarkResponse manageBookmarkResponse = new ManageBookmarkResponse();

		// Make the service call
		Instant start = Instant.now();
		String rbUrl = REST_BOOKMARK_URL + "/default/bookmark/" + manageBookmarkRequest.getUsername() + "/" +
				URLEncoder.encode(manageBookmarkRequest.getContentId(), StandardCharsets.UTF_8.toString()) +
				"?lang=" + Locale;
		ResponseEntity<String> restResponse = RestUtil.getRestResponse(rbUrl, null, HttpMethod.DELETE, String.class,
				manageBookmarkRequest.getOidcToken(), null, true);
		Instant end = Instant.now();
		LOGGER.debug("SERVICE_CALL_PERFORMANCE(" + manageBookmarkRequest.getUsername() + ") - removeBookmark() duration: " + Duration.between(start, end).toMillis() + "ms");
		
		LOGGER.debug("ManageBookmarkResponseBodyType: " + restResponse.getStatusCode() + ", " + restResponse.getBody());
		if (!restResponse.getStatusCode().is2xxSuccessful()) {
			// We have a problem with the service
			throw new AppException(500, AppErrorCodes.ADD_BOOKMARK_ERROR,
					AppErrorMessage.ADD_BOOKMARK_ERROR);
		}
		LOGGER.debug("ManageBookmarkResponse: " + manageBookmarkResponse);
		LOGGER.info("Exiting addBookmark()");
		return manageBookmarkResponse;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.verint.services.km.dao.BookmarksDAO#isContentBookmarked(com.verint.services.km.model.ContentRequest)
	 */
		public boolean isContentBookmarked(ContentRequest contentRequest) throws RemoteException, AppException, UnsupportedEncodingException {
		boolean isBookmarked = false;

		Instant start = Instant.now();
		String rbUrl = REST_BOOKMARK_URL + "/default/bookmark/" + contentRequest.getUsername() +
				"/" +
				URLEncoder.encode(contentRequest.getContentId(), StandardCharsets.UTF_8.toString()) +
				"?lang=" + Locale;
		ResponseEntity<String> restResponse = RestUtil.getRestResponse(rbUrl, null, HttpMethod.GET,
				String.class, contentRequest.getOidcToken(), RestUtil.getIgnoreErrorHandler(), true);
		Instant end = Instant.now();
		LOGGER.debug("SERVICE_CALL_PERFORMANCE - isContentBookmarked() duration: " + Duration.between(start, end).toMillis() + "ms");
		
		if (restResponse.getStatusCode().is2xxSuccessful()) {
			isBookmarked = true;
		} else if (restResponse.getStatusCode() == HttpStatus.NOT_FOUND &&
				restResponse.getBody() != null &&
				restResponse.getBody().contains("Bookmark Not Found")) {
			//Content not bookmarked
		} else {
			// We have a problem with the service
			throw new AppException(500, AppErrorCodes.CONTENT_BOOKMARK_ERROR,
					AppErrorMessage.CONTENT_BOOKMARK_ERROR);
		}
		return isBookmarked;
	}

	
}
