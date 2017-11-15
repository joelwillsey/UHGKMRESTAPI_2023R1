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
import com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.ReorderBookmarksRequestBodyType;
import com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.ReorderBookmarksResponseBodyType;
import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.ContentRequest;
import com.verint.services.km.model.ErrorList;
import com.verint.services.km.model.ManageBookmarkRequest;
import com.verint.services.km.model.ManageBookmarkResponse;
import com.verint.services.km.model.ManageBookmarkV2Request;
import com.verint.services.km.model.ManageBookmarkV2Response;

import KMBookmarkServiceV2Service_wsdl.BookmarkFolderContent;
import KMBookmarkServiceV2Service_wsdl.BookmarkedContentV2;
import KMBookmarkServiceV2Service_wsdl.KMBookmarkServiceV2PortType;
import KMBookmarkServiceV2Service_wsdl.ManageBookmarksV2RequestBodyType;
import KMBookmarkServiceV2Service_wsdl.ManageBookmarksV2ResponseBodyType;

/**
 * @author jmiller
 *
 */
@Repository
public class BookmarksV2DAOImpl extends BaseDAOImpl implements BookmarksV2DAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(BookmarksV2DAOImpl.class);

	/**
	 * 
	 */
	public BookmarksV2DAOImpl() {
		super();
		LOGGER.info("Entering BookmarksV2DAOImpl()");
		LOGGER.info("Exiting BookmarksV2DAOImpl()");
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
	public ManageBookmarkV2Response addBookmark(ManageBookmarkV2Request manageBookmarkRequest) throws RemoteException, AppException {
		LOGGER.info("Entering addBookmark()");
		LOGGER.debug("ManageV2BookmarkRequest: " + manageBookmarkRequest);
		final ManageBookmarkV2Response manageBookmarkResponse = new ManageBookmarkV2Response();
		
		// Setup the service request
		final ManageBookmarksV2RequestBodyType request = new ManageBookmarksV2RequestBodyType();
		request.setApplicationID(AppID);
		request.setLocaleName(Locale);
		request.setContentID(manageBookmarkRequest.getContentId());
		request.setFolderID(manageBookmarkRequest.getFolderId());
		request.setUserName(manageBookmarkRequest.getUsername());
		request.setPassword(manageBookmarkRequest.getPassword());
		request.setUserAction("ADD");

		// Make the service call
		Instant start = Instant.now();
		//final ManageBookmarksV2ResponseBodyType response = KMBookmarkServiceV2PortType.manageBookmarksV2(request);
		//Some issue here with the above method being static - will need to sort out ASAP
		final  ManageBookmarksV2ResponseBodyType response = new ManageBookmarksV2ResponseBodyType();
		Instant end = Instant.now();
		LOGGER.debug("SERVICE_CALL_PERFORMANCE(" + manageBookmarkRequest.getUsername() + ") - addBookmark() duration: " + Duration.between(start, end).toMillis() + "ms");
		
		LOGGER.debug("ManageBookmarkV2ResponseBodyType: " + response);
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
		LOGGER.debug("ManageBookmarkV2Response: " + manageBookmarkResponse);
		LOGGER.info("Exiting addBookmark()");
		return manageBookmarkResponse;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.verint.services.km.dao.BookmarksDAO#addBookmark(com.verint.services.km.model.ManageBookmarkRequest)
	 */
	@Override
	public ManageBookmarkV2Response removeBookmark(ManageBookmarkV2Request manageBookmarkRequest) throws RemoteException, AppException {
		LOGGER.info("Entering addBookmark()");
		LOGGER.debug("ManageBookmarkV2Request: " + manageBookmarkRequest);
		final ManageBookmarkV2Response manageBookmarkResponse = new ManageBookmarkV2Response();
		
		// Setup the service request
		final ManageBookmarksV2RequestBodyType request = new ManageBookmarksV2RequestBodyType();
		request.setApplicationID(AppID);
		request.setLocaleName(Locale);
		request.setContentID(manageBookmarkRequest.getContentId());
		request.setFolderID(manageBookmarkRequest.getFolderId());
		request.setUserName(manageBookmarkRequest.getUsername());
		request.setPassword(manageBookmarkRequest.getPassword());
		request.setUserAction("REMOVE");

		// Make the service call
		Instant start = Instant.now();
		//final ManageBookmarksV2ResponseBodyType response = KMBookmarkServiceV2PortType.manageBookmarksV2(request);
		//issue with static reference to method above
		final  ManageBookmarksV2ResponseBodyType response = new ManageBookmarksV2ResponseBodyType();
		Instant end = Instant.now();
		LOGGER.debug("SERVICE_CALL_PERFORMANCE(" + manageBookmarkRequest.getUsername() + ") - removeBookmark() duration: " + Duration.between(start, end).toMillis() + "ms");
		
		LOGGER.debug("ManageBookmarkResponseV2BodyType: " + response);
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
		LOGGER.debug("ManageBookmarkV2Response: " + manageBookmarkResponse);
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
		LOGGER.debug("SERVICE_CALL_PERFORMANCE - isContentBookmarked() duration: " + Duration.between(start, end).toMillis() + "ms");
		
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
	
	
	public ManageBookmarkV2Response reorderBookmarkUp(ManageBookmarkV2Request bookmarkRequest) throws RemoteException, AppException {
		LOGGER.info("Entering reorderBookmarkUp()");
		LOGGER.debug("ManageBookmarkRequest: " + bookmarkRequest);

		// Call the service
		final ManageBookmarkV2Response bookmarkResponse = reorderBookmark(bookmarkRequest, "UP");

		LOGGER.debug("ManageBookmarkResponse: " + bookmarkResponse);
		LOGGER.info("Exiting reorderBookmarkUp()");
		return bookmarkResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.verint.services.km.dao.BookmarkDAO#reorderBookmarkDown(com.verint.services.km.model.ManageBookmarkRequest)
	 */
	public ManageBookmarkV2Response reorderBookmarkDown(ManageBookmarkV2Request bookmarkRequest) throws RemoteException, AppException {
		LOGGER.info("Entering reorderBookmarkDown()");
		LOGGER.debug("ManageBookmarkRequest: " + bookmarkRequest);

		// Call the service
		final ManageBookmarkV2Response bookmarkResponse = reorderBookmark(bookmarkRequest, "DOWN");

		LOGGER.debug("ManageBookmarkResponse: " + bookmarkResponse);
		LOGGER.info("Exiting reorderBookmarkDown()");
		return bookmarkResponse;
	}

	
	private ManageBookmarkV2Response reorderBookmark(ManageBookmarkV2Request bookmarkRequest, String direction) throws RemoteException, AppException {
		LOGGER.info("Entering reorderBookmark()");
		LOGGER.debug("ManageBookmarkRequest: " + bookmarkRequest);
		final ManageBookmarkV2Response bookmarkResponse = new ManageBookmarkV2Response();

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

	@Override
	public BookmarkFolderContent getFolder(String folderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookmarkedContentV2 getBookmark(String contentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookmarkedContentV2 listAllBookmarks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ManageBookmarkV2Response reorderFolderUp(ManageBookmarkV2Request bookmarkRequest)
			throws RemoteException, AppException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ManageBookmarkV2Response reorderFolderDown(ManageBookmarkV2Request bookmarkRequest)
			throws RemoteException, AppException {
		// TODO Auto-generated method stub
		return null;
	}

	
}