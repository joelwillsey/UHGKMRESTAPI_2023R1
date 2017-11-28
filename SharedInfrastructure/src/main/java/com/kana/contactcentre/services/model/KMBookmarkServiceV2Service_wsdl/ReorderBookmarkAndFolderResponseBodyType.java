/**
 * ReorderBookmarkAndFolderResponseBodyType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl;

public class ReorderBookmarkAndFolderResponseBodyType  implements java.io.Serializable {
    private com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ErrorMessage[] errorList;

    private com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ContentBookmarksV2 response;

    public ReorderBookmarkAndFolderResponseBodyType() {
    }

    public ReorderBookmarkAndFolderResponseBodyType(
           com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ErrorMessage[] errorList,
           com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ContentBookmarksV2 response) {
           this.errorList = errorList;
           this.response = response;
    }


    /**
     * Gets the errorList value for this ReorderBookmarkAndFolderResponseBodyType.
     * 
     * @return errorList
     */
    public com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ErrorMessage[] getErrorList() {
        return errorList;
    }


    /**
     * Sets the errorList value for this ReorderBookmarkAndFolderResponseBodyType.
     * 
     * @param errorList
     */
    public void setErrorList(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ErrorMessage[] errorList) {
        this.errorList = errorList;
    }


    /**
     * Gets the response value for this ReorderBookmarkAndFolderResponseBodyType.
     * 
     * @return response
     */
    public com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ContentBookmarksV2 getResponse() {
        return response;
    }


    /**
     * Sets the response value for this ReorderBookmarkAndFolderResponseBodyType.
     * 
     * @param response
     */
    public void setResponse(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ContentBookmarksV2 response) {
        this.response = response;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReorderBookmarkAndFolderResponseBodyType)) return false;
        ReorderBookmarkAndFolderResponseBodyType other = (ReorderBookmarkAndFolderResponseBodyType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.errorList==null && other.getErrorList()==null) || 
             (this.errorList!=null &&
              java.util.Arrays.equals(this.errorList, other.getErrorList()))) &&
            ((this.response==null && other.getResponse()==null) || 
             (this.response!=null &&
              this.response.equals(other.getResponse())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getErrorList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getErrorList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getErrorList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getResponse() != null) {
            _hashCode += getResponse().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReorderBookmarkAndFolderResponseBodyType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ReorderBookmarkAndFolderResponseBodyType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errorList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ErrorMessage"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "ErrorMessage"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("response");
        elemField.setXmlName(new javax.xml.namespace.QName("", "response"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "ContentBookmarksV2"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
