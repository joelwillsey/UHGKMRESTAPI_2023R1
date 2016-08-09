/**
 * ListAllBookmarksResponseBodyType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl;

public class ListAllBookmarksResponseBodyType  implements java.io.Serializable {
    private com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.BookmarkedContent[] contentList;

    public ListAllBookmarksResponseBodyType() {
    }

    public ListAllBookmarksResponseBodyType(
           com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.BookmarkedContent[] contentList) {
           this.contentList = contentList;
    }


    /**
     * Gets the contentList value for this ListAllBookmarksResponseBodyType.
     * 
     * @return contentList
     */
    public com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.BookmarkedContent[] getContentList() {
        return contentList;
    }


    /**
     * Sets the contentList value for this ListAllBookmarksResponseBodyType.
     * 
     * @param contentList
     */
    public void setContentList(com.kana.contactcentre.services.model.KMBookmarkServiceV1Service_wsdl.BookmarkedContent[] contentList) {
        this.contentList = contentList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListAllBookmarksResponseBodyType)) return false;
        ListAllBookmarksResponseBodyType other = (ListAllBookmarksResponseBodyType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.contentList==null && other.getContentList()==null) || 
             (this.contentList!=null &&
              java.util.Arrays.equals(this.contentList, other.getContentList())));
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
        if (getContentList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getContentList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getContentList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ListAllBookmarksResponseBodyType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV1Service.wsdl", "ListAllBookmarksResponseBodyType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contentList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contentList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV1Service.wsdl", "BookmarkedContent"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "BookmarkedContent"));
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
