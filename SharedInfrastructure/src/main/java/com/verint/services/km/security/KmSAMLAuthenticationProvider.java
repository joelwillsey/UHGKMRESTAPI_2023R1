package com.verint.services.km.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.opensaml.common.SAMLException;
import org.opensaml.common.SAMLRuntimeException;
import org.opensaml.xml.encryption.DecryptionException;
import org.opensaml.xml.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.providers.ExpiringUsernameAuthenticationToken;
import org.springframework.security.saml.SAMLAuthenticationProvider;
import org.springframework.security.saml.SAMLAuthenticationToken;
import org.springframework.security.saml.SAMLConstants;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.security.saml.context.SAMLMessageContext;

/**
 * 
 * @author John.Miller
 *
 */
public class KmSAMLAuthenticationProvider extends SAMLAuthenticationProvider {
	private final static Logger LOGGER = LoggerFactory.getLogger(KmSAMLAuthenticationProvider.class);

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.saml.SAMLAuthenticationProvider#authenticate(org.springframework.security.core.Authentication)
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		LOGGER.info("Entering authenticate()");
		// First check for KmSAMLAuthenticationToken
		Object principal = null;
		Date expiration = null;
		Collection<? extends GrantedAuthority> entitlements = null;
		Object authenticationCredential = null;

		// Check for the custom SAML authentication token
        if (KmSAMLAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
        	final KmSAMLAuthenticationToken token = (KmSAMLAuthenticationToken) authentication;
        	expiration = token.getExpiration();
        	principal = token.getPrincipal();
        	authenticationCredential = "";
    		final SimpleGrantedAuthority authority = new SimpleGrantedAuthority("IS_AUTHENTICATED_FULLY");
    		final List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
    		authorities.add(authority);
    		entitlements = authorities;
        } else {
            final SAMLAuthenticationToken token = (SAMLAuthenticationToken) authentication;
            final SAMLMessageContext context = token.getCredentials();

            if (context == null) {
                throw new AuthenticationServiceException("SAML message context is not available in the authentication token");
            }
            SAMLCredential credential = null;

            try {
                if (SAMLConstants.SAML2_WEBSSO_PROFILE_URI.equals(context.getCommunicationProfileId())) {
                    credential = consumer.processAuthenticationResponse(context);
                } else if (SAMLConstants.SAML2_HOK_WEBSSO_PROFILE_URI.equals(context.getCommunicationProfileId())) {
                    credential = hokConsumer.processAuthenticationResponse(context);
                } else {
                    throw new SAMLException("Unsupported profile encountered in the context " + context.getCommunicationProfileId());
                }
            } catch (SAMLRuntimeException e) {
            	LOGGER.debug("Error validating SAML message", e);
                samlLogger.log(SAMLConstants.AUTH_N_RESPONSE, SAMLConstants.FAILURE, context, e);
                throw new AuthenticationServiceException("Error validating SAML message", e);
            } catch (SAMLException e) {
            	LOGGER.debug("Error validating SAML message", e);
                samlLogger.log(SAMLConstants.AUTH_N_RESPONSE, SAMLConstants.FAILURE, context, e);
                throw new AuthenticationServiceException("Error validating SAML message", e);
            } catch (ValidationException e) {
            	LOGGER.debug("Error validating signature", e);
                samlLogger.log(SAMLConstants.AUTH_N_RESPONSE, SAMLConstants.FAILURE, context, e);
                throw new AuthenticationServiceException("Error validating SAML message signature", e);
            } catch (org.opensaml.xml.security.SecurityException e) {
            	LOGGER.debug("Error validating signature", e);
                samlLogger.log(SAMLConstants.AUTH_N_RESPONSE, SAMLConstants.FAILURE, context, e);
                throw new AuthenticationServiceException("Error validating SAML message signature", e);
            } catch (DecryptionException e) {
            	LOGGER.debug("Error decrypting SAML message", e);
                samlLogger.log(SAMLConstants.AUTH_N_RESPONSE, SAMLConstants.FAILURE, context, e);
                throw new AuthenticationServiceException("Error decrypting SAML message", e);
            }

            // Setup userDetails
            final Object userDetails = getUserDetails(credential);
            principal = getPrincipal(credential, userDetails);
            entitlements = getEntitlements(credential, userDetails);
            expiration = getExpirationDate(credential);
            authenticationCredential = isExcludeCredential() ? null : credential;        	
        }

        // Create the ExpiringUsernameAuthenticationToken
        final ExpiringUsernameAuthenticationToken result = new ExpiringUsernameAuthenticationToken(expiration, principal, authenticationCredential, entitlements);
        result.setDetails(userDetails);
//        samlLogger.log(SAMLConstants.AUTH_N_RESPONSE, SAMLConstants.SUCCESS, context, result, null);
        LOGGER.info("Exiting authenticate()");
        return result;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.saml.SAMLAuthenticationProvider#supports(java.lang.Class)
	 */
    public boolean supports(Class aClass) {
    	LOGGER.info("Entering supports()");
    	boolean retValue = false;
    	if (KmSAMLAuthenticationToken.class.isAssignableFrom(aClass) ||
    			SAMLAuthenticationToken.class.isAssignableFrom(aClass)) {
    		retValue = true;
    	}
    	LOGGER.debug("Return value: " + retValue);
		LOGGER.info("Exiting supports()");
    	return retValue;
    }
}