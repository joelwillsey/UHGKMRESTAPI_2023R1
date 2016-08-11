package com.verint.services.km.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verint.services.km.dao.TeamKBaseTagsDAO;
import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.Tag;
import com.verint.services.km.model.TeamKBaseTagsResponse;

@Path("/kbasetags")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Service
public class TeamKBaseTagsService extends BaseService {

	private final Logger LOGGER = LoggerFactory.getLogger(TeamKBaseTagsService.class);

	@Autowired
	private TeamKBaseTagsDAO teamKBaseTagsDAO;

	/**
	 * 
	 */
	public TeamKBaseTagsService() {
		super();
		LOGGER.debug("Entering TeamKBaseTagsService()");
		LOGGER.debug("Exiting TeamKBaseTagsService()");
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
    public TeamKBaseTagsResponse getKBaseTags(@Context HttpServletRequest httpRequest) {
		LOGGER.info("Entering getKBaseTags()");
		TeamKBaseTagsResponse teamKBaseTagsResponse = new TeamKBaseTagsResponse();

		try {
			// Get the authentication information
			final String[] credentials = getAuthenticatinCredentials(httpRequest);
			LOGGER.debug("Username: " + credentials[0]);
			LOGGER.debug("Password: " + credentials[1]);
	
			// Get all the cross tag combinations
			Set<Tag> kBaseTagSet = teamKBaseTagsDAO.getAllTeamKBaseTags(credentials[0], credentials[1]);
			for(Tag kbaseTag : kBaseTagSet) {
				teamKBaseTagsResponse.addTags(kbaseTag);
			}
			teamKBaseTagsResponse.setTags(kBaseTagSet);
		} catch (SQLException sqle) {
			LOGGER.error("SQLException in getKBaseTags()", sqle);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);			
		} catch (IOException ioe) {
			LOGGER.error("IOException in getKBaseTags()", ioe);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
		} catch (Throwable t) {
			LOGGER.error("Unexpected exception in getKBaseTags()", t);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
		}
		LOGGER.debug("TeamKBaseTagsResponse: " + teamKBaseTagsResponse);
		LOGGER.info("Exiting getKBaseTags()");
		return teamKBaseTagsResponse;
	}

}
