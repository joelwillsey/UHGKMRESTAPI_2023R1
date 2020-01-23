package com.verint.services.km.security;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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
import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.LoginV2Request;
import com.verint.services.km.model.LoginV2Response;


/**
 * @author pseifert
 *
 */
@Component
public class KmPingOIDCAuthenticationProvider implements AuthenticationProvider {
	private final Logger LOGGER = LoggerFactory.getLogger(KmPingOIDCAuthenticationProvider.class);

	@Autowired
	private LoginV2DAO loginV2DAO;

	/**
	 * 
	 */
	public KmPingOIDCAuthenticationProvider() {
		super();
		LOGGER.info("Entering KmPingOIDCAuthenticationProvider()");
		LOGGER.info("Exiting KmPingOIDCAuthenticationProvider()");
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
		String username = (String) authentication.getPrincipal();
		String dataString = (String) authentication.getCredentials();
		String password = null;
		String firstName = "";
		String lastName = "";
		String vemGroups = "";
		
		LOGGER.debug("username (Principal)=" + username);
		LOGGER.debug("dataString=" + dataString);
		
		String[] credentials = dataString.split(Pattern.quote("|"), -1);

		LOGGER.debug("credentials.length=" + credentials.length);		
		for(int i=0; i < credentials.length; i++ ) {
			LOGGER.debug("credentials[" + i + "]="+ credentials[i]);
		}
		
		if(credentials.length == 4) {
			password = credentials[0];
			firstName = credentials[1];
			lastName = credentials[2];
			vemGroups = credentials[3];
		} 
			
		if (username == null || password == null) {
			throw new BadCredentialsException("Username/password not found");
		}
		
		// Check data
		if (firstName == "" || lastName == "") {
			LOGGER.debug("No Login info found" + authentication.toString());
			throw new BadCredentialsException("firstName/lastName not found");
		} else if (vemGroups == "") {
			throw new BadCredentialsException("No Knowledge base (Active Directory groups) found");
		}
		
		final LoginV2Request request = new LoginV2Request();
		
		request.setUsername(username);
		request.setPassword(password);
		request.setFirstName(firstName);
		request.setLastName(lastName);
		request.setVemGroups(vemGroups);		
		LOGGER.debug("LoginV2Request request: " + request.toString());
		
		LoginV2Response response = null;
		try {			
			// Try to login
			response = login(request);
		} catch (Throwable t) {
			LOGGER.error("Exception during loginV2DAO()", t);
		}
				
		// Check for a response
		if (response == null & response.getLoginResult() != null) {
			LOGGER.error("Bad response (null) from loginV2DAO()");
			throw new BadCredentialsException("LoginV2 request failed, null response");
		}

		LOGGER.debug("LoginV2Request response: " + response.toString());
		
		if (response.getLoginResult().compareTo(new BigInteger(String.valueOf(1))) != 0) {
			LOGGER.error("LoginV2 request failed. Bad Login result. LoginResult=" + response.getLoginResult().toString() + " message: " + response.getMessage());
			throw new BadCredentialsException("LoginV2 request failed. Bad Login result. LoginResult=" + response.getLoginResult().toString() + " message: " + response.getMessage());
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
