package com.kana.contactcentre.services.model.FeaturedV1Service_wsdl;

public class FeaturedV1PortTypeProxy implements com.kana.contactcentre.services.model.FeaturedV1Service_wsdl.FeaturedV1PortType {
  private String _endpoint = null;
  private com.kana.contactcentre.services.model.FeaturedV1Service_wsdl.FeaturedV1PortType featuredV1PortType = null;
  
  public FeaturedV1PortTypeProxy() {
    _initFeaturedV1PortTypeProxy();
  }
  
  public FeaturedV1PortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initFeaturedV1PortTypeProxy();
  }
  
  private void _initFeaturedV1PortTypeProxy() {
    try {
      featuredV1PortType = (new com.kana.contactcentre.services.model.FeaturedV1Service_wsdl.FeaturedV1ServiceLocator()).getFeaturedV1Port();
      if (featuredV1PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)featuredV1PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)featuredV1PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (featuredV1PortType != null)
      ((javax.xml.rpc.Stub)featuredV1PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.kana.contactcentre.services.model.FeaturedV1Service_wsdl.FeaturedV1PortType getFeaturedV1PortType() {
    if (featuredV1PortType == null)
      _initFeaturedV1PortTypeProxy();
    return featuredV1PortType;
  }
  
  public com.kana.contactcentre.services.model.FeaturedV1Service_wsdl.GetFeaturedContentResponseBodyType getFeaturedContent(com.kana.contactcentre.services.model.FeaturedV1Service_wsdl.GetFeaturedContentRequestBodyType body) throws java.rmi.RemoteException{
    if (featuredV1PortType == null)
      _initFeaturedV1PortTypeProxy();
    return featuredV1PortType.getFeaturedContent(body);
  }
  
  
}