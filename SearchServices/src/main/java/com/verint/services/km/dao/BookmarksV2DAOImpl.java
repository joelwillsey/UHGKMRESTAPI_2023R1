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

import com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ListAllBookmarksV2RequestBodyType;
import com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ListAllBookmarksV2ResponseBodyType;
import com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ManageBookmarksV2RequestBodyType;
import com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ManageBookmarksV2ResponseBodyType;
import com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ReorderBookmarkAndFolderRequestBodyType;
import com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ReorderBookmarkAndFolderResponseBodyType;
import com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ErrorMessage;
import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.BookmarkFolderContent;
import com.verint.services.km.model.BookmarkFolderContents;
import com.verint.services.km.model.BookmarkSubFolderContents;
import com.verint.services.km.model.BookmarkedContentV2;
import com.verint.services.km.model.ContentBookmarksV2;
import com.verint.services.km.model.ContentRequest;
import com.verint.services.km.model.ErrorList;
import com.verint.services.km.model.ListAllBookmarksV2Request;
import com.verint.services.km.model.ListAllBookmarksV2Response;
import com.verint.services.km.model.ManageBookmarkV2Request;
import com.verint.services.km.model.ManageBookmarkV2Response;
import com.verint.services.km.model.ReorderBookmarkAndFolderRequest;
import com.verint.services.km.model.ReorderBookmarkAndFolderResponse;


/**
 * @author ARumpf
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
			final com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ContentBookmarksV2 content = bookmarkResponse.getResponse();
			
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
	
	



	
	public ReorderBookmarkAndFolderResponse reorderBookmark(ReorderBookmarkAndFolderRequest bookmarkRequest) throws RemoteException, AppException {
		LOGGER.info("Entering reorderBookmark()");
		LOGGER.debug("ManageBookmarkRequest: " + bookmarkRequest);
		ReorderBookmarkAndFolderResponse bookmarkResponse = new ReorderBookmarkAndFolderResponse();

		// Setup the request
		final ReorderBookmarkAndFolderRequestBodyType request = new ReorderBookmarkAndFolderRequestBodyType();
		request.setApplicationID(AppID);
		request.setUserName(bookmarkRequest.getUserName());
		request.setPassword(bookmarkRequest.getPassword());
		request.setDirection(bookmarkRequest.getDirection());
		request.setDestinationFolderID(bookmarkRequest.getDestinationFolderID());
		request.setFolderID(bookmarkRequest.getFolderID());
		request.setNumMoved(bookmarkRequest.getNumMoved());
		request.setContentID(bookmarkRequest.getContentID());
		request.setDirection(bookmarkRequest.getDirection());
		
		// Call the service
		Instant start = Instant.now();
		final ReorderBookmarkAndFolderResponseBodyType response = KMBookmarkServiceV2PortType.reorderBookmarkAndFolder(request);
		Instant end = Instant.now();
		LOGGER.debug("SERVICE_CALL_PERFORMANCE("+bookmarkRequest.getUserName()+") - reorderBookmark() duration: " + Duration.between(start, end).toMillis() + "ms");
		bookmarkResponse = populateReorderBookmarkAndFolderResponse(response, bookmarkResponse);
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
		LOGGER.debug("ReorderBookmarkAndFolderResponse: " + bookmarkResponse);
		LOGGER.info("Exiting reorderBookmark()");
		return bookmarkResponse;		
	}

	@Override
	public com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.BookmarkFolderContent getFolder(String folderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.BookmarkedContentV2 getBookmark(String contentId) {
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


	/*
	 * (non-Javadoc)
	 * @see com.verint.services.km.dao.BookmarksV2DAO#listAllBookmarksV2(com.verint.services.km.model.ListAllBookmarksV2Request)
	 */
	@Override
	public ListAllBookmarksV2Response listAllBookmarksV2(ListAllBookmarksV2Request listAllBookmarksV2Request)
			throws RemoteException, AppException {
		LOGGER.info("Entering listAllBookmarksV2 -");
		LOGGER.debug("ListAllBookmarksV2Request: " + listAllBookmarksV2Request.toString());
		ListAllBookmarksV2Response listAllBookmarksV2Response = new  ListAllBookmarksV2Response();
		
		// Setup the request object to the SOAP call
		final ListAllBookmarksV2RequestBodyType request = new ListAllBookmarksV2RequestBodyType();		
		request.setApplicationID(AppID);
		request.setPassword(listAllBookmarksV2Request.getPassword());
		request.setSortColumnName(listAllBookmarksV2Request.getSortColumnName());
		request.setSortOrder(listAllBookmarksV2Request.getSortOrder());
		request.setUserName(listAllBookmarksV2Request.getUsername());
		
		// Call the service
		Instant start = Instant.now();
		final ListAllBookmarksV2ResponseBodyType response = KMBookmarkServiceV2PortType.listAllBookmarksV2(request);
		Instant end = Instant.now();
		LOGGER.debug("SERVICE_CALL_PERFORMANCE("+request.getUserName()+") - listAllBookmarksV2() duration: " + Duration.between(start, end).toMillis() + "ms");
					
		//Check Response
		if (response != null && response.getResponse() != null) {
			// Valid response
			LOGGER.debug("listallBookmarksV2Response- Valid response: " + response.toString());
			// Populate Base Response
			listAllBookmarksV2Response = populatelistAllBookmarksResponse(response, listAllBookmarksV2Response);
			
		} else {
			// We have a problem with the service
			// We already have one, send necessary error message
			throw new AppException(500, AppErrorCodes.BOOKMARKSV2_LISTALLBOOKMARKSV2_ERROR,  
					AppErrorMessage.BOOKMARKSV2_LISTALLBOOKMARKSV2_ERROR);
		}
				
		LOGGER.debug("listallBookmarksV2Response: " + response.toString());
		LOGGER.info("Exiting listAllBookmarksV2()");
		return listAllBookmarksV2Response;
	}
	
	
	private ManageBookmarkV2Response populateManageResponse(ManageBookmarksV2ResponseBodyType soapResponse, ManageBookmarkV2Response restResponse){

		restResponse.setToReturn(true);
		
		return restResponse;		
	}
	
	private ReorderBookmarkAndFolderResponse populateReorderBookmarkAndFolderResponse(ReorderBookmarkAndFolderResponseBodyType soapResponse, ReorderBookmarkAndFolderResponse restResponse){
		ContentBookmarksV2 contentBookmarksV2 = new ContentBookmarksV2();		
		
		BookmarkedContentV2[] bookmarks = new BookmarkedContentV2[soapResponse.getResponse().getBookmarks().length];
		BookmarkFolderContents[] folders = new BookmarkFolderContents[soapResponse.getResponse().getFolders().length];
		
		bookmarks = populateBookmarkedContentV2List(soapResponse.getResponse().getBookmarks());
		folders = populateBookmarkFolderContentsList(soapResponse.getResponse().getFolders()); 				
				
		contentBookmarksV2.setBookmarks(bookmarks);
		contentBookmarksV2.setFolders(folders);
		
		restResponse.setResponse(contentBookmarksV2);
		
		return restResponse;		
	}
	private ListAllBookmarksV2Response populatelistAllBookmarksResponse(ListAllBookmarksV2ResponseBodyType soapResponse, ListAllBookmarksV2Response restResponse){
		
		ContentBookmarksV2 contentBookmarksV2 = new ContentBookmarksV2();		
		
		BookmarkedContentV2[] bookmarks = new BookmarkedContentV2[soapResponse.getResponse().getBookmarks().length];
		BookmarkFolderContents[] folders = new BookmarkFolderContents[soapResponse.getResponse().getFolders().length];
		
		bookmarks = populateBookmarkedContentV2List(soapResponse.getResponse().getBookmarks());
		folders = populateBookmarkFolderContentsList(soapResponse.getResponse().getFolders()); 				
				
		contentBookmarksV2.setBookmarks(bookmarks);
		contentBookmarksV2.setFolders(folders);
		
		restResponse.setContentBookmarksV2(contentBookmarksV2);
		
		return restResponse;
	}
	
	
	private BookmarkedContentV2 populateBookmarkedContentV2(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.BookmarkedContentV2 soapBookmark){
		
		BookmarkedContentV2 restBookmark = new BookmarkedContentV2();
		
		restBookmark.setContentID(soapBookmark.getContentId());
		restBookmark.setContentType(soapBookmark.getContentType());
		restBookmark.setCreatedDate(soapBookmark.getCreatedDate());
		restBookmark.setIsFeatured(soapBookmark.isIsFeatured());
		restBookmark.setIsNewOrChanged(soapBookmark.isIsNewOrChanged());
		restBookmark.setLocaleName(soapBookmark.getLocaleName());
		restBookmark.setParentFolderId(soapBookmark.getParentFolderId());
		restBookmark.setSequenceNumber(soapBookmark.getSequenceNumber());
		restBookmark.setSynopsis(soapBookmark.getSynopsis());
		restBookmark.setTitle(soapBookmark.getTitle());
		
		return restBookmark;
	}
	
	
	private BookmarkedContentV2[] populateBookmarkedContentV2List(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.BookmarkedContentV2[] soapBookmarkList){
		
		BookmarkedContentV2[] restBookmarkList = new BookmarkedContentV2[soapBookmarkList.length];
		
		for (int i = 0; i < soapBookmarkList.length; i++) {
			restBookmarkList[i]= populateBookmarkedContentV2(soapBookmarkList[i]);
		}
		
		return restBookmarkList;
	}
	
	
	private BookmarkFolderContent populateBookmarkFolderContent(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.BookmarkFolderContent soapFolder){
		
		BookmarkFolderContent restFolder = new BookmarkFolderContent();
		
		restFolder.setBookmarkContentList(populateBookmarkedContentV2List(soapFolder.getBookmarkedContentList()));
		restFolder.setCreatedDate(soapFolder.getDateCreated());
		restFolder.setFolderId(soapFolder.getFolderId());
		restFolder.setLocaleName(soapFolder.getLocaleName());
		restFolder.setParentFolderId(soapFolder.getParentFolderId());
		restFolder.setSequenceNumber(soapFolder.getSequenceNumber());
		restFolder.setTitle(soapFolder.getTitle());
		
		
		return restFolder;
		
	}
	
	private BookmarkFolderContent[] populateBookmarkFolderContentList(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.BookmarkFolderContent[] soapFolderList){
		
		BookmarkFolderContent[] restFolderList = new BookmarkFolderContent[soapFolderList.length];
		
		for (int i = 0; i < soapFolderList.length; i++) {
			restFolderList[i]= populateBookmarkFolderContent(soapFolderList[i]);
		}
		
		return restFolderList;
	}
	
	private BookmarkSubFolderContents populateBookmarkSubFolderContents(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.BookmarkSubFolderContents soapBookmarkSubFolderContents){
			
			BookmarkSubFolderContents restBookmarkSubFolderContents = new BookmarkSubFolderContents();
			
			restBookmarkSubFolderContents.setSubSubFolders(populateBookmarkFolderContentList(soapBookmarkSubFolderContents.getSubSubFolders()));
			restBookmarkSubFolderContents.setBookmarkFolderContent(populateBookmarkFolderContent(soapBookmarkSubFolderContents.getBookmarkFolderContent()));
			
			return restBookmarkSubFolderContents;
	}

	private BookmarkSubFolderContents[] populateBookmarkSubFolderContentsList(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.BookmarkSubFolderContents[] soapSubSubFolderList){
			
			BookmarkSubFolderContents[] restSubSubFolderList = new BookmarkSubFolderContents[soapSubSubFolderList.length];
			
			for (int i = 0; i < soapSubSubFolderList.length; i++) {
				restSubSubFolderList[i]= populateBookmarkSubFolderContents(soapSubSubFolderList[i]);
			}
			
			return restSubSubFolderList;
	}

	private BookmarkFolderContents populateBookmarkFolderContents(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.BookmarkFolderContents soapBookmarkFolderContents){
		
		BookmarkFolderContents restBookmarkFolderContents = new BookmarkFolderContents();
		
		restBookmarkFolderContents.setBookmarkFolderContent(populateBookmarkFolderContent(soapBookmarkFolderContents.getBookmarkFolderContent()));
		restBookmarkFolderContents.setSubFolders(populateBookmarkSubFolderContentsList(soapBookmarkFolderContents.getSubFolders()));
		
		return restBookmarkFolderContents;
	}
	
	private BookmarkFolderContents[] populateBookmarkFolderContentsList(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.BookmarkFolderContents[] soapFolderList){
		
		BookmarkFolderContents[] restFolderList = new BookmarkFolderContents[soapFolderList.length];
		
		for (int i = 0; i < soapFolderList.length; i++) {
			restFolderList[i]= populateBookmarkFolderContents(soapFolderList[i]);
		}
		
		return restFolderList;
	}

	@Override
	public ManageBookmarkV2Response manageBookmarksV2(ManageBookmarkV2Request bookmarkRequest)
			throws RemoteException, AppException {
		LOGGER.info("Entering reorderBookmark()");
		LOGGER.debug("ManageBookmarkRequest: " + bookmarkRequest);
		ManageBookmarkV2Response bookmarkResponse = new ManageBookmarkV2Response();

		// Setup the request
		final ManageBookmarksV2RequestBodyType request = new ManageBookmarksV2RequestBodyType();
		request.setApplicationID(AppID);
		request.setUserName(bookmarkRequest.getUserName());
		request.setPassword(bookmarkRequest.getPassword());
		request.setUserAction(bookmarkRequest.getUserAction());
		request.setFolderID(bookmarkRequest.getFolderID());
		request.setContentID(bookmarkRequest.getContentID());
		
		// Call the service
		Instant start = Instant.now();
		final ManageBookmarksV2ResponseBodyType response = KMBookmarkServiceV2PortType.manageBookmarksV2(request);
		Instant end = Instant.now();
		LOGGER.debug("SERVICE_CALL_PERFORMANCE("+bookmarkRequest.getUserName()+") - reorderBookmark() duration: " + Duration.between(start, end).toMillis() + "ms");
		bookmarkResponse = populateManageResponse(response, bookmarkResponse);
		if (response != null) {
			//error handling would go here
			
		} else {
			// We have a problem with the service
			throw new AppException(500, AppErrorCodes.MANAGE_BOOKMARK_ERROR,  
					AppErrorMessage.REORDER_BOOKMARK_ERROR);			
		}
		LOGGER.debug("Manage bookmark response: " + bookmarkResponse);
		LOGGER.info("Exiting manageBookmarksV2()");
		return bookmarkResponse;	
	}
}