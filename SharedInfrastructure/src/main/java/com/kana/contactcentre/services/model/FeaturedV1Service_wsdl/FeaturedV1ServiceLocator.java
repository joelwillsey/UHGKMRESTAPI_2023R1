/**
 * FeaturedV1ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.FeaturedV1Service_wsdl;

public class FeaturedV1ServiceLocator extends org.apache.axis.client.Service implements FeaturedV1Service {

/**
 * Undefined
 */

    public FeaturedV1ServiceLocator() {
    }


    public FeaturedV1ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public FeaturedV1ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for FeaturedV1Port
    private java.lang.String FeaturedV1Port_address = "http://127.0.0.1:8280/GTConnect/StatelessSoapAcceptor/?gtxInitialProcess=UHGFeaturedAPIServices.API.Featured.FeaturedV1";

    public java.lang.String getFeaturedV1PortAddress() {
        return FeaturedV1Port_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String FeaturedV1PortWSDDServiceName = "FeaturedV1Port";

    public java.lang.String getFeaturedV1PortWSDDServiceName() {
        return FeaturedV1PortWSDDServiceName;
    }

    public void setFeaturedV1PortWSDDServiceName(java.lang.String name) {
        FeaturedV1PortWSDDServiceName = name;
    }

    public FeaturedV1PortType getFeaturedV1Port() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(FeaturedV1Port_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getFeaturedV1Port(endpoint);
    }

    public FeaturedV1PortType getFeaturedV1Port(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            FeaturedV1BindingStub _stub = new FeaturedV1BindingStub(portAddress, this);
            _stub.setPortName(getFeaturedV1PortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setFeaturedV1PortEndpointAddress(java.lang.String address) {
        FeaturedV1Port_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (FeaturedV1PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                FeaturedV1BindingStub _stub = new FeaturedV1BindingStub(new java.net.URL(FeaturedV1Port_address), this);
                _stub.setPortName(getFeaturedV1PortWSDDServiceName());
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
        if ("FeaturedV1Port".equals(inputPortName)) {
            return getFeaturedV1Port();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/FeaturedV1Service.wsdl", "FeaturedV1Service");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/FeaturedV1Service.wsdl", "FeaturedV1Port"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("FeaturedV1Port".equals(portName)) {
            setFeaturedV1PortEndpointAddress(address);
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
