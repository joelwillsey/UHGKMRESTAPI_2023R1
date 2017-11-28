/**
 * KMBookmarkServiceV2Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.verint.services.km.service;

import javax.servlet.http.HttpServletRequest;
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
import com.verint.services.km.service.BaseService;
import com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ManageBookmarksV2RequestBodyType;
import com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ManageBookmarksV2ResponseBodyType;
import com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ListAllBookmarksV2RequestBodyType;
import com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ListAllBookmarksV2ResponseBodyType;
//public interface KMBookmarkServiceV2Service extends javax.xml.rpc.Service {
	@Path("/bookmarksv2")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Service
	public class KMBookmarkServiceV2Service extends BaseService{
		private final Logger LOGGER = LoggerFactory.getLogger(KMBookmarkServiceV2Service.class);
		
		@Autowired
		private BookmarksV2DAO bookmarksV2DAO;
		
		
		/**
		 * 
		 */
		public KMBookmarkServiceV2Service() {
			super();
			LOGGER.debug("Entering KMBookmarkServiceV2Service()");
			LOGGER.debug("Exiting KMBookmarkServiceV2Service()");
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
			LOGGER.error("AppException in addFolder()", ae);
			throw ae;
		} catch (Throwable t) {
			LOGGER.error("Unexpected exception in addFolder()", t);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
		}
			return removeBookmarkResponse;
 }		
		
		
		
		@Path("/list")
		@GET
		@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		public ListAllBookmarksV2ResponseBodyType listAllBookmarksV2(@Context HttpServletRequest httpRequest){
			LOGGER.info("Entering list all()");		
			ListAllBookmarksV2ResponseBodyType listAllResponse = new ListAllBookmarksV2ResponseBodyType();
			final ListAllBookmarksV2RequestBodyType request = new ListAllBookmarksV2RequestBodyType();
			
			try {
				// Get the authentication information
				final String[] credentials = getAuthenticatinCredentials(httpRequest);
				LOGGER.debug("Username: " + credentials[0]);
				LOGGER.debug("Password: " + credentials[1]);
				request.setUserName(credentials[0]);
				request.setPassword(credentials[1]);
				//request.setApplicationID(applicationID);
				//request.set
				//request.setApplicationID("en-US");
				listAllResponse = bookmarksV2DAO.listAllBookmarksV2(request);

				
		}catch (AppException ae) {
			LOGGER.error("AppException in addFolder()", ae);
			throw ae;
		} catch (Throwable t) {
			LOGGER.error("Unexpected exception in addFolder()", t);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
		}
			return listAllResponse;
 }
		
	}

		
		 //  public java.lang.String getKMBookmarkServiceV2PortAddress();

		  //  public com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.KMBookmarkServiceV2PortType getKMBookmarkServiceV2Port() throws javax.xml.rpc.ServiceException;

		   // public com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.KMBookmarkServiceV2PortType getKMBookmarkServiceV2Port(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
