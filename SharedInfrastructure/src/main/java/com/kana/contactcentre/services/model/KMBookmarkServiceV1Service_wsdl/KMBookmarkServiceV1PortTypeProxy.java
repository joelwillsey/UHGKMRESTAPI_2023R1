package com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl;

public class KMBookmarkServiceV1PortTypeProxy implements com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.KMBookmarkServiceV1PortType {
  private String _endpoint = null;
  private com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.KMBookmarkServiceV1PortType kMBookmarkServiceV1PortType = null;
  
  public KMBookmarkServiceV1PortTypeProxy() {
    _initKMBookmarkServiceV1PortTypeProxy();
  }
  
  public KMBookmarkServiceV1PortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initKMBookmarkServiceV1PortTypeProxy();
  }
  
  private void _initKMBookmarkServiceV1PortTypeProxy() {
    try {
      kMBookmarkServiceV1PortType = (new com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.KMBookmarkServiceV1ServiceLocator()).getKMBookmarkServiceV1Port();
      if (kMBookmarkServiceV1PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)kMBookmarkServiceV1PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)kMBookmarkServiceV1PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (kMBookmarkServiceV1PortType != null)
      ((javax.xml.rpc.Stub)kMBookmarkServiceV1PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.KMBookmarkServiceV1PortType getKMBookmarkServiceV1PortType() {
    if (kMBookmarkServiceV1PortType == null)
      _initKMBookmarkServiceV1PortTypeProxy();
    return kMBookmarkServiceV1PortType;
  }
  
  public com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.ManageBookmarkResponseBodyType manageBookmark(com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.ManageBookmarkRequestBodyType body) throws java.rmi.RemoteException{
    if (kMBookmarkServiceV1PortType == null)
      _initKMBookmarkServiceV1PortTypeProxy();
    return kMBookmarkServiceV1PortType.manageBookmark(body);
  }
  
  public com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.ListAllBookmarksResponseBodyType listAllBookmarks(com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.ListAllBookmarksRequestBodyType body) throws java.rmi.RemoteException{
    if (kMBookmarkServiceV1PortType == null)
      _initKMBookmarkServiceV1PortTypeProxy();
    return kMBookmarkServiceV1PortType.listAllBookmarks(body);
  }
  
  public com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.ReorderBookmarksResponseBodyType reorderBookmarks(com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.ReorderBookmarksRequestBodyType body) throws java.rmi.RemoteException{
    if (kMBookmarkServiceV1PortType == null)
      _initKMBookmarkServiceV1PortTypeProxy();
    return kMBookmarkServiceV1PortType.reorderBookmarks(body);
  }
  
  
}