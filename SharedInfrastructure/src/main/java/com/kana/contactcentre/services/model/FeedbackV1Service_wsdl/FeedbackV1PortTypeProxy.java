package com.kana.contactcentre.services.model.FeedbackV1Service_wsdl;

public class FeedbackV1PortTypeProxy implements com.kana.contactcentre.services.model.FeedbackV1Service_wsdl.FeedbackV1PortType {
  private String _endpoint = null;
  private com.kana.contactcentre.services.model.FeedbackV1Service_wsdl.FeedbackV1PortType feedbackV1PortType = null;
  
  public FeedbackV1PortTypeProxy() {
    _initFeedbackV1PortTypeProxy();
  }
  
  public FeedbackV1PortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initFeedbackV1PortTypeProxy();
  }
  
  private void _initFeedbackV1PortTypeProxy() {
    try {
      feedbackV1PortType = (new com.kana.contactcentre.services.model.FeedbackV1Service_wsdl.FeedbackV1ServiceLocator()).getFeedbackV1Port();
      if (feedbackV1PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)feedbackV1PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)feedbackV1PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (feedbackV1PortType != null)
      ((javax.xml.rpc.Stub)feedbackV1PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.kana.contactcentre.services.model.FeedbackV1Service_wsdl.FeedbackV1PortType getFeedbackV1PortType() {
    if (feedbackV1PortType == null)
      _initFeedbackV1PortTypeProxy();
    return feedbackV1PortType;
  }
  
  public com.kana.contactcentre.services.model.FeedbackV1Service_wsdl.FeedbackResponseBodyType feedback(com.kana.contactcentre.services.model.FeedbackV1Service_wsdl.FeedbackRequestBodyType body) throws java.rmi.RemoteException{
    if (feedbackV1PortType == null)
      _initFeedbackV1PortTypeProxy();
    return feedbackV1PortType.feedback(body);
  }
  
  
}