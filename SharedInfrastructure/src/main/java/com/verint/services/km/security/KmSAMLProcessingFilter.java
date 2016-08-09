package com.verint.services.km.security;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.opensaml.common.SAMLException;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.Attribute;
import org.opensaml.saml2.core.AttributeStatement;
import org.opensaml.saml2.core.Conditions;
import org.opensaml.saml2.core.Response;
import org.opensaml.saml2.core.Status;
import org.opensaml.saml2.metadata.provider.MetadataProviderException;
import org.opensaml.ws.message.decoder.MessageDecodingException;
import org.opensaml.xml.XMLObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.saml.SAMLAuthenticationToken;
import org.springframework.security.saml.SAMLProcessingFilter;
import org.springframework.security.saml.util.SAMLUtil;

public class KmSAMLProcessingFilter extends SAMLProcessingFilter {
	private static final Logger LOGGER = LoggerFactory.getLogger(KmSAMLProcessingFilter.class);

	/**
	 * 
	 */
    public KmSAMLProcessingFilter() {
        this(FILTER_URL);
        LOGGER.info("Entering KmSAMLProcessingFilter()");
        LOGGER.info("Exiting KmSAMLProcessingFilter()");
    }

    /**
     * 
     * @param defaultFilterProcessesUrl
     */
    protected KmSAMLProcessingFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
        LOGGER.info("Entering KmSAMLProcessingFilter()");
        LOGGER.debug("defaultFilterProcessesUrl: " + defaultFilterProcessesUrl);
        setFilterProcessesUrl(defaultFilterProcessesUrl);
        LOGGER.info("Exiting KmSAMLProcessingFilter()");
    }

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.saml.SAMLProcessingFilter#attemptAuthentication(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		LOGGER.info("Entering attemptAuthentication()");
        try {
        	LOGGER.debug("Attempting SAML2 authentication using profile {}", getProfileName());
            KmSAMLMessageContext context = (KmSAMLMessageContext)contextProvider.getLocalEntity(request, response);
            context = (KmSAMLMessageContext)processor.retrieveMessage(context);

            Date expirationDate = new Date();
            String principle = "";
            String firstName = "";
            String lastName = "";
            String email = "";

            if (context.getInboundMessage() != null) {
            	LOGGER.debug("KmSAMLMessageContext: " + context.getInboundMessage());
            	Response samlResponse = (Response)context.getInboundMessage();
            	Status status = samlResponse.getStatus();
            	if (status != null && status.getStatusCode() != null && status.getStatusCode().getValue().contains("Success")) {
	            	List<Assertion> assertions = samlResponse.getAssertions();
	            	for (int x = 0; (assertions != null && x < assertions.size()); x++) {
	            		Assertion assertion = assertions.get(x);
	            		principle = assertion.getSubject().getNameID().getValue();
	            		List<AttributeStatement> statements = assertion.getAttributeStatements();
		            	for (int i = 0; (statements != null && i < statements.size()); i++) {
		            		AttributeStatement attributeStatement = statements.get(i);
		            		List<Attribute> attributes = attributeStatement.getAttributes();
		            		for (int y = 0; (attributes != null && y < attributes.size()); y++) {
		            			Attribute attribute = attributes.get(y);
		            			LOGGER.debug("Attribute Name: " + attribute.getName());
		            			String attrName = attribute.getName();
		            			List<XMLObject> attributeValues = attribute.getAttributeValues();
		            			for (int z = 0; (attributeValues != null && z < attributeValues.size()); z++) {
		            				XMLObject xml = attributeValues.get(z);
		            				LOGGER.debug("Attribute Value: " + xml.getDOM().getTextContent());
		            				if ("EmailAddress".equals(attrName)) {
		            					email = xml.getDOM().getTextContent();
		            				} else if ("FirstName".equals(attrName)) {
		            					firstName = xml.getDOM().getTextContent();
		            				} else if ("LastName".equals(attrName)) {
		            					lastName = xml.getDOM().getTextContent();
		            				}
		            			}
		            		}
		            	}
		            	Conditions conditions = assertion.getConditions();
		            	DateTime expiration = null;
		            	DateTime newExpiration = conditions.getNotOnOrAfter();
	                    if (newExpiration != null) {
	                        if (expiration == null || expiration.isAfter(newExpiration)) {
	                            expiration = newExpiration;
	                        }
	                    }
	                    expirationDate = (expiration != null ? expiration.toDate() : null);
	            	}
            	}
            }

            try {
	            // Build up the cookies
            	String authCookie = "";
            	if (expirationDate != null) {
    	            authCookie = principle + ":" + expirationDate.toString();            		
            	} else {
            		Date tempDate = new Date();
            		authCookie = principle + ":" + tempDate.toString();
            	}
            	LOGGER.debug("AuthCookie: " + authCookie);
	            byte[] bytesEncoded = Base64.getEncoder().encode(authCookie.getBytes());
	            String agentInfo = firstName + " " + lastName;
	            response.setHeader("Set-Cookie", "AuthToken=" +  new String(bytesEncoded, "US-ASCII") + "; path=/ ; HttpOnly" + " ,AgentInfo=" +  agentInfo + "; path=/ ; HttpOnly");
            } catch (UnsupportedEncodingException uee) {
            	LOGGER.error("UnsupportedEncodingException encoding bytes", uee);
            }

            // Override set values
            context.setCommunicationProfileId(getProfileName());
            context.setLocalEntityEndpoint(SAMLUtil.getEndpoint(context.getLocalEntityRoleMetadata().getEndpoints(), context.getInboundSAMLBinding(), context.getInboundMessageTransport()));
            final SAMLAuthenticationToken token = new SAMLAuthenticationToken(context);

            LOGGER.info("Exiting attemptAuthentication()");
            return getAuthenticationManager().authenticate(token);
        } catch (SAMLException e) {
            logger.debug("Incoming SAML message is invalid", e);
            throw new AuthenticationServiceException("Incoming SAML message is invalid", e);
        } catch (MetadataProviderException e) {
            logger.debug("Error determining metadata contracts", e);
            throw new AuthenticationServiceException("Error determining metadata contracts", e);
        } catch (MessageDecodingException e) {
            logger.debug("Error decoding incoming SAML message", e);
            throw new AuthenticationServiceException("Error decoding incoming SAML message", e);
        } catch (org.opensaml.xml.security.SecurityException e) {
            logger.debug("Incoming SAML message is invalid", e);
            throw new AuthenticationServiceException("Incoming SAML message is invalid", e);
        }
    }	
}