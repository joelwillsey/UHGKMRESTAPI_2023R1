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

import com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.ErrorMessage;
import com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.ManageBookmarkRequestBodyType;
import com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.ManageBookmarkResponseBodyType;
import com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.ReorderBookmarksRequestBodyType;
import com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.ReorderBookmarksResponseBodyType;
import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.ErrorList;
import com.verint.services.km.model.ManageBookmarkRequest;
import com.verint.services.km.model.ManageBookmarkResponse;

/**
 * @author jmiller
 *
 */
@Repository
public class BookmarkDAOImpl extends BaseDAOImpl implements BookmarkDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(BookmarkDAOImpl.class);

	/**
	 * 
	 */
	public BookmarkDAOImpl() {
		super();
		LOGGER.info("Entering BookmarkDAOImpl()");
		LOGGER.info("Exiting BookmarkDAOImpl()");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

	/*
	 * (non-Javadoc)
	 * @see com.verint.services.km.dao.SearchDAO#addBookmark(com.verint.services.km.model.ManageBookmarkRequest)
	 */
	@Override
	public ManageBookmarkResponse addBookmark(ManageBookmarkRequest bookmarkRequest) throws RemoteException, AppException {
		LOGGER.info("Entering addBookmark()");
		LOGGER.debug("ManageBookmarkRequest: " + bookmarkRequest);

		// Call the service
		final ManageBookmarkResponse bookmarkResponse = manageBookmark(bookmarkRequest, "ADD");

		LOGGER.debug("ManageBookmarkResponse: " + bookmarkResponse);
		LOGGER.info("Exiting addBookmark()");
		return bookmarkResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.verint.services.km.dao.SearchDAO#removeBookmark(com.verint.services.km.model.ManageBookmarkRequest)
	 */
	@Override
	public ManageBookmarkResponse removeBookmark(ManageBookmarkRequest bookmarkRequest) throws RemoteException, AppException {
		LOGGER.info("Entering removeBookmark()");
		LOGGER.debug("ManageBookmarkRequest: " + bookmarkRequest);

		// Call the service
		final ManageBookmarkResponse bookmarkResponse = manageBookmark(bookmarkRequest, "REMOVE");

		LOGGER.debug("ManageBookmarkResponse: " + bookmarkResponse);
		LOGGER.info("Exiting removeBookmark()");
		return bookmarkResponse;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.verint.services.km.dao.BookmarkDAO#reorderBookmarkUp(com.verint.services.km.model.ManageBookmarkRequest)
	 */
	public ManageBookmarkResponse reorderBookmarkUp(ManageBookmarkRequest bookmarkRequest) throws RemoteException, AppException {
		LOGGER.info("Entering reorderBookmarkUp()");
		LOGGER.debug("ManageBookmarkRequest: " + bookmarkRequest);

		// Call the service
		final ManageBookmarkResponse bookmarkResponse = reorderBookmark(bookmarkRequest, "UP");

		LOGGER.debug("ManageBookmarkResponse: " + bookmarkResponse);
		LOGGER.info("Exiting reorderBookmarkUp()");
		return bookmarkResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.verint.services.km.dao.BookmarkDAO#reorderBookmarkDown(com.verint.services.km.model.ManageBookmarkRequest)
	 */
	public ManageBookmarkResponse reorderBookmarkDown(ManageBookmarkRequest bookmarkRequest) throws RemoteException, AppException {
		LOGGER.info("Entering reorderBookmarkDown()");
		LOGGER.debug("ManageBookmarkRequest: " + bookmarkRequest);

		// Call the service
		final ManageBookmarkResponse bookmarkResponse = reorderBookmark(bookmarkRequest, "DOWN");

		LOGGER.debug("ManageBookmarkResponse: " + bookmarkResponse);
		LOGGER.info("Exiting reorderBookmarkDown()");
		return bookmarkResponse;
	}

	/**
	 * 
	 * @param bookmarkRequest
	 * @param action
	 * @return
	 * @throws RemoteException
	 * @throws AppException
	 */
	private ManageBookmarkResponse manageBookmark(ManageBookmarkRequest bookmarkRequest, String action) throws RemoteException, AppException {
		LOGGER.info("Entering manageBookmark()");
		LOGGER.debug("ManageBookmarkRequest: " + bookmarkRequest);
		final ManageBookmarkResponse bookmarkResponse = new ManageBookmarkResponse();

		// Setup the request
		final ManageBookmarkRequestBodyType request = new ManageBookmarkRequestBodyType();
		request.setApplicationID(AppID);
		request.setUserName(bookmarkRequest.getUsername());
		request.setPassword(bookmarkRequest.getPassword());
		request.setLocaleName(Locale);
		request.setContentId(bookmarkRequest.getContentId());
		request.setUserAction(action);

		// Call the service
		Instant start = Instant.now();
		final ManageBookmarkResponseBodyType response = KMBookmarkServicePortType.manageBookmark(request);
		Instant end = Instant.now();
		LOGGER.debug("SERVICE_CALL_PERFORMANCE("+bookmarkRequest.getUsername()+") - manageBookmark() duration: " + Duration.between(start, end).toMillis() + "ms");

		if (response != null && response.getErrorList() != null) {
			final ErrorMessage[] errors = response.getErrorList();			
			// Loop through errors if any
			for (int x = 0; (errors != null && x < errors.length); x++) {
				final ErrorList errorList = new ErrorList();
				errorList.setCode(errors[x].getCode());
				errorList.setMessage(errors[x].getMessage());
				bookmarkResponse.addErrorList(errorList);
			}
		} else {
			// We have a problem with the service
			throw new AppException(500, AppErrorCodes.MANAGE_BOOKMARK_ERROR,  
					AppErrorMessage.MANAGE_BOOKMARK_ERROR);			
		}
		LOGGER.debug("ManageBookmarkResponse: " + bookmarkResponse);
		LOGGER.info("Exiting manageBookmark()");
		return bookmarkResponse;		
	}

	/**
	 * 
	 * @param bookmarkRequest
	 * @param direction
	 * @return
	 * @throws RemoteException
	 * @throws AppException
	 */
	private ManageBookmarkResponse reorderBookmark(ManageBookmarkRequest bookmarkRequest, String direction) throws RemoteException, AppException {
		LOGGER.info("Entering reorderBookmark()");
		LOGGER.debug("ManageBookmarkRequest: " + bookmarkRequest);
		final ManageBookmarkResponse bookmarkResponse = new ManageBookmarkResponse();

		// Setup the request
		final ReorderBookmarksRequestBodyType request = new ReorderBookmarksRequestBodyType();
		request.setApplicationID(AppID);
		request.setUserName(bookmarkRequest.getUsername());
		request.setPassword(bookmarkRequest.getPassword());
		request.setLocaleName(Locale);
		request.setContentId(bookmarkRequest.getContentId());
		request.setReorderDirection(direction);

		// Call the service
		Instant start = Instant.now();
		final ReorderBookmarksResponseBodyType response = KMBookmarkServicePortType.reorderBookmarks(request);
		Instant end = Instant.now();
		LOGGER.debug("SERVICE_CALL_PERFORMANCE("+bookmarkRequest.getUsername()+") - manageBookmark() duration: " + Duration.between(start, end).toMillis() + "ms");

		if (response != null && response.getErrorList() != null) {
			final ErrorMessage[] errors = response.getErrorList();			
			// Loop through errors if any
			for (int x = 0; (errors != null && x < errors.length); x++) {
				final ErrorList errorList = new ErrorList();
				errorList.setCode(errors[x].getCode());
				errorList.setMessage(errors[x].getMessage());
				bookmarkResponse.addErrorList(errorList);
			}
		} else {
			// We have a problem with the service
			throw new AppException(500, AppErrorCodes.REORDER_BOOKMARK_ERROR,  
					AppErrorMessage.REORDER_BOOKMARK_ERROR);			
		}
		LOGGER.debug("ManageBookmarkResponse: " + bookmarkResponse);
		LOGGER.info("Exiting reorderBookmark()");
		return bookmarkResponse;		
	}
}