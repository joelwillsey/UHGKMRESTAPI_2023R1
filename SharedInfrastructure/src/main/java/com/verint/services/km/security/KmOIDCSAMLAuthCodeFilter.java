package com.verint.services.km.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.rmi.RemoteException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.AxisFault;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.jose4j.json.internal.json_simple.parser.JSONParser;
import org.jose4j.json.internal.json_simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.NullRememberMeServices;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.api.client.http.HttpResponseException;
import com.verint.services.km.model.rest.VerintOIDCToken;
import com.verint.services.km.util.ConfigInfo;
import com.verint.services.km.util.RestUtil;
import com.verint.services.km.util.VerintOIDCTokenUtil;


public class KmOIDCSAMLAuthCodeFilter  extends OncePerRequestFilter {
	private final Logger LOGGER = LoggerFactory.getLogger(KmOIDCSAMLAuthCodeFilter.class);
	private AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = new WebAuthenticationDetailsSource();
	private RememberMeServices rememberMeServices = new NullRememberMeServices();
	private AuthenticationEntryPoint authenticationEntryPoint;
	private AuthenticationManager authenticationManager;
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	private AuthenticationFailureHandler authenticationFailureHandler;
	private boolean ignoreFailure = false;

	private String redirectURI;
	private Boolean redirectURIForceHTTPS = true;
	private String redirectURIUnAuthorized;
	private String redirectURIGeneralError;
	private String oidcTokenServiceURL;
	private String oidcTokenServiceGrantType;
	private String oidcTokenServiceClientId;
	private int authTokenExpirationDuration;
	private static final String USERNAME = "USERNAME";
	private static final String X_KM_AUTHORIZATION = "x-km-authorization";
	private static final String AUTH_TOKEN_COOKIE_NAME = "AuthToken";
	private static final String AGENT_INFO_COOKIE_NAME = "AgentInfo";
	public static final String ACCESS_TOKEN = "access_token";
	public static final String TOKEN_TYPE = "token_type";
	public static final String EXPIRES_IN = "expires_in";
	public static final String ID_TOKEN = "id_token";
	private static final String VERINT_AUTH_TOKEN_COOKIE_NAME = "verintAuthToken";
	private final ConfigInfo kmConfiguration = new ConfigInfo();
	
	/**
	 * The authorization code query parameter.
	 */
	private static final String PARAMETER_CODE = "code";
	private static final String PARAMETER_ERROR = "error";
	
	/**
	 * Default name of path suffix which will invoke this filter.
	 */
	public static final String FILTER_URL = "/km/ping/authcode";

	/**
	 * Url this filter should get activated on.
	 */
	protected String filterProcessesUrl = FILTER_URL;
	
	/**
	 * 
	 */
	public KmOIDCSAMLAuthCodeFilter() {
		super();
		LOGGER.info("Entering KmOIDCSAMLAuthCodeFilter()");
		LOGGER.info("Exiting KmOIDCSAMLAuthCodeFilter()");
	}

	/**
	 * 
	 * @param authenticationManager
	 */
	public KmOIDCSAMLAuthCodeFilter(AuthenticationManager authenticationManager) {
		super();
		LOGGER.info("Entering KmPingOIDCAuthenticationFilter()");
		this.authenticationManager = authenticationManager;
		ignoreFailure = true;
		LOGGER.info("Exiting KmPingOIDCAuthenticationFilter()");
	}

	/**
	 * 
	 * @param authenticationManager
	 * @param authenticationEntryPoint
	 */
	public KmOIDCSAMLAuthCodeFilter(AuthenticationManager authenticationManager,
			AuthenticationEntryPoint authenticationEntryPoint) {
		super();
		LOGGER.info("Entering KmPingOIDCAuthenticationFilter()");
		this.authenticationEntryPoint = authenticationEntryPoint;
		this.authenticationManager = authenticationManager;
		//LOGGER.info("2authenticateURL::: " + authenticateURL);
		LOGGER.info("Exiting KmPingOIDCAuthenticationFilter()");
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		LOGGER.info("Entering doFilterInternal()");
		FilterInvocation fi = new FilterInvocation(request, response, chain);

		if (!processFilter(fi.getRequest())) {
			chain.doFilter(request, response);
			return;
		}

		commence(fi.getRequest(), fi.getResponse(), null);
		LOGGER.info("Exiting doFilterInternal)");
		chain.doFilter(request, response);
	}
	
	protected boolean processFilter(HttpServletRequest request) {
		boolean result = false;
		LOGGER.info("Entering processFilter()");
		result = request.getRequestURI().contains(filterProcessesUrl);
		LOGGER.debug("FilterProcessesUrl: " + filterProcessesUrl + " match: " + result);
		LOGGER.info("Exiting processFilter()");
		return result;
	}
	

	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
			throws IOException, ServletException {
		LOGGER.info("Entering commence()");
		
		String ssoUserName = "";
		String dummyPassword = "doesNotMatterForSSO1";
		String authCode = null;
		String errorStr = null; 
		String ssoFirstName = ""; 
		String ssoLastName = ""; 
		Date expiration = null;
		
		try {
			
			
			
			authCode = request.getParameter(PARAMETER_CODE);
			LOGGER.debug("authCode from request parameter:::"+authCode);
			
			errorStr = request.getParameter(PARAMETER_ERROR);
			if (errorStr != null) {
			LOGGER.debug("Error from request parameter:::"+errorStr);
				if (errorStr.equals("access_denied")) {
					LOGGER.info("User unable to log in error: " + errorStr);
					String errorMsg = "?errorMsg="+errorStr;
					String redirectUrl = createRedirectURI(request);	
					redirectUrl = redirectUrl.replaceFirst("/km/ping/authcode", "");
					String errorURL = redirectUrl + "/unauthorized.html" + errorMsg;
					LOGGER.info("Redirecting to error page: " + errorURL);
					response.sendRedirect(errorURL);
					return;
				}else {
					LOGGER.info("User unable to log in error: " + errorStr);
					String errorMsg = "?errorMsg="+errorStr;
					String redirectUrl = createRedirectURI(request);
					//this will have the /km/ping/login or /km/ping/authcode url after the service part , need to remove it
					redirectUrl = redirectUrl.replaceFirst("/km/ping/authcode", "");				
					String errorURL = redirectUrl + "/general_error.html" + errorMsg;
					LOGGER.info("Redirecting to error page: " + errorURL);
					response.sendRedirect(errorURL);
					return;
				}
			}
			
			if (authCode == null) {
				LOGGER.error("Missing AuthCode unable to authenticate");
				return;
			} else 
			
			// we have authCode need to get the token
			LOGGER.debug("############################ Request Verint OIDC Token ############################");
			
			//create redirect url to math the one used in the Auth filter
			String redirect_uri = createRedirectURI(request);
			LOGGER.debug("Created redirect_uri: " + redirect_uri);
			
			//get token from oidc-token-service
			//JSONObject verintOidcToken = getVerintOIDCToken(authCode, redirect_uri);
			JSONObject verintOidcToken = getTokenAuthorizationCodeGrantType(authCode, redirect_uri);
			
			// Create Verint OIDC token cookie
			if (verintOidcToken.containsKey(ID_TOKEN)) {
				String verintIdToken = (String) verintOidcToken.get(ID_TOKEN);
				//Long verintIdTokenExpiresl = (Long) verintOidcToken.get(EXPIRES_IN);
				//int verintIdTokenExpires = (int) verintOidcToken.get(EXPIRES_IN);
				//int verintIdTokenExpires = verintIdTokenExpiresl.intValue();
				//int expiryTimeInSeconds = getExpriyTimeInSecondsFromNow(verintIdTokenExpires);					
				LOGGER.debug("verintOidcToken: " + verintOidcToken);
				LOGGER.debug("Adding " + VERINT_AUTH_TOKEN_COOKIE_NAME + " ['" + verintIdToken + "'] cookie");
				//LOGGER.debug("Adding " + VERINT_AUTH_TOKEN_COOKIE_NAME + " ['" + verintIdToken + "'] cookie, expires in "+ expiryTimeInSeconds);
				Cookie verintAuthTokenCookie = new Cookie(VERINT_AUTH_TOKEN_COOKIE_NAME , verintIdToken);
				//Remove expiration to make it a session cookie
				//verintAuthTokenCookie.setMaxAge(expiryTimeInSeconds);
				verintAuthTokenCookie.setPath("/");
				response.addCookie(verintAuthTokenCookie);
				
				VerintOIDCTokenUtil jwtDecoded = new VerintOIDCTokenUtil(verintIdToken);
				
				ssoUserName = jwtDecoded.getSubject();				
				String credentials = dummyPassword;
				
				//TODO Need to call agent-service to get agent info here
				/**
				JSONObject agentInfoObj = getAgentInfo(ssoUserName, verintIdToken);
				if (agentInfoObj != null) {
					if (agentInfoObj.containsKey("so:familyName")) {
						ssoFirstName = (String) agentInfoObj.get("so:familyName");
					}					
					if (agentInfoObj.containsKey("so:givenName")) {
						ssoFirstName = (String) agentInfoObj.get("so:givenName");
					}
				} **/
				
				UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
						ssoUserName, credentials);
				authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
				Authentication authResult = authenticationManager.authenticate(authRequest);
				if (authResult.isAuthenticated()) {
					LOGGER.debug("Authentication success: " + authResult);
					request.setAttribute("kmuser", ssoUserName);
					request.setAttribute("kmpassword", dummyPassword);
					// Build up the cookies
					try {
						String user = (String) authResult.getPrincipal();
						String pass = (String) authResult.getCredentials();
				        String authToken = setupToken(user,pass);		        
				        String agentInfo = ssoFirstName + " " + ssoLastName;
				        //We longer have first and last name so just use the username
				        agentInfo = ssoUserName;
				        //LOGGER.debug("authToken: " + authToken);	
				        //LOGGER.debug("agentInfo: " + agentInfo);
						response.setHeader(X_KM_AUTHORIZATION, authToken);
						response.setHeader(USERNAME, user);
						//response.setHeader(FIRST_NAME, ssoFirstName);
						//response.setHeader(LAST_NAME, ssoLastName);
						LOGGER.debug("Adding " +  AGENT_INFO_COOKIE_NAME + " ['"+ agentInfo + "'] cookies");
						Cookie agentInfoCookie = new Cookie(AGENT_INFO_COOKIE_NAME, agentInfo);
						agentInfoCookie.setPath("/");												
						response.addCookie(agentInfoCookie);						
						LOGGER.debug("Adding " + AUTH_TOKEN_COOKIE_NAME + " ['" + authToken + "'] cookie");
						Cookie authTokenCookie = new Cookie(AUTH_TOKEN_COOKIE_NAME , authToken);
						authTokenCookie.setPath("/");												
						response.addCookie(authTokenCookie);
						

					} catch (UnsupportedEncodingException eu) {
						LOGGER.error("UnsupportedEncodingException" + eu.getMessage());
						throw eu;
					}
					
					
									
					SecurityContextHolder.getContext().setAuthentication(authResult);
					rememberMeServices.loginSuccess(request, response, authResult);
					onSuccessfulAuthentication(request, response, authResult);
					
				} else {
					LOGGER.error("Authentication failure: " + authResult);
				}
			} else {
				LOGGER.error("Unable to read verintOidcToken json: " + verintOidcToken.toString());
			}
			
			
		} catch (BadCredentialsException e0) { 
			LOGGER.error("BadCredentialsException message: " + e0.toString());
			String relativePath = getRelativedPath(request);
			String errParam = "?errorMsg=" + URLEncoder.encode(e0.getMessage(), "utf-8");
			if(e0.getMessage().contains("LoginResult=14") || e0.getMessage().contains("No Knowledge base")) {				
				LOGGER.error("Redirecting to: " + relativePath + redirectURIUnAuthorized + errParam);
				response.sendRedirect(relativePath + redirectURIUnAuthorized + errParam);
			} else {
			LOGGER.error("Redirecting to: " + relativePath + redirectURIGeneralError + errParam);				
			response.sendRedirect(relativePath +redirectURIGeneralError + errParam);
			}
		} catch (AuthenticationException ae) {
			LOGGER.error("AuthenticationException message: " + ae.toString());
			String relativePath = getRelativedPath(request);
			String errString = "Exception during loginV2DAO() - " + ae.getMessage();
			String errParam = "?errorMsg=" + URLEncoder.encode(errString, "utf-8");
			response.sendRedirect(relativePath +redirectURIGeneralError + errParam);
		} catch (RemoteException re) {
			String relativePath = getRelativedPath(request);
			String errString = "";
			if(re instanceof AxisFault) {
				errString = "AxisFault - " + ((AxisFault)re).getFaultString();
			} else {
			LOGGER.error("RemoteException: " + re.toString());
				errString = "RemoteException - " + re.getMessage();
			}
			String errParam = "?errorMsg=" + URLEncoder.encode(errString, "utf-8");
			response.sendRedirect(relativePath +redirectURIGeneralError + errParam);
		}catch (HttpResponseException hre1) {
			String relativePath = getRelativedPath(request);
			String errString = "Error with authorization code, HttpResponseException: " + hre1;
			LOGGER.error(errString);
			String errParam = "?errorMsg=" + URLEncoder.encode(errString, "utf-8");
			response.sendRedirect(relativePath +redirectURIUnAuthorized + errParam);
        }catch (Exception e1) {
			LOGGER.error("Error with authcode: " + e1);
			throw new ServletException(e1);
		}

		LOGGER.info("Exiting commence()");
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
		
		try {
			authenticationSuccessHandler.onAuthenticationSuccess(request, response, authResult);
		} catch (ServletException e) {
			LOGGER.debug("Error with authenticationSuccessHandler.onAuthenticationSuccess code", e);
		}
		
	}
	
	public String setupToken(String username, String password) throws UnsupportedEncodingException {
		String creds = username + ":" +  password;
        byte[] bytesEncoded = Base64.encode(creds.getBytes());
        String encodedCredentials = new String(bytesEncoded, "US-ASCII");
        String authToken = "%20Basic%20" + encodedCredentials;
        LOGGER.debug("Set up AuthToken: " + authToken);
        return authToken;
	}
	
	private JSONObject getTokenAuthorizationCodeGrantType(String code, String redirectURI) {
		JSONObject authToken = null;
		String oidcTokenServiceTokenURL = oidcTokenServiceURL;
		
				
		try {
			LOGGER.info("Entering getTokenAuthorizationCodeGrantType()");
			
			String requestURL = oidcTokenServiceTokenURL + "?grant_type=" + oidcTokenServiceGrantType + "&code=" + code + "&client_id=" + oidcTokenServiceClientId +  "&redirect_uri=" + redirectURI;
			LOGGER.debug("Rest Call (token): " + requestURL);
			Instant start = Instant.now();
			authToken = RestUtil.getAndDeserializeAuthCall(requestURL, null, HttpMethod.POST, JSONObject.class, null);
			Instant end = Instant.now();
			LOGGER.debug("SERVICE_CALL_PERFORMANCE -TokenAuthorizationCodeGrantType() duration: " + Duration.between(start, end).toMillis() + "ms");
			
			if (authToken !=null) {
				LOGGER.debug("Authorization Code Grant Type response:" + authToken.toString());
			} else {
				LOGGER.error("Unable to get the Token");
			}
		} catch (Exception ex) {
			LOGGER.error("Unexpected exception in AuthorizationCodeGrantType(): " +  ex.getMessage());
		}
		
		LOGGER.info("Exiting getTokenAuthorizationCodeGrantType()");
		return authToken;
	}
	
	  public JSONObject getVerintOIDCToken(String authcode, String redirect_uri) {
		  JSONObject verintOIDCToken = null;
		  
		  LOGGER.debug("Entering getVerintOIDCToken()");
		  
		  CloseableHttpClient httpClient = HttpClients.createDefault();
		  HttpPost httpPost = new HttpPost(oidcTokenServiceURL);
		 		  
		  
		  try {
			// Request parameters and other properties.
			  List<NameValuePair> form = new ArrayList<NameValuePair>();
			  form.add(new BasicNameValuePair("grant_type", oidcTokenServiceGrantType));
			  form.add(new BasicNameValuePair("redirect_uri", redirect_uri));
			  form.add(new BasicNameValuePair("client_id", oidcTokenServiceClientId));			  
			  form.add(new BasicNameValuePair("code", authcode));
			  UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form,  "UTF-8");
			  httpPost.setEntity(entity);
			  httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
			  
	
			// Create a custom response handler
	          ResponseHandler < String > responseHandler = response -> {
	              int status = response.getStatusLine().getStatusCode();
	              if (status >= 200 && status < 300) {
	                  HttpEntity responseEntity = response.getEntity();
	                  return responseEntity != null ? EntityUtils.toString(responseEntity) : null;
	              } else {
	                  throw new ClientProtocolException("Unexpected response status code: " + status);
	              }
	          };
	          
	          
	          
			  //Execute and get the response.
	          LOGGER.debug("oidc-token-service Request - " + httpPost.getURI().toString());
	          String response = httpClient.execute(httpPost, responseHandler);
	          LOGGER.debug("oidc-token-service Response - " + response);
	          
	          verintOIDCToken = (JSONObject) new JSONParser().parse(response);
	          	          
			} catch (ClientProtocolException e) {
				LOGGER.error("ClientProtocolException - " + e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				LOGGER.error("IOException - " + e.getMessage());
				e.printStackTrace();
			} catch (ParseException e) {
				LOGGER.error("ParseException - " + e.getMessage());
				e.printStackTrace();
			}
		 
		  LOGGER.debug("Exiting getVerintOIDCToken()");		  
		  return verintOIDCToken;
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
	 * @param am
	 */
	public void setAuthenticationManager(AuthenticationManager am) {
		this.authenticationManager = am;
	}
	
	/**
	 * 
	 * @return
	 */
	protected AuthenticationSuccessHandler getAuthenticationSuccessHandler() {
		return authenticationSuccessHandler;
	}

	/**
	 * 
	 * @param am
	 */
	public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler ash) {
		this.authenticationSuccessHandler = ash;
	}
	
	/**
	 * 
	 * @return
	 */
	protected AuthenticationFailureHandler getAuthenticationFailureHandler() {
		return authenticationFailureHandler;
	}

	/**
	 * 
	 * @param am
	 */
	public void setAuthenticationFailureHandler(AuthenticationFailureHandler afh) {
		this.authenticationFailureHandler = afh;
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
	 * @param aep
	 */
	public void setAuthenticationEntryPoint(AuthenticationEntryPoint aep) {
		this.authenticationEntryPoint = aep;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public int getAuthTokenExpirationDuration() {
		return this.authTokenExpirationDuration;
	}
	
	/**
	 * 
	 * @param authTokenExpirationDuration
	 */
	public void setAuthTokenExpirationDuration(int authTokenExpirationDuration) {
		this.authTokenExpirationDuration = authTokenExpirationDuration;		
	}
	
	/**
	 * 
	 * @return
	 */
	public String getOidcTokenServiceURL() {
		return this.oidcTokenServiceURL;
	}
	
	/**
	 * 
	 * @param clientId
	 */
	public void setOidcTokenServiceURL(String oidcTokenServiceURL) {
		this.oidcTokenServiceURL = oidcTokenServiceURL;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getOidcTokenServiceGrantType() {
		return this.oidcTokenServiceGrantType;
	}
	
	/**
	 * 
	 * @param clientId
	 */
	public void setOidcTokenServiceGrantType(String oidcTokenServiceGrantType) {
		this.oidcTokenServiceGrantType = oidcTokenServiceGrantType;
	}

	/**
	 * 
	 * @return
	 */
	public String getOidcTokenServiceClientId() {
		return this.oidcTokenServiceClientId;
	}
	
	/**
	 * 
	 * @param clientId
	 */
	public void setOidcTokenServiceClientId(String oidcTokenServiceClientId) {
		this.oidcTokenServiceClientId = oidcTokenServiceClientId;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getRedirectURI() {
		return this.redirectURI;
	}
	
	/**
	 * 
	 * @param redirectURI
	 */
	public void setRedirectURI(String redirectURI) {
		this.redirectURI = redirectURI;
	}
	
	/**
	 * 
	 * @return
	 */
	public Boolean getRedirectURIForceHTTPS() {
		return this.redirectURIForceHTTPS;
	}
	
	/**
	 * 
	 * @param redirectURI
	 */
	public void setRedirectURIForceHTTPS(Boolean redirectURI) {
		this.redirectURIForceHTTPS = redirectURI;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getRedirectURIUnAuthorized() {
		return this.redirectURIUnAuthorized;
	}
	
	/**
	 * 
	 * @param redirectURIUnAuthorized
	 */
	public void setRedirectURIUnAuthorized(String redirectURIUnAuthorized) {
		this.redirectURIUnAuthorized = redirectURIUnAuthorized;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getRedirectURIGeneralError() {
		return this.redirectURIGeneralError;
	}
	
	/**
	 * 
	 * @param redirectURIGeneralError
	 */
	public void setRedirectURIGeneralError(String redirectURIGeneralError) {
		this.redirectURIGeneralError = redirectURIGeneralError;
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
		
		if (!strRedirectURI.startsWith("https") && redirectURIForceHTTPS) {
			//force it to be https  For some stupid reason the request.getRequestURL() always returns http not https
			LOGGER.debug("Forced over to https original host: " + strRedirectURI);
			strRedirectURI = strRedirectURI.replaceFirst("http", "https");
		}
		
		if (strRedirectURI.indexOf(":80/") != -1) {
			LOGGER.debug("Removing port 80 which should never be on https: " + strRedirectURI);
			strRedirectURI = strRedirectURI.replaceFirst(":80/", "/");
		}
		
		LOGGER.debug("Modified from request URL - savedUrl: " + strRedirectURI);
		LOGGER.info("Exiting createRedirectURI()");
		return strRedirectURI;
	}
	
	private String getRelativedPath(HttpServletRequest request) {
		
		LOGGER.info("getRelativedPath");	
		
		String strRedirectURI = "";
		String uRl = request.getRequestURL().toString();
		
		if(uRl.contains("verintkm")) {
			strRedirectURI = "/verintkm";
		} else if(uRl.contains("contentservices")) {
			strRedirectURI = "/contentservices";
		} else if(uRl.contains("searchservices")) {
			strRedirectURI = "/searchservices";
		} else if(uRl.contains("filterservices")) {
			strRedirectURI = "/filterservices";
		}

		
		LOGGER.debug("Relatived Path: " + strRedirectURI);
		LOGGER.info("getRelativedPath()");
		return strRedirectURI;
	}
	
	public static int getExpriyTimeInSecondsFromNow(int epochTime) {
	
		Long currentEpochTimeInMilliSeconds = System.currentTimeMillis()/1000;
		int currentEpochTimeInSeconds = currentEpochTimeInMilliSeconds.intValue();
		if (epochTime > currentEpochTimeInSeconds) {
			return epochTime - currentEpochTimeInSeconds;
		}
	
		return 0; 
	}
	
}
