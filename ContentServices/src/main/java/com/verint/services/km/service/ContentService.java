/**
 * 
 */
package com.verint.services.km.service;

import java.rmi.RemoteException;

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
	 * @param userid
	 * @return
	 */
	@GET
	@Path("/id/{contentid}{state:(/state/[^/]+?)?}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public ContentResponse content(@PathParam("contentid") String contentid,
    		@PathParam("state") String state,
    		@Context HttpServletRequest httpRequest) {
		LOGGER.info("Entering content()");
		LOGGER.debug("contentid: " + contentid);
		String workflowState = "";
		if(state.equals("")){
			LOGGER.debug("Optional parameter state not specified");
		} else {
			workflowState = state.split("/")[2];
			LOGGER.debug("status: "+ workflowState);
		}

		ContentResponse contentResponse = null;
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
			if(!workflowState.equals("")){
				contentRequest.setWorkflowState(workflowState);
			}
			
			// Call the service
			
			contentResponse = contentDAO.getContent(contentRequest);
			

			// Check if content is bookmarked
			if(!workflowState.equals("PUBLISHED")){
				//bookmarks do not work on draft state content
				contentResponse.setBookmarked(false);
				LOGGER.debug("Content is in a non-PUBLISHED state skipping bookmarks and markAsViewed");
			} else {
				
				// Check if content is bookmarked
				contentResponse.setBookmarked(bookmarksDAO.isContentBookmarked(contentRequest));
				
				// Mark content as viewed
				contentResponse.setViewUUID(searchDAO.markAsViewed(contentid, credentials[0], credentials[1]));
			}
			
			
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