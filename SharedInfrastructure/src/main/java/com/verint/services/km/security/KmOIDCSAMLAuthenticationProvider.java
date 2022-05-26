package com.verint.services.km.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;


/**
 * @author pseifert
 *
 */
@Component
public class KmOIDCSAMLAuthenticationProvider implements AuthenticationProvider {
	private final Logger LOGGER = LoggerFactory.getLogger(KmOIDCSAMLAuthenticationProvider.class);


	/**
	 * 
	 */
	public KmOIDCSAMLAuthenticationProvider() {
		super();
		LOGGER.info("Entering KmOIDCSAMLAuthenticationProvider()");
		LOGGER.info("Exiting KmOIDCSAMLAuthenticationProvider()");
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
		String password = (String) authentication.getCredentials();
		
		LOGGER.debug("username (Principal)=" + username);
		LOGGER.debug("password=" + password);
			
		if (isNullOrEmpty(username) ||isNullOrEmpty(password)) {
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
	 
    public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.trim().isEmpty())
            return false;
        return true;
    }
}
