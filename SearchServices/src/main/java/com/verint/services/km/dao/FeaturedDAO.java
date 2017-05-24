package com.verint.services.km.dao;

import java.rmi.RemoteException;

import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.FeaturedRequest;
import com.verint.services.km.model.FeaturedResponse;
/**
 * @author HBendale
 *
 */
public interface FeaturedDAO {

	FeaturedResponse featuredQuery(FeaturedRequest featuredRequest)
			throws RemoteException, AppException;

	
}
