/**
 * 
 */
package com.verint.services.km.dao;

import java.io.IOException;
import java.sql.SQLException;

import com.verint.services.km.model.AlertsResponse;


/**
 * @author jmiller
 *
 */
public interface NewAlertsDAO {

	/**
	 * 
	 * @param username 
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public AlertsResponse getReadAlerts(String userName) throws SQLException, IOException;
	public void recordReadStatus(String content_id, String migRefId, String userName) throws SQLException, IOException;
		
}