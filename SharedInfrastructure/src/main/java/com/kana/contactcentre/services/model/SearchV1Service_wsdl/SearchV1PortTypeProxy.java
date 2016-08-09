package com.kana.contactcentre.services.model.SearchV1Service_wsdl;

public class SearchV1PortTypeProxy implements com.kana.contactcentre.services.model.SearchV1Service_wsdl.SearchV1PortType {
  private String _endpoint = null;
  private com.kana.contactcentre.services.model.SearchV1Service_wsdl.SearchV1PortType searchV1PortType = null;
  
  public SearchV1PortTypeProxy() {
    _initSearchV1PortTypeProxy();
  }
  
  public SearchV1PortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initSearchV1PortTypeProxy();
  }
  
  private void _initSearchV1PortTypeProxy() {
    try {
      searchV1PortType = (new com.kana.contactcentre.services.model.SearchV1Service_wsdl.SearchV1ServiceLocator()).getSearchV1Port();
      if (searchV1PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)searchV1PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)searchV1PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (searchV1PortType != null)
      ((javax.xml.rpc.Stub)searchV1PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.kana.contactcentre.services.model.SearchV1Service_wsdl.SearchV1PortType getSearchV1PortType() {
    if (searchV1PortType == null)
      _initSearchV1PortTypeProxy();
    return searchV1PortType;
  }
  
  public com.kana.contactcentre.services.model.SearchV1Service_wsdl.MarkAsViewedResponseBodyType markAsViewed(com.kana.contactcentre.services.model.SearchV1Service_wsdl.MarkAsViewedRequestBodyType body) throws java.rmi.RemoteException{
    if (searchV1PortType == null)
      _initSearchV1PortTypeProxy();
    return searchV1PortType.markAsViewed(body);
  }
  
  public com.kana.contactcentre.services.model.SearchV1Service_wsdl.RateResponseBodyType rate(com.kana.contactcentre.services.model.SearchV1Service_wsdl.RateRequestBodyType body) throws java.rmi.RemoteException{
    if (searchV1PortType == null)
      _initSearchV1PortTypeProxy();
    return searchV1PortType.rate(body);
  }
  
  public com.kana.contactcentre.services.model.SearchV1Service_wsdl.SharedTextSearchResponseBodyType sharedTextSearch(com.kana.contactcentre.services.model.SearchV1Service_wsdl.SharedTextSearchRequestBodyType body) throws java.rmi.RemoteException{
    if (searchV1PortType == null)
      _initSearchV1PortTypeProxy();
    return searchV1PortType.sharedTextSearch(body);
  }
  
  public com.kana.contactcentre.services.model.SearchV1Service_wsdl.MarkAsFeaturedResponseBodyType markAsFeatured(com.kana.contactcentre.services.model.SearchV1Service_wsdl.MarkAsFeaturedRequestBodyType body) throws java.rmi.RemoteException{
    if (searchV1PortType == null)
      _initSearchV1PortTypeProxy();
    return searchV1PortType.markAsFeatured(body);
  }
  
  public com.kana.contactcentre.services.model.SearchV1Service_wsdl.GetFeaturedContentResponseBodyType getFeaturedContent(com.kana.contactcentre.services.model.SearchV1Service_wsdl.GetFeaturedContentRequestBodyType body) throws java.rmi.RemoteException{
    if (searchV1PortType == null)
      _initSearchV1PortTypeProxy();
    return searchV1PortType.getFeaturedContent(body);
  }
  
  public com.kana.contactcentre.services.model.SearchV1Service_wsdl.GetTopContentResponseBodyType getTopContent(com.kana.contactcentre.services.model.SearchV1Service_wsdl.GetTopContentRequestBodyType body) throws java.rmi.RemoteException{
    if (searchV1PortType == null)
      _initSearchV1PortTypeProxy();
    return searchV1PortType.getTopContent(body);
  }
  
  
}