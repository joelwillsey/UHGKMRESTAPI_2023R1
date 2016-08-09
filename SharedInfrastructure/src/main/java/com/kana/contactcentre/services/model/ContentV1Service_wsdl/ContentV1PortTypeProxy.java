package com.kana.contactcentre.services.model.ContentV1Service_wsdl;

public class ContentV1PortTypeProxy implements com.kana.contactcentre.services.model.ContentV1Service_wsdl.ContentV1PortType {
  private String _endpoint = null;
  private com.kana.contactcentre.services.model.ContentV1Service_wsdl.ContentV1PortType contentV1PortType = null;
  
  public ContentV1PortTypeProxy() {
    _initContentV1PortTypeProxy();
  }
  
  public ContentV1PortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initContentV1PortTypeProxy();
  }
  
  private void _initContentV1PortTypeProxy() {
    try {
      contentV1PortType = (new com.kana.contactcentre.services.model.ContentV1Service_wsdl.ContentV1ServiceLocator()).getContentV1Port();
      if (contentV1PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)contentV1PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)contentV1PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (contentV1PortType != null)
      ((javax.xml.rpc.Stub)contentV1PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.kana.contactcentre.services.model.ContentV1Service_wsdl.ContentV1PortType getContentV1PortType() {
    if (contentV1PortType == null)
      _initContentV1PortTypeProxy();
    return contentV1PortType;
  }
  
  public com.kana.contactcentre.services.model.ContentV1Service_wsdl.GetContentDetailsResponseBodyType getContentDetails(com.kana.contactcentre.services.model.ContentV1Service_wsdl.GetContentDetailsRequestBodyType body) throws java.rmi.RemoteException{
    if (contentV1PortType == null)
      _initContentV1PortTypeProxy();
    return contentV1PortType.getContentDetails(body);
  }
  
  
}