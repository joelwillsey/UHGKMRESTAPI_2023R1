/**
 * SearchV1PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.SearchV1Service_wsdl;

public interface SearchV1PortType extends java.rmi.Remote {
    public com.kana.contactcentre.services.model.SearchV1Service_wsdl.MarkAsViewedResponseBodyType markAsViewed(com.kana.contactcentre.services.model.SearchV1Service_wsdl.MarkAsViewedRequestBodyType body) throws java.rmi.RemoteException;
    public com.kana.contactcentre.services.model.SearchV1Service_wsdl.RateResponseBodyType rate(com.kana.contactcentre.services.model.SearchV1Service_wsdl.RateRequestBodyType body) throws java.rmi.RemoteException;
    public com.kana.contactcentre.services.model.SearchV1Service_wsdl.SharedTextSearchResponseBodyType sharedTextSearch(com.kana.contactcentre.services.model.SearchV1Service_wsdl.SharedTextSearchRequestBodyType body) throws java.rmi.RemoteException;
    public com.kana.contactcentre.services.model.SearchV1Service_wsdl.MarkAsFeaturedResponseBodyType markAsFeatured(com.kana.contactcentre.services.model.SearchV1Service_wsdl.MarkAsFeaturedRequestBodyType body) throws java.rmi.RemoteException;
    public com.kana.contactcentre.services.model.SearchV1Service_wsdl.GetFeaturedContentResponseBodyType getFeaturedContent(com.kana.contactcentre.services.model.SearchV1Service_wsdl.GetFeaturedContentRequestBodyType body) throws java.rmi.RemoteException;
    public com.kana.contactcentre.services.model.SearchV1Service_wsdl.GetTopContentResponseBodyType getTopContent(com.kana.contactcentre.services.model.SearchV1Service_wsdl.GetTopContentRequestBodyType body) throws java.rmi.RemoteException;
}
