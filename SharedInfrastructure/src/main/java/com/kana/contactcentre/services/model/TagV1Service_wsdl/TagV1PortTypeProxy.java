package com.kana.contactcentre.services.model.TagV1Service_wsdl;

public class TagV1PortTypeProxy implements com.kana.contactcentre.services.model.TagV1Service_wsdl.TagV1PortType {
  private String _endpoint = null;
  private com.kana.contactcentre.services.model.TagV1Service_wsdl.TagV1PortType tagV1PortType = null;
  
  public TagV1PortTypeProxy() {
    _initTagV1PortTypeProxy();
  }
  
  public TagV1PortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initTagV1PortTypeProxy();
  }
  
  private void _initTagV1PortTypeProxy() {
    try {
      tagV1PortType = (new com.kana.contactcentre.services.model.TagV1Service_wsdl.TagV1ServiceLocator()).getTagV1Port();
      if (tagV1PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)tagV1PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)tagV1PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (tagV1PortType != null)
      ((javax.xml.rpc.Stub)tagV1PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.kana.contactcentre.services.model.TagV1Service_wsdl.TagV1PortType getTagV1PortType() {
    if (tagV1PortType == null)
      _initTagV1PortTypeProxy();
    return tagV1PortType;
  }
  
  public com.kana.contactcentre.services.model.TagV1Service_wsdl.GetTagSetResponseBodyType getTagSet(com.kana.contactcentre.services.model.TagV1Service_wsdl.GetTagSetRequestBodyType body) throws java.rmi.RemoteException{
    if (tagV1PortType == null)
      _initTagV1PortTypeProxy();
    return tagV1PortType.getTagSet(body);
  }
  
  public com.kana.contactcentre.services.model.TagV1Service_wsdl.GetAllTagSetResponseBodyType getAllTagSet(com.kana.contactcentre.services.model.TagV1Service_wsdl.GetAllTagSetRequestBodyType body) throws java.rmi.RemoteException{
    if (tagV1PortType == null)
      _initTagV1PortTypeProxy();
    return tagV1PortType.getAllTagSet(body);
  }
  
  
}