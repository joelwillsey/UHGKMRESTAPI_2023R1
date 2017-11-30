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
//import com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.BookmarkedContent;
//import com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.ErrorMessage;
//import com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.ListAllBookmarksRequestBodyType;
//import com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.ListAllBookmarksResponseBodyType;
//import com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.ManageBookmarkRequestBodyType;
//import com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.ManageBookmarkResponseBodyType;
import com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.ReorderBookmarksRequestBodyType;
import com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.ReorderBookmarksResponseBodyType;
import com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.BookmarkFolderContent;
import com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.BookmarkedContentV2;
import com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.KMBookmarkServiceV2PortType;
import com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ListAllBookmarksV2RequestBodyType;
import com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ListAllBookmarksV2ResponseBodyType;
import com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ManageBookmarksV2RequestBodyType;
import com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ManageBookmarksV2ResponseBodyType;
import com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ReorderBookmarkAndFolderRequestBodyType;
import com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ReorderBookmarkAndFolderResponseBodyType;
import com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ErrorMessage;
import com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ContentBookmarksV2;
import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.ContentRequest;
import com.verint.services.km.model.ErrorList;
import com.verint.services.km.model.ListAllBookmarksV2Request;
import com.verint.services.km.model.ListAllBookmarksV2Response;
import com.verint.services.km.model.ManageBookmarkV2Request;
import com.verint.services.km.model.ManageBookmarkV2Response;

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
	 * @see com.verint.services.km.dao.BookmarksDAO#isContentBookmarked(com.verint.services.km.model.ContentRequest)
	 */
	public boolean isContentBookmarked(ContentRequest contentRequest) throws RemoteException, AppException {
		boolean isBookmarked = false;
		final ListAllBookmarksV2RequestBodyType bookmarkRequest = new ListAllBookmarksV2RequestBodyType();
		bookmarkRequest.setApplicationID(AppID);
		bookmarkRequest.setUserName(contentRequest.getUsername());
		bookmarkRequest.setPassword(contentRequest.getPassword());
		bookmarkRequest.setSortColumnName("");
		bookmarkRequest.setSortOrder("");

		// Check for a match
		Instant start = Instant.now();
		final ListAllBookmarksV2ResponseBodyType bookmarkResponse = KMBookmarkServiceV2PortType.listAllBookmarksV2(bookmarkRequest);
		Instant end = Instant.now();
		LOGGER.debug("SERVICE_CALL_PERFORMANCE - isContentBookmarked() duration: " + Duration.between(start, end).toMillis() + "ms");
		
		if (bookmarkResponse != null && bookmarkResponse.getResponse() != null) {
			final ContentBookmarksV2 content = bookmarkResponse.getResponse();
			
			//for (int x = 0; (content != null) && (x < content.length); x++) {
				// Is there a match?
			//	if (contentRequest.getContentId().equalsIgnoreCase(content[x].getContentId())) {
			//		isBookmarked = true;
			//	}
			//}
		} else {
			// We have a problem with the service
			throw new AppException(500, AppErrorCodes.CONTENT_BOOKMARK_ERROR,
					AppErrorMessage.CONTENT_BOOKMARK_ERROR);			
		}
		return isBookmarked;
	}
	
	



	
	private ManageBookmarkV2Response reorderBookmark(ManageBookmarkV2Request bookmarkRequest, String direction) throws RemoteException, AppException {
		LOGGER.info("Entering reorderBookmark()");
		LOGGER.debug("ManageBookmarkRequest: " + bookmarkRequest);
		final ManageBookmarkV2Response bookmarkResponse = new ManageBookmarkV2Response();

		// Setup the request
		final ReorderBookmarkAndFolderRequestBodyType request = new ReorderBookmarkAndFolderRequestBodyType();
		request.setApplicationID(AppID);
		request.setUser(bookmarkRequest.getUsername());
		request.setPassword(bookmarkRequest.getPassword());
		//request.(Locale);
		request.setContentID(bookmarkRequest.getContentId());
		request.setDirection(direction);
		
		// Call the service
		Instant start = Instant.now();
		final ReorderBookmarkAndFolderResponseBodyType response = KMBookmarkServiceV2PortType.reorderBookmarkAndFolder(request);
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
	public ManageBookmarksV2ResponseBodyType addBookmark(ManageBookmarksV2RequestBodyType manageBookmarkRequest)
			throws RemoteException, AppException {
		LOGGER.info("Entering add bookmark -");
		LOGGER.debug("ManageBookmarksV2RequestBodyType: " + manageBookmarkRequest);
		
		//manageBookmarkRequest.setUserAction("ADD");
		//final ReorderBookmarkAndFolderResponseBodyType reorderBookmarkResponse = new ReorderBookmarkAndFolderResponseBodyType();
		
		 ManageBookmarksV2ResponseBodyType manageBookmarkResponse = KMBookmarkServiceV2PortType.manageBookmarksV2(manageBookmarkRequest);

		return manageBookmarkResponse;
	}

	@Override
	public ManageBookmarksV2ResponseBodyType removeBookmark(ManageBookmarksV2RequestBodyType manageBookmarkRequest)
			throws RemoteException, AppException {
		LOGGER.info("Entering remove bookmark -");
		LOGGER.debug("ManageBookmarksV2RequestBodyType: " + manageBookmarkRequest);
		
		manageBookmarkRequest.setUserAction("REMOVE");
		//final ReorderBookmarkAndFolderResponseBodyType reorderBookmarkResponse = new ReorderBookmarkAndFolderResponseBodyType();
		
		final ManageBookmarksV2ResponseBodyType manageBookmarkResponse = KMBookmarkServiceV2PortType.manageBookmarksV2(manageBookmarkRequest);

		return manageBookmarkResponse;
	}

	@Override
	public ReorderBookmarkAndFolderResponseBodyType reorderBookmarkUp(
			ReorderBookmarkAndFolderRequestBodyType bookmarkRequest) throws RemoteException, AppException {
	
	LOGGER.info("Entering reorder bookmark up - ");
	LOGGER.debug("ReorderBookmarkAndFolderRequestBodyType: " + bookmarkRequest);
	
	bookmarkRequest.setDirection("UP");
	//final ReorderBookmarkAndFolderResponseBodyType reorderBookmarkResponse = new ReorderBookmarkAndFolderResponseBodyType();
	
	final ReorderBookmarkAndFolderResponseBodyType reorderBookmarkResponse = KMBookmarkServiceV2PortType.reorderBookmarkAndFolder(bookmarkRequest);

	return reorderBookmarkResponse;
	
	}

	@Override
	public ReorderBookmarkAndFolderResponseBodyType reorderFolderUp(
			ReorderBookmarkAndFolderRequestBodyType bookmarkRequest) throws RemoteException, AppException {
		LOGGER.info("Entering reorder folder up - ");
		LOGGER.debug("ReorderBookmarkAndFolderRequestBodyType: " + bookmarkRequest);
		
		bookmarkRequest.setDirection("UP");
		//final ReorderBookmarkAndFolderResponseBodyType reorderBookmarkResponse = new ReorderBookmarkAndFolderResponseBodyType();
		
		final ReorderBookmarkAndFolderResponseBodyType reorderBookmarkResponse = KMBookmarkServiceV2PortType.reorderBookmarkAndFolder(bookmarkRequest);

		return reorderBookmarkResponse;
	}

	@Override
	public ReorderBookmarkAndFolderResponseBodyType reorderFolderDown(
			ReorderBookmarkAndFolderRequestBodyType bookmarkRequest) throws RemoteException, AppException {
		LOGGER.info("Entering reorder folder down -");
		LOGGER.debug("ReorderBookmarkAndFolderRequestBodyType: " + bookmarkRequest);
		
		bookmarkRequest.setDirection("DOWN");
		//final ReorderBookmarkAndFolderResponseBodyType reorderBookmarkResponse = new ReorderBookmarkAndFolderResponseBodyType();
		
		final ReorderBookmarkAndFolderResponseBodyType reorderBookmarkResponse = KMBookmarkServiceV2PortType.reorderBookmarkAndFolder(bookmarkRequest);

		return reorderBookmarkResponse;
	}

	@Override
	public ReorderBookmarkAndFolderResponseBodyType reorderBookmarkDown(
			ReorderBookmarkAndFolderRequestBodyType bookmarkRequest) throws RemoteException, AppException {
		LOGGER.info("Entering reorder bookmark down -");
		LOGGER.debug("ReorderBookmarkAndFolderRequestBodyType: " + bookmarkRequest);
		
		bookmarkRequest.setDirection("DOWN");
		//final ReorderBookmarkAndFolderResponseBodyType reorderBookmarkResponse = new ReorderBookmarkAndFolderResponseBodyType();
		
		final ReorderBookmarkAndFolderResponseBodyType reorderBookmarkResponse = KMBookmarkServiceV2PortType.reorderBookmarkAndFolder(bookmarkRequest);

		return reorderBookmarkResponse;
	}

	@Override
	public ManageBookmarksV2ResponseBodyType addFolder(ManageBookmarksV2RequestBodyType manageBookmarkRequest)
			throws RemoteException, AppException {
		LOGGER.info("Entering add folder -");
		LOGGER.debug("ManageBookmarksV2ResponseBodyType: " + manageBookmarkRequest);
		manageBookmarkRequest.setUserAction("ADDFOLDER");
		final ManageBookmarksV2ResponseBodyType manageBookmarkResponse = KMBookmarkServiceV2PortType.manageBookmarksV2(manageBookmarkRequest);
		
		return manageBookmarkResponse;
	}

	 @Override
	 public ManageBookmarksV2ResponseBodyType removeFolder(ManageBookmarksV2RequestBodyType manageBookmarkRequest)
	   throws RemoteException, AppException {
	  LOGGER.info("Entering add folder -");
	  LOGGER.debug("ManageBookmarksV2ResponseBodyType: " + manageBookmarkRequest);
	  manageBookmarkRequest.setUserAction("REMOVEFOLDER");
	  final ManageBookmarksV2ResponseBodyType manageBookmarkResponse = KMBookmarkServiceV2PortType.manageBookmarksV2(manageBookmarkRequest);
	  
	  return manageBookmarkResponse;
	 }


	@Override
	public ListAllBookmarksV2Response listAllBookmarksV2(ListAllBookmarksV2Request listallRequest)
			throws RemoteException, AppException {
		LOGGER.info("Entering listAllBookmarksV2 -");
		LOGGER.debug("ListAllBookmarksV2RequestBodyType: " + listallRequest);

		ListAllBookmarksV2RequestBodyType listAllBookmarksV2Request = new  ListAllBookmarksV2RequestBodyType();
		listallRequest.setApplicationID(AppID);
		
		
		
		// Call the service
		Instant start = Instant.now();
		final ListAllBookmarksV2ResponseBodyType response = KMBookmarkServiceV2PortType.listAllBookmarksV2(listAllBookmarksV2Request);
		Instant end = Instant.now();
		LOGGER.debug("SERVICE_CALL_PERFORMANCE("+listAllBookmarksV2Request.getUserName()+") - listAllBookmarksV2() duration: " + Duration.between(start, end).toMillis() + "ms");

		ListAllBookmarksV2Response listAllBookmarksV2Response = new  ListAllBookmarksV2Response();
					
		//TODO Map the objects in the return
		
		
		LOGGER.debug("listallBookmarksV2Response: " + listallRequest);
		LOGGER.info("Exiting listAllBookmarksV2()");
		return listAllBookmarksV2Response;
		
		
	}

	
}