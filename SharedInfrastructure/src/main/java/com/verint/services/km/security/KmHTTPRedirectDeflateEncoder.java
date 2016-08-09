package com.verint.services.km.security;


import org.opensaml.common.binding.SAMLMessageContext;
import org.opensaml.saml2.binding.encoding.HTTPRedirectDeflateEncoder;
import org.opensaml.util.URLBuilder;
import org.opensaml.ws.message.MessageContext;
import org.opensaml.ws.message.encoder.MessageEncodingException;
import org.opensaml.ws.transport.http.HTTPOutTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KmHTTPRedirectDeflateEncoder extends HTTPRedirectDeflateEncoder {
	private static final Logger LOGGER = LoggerFactory.getLogger(KmSAMLProcessorImpl.class);

	/*
	 * (non-Javadoc)
	 * @see org.opensaml.saml2.binding.encoding.HTTPRedirectDeflateEncoder#doEncode(org.opensaml.ws.message.MessageContext)
	 */
	@Override
    protected void doEncode(MessageContext messageContext) throws MessageEncodingException {
		LOGGER.info("Entering doEncode()");
        if (!(messageContext instanceof SAMLMessageContext)) {
        	LOGGER.error("Invalid message context type, this encoder only support SAMLMessageContext");
            throw new MessageEncodingException(
                    "Invalid message context type, this encoder only support SAMLMessageContext");
        }

        if (!(messageContext.getOutboundMessageTransport() instanceof HTTPOutTransport)) {
        	LOGGER.error("Invalid outbound message transport type, this encoder only support HTTPOutTransport");
            throw new MessageEncodingException(
                    "Invalid outbound message transport type, this encoder only support HTTPOutTransport");
        }

        final KmSAMLMessageContext samlMsgCtx = (KmSAMLMessageContext) messageContext;
        final URLBuilder urlBuilder = getEndpointURL(samlMsgCtx);
        setResponseDestination(samlMsgCtx.getOutboundSAMLMessage(), urlBuilder.buildURL());
        removeSignature(samlMsgCtx);
        final String redirectURL = buildRedirectURL(samlMsgCtx, urlBuilder.buildURL(), deflateAndBase64Encode(samlMsgCtx.getOutboundSAMLMessage()));
        LOGGER.debug("RedirectURL: " + redirectURL);
        samlMsgCtx.setRedirectUrl(redirectURL);

        LOGGER.info("Exiting doEncode()");
    }
}