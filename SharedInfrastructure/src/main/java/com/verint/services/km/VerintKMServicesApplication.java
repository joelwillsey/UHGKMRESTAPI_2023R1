package com.verint.services.km;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

/**
 * 
 * @author jmiller
 *
 */
public class VerintKMServicesApplication extends ResourceConfig {
	private static Logger LOGGER =
			 Logger.getLogger(VerintKMServicesApplication.class.getName());

	/**
	 * 
	 */
	public VerintKMServicesApplication() {
        register(new VerintKMServicesBinder());
        packages(true, "com.verint.services.km");
 
        // Enable Tracing support.
        property(ServerProperties.TRACING, "ALL");
        LOGGER.setLevel(Level.FINEST);
        registerInstances(new LoggingFilter(LOGGER, true));
	}
}
