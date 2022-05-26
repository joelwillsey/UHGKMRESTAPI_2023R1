package com.verint.services.km.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jose4j.base64url.Base64Url;
import org.jose4j.json.internal.json_simple.JSONArray;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.jose4j.json.internal.json_simple.parser.JSONParser;
import org.jose4j.json.internal.json_simple.parser.ParseException;
import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwa.AlgorithmConstraints.ConstraintType;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwx.CompactSerializer;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;
import org.jose4j.lang.StringUtil;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.JsonWebKeySet;
import org.jose4j.jwk.VerificationJwkSelector;
import java.util.Base64;


import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.Validate;




public class KmPingOIDCOauthUtils {

	private final Logger LOGGER = LoggerFactory.getLogger(KmPingOIDCOauthUtils.class);
	private ObjectMapper mapper = new ObjectMapper();
	
	public static final String TOKEN_USERNAME = "username";
	public static final String TOKEN_FIRST_NAME = "given_name";
	public static final String TOKEN_LAST_NAME = "family_name";
	public static final String TOKEN_GROUPS = "msad_groups";
	public static final String SSO_FIRST_NAME = "SSO_FIRST_NAME";
	public static final String SSO_LAST_NAME = "SSO_LAST_NAME";
	public static final String KB_NAMES = "KB_NAMES";
	public static final String USERNAME = "USERNAME";
		
	
	public JSONObject getUserDetails(String id_token, String secret, String jsonWebKeysURL, String kmGroups) throws ParseException, JoseException {
		
		JSONObject userJson = null;
		JSONObject json = null;
		
		String ssoUserName; 
		String ssoFirstName; 
		String ssoLastName; 
		JSONArray roles;
		String parsedKmGroups = "";
		String kbMembership = "";
		
		LOGGER.debug("Enter getUserDetails" );
		
		
		if (verifyJwtSignatureAndUpdateResult(id_token, secret, jsonWebKeysURL)) {
			

			String[] parts = CompactSerializer.deserialize(id_token);
			
			Base64Url base64url = new Base64Url();
			//String decoded_payload = base64url.base64UrlDecodeToString(parts[1], StringUtil.UTF_8);
			String decoded_payload = base64url.base64UrlDecodeToUtf8String(parts[1]);
			
			
			
			json = (JSONObject) new JSONParser().parse(decoded_payload);
			LOGGER.debug("Decoded payload:  " + json);
			
			LOGGER.debug("Default CharSet:  " + Charset.defaultCharset());			
			String fakeToken = "eyJzdWIiOiJhbWFydDQ2MSIsImF1ZCI6IlJlZzFfa21rYyIsImp0aSI6IjdSamJpYTBqSGNybHhQZWMzTzI3c3IiLCJpc3MiOiJodHRwczovL2F1dGhnYXRld2F5MS1kZXYuZW50aWFtLnVoZy5jb20iLCJpYXQiOjE2NDAwMjI5NjcsImV4cCI6MTY0MDAyMzI2NywiYWNyIjoiUjFfQUFMMV9NUy1BRC1LZXJiZXJvcyIsImF1dGhfdGltZSI6MTY0MDAyMjk2NywibXNhZF9ncm91cHMiOlsiQ049S01fTGVhcm5pbmdTb2x1dGlvbnMsQ049VXNlcnMsREM9bXMsREM9ZHMsREM9dWhjLERDPWNvbSIsIkNOPUtNX0NuU19SZXRlbnRpb24sQ049VXNlcnMsREM9bXMsREM9ZHMsREM9dWhjLERDPWNvbSIsIkNOPUtNX01uUl9DbGFpbXMsQ049VXNlcnMsREM9bXMsREM9ZHMsREM9dWhjLERDPWNvbSIsIkNOPUtNX01uUl9Db25zdW1lcl9TZXJ2aWNlLENOPVVzZXJzLERDPW1zLERDPWRzLERDPXVoYyxEQz1jb20iLCJDTj1LTV9NblJfQ2xhaW1fbl9BcHBlYWxzLENOPVVzZXJzLERDPW1zLERDPWRzLERDPXVoYyxEQz1jb20iLCJDTj1LTV9NblJfVGVsZXNhbGVzLENOPVVzZXJzLERDPW1zLERDPWRzLERDPXVoYyxEQz1jb20iLCJDTj1LTV9NblJfUHJvdmlkZXJfU2VydmljZSxDTj1Vc2VycyxEQz1tcyxEQz1kcyxEQz11aGMsREM9Y29tIiwiQ049S01fTW5SX0Vucm9sbEJpbGwsQ049VXNlcnMsREM9bXMsREM9ZHMsREM9dWhjLERDPWNvbSIsIkNOPUtNX01uUl9Qcm9kdWNlcl9IZWxwRGVzayxDTj1Vc2VycyxEQz1tcyxEQz1kcyxEQz11aGMsREM9Y29tIl0sImVtcGxveWVlSWQiOiIwMDE2NjQwMzIiLCJnaXZlbl9uYW1lIjoiQW5kcmVzIEZlbGlwZSIsImZhbWlseV9uYW1lIjoiTWFydGluZXogUGXDsWEiLCJ1c2VybmFtZSI6ImFtYXJ0NDYxIiwicGkuc3JpIjoiVHlnNzlyMzdPdHVhdGt2S3NXYjRfQWU4djM0LlVqRS53TU8yIn0";
			String checkLogging = "Ñoño";
			byte[] convertTo = fakeToken.getBytes(StandardCharsets.UTF_8);
			String fakeTokenUTF8 = new String(fakeToken.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
			String testStr4 = Base64Url.decodeToUtf8String(new String(convertTo, StandardCharsets.UTF_8));
			String testStr = new String(Base64.getDecoder().decode(convertTo), StandardCharsets.UTF_8);
			String testStr3 = base64url.base64UrlDecodeToUtf8String(fakeTokenUTF8);
			//byte[] decoder = Base64.getDecoder().decode(fakeToken);
			byte[] decoder = Base64.getUrlDecoder().decode(fakeToken);						
			String testStr2 = new String(decoder, StandardCharsets.UTF_8);
			LOGGER.debug("FakeToken Decoded payload:  " + testStr);
			LOGGER.debug("FakeToken2 Decoded payload:  " + testStr2);
			LOGGER.debug("FakeToken3 Decoded payload:  " + testStr3);
			LOGGER.debug("FakeToken4 Decoded payload:  " + testStr4);
			LOGGER.debug("FakeToken5 checkLogging:  " + checkLogging);
			if (testStr.contains("ñ")) LOGGER.debug("Test String contains ñ");
			Base64.Decoder b64Decoder = Base64.getUrlDecoder();
			String testStr6 = new String(b64Decoder.decode(fakeToken));
			LOGGER.debug("FakeToken6 Decoded payload:  " + testStr6);
			
			try {
				byte[] tempDecodeTokenByte = decoded_payload.getBytes(StandardCharsets.UTF_8);
				LOGGER.info("tempDecodeToken: " + new String(tempDecodeTokenByte, StringUtil.UTF_8 ));
			} catch (UnsupportedEncodingException e) {
				LOGGER.error("UnsupportedEncodingException - tempDecodeToken" + e.getMessage());
			}
			
			
			ssoUserName = (json.containsKey(TOKEN_USERNAME))?(String)json.get(TOKEN_USERNAME):"";
			ssoFirstName = (json.containsKey(TOKEN_FIRST_NAME))?(String)json.get(TOKEN_FIRST_NAME):"";
			ssoLastName = (json.containsKey(TOKEN_LAST_NAME))?(String)json.get(TOKEN_LAST_NAME):""; 
			
		
			if (json.containsKey(TOKEN_GROUPS)) {
				if (json.get(TOKEN_GROUPS) instanceof JSONArray) {
					roles = (JSONArray)json.get(TOKEN_GROUPS);
					if (roles != null) {
						parsedKmGroups = roles.toString();
					} else {
						parsedKmGroups = "";
					}
				} else {
					parsedKmGroups = (String)json.get(TOKEN_GROUPS);
				}
			}	
			
			kbMembership = parseKMGroups(parsedKmGroups,kmGroups);
			
			userJson = new JSONObject();				
			userJson.put(USERNAME, ssoUserName);
			userJson.put(SSO_FIRST_NAME, ssoFirstName);
			userJson.put(SSO_LAST_NAME, ssoLastName);
			userJson.put(KB_NAMES, kbMembership);			

		} else {
			LOGGER.error( "verifyJwtSignatureAndUpdateResult is false, no token processing" );
			throw new JoseException("Token does not contain a valid signature (no match jwk)");
		}
		
		LOGGER.debug("Exiting getUserDetails" );
		
		return userJson;
	}
	

	private boolean verifyJwtSignatureAndUpdateResult(String id_token, String secret, String jsonWebKeysURL) {
		boolean result = false;
		
		try {

			String[] parts = CompactSerializer.deserialize(id_token);
			Validate.isTrue(parts.length == 3);
			Base64Url base64url = new Base64Url();			
			String decoded_header = base64url.base64UrlDecodeToString(parts[0], StringUtil.UTF_8);
			//String decoded_payload = base64url.base64UrlDecodeToString(parts[1], StringUtil.UTF_8);
			//String decoded_signature = base64url.base64UrlDecodeToString(parts[2], StringUtil.UTF_8);
			
			LOGGER.debug("decoded_header: " +  decoded_header);
			//LOGGER.debug("decoded_payload = " +  decoded_payload);
			//LOGGER.debug("decoded_signature = " +  decoded_signature);
			
			Map<String, Object> header = mapJson(decoded_header);
			String alg = (String) header.get("alg");
			String kid = (String) header.get("kid");
			LOGGER.info("Encryption algorithm (alg) = " +  alg + " ::: Key Id (kid) = " + kid);
			
			Validate.isTrue(alg != null && (alg.startsWith("HS")||alg.startsWith("ES256")), "Unsupported algorithm -" + alg);
			
			if (alg.startsWith("HS")) {
				//HMAC key
				JsonWebSignature jws = new JsonWebSignature();
 
				// Set the compact serialization on the JWS
				jws.setCompactSerialization(id_token);

				//Grab the secret key
				byte[] bytes = secret.getBytes("UTF-8");
				
				//Create the public key using the secret
				HmacKey publicKey = new HmacKey(bytes);
				jws.setKey(publicKey);
				
				// Check the signature
				result =  jws.verifySignature();
			} else if (alg.startsWith("ES256")) {
				JsonWebSignature jws = new JsonWebSignature();
				// Set the algorithm constraints based on what is agreed upon or expected from the sender
				jws.setAlgorithmConstraints(new AlgorithmConstraints(ConstraintType.WHITELIST, AlgorithmIdentifiers.ECDSA_USING_P256_CURVE_AND_SHA256));
				
				// Set the compact serialization on the JWS
				jws.setCompactSerialization(id_token);
				
				//need to retrieve the web keys as these can change daily
				//LOGGER.debug("Retrieve jsonWebKeys (Public Keys) from " + jsonWebKeysURL);
				String jwKeys = null;;
				try {
					jwKeys = readJsonFromUrl(jsonWebKeysURL);
				} catch (IOException ex){
					LOGGER.error("IOException: " + ex.toString());
				}
				
				//If the string parse without throwing exception we know it in proper json format or empty
				JSONParser parser = new JSONParser();
				JSONObject jKeys = (JSONObject) parser.parse(jwKeys);

				if (jKeys.isEmpty()) {
					LOGGER.info("No jsonWebKeys (Public Keys) were retrieved from " + jsonWebKeysURL  + "key string: " + jwKeys.toString());
				} else {
					LOGGER.info("Successfully retrieved jsonWebKeys (Public Keys) from " + jsonWebKeysURL);
				}
						
				// Create a new JsonWebKeySet object with the JWK Set JSON
				//LOGGER.debug"Load  jsonWebKeySet");
				JsonWebKeySet jsonWebKeySet = new JsonWebKeySet(jwKeys);
				
				// The JWS header contains information indicating which key was used to secure the JWS.
			    // In this case (as will hopefully often be the case) the JWS Key ID
			    // corresponds directly to the Key ID in the JWK Set.
			    // The VerificationJwkSelector looks at Key ID, Key Type, designated use (signatures vs. encryption),
			    // and the designated algorithm in order to select the appropriate key for verification from
			    // a set of JWKs.
				//LOGGER.debug("Set jwkSelector");
				VerificationJwkSelector jwkSelector = new VerificationJwkSelector();
				
				//LOGGER.debug("Create jwk key");
				//LOGGER.debug("jws =" + jws.toString());	
				//LOGGER.debug("JsonWebKeys = " + jsonWebKeySet.getJsonWebKeys());	
				JsonWebKey jwk = jwkSelector.select(jws, jsonWebKeySet.getJsonWebKeys());
				
				if (jwk != null) {
					// The verification key on the JWS is the public key from the JWK we pulled from the JWK Set.
					LOGGER.info("Set key: " + jwk.getKey().toString());
				    jws.setKey(jwk.getKey());	
				    
				    // Check the signature
				    //LOGGER.debug("verifyJwtSignatureAndUpdateResult" , "Verify key");
					result =  jws.verifySignature();
					
					LOGGER.info("JWT EncodedPayload: " + jws.getEncodedPayload());
					LOGGER.info("JWT PayloadCharEncoding: " + jws.getPayloadCharEncoding());
					LOGGER.info("JWT Payload: " + jws.getPayload());
					
				} else {
					LOGGER.error("No matching Public key found to verify the signature for kid: " + kid);
					LOGGER.error("Available Public keys are: " + jwKeys);
				}
			} else {
				LOGGER.error("Unsupported algorithm - " + alg);
			}
			
			LOGGER.info("id_token verify signature result = " + result);
			
		} catch (JoseException jex) {
			LOGGER.error("JoseException in validating id_token signature: " + jex.toString() + " Message: " + jex.getMessage());
			result =  false;
		} catch  (Exception ex) {
			LOGGER.error("Exception in validating id_token signature: " + ex.toString() + " Message: " +ex.getMessage());
			result =  false;
		}
		
		return result;
	}
	
	private Map<String, Object> mapJson(String json) {
		try {
			TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
			};
			return mapper.readValue(json, typeRef);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}		
	}
	
	private String parseKMGroups(String kmGroup, String kmGroups) 
	{
		String[] ary = kmGroups.split(",");

		String group = "";
		if (kmGroup != null) 
		{
			for (int i = 0; i < ary.length; i++) 
			{
				//LOGGER.debug("ary["+i+"]=" + ary[i]);
				if (kmGroup.contains(ary[i].trim())) 
				{
					group +=ary[i].trim();
					group += ",";
				}
			}
		}
		//if(group=="") return "default";
		return group;
	}
	
	  private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }
	  
	  public static String readJsonFromUrl(String url) throws IOException {
	    InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      rd.close();
	      return jsonText;
	    } finally {
	      is.close();		      
	    }
	  }
}
