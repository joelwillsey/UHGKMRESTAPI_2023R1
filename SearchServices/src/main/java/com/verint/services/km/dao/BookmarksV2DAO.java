/**
 * 
 */
package com.verint.services.km.dao;

import java.rmi.RemoteException;

import com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.BookmarkFolderContent;
import com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.BookmarkedContentV2;

import com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ManageBookmarksV2RequestBodyType;
import com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ManageBookmarksV2ResponseBodyType;
import com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ReorderBookmarkAndFolderRequestBodyType;
import com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ReorderBookmarkAndFolderResponseBodyType;
import com.verint.services.km.errorhandling.AppException;
import com.verint.services.km.model.ContentRequest;
import com.verint.services.km.model.ListAllBookmarksV2Request;
import com.verint.services.km.model.ListAllBookmarksV2Response;
import com.verint.services.km.model.ManageBookmarkV2Request;
import com.verint.services.km.model.ManageBookmarkV2Response;
import com.verint.services.km.model.ReorderBookmarkAndFolderRequest;
import com.verint.services.km.model.ReorderBookmarkAndFolderResponse;

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
	public ManageBookmarksV2ResponseBodyType addBookmark(ManageBookmarksV2RequestBodyType manageBookmarkRequest) throws RemoteException, AppException;
	
	/**
	 * 
	 * @param bookmarkRequest
	 * @return
	 * @throws RemoteException
	 * @throws AppException
	 */	
	public ManageBookmarksV2ResponseBodyType addFolder(ManageBookmarksV2RequestBodyType manageBookmarkRequest) throws RemoteException, AppException;
	
	/**
	 * 
	 * @param bookmarkRequest
	 * @return
	 * @throws RemoteException
	 * @throws AppException
	 */	
	public ManageBookmarksV2ResponseBodyType removeFolder(ManageBookmarksV2RequestBodyType manageBookmarkRequest) throws RemoteException, AppException;
	
	/**
	 * 
	 * @param manageBookmarkRequest
	 * @return
	 * @throws RemoteException
	 * @throws AppException
	 */
	public ManageBookmarksV2ResponseBodyType removeBookmark(ManageBookmarksV2RequestBodyType manageBookmarkRequest) throws RemoteException, AppException;
	
	/**
	 * 
	 * @param bookmarkRequest
	 * @return
	 * @throws RemoteException
	 * @throws AppException
	 */	
	public BookmarkFolderContent getFolder(java.lang.String folderId);
	
	/**
	 * 
	 * @param bookmarkRequest
	 * @return
	 * @throws RemoteException
	 * @throws AppException
	 */	
	public BookmarkedContentV2 getBookmark(java.lang.String contentId);
		
	/**
	 * 
	 * @param contentRequest
	 * @return
	 * @throws RemoteException
	 * @throws AppException
	 */
	public boolean isContentBookmarked(ContentRequest contentRequest) throws RemoteException, AppException;
	
	/**
	 * 
	 * @param bookmarkRequest
	 * @return
	 * @throws RemoteException
	 * @throws AppException
	 */	
	public ReorderBookmarkAndFolderResponseBodyType reorderBookmarkUp(ReorderBookmarkAndFolderRequestBodyType bookmarkRequest) throws RemoteException, AppException;
	public ReorderBookmarkAndFolderResponse reorderBookmark(ReorderBookmarkAndFolderRequest bookmarkRequest) throws RemoteException, AppException;
	public ManageBookmarkV2Response manageBookmarksV2(ManageBookmarkV2Request bookmarkRequest) throws RemoteException, AppException;
	/**
	 * 
	 * @param bookmarkRequest
	 * @return
	 * @throws RemoteException
	 * @throws AppException
	 */	
	public ReorderBookmarkAndFolderResponseBodyType reorderFolderUp(ReorderBookmarkAndFolderRequestBodyType bookmarkRequest) throws RemoteException, AppException;

	/**
	 * 
	 * @param bookmarkRequest
	 * @return
	 * @throws RemoteException
	 * @throws AppException
	 */	
	public ReorderBookmarkAndFolderResponseBodyType reorderFolderDown(ReorderBookmarkAndFolderRequestBodyType bookmarkRequest) throws RemoteException, AppException;
	
	/**
	 * 
	 * @param bookmarkRequest
	 * @return
	 * @throws RemoteException
	 * @throws AppException
	 */	
	public ListAllBookmarksV2Response listAllBookmarksV2(ListAllBookmarksV2Request ListAllBookmarksV2Request) throws RemoteException, AppException;
	
	/**
	 * 
	 * @param bookmarkRequest
	 * @return
	 * @throws RemoteException
	 * @throws AppException
	 */
	public ReorderBookmarkAndFolderResponseBodyType reorderBookmarkDown(ReorderBookmarkAndFolderRequestBodyType bookmarkRequest) throws RemoteException, AppException;

	
}