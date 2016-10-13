package com.verint.services.km.dao;

import java.util.Set;

import com.verint.services.km.model.MigratableReferenceId;

public interface ISETDAO {
	
	public Set<MigratableReferenceId> getISETResponse(String refName, String objType, String objID) throws Exception;
	
}
