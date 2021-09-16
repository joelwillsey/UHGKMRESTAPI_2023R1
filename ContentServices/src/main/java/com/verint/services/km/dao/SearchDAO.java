/**
 * 
 */
package com.verint.services.km.dao;

import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;

import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.RateRequest;

/**
 * @author jmiller
 *
 */
public interface SearchDAO {

	/**
	 * 
	 * @param rateRequest
	 * @return
	 * @throws RemoteException
	 * @throws AppException
	 */
	public String rateContent(RateRequest rateRequest) throws RemoteException, AppException, UnsupportedEncodingException;
	
	/**
	 * 
	 * @param contentID
	 * @param username
	 * @param password
	 * @return
	 * @throws RemoteException
	 * @throws AppException
	 */
	public String markAsViewed(String contentID, String version, String username, String password, String siteName, String oidcToken, String externalSearchId) throws RemoteException, AppException, UnsupportedEncodingException;
}
