package com.verint.services.km.service;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verint.services.km.dao.BookmarksV2DAO;
import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.ListAllBookmarksV2Request;
import com.verint.services.km.model.ListAllBookmarksV2Response;
import com.verint.services.km.model.ManageBookmarkV2Request;
import com.verint.services.km.model.ManageBookmarkV2Response;
import com.verint.services.km.model.ReorderBookmarkAndFolderRequest;
import com.verint.services.km.model.ReorderBookmarkAndFolderResponse;
import com.verint.services.km.service.BaseService;
import com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ManageBookmarksV2RequestBodyType;
import com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ManageBookmarksV2ResponseBodyType;
import com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ListAllBookmarksV2RequestBodyType;
import com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ListAllBookmarksV2ResponseBodyType;
import com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ReorderBookmarkAndFolderRequestBodyType;
import com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ReorderBookmarkAndFolderResponseBodyType;
/**
 * @author ARumpf
 *
 */

	@Path("/bookmarksv2")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Service
	public class BookmarkV2Service extends BaseService{
		private final Logger LOGGER = LoggerFactory.getLogger(BookmarkV2Service.class);
		
		@Autowired
		private BookmarksV2DAO bookmarksV2DAO;
		
		
		/**
		 * 
		 */
		public BookmarkV2Service() {
			super();
			LOGGER.debug("Entering BookmarkV2Service()");
			LOGGER.debug("Exiting BookmarkV2Service()");
		}
		

		/**
		 * @param args
		 */
		public static void main(String[] args) {
		}
		@Path("/addbookmark")
		@GET
		@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		public ManageBookmarksV2ResponseBodyType addBookmark(@Context HttpServletRequest httpRequest,
				@QueryParam("contentID") String contentID){
			LOGGER.info("Entering addBookmark()");		
			ManageBookmarksV2ResponseBodyType addBookmarkResponse = new ManageBookmarksV2ResponseBodyType();
			final ManageBookmarksV2RequestBodyType request = new ManageBookmarksV2RequestBodyType();
			
			try {
				// Get the authentication information
				final String[] credentials = getAuthenticatinCredentials(httpRequest);
				LOGGER.debug("Username: " + credentials[0]);
				LOGGER.debug("Password: " + credentials[1]);
				request.setUserName(credentials[0]);
				request.setPassword(credentials[1]);
				request.setUserAction("ADDBOOKMARK");
				request.setContentID(contentID);
				request.setApplicationID("en-US");
				addBookmarkResponse = bookmarksV2DAO.addBookmark(request);

				
		}catch (AppException ae) {
			LOGGER.error("AppException in addBookmark()", ae);
			throw ae;
		} catch (Throwable t) {
			LOGGER.error("Unexpected exception in addBookmark()", t);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
		}
			return addBookmarkResponse;
 }
		
		@Path("/removebookmark")
		@GET
		@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		public ManageBookmarksV2ResponseBodyType removeBookmark(@Context HttpServletRequest httpRequest,
				@QueryParam("contentID") String contentID){
			LOGGER.info("Entering addBookmark()");		
			ManageBookmarksV2ResponseBodyType removeBookmarkResponse = new ManageBookmarksV2ResponseBodyType();
			final ManageBookmarksV2RequestBodyType request = new ManageBookmarksV2RequestBodyType();
			
			try {
				// Get the authentication information
				final String[] credentials = getAuthenticatinCredentials(httpRequest);
				LOGGER.debug("Username: " + credentials[0]);
				LOGGER.debug("Password: " + credentials[1]);
				request.setUserName(credentials[0]);
				request.setPassword(credentials[1]);
				request.setUserAction("REMOVEBOOKMARK");
				request.setContentID(contentID);
				request.setApplicationID("en-US");
				removeBookmarkResponse = bookmarksV2DAO.removeBookmark(request);

				
		}catch (AppException ae) {
			LOGGER.error("AppException in removeBookmark()", ae);
			throw ae;
		} catch (Throwable t) {
			LOGGER.error("Unexpected exception in removeBookmark()", t);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
		}
			return removeBookmarkResponse;
 }		
		
		@Path("/addfolder")
		@GET
		@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		public ManageBookmarksV2ResponseBodyType addFolder(@Context HttpServletRequest httpRequest,
				@QueryParam("foldername") String folderName){
			LOGGER.info("Entering addBookmark()");		
			ManageBookmarksV2ResponseBodyType removeBookmarkResponse = new ManageBookmarksV2ResponseBodyType();
			final ManageBookmarksV2RequestBodyType request = new ManageBookmarksV2RequestBodyType();
			
			try {
				// Get the authentication information
				final String[] credentials = getAuthenticatinCredentials(httpRequest);
				LOGGER.debug("Username: " + credentials[0]);
				LOGGER.debug("Password: " + credentials[1]);
				request.setUserName(credentials[0]);
				request.setPassword(credentials[1]);
				request.setUserAction("ADDFOLDER");
				request.setFolderName(folderName);
				request.setApplicationID("en-US");
				removeBookmarkResponse = bookmarksV2DAO.addFolder(request);

				
		}catch (AppException ae) {
			LOGGER.error("AppException in addFolder()", ae);
			throw ae;
		} catch (Throwable t) {
			LOGGER.error("Unexpected exception in addFolder()", t);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
		}
			return removeBookmarkResponse;
 }			
		
		
		@Path("/removefolder")
		@GET
		@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		public ManageBookmarksV2ResponseBodyType removeFolder(@Context HttpServletRequest httpRequest,
				@QueryParam("folderID") String folderID){
			LOGGER.info("Entering removeFolder()");		
			ManageBookmarksV2ResponseBodyType removeBookmarkResponse = new ManageBookmarksV2ResponseBodyType();
			final ManageBookmarksV2RequestBodyType request = new ManageBookmarksV2RequestBodyType();
			
			try {
				// Get the authentication information
				final String[] credentials = getAuthenticatinCredentials(httpRequest);
				LOGGER.debug("Username: " + credentials[0]);
				LOGGER.debug("Password: " + credentials[1]);
				request.setUserName(credentials[0]);
				request.setPassword(credentials[1]);
				request.setUserAction("REMOVEFOLDER");
				request.setFolderID(folderID);
				request.setApplicationID("en-US");
				removeBookmarkResponse = bookmarksV2DAO.removeFolder(request);

				
		}catch (AppException ae) {
			LOGGER.error("AppException in removeFolder()", ae);
			throw ae;
		} catch (Throwable t) {
			LOGGER.error("Unexpected exception in removeFolder()", t);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
		}
			return removeBookmarkResponse;
 }		
		
		

		/**
		 * 
		 * @param sortOrder
		 * @param sortColumnName
		 * @param applicationID
		 * @param httpRequest
		 * @return
		 * @throws AppException
		 */
		@Path("/listallbookmarks")
		@GET
		@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		public ListAllBookmarksV2Response listAllBookmarksV2(@Context HttpServletRequest httpRequest,
				@QueryParam("sortorder") String sortOrder,
	    		@QueryParam("sortcolumnname") String sortColumnName,
				@QueryParam("applicationID") String applicationID){
			
		LOGGER.info("Entering ListAllBookmarksV2()");		
		ListAllBookmarksV2Response listAllBookmarksResponse = null;
			
		try {
			LOGGER.debug("sortOrder: "+sortOrder);
			LOGGER.debug("sortColumnName: "+sortColumnName);
			LOGGER.debug("applicationID: "+applicationID);
			
			
			// Check for valid fields
			if (sortOrder == null)  {
				sortOrder = "";
			}			
			if (sortColumnName == null)  {
				sortColumnName = "";
			}			
			if (applicationID == null || applicationID.equals(""))  {
				applicationID = "AD";
			}
		
			
			// Get the authentication information			
			final String[] credentials = getAuthenticatinCredentials(httpRequest);
			
			final ListAllBookmarksV2Request listAllBookmarksRequest = new ListAllBookmarksV2Request();
			listAllBookmarksRequest.setUsername(credentials[0]);
			listAllBookmarksRequest.setPassword(credentials[1]);
			listAllBookmarksRequest.setSortOrder(sortOrder);
			listAllBookmarksRequest.setSortColumnName(sortColumnName);
			listAllBookmarksRequest.setApplicationID(applicationID);
			listAllBookmarksRequest.setLocale("en-US");
			LOGGER.debug("ListAllBookmarksRequest: " + listAllBookmarksRequest);
			
			//Retrieve all the bookmarks
			listAllBookmarksResponse = bookmarksV2DAO.listAllBookmarksV2(listAllBookmarksRequest);
			
			if (listAllBookmarksResponse == null){
				listAllBookmarksResponse = new ListAllBookmarksV2Response();
			}
				
				
		}catch (AppException ae) {
			LOGGER.error("AppException in listAllBookmarksV2()", ae);
			throw ae;
		} catch (Throwable t) {
			LOGGER.error("Unexpected exception in listAllBookmarksV2()", t);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
		}
		
		LOGGER.debug("ListAllBookmarksResponse: " + listAllBookmarksResponse);
		LOGGER.info("Exiting ListAllBookmarksV2()");
		return listAllBookmarksResponse;
 }
		

		/**
		 * 
		 * @param sortOrder
		 * @param sortColumnName
		 * @param applicationID
		 * @param httpRequest
		 * @return
		 * @throws AppException
		 */
		@Path("/reorder")
		@GET
		@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		public ReorderBookmarkAndFolderResponse reorderBookmarkAndFolder(@Context HttpServletRequest httpRequest,
				@QueryParam("direction") String direction,
	    		@QueryParam("numMoved") BigInteger numMoved,
				@QueryParam("applicationID") String applicationID,
				@QueryParam("contentID") String contentID,
				@QueryParam("folderID") BigInteger folderID,
				@QueryParam("destFolder") BigInteger destinationFolderID){
			
		LOGGER.info("Entering reorderBookmarkAndFolder()");		
		ReorderBookmarkAndFolderResponse reorderBookmarkAndFolderResponse = null;
			
		try {
			
			LOGGER.debug("applicationID: "+applicationID);
			
			
			// Check for valid fields
	
			if (contentID == null)  {
				contentID = "";
			}			
			if (applicationID == null || applicationID.equals(""))  {
				applicationID = "AD";
			}
		
			
			// Get the authentication information			
			final String[] credentials = getAuthenticatinCredentials(httpRequest);
			
			final ReorderBookmarkAndFolderRequest reorderRequest = new ReorderBookmarkAndFolderRequest();
			reorderRequest.setUserName(credentials[0]);
			reorderRequest.setPassword(credentials[1]);
			reorderRequest.setFolderID(folderID);
			reorderRequest.setDirection(direction);
			reorderRequest.setDestinationFolderID(destinationFolderID);
			reorderRequest.setNumMoved(numMoved);
			reorderRequest.setContentID(contentID);
			reorderRequest.setApplicationID(applicationID);
			reorderRequest.setLocaleName("en-US");
			LOGGER.debug("Reorder request: " + reorderRequest);
			
			//Retrieve all the bookmarks
			
			reorderBookmarkAndFolderResponse = bookmarksV2DAO.reorderBookmark(reorderRequest);
			
			if (reorderBookmarkAndFolderResponse == null){
				reorderBookmarkAndFolderResponse = new ReorderBookmarkAndFolderResponse();
			}
				
				
		}catch (AppException ae) {
			LOGGER.error("AppException in reorderBookmarkAndFolder()", ae);
			throw ae;
		} catch (Throwable t) {
			LOGGER.error("Unexpected exception in reorderBookmarkAndFolder()", t);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
		}
		
		LOGGER.debug("ReorderBookmarkAndFolderResponse: " + reorderBookmarkAndFolderResponse);
		LOGGER.info("Exiting reorderBookmarkAndFolder()");
		return reorderBookmarkAndFolderResponse;
 }
		
		
		
		
		@Path("/manage")
		@GET
		@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		public ManageBookmarkV2Response manageBookmarksV2(@Context HttpServletRequest httpRequest,
				@QueryParam("userAction") String userAction,
	    		@QueryParam("folderName") String folderName,
				@QueryParam("applicationID") String applicationID,
				@QueryParam("contentID") String contentID,
				@QueryParam("folderID") String folderID){
			
		LOGGER.info("Entering ManageBookmarkV2Response()");		
		ManageBookmarkV2Response manageBookmarkV2Response = null;
			
		try {
			
			LOGGER.debug("applicationID: "+applicationID);
			
			
			// Check for valid fields
	
			if (contentID == null)  {
				contentID = "";
			}			
			if (applicationID == null || applicationID.equals(""))  {
				applicationID = "AD";
			}
		
			
			// Get the authentication information			
			final String[] credentials = getAuthenticatinCredentials(httpRequest);
			
			final ManageBookmarkV2Request manageRequest = new ManageBookmarkV2Request();
			manageRequest.setUserName(credentials[0]);
			manageRequest.setPassword(credentials[1]);
			manageRequest.setFolderId(folderID);
			manageRequest.setUserAction(userAction);
			manageRequest.setFolderName(folderName);
			manageRequest.setContentID(contentID);
			manageRequest.setApplicationID(applicationID);
			manageRequest.setLocaleName("en-US");
			LOGGER.debug("Reorder request: " + manageRequest);
			
			//Retrieve all the bookmarks
			
			manageBookmarkV2Response = bookmarksV2DAO.manageBookmarksV2(manageRequest);
			
			if (manageBookmarkV2Response == null){
				manageBookmarkV2Response = new ManageBookmarkV2Response();
			}
				
				
		}catch (AppException ae) {
			LOGGER.error("AppException in manageBookmarkV2()", ae);
			throw ae;
		} catch (Throwable t) {
			LOGGER.error("Unexpected exception in manageBookmarkV2()", t);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
		}
		
		LOGGER.debug("manageBookmarkV2Response: " + manageBookmarkV2Response);
		LOGGER.info("Exiting manageBookmarkV2()");
		return manageBookmarkV2Response;
 }
		
	}

		
		 //  public java.lang.String getKMBookmarkServiceV2PortAddress();

		  //  public com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.KMBookmarkServiceV2PortType getKMBookmarkServiceV2Port() throws javax.xml.rpc.ServiceException;

		   // public com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.KMBookmarkServiceV2PortType getKMBookmarkServiceV2Port(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
