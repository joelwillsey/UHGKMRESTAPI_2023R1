package com.verint.services.km.security;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.opensaml.common.SAMLObject;
import org.opensaml.common.binding.SAMLMessageContext;
import org.opensaml.saml2.binding.decoding.HTTPPostDecoder;
import org.opensaml.ws.message.MessageContext;
import org.opensaml.ws.message.decoder.MessageDecodingException;
import org.opensaml.ws.transport.http.HTTPInTransport;
import org.opensaml.xml.parse.ParserPool;
import org.opensaml.xml.util.Base64;
import org.opensaml.xml.util.DatatypeHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author John.Miller
 *
 */
public class KmHTTPPostDecoder extends HTTPPostDecoder {
	private static final Logger LOGGER = LoggerFactory.getLogger(KmHTTPPostDecoder.class);
	private String encodedSAMLResponse;

	/**
	 * 
	 */
	public KmHTTPPostDecoder() {
		super();
		LOGGER.info("Entering KmHTTPPostDecoder()");
		LOGGER.info("Exiting KmHTTPPostDecoder()");
	}

	/**
	 * 
	 * @param pool
	 */
	public KmHTTPPostDecoder(ParserPool pool) {
		super(pool);
		LOGGER.info("Entering KmHTTPPostDecoder()");
		LOGGER.info("Exiting KmHTTPPostDecoder()");
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.opensaml.saml2.binding.decoding.HTTPPostDecoder#doDecode(org.opensaml.ws.message.MessageContext)
	 */
	@Override
    protected void doDecode(MessageContext messageContext) throws MessageDecodingException {
		LOGGER.info("Entering doDecode()");
        if (!(messageContext instanceof SAMLMessageContext)) {
        	LOGGER.error("Invalid message context type, this decoder only support SAMLMessageContext");
            throw new MessageDecodingException(
                    "Invalid message context type, this decoder only support SAMLMessageContext");
        }

        if (!(messageContext.getInboundMessageTransport() instanceof HTTPInTransport)) {
        	LOGGER.error("Invalid inbound message transport type, this decoder only support HTTPInTransport");
            throw new MessageDecodingException(
                    "Invalid inbound message transport type, this decoder only support HTTPInTransport");
        }

        final KmSAMLMessageContext samlMsgCtx = (KmSAMLMessageContext)messageContext;
        final HTTPInTransport inTransport = (HTTPInTransport) samlMsgCtx.getInboundMessageTransport();
        if (!inTransport.getHTTPMethod().equalsIgnoreCase("POST")) {
            throw new MessageDecodingException("This message deocoder only supports the HTTP POST method");
        }

        final String relayState = inTransport.getParameterValue("RelayState");
        samlMsgCtx.setRelayState(relayState);
        LOGGER.debug("Decoded SAML relay state of: {}", relayState);

        final InputStream base64DecodedMessage = getBase64DecodedMessage(inTransport);
        final SAMLObject inboundMessage = (SAMLObject) unmarshallMessage(base64DecodedMessage);
        samlMsgCtx.setInboundMessage(inboundMessage);
        samlMsgCtx.setInboundSAMLMessage(inboundMessage);        
        samlMsgCtx.setEncodedSAMLResponse(getEncodedSAMLResponse());
        LOGGER.debug("Decoded SAML message");
        populateMessageContext(samlMsgCtx);
        LOGGER.info("Exiting doDecode()");
    }

	/*
	 * (non-Javadoc)
	 * @see org.opensaml.saml2.binding.decoding.HTTPPostDecoder#getBase64DecodedMessage(org.opensaml.ws.transport.http.HTTPInTransport)
	 */
	@Override
    protected InputStream getBase64DecodedMessage(HTTPInTransport transport) throws MessageDecodingException {
		LOGGER.info("Entering getBase64DecodedMessage()");
    	LOGGER.debug("Getting Base64 encoded message from request");
        String encodedMessage = transport.getParameterValue("SAMLRequest");
        if (DatatypeHelper.isEmpty(encodedMessage)) {
            encodedMessage = transport.getParameterValue("SAMLResponse");
            if (DatatypeHelper.isEmpty(encodedMessage)) {
            	encodedMessage = transport.getHeaderValue("SAMLResponse");
            }
        }

        if (DatatypeHelper.isEmpty(encodedMessage)) {
        	LOGGER.error("Request did not contain either a SAMLRequest or "
                    + "SAMLResponse paramter.  Invalid request for SAML 2 HTTP POST binding.");
            throw new MessageDecodingException("No SAML message present in request");
        }

        // Set the encoded message to be used later
        setEncodedSAMLResponse(encodedMessage);
        
        LOGGER.debug("Base64 decoding SAML message:\n{}", encodedMessage);
        byte[] decodedBytes = Base64.decode(encodedMessage);
        if(decodedBytes == null){
        	LOGGER.error("Unable to Base64 decode SAML message");
            throw new MessageDecodingException("Unable to Base64 decode SAML message");
        }

        LOGGER.debug("Decoded SAML message:\n{}", new String(decodedBytes));
        LOGGER.info("Exiting getBase64DecodedMessage()");
        return new ByteArrayInputStream(decodedBytes);
    }

	/**
	 * @return the encodedSAMLResponse
	 */
	public String getEncodedSAMLResponse() {
		return encodedSAMLResponse;
	}

	/**
	 * @param encodedSAMLResponse the encodedSAMLResponse to set
	 */
	public void setEncodedSAMLResponse(String encodedSAMLResponse) {
		this.encodedSAMLResponse = encodedSAMLResponse;
	}
}