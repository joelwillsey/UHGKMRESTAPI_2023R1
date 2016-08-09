/**
 * ContentV1Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.ContentV1Service_wsdl;

public interface ContentV1Service extends javax.xml.rpc.Service {

/**
 * Externally visible APIs for KM Content
 */
    public java.lang.String getContentV1PortAddress();

    public com.kana.contactcentre.services.model.ContentV1Service_wsdl.ContentV1PortType getContentV1Port() throws javax.xml.rpc.ServiceException;

    public com.kana.contactcentre.services.model.ContentV1Service_wsdl.ContentV1PortType getContentV1Port(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
