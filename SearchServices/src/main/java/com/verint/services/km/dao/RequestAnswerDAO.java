/**
 * 
 */
package com.verint.services.km.dao;

import java.rmi.RemoteException;

import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.RequestAnswerRequest;

/**
 * @author jmiller
 *
 */
public interface RequestAnswerDAO {

	/**
	 * 
	 * @param requestAnswer
	 * @throws RemoteException
	 * @throws AppException
	 */
	public void suggestContent(RequestAnswerRequest requestAnswer, String oidcToken) throws RemoteException, AppException;
}
