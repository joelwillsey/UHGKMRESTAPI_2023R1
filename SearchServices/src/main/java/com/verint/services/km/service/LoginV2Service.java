/**
 * 
 */
package com.verint.services.km.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verint.services.km.dao.LoginV2DAO;
import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.LoginV2Request;
import com.verint.services.km.model.LoginV2Response;


/**
 * @author jmiller
 *
 */
@Path("/login_v2")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Service
public class LoginV2Service {
	private final Logger LOGGER = LoggerFactory.getLogger(LoginV2Service.class);

	@Autowired
	private LoginV2DAO loginV2DAO;

	/**
	 * 
	 */
	public LoginV2Service() {
		super();
		LOGGER.info("Entering LoginV2Service()");
		LOGGER.info("Exiting LoginV2Service()");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

	/**
	 * 
	 * @param loginV2Request
	 * @return
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public LoginV2Response login(LoginV2Request loginV2Request) {
		LOGGER.info("Entering login_v2()");
		LOGGER.debug("LoginV2Request: " + loginV2Request);
		LoginV2Response loginV2Response = null;

		try {
			// Create the login request
			loginV2Response = loginV2DAO.login(loginV2Request);
		} catch (Throwable t) {
			LOGGER.error("Unexpected exception in login_v2()", t);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
		}
		LOGGER.debug("LoginV2Response: " + loginV2Response);
		LOGGER.info("Exiting login_v2()");
		return loginV2Response;
	}
}