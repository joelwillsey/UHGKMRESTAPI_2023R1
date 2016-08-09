package com.kana.contactcentre.services.model.RequestAnswerV1Service_wsdl;

public class RequestAnswerV1PortTypeProxy implements com.kana.contactcentre.services.model.RequestAnswerV1Service_wsdl.RequestAnswerV1PortType {
  private String _endpoint = null;
  private com.kana.contactcentre.services.model.RequestAnswerV1Service_wsdl.RequestAnswerV1PortType requestAnswerV1PortType = null;
  
  public RequestAnswerV1PortTypeProxy() {
    _initRequestAnswerV1PortTypeProxy();
  }
  
  public RequestAnswerV1PortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initRequestAnswerV1PortTypeProxy();
  }
  
  private void _initRequestAnswerV1PortTypeProxy() {
    try {
      requestAnswerV1PortType = (new com.kana.contactcentre.services.model.RequestAnswerV1Service_wsdl.RequestAnswerV1ServiceLocator()).getRequestAnswerV1Port();
      if (requestAnswerV1PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)requestAnswerV1PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)requestAnswerV1PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (requestAnswerV1PortType != null)
      ((javax.xml.rpc.Stub)requestAnswerV1PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.kana.contactcentre.services.model.RequestAnswerV1Service_wsdl.RequestAnswerV1PortType getRequestAnswerV1PortType() {
    if (requestAnswerV1PortType == null)
      _initRequestAnswerV1PortTypeProxy();
    return requestAnswerV1PortType;
  }
  
  public com.kana.contactcentre.services.model.RequestAnswerV1Service_wsdl.RequestAnswerResponseBodyType requestAnswer(com.kana.contactcentre.services.model.RequestAnswerV1Service_wsdl.RequestAnswerRequestBodyType body) throws java.rmi.RemoteException{
    if (requestAnswerV1PortType == null)
      _initRequestAnswerV1PortTypeProxy();
    return requestAnswerV1PortType.requestAnswer(body);
  }
  
  
}