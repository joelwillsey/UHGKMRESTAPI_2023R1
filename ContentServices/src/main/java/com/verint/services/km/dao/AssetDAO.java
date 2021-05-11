package com.verint.services.km.dao;

import com.verint.services.km.errorhandling.AppException;
import org.springframework.http.ResponseEntity;

import java.rmi.RemoteException;

public interface AssetDAO {
    public ResponseEntity<String> getAsset(String assetUrl, String oidcToken) throws RemoteException, AppException;
}
