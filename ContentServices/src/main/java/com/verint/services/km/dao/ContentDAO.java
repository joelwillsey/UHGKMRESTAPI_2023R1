/**
 * 
 */
package com.verint.services.km.dao;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.ContentRequest;
import com.verint.services.km.model.ContentResponse;
import com.verint.services.km.model.ContentVersionRequest;
import com.verint.services.km.model.ContentVersionResponse;

/**
 * @author jmiller
 *
 */
public interface ContentDAO {

	/**
	 * 
	 * @param contentRequest
	 * @return
	 * @throws RemoteException
	 * @throws IOException
	 * @throws SQLException
	 * @throws AppException
	 */
	//public ContentVersionResponse getContentVersion(ContentVersionRequest contentVersionRequest) throws AppException;	

	public ContentResponse getContent(ContentRequest contentRequest) throws RemoteException, IOException, SQLException, AppException;

	public ContentVersionResponse getContentVersion(ContentVersionRequest contentVersionRequest) throws AppException, RemoteException;	
}