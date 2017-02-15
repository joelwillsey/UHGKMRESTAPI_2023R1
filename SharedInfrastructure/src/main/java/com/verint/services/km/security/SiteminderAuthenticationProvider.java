package com.verint.services.km.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.verint.services.km.dao.LoginV2DAO;
import com.verint.services.km.model.LoginV2Request;
import com.verint.services.km.model.LoginV2Response;

/**
 * @author jmiller
 *
 */
@Component
public class SiteminderAuthenticationProvider implements AuthenticationProvider {
	private final Logger LOGGER = LoggerFactory.getLogger(SiteminderAuthenticationProvider.class);

	@Autowired
	private LoginV2DAO loginV2DAO;

	/**
	 * 
	 */
	public SiteminderAuthenticationProvider() {
		super();
		LOGGER.info("Entering ()");
		LOGGER.info("Exiting SiteminderAuthenticationProvider()");
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.authentication.AuthenticationProvider#
	 * authenticate(org.springframework.security.core.Authentication)
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		LOGGER.info("Entering authenticate()");
		LOGGER.debug("authenticate: " + authentication);
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();


		LOGGER.debug("Username: " + username);
		LOGGER.debug("Password: " + password);
		
		
/*	final LoginV2Request request = new LoginV2Request();
		request.setUsername(username);
		request.setPassword(password);
		request.setFirstName("");
		request.setLastName("");
		request.setVemGroups("");
		
		LoginV2Response response = null;
		try {			
			// Try to login
			response = loginV2DAO.login(request);
		} catch (Throwable t) {
			LOGGER.error("Exception during loginV2DAO()", t);
		}

		// Check for a response
		if (response == null) {
			throw new BadCredentialsException("Username/password not found");
		}*/


		// Check for a response
		if (username == null || password == null) {
			throw new BadCredentialsException("Username/password not found");
		}

		// Now grant the proper access
		final SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_REST");
		final List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(authority);

		LOGGER.info("Exiting authenticate()");
		return new UsernamePasswordAuthenticationToken(username, password, authorities);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.authentication.AuthenticationProvider#
	 * supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}
}