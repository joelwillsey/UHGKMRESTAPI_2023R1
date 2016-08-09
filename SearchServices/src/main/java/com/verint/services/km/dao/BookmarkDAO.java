/**
 * 
 */
package com.verint.services.km.dao;

import java.rmi.RemoteException;

import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.ManageBookmarkRequest;
import com.verint.services.km.model.ManageBookmarkResponse;

/**
 * @author jmiller
 *
 */
public interface BookmarkDAO {
	/**
	 * 
	 * @param bookmarkRequest
	 * @return
	 * @throws RemoteException
	 * @throws AppException
	 */
	public ManageBookmarkResponse addBookmark(ManageBookmarkRequest bookmarkRequest) throws RemoteException, AppException;

	/**
	 * 
	 * @param bookmarkRequest
	 * @return
	 * @throws RemoteException
	 * @throws AppException
	 */
	public ManageBookmarkResponse removeBookmark(ManageBookmarkRequest bookmarkRequest) throws RemoteException, AppException;

	/**
	 * 
	 * @param bookmarkRequest
	 * @return
	 * @throws RemoteException
	 * @throws AppException
	 */
	public ManageBookmarkResponse reorderBookmarkUp(ManageBookmarkRequest bookmarkRequest) throws RemoteException, AppException;

	/**
	 * 
	 * @param bookmarkRequest
	 * @return
	 * @throws RemoteException
	 * @throws AppException
	 */
	public ManageBookmarkResponse reorderBookmarkDown(ManageBookmarkRequest bookmarkRequest) throws RemoteException, AppException;
}