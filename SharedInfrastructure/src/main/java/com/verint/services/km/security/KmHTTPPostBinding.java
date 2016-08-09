package com.verint.services.km.security;

import java.util.List;

import org.apache.velocity.app.VelocityEngine;
import org.opensaml.common.binding.security.SAMLProtocolMessageXMLSignatureSecurityPolicyRule;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.binding.encoding.HTTPPostEncoder;
import org.opensaml.saml2.binding.security.SAML2HTTPPostSimpleSignRule;
import org.opensaml.ws.message.decoder.MessageDecoder;
import org.opensaml.ws.message.encoder.MessageEncoder;
import org.opensaml.ws.security.SecurityPolicyRule;
import org.opensaml.ws.transport.InTransport;
import org.opensaml.ws.transport.OutTransport;
import org.opensaml.ws.transport.http.HTTPInTransport;
import org.opensaml.ws.transport.http.HTTPOutTransport;
import org.opensaml.ws.transport.http.HTTPTransport;
import org.opensaml.xml.parse.ParserPool;
import org.opensaml.xml.signature.SignatureTrustEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.saml.context.SAMLMessageContext;
import org.springframework.security.saml.processor.SAMLBindingImpl;

/**
 * Http POST binding.
 *
 * @author John Miller
 */
public class KmHTTPPostBinding extends SAMLBindingImpl {
	private static final Logger LOGGER = LoggerFactory.getLogger(KmHTTPPostBinding.class);
	
	/**
	 * Pool for message deserializers.
	 */
	protected ParserPool parserPool;

	/**
	 * Creates default implementation of the binding.
	 *
	 * @param parserPool
	 *            parserPool for message deserialization
	 * @param velocityEngine
	 *            engine for message formatting
	 */
	public KmHTTPPostBinding(ParserPool parserPool, VelocityEngine velocityEngine) {
		this(parserPool, new KmHTTPPostDecoder(parserPool),
				new HTTPPostEncoder(velocityEngine, "/templates/saml2-post-binding.vm"));
		LOGGER.info("Entering KmHTTPPostBinding()");
		LOGGER.info("Exiting KmHTTPPostBinding()");
	}

	/**
	 * Implementation of the binding with custom encoder and decoder.
	 *
	 * @param parserPool
	 *            parserPool for message deserialization
	 * @param decoder
	 *            custom decoder implementation
	 * @param encoder
	 *            custom encoder implementation
	 */
	public KmHTTPPostBinding(ParserPool parserPool, MessageDecoder decoder, MessageEncoder encoder) {
		super(decoder, encoder);
		this.parserPool = parserPool;
		LOGGER.info("Entering KmHTTPPostBinding()");
		LOGGER.info("Exiting KmHTTPPostBinding()");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.saml.processor.SAMLBinding#supports(org.
	 * opensaml.ws.transport.InTransport)
	 */
	@Override
	public boolean supports(InTransport transport) {
		LOGGER.info("Entering supports()");
		boolean retValue = false;
		// Check for SAMLRequest and SAMLResponse
		if (transport instanceof HTTPInTransport) {
			HTTPTransport t = (HTTPTransport) transport;
			retValue = "POST".equalsIgnoreCase(t.getHTTPMethod())
					&& (t.getParameterValue("SAMLRequest") != null || t.getParameterValue("SAMLResponse") != null);
		}
		LOGGER.debug("Return value: " + retValue);
		LOGGER.info("Exiting supports()");
		return retValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.saml.processor.SAMLBinding#supports(org.
	 * opensaml.ws.transport.OutTransport)
	 */
	@Override
	public boolean supports(OutTransport transport) {
		return transport instanceof HTTPOutTransport;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.saml.processor.SAMLBinding#getBindingURI()
	 */
	@Override
	public String getBindingURI() {
		return SAMLConstants.SAML2_POST_BINDING_URI;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.saml.processor.SAMLBindingImpl#
	 * getSecurityPolicy(java.util.List,
	 * org.springframework.security.saml.context.SAMLMessageContext)
	 */
	@Override
	public void getSecurityPolicy(List<SecurityPolicyRule> securityPolicy, SAMLMessageContext samlContext) {
		LOGGER.info("Entering getSecurityPolicy()");
		final SignatureTrustEngine engine = samlContext.getLocalTrustEngine();
		securityPolicy.add(new SAML2HTTPPostSimpleSignRule(engine, parserPool, engine.getKeyInfoResolver()));
		securityPolicy.add(new SAMLProtocolMessageXMLSignatureSecurityPolicyRule(engine));
		LOGGER.info("Exiting getSecurityPolicy()");
	}
}