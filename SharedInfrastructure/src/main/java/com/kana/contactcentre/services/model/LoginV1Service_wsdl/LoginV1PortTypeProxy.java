package com.kana.contactcentre.services.model.LoginV1Service_wsdl;

public class LoginV1PortTypeProxy implements com.kana.contactcentre.services.model.LoginV1Service_wsdl.LoginV1PortType {
  private String _endpoint = null;
  private com.kana.contactcentre.services.model.LoginV1Service_wsdl.LoginV1PortType loginV1PortType = null;
  
  public LoginV1PortTypeProxy() {
    _initLoginV1PortTypeProxy();
  }
  
  public LoginV1PortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initLoginV1PortTypeProxy();
  }
  
  private void _initLoginV1PortTypeProxy() {
    try {
      loginV1PortType = (new com.kana.contactcentre.services.model.LoginV1Service_wsdl.LoginV1ServiceLocator()).getLoginV1Port();
      if (loginV1PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)loginV1PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)loginV1PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (loginV1PortType != null)
      ((javax.xml.rpc.Stub)loginV1PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.kana.contactcentre.services.model.LoginV1Service_wsdl.LoginV1PortType getLoginV1PortType() {
    if (loginV1PortType == null)
      _initLoginV1PortTypeProxy();
    return loginV1PortType;
  }
  
  public com.kana.contactcentre.services.model.LoginV1Service_wsdl.LoginUserResponseBodyType loginUser(com.kana.contactcentre.services.model.LoginV1Service_wsdl.LoginUserRequestBodyType body) throws java.rmi.RemoteException{
    if (loginV1PortType == null)
      _initLoginV1PortTypeProxy();
    return loginV1PortType.loginUser(body);
  }
  
  
}