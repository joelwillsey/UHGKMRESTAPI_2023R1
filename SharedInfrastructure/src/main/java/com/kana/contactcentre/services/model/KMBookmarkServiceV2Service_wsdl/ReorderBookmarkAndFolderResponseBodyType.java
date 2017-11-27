/**
 * ReorderBookmarkAndFolderResponseBodyType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl;

public class ReorderBookmarkAndFolderResponseBodyType  implements java.io.Serializable {
    private com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ErrorMessage[] errorList;

    private boolean toReturn;

    private com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ContentBookmarksV2 newTree;

    public ReorderBookmarkAndFolderResponseBodyType() {
    }

    public ReorderBookmarkAndFolderResponseBodyType(
           com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ErrorMessage[] errorList,
           boolean toReturn,
           com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ContentBookmarksV2 newTree) {
           this.errorList = errorList;
           this.toReturn = toReturn;
           this.newTree = newTree;
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
     * Gets the toReturn value for this ReorderBookmarkAndFolderResponseBodyType.
     * 
     * @return toReturn
     */
    public boolean isToReturn() {
        return toReturn;
    }


    /**
     * Sets the toReturn value for this ReorderBookmarkAndFolderResponseBodyType.
     * 
     * @param toReturn
     */
    public void setToReturn(boolean toReturn) {
        this.toReturn = toReturn;
    }


    /**
     * Gets the newTree value for this ReorderBookmarkAndFolderResponseBodyType.
     * 
     * @return newTree
     */
    public com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ContentBookmarksV2 getNewTree() {
        return newTree;
    }


    /**
     * Sets the newTree value for this ReorderBookmarkAndFolderResponseBodyType.
     * 
     * @param newTree
     */
    public void setNewTree(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.ContentBookmarksV2 newTree) {
        this.newTree = newTree;
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
            this.toReturn == other.isToReturn() &&
            ((this.newTree==null && other.getNewTree()==null) || 
             (this.newTree!=null &&
              this.newTree.equals(other.getNewTree())));
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
        _hashCode += (isToReturn() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getNewTree() != null) {
            _hashCode += getNewTree().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReorderBookmarkAndFolderResponseBodyType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "ReorderBookmarkAndFolderResponseBodyType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errorList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "ErrorMessage"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "ErrorMessage"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("toReturn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "toReturn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("newTree");
        elemField.setXmlName(new javax.xml.namespace.QName("", "newTree"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "ContentBookmarksV2"));
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
