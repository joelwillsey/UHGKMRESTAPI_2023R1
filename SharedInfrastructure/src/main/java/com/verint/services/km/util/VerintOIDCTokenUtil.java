package com.verint.services.km.util;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

import org.jose4j.json.JsonUtil;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.JsonWebKeySet;
import org.jose4j.jwk.VerificationJwkSelector;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;


public class VerintOIDCTokenUtil {

	private final Logger LOGGER = LoggerFactory.getLogger(VerintOIDCTokenUtil.class);
	private final ConfigInfo kmConfiguration = new ConfigInfo();
	private String jwt;
	private boolean validFormat = false;
	private long expiry = 0;
	private long issuedAt = 0;
	private String subject = "";
	private String[] tags;
	private String[] contentEntitlements;
	private String[] contextEntitlements;
	private String[] emAPIAccess;
	private String issuer= "";
	private String oidcTokenHeader = "";
	private String oidcTokenBody = "";
	private String oidcTokenSignature = "";
	
	public VerintOIDCTokenUtil (String token) {
		jwt = token;
		if (jwt != null && !jwt.isEmpty()) {
			processJWT();
		}
	}
	
	private void processJWT() {
				
		int indexOfHeader = jwt.indexOf(".");					
		if (indexOfHeader != -1) {
			String[] chunks = jwt.split("\\.");
			//LOGGER.debug("JWT has " + chunks.length + " chunks");
			if (chunks.length > 1 && chunks.length <= 3) {
				//there should be 2 or 3 chunks so this appears to be structured as a jwt so I'll let it through
				oidcTokenHeader = decode(chunks[0]);				
				oidcTokenBody = decode(chunks[1]);
				if (chunks.length == 3) {
					oidcTokenSignature = decode(chunks[2]);
				}
								
				try {
					JSONObject json = new JSONObject(JsonUtil.parseJson(oidcTokenBody));
					
					if (json.containsKey("exp") && json.containsKey("sub") && json.containsKey("iat") && json.containsKey("iss")) {
						//if these four keys are here we are going to assume the token is valid
						validFormat = true;
					} else {
						//Bad token just return
						return;
					}
					
					if (json.containsKey("exp")) {
						expiry = (long) json.get("exp");						
					}
					
					if (json.containsKey("iat")) {
						issuedAt = (long) json.get("iat");
					}
						
					if (json.containsKey("iss")) {
						issuer = (String) json.get("iss");
					}
					
					if (json.containsKey("sub")) {
						subject = (String) json.get("sub");
					}
					
					if (json.containsKey("tags")) {
						ArrayList<String> jTags = (ArrayList<String>) json.get("tags");
						tags = (String[]) jTags.toArray(new String[0]);
					} else {
						//init empty array
						tags = new String[0];
					}
					
					if (json.containsKey("content_entitlements")) {
						ArrayList<String> jContentEntitlements = (ArrayList<String>) json.get("content_entitlements");
						contentEntitlements = (String[]) jContentEntitlements.toArray(new String[0]);
					} else {
						//init empty array
						contentEntitlements = new String[0];
					}
					
					if (json.containsKey("context_entitlements")) {
						ArrayList<String> jContextEntitlements = (ArrayList<String>) json.get("context_entitlements");
						contextEntitlements = (String[]) jContextEntitlements.toArray(new String[0]);
					} else {
						//init empty array
						contextEntitlements = new String[0];
					}
					
					if (json.containsKey("em_api_access")) {
						ArrayList<String> jEmAPIAccess = (ArrayList<String>) json.get("em_api_access");
						emAPIAccess = (String[]) jEmAPIAccess.toArray(new String[0]);
					} else {
						//init empty array
						emAPIAccess = new String[0];
					}
					
				} catch (JoseException e) {
					LOGGER.error("processJWT() JoseException: " + e.getMessage());
				}
			} else {
				LOGGER.error("JWT has " + chunks.length + " chunks");
			}
		} else {
			LOGGER.error("Could not split JWT into chunks, no seperater found");
		}
				
	}
	
	private static String decode(String encodedString) {
		byte[] decodedBytes = Base64Utils.decodeFromString(encodedString);
		String decodedString = new String(decodedBytes);
		return decodedString;
	}
	
	public boolean  isValidSignature() {

		if(!validFormat) {
			return false;
		}
		
		try {
			String requestURL = kmConfiguration.getRestOidcTokenService() + "/" + kmConfiguration.getRestTenantId() + "/connect/jwk_uri";
			LOGGER.debug("Rest Call (jwk): " + requestURL);
			Instant start = Instant.now();
			ResponseEntity<String> jsonWebKeySetJson = RestUtil.getRestResponseAuthCall(requestURL, null, HttpMethod.GET, String.class, null);
			Instant end = Instant.now();
			LOGGER.debug("SERVICE_CALL_PERFORMANCE -validateEMOidcTokenSignature() duration: " + Duration.between(start, end).toMillis() + "ms");

			JsonWebSignature jws = new JsonWebSignature();
			jws.setAlgorithmConstraints(new AlgorithmConstraints(AlgorithmConstraints.ConstraintType.WHITELIST,
					AlgorithmIdentifiers.RSA_USING_SHA256));
			jws.setCompactSerialization(jwt);
			JsonWebKeySet jsonWebKeySet = new JsonWebKeySet(jsonWebKeySetJson.getBody());
			VerificationJwkSelector jwkSelector = new VerificationJwkSelector();
			JsonWebKey jwk = jwkSelector.select(jws, jsonWebKeySet.getJsonWebKeys());
			jws.setKey(jwk.getKey());
			boolean signatureVerified = jws.verifySignature();
			LOGGER.debug("JWS Signature is valid: " + signatureVerified);
			String payload = jws.getPayload();
			LOGGER.debug("JWS payload: " + payload);
			return signatureVerified;
		} catch (JoseException e) {
			LOGGER.debug("JoseException", e);
			return false;
		}
		
	}
	
	
	public boolean isValid() {
		return validFormat;
	}
	
	public boolean isExpired() {
		long secondsFromEpoch = System.currentTimeMillis()/1000;		
		if (expiry > secondsFromEpoch) {
			//Token is not expired			
			return false;
		} else {
			LOGGER.debug("JWT is expired, expiry: " + expiry);
			return true;
		}
	}
	
	public String getSubject() {
		return subject; 
	}
	
	public String getIssuer() {
		return issuer; 
	}
	
	public String[] getTags() {
		return tags; 
	}
	
	public String[] getContentEntitlements() {
		return contentEntitlements; 
	}
	
	public String[] getContextEntitlements() {
		return contextEntitlements; 
	}
	
	public String[] getEmAPIAccess() {
		return emAPIAccess; 
	}
	
	public long getIssuedAt() {
		return issuedAt;
	}
	
	public long getExpirationTime() {
		return expiry;
	}
	
	public String getTokenHeader() {
		return oidcTokenHeader; 
	}
	
	public String getTokenBody() {
		return oidcTokenBody; 
	}
	
	public String getTokenSignature() {
		return oidcTokenSignature; 
	}
	
	public String getOIDCToken() {
		return jwt; 
	}
}
