/**
 * 
 */
package com.verint.services.km.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.NullRememberMeServices;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.GenericData;


import org.jose4j.json.internal.json_simple.JSONObject;
import org.jose4j.json.internal.json_simple.parser.ParseException;


import com.verint.services.km.security.KmPingOIDCOauthUtils;



/**
 * @author jmiller
 * 
 */
public class KmPingOIDCAuthenticationFilter extends OncePerRequestFilter {
	private final Logger LOGGER = LoggerFactory.getLogger(KmPingOIDCAuthenticationFilter.class);
	private AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = new WebAuthenticationDetailsSource();
	private RememberMeServices rememberMeServices = new NullRememberMeServices();
	private AuthenticationEntryPoint authenticationEntryPoint;
	private AuthenticationManager authenticationManager;
	private boolean ignoreFailure = false;
	private String credentialsCharset = "UTF-8";
	private String authenticateURL;
	private String responseType;
	private String clientId;
	private String scope;
	private String acrValues;
	private String clientSecret;
	private String jsonWebKeysURL;
	private String tokenURL;
	private String kmGroups;
	private String redirectURI;
	
	private static final String USERNAME = "USERNAME",
								FIRST_NAME = "FIRST_NAME", 
								LAST_NAME = "LAST_NAME",
								GROUPS = "GROUPS",
								SSO_FIRST_NAME = "SSO_FIRST_NAME",
								SSO_LAST_NAME = "SSO_LAST_NAME",
								KB_NAMES = "KB_NAMES";
	private static final String HEADER_CODE = "code";

	private static final NetHttpTransport httpTransport = new NetHttpTransport();
	

	
	/**
	 * 
	 */
	public KmPingOIDCAuthenticationFilter() {
		super();
		LOGGER.info("Entering kmPingOIDCAuthenticationFilter()");
		LOGGER.info("Exiting kmPingOIDCAuthenticationFilter()");
	}

	/**
	 * 
	 * @param authenticationManager
	 */
	public KmPingOIDCAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		LOGGER.info("Entering kmPingOIDCAuthenticationFilter()");
		this.authenticationManager = authenticationManager;
		ignoreFailure = true;
		LOGGER.info("Exiting kmPingOIDCAuthenticationFilter()");
	}

	/**
	 * 
	 * @param authenticationManager
	 * @param authenticationEntryPoint
	 */
	public KmPingOIDCAuthenticationFilter(AuthenticationManager authenticationManager,
			AuthenticationEntryPoint authenticationEntryPoint) {
		super();
		LOGGER.info("Entering kmPingOIDCAuthenticationFilter()");
		this.authenticationEntryPoint = authenticationEntryPoint;
		this.authenticationManager = authenticationManager;
		LOGGER.info("2authenticateURL::: " + authenticateURL);
		LOGGER.info("Exiting kmPingOIDCAuthenticationFilter()");
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
		String ssoUserName = null;
		String dummyPassword = null;
		String authCode = null; 

		String username = null;
		String password = null;
		Date expiration = null;
				
		//Properties needed to be grabbed
		
		String requestURL = request.getRequestURL().toString();
		LOGGER.debug("requestURL:::"+requestURL);
		
		//query string has the process name in it like "gtxInitialProcess=AddKnowPageSetServices.Implementation.PageSetV1"
		String queryStr = request.getQueryString();
		LOGGER.debug("queryStr:::"+queryStr);
		
		String requestingURL;
		
		if(queryStr != null && queryStr.length() > 0) {			
//			queryStr = queryStr.replaceAll("%3F", "?");
//			queryStr = queryStr.replaceAll("%3D", "=");
			requestingURL = requestURL + "%3F" + queryStr;
		} else {
			requestingURL = requestURL;
		}
		
		LOGGER.debug("Requesting Resource:::" + requestingURL);
		
		authCode = request.getParameter(HEADER_CODE);
		LOGGER.debug("authCode from request parameter:::"+authCode);
		
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
			String authToken = null;
			//Well this sucks the request.getCookies() is not bring back the whole authtoken because there is a space in the cookie
			//we will get them via headers
			final Cookie[]cookies = request.getCookies();
			
			if (cookies != null) {
				String rawCookie = request.getHeader("Cookie");
				String[] rawCookieParams = rawCookie.split(";");
				for(String rawCookieNameAndValue :rawCookieParams)
				{
				  String[] rawCookieNameAndValuePair = rawCookieNameAndValue.split("=");
				  if(rawCookieNameAndValuePair.length== 2) {
					  LOGGER.debug("Cookie Name: " + rawCookieNameAndValuePair[0] + "=" + rawCookieNameAndValuePair[1]);
					  if ("AuthToken".equals(rawCookieNameAndValuePair[0].trim())) {
						  authToken = rawCookieNameAndValuePair[1];
						  LOGGER.debug("AuthToken: " + authToken);
					  }
					  
				  }
				}
			}
			
			if (authToken != null) {
				final String[] tokens = extractAndDecodeHeader(authToken, request);
				assert tokens.length == 2;
				ssoUserName = tokens[0];
				dummyPassword = tokens[1];
				Authentication authenticationToken = setAuthentication(ssoUserName, dummyPassword);
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				rememberMeServices.loginSuccess(request, response, authenticationToken);
				LOGGER.debug("Ping Authentication Authorization cookie found for user '" + ssoUserName + "'");
				chain.doFilter(request, response);
				return;
			}
		}
		

		String header = request.getHeader("x-km-authorization");
		if (header != null && header.startsWith("Basic ")) {
			try {
				String[] tokens = extractAndDecodeHeader(header, request);
				assert tokens.length == 2;
				ssoUserName = tokens[0];
				dummyPassword = tokens[1];
				Authentication authenticationToken = setAuthentication(ssoUserName, dummyPassword);
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				rememberMeServices.loginSuccess(request, response, authenticationToken);
				
				LOGGER.debug("Basic Authentication Authorization header found for user '" + ssoUserName + "', processing filters");
				chain.doFilter(request, response);
				return;
			} catch (Exception exc){
				LOGGER.debug("Found Basic Authentication Authorization header but unable to decode: " + exc.toString());
			}
		} else {
			LOGGER.debug("Basic Authentication Authorization header missing");
		}
	


		try {
			// Check if authentication is required
			if (authenticationIsRequired(ssoUserName)) {			
				if (authCode==null) {
					// need to redirect to Ping
					LOGGER.debug("############################ Redirect to authenticateURL ############################");
					
					String savedUrl = createRedirectURI(request);
					String redirect_uri;
					
					if(savedUrl.length() > 0) {
						redirect_uri =  authenticateURL + "?&response_type=" + responseType + "&client_id=" + clientId 
								+ "&redirect_uri="+ savedUrl + "&acr_values=" + acrValues + "&scope=" + scope;
					} else {	
						redirect_uri =  authenticateURL + "?&response_type=" + responseType + "&client_id=" + clientId 
								+ "&redirect_uri="+ requestingURL + "&acr_values=" + acrValues + "&scope=" + scope;
						LOGGER.error("Cookie savedUrl was missing redirecting to Requesting Resource");						
					}
					
					LOGGER.debug("Responding to a Redirect to authenticateURL request with URL: " + redirect_uri);
					
					String csfrToken = createCSRFToken();					
					response.setHeader("Set-Cookie","X-CSRF-TOKEN="+ csfrToken +"; path=/" );
					response.setHeader("X-CSRF-TOKEN", csfrToken);
					LOGGER.info("CSFR Token: " + csfrToken );
					
					if (!response.containsHeader("Access-Control-Allow-Origin")) {
					//if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
						LOGGER.debug("Adding headers for CORS");
						response.setHeader("Access-Control-Allow-Origin", "*");
//						LOGGER.debug("Setting header 'Access-Control-Allow-Origin': " + origin);
					    response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
					    response.setHeader("Access-Control-Allow-Headers", "Access-Control-Expose-Headers,"+"Authorization, content-type,"+
					                  "access-control-request-headers,access-control-request-method,accept,origin,authorization,x-requested-with,responseType,observe");
					    response.setHeader("Access-Control-Max-Age", "3600");
					    response.setHeader("Access-Control-Allow-Credentials", "true");
					    response.setHeader("Access-Control-Expose-Headers", "Authorization");
					    response.addHeader("Access-Control-Expose-Headers", "responseType");
					    response.addHeader("Access-Control-Expose-Headers", "observe");
					    LOGGER.debug("Request Method: "+request.getMethod());
				       if ( request.getMethod().equals("OPTIONS") ) {
				            response.setStatus(HttpServletResponse.SC_OK);
				            LOGGER.debug("Request Method: "+request.getMethod());
				        }					      
					}
					response.sendRedirect(redirect_uri);
					LOGGER.info("Exiting doFilterInternal()");
					return;
				} else {
					LOGGER.info("authCode: " + authCode + " was present, wrong filter (This is normal)");
				}								
			} else {
				LOGGER.info("authenticationIsRequired = false");
			}
		}
		catch (AuthenticationException failed) {
			SecurityContextHolder.clearContext();
			LOGGER.error("Authentication request for failed: " + failed);
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
//		catch (ParseException e) {
//			LOGGER.error("ParseException: " + e.toString());
//		}
		LOGGER.info("Exiting doFilterInternal()");
		chain.doFilter(request, response);
	}

	/**
	 * Decodes the header into a username and password.
	 *
	 * @throws BadCredentialsException if the Basic header is not present or is not valid
	 * Base64
	 */
	private String[] extractAndDecodeHeader(String header, HttpServletRequest request)
			throws IOException {

		if (header == null || header.length() < 6){
			throw new BadCredentialsException("Invalid basic authentication token. Header:" + header);
		}
		
		byte[] base64Token = header.substring(6).getBytes("UTF-8");
		byte[] decoded;
		try {
			decoded = Base64.decode(base64Token);
		}
		catch (IllegalArgumentException e) {
			throw new BadCredentialsException(
					"Failed to decode basic authentication token");
		}

		String token = new String(decoded, getCredentialsCharset(request));
		int delim = token.indexOf(":");
		if (delim == -1) {
			throw new BadCredentialsException("Invalid basic authentication token");
		}
		return new String[] { token.substring(0, delim), token.substring(delim + 1) };
	}

	/**
	 * 
	 * @param username
	 * @return
	 */
	private boolean authenticationIsRequired(String username) {
		// Only re-authenticate if username doesn't match SecurityContextHolder and user
		// isn't authenticated
		// (see SEC-53)
		LOGGER.info("Entering authenticationIsRequired");
		
		Authentication existingAuth = SecurityContextHolder.getContext()
				.getAuthentication();
				
		if (existingAuth != null) {
			LOGGER.info("existingAuth=" + existingAuth.toString());
		}
		
		if (existingAuth == null || !existingAuth.isAuthenticated()) {
			return true;
		}

		// Limit username comparison to providers which use usernames (ie
		// UsernamePasswordAuthenticationToken)
		// (see SEC-348)

		if (existingAuth instanceof UsernamePasswordAuthenticationToken
				//&& !existingAuth.getName().equals(username)) {
			&& !existingAuth.getPrincipal().equals(username)) {
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
	
	public Authentication setAuthentication(String username, String password) {		
		// Now grant the proper access
		final SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_REST");
		final List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(authority);
		return new UsernamePasswordAuthenticationToken(username, password, authorities);
	}
	
	private String createCSRFToken() {
		int len = 36;
		final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		SecureRandom rnd = new SecureRandom();
		
	    StringBuilder sb = new StringBuilder( len );
	    for( int i = 0; i < len; i++ ) 
	       sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	    return sb.toString();
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
	
	/**
	 * 
	 * @return
	 */
	public String getAuthenticateURL() {
		return this.authenticateURL;
	}
	
	/**
	 * 
	 * @param authenticateURL
	 */
	public void setAuthenticateURL(String authenticateURL) {
		this.authenticateURL = authenticateURL;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getResponseType() {
		return this.responseType;
	}
	
	/**
	 * 
	 * @param responseType
	 */
	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getClientId() {
		return this.clientId;
	}
	
	/**
	 * 
	 * @param clientId
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getScope() {
		return this.scope;
	}
	
	/**
	 * 
	 * @param scope
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getAcrValues() {
		return this.acrValues;
	}
	
	/**
	 * 
	 * @param acrValues
	 */
	public void setAcrValues(String acrValues) {
		this.acrValues = acrValues;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getClientSecret() {
		return this.clientSecret;
	}
	
	/**
	 * 
	 * @param clientSecret
	 */
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getJsonWebKeysURL() {
		return this.jsonWebKeysURL;
	}
	
	/**
	 * 
	 * @param jsonWebKeysURL
	 */
	public void setJsonWebKeysURL(String jsonWebKeysURL) {
		this.jsonWebKeysURL = jsonWebKeysURL;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getKmGroups() {
		return this.kmGroups;
	}
	
	/**
	 * 
	 * @param kmGroups
	 */
	public void setKmGroups(String kmGroups) {
		this.kmGroups = kmGroups;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getTokenURL() {
		return this.tokenURL;
	}
	
	/**
	 * 
	 * @param tokenURL
	 */
	public void setTokenURL(String tokenURL) {
		this.tokenURL = tokenURL;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getRedirectURIL() {
		return this.redirectURI;
	}
	
	/**
	 * 
	 * @param .redirectURI
	 */
	public void setRedirectURI(String redirectURI) {
		this.redirectURI = redirectURI;
	}
	
	private String createRedirectUri(HttpServletRequest request) {
		String savedUrl = "";
		
		LOGGER.info("Entering createRedirectUri()");		
		// Go through the cookies to find original URL
		final Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (int x = 0; x < cookies.length; x++) {
				LOGGER.info("Cookie: " + cookies[x].getName() + "=" + cookies[x].getValue());
				if ("savedurl".equals(cookies[x].getName())) {
					savedUrl = "/" + cookies[x].getValue();
//					savedUrl = savedUrl.replaceAll("?","%3F");
//					savedUrl = savedUrl.replaceAll("=", "%3D");
					LOGGER.debug("Found Cookie savedUrl: " + savedUrl);
					
					String referer = request.getHeader("referer");
					LOGGER.debug("Found Request Header: Referer=" + referer);
					if(!savedUrl.contains("http")) {
						if(referer.contains("verintkm")) {
							savedUrl = "/verintkm" + redirectURI;
						} else if(referer.contains("contentservices")) {
							savedUrl = "/contentservices" + redirectURI;
						} else if(referer.contains("searchservices")) {
							savedUrl = "/searchservices" + redirectURI;
						} else if(referer.contains("filterservices")) {
							savedUrl = "/filterservices" + redirectURI;
						}
						
						//get the host protocol://name:port
						StringBuffer url = request.getRequestURL();
						String uri = request.getRequestURI();
						String host = url.substring(0, url.indexOf(uri)); //result
						
						if (!host.startsWith("https")) {
							//force it to be https
							LOGGER.debug("Forced over to https original host: " + host);
							host = host.replaceFirst("http", "https");
						}
						
						savedUrl = host  + savedUrl;
						
					}
				}
			}
		}

		LOGGER.debug("Modified from referer header - savedUrl: " + savedUrl);
		LOGGER.info("Exiting createRedirectUri()");
		return savedUrl;
	}
	
	private String createRedirectURI(HttpServletRequest request) {
		
		LOGGER.info("Entering createRedirectURI()");	
		
		String strRedirectURI = "";
		String uRl = request.getRequestURL().toString();
		
		if(uRl.contains("verintkm")) {
			strRedirectURI = uRl.substring(0, uRl.indexOf("verintkm") + "verintkm".length()) + redirectURI;
		} else if(uRl.contains("contentservices")) {
			strRedirectURI = uRl.substring(0, uRl.indexOf("contentservices") + "contentservices".length()) + redirectURI;
		} else if(uRl.contains("searchservices")) {
			strRedirectURI = uRl.substring(0, uRl.indexOf("searchservices") + "searchservices".length()) + redirectURI;
		} else if(uRl.contains("filterservices")) {
			strRedirectURI = uRl.substring(0, uRl.indexOf("filterservices") + "filterservices".length()) + redirectURI;
		}
		
		if (!strRedirectURI.startsWith("https")) {
			//force it to be https  For some stupid reason the request.getRequestURL() always returns http not https
			LOGGER.debug("Forced over to https original host: " + strRedirectURI);
			strRedirectURI = strRedirectURI.replaceFirst("http", "https");
		}
		
		LOGGER.debug("Modified from request URL - savedUrl: " + strRedirectURI);
		LOGGER.info("Exiting createRedirectURI()");
		return strRedirectURI;
	}
}