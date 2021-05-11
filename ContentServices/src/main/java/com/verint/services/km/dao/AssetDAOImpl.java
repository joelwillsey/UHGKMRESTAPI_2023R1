package com.verint.services.km.dao;

import com.verint.services.km.util.RestUtil;
import com.verint.services.km.errorhandling.AppException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.rmi.RemoteException;

@Repository
public class AssetDAOImpl extends BaseDAOImpl implements AssetDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssetDAOImpl.class);
	
    @Override
    public ResponseEntity<String> getAsset(String assetUrl, String oidcToken) throws RemoteException, AppException {
    	LOGGER.debug("getAsset():  " + assetUrl);
        return RestUtil.getRestResponse(assetUrl, null, HttpMethod.GET, String.class, oidcToken, null, true);
    }
}
