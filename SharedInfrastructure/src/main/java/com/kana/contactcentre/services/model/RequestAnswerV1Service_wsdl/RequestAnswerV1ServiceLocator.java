/**
 * RequestAnswerV1ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.RequestAnswerV1Service_wsdl;

public class RequestAnswerV1ServiceLocator extends org.apache.axis.client.Service implements com.kana.contactcentre.services.model.RequestAnswerV1Service_wsdl.RequestAnswerV1Service {

/**
 * Undefined
 */

    public RequestAnswerV1ServiceLocator() {
    }


    public RequestAnswerV1ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public RequestAnswerV1ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for RequestAnswerV1Port
    private java.lang.String RequestAnswerV1Port_address = "http://10.211.55.4:8280/GTConnect/StatelessSoapAcceptor/?gtxInitialProcess=AddKnowRequestAnswerAPIServices.API.RequestAnswer.RequestAnswerV1";

    public java.lang.String getRequestAnswerV1PortAddress() {
        return RequestAnswerV1Port_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String RequestAnswerV1PortWSDDServiceName = "RequestAnswerV1Port";

    public java.lang.String getRequestAnswerV1PortWSDDServiceName() {
        return RequestAnswerV1PortWSDDServiceName;
    }

    public void setRequestAnswerV1PortWSDDServiceName(java.lang.String name) {
        RequestAnswerV1PortWSDDServiceName = name;
    }

    public com.kana.contactcentre.services.model.RequestAnswerV1Service_wsdl.RequestAnswerV1PortType getRequestAnswerV1Port() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(RequestAnswerV1Port_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getRequestAnswerV1Port(endpoint);
    }

    public com.kana.contactcentre.services.model.RequestAnswerV1Service_wsdl.RequestAnswerV1PortType getRequestAnswerV1Port(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.kana.contactcentre.services.model.RequestAnswerV1Service_wsdl.RequestAnswerV1BindingStub _stub = new com.kana.contactcentre.services.model.RequestAnswerV1Service_wsdl.RequestAnswerV1BindingStub(portAddress, this);
            _stub.setPortName(getRequestAnswerV1PortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setRequestAnswerV1PortEndpointAddress(java.lang.String address) {
        RequestAnswerV1Port_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.kana.contactcentre.services.model.RequestAnswerV1Service_wsdl.RequestAnswerV1PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.kana.contactcentre.services.model.RequestAnswerV1Service_wsdl.RequestAnswerV1BindingStub _stub = new com.kana.contactcentre.services.model.RequestAnswerV1Service_wsdl.RequestAnswerV1BindingStub(new java.net.URL(RequestAnswerV1Port_address), this);
                _stub.setPortName(getRequestAnswerV1PortWSDDServiceName());
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
        if ("RequestAnswerV1Port".equals(inputPortName)) {
            return getRequestAnswerV1Port();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/RequestAnswerV1Service.wsdl", "RequestAnswerV1Service");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/RequestAnswerV1Service.wsdl", "RequestAnswerV1Port"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("RequestAnswerV1Port".equals(portName)) {
            setRequestAnswerV1PortEndpointAddress(address);
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
