/**
 * 
 */
package com.verint.services.km.dbcp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jmiller
 *
 */
public class ConnectionPool {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionPool.class);
	private static BasicDataSource DATASOURCE = null;

	static {
		final String OSName = System.getProperty("os.name");
		LOGGER.debug("OSName: " + OSName);

		String fileLocation = "/opt/kmservices/";
		if (OSName != null && OSName.length() > 0) {
			if (OSName.startsWith("Windows")) {
				fileLocation = "C:\\opt\\kmservices\\";
			}
		}
		try {
			// Get the properties
			final Properties prop = new Properties();
			LOGGER.debug("FileLocation: " + fileLocation + "connectionPool.properties");
	        final InputStream in = new FileInputStream(fileLocation + "connectionPool.properties");
	        LOGGER.debug("After: " + in);
	        prop.load(in);
	        
	        // Create the basic data source
	        LOGGER.debug("Creating the BasicDataSource");
	        DATASOURCE = new BasicDataSource();
	        LOGGER.debug("DATASOURCE: " + DATASOURCE);
	        DATASOURCE.setDriverClassName(prop.getProperty("connection.driverclassname"));
	        DATASOURCE.setUrl(prop.getProperty("connection.url"));
	        DATASOURCE.setUsername(prop.getProperty("connection.username"));
	        DATASOURCE.setPassword(prop.getProperty("connection.password"));
	        DATASOURCE.setMaxTotal(Integer.valueOf(prop.getProperty("connection.maxtotal")));
	        DATASOURCE.setMaxIdle(Integer.valueOf(prop.getProperty("connection.maxidle")));
	        in.close();
		} catch (FileNotFoundException fnfe) {
			LOGGER.error("FileNotFoundException", fnfe);
			System.exit(1);
		} catch (IOException ioe) {
			LOGGER.error("IOException", ioe);
			System.exit(1);
		} catch (Throwable t) {
			LOGGER.error("Throwable Exception", t);
		}
	}

	/**
	 * 
	 */
	private ConnectionPool() {
		super();
	}

	/**
	 * 
	 * @return
	 */
	public static DataSource getDataSource() {
		return DATASOURCE;
	}

	/**
	 * 
	 * @return
	 */
	public static Connection getConnection() throws SQLException {
		return DATASOURCE.getConnection();
	}
}