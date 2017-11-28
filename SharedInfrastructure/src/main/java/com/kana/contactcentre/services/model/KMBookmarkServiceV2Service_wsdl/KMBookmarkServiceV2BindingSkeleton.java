/**
 * KMBookmarkServiceV2BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl;

public class KMBookmarkServiceV2BindingSkeleton implements com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.KMBookmarkServiceV2PortType, org.apache.axis.wsdl.Skeleton {
    private com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.KMBookmarkServiceV2PortType impl;
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
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ManageBookmarksV2RequestBody"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ManageBookmarksV2RequestBodyType"), com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ManageBookmarksV2RequestBodyType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("manageBookmarksV2", _params, new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ManageBookmarksV2ResponseBody"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ManageBookmarksV2ResponseBodyType"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "ManageBookmarksV2"));
        _oper.setSoapAction("KMBookmarkServiceV2Service#ManageBookmarksV2");
        _myOperationsList.add(_oper);
        if (_myOperations.get("manageBookmarksV2") == null) {
            _myOperations.put("manageBookmarksV2", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("manageBookmarksV2")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ListAllBookmarksV2RequestBody"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ListAllBookmarksV2RequestBodyType"), com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ListAllBookmarksV2RequestBodyType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("listAllBookmarksV2", _params, new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ListAllBookmarksV2ResponseBody"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ListAllBookmarksV2ResponseBodyType"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "ListAllBookmarksV2"));
        _oper.setSoapAction("KMBookmarkServiceV2Service#ListAllBookmarksV2");
        _myOperationsList.add(_oper);
        if (_myOperations.get("listAllBookmarksV2") == null) {
            _myOperations.put("listAllBookmarksV2", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("listAllBookmarksV2")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "GetBookmarkRequestBody"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "GetBookmarkRequestBodyType"), com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.GetBookmarkRequestBodyType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getBookmark", _params, new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "GetBookmarkResponseBody"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "GetBookmarkResponseBodyType"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "GetBookmark"));
        _oper.setSoapAction("KMBookmarkServiceV2Service#GetBookmark");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getBookmark") == null) {
            _myOperations.put("getBookmark", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getBookmark")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "GetFolderRequestBody"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "GetFolderRequestBodyType"), com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.GetFolderRequestBodyType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getFolder", _params, new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "GetFolderResponseBody"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "GetFolderResponseBodyType"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "GetFolder"));
        _oper.setSoapAction("KMBookmarkServiceV2Service#GetFolder");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getFolder") == null) {
            _myOperations.put("getFolder", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getFolder")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ReorderBookmarkAndFolderRequestBody"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ReorderBookmarkAndFolderRequestBodyType"), com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ReorderBookmarkAndFolderRequestBodyType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("reorderBookmarkAndFolder", _params, new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ReorderBookmarkAndFolderResponseBody"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ReorderBookmarkAndFolderResponseBodyType"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "ReorderBookmarkAndFolder"));
        _oper.setSoapAction("KMBookmarkServiceV2Service#ReorderBookmarkAndFolder");
        _myOperationsList.add(_oper);
        if (_myOperations.get("reorderBookmarkAndFolder") == null) {
            _myOperations.put("reorderBookmarkAndFolder", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("reorderBookmarkAndFolder")).add(_oper);
    }

    public KMBookmarkServiceV2BindingSkeleton() {
        this.impl = new com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.KMBookmarkServiceV2BindingImpl();
    }

    public KMBookmarkServiceV2BindingSkeleton(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.KMBookmarkServiceV2PortType impl) {
        this.impl = impl;
    }
    public com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ManageBookmarksV2ResponseBodyType manageBookmarksV2(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ManageBookmarksV2RequestBodyType body) throws java.rmi.RemoteException
    {
        com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ManageBookmarksV2ResponseBodyType ret = impl.manageBookmarksV2(body);
        return ret;
    }

    public com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ListAllBookmarksV2ResponseBodyType listAllBookmarksV2(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ListAllBookmarksV2RequestBodyType body) throws java.rmi.RemoteException
    {
        com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ListAllBookmarksV2ResponseBodyType ret = impl.listAllBookmarksV2(body);
        return ret;
    }

    public com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.GetBookmarkResponseBodyType getBookmark(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.GetBookmarkRequestBodyType body) throws java.rmi.RemoteException
    {
        com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.GetBookmarkResponseBodyType ret = impl.getBookmark(body);
        return ret;
    }

    public com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.GetFolderResponseBodyType getFolder(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.GetFolderRequestBodyType body) throws java.rmi.RemoteException
    {
        com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.GetFolderResponseBodyType ret = impl.getFolder(body);
        return ret;
    }

    public com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ReorderBookmarkAndFolderResponseBodyType reorderBookmarkAndFolder(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ReorderBookmarkAndFolderRequestBodyType body) throws java.rmi.RemoteException
    {
        com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ReorderBookmarkAndFolderResponseBodyType ret = impl.reorderBookmarkAndFolder(body);
        return ret;
    }

}
