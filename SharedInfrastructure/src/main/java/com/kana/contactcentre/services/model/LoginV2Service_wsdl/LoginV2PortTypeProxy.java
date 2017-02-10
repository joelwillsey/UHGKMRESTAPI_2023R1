package com.kana.contactcentre.services.model.LoginV2Service_wsdl;

public class LoginV2PortTypeProxy implements com.kana.contactcentre.services.model.LoginV2Service_wsdl.LoginV2PortType {
  private String _endpoint = null;
  private com.kana.contactcentre.services.model.LoginV2Service_wsdl.LoginV2PortType loginV2PortType = null;
  
  public LoginV2PortTypeProxy() {
    _initLoginV2PortTypeProxy();
  }
  
  public LoginV2PortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initLoginV2PortTypeProxy();
  }
  
  private void _initLoginV2PortTypeProxy() {
    try {
      loginV2PortType = (new com.kana.contactcentre.services.model.LoginV2Service_wsdl.LoginV2ServiceLocator()).getLoginV2Port();
      if (loginV2PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)loginV2PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)loginV2PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (loginV2PortType != null)
      ((javax.xml.rpc.Stub)loginV2PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.kana.contactcentre.services.model.LoginV2Service_wsdl.LoginV2PortType getLoginV2PortType() {
    if (loginV2PortType == null)
      _initLoginV2PortTypeProxy();
    return loginV2PortType;
  }
  
  public com.kana.contactcentre.services.model.LoginV2Service_wsdl.LoginUserResponseBodyType loginUser(com.kana.contactcentre.services.model.LoginV2Service_wsdl.LoginUserRequestBodyType body) throws java.rmi.RemoteException{
    if (loginV2PortType == null)
      _initLoginV2PortTypeProxy();
    return loginV2PortType.loginUser(body);
  }
  
  
}