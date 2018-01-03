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
	 * @param password
	 * @param sourcetag
	 * @param targettagset
	 * @param targettagset2 
	 * @param targettagset1 
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public AlertsResponse getReadAlerts(String userName) throws SQLException, IOException;
}