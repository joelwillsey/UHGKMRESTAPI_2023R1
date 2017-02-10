/**
 * LoginV2ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.LoginV2Service_wsdl;

public class LoginV2ServiceLocator extends org.apache.axis.client.Service implements com.kana.contactcentre.services.model.LoginV2Service_wsdl.LoginV2Service {

/**
 * Undefined
 */

    public LoginV2ServiceLocator() {
    }


    public LoginV2ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public LoginV2ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for LoginV2Port
    private java.lang.String LoginV2Port_address = "http://127.0.0.1:8280/GTConnect/StatelessSoapAcceptor/?gtxInitialProcess=UHGAddKnowledgeUserLoginAPIServices.API.LoginV2.LoginV2";

    public java.lang.String getLoginV2PortAddress() {
        return LoginV2Port_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String LoginV2PortWSDDServiceName = "LoginV2Port";

    public java.lang.String getLoginV2PortWSDDServiceName() {
        return LoginV2PortWSDDServiceName;
    }

    public void setLoginV2PortWSDDServiceName(java.lang.String name) {
        LoginV2PortWSDDServiceName = name;
    }

    public com.kana.contactcentre.services.model.LoginV2Service_wsdl.LoginV2PortType getLoginV2Port() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(LoginV2Port_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getLoginV2Port(endpoint);
    }

    public com.kana.contactcentre.services.model.LoginV2Service_wsdl.LoginV2PortType getLoginV2Port(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.kana.contactcentre.services.model.LoginV2Service_wsdl.LoginV2BindingStub _stub = new com.kana.contactcentre.services.model.LoginV2Service_wsdl.LoginV2BindingStub(portAddress, this);
            _stub.setPortName(getLoginV2PortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setLoginV2PortEndpointAddress(java.lang.String address) {
        LoginV2Port_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.kana.contactcentre.services.model.LoginV2Service_wsdl.LoginV2PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.kana.contactcentre.services.model.LoginV2Service_wsdl.LoginV2BindingStub _stub = new com.kana.contactcentre.services.model.LoginV2Service_wsdl.LoginV2BindingStub(new java.net.URL(LoginV2Port_address), this);
                _stub.setPortName(getLoginV2PortWSDDServiceName());
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
        if ("LoginV2Port".equals(inputPortName)) {
            return getLoginV2Port();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://localhost:80/LoginV2Service.wsdl", "LoginV2Service");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://localhost:80/LoginV2Service.wsdl", "LoginV2Port"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("LoginV2Port".equals(portName)) {
            setLoginV2PortEndpointAddress(address);
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
