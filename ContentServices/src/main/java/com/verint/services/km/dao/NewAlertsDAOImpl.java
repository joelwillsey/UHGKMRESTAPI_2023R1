/**
 * 
 */
package com.verint.services.km.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.verint.services.km.dbcp.ConnectionPoolRS;

import com.verint.services.km.model.AlertsResponse;
import com.verint.services.km.model.ReadAlert;
import com.verint.services.km.model.RecordReadResponse;
import com.verint.services.km.util.ConfigInfo;


/**
 * @author art
 *
 */
@Repository
public class NewAlertsDAOImpl extends BaseDAOImpl implements NewAlertsDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(NewAlertsDAOImpl.class);
	
	//@Autowired
	
	/**
	 * 
	 */
	public NewAlertsDAOImpl() {
		super();
		LOGGER.info("Entering NewAlertsDAOImpl()");
		LOGGER.info("Exiting NewAlertsDAOImpl()");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.verint.services.km.dao.CrossTagsDAO#getTagSetConfigurations(java.lang.String, java.lang.String, java.lang.String[], java.lang.String)
	 */
	@Override
	public AlertsResponse getReadAlerts(String userName) throws SQLException, IOException {
		LOGGER.info("Entering getReadAlerts()");
		LOGGER.debug("userName: " + userName);
		ConfigInfo prop = new ConfigInfo();
		Integer numDays = Integer.parseInt(prop.getalertsNumDays());

		final AlertsResponse response = new AlertsResponse();
		
		// Get the connection and statement
		final Connection connection = ConnectionPoolRS.getConnection();
		PreparedStatement stmt = null;

		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DATE, -(numDays));
			long dateBeforeXDays = cal.getTimeInMillis();
		
				final String query = "SELECT a.content_id, a.migratable_reference, a.first_viewed_date from UHG_READ_ALERT a where a.USERNAME = ? and a.first_viewed_date > ?";
				stmt = connection.prepareStatement(query);
				stmt.setString(1, userName);
				stmt.setDate(2, new java.sql.Date(dateBeforeXDays));

				// Execute query
				Instant start = Instant.now();
				final ResultSet rs = stmt.executeQuery();
				Instant end = Instant.now();
				LOGGER.debug("SERVICE_CALL_PERFORMANCE("+userName+") - getReadAlerts() duration: " + Duration.between(start, end).toMillis() + "ms");

				String contentId = "";
				String migRefId = "";
				String firstViewedDate = "";
				ReadAlert temp; 
				// loop through all records
				while (rs != null && rs.next()) {
					temp = new ReadAlert();
					 contentId = rs.getString("content_id");
					 migRefId = rs.getString("migratable_reference");
					 firstViewedDate = rs.getString("first_viewed_date");
					 temp.setContentID(contentId);
					temp.setMigRefID(migRefId);
					temp.setAccessDate(firstViewedDate);
					response.addReadAlert(temp);
					 
				}
				rs.close();
			
		} catch (SQLException sqle) {
			LOGGER.error("SQLException", sqle);
			throw sqle;
		} finally {
			if (stmt != null)
				stmt.close();
			if (connection != null)
				connection.close();
		}
		
		LOGGER.debug("NewAlertsResponse: "  + response);
		LOGGER.info("Exiting getReadAlerts()");
		return response;
	
	}
	
	
	public RecordReadResponse recordReadStatus(String content_id, String migRefId, String userName) throws SQLException, IOException {
		LOGGER.info("Entering recordReadStatus()");
		LOGGER.debug("userName: " + userName);

	
		// Get the connection and statement
		final Connection connection = ConnectionPoolRS.getConnection();
		final RecordReadResponse response = new RecordReadResponse();
		PreparedStatement stmt = null;
		DateTime today = new DateTime();
		today = DateTime.now();
		try {
			
			final String query = "SELECT NEXT_ID from RES_NEXT_ID_HOLDER where TABLE_NAME = 'UHG_READ_ALERT'";
			stmt = connection.prepareStatement(query);
			Instant start = Instant.now();
			final ResultSet results = stmt.executeQuery();
			
			Instant end = Instant.now();
			LOGGER.debug("Getting ID - getReadAlerts() duration: " + Duration.between(start, end).toMillis() + "ms");
			Integer nextId = 0;
			//there should only be one result hereS
			while (results != null && results.next()) {
				 nextId = results.getInt("NEXT_ID");
			}
			LOGGER.debug("nextId: " + nextId);
			results.close();
			stmt.close();
			final String updateIDstatement = "UPDATE RES_NEXT_ID_HOLDER SET NEXT_ID = (?) WHERE TABLE_NAME = 'UHG_READ_ALERT'";
			PreparedStatement stmt2 = connection.prepareStatement(updateIDstatement);
			stmt2.setInt(1, nextId + 1);
			final Boolean updateID = stmt2.execute();
			stmt2.close();
				final String statement = "INSERT INTO UHG_READ_ALERT (CONTENT_ID, MIGRATABLE_REFERENCE, FIRST_VIEWED_DATE, USERNAME, ID) VALUES (?, ?, ?, ?, ?)";
				PreparedStatement stmt3 = connection.prepareStatement(statement);
				stmt3.setString(1, content_id);
				stmt3.setString(2, migRefId);
				stmt3.setDate(3, new java.sql.Date(today.getMillis()));  //new java.sql.Date(today.getTime()));
				stmt3.setString(4, userName);
				stmt3.setInt(5, nextId);
				start = Instant.now();
				final Boolean rs = stmt3.execute();
				
				end = Instant.now();
				LOGGER.debug("SERVICE_CALL_PERFORMANCE("+userName+") - getReadAlerts() duration: " + Duration.between(start, end).toMillis() + "ms");
				response.setdidComplete(true);
				stmt3.close();
	
	} catch (SQLException sqle) {
		LOGGER.error("SQLException", sqle);
		response.setdidComplete(false);
		throw sqle;
	} finally {
		if (stmt != null)
			stmt.close();
		if (connection != null)
			connection.close();
	}
	
	return response;
	}
}
	
