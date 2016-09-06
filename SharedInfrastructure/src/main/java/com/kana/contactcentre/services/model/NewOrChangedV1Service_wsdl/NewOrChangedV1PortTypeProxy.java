package com.kana.contactcentre.services.model.NewOrChangedV1Service_wsdl;

public class NewOrChangedV1PortTypeProxy implements com.kana.contactcentre.services.model.NewOrChangedV1Service_wsdl.NewOrChangedV1PortType {
  private String _endpoint = null;
  private com.kana.contactcentre.services.model.NewOrChangedV1Service_wsdl.NewOrChangedV1PortType newOrChangedV1PortType = null;
  
  public NewOrChangedV1PortTypeProxy() {
    _initNewOrChangedV1PortTypeProxy();
  }
  
  public NewOrChangedV1PortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initNewOrChangedV1PortTypeProxy();
  }
  
  private void _initNewOrChangedV1PortTypeProxy() {
    try {
      newOrChangedV1PortType = (new com.kana.contactcentre.services.model.NewOrChangedV1Service_wsdl.NewOrChangedV1ServiceLocator()).getNewOrChangedV1Port();
      if (newOrChangedV1PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)newOrChangedV1PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)newOrChangedV1PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (newOrChangedV1PortType != null)
      ((javax.xml.rpc.Stub)newOrChangedV1PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.kana.contactcentre.services.model.NewOrChangedV1Service_wsdl.NewOrChangedV1PortType getNewOrChangedV1PortType() {
    if (newOrChangedV1PortType == null)
      _initNewOrChangedV1PortTypeProxy();
    return newOrChangedV1PortType;
  }
  
  public com.kana.contactcentre.services.model.NewOrChangedV1Service_wsdl.GetNewOrChangedContentResponseBodyType getNewOrChangedContent(com.kana.contactcentre.services.model.NewOrChangedV1Service_wsdl.GetNewOrChangedContentRequestBodyType body) throws java.rmi.RemoteException{
    if (newOrChangedV1PortType == null)
      _initNewOrChangedV1PortTypeProxy();
    return newOrChangedV1PortType.getNewOrChangedContent(body);
  }
  
  
}