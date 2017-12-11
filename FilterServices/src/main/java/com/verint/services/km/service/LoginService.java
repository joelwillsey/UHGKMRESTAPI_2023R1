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

import com.verint.services.km.dao.LoginDAO;
import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.LoginRequest;
import com.verint.services.km.model.LoginResponse;


/**
 * @author jmiller
 *
 */
@Path("/login")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Service
public class LoginService {
	private final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

	@Autowired
	private LoginDAO loginDAO;

	/**
	 * 
	 */
	public LoginService() {
		super();
		LOGGER.info("Entering LoginService()");
		LOGGER.info("Exiting LoginService()");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

	/**
	 * 
	 * @param loginRequest
	 * @return
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public LoginResponse login(LoginRequest loginRequest) {
		LOGGER.info("Entering login()");
		LOGGER.debug("LoginRequest: " + loginRequest);
		LoginResponse loginResponse = null;

		try {
			// Create the login request
			loginResponse = loginDAO.login(loginRequest);
		} catch (Throwable t) {
			LOGGER.error("Unexpected exception in login()", t);
			throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
		}
		LOGGER.debug("LoginResponse: " + loginResponse);
		LOGGER.info("Exiting login()");
		return loginResponse;
	}
}