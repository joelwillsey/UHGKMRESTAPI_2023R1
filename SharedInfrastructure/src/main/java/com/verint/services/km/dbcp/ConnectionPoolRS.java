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

import com.verint.services.km.util.ConfigInfo;
import com.verint.services.km.util.PropertyUtil;

/**
 * @author jmiller
 *
 */
public class ConnectionPoolRS {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionPoolRS.class);
	private static BasicDataSource DATASOURCE = null;

	static {
		String fileLocation = PropertyUtil.getConnectionPoolRSPath();
		LOGGER.debug("Connection Pool FileLocation: " + fileLocation);
		

		
		try {
			
			ConfigInfo kmConfiguration = new ConfigInfo();
			
			// Get the properties
			final Properties prop = new Properties();

	        
	        // Create the basic data source
	        LOGGER.debug("Creating the BasicDataSource");
	        DATASOURCE = new BasicDataSource();
	        LOGGER.debug("DATASOURCE: " + DATASOURCE);
	        DATASOURCE.setDriverClassName(kmConfiguration.getConnectionRSDriverclassname());
	        DATASOURCE.setUrl(kmConfiguration.getConnectionRSUrl());
	        DATASOURCE.setUsername(kmConfiguration.getConnectionRSUsername());
	        DATASOURCE.setPassword(kmConfiguration.getConnectionRSPassword());
	        DATASOURCE.setMaxTotal(Integer.valueOf(kmConfiguration.getConnectionRSMaxtotal()));
	        DATASOURCE.setMaxIdle(Integer.valueOf(kmConfiguration.getConnectionRSMaxidle()));


		} catch (Throwable t) {
			LOGGER.error("Throwable Exception", t);
		}
	}

	/**
	 * 
	 */
	private ConnectionPoolRS() {
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