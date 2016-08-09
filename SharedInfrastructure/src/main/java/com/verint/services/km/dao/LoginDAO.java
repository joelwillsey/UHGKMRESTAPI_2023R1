package com.verint.services.km.dao;

import java.rmi.RemoteException;

import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.LoginRequest;
import com.verint.services.km.model.LoginResponse;

public interface LoginDAO {

	/**
	 * 
	 * @param request
	 * @return
	 */
	public LoginResponse login(LoginRequest request) throws RemoteException, AppException;
}
