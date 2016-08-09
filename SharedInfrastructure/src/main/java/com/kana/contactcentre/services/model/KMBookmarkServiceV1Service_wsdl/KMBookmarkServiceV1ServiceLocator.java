/**
 * KMBookmarkServiceV1ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl;

public class KMBookmarkServiceV1ServiceLocator extends org.apache.axis.client.Service implements com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.KMBookmarkServiceV1Service {

/**
 * Soap Service to expose API(s) to manage bookmarks.
 */

    public KMBookmarkServiceV1ServiceLocator() {
    }


    public KMBookmarkServiceV1ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public KMBookmarkServiceV1ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for KMBookmarkServiceV1Port
    private java.lang.String KMBookmarkServiceV1Port_address = "http://10.211.55.8:8280/GTConnect/StatelessSoapAcceptor/?gtxInitialProcess=AddKnowContentServices.API.BookmarkService.KMBookmarkServiceV1";

    public java.lang.String getKMBookmarkServiceV1PortAddress() {
        return KMBookmarkServiceV1Port_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String KMBookmarkServiceV1PortWSDDServiceName = "KMBookmarkServiceV1Port";

    public java.lang.String getKMBookmarkServiceV1PortWSDDServiceName() {
        return KMBookmarkServiceV1PortWSDDServiceName;
    }

    public void setKMBookmarkServiceV1PortWSDDServiceName(java.lang.String name) {
        KMBookmarkServiceV1PortWSDDServiceName = name;
    }

    public com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.KMBookmarkServiceV1PortType getKMBookmarkServiceV1Port() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(KMBookmarkServiceV1Port_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getKMBookmarkServiceV1Port(endpoint);
    }

    public com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.KMBookmarkServiceV1PortType getKMBookmarkServiceV1Port(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.KMBookmarkServiceV1BindingStub _stub = new com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.KMBookmarkServiceV1BindingStub(portAddress, this);
            _stub.setPortName(getKMBookmarkServiceV1PortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setKMBookmarkServiceV1PortEndpointAddress(java.lang.String address) {
        KMBookmarkServiceV1Port_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.KMBookmarkServiceV1PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.KMBookmarkServiceV1BindingStub _stub = new com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.KMBookmarkServiceV1BindingStub(new java.net.URL(KMBookmarkServiceV1Port_address), this);
                _stub.setPortName(getKMBookmarkServiceV1PortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("KMBookmarkServiceV1Port".equals(inputPortName)) {
            return getKMBookmarkServiceV1Port();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV1Service.wsdl", "KMBookmarkServiceV1Service");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV1Service.wsdl", "KMBookmarkServiceV1Port"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("KMBookmarkServiceV1Port".equals(portName)) {
            setKMBookmarkServiceV1PortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
