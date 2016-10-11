package com.verint.services.km.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.verint.services.km.dbcp.ConnectionPool;

import com.verint.services.km.dao.BaseDAOImpl;

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
	public String getISETResponse(String refName, String objType, String objID) throws SQLException {
		LOGGER.info("Entering getISETResponse()");
		//ISETRequest 
		
		final Connection connection = ConnectionPool.getConnection();
		PreparedStatement stmt = null;
		
		String migratableReference = "";
		
		try {
			final String query = "SELECT MIGRATABLE_REFERENCE"
					+ "FROM UHG_MigRefID2RefName"
					+ " WHERE (REFERENCE_NAME = "+refName
					+ " AND OBJECT_TYPE = "+objType+")"
					+ " OR (IQ_OBJECT_ID = "+objID
					+ " AND OBJECT_TYPE = "+objType+")";
			stmt = connection.prepareStatement(query);
			final ResultSet rs = stmt.executeQuery();
			
			migratableReference = rs.getString("MIGRATABLE_REFERENCE");
		} catch (SQLException sqle) {
			LOGGER.error("SQLException", sqle);
			throw sqle;
		}finally {
			if (stmt != null)
				stmt.close();
			if (connection != null)
				connection.close();
		}
		LOGGER.debug("getISETResponse: " + migratableReference);
		LOGGER.info("Exiting getISETResponse()");
		return migratableReference;
		
	}
	
}
