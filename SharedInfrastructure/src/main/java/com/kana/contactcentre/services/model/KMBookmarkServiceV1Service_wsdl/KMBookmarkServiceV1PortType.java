/**
 * KMBookmarkServiceV1PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl;

public interface KMBookmarkServiceV1PortType extends java.rmi.Remote {
    public com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.ManageBookmarkResponseBodyType manageBookmark(com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.ManageBookmarkRequestBodyType body) throws java.rmi.RemoteException;
    public com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.ListAllBookmarksResponseBodyType listAllBookmarks(com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.ListAllBookmarksRequestBodyType body) throws java.rmi.RemoteException;
    public com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.ReorderBookmarksResponseBodyType reorderBookmarks(com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.ReorderBookmarksRequestBodyType body) throws java.rmi.RemoteException;
}
