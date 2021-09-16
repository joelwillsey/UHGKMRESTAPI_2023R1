/**
 * 
 */
package com.verint.services.km.service;

import java.rmi.RemoteException;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verint.services.km.dao.BookmarksDAO;
import com.verint.services.km.dao.ContentDAO;
import com.verint.services.km.dao.SearchDAO;
import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.ContentRequest;
import com.verint.services.km.model.ContentResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author jmiller
 *
 */
@Path("/content")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Service
public class ContentService extends BaseService {
	private final Logger LOGGER = LoggerFactory.getLogger(ContentService.class);
	org.jboss.logging.Logger LOG = org.jboss.logging.Logger.getLogger(ContentService.class);

	@Autowired
	private BookmarksDAO bookmarksDAO;

	@Autowired
	private ContentDAO contentDAO;

	@Autowired
	private SearchDAO searchDAO;

	/**
	 * 
	 */
	public ContentService() {
		super();
		LOGGER.debug("Entering ContentService()");
		LOGGER.debug("Exiting ContentService()");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

	/**
	 * 
	 * @param contentid, state, cVersion, httpRequest
	 * @return
	 */
	@GET
	//@Path("/id/{contentid}{state:(/state/[^/]+?)?}")

	@Path("/")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public ContentResponse content(@QueryParam("contentid") String contentid,
								   @QueryParam("state") String state,
								   @QueryParam("cVersion") String cVersion,
								   @QueryParam("version") String version,
								   @QueryParam("contentType") String contentType,
								   @QueryParam("externalSearchId") String externalSearchId,
								   @Context HttpServletRequest httpRequest) {
		LOGGER.info("Entering content()");
		LOGGER.debug("contentid: " + contentid);
		ContentResponse contentResponse = null;
		if (StringUtils.isEmpty(version) && !StringUtils.isEmpty(cVersion)) {
			version = cVersion;
		}
		LOGGER.debug("version: "+ version);
		try {
			// Check for a valid request
			if (contentid == null || contentid.length() == 0) {
				LOGGER.error("No valid content ID");
				throw new AppException(500, AppErrorCodes.CONTENT_RETRIEVAL_ERROR,
					AppErrorMessage.CONTENT_RETRIEVAL_ERROR);
			}

			// Get the authentication information
			final String[] credentials = getAuthenticatinCredentials(httpRequest);
			LOGGER.debug("Username: " + credentials[0]);
			LOGGER.debug("Password: " + credentials[1]);

			// Create the ContentRequest
			final ContentRequest contentRequest = new ContentRequest();
			contentRequest.setContentId(contentid);
			contentRequest.setUsername(credentials[0]);
			contentRequest.setPassword(credentials[1]);
			if (!StringUtils.isEmpty(state)) {
				contentRequest.setWorkflowState(state);
			}
			if (!StringUtils.isEmpty(version)) {
				contentRequest.setVersion(version);
			}
			contentRequest.setContentType(contentType);
			contentRequest.setOidcToken(getOIDCToken(httpRequest));
			LOGGER.debug("ContentService(" + contentRequest.getUsername() + ") - content() - Start");
			Instant startWrapper = Instant.now();
			// Call the service
			Instant start = Instant.now();			
			contentResponse = contentDAO.getContent(contentRequest);
			Instant end = Instant.now();
			LOGGER.debug("ContentService(" + contentRequest.getUsername() + ") - getContent(): " + Duration.between(start, end).toMillis() + "ms");

			// Check if content is bookmarked
			if (!StringUtils.isEmpty((state)) && !"PUBLISHED".equals(state)) {
				//bookmarks do not work on draft state content
				contentResponse.setBookmarked(false);
				LOGGER.debug("Content is in a non-PUBLISHED state skipping bookmarks and markAsViewed. Workflow State: " + state);
			} else {
				
				// Check if content is bookmarked
				start = Instant.now();
				contentResponse.setBookmarked(bookmarksDAO.isContentBookmarked(contentRequest));
				end = Instant.now();
				LOGGER.debug("ContentService(" + contentRequest.getUsername() + ") - setBookmarked() duration: " + Duration.between(start, end).toMillis() + "ms");
				
				// Mark content as viewed
				start = Instant.now();
				contentResponse.setViewUUID(searchDAO.markAsViewed(contentid, version, credentials[0], credentials[1],
						null, getOIDCToken(httpRequest), externalSearchId));
				end = Instant.now();
				LOGGER.debug("ContentService(" + contentRequest.getUsername() + ") - setViewUUID() duration: " + Duration.between(start, end).toMillis() + "ms");
			}
			
			Instant endWrapper = Instant.now();
			LOGGER.debug("ContentService(" + contentRequest.getUsername() + ") - content() - End duration: " + Duration.between(startWrapper, endWrapper).toMillis() + "ms");
			
		} catch (AppException ae) {
			LOGGER.error("AppException in content()", ae);
			throw ae;
		} catch (RemoteException re) {
			throw new AppException(500, AppErrorCodes.CONTENT_RETRIEVAL_ERROR,
					AppErrorMessage.CONTENT_RETRIEVAL_ERROR);
		} catch (Throwable t) {
			LOGGER.error("Unexpected exception in content()", t);
			int indexUnableToContentId;
			indexUnableToContentId = t.getMessage().indexOf("Unable to find content for this contentId");
			if (indexUnableToContentId != -1){
				throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
						t.getMessage().substring(indexUnableToContentId));
			} else {
				LOGGER.error("Error Message: " +t.getMessage());
				throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
						AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
			}
		}
		LOGGER.debug("ContentResponse: " + contentResponse);
		LOGGER.info("Exiting content()");
		return contentResponse;
	}
}
