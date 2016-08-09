/**
 * 
 */
package com.verint.services.km.security;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.NullRememberMeServices;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;


/**
 * @author jmiller
 *
 */
public class KmSAMLAuthenticationFilter extends OncePerRequestFilter {
	private final Logger LOGGER = LoggerFactory.getLogger(KmSAMLAuthenticationFilter.class);
	private AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = new WebAuthenticationDetailsSource();
	private RememberMeServices rememberMeServices = new NullRememberMeServices();
	private AuthenticationEntryPoint authenticationEntryPoint;
	private AuthenticationManager authenticationManager;
	private boolean ignoreFailure = false;
	private String credentialsCharset = "UTF-8";

	/**
	 * 
	 */
	public KmSAMLAuthenticationFilter() {
		super();
		LOGGER.info("Entering KmSAMLAuthenticationFilter()");
		LOGGER.info("Exiting KmSAMLAuthenticationFilter()");
	}

	/**
	 * 
	 * @param authenticationManager
	 */
	public KmSAMLAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		LOGGER.info("Entering KmSAMLAuthenticationFilter()");
		this.authenticationManager = authenticationManager;
		ignoreFailure = true;
		LOGGER.info("Exiting KmSAMLAuthenticationFilter()");
	}

	/**
	 * 
	 * @param authenticationManager
	 * @param authenticationEntryPoint
	 */
	public KmSAMLAuthenticationFilter(AuthenticationManager authenticationManager,
			AuthenticationEntryPoint authenticationEntryPoint) {
		super();
		LOGGER.info("Entering KmSAMLAuthenticationFilter()");
		this.authenticationEntryPoint = authenticationEntryPoint;
		this.authenticationManager = authenticationManager;
		LOGGER.info("Exiting KmSAMLAuthenticationFilter()");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		LOGGER.info("Entering doFilterInternal()");
		final boolean debug = logger.isDebugEnabled();
		String username = null;
		String password = null;
		Date expiration = null;
				
		// NOTE: Back-door way in
		if (debug) {
			username = request.getParameter("username");
			password = request.getParameter("password");
		}

		// Check if on url
		if (username != null && username.length() > 0 &&
			password != null && password.length() > 0) {
			// We have valid parameters
			LOGGER.info("Valid username/password");
		} else {
			final Cookie[]cookies = request.getCookies();
			String authToken = null;
			for (int x = 0; x < cookies.length; x++) {
				if ("AuthToken".equals(cookies[x].getName())) {
					authToken = cookies[x].getValue();
					LOGGER.debug("AuthToken: " + authToken);
				}
			}
			if (authToken == null) {
				chain.doFilter(request, response);
				return;
			}

			final String[] tokens = extractAndDecodeHeader(authToken, request);
			assert tokens.length == 2;
			username = tokens[0];
			try {
				DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy");
				expiration = df.parse(tokens[1]);
			} catch (ParseException pe) {
				LOGGER.error("ParseException", pe);
			}

			LOGGER.debug("SAML Authentication Authorization header found for user '" + username + "'");
		}

		try {
			// Check if authentication is required
			if (authenticationIsRequired(username)) {
				KmSAMLAuthenticationToken authRequest = new KmSAMLAuthenticationToken(username);
				authRequest.setExpiration(expiration);
				authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
				Authentication authResult = authenticationManager
						.authenticate(authRequest);
				LOGGER.debug("Authentication success: " + authResult);
				request.setAttribute("kmuser", username);
				request.setAttribute("kmpassword", password);
				SecurityContextHolder.getContext().setAuthentication(authResult);
				rememberMeServices.loginSuccess(request, response, authResult);
				onSuccessfulAuthentication(request, response, authResult);
			}
		}
		catch (AuthenticationException failed) {
			SecurityContextHolder.clearContext();
			LOGGER.debug("Authentication request for failed: " + failed);
			rememberMeServices.loginFail(request, response);
			onUnsuccessfulAuthentication(request, response, failed);
			if (ignoreFailure) {
				chain.doFilter(request, response);
			}
			else {
				authenticationEntryPoint.commence(request, response, failed);
			}
			return;
		}
		LOGGER.info("Exiting doFilterInternal()");
		chain.doFilter(request, response);
	}

	/**
	 * Decodes the cookie into a username and password.
	 *
	 * @throws BadCredentialsException if the Basic header is not present or is not valid
	 * Base64
	 */
	private String[] extractAndDecodeHeader(String cookie, HttpServletRequest request)
			throws IOException {
		LOGGER.info("Entering extractAndDecodeHeader()");
		byte[] base64Token = cookie.getBytes("UTF-8");
		byte[] decoded;
		try {
			decoded = Base64.decode(base64Token);
		}
		catch (IllegalArgumentException e) {
			throw new BadCredentialsException(
					"Failed to decode SAML authentication token");
		}
		
		String token = new String(decoded, getCredentialsCharset(request));
		LOGGER.debug("Token: " + token);
		int delim = token.indexOf(":");
		if (delim == -1) {
			throw new BadCredentialsException("Invalid basic authentication token");
		}
		LOGGER.info("Exiting extractAndDecodeHeader()");
		return new String[] { token.substring(0, delim), token.substring(delim + 1) };
	}

	/**
	 * 
	 * @param username
	 * @return
	 */
	private boolean authenticationIsRequired(String username) {
		// Only reauthenticate if username doesn't match SecurityContextHolder and user
		// isn't authenticated
		// (see SEC-53)
		Authentication existingAuth = SecurityContextHolder.getContext()
				.getAuthentication();

		if (existingAuth == null || !existingAuth.isAuthenticated()) {
			return true;
		}

		// Limit username comparison to providers which use usernames (ie
		// UsernamePasswordAuthenticationToken)
		// (see SEC-348)

		if (existingAuth instanceof UsernamePasswordAuthenticationToken
				&& !existingAuth.getName().equals(username)) {
			return true;
		}

		// Handle unusual condition where an AnonymousAuthenticationToken is already
		// present
		// This shouldn't happen very often, as BasicProcessingFitler is meant to be
		// earlier in the filter
		// chain than AnonymousAuthenticationFilter. Nevertheless, presence of both an
		// AnonymousAuthenticationToken
		// together with a BASIC authentication request header should indicate
		// reauthentication using the
		// BASIC protocol is desirable. This behaviour is also consistent with that
		// provided by form and digest,
		// both of which force re-authentication if the respective header is detected (and
		// in doing so replace
		// any existing AnonymousAuthenticationToken). See SEC-610.
		if (existingAuth instanceof AnonymousAuthenticationToken) {
			return true;
		}

		return false;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param authResult
	 * @throws IOException
	 */
	protected void onSuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, Authentication authResult) throws IOException {
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param failed
	 * @throws IOException
	 */
	protected void onUnsuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException failed)
			throws IOException {
	}
	
	/**
	 * 
	 * @return
	 */
	protected AuthenticationEntryPoint getAuthenticationEntryPoint() {
		return authenticationEntryPoint;
	}

	/**
	 * 
	 * @return
	 */
	protected AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	/**
	 * 
	 * @param aep
	 */
	public void setAuthenticationEntryPoint(AuthenticationEntryPoint aep) {
		this.authenticationEntryPoint = aep;
	}

	/**
	 * 
	 * @param am
	 */
	public void setAuthenticationManager(AuthenticationManager am) {
		this.authenticationManager = am;
	}

	/**
	 * 
	 * @return
	 */
	protected boolean isIgnoreFailure() {
		return ignoreFailure;
	}

	/**
	 * 
	 * @param authenticationDetailsSource
	 */
	public void setAuthenticationDetailsSource(
			AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource) {
		Assert.notNull(authenticationDetailsSource,
				"AuthenticationDetailsSource required");
		this.authenticationDetailsSource = authenticationDetailsSource;
	}

	/**
	 * 
	 * @param rememberMeServices
	 */
	public void setRememberMeServices(RememberMeServices rememberMeServices) {
		Assert.notNull(rememberMeServices, "rememberMeServices cannot be null");
		this.rememberMeServices = rememberMeServices;
	}

	/**
	 * 
	 * @param credentialsCharset
	 */
	public void setCredentialsCharset(String credentialsCharset) {
		Assert.hasText(credentialsCharset, "credentialsCharset cannot be null or empty");
		this.credentialsCharset = credentialsCharset;
	}

	/**
	 * 
	 * @param httpRequest
	 * @return
	 */
	protected String getCredentialsCharset(HttpServletRequest httpRequest) {
		return credentialsCharset;
	}
}