/**
 * 
 */
package com.verint.services.km.dao;

import java.rmi.RemoteException;

import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.FeedbackRequest;
import com.verint.services.km.model.FeedbackResponse;

/**
 * @author jmiller
 *
 */
public interface FeedbackDAO {

	/**
	 * 
	 * @param feedbackRequest
	 * @return
	 * @throws RemoteException
	 * @throws AppException
	 */
	public FeedbackResponse feedback(FeedbackRequest feedbackRequest) throws RemoteException, AppException;
}