/**
 * 
 */
package com.verint.services.km.dao;

import java.io.IOException;
import java.sql.SQLException;

import com.verint.services.km.model.CrossTagResponse;

/**
 * @author jmiller
 *
 */
public interface CrossTagsDAO {

	/**
	 * 
	 * @param username
	 * @param password
	 * @param sourcetag
	 * @param targettagset
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public CrossTagResponse getTagSetConfigurations(String username, String password, String[] sourcetag, String targettagset) throws SQLException, IOException;
}