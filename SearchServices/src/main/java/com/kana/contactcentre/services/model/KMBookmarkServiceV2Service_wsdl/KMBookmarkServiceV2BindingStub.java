/**
 * KMBookmarkServiceV2BindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl;

public class KMBookmarkServiceV2BindingStub extends org.apache.axis.client.Stub implements com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.KMBookmarkServiceV2PortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[5];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ManageBookmarksV2");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ManageBookmarksV2RequestBody"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ManageBookmarksV2RequestBodyType"), com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ManageBookmarksV2RequestBodyType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ManageBookmarksV2ResponseBodyType"));
        oper.setReturnClass(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ManageBookmarksV2ResponseBodyType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ManageBookmarksV2ResponseBody"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ListAllBookmarksV2");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ListAllBookmarksV2RequestBody"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ListAllBookmarksV2RequestBodyType"), com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ListAllBookmarksV2RequestBodyType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ListAllBookmarksV2ResponseBodyType"));
        oper.setReturnClass(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ListAllBookmarksV2ResponseBodyType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ListAllBookmarksV2ResponseBody"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetBookmark");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "GetBookmarkRequestBody"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "GetBookmarkRequestBodyType"), com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.GetBookmarkRequestBodyType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "GetBookmarkResponseBodyType"));
        oper.setReturnClass(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.GetBookmarkResponseBodyType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "GetBookmarkResponseBody"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetFolder");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "GetFolderRequestBody"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "GetFolderRequestBodyType"), com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.GetFolderRequestBodyType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "GetFolderResponseBodyType"));
        oper.setReturnClass(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.GetFolderResponseBodyType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "GetFolderResponseBody"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ReorderBookmarkAndFolder");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ReorderBookmarkAndFolderRequestBody"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ReorderBookmarkAndFolderRequestBodyType"), com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ReorderBookmarkAndFolderRequestBodyType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ReorderBookmarkAndFolderResponseBodyType"));
        oper.setReturnClass(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ReorderBookmarkAndFolderResponseBodyType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ReorderBookmarkAndFolderResponseBody"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

    }

    public KMBookmarkServiceV2BindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public KMBookmarkServiceV2BindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public KMBookmarkServiceV2BindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "BookmarkedContentV2");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.BookmarkedContentV2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "BookmarkedContentV2List");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.BookmarkedContentV2[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "BookmarkedContentV2");
            qName2 = new javax.xml.namespace.QName("", "BookmarkedContentV2");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "BookmarkFolderContent");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.BookmarkFolderContent.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "BookmarkFolderContentList");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.BookmarkFolderContent[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "BookmarkFolderContent");
            qName2 = new javax.xml.namespace.QName("", "BookmarkFolderContent");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "BookmarkFolderContents");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.BookmarkFolderContents.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "BookmarkFolderContentsList");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.BookmarkFolderContents[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "BookmarkFolderContents");
            qName2 = new javax.xml.namespace.QName("", "BookmarkFolderContents");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "BookmarkSubFolderContents");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.BookmarkSubFolderContents.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "BookmarkSubFolderContentsList");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.BookmarkSubFolderContents[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "BookmarkSubFolderContents");
            qName2 = new javax.xml.namespace.QName("", "BookmarkSubFolderContents");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ContentBookmark");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ContentBookmark.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ContentBookmarksV2");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ContentBookmarksV2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "EIContentType");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.EIContentType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ErrorList");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ErrorMessage[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ErrorMessage");
            qName2 = new javax.xml.namespace.QName("", "ErrorMessage");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ErrorMessage");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ErrorMessage.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "GetBookmarkRequestBodyType");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.GetBookmarkRequestBodyType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "GetBookmarkResponseBodyType");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.GetBookmarkResponseBodyType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "GetFolderRequestBodyType");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.GetFolderRequestBodyType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "GetFolderResponseBodyType");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.GetFolderResponseBodyType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "IAssociationList");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.IAssociationList.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "IEntityLock");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.IEntityLock.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "IEvaId");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.IEvaId.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "IPersistableEntity");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.IPersistableEntity.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ListAllBookmarksV2RequestBodyType");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ListAllBookmarksV2RequestBodyType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ListAllBookmarksV2ResponseBodyType");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ListAllBookmarksV2ResponseBodyType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ManageBookmarksV2RequestBodyType");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ManageBookmarksV2RequestBodyType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ManageBookmarksV2ResponseBodyType");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ManageBookmarksV2ResponseBodyType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ReorderBookmarkAndFolderRequestBodyType");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ReorderBookmarkAndFolderRequestBodyType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ReorderBookmarkAndFolderResponseBodyType");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ReorderBookmarkAndFolderResponseBodyType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ManageBookmarksV2ResponseBodyType manageBookmarksV2(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ManageBookmarksV2RequestBodyType body) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("KMBookmarkServiceV2Service#ManageBookmarksV2");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "ManageBookmarksV2"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {body});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ManageBookmarksV2ResponseBodyType) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ManageBookmarksV2ResponseBodyType) org.apache.axis.utils.JavaUtils.convert(_resp, com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ManageBookmarksV2ResponseBodyType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ListAllBookmarksV2ResponseBodyType listAllBookmarksV2(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ListAllBookmarksV2RequestBodyType body) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("KMBookmarkServiceV2Service#ListAllBookmarksV2");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "ListAllBookmarksV2"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {body});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ListAllBookmarksV2ResponseBodyType) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ListAllBookmarksV2ResponseBodyType) org.apache.axis.utils.JavaUtils.convert(_resp, com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ListAllBookmarksV2ResponseBodyType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.GetBookmarkResponseBodyType getBookmark(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.GetBookmarkRequestBodyType body) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("KMBookmarkServiceV2Service#GetBookmark");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "GetBookmark"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {body});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.GetBookmarkResponseBodyType) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.GetBookmarkResponseBodyType) org.apache.axis.utils.JavaUtils.convert(_resp, com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.GetBookmarkResponseBodyType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.GetFolderResponseBodyType getFolder(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.GetFolderRequestBodyType body) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("KMBookmarkServiceV2Service#GetFolder");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "GetFolder"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {body});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.GetFolderResponseBodyType) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.GetFolderResponseBodyType) org.apache.axis.utils.JavaUtils.convert(_resp, com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.GetFolderResponseBodyType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ReorderBookmarkAndFolderResponseBodyType reorderBookmarkAndFolder(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ReorderBookmarkAndFolderRequestBodyType body) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("KMBookmarkServiceV2Service#ReorderBookmarkAndFolder");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "ReorderBookmarkAndFolder"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {body});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ReorderBookmarkAndFolderResponseBodyType) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ReorderBookmarkAndFolderResponseBodyType) org.apache.axis.utils.JavaUtils.convert(_resp, com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ReorderBookmarkAndFolderResponseBodyType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

	@Override
	public ListAllBookmarksV2ResponseBodyType listAllV2(ListAllBookmarksV2RequestBodyType listallRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("KMBookmarkServiceV2Service#ListAllBookmarksV2");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "ListAllBookmarksV2"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {listallRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ListAllBookmarksV2ResponseBodyType) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ListAllBookmarksV2ResponseBodyType) org.apache.axis.utils.JavaUtils.convert(_resp, com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ListAllBookmarksV2ResponseBodyType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
	}

}
