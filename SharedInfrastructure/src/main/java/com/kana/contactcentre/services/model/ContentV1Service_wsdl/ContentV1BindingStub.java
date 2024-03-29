/**
 * ContentV1BindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.ContentV1Service_wsdl;

public class ContentV1BindingStub extends org.apache.axis.client.Stub implements com.kana.contactcentre.services.model.ContentV1Service_wsdl.ContentV1PortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[3];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetContentDetails");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "GetContentDetailsRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "GetContentDetailsRequestBodyType"), com.kana.contactcentre.services.model.ContentV1Service_wsdl.GetContentDetailsRequestBodyType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "GetContentDetailsResponseBodyType"));
        oper.setReturnClass(com.kana.contactcentre.services.model.ContentV1Service_wsdl.GetContentDetailsResponseBodyType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "GetContentDetailsResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetContentVersions");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "GetContentVersionsRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "GetContentVersionsRequestBodyType"), com.kana.contactcentre.services.model.ContentV1Service_wsdl.GetContentVersionsRequestBodyType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "GetContentVersionsResponseBodyType"));
        oper.setReturnClass(com.kana.contactcentre.services.model.ContentV1Service_wsdl.GetContentVersionsResponseBodyType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "GetContentVersionsResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetViewDefinition");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "GetViewDefinitionRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "GetViewDefinitionRequestBodyType"), com.kana.contactcentre.services.model.ContentV1Service_wsdl.GetViewDefinitionRequestBodyType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "GetViewDefinitionResponseBodyType"));
        oper.setReturnClass(com.kana.contactcentre.services.model.ContentV1Service_wsdl.GetViewDefinitionResponseBodyType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "GetViewDefinitionResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

    }

    public ContentV1BindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public ContentV1BindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public ContentV1BindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "ContentDetails");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.ContentV1Service_wsdl.ContentDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "ContentVersionList");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.ContentV1Service_wsdl.Version[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "Version");
            qName2 = new javax.xml.namespace.QName("", "Version");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "ContentVersionsResultSet");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.ContentV1Service_wsdl.ContentVersionsResultSet.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "ContentViewDefinition");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.ContentV1Service_wsdl.ContentViewDefinition.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "ErrorList");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.ContentV1Service_wsdl.ErrorMessage[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "ErrorMessage");
            qName2 = new javax.xml.namespace.QName("", "ErrorMessage");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "ErrorMessage");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.ContentV1Service_wsdl.ErrorMessage.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "GetContentDetailsRequestBodyType");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.ContentV1Service_wsdl.GetContentDetailsRequestBodyType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "GetContentDetailsResponseBodyType");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.ContentV1Service_wsdl.GetContentDetailsResponseBodyType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "GetContentVersionsRequestBodyType");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.ContentV1Service_wsdl.GetContentVersionsRequestBodyType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "GetContentVersionsResponseBodyType");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.ContentV1Service_wsdl.GetContentVersionsResponseBodyType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "GetViewDefinitionRequestBodyType");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.ContentV1Service_wsdl.GetViewDefinitionRequestBodyType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "GetViewDefinitionResponseBodyType");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.ContentV1Service_wsdl.GetViewDefinitionResponseBodyType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "GTMap");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.ContentV1Service_wsdl.GTMap.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "StringItem");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.ContentV1Service_wsdl.StringItem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "StringItemList");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.ContentV1Service_wsdl.StringItem[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "StringItem");
            qName2 = new javax.xml.namespace.QName("", "StringItem");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "Version");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.ContentV1Service_wsdl.Version.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "ViewFieldDefinition");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.ContentV1Service_wsdl.ViewFieldDefinition.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "ViewFieldDefinitionList");
            cachedSerQNames.add(qName);
            cls = com.kana.contactcentre.services.model.ContentV1Service_wsdl.ViewFieldDefinition[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "ViewFieldDefinition");
            qName2 = new javax.xml.namespace.QName("", "ViewFieldDefinition");
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

    public com.kana.contactcentre.services.model.ContentV1Service_wsdl.GetContentDetailsResponseBodyType getContentDetails(com.kana.contactcentre.services.model.ContentV1Service_wsdl.GetContentDetailsRequestBodyType body) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("ContentV1Service#GetContentDetails");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "GetContentDetails"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {body});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.kana.contactcentre.services.model.ContentV1Service_wsdl.GetContentDetailsResponseBodyType) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.kana.contactcentre.services.model.ContentV1Service_wsdl.GetContentDetailsResponseBodyType) org.apache.axis.utils.JavaUtils.convert(_resp, com.kana.contactcentre.services.model.ContentV1Service_wsdl.GetContentDetailsResponseBodyType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.kana.contactcentre.services.model.ContentV1Service_wsdl.GetContentVersionsResponseBodyType getContentVersions(com.kana.contactcentre.services.model.ContentV1Service_wsdl.GetContentVersionsRequestBodyType body) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("ContentV1Service#GetContentVersions");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "GetContentVersions"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {body});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.kana.contactcentre.services.model.ContentV1Service_wsdl.GetContentVersionsResponseBodyType) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.kana.contactcentre.services.model.ContentV1Service_wsdl.GetContentVersionsResponseBodyType) org.apache.axis.utils.JavaUtils.convert(_resp, com.kana.contactcentre.services.model.ContentV1Service_wsdl.GetContentVersionsResponseBodyType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.kana.contactcentre.services.model.ContentV1Service_wsdl.GetViewDefinitionResponseBodyType getViewDefinition(com.kana.contactcentre.services.model.ContentV1Service_wsdl.GetViewDefinitionRequestBodyType body) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("ContentV1Service#GetViewDefinition");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "GetViewDefinition"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {body});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.kana.contactcentre.services.model.ContentV1Service_wsdl.GetViewDefinitionResponseBodyType) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.kana.contactcentre.services.model.ContentV1Service_wsdl.GetViewDefinitionResponseBodyType) org.apache.axis.utils.JavaUtils.convert(_resp, com.kana.contactcentre.services.model.ContentV1Service_wsdl.GetViewDefinitionResponseBodyType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
