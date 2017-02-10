package com.verint.services.km.dao;

import java.rmi.RemoteException;

import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.LoginV2Request;
import com.verint.services.km.model.LoginV2Response;

public interface LoginV2DAO {

	/**
	 * 
	 * @param request
	 * @return
	 */
	public LoginV2Response login(LoginV2Request request) throws RemoteException, AppException;
}
