/**
 * 
 */
package com.verint.services.km.dao;

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
	public String rateContent(RateRequest rateRequest) throws RemoteException, AppException;
	
	/**
	 * 
	 * @param contentID
	 * @param username
	 * @param password
	 * @return
	 * @throws RemoteException
	 * @throws AppException
	 */
	public String markAsViewed(String contentID, String username, String password) throws RemoteException, AppException;
}