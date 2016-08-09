/**
 * TagV1Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.TagV1Service_wsdl;

public interface TagV1Service extends javax.xml.rpc.Service {

/**
 * Provides web services to interact with tags
 */
    public java.lang.String getTagV1PortAddress();

    public com.kana.contactcentre.services.model.TagV1Service_wsdl.TagV1PortType getTagV1Port() throws javax.xml.rpc.ServiceException;

    public com.kana.contactcentre.services.model.TagV1Service_wsdl.TagV1PortType getTagV1Port(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
