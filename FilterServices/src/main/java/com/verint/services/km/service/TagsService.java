/**
 * 
 */
package com.verint.services.km.service;

import java.util.Set;

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

import com.verint.services.km.dao.TagsDAO;
import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.Tag;
import com.verint.services.km.model.TagResponse;
import com.verint.services.km.model.TagSet;
import com.verint.services.km.model.TagSetResponse;


/**
 * @author jmiller
 *
 */
@Path("/tags")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Service
public class TagsService extends BaseService {
	private final Logger LOGGER = LoggerFactory.getLogger(TagsService.class);

	@Autowired
	private TagsDAO tagsDAO;

	/**
	 * 
	 */
	public TagsService() {
		super();
		LOGGER.debug("Entering TagsService()");
		LOGGER.debug("Exiting TagsService()");
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
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public TagSetResponse getTagsets(@Context HttpServletRequest httpRequest) {
		LOGGER.info("Entering getTagsets()");
		TagSetResponse tagSetResponse = new TagSetResponse();

		try {
			// Get the authentication information
			final String[] credentials = getAuthenticatinCredentials(httpRequest);
			LOGGER.debug("Username: " + credentials[0]);
			LOGGER.debug("Password: " + credentials[1]);
	
			// Get all the tagsets
			final TagSet[] tagsets = tagsDAO.getAllTagSets(credentials[0], credentials[1]);
			LOGGER.debug("TagSet[]: " + tagsets);
			for (int x = 0; (tagsets != null) && x < tagsets.length; x++) {
				tagSetResponse.addTagSets(tagsets[x]);
			}
		} catch (Throwable t) {
			LOGGER.error("Unexpected exception in getCrossTags()", t);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
		}
		LOGGER.debug("TagSetResponse: " + tagSetResponse);
		LOGGER.info("Exiting getTagsets()");
		return tagSetResponse;
	}

	/**
	 * 
	 * @param tagset
	 * @return
	 */
	@GET
	@Path("/gettags/{tagset}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public TagResponse getTagset(@PathParam("tagset") String tagset,
    		@Context HttpServletRequest httpRequest) {
		LOGGER.info("Entering getTagset()");
		LOGGER.debug("tagset: " + tagset);
		final TagResponse tagResponse = new TagResponse();

		try {
			// Get the authentication information
			final String[] credentials = getAuthenticatinCredentials(httpRequest);
			LOGGER.debug("Username: " + credentials[0]);
			LOGGER.debug("Password: " + credentials[1]);
	
			// Get the tags for the tagset
			final Tag[] tags = tagsDAO.getTagSet(credentials[0], credentials[1], tagset);
			LOGGER.debug("Tag[]: " + tags);
			for (int x = 0; (tags != null) && x < tags.length; x++) {
				tagResponse.addTag(tags[x]);
			}
		} catch (Throwable t) {
			LOGGER.error("Unexpected exception in getCrossTags()", t);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
		}
		LOGGER.debug("TagResponse: " + tagResponse);
		LOGGER.info("Exiting getTagset()");
		return tagResponse;
	}

	/**
	 * 
	 * @param tagsets
	 * @return
	 */
	@GET
	@Path("/gettagsfortagsets")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public TagSetResponse getTagsets(@QueryParam("tagsets") String tagsets,
    		@Context HttpServletRequest httpRequest) {
		LOGGER.info("Entering getTagsets()");
		LOGGER.debug("tagsets: " + tagsets);
		final TagSetResponse tagSetResponse = new TagSetResponse();
		
		try {
			// Get the authentication information
			final String[] credentials = getAuthenticatinCredentials(httpRequest);
			LOGGER.debug("Username: " + credentials[0]);
			LOGGER.debug("Password: " + credentials[1]);
	
			// Get the tags for the tagset
			String[] tokens = null;
			if (tagsets != null && tagsets.length() > 0) {
				tokens = tagsets.split(",");
				final Set<TagSet> tagSetTags = tagsDAO.getTagSets(credentials[0], credentials[1], tokens);
				LOGGER.debug("TagSet[]: " + tagSetTags);
				tagSetResponse.setTagSets(tagSetTags);
			} else {
				// TODO
			}
		} catch (Throwable t) {
			LOGGER.error("Unexpected exception in getCrossTags()", t);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
		}
		LOGGER.debug("TagSetResponse: " + tagSetResponse);
		LOGGER.info("Exiting getTagsets()");
		return tagSetResponse;
	}
}