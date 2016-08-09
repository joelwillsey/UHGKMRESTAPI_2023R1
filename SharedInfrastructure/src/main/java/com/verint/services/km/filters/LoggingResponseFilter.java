package com.verint.services.km.filters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author jmiller
 *
 */
@Provider
public class LoggingResponseFilter implements ContainerResponseFilter {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingResponseFilter.class);

	public void filter(ContainerRequestContext requestContext,
			ContainerResponseContext responseContext) throws IOException {
		final String method = requestContext.getMethod();

		LOGGER.debug("Requesting " + method + " for path " + requestContext.getUriInfo().getPath());
		final Object entity = responseContext.getEntity();
		if (entity != null) {
//			LOGGER.debug("Response " + new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(entity));
		}
		
	}
}