/**
 * 
 */
package com.verint.services.km.dao;

import java.rmi.RemoteException;

import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.ContentRequest;
import com.verint.services.km.model.ManageBookmarkV2Request;
import com.verint.services.km.model.ManageBookmarkV2Response;

import KMBookmarkServiceV2Service_wsdl.BookmarkFolderContent;
import KMBookmarkServiceV2Service_wsdl.BookmarkedContentV2;

/**
 * @author jmiller
 *
 */
public interface BookmarksV2DAO {

	/**
	 * 
	 * @param manageBookmarkRequest
	 * @return
	 * @throws RemoteException
	 * @throws AppException
	 */
	public ManageBookmarkV2Response addBookmark(ManageBookmarkV2Request manageBookmarkRequest) throws RemoteException, AppException;
	
	/**
	 * 
	 * @param manageBookmarkRequest
	 * @return
	 * @throws RemoteException
	 * @throws AppException
	 */
	public ManageBookmarkV2Response removeBookmark(ManageBookmarkV2Request manageBookmarkRequest) throws RemoteException, AppException;
	
	//returns folder information
	public BookmarkFolderContent getFolder(java.lang.String folderId);
	
	public BookmarkedContentV2 getBookmark(java.lang.String contentId);
	
	public BookmarkedContentV2 listAllBookmarks();
	
	/**
	 * 
	 * @param contentRequest
	 * @return
	 * @throws RemoteException
	 * @throws AppException
	 */
	
	
	public boolean isContentBookmarked(ContentRequest contentRequest) throws RemoteException, AppException;
	
	public ManageBookmarkV2Response reorderBookmarkUp(ManageBookmarkV2Request bookmarkRequest) throws RemoteException, AppException;

	public ManageBookmarkV2Response reorderFolderUp(ManageBookmarkV2Request bookmarkRequest) throws RemoteException, AppException;

	public ManageBookmarkV2Response reorderFolderDown(ManageBookmarkV2Request bookmarkRequest) throws RemoteException, AppException;

	/**
	 * 
	 * @param bookmarkRequest
	 * @return
	 * @throws RemoteException
	 * @throws AppException
	 */
	public ManageBookmarkV2Response reorderBookmarkDown(ManageBookmarkV2Request bookmarkRequest) throws RemoteException, AppException;

	
}