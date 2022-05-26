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


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
		Boolean existing = false;
		try {
			final String qcheck = "SELECT * FROM UHG_READ_ALERT where USERNAME = ? and CONTENT_ID = ? and MIGRATABLE_REFERENCE = ?";
			PreparedStatement stmt0 = connection.prepareStatement(qcheck);
			stmt0.setString(1, userName);
			stmt0.setString(2, content_id);
			stmt0.setString(3, migRefId);
			final ResultSet checkResults = stmt0.executeQuery();
			
			while (checkResults != null && checkResults.next()){
				// the record already exists and didn't get caught by the earlier logic check - might occur if
				// the record is older than the alertNumDays setting
				existing = true;
				LOGGER.debug("Record exists, canceling recordReadStatus operation.");
			}
			
			checkResults.close();
			stmt0.close();
			
			if (!existing){
				
				Instant start = Instant.now();
				Instant end = Instant.now();

				final String statement = "INSERT INTO UHG_READ_ALERT (CONTENT_ID, MIGRATABLE_REFERENCE, FIRST_VIEWED_DATE, USERNAME, ID) VALUES (?, ?, CURRENT_TIMESTAMP, ?, (select MAX(ID)+1 from UHG_READ_ALERT))";
				PreparedStatement stmt3 = connection.prepareStatement(statement);
				stmt3.setString(1, content_id);
				stmt3.setString(2, migRefId);
				stmt3.setString(3, userName);
				//stmt3.setInt(4, nextId);
				start = Instant.now();
				final Boolean rs = stmt3.execute();

				end = Instant.now();
				LOGGER.debug("SERVICE_CALL_PERFORMANCE("+userName+") - recordReadStatus() duration: " + Duration.between(start, end).toMillis() + "ms");
				response.setdidComplete(rs);
				stmt3.close();
			} 

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

