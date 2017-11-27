/**
 * KMBookmarkServiceV2PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl;

public interface KMBookmarkServiceV2PortType extends java.rmi.Remote {
    public com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ManageBookmarksV2ResponseBodyType manageBookmarksV2(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ManageBookmarksV2RequestBodyType body) throws java.rmi.RemoteException;
    public com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ListAllBookmarksV2ResponseBodyType listAllBookmarksV2(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ListAllBookmarksV2RequestBodyType body) throws java.rmi.RemoteException;
    public com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.GetBookmarkResponseBodyType getBookmark(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.GetBookmarkRequestBodyType body) throws java.rmi.RemoteException;
    public com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.GetFolderResponseBodyType getFolder(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.GetFolderRequestBodyType body) throws java.rmi.RemoteException;
    public com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ReorderBookmarkAndFolderResponseBodyType reorderBookmarkAndFolder(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ReorderBookmarkAndFolderRequestBodyType body) throws java.rmi.RemoteException;
}
