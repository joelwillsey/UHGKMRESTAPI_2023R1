/**
 * NewOrChangedV1ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.NewOrChangedV1Service_wsdl;

public class NewOrChangedV1ServiceLocator extends org.apache.axis.client.Service implements com.kana.contactcentre.services.model.NewOrChangedV1Service_wsdl.NewOrChangedV1Service {

/**
 * Undefined
 */

    public NewOrChangedV1ServiceLocator() {
    }


    public NewOrChangedV1ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public NewOrChangedV1ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for NewOrChangedV1Port
    private java.lang.String NewOrChangedV1Port_address = "http://127.0.0.1:8280/GTConnect/StatelessSoapAcceptor/?gtxInitialProcess=UHGAddKnowNewOrChangedAPIServices.API.NewOrChanged.NewOrChangedV1";

    public java.lang.String getNewOrChangedV1PortAddress() {
        return NewOrChangedV1Port_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String NewOrChangedV1PortWSDDServiceName = "NewOrChangedV1Port";

    public java.lang.String getNewOrChangedV1PortWSDDServiceName() {
        return NewOrChangedV1PortWSDDServiceName;
    }

    public void setNewOrChangedV1PortWSDDServiceName(java.lang.String name) {
        NewOrChangedV1PortWSDDServiceName = name;
    }

    public com.kana.contactcentre.services.model.NewOrChangedV1Service_wsdl.NewOrChangedV1PortType getNewOrChangedV1Port() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(NewOrChangedV1Port_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getNewOrChangedV1Port(endpoint);
    }

    public com.kana.contactcentre.services.model.NewOrChangedV1Service_wsdl.NewOrChangedV1PortType getNewOrChangedV1Port(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.kana.contactcentre.services.model.NewOrChangedV1Service_wsdl.NewOrChangedV1BindingStub _stub = new com.kana.contactcentre.services.model.NewOrChangedV1Service_wsdl.NewOrChangedV1BindingStub(portAddress, this);
            _stub.setPortName(getNewOrChangedV1PortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setNewOrChangedV1PortEndpointAddress(java.lang.String address) {
        NewOrChangedV1Port_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.kana.contactcentre.services.model.NewOrChangedV1Service_wsdl.NewOrChangedV1PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.kana.contactcentre.services.model.NewOrChangedV1Service_wsdl.NewOrChangedV1BindingStub _stub = new com.kana.contactcentre.services.model.NewOrChangedV1Service_wsdl.NewOrChangedV1BindingStub(new java.net.URL(NewOrChangedV1Port_address), this);
                _stub.setPortName(getNewOrChangedV1PortWSDDServiceName());
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
        if ("NewOrChangedV1Port".equals(inputPortName)) {
            return getNewOrChangedV1Port();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/NewOrChangedV1Service.wsdl", "NewOrChangedV1Service");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/NewOrChangedV1Service.wsdl", "NewOrChangedV1Port"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("NewOrChangedV1Port".equals(portName)) {
            setNewOrChangedV1PortEndpointAddress(address);
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
