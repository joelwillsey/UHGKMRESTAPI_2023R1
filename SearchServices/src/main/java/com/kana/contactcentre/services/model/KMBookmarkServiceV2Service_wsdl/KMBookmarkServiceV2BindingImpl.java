/**
 * KMBookmarkServiceV2BindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl;

import java.rmi.RemoteException;

public class KMBookmarkServiceV2BindingImpl implements com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.KMBookmarkServiceV2PortType{
	private String _endpoint = null;
	private com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.KMBookmarkServiceV2PortType bookmarkServiceV2Port = null;

	private void _initBookmarksV2Port() {
		try {
			bookmarkServiceV2Port = (new com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.KMBookmarkServiceV2ServiceLocator()).getKMBookmarkServiceV2Port();
			if (bookmarkServiceV2Port != null) {
				if (_endpoint != null)
					((javax.xml.rpc.Stub)bookmarkServiceV2Port)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
				else
					_endpoint = (String)((javax.xml.rpc.Stub)bookmarkServiceV2Port)._getProperty("javax.xml.rpc.service.endpoint.address");
			}

		}
		catch (javax.xml.rpc.ServiceException serviceException) {}
	}

	public com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ManageBookmarksV2ResponseBodyType manageBookmarksV2(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ManageBookmarksV2RequestBodyType body) throws java.rmi.RemoteException {
		return null;
	}

	public com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ListAllBookmarksV2ResponseBodyType listAllBookmarksV2(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ListAllBookmarksV2RequestBodyType body) throws java.rmi.RemoteException {
		if (bookmarkServiceV2Port == null)
			_initBookmarksV2Port();
		return bookmarkServiceV2Port.listAllBookmarksV2(body);
	}

	public com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.GetBookmarkResponseBodyType getBookmark(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.GetBookmarkRequestBodyType body) throws java.rmi.RemoteException {
		return null;
	}

	public com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.GetFolderResponseBodyType getFolder(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.GetFolderRequestBodyType body) throws java.rmi.RemoteException {
		return null;
	}

	public com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ReorderBookmarkAndFolderResponseBodyType reorderBookmarkAndFolder(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ReorderBookmarkAndFolderRequestBodyType body) throws java.rmi.RemoteException {
		if (bookmarkServiceV2Port == null)
			_initBookmarksV2Port();
		return reorderBookmarkAndFolder(body);
	}

	@Override
	public ListAllBookmarksV2ResponseBodyType listAllV2(ListAllBookmarksV2RequestBodyType listallRequest) throws RemoteException {
		if (bookmarkServiceV2Port == null)
			_initBookmarksV2Port();
		return listAllBookmarksV2(listallRequest);
	}

}
