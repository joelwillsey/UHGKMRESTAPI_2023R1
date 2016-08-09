/**
 * SearchV1BindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.SearchV1Service_wsdl;

public class SearchV1BindingStub extends org.apache.axis.client.Stub implements com.kana.contactcentre.services.model.SearchV1Service_wsdl.SearchV1PortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[6];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("MarkAsViewed");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "MarkAsViewedRequestBody"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "MarkAsViewedRequestBodyType"), com.kana.contactcentre.services.model.SearchV1Service_wsdl.MarkAsViewedRequestBodyType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "MarkAsViewedResponseBodyType"));
        oper.setReturnClass(com.kana.contactcentre.services.model.SearchV1Service_wsdl.MarkAsViewedResponseBodyType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "MarkAsViewedResponseBody"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Rate");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "RateRequestBody"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "RateRequestBodyType"), com.kana.contactcentre.services.model.SearchV1Service_wsdl.RateRequestBodyType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "RateResponseBodyType"));
        oper.setReturnClass(com.kana.contactcentre.services.model.SearchV1Service_wsdl.RateResponseBodyType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "RateResponseBody"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SharedTextSearch");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "SharedTextSearchRequestBody"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "SharedTextSearchRequestBodyType"), com.kana.contactcentre.services.model.SearchV1Service_wsdl.SharedTextSearchRequestBodyType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "SharedTextSearchResponseBodyType"));
        oper.setReturnClass(com.kana.contactcentre.services.model.SearchV1Service_wsdl.SharedTextSearchResponseBodyType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "SharedTextSearchResponseBody"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("MarkAsFeatured");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "MarkAsFeaturedRequestBody"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "MarkAsFeaturedRequestBodyType"), com.kana.contactcentre.services.model.SearchV1Service_wsdl.MarkAsFeaturedRequestBodyType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "MarkAsFeaturedResponseBodyType"));
        oper.setReturnClass(com.kana.contactcentre.services.model.SearchV1Service_wsdl.MarkAsFeaturedResponseBodyType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "MarkAsFeaturedResponseBody"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetFeaturedContent");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "GetFeaturedContentRequestBody"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "GetFeaturedContentRequestBodyType"), com.kana.contactcentre.services.model.SearchV1Service_wsdl.GetFeaturedContentRequestBodyType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "GetFeaturedContentResponseBodyType"));
        oper.setReturnClass(com.kana.contactcentre.services.model.SearchV1Service_wsdl.GetFeaturedContentResponseBodyType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "GetFeaturedContentResponseBody"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetTopContent");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "GetTopContentRequestBody"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "GetTopContentRequestBodyType"), com.kana.contactcentre.services.model.SearchV1Service_wsdl.GetTopContentRequestBodyType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "GetTopContentResponseBodyType"));
        oper.setReturnClass(com.kana.contactcentre.services.model.SearchV1Service_wsdl.GetTopContentResponseBodyType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "GetTopContentResponseBody"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

    }

    public SearchV1BindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public SearchV1BindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public SearchV1BindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "ControlData");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.SearchV1Service_wsdl.ControlData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "GetFeaturedContentRequestBodyType");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.SearchV1Service_wsdl.GetFeaturedContentRequestBodyType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "GetFeaturedContentResponseBodyType");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.SearchV1Service_wsdl.GetFeaturedContentResponseBodyType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "GetTopContentRequestBodyType");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.SearchV1Service_wsdl.GetTopContentRequestBodyType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "GetTopContentResponseBodyType");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.SearchV1Service_wsdl.GetTopContentResponseBodyType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "KnowledgeGroupUnit");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.SearchV1Service_wsdl.KnowledgeGroupUnit.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "KnowledgeGroupUnitList");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.SearchV1Service_wsdl.KnowledgeGroupUnit[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "KnowledgeGroupUnit");
            qName2 = new javax.xml.namespace.QName("", "KnowledgeGroupUnit");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "KnowledgeResultSet");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.SearchV1Service_wsdl.KnowledgeResultSet.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "KnowledgeUnit");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.SearchV1Service_wsdl.KnowledgeUnit.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "KnowledgeUnitList");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.SearchV1Service_wsdl.KnowledgeUnit[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "KnowledgeUnit");
            qName2 = new javax.xml.namespace.QName("", "KnowledgeUnit");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "MarkAsFeaturedRequestBodyType");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.SearchV1Service_wsdl.MarkAsFeaturedRequestBodyType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "MarkAsFeaturedResponseBodyType");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.SearchV1Service_wsdl.MarkAsFeaturedResponseBodyType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "MarkAsViewedRequestBodyType");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.SearchV1Service_wsdl.MarkAsViewedRequestBodyType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "MarkAsViewedResponseBodyType");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.SearchV1Service_wsdl.MarkAsViewedResponseBodyType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "RateRequestBodyType");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.SearchV1Service_wsdl.RateRequestBodyType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "RateResponseBodyType");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.SearchV1Service_wsdl.RateResponseBodyType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "RatingInformation");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.SearchV1Service_wsdl.RatingInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "ReplacedTerm");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.SearchV1Service_wsdl.ReplacedTerm.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "ReplacedTermsList");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.SearchV1Service_wsdl.ReplacedTerm[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "ReplacedTerm");
            qName2 = new javax.xml.namespace.QName("", "ReplacedTerm");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "SearchDateType");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.SearchV1Service_wsdl.SearchDateType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "SharedTextSearchRequestBodyType");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.SearchV1Service_wsdl.SharedTextSearchRequestBodyType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "SharedTextSearchResponseBodyType");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.SearchV1Service_wsdl.SharedTextSearchResponseBodyType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "SuggestedQuery");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.SearchV1Service_wsdl.SuggestedQuery.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "SuggestedQueryList");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.SearchV1Service_wsdl.SuggestedQuery[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/SearchV1Service.wsdl", "SuggestedQuery");
            qName2 = new javax.xml.namespace.QName("", "SuggestedQuery");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

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

    public com.kana.contactcentre.services.model.SearchV1Service_wsdl.MarkAsViewedResponseBodyType markAsViewed(com.kana.contactcentre.services.model.SearchV1Service_wsdl.MarkAsViewedRequestBodyType body) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("SearchV1Service#MarkAsViewed");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "MarkAsViewed"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {body});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.kana.contactcentre.services.model.SearchV1Service_wsdl.MarkAsViewedResponseBodyType) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.kana.contactcentre.services.model.SearchV1Service_wsdl.MarkAsViewedResponseBodyType) org.apache.axis.utils.JavaUtils.convert(_resp, com.kana.contactcentre.services.model.SearchV1Service_wsdl.MarkAsViewedResponseBodyType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.kana.contactcentre.services.model.SearchV1Service_wsdl.RateResponseBodyType rate(com.kana.contactcentre.services.model.SearchV1Service_wsdl.RateRequestBodyType body) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("SearchV1Service#Rate");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Rate"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {body});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.kana.contactcentre.services.model.SearchV1Service_wsdl.RateResponseBodyType) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.kana.contactcentre.services.model.SearchV1Service_wsdl.RateResponseBodyType) org.apache.axis.utils.JavaUtils.convert(_resp, com.kana.contactcentre.services.model.SearchV1Service_wsdl.RateResponseBodyType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.kana.contactcentre.services.model.SearchV1Service_wsdl.SharedTextSearchResponseBodyType sharedTextSearch(com.kana.contactcentre.services.model.SearchV1Service_wsdl.SharedTextSearchRequestBodyType body) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("SearchV1Service#SharedTextSearch");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "SharedTextSearch"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {body});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.kana.contactcentre.services.model.SearchV1Service_wsdl.SharedTextSearchResponseBodyType) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.kana.contactcentre.services.model.SearchV1Service_wsdl.SharedTextSearchResponseBodyType) org.apache.axis.utils.JavaUtils.convert(_resp, com.kana.contactcentre.services.model.SearchV1Service_wsdl.SharedTextSearchResponseBodyType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.kana.contactcentre.services.model.SearchV1Service_wsdl.MarkAsFeaturedResponseBodyType markAsFeatured(com.kana.contactcentre.services.model.SearchV1Service_wsdl.MarkAsFeaturedRequestBodyType body) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("SearchV1Service#MarkAsFeatured");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "MarkAsFeatured"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {body});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.kana.contactcentre.services.model.SearchV1Service_wsdl.MarkAsFeaturedResponseBodyType) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.kana.contactcentre.services.model.SearchV1Service_wsdl.MarkAsFeaturedResponseBodyType) org.apache.axis.utils.JavaUtils.convert(_resp, com.kana.contactcentre.services.model.SearchV1Service_wsdl.MarkAsFeaturedResponseBodyType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.kana.contactcentre.services.model.SearchV1Service_wsdl.GetFeaturedContentResponseBodyType getFeaturedContent(com.kana.contactcentre.services.model.SearchV1Service_wsdl.GetFeaturedContentRequestBodyType body) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("SearchV1Service#GetFeaturedContent");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "GetFeaturedContent"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {body});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.kana.contactcentre.services.model.SearchV1Service_wsdl.GetFeaturedContentResponseBodyType) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.kana.contactcentre.services.model.SearchV1Service_wsdl.GetFeaturedContentResponseBodyType) org.apache.axis.utils.JavaUtils.convert(_resp, com.kana.contactcentre.services.model.SearchV1Service_wsdl.GetFeaturedContentResponseBodyType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.kana.contactcentre.services.model.SearchV1Service_wsdl.GetTopContentResponseBodyType getTopContent(com.kana.contactcentre.services.model.SearchV1Service_wsdl.GetTopContentRequestBodyType body) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("SearchV1Service#GetTopContent");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "GetTopContent"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {body});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.kana.contactcentre.services.model.SearchV1Service_wsdl.GetTopContentResponseBodyType) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.kana.contactcentre.services.model.SearchV1Service_wsdl.GetTopContentResponseBodyType) org.apache.axis.utils.JavaUtils.convert(_resp, com.kana.contactcentre.services.model.SearchV1Service_wsdl.GetTopContentResponseBodyType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
