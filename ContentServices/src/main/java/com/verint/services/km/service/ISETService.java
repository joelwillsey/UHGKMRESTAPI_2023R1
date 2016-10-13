package com.verint.services.km.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verint.services.km.dao.ISETDAO;
import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.MigratableReferenceId;
import com.verint.services.km.model.IsetResponse;

@Path("/iset")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Service
public class ISETService extends BaseService{
	private final Logger LOGGER = LoggerFactory.getLogger(ISETService.class);
	
	@Autowired
	private ISETDAO isetDAO;
	
	
	/**
	 * 
	 */
	public ISETService() {
		super();
		LOGGER.debug("Entering ISETService()");
		LOGGER.debug("Exiting ISETService()");
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}
	@Path("/migref")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public IsetResponse getISETResponse(@Context HttpServletRequest httpRequest,
			@QueryParam("refName") String refName,
			@QueryParam("objType") String objType,
			@QueryParam("objID") String objID) {
		LOGGER.info("Entering getISETResponse()");		
		IsetResponse isetResponse = new IsetResponse();
		
		try {
			// Get the authentication information
			final String[] credentials = getAuthenticatinCredentials(httpRequest);
			LOGGER.debug("Username: " + credentials[0]);
			LOGGER.debug("Password: " + credentials[1]);
			
			Set<MigratableReferenceId> migratableReferenceId = isetDAO.getISETResponse(refName, objType, objID);
			
			isetResponse.setMigratableReferenceId(migratableReferenceId);
			
		}catch (AppException ae) {
			LOGGER.error("AppException in getISETResponse()", ae);
			throw ae;
		} catch (Throwable t) {
			LOGGER.error("Unexpected exception in getISETResponse()", t);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
		}
		return isetResponse;
	}
	
	

}
