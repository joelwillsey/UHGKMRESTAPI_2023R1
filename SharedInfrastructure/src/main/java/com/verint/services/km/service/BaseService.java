/**
 * 
 */
package com.verint.services.km.service;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 * @author jmiller 
 *
 */
public class BaseService {
	private final Logger LOGGER = LoggerFactory.getLogger(BaseService.class);
	protected String[] credentials;

	/**
	 * 
	 */
	public BaseService() {
		super();
		LOGGER.debug("Entering BaseService()");
		LOGGER.debug("Exiting BaseService()");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

	/**
	 * 
	 * @param httpRequest
	 * @return
	 */
	protected String[] getAuthenticatinCredentials(HttpServletRequest httpRequest) {
		LOGGER.debug("Entering getAuthenticatinCredentials()");
		
		// Get the authentication information
		credentials = new String[2];
		credentials[0] = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		// Check if we may have password
		if (auth instanceof UsernamePasswordAuthenticationToken) {
			credentials[1] = (String)SecurityContextHolder.getContext().getAuthentication().getCredentials();
			if (credentials[1] == null ) {
				credentials[1] = "";
			}
		} else {
			credentials[1] = "";
		}

		//
		// NOTE: These attributes are setup by the Spring security framework filter
		//  
		// OLD WAY
		//
		//credentials[0] = (String)httpRequest.getAttribute("kmuser");
		//credentials[1] = (String)httpRequest.getAttribute("kmpassword");
		//
		if (credentials != null && credentials[0] != null && "john.miller@verint.com".equals(credentials[0])) {
			credentials[0] = "kmagent";
		}
		LOGGER.debug("Username: " + credentials[0]);
		LOGGER.debug("Password: " + credentials[1]);

		LOGGER.debug("Exiting getAuthenticatinCredentials()");
		return credentials;
	}
}