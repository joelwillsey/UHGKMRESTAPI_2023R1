/**
 * 
 */
package com.verint.services.km.dao;

import java.rmi.RemoteException;

import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.ContentRequest;
import com.verint.services.km.model.ManageBookmarkRequest;
import com.verint.services.km.model.ManageBookmarkResponse;

/**
 * @author jmiller
 *
 */
public interface BookmarksDAO {

	/**
	 * 
	 * @param manageBookmarkRequest
	 * @return
	 * @throws RemoteException
	 * @throws AppException
	 */
	public ManageBookmarkResponse addBookmark(ManageBookmarkRequest manageBookmarkRequest) throws RemoteException, AppException;
	
	/**
	 * 
	 * @param manageBookmarkRequest
	 * @return
	 * @throws RemoteException
	 * @throws AppException
	 */
	public ManageBookmarkResponse removeBookmark(ManageBookmarkRequest manageBookmarkRequest) throws RemoteException, AppException;
	
	/**
	 * 
	 * @param contentRequest
	 * @return
	 * @throws RemoteException
	 * @throws AppException
	 */
	public boolean isContentBookmarked(ContentRequest contentRequest) throws RemoteException, AppException;
}