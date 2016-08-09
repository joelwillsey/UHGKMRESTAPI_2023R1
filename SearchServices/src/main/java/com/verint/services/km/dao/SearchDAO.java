/**
 * 
 */
package com.verint.services.km.dao;

import java.rmi.RemoteException;

import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.SearchRequest;
import com.verint.services.km.model.SearchResponse;

/**
 * @author jmiller
 *
 */
public interface SearchDAO {

	/**
	 * 
	 * @param searchRequest
	 * @return
	 * @throws RemoteException
	 * @throws AppException
	 */
	public SearchResponse searchQuery(SearchRequest searchRequest) throws RemoteException, AppException;

	/**
	 * 
	 * @param searchRequest
	 * @return
	 * @throws RemoteException
	 * @throws AppException
	 */
	public SearchResponse searchFeatured(SearchRequest searchRequest) throws RemoteException, AppException;

	/**
	 * 
	 * @param searchRequest
	 * @return
	 * @throws RemoteException
	 * @throws AppException
	 */
	public SearchResponse searchTopContent(SearchRequest searchRequest) throws RemoteException, AppException;

	/**
	 * 
	 * @param searchRequest
	 * @return
	 * @throws RemoteException
	 * @throws AppException
	 */
	public SearchResponse searchSuggestions(SearchRequest searchRequest) throws RemoteException, AppException;

	/**
	 * 
	 * @param searchRequest
	 * @return
	 * @throws RemoteException
	 * @throws AppException
	 */
	public SearchResponse searchBookmarks(SearchRequest searchRequest) throws RemoteException, AppException;
}