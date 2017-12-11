/**
 * 
 */
package com.verint.services.km.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.StringTokenizer;

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

import com.verint.services.km.dao.CrossTagsDAO;
import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.CrossTagResponse;


/**
 * @author jmiller
 *
 */
@Path("/crosstags")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Service
public class CrossTagsService extends BaseService {
	private final Logger LOGGER = LoggerFactory.getLogger(CrossTagsService.class);

	@Autowired
	private CrossTagsDAO crossTagsDAO;

	/**
	 * 
	 */
	public CrossTagsService() {
		super();
		LOGGER.debug("Entering CrossTagsService()");
		LOGGER.debug("Exiting CrossTagsService()");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

	/**
	 * 
	 * @param sourcetag
	 * @param targettagset
	 * @param httpRequest
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public CrossTagResponse getCrossTags(@QueryParam("sourcetag") String sourcetag,
    		@QueryParam("targettagset") String targettagset,
    		@QueryParam("targettagset1") String targettagset1,
    		@QueryParam("targettagset2") String targettagset2,
    		@QueryParam("targettagset3") String targettagset3,
    		@Context HttpServletRequest httpRequest) {
		LOGGER.info("Entering getCrossTags()");
		LOGGER.debug("sourcetag: " + sourcetag);
		LOGGER.debug("targettagset: " + targettagset);
		LOGGER.debug("targettagset1: " + targettagset1);
		LOGGER.debug("targettagset2: " + targettagset2);
		LOGGER.debug("targettagset3: " + targettagset3);
		CrossTagResponse crossTagResponse = new CrossTagResponse();

		try {
			// Get the authentication information
			getAuthenticatinCredentials(httpRequest);
			LOGGER.debug("Username: " + credentials[0]);
			LOGGER.debug("Password: " + credentials[1]);

			if (sourcetag == null || sourcetag.length() == 0 ||
				targettagset == null || targettagset.length() == 0) {
				throw new AppException(500, AppErrorCodes.CROSS_TAGS_ERROR,  
						AppErrorMessage.CROSS_TAGS_ERROR);			
			} else {
				final StringTokenizer st2 = new StringTokenizer(sourcetag, ",");
				if (st2 != null && st2.countTokens() > 0) {
					String[] stags = new String[st2.countTokens()];
					int x = 0;
					while (st2.hasMoreTokens()) {
						stags[x++] = st2.nextToken(); 
					}
					// Get all the cross tag combinations
					crossTagResponse = crossTagsDAO.getTagSetConfigurations(credentials[0], credentials[1], stags, targettagset, targettagset1, targettagset2, targettagset3);
				}
			}
		} catch (SQLException sqle) {
			LOGGER.error("SQLException in getCrossTags()", sqle);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);			
		} catch (IOException ioe) {
			LOGGER.error("IOException in getCrossTags()", ioe);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
		} catch (Throwable t) {
			LOGGER.error("Unexpected exception in getCrossTags()", t);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
		}
		LOGGER.debug("CrossTagResponse: " + crossTagResponse);
		LOGGER.info("Exiting getCrossTags()");
		return crossTagResponse;
	}
}