package com.verint.services.km.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.verint.services.km.dbcp.ConnectionPool;
import com.verint.services.km.model.MigratableReferenceId;;

/**
 * @author eraygorodetskiy
 *
 */
@Repository
public class ISETDAOImpl extends BaseDAOImpl implements ISETDAO{

	private static final Logger LOGGER = LoggerFactory.getLogger(ISETDAOImpl.class);
	
	public ISETDAOImpl() {
		super();
		LOGGER.info("Entering ISETDAOImpl()");
		LOGGER.info("Exiting ISETDAOImpl()");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	}
	
	@Override
	public Set<MigratableReferenceId> getISETResponse(String refName, String objType, String objID) throws SQLException {
		LOGGER.info("Entering getISETResponse()");
		Set<MigratableReferenceId> migratableReferenceIdSet = new HashSet<MigratableReferenceId>();
		MigratableReferenceId migratableReferenceIdObj;
		// Get the connection and statement
		final Connection connection = ConnectionPool.getConnection();
		PreparedStatement stmt = null;
		
		if(refName == null || refName.length() == 0){
			refName = "NULL";
		}
		if(objType == null || objType.length() == 0){
			objType = "NULL";
		}
		if(objID == null || objID.length() == 0){
			objID = "NULL";
		}
		
		// Execute query
		try {
			final String query = "SELECT MIGRATABLE_REFERENCE"
					+ " FROM UHG_MigRefID2RefName"
					+ " WHERE (REFERENCE_NAME = '"+refName +"'"
					+ " AND OBJECT_TYPE = '"+objType+"')"
					+ " OR (IQ_OBJECT_ID = '"+objID +"'"
					+ " AND OBJECT_TYPE = '"+objType+"')";
			stmt = connection.prepareStatement(query);
			
			Instant start = Instant.now();
			final ResultSet rs = stmt.executeQuery();
			Instant end = Instant.now();
			LOGGER.debug("SQL Execution - getISETResponse() duration: " + Duration.between(start, end).toMillis() + "ms");

			
			// loop through all records
			while (rs != null && rs.next()){
				String migRefId = rs.getString("MIGRATABLE_REFERENCE");
				migratableReferenceIdObj = new MigratableReferenceId();
				migratableReferenceIdObj.setMigratableReferenceId(migRefId);
				migratableReferenceIdSet.add(migratableReferenceIdObj);
			}
			rs.close();
		} catch (SQLException sqle) {
			LOGGER.error("SQLException", sqle);
			throw sqle;
		}finally {
			if (stmt != null)
				stmt.close();
			if (connection != null)
				connection.close();
		}
		LOGGER.debug("getISETResponse: " + migratableReferenceIdSet);
		LOGGER.info("Exiting getISETResponse()");
		return migratableReferenceIdSet;
		
	}
	
}
