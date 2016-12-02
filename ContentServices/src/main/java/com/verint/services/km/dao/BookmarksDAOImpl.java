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
import com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.BookmarkedContent;
import com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.ErrorMessage;
import com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.ListAllBookmarksRequestBodyType;
import com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.ListAllBookmarksResponseBodyType;
import com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.ManageBookmarkRequestBodyType;
import com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.ManageBookmarkResponseBodyType;
import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.ContentRequest;
import com.verint.services.km.model.ErrorList;
import com.verint.services.km.model.ManageBookmarkRequest;
import com.verint.services.km.model.ManageBookmarkResponse;

/**
 * @author jmiller
 *
 */
@Repository
public class BookmarksDAOImpl extends BaseDAOImpl implements BookmarksDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(BookmarksDAOImpl.class);

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
		
		// Setup the service request
		final ManageBookmarkRequestBodyType request = new ManageBookmarkRequestBodyType();
		request.setApplicationID(AppID);
		request.setLocaleName(Locale);
		request.setContentId(manageBookmarkRequest.getContentId());
		request.setUserName(manageBookmarkRequest.getUsername());
		request.setPassword(manageBookmarkRequest.getPassword());
		request.setUserAction("ADD");

		// Make the service call
		Instant start = Instant.now();
		final ManageBookmarkResponseBodyType response = KMBookmarkServicePortType.manageBookmark(request);
		Instant end = Instant.now();
		LOGGER.debug("SERVICE_CALL_PERFORMANCE(" + manageBookmarkRequest.getUsername() + ") - addBookmark() duration: " + Duration.between(start, end).toMillis() + "ms");
		
		LOGGER.debug("ManageBookmarkResponseBodyType: " + response);
		if (response != null && response.getErrorList() != null) {
			final ErrorMessage[] errors = response.getErrorList();
			// Loop through the errors
			for (int x = 0; (errors != null) && (x < errors.length); x++) {
				ErrorList errorList = new ErrorList();
				errorList.setCode(errors[x].getCode());
				errorList.setMessage(errors[x].getMessage());
				manageBookmarkResponse.addErrorList(errorList);
			}
		} else {
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
	public ManageBookmarkResponse removeBookmark(ManageBookmarkRequest manageBookmarkRequest) throws RemoteException, AppException {
		LOGGER.info("Entering addBookmark()");
		LOGGER.debug("ManageBookmarkRequest: " + manageBookmarkRequest);
		final ManageBookmarkResponse manageBookmarkResponse = new ManageBookmarkResponse();
		
		// Setup the service request
		final ManageBookmarkRequestBodyType request = new ManageBookmarkRequestBodyType();
		request.setApplicationID(AppID);
		request.setLocaleName(Locale);
		request.setContentId(manageBookmarkRequest.getContentId());
		request.setUserName(manageBookmarkRequest.getUsername());
		request.setPassword(manageBookmarkRequest.getPassword());
		request.setUserAction("REMOVE");

		// Make the service call
		Instant start = Instant.now();
		final ManageBookmarkResponseBodyType response = KMBookmarkServicePortType.manageBookmark(request);
		Instant end = Instant.now();
		LOGGER.debug("SERVICE_CALL_PERFORMANCE(" + manageBookmarkRequest.getUsername() + ") - removeBookmark() duration: " + Duration.between(start, end).toMillis() + "ms");
		
		LOGGER.debug("ManageBookmarkResponseBodyType: " + response);
		if (response != null && response.getErrorList() != null) {
			final ErrorMessage[] errors = response.getErrorList();
			// Loop through the errors
			for (int x = 0; (errors != null) && (x < errors.length); x++) {
				ErrorList errorList = new ErrorList();
				errorList.setCode(errors[x].getCode());
				errorList.setMessage(errors[x].getMessage());
				manageBookmarkResponse.addErrorList(errorList);
			}
		} else {
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
	public boolean isContentBookmarked(ContentRequest contentRequest) throws RemoteException, AppException {
		boolean isBookmarked = false;
		final ListAllBookmarksRequestBodyType bookmarkRequest = new ListAllBookmarksRequestBodyType();
		bookmarkRequest.setApplicationID(AppID);
		bookmarkRequest.setUserName(contentRequest.getUsername());
		bookmarkRequest.setPassword(contentRequest.getPassword());
		bookmarkRequest.setSortColumnName("");
		bookmarkRequest.setSortOrder("");

		// Check for a match
		Instant start = Instant.now();
		final ListAllBookmarksResponseBodyType bookmarkResponse = KMBookmarkServicePortType.listAllBookmarks(bookmarkRequest);
		Instant end = Instant.now();
		LOGGER.debug("SOAP Request->Response - isContentBookmarked() duration: " + Duration.between(start, end).toMillis() + "ms");
		
		if (bookmarkResponse != null && bookmarkResponse.getContentList() != null) {
			final BookmarkedContent[] content = bookmarkResponse.getContentList();
			for (int x = 0; (content != null) && (x < content.length); x++) {
				// Is there a match?
				if (contentRequest.getContentId().equalsIgnoreCase(content[x].getContentId())) {
					isBookmarked = true;
				}
			}
		} else {
			// We have a problem with the service
			throw new AppException(500, AppErrorCodes.CONTENT_BOOKMARK_ERROR,
					AppErrorMessage.CONTENT_BOOKMARK_ERROR);			
		}
		return isBookmarked;
	}

	
}