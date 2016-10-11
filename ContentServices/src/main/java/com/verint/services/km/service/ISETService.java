package com.verint.services.km.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.verint.services.km.dao.ISETDAO;
import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.AppException;

@Path("/iset")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Service
public class ISETService extends BaseService{
	private final Logger LOGGER = LoggerFactory.getLogger(ISETService.class);
	org.jboss.logging.Logger LOG = org.jboss.logging.Logger.getLogger(ISETService.class);
	
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
	public String getISETResponse(@QueryParam("refName") String refName,
			@QueryParam("objType") String objType,
			@QueryParam("objID") String objID) {
		LOGGER.info("Entering getISETResponse()");
		String migratableReferenceId = "";
		
		try {
			migratableReferenceId = isetDAO.getISETResponse(refName, objType, objID);
		}catch (AppException ae) {
			LOGGER.error("AppException in getISETResponse()", ae);
			throw ae;
		} catch (Throwable t) {
			LOGGER.error("Unexpected exception in getISETResponse()", t);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
		}
		return migratableReferenceId;
	}
	
	

}
