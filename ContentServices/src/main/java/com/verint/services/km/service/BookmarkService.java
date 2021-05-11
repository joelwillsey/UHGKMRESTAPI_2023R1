/**
 * 
 */
package com.verint.services.km.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verint.services.km.dao.BookmarksDAO;
import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.ManageBookmarkRequest;
import com.verint.services.km.model.ManageBookmarkResponse;


/**
 * @author jmiller
 *
 */
@Path("/bookmarkservice")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Service
public class BookmarkService extends BaseService {
	private final Logger LOGGER = LoggerFactory.getLogger(BookmarkService.class);

	@Autowired
	private BookmarksDAO bookmarksDAO;

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
	 * @param bookmark
	 * @param httpRequest
	 * @return
	 */
	@Path("/addbookmark")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public ManageBookmarkResponse addBookmark(ManageBookmarkRequest bookmark,
    		@Context HttpServletRequest httpRequest) {
		LOGGER.info("Entering addBookmark()");
		LOGGER.debug("ManageBookmarkRequest: " + bookmark);
		ManageBookmarkResponse manageBookmarkResponse = null;
		try {
			// Check for a valid request
			if (bookmark == null || bookmark.getContentId() == null) {
				LOGGER.error("No valid content ID");
				throw new AppException(500, AppErrorCodes.CONTENT_BOOKMARK_ERROR,
					AppErrorMessage.CONTENT_BOOKMARK_ERROR);
			}
			// Get the authentication information
			final String[] credentials = getAuthenticatinCredentials(httpRequest);
			LOGGER.debug("Username: " + credentials[0]);
			LOGGER.debug("Password: " + credentials[1]);

			bookmark.setUsername(credentials[0]);
			bookmark.setPassword(credentials[1]);
			bookmark.setOidcToken(getOIDCToken(httpRequest));
			
			// Call the service
			manageBookmarkResponse = bookmarksDAO.addBookmark(bookmark);
		} catch (AppException ae) {
			LOGGER.error("AppException in addBookmark()", ae);
			throw ae;
		} catch (Throwable t) {
			LOGGER.error("Unexpected exception in addBookmark()", t);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
		}
		LOGGER.debug("ManageBookmarkResponse: " + manageBookmarkResponse);
		LOGGER.info("Exiting addBookmark()");
		return manageBookmarkResponse;
	}
	
	/**
	 * 
	 * @param bookmark
	 * @param httpRequest
	 * @return
	 */
	@Path("/removebookmark")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public ManageBookmarkResponse removeBookmark(ManageBookmarkRequest bookmark,
    		@Context HttpServletRequest httpRequest) {
		LOGGER.info("Entering addBookmark()");
		LOGGER.debug("ManageBookmarkRequest: " + bookmark);
		ManageBookmarkResponse manageBookmarkResponse = null;
		try {
			// Check for a valid request
			if (bookmark == null || bookmark.getContentId() == null) {
				LOGGER.error("No valid content ID");
				throw new AppException(500, AppErrorCodes.CONTENT_BOOKMARK_ERROR,
					AppErrorMessage.CONTENT_BOOKMARK_ERROR);
			}
			// Get the authentication information
			final String[] credentials = getAuthenticatinCredentials(httpRequest);
			LOGGER.debug("Username: " + credentials[0]);
			LOGGER.debug("Password: " + credentials[1]);

			bookmark.setUsername(credentials[0]);
			bookmark.setPassword(credentials[1]);
			bookmark.setOidcToken(getOIDCToken(httpRequest));
			
			// Call the service
			manageBookmarkResponse = bookmarksDAO.removeBookmark(bookmark);
		} catch (AppException ae) {
			LOGGER.error("AppException in addBookmark()", ae);
			throw ae;
		} catch (Throwable t) {
			LOGGER.error("Unexpected exception in addBookmark()", t);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
		}
		LOGGER.debug("ManageBookmarkResponse: " + manageBookmarkResponse);
		LOGGER.info("Exiting addBookmark()");
		return manageBookmarkResponse;
	}
}