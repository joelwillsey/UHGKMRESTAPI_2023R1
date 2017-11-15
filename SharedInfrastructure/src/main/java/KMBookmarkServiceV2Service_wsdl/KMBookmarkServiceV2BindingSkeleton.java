/**
 * KMBookmarkServiceV2BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package KMBookmarkServiceV2Service_wsdl;

public class KMBookmarkServiceV2BindingSkeleton implements KMBookmarkServiceV2Service_wsdl.KMBookmarkServiceV2PortType, org.apache.axis.wsdl.Skeleton {
    private KMBookmarkServiceV2Service_wsdl.KMBookmarkServiceV2PortType impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "ManageBookmarksV2RequestBody"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "ManageBookmarksV2RequestBodyType"), KMBookmarkServiceV2Service_wsdl.ManageBookmarksV2RequestBodyType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("manageBookmarksV2", _params, new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "ManageBookmarksV2ResponseBody"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "ManageBookmarksV2ResponseBodyType"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "ManageBookmarksV2"));
        _oper.setSoapAction("KMBookmarkServiceV2Service#ManageBookmarksV2");
        _myOperationsList.add(_oper);
        if (_myOperations.get("manageBookmarksV2") == null) {
            _myOperations.put("manageBookmarksV2", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("manageBookmarksV2")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "ListAllBookmarksV2RequestBody"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "ListAllBookmarksV2RequestBodyType"), KMBookmarkServiceV2Service_wsdl.ListAllBookmarksV2RequestBodyType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("listAllBookmarksV2", _params, new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "ListAllBookmarksV2ResponseBody"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "ListAllBookmarksV2ResponseBodyType"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "ListAllBookmarksV2"));
        _oper.setSoapAction("KMBookmarkServiceV2Service#ListAllBookmarksV2");
        _myOperationsList.add(_oper);
        if (_myOperations.get("listAllBookmarksV2") == null) {
            _myOperations.put("listAllBookmarksV2", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("listAllBookmarksV2")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "GetBookmarkRequestBody"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "GetBookmarkRequestBodyType"), KMBookmarkServiceV2Service_wsdl.GetBookmarkRequestBodyType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getBookmark", _params, new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "GetBookmarkResponseBody"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "GetBookmarkResponseBodyType"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "GetBookmark"));
        _oper.setSoapAction("KMBookmarkServiceV2Service#GetBookmark");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getBookmark") == null) {
            _myOperations.put("getBookmark", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getBookmark")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "GetFolderRequestBody"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "GetFolderRequestBodyType"), KMBookmarkServiceV2Service_wsdl.GetFolderRequestBodyType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getFolder", _params, new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "GetFolderResponseBody"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "GetFolderResponseBodyType"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "GetFolder"));
        _oper.setSoapAction("KMBookmarkServiceV2Service#GetFolder");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getFolder") == null) {
            _myOperations.put("getFolder", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getFolder")).add(_oper);
    }

    public KMBookmarkServiceV2BindingSkeleton() {
        this.impl = new KMBookmarkServiceV2Service_wsdl.KMBookmarkServiceV2BindingImpl();
    }

    public KMBookmarkServiceV2BindingSkeleton(KMBookmarkServiceV2Service_wsdl.KMBookmarkServiceV2PortType impl) {
        this.impl = impl;
    }
    public KMBookmarkServiceV2Service_wsdl.ManageBookmarksV2ResponseBodyType manageBookmarksV2(KMBookmarkServiceV2Service_wsdl.ManageBookmarksV2RequestBodyType body) throws java.rmi.RemoteException
    {
        KMBookmarkServiceV2Service_wsdl.ManageBookmarksV2ResponseBodyType ret = impl.manageBookmarksV2(body);
        return ret;
    }

    public KMBookmarkServiceV2Service_wsdl.ListAllBookmarksV2ResponseBodyType listAllBookmarksV2(KMBookmarkServiceV2Service_wsdl.ListAllBookmarksV2RequestBodyType body) throws java.rmi.RemoteException
    {
        KMBookmarkServiceV2Service_wsdl.ListAllBookmarksV2ResponseBodyType ret = impl.listAllBookmarksV2(body);
        return ret;
    }

    public KMBookmarkServiceV2Service_wsdl.GetBookmarkResponseBodyType getBookmark(KMBookmarkServiceV2Service_wsdl.GetBookmarkRequestBodyType body) throws java.rmi.RemoteException
    {
        KMBookmarkServiceV2Service_wsdl.GetBookmarkResponseBodyType ret = impl.getBookmark(body);
        return ret;
    }

    public KMBookmarkServiceV2Service_wsdl.GetFolderResponseBodyType getFolder(KMBookmarkServiceV2Service_wsdl.GetFolderRequestBodyType body) throws java.rmi.RemoteException
    {
        KMBookmarkServiceV2Service_wsdl.GetFolderResponseBodyType ret = impl.getFolder(body);
        return ret;
    }

}
