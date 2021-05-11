package com.verint.services.km.service;

import java.time.Duration;
import java.time.Instant;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.verint.services.km.errorhandling.AppErrorCodes;
import com.verint.services.km.errorhandling.AppErrorMessage;
import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.AuthTokenRequest;
import com.verint.services.km.model.AuthTokenResponse;
import com.verint.services.km.util.RestUtil;
import com.verint.services.km.util.VerintOIDCTokenUtil;
import com.verint.services.km.model.rest.AuthorizationCode;
import com.verint.services.km.model.rest.VerintOIDCToken;
import com.verint.services.km.util.ConfigInfo;

/**
 * @author pseifert
 *
 */
@Path("/authtoken")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Service
public class AuthTokenService extends BaseService {

	private final Logger LOGGER = LoggerFactory.getLogger(AuthTokenService.class);
	private final ConfigInfo kmConfiguration = new ConfigInfo();
	private final String OIDCTokenCookieName = "verintAuthToken";
	
	/**
	 * 
	 */
	public AuthTokenService(){
		super();
		LOGGER.debug("Entering AuthTokenService()");
		LOGGER.debug("Exiting AuthTokenService()");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args){
	}
	
	/**
	 *
	 * @param authTokenRequest
	 * @param httpRequest
	 * @param httpResponse
	 * @return
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public AuthTokenResponse authtoken(AuthTokenRequest authTokenRequest,
    		@Context HttpServletRequest httpRequest,
    		@Context HttpServletResponse httpResponse) {
		LOGGER.info("Entering authtoken()");
		LOGGER.debug("AuthTokenRequest: " + authTokenRequest);
		AuthTokenResponse authTokenResponse = new AuthTokenResponse();
		String redirectURI = authTokenRequest.getredirect_uri();
		String username = authTokenRequest.getUsername();
		String password = authTokenRequest.getPassword();
		boolean useCookie = authTokenRequest.getUseCookie();
		
		try {
			
			if (useCookie) {
												
				String verintToken = getCookie(httpRequest, OIDCTokenCookieName);
							
				if (verintToken != null ) {	
									
					VerintOIDCToken tempToken = new VerintOIDCToken();
					tempToken.setIdToken(verintToken);
					
					if (validateEMOidcToken(tempToken)) {
						//This is a good token
						authTokenResponse.setId_token(verintToken);
						LOGGER.debug("Already existing token in cookie, exiting");
						LOGGER.debug("AuthTokenResponse: " + authTokenResponse);
						LOGGER.info("Exiting authtoken()");
						return authTokenResponse;
					}
				}
			}
			
			AuthorizationCode authCode = null;
			VerintOIDCToken authToken = null;
			authCode = AuthorizationCode(username, password, redirectURI);
			

			if (authCode !=null) {
				LOGGER.debug("AuthorizationCode:" + authCode.getCode());
				
				authToken = TokenAuthorizationCodeGrantType(authCode.getCode(), redirectURI);				
				
				if (validateEMOidcToken(authToken)) {
					LOGGER.debug("Authorization Code Grant Type response:" + authToken.toString());
					
					authTokenResponse.setAccess_token(authToken.getAccessToken()); ;
					authTokenResponse.setId_token(authToken.getIdToken());
					authTokenResponse.setToken_type(authToken.getTokenType());
					authTokenResponse.setExpires_in(authToken.getExpiresIn());
					
					if (useCookie) {
						//Set the Verint OIDC cookie
						if (authToken.getIdToken() != null && !authToken.getIdToken().equals("")) {
							Cookie verintTokenCookie = new Cookie(OIDCTokenCookieName, authToken.getIdToken());
							LOGGER.debug("Setting Cookie " + OIDCTokenCookieName);
							//int expiryTimeInSeconds = getExpriyTimeInSecondsFromNow(authToken.getExpiresIn());
							//LOGGER.debug("Setting Cookie " + OIDCTokenCookieName + " expires " + expiryTimeInSeconds + " seconds");
							//verintTokenCookie.setMaxAge(expiryTimeInSeconds);
							verintTokenCookie.setPath("/");
							httpResponse.addCookie(verintTokenCookie);
						}
					}					
				} else {
					LOGGER.error("Unable to get the OIDC Token");
				}
			} else {
				LOGGER.error("Unable to get the AuthorizationCode");
			}			
						
		} catch (Throwable t) {
			LOGGER.error("Unexpected exception in authtoken() - " +  t.getMessage());
			if(t.getMessage().contentEquals(AppErrorMessage.BAD_REQUEST)) {
				//This was a bad password or user just let it return empty object
			} else {
				throw new AppException(500, AppErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					AppErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION);
			}
		}
				
		LOGGER.debug("AuthTokenResponse: " + authTokenResponse);
		LOGGER.info("Exiting authtoken()");
		return authTokenResponse;
	}
	
	private AuthorizationCode AuthorizationCode(String userName, String password, String redirectURI) {
		AuthorizationCode authCode = null;
		String oidcTokenServiceAuthCodeURL = kmConfiguration.getRestOidcTokenService() + "/" + kmConfiguration.getRestTenantId() + "/authorization_code";
		String scope= "openid tags content_entitlements context_entitlements";
		String clientID = "default";
		
		try {
			LOGGER.info("Entering AuthorizationCode()");
			
			String requestURL = oidcTokenServiceAuthCodeURL + "?username=" + userName + "&password=" + password + "&client_id=" + clientID + "&scope=" + scope + "&redirect_uri=" + redirectURI;
			
			LOGGER.debug("Rest Call (authorization_code): " + requestURL);
			Instant start = Instant.now();
			authCode = RestUtil.getAndDeserializeAuthCall(requestURL, null, HttpMethod.POST, AuthorizationCode.class, null);
			Instant end = Instant.now();
			LOGGER.debug("SERVICE_CALL_PERFORMANCE - AuthorizationCode() duration: " + Duration.between(start, end).toMillis() + "ms");
			
			if (authCode !=null) {				
				LOGGER.debug("AuthorizationCode response:" + authCode.toString());
			} else {
				LOGGER.error("Unable to get the AuthorizationCode");
			}
			
		} catch (Exception ex) {
			LOGGER.error("Unexpected exception in AuthorizationCode(): " + ex.getMessage());
			/*
			 * if (ex.getMessage().contains("400")) { throw new AppException(400,
			 * AppErrorCodes.BAD_REQUEST, AppErrorMessage.BAD_REQUEST); }
			 */
		}
		
		LOGGER.info("Exiting AuthorizationCode()");
		return authCode;
	}
	
	private VerintOIDCToken TokenAuthorizationCodeGrantType(String code, String redirectURI) {
		VerintOIDCToken authToken = null;
		String oidcTokenServiceTokenURL = kmConfiguration.getRestOidcTokenService() + "/" + kmConfiguration.getRestTenantId() +"/token";
		String clientID = "default";
		String grantType = "authorization_code";
				
		try {
			LOGGER.info("Entering TokenAuthorizationCodeGrantType()");
			
			String requestURL = oidcTokenServiceTokenURL + "?grant_type=" + grantType + "&code=" + code + "&client_id=" + clientID +  "&redirect_uri=" + redirectURI;
			LOGGER.debug("Rest Call (token): " + requestURL);
			Instant start = Instant.now();
			authToken = RestUtil.getAndDeserializeAuthCall(requestURL, null, HttpMethod.POST, VerintOIDCToken.class, null);
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
		
		LOGGER.info("Exiting TokenAuthorizationCodeGrantType()");
		return authToken;
	}
	
	public static String getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            if (cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }
        return null;
    }
	
	public static int getExpriyTimeInSecondsFromNow(int epochTime) {
				
		Long currentEpochTimeInMilliSeconds = System.currentTimeMillis()/1000;
		int currentEpochTimeInSeconds = currentEpochTimeInMilliSeconds.intValue();
		if (epochTime > currentEpochTimeInSeconds) {
			return epochTime - currentEpochTimeInSeconds;
		}
		
		return 0; 
	}

	private boolean validateEMOidcToken(VerintOIDCToken authToken) {
		
		VerintOIDCTokenUtil oidcToken = new VerintOIDCTokenUtil(authToken.getIdToken());
		if (!oidcToken.isValid()) {
			LOGGER.error("Verint OIDC Token is not valid");
			return false;
		}
		
		if (oidcToken.isExpired()) {
			LOGGER.error("Verint OIDC Token is expired");
			return false;
		}
		
		if (!oidcToken.isValidSignature()) {
			LOGGER.error("Verint OIDC Token signature is invalid");
			return false;
		}
		
		return true;
	}
		
}
