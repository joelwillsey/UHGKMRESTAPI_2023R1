/**
 * 
 */
package com.verint.services.km.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verint.services.km.dao.BookmarkDAO;
import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.ManageBookmarkRequest;
import com.verint.services.km.model.ManageBookmarkResponse;


/**
 * @author jmiller
 *
 */
@Path("/bookmark")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Service
public class BookmarkService extends BaseService {
	private final Logger LOGGER = LoggerFactory.getLogger(BookmarkService.class);

	@Autowired
	private BookmarkDAO bookmarkDAO;

	/**
	 * 
	 */
	public BookmarkService() {
		super();
		LOGGER.debug("Entering BookmarkService()");
		LOGGER.debug("Exiting BookmarkService()");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

	/**
	 * 
	 * @param contentId
	 * @param httpRequest
	 * @return
	 * @throws AppException
	 */
	@POST
	@Path("/add")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public ManageBookmarkResponse add(@QueryParam("contentid") String contentid, 
    		@Context HttpServletRequest httpRequest) throws AppException {
		LOGGER.info("Entering add()");
		ManageBookmarkResponse bookmarkResponse = null;

		try {
			LOGGER.debug("contentid: " + contentid);
	
			// Check for valid fields
			if (contentid == null || contentid.length() == 0) {
				throw new AppException(500, AppErrorCodes.NO_CONTENTID_BOOKMARK,  
						AppErrorMessage.NO_CONTENTID_BOOKMARK);
			}
			// Get the authentication information
			final String[] credentials = getAuthenticatinCredentials(httpRequest);

			// Create the ManageBookmarkRequest
			final ManageBookmarkRequest bookmarkRequest = new ManageBookmarkRequest();
			bookmarkRequest.setContentId(contentid);
			bookmarkRequest.setUsername(credentials[0]);
			bookmarkRequest.setPassword(credentials[1]);
			LOGGER.debug("ManageBookmarkRequest: " + bookmarkRequest);

			// add the bookmark
			bookmarkResponse = bookmarkDAO.addBookmark(bookmarkRequest);
		} catch (AppException ae) {
			LOGGER.error("AppException in add()", ae);
			throw ae;
		} catch (Throwable t) {
			LOGGER.error("Unexpected exception in add()", t);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
		}
		LOGGER.debug("ManageBookmarkResponse: " + bookmarkResponse);
		LOGGER.info("Exiting add()");
		return bookmarkResponse;
	}

	/**
	 * 
	 * @param contentId
	 * @param httpRequest
	 * @return
	 * @throws AppException
	 */
	@POST
	@Path("/remove")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public ManageBookmarkResponse remove(@QueryParam("contentid") String contentid, 
    		@Context HttpServletRequest httpRequest) throws AppException {
		LOGGER.info("Entering remove()");
		ManageBookmarkResponse bookmarkResponse = null;

		try {
			LOGGER.debug("contentid: " + contentid);
	
			// Check for valid fields
			if (contentid == null || contentid.length() == 0) {
				throw new AppException(500, AppErrorCodes.NO_CONTENTID_BOOKMARK,  
						AppErrorMessage.NO_CONTENTID_BOOKMARK);
			}
			// Get the authentication information
			final String[] credentials = getAuthenticatinCredentials(httpRequest);

			// Create the ManageBookmarkRequest
			final ManageBookmarkRequest bookmarkRequest = new ManageBookmarkRequest();
			bookmarkRequest.setContentId(contentid);
			bookmarkRequest.setUsername(credentials[0]);
			bookmarkRequest.setPassword(credentials[1]);
			LOGGER.debug("ManageBookmarkRequest: " + bookmarkRequest);

			// remove the bookmark
			bookmarkResponse = bookmarkDAO.removeBookmark(bookmarkRequest);
		} catch (AppException ae) {
			LOGGER.error("AppException in remove()", ae);
			throw ae;
		} catch (Throwable t) {
			LOGGER.error("Unexpected exception in remove()", t);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
		}
		LOGGER.debug("ManageBookmarkResponse: " + bookmarkResponse);
		LOGGER.info("Exiting remove()");
		return bookmarkResponse;
	}

	/**
	 * 
	 * @param contentId
	 * @param httpRequest
	 * @return
	 * @throws AppException
	 */
	@POST
	@Path("/reorderup")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public ManageBookmarkResponse reorderup(@QueryParam("contentid") String contentid, 
    		@Context HttpServletRequest httpRequest) throws AppException {
		LOGGER.info("Entering reorderup()");
		ManageBookmarkResponse bookmarkResponse = null;

		try {
			LOGGER.debug("contentid: " + contentid);
	
			// Check for valid fields
			if (contentid == null || contentid.length() == 0) {
				throw new AppException(500, AppErrorCodes.NO_CONTENTID_BOOKMARK,  
						AppErrorMessage.NO_CONTENTID_BOOKMARK);
			}
			// Get the authentication information
			final String[] credentials = getAuthenticatinCredentials(httpRequest);

			// Create the ManageBookmarkRequest
			final ManageBookmarkRequest bookmarkRequest = new ManageBookmarkRequest();
			bookmarkRequest.setContentId(contentid);
			bookmarkRequest.setUsername(credentials[0]);
			bookmarkRequest.setPassword(credentials[1]);
			LOGGER.debug("ManageBookmarkRequest: " + bookmarkRequest);

			// reorderup the bookmark
			bookmarkResponse = bookmarkDAO.reorderBookmarkUp(bookmarkRequest);
		} catch (AppException ae) {
			LOGGER.error("AppException in reorderup()", ae);
			throw ae;
		} catch (Throwable t) {
			LOGGER.error("Unexpected exception in reorderup()", t);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
		}
		LOGGER.debug("ManageBookmarkResponse: " + bookmarkResponse);
		LOGGER.info("Exiting reorderup()");
		return bookmarkResponse;
	}

	/**
	 * 
	 * @param contentId
	 * @param httpRequest
	 * @return
	 * @throws AppException
	 */
	@POST
	@Path("/reorderdown")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public ManageBookmarkResponse reorderdown(@QueryParam("contentid") String contentid, 
    		@Context HttpServletRequest httpRequest) throws AppException {
		LOGGER.info("Entering reorderdown()");
		ManageBookmarkResponse bookmarkResponse = null;

		try {
			LOGGER.debug("contentid: " + contentid);
	
			// Check for valid fields
			if (contentid == null || contentid.length() == 0) {
				throw new AppException(500, AppErrorCodes.NO_CONTENTID_BOOKMARK,  
						AppErrorMessage.NO_CONTENTID_BOOKMARK);
			}
			// Get the authentication information
			final String[] credentials = getAuthenticatinCredentials(httpRequest);

			// Create the ManageBookmarkRequest
			final ManageBookmarkRequest bookmarkRequest = new ManageBookmarkRequest();
			bookmarkRequest.setContentId(contentid);
			bookmarkRequest.setUsername(credentials[0]);
			bookmarkRequest.setPassword(credentials[1]);
			LOGGER.debug("ManageBookmarkRequest: " + bookmarkRequest);

			// reorderdown the bookmark
			bookmarkResponse = bookmarkDAO.reorderBookmarkDown(bookmarkRequest);
		} catch (AppException ae) {
			LOGGER.error("AppException in reorderdown()", ae);
			throw ae;
		} catch (Throwable t) {
			LOGGER.error("Unexpected exception in reorderdown()", t);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
		}
		LOGGER.debug("ManageBookmarkResponse: " + bookmarkResponse);
		LOGGER.info("Exiting reorderdown()");
		return bookmarkResponse;
	}
}