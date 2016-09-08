package com.verint.services.km.dao;

import java.rmi.RemoteException;

import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.NewOrChangedRequest;
import com.verint.services.km.model.NewOrChangedResponse;
/**
 * @author ERaygorodetskiy
 *
 */
public interface NewOrChangedDAO {

	NewOrChangedResponse newOrChangedQuery(NewOrChangedRequest newOrChangedRequest)
			throws RemoteException, AppException;

	
}
