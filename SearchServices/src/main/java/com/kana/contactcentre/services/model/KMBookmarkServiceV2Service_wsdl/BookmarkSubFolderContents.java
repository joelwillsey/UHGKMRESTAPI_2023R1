/**
 * BookmarkSubFolderContents.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl;

public class BookmarkSubFolderContents  implements java.io.Serializable {
    private com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.BookmarkFolderContent[] subSubFolders;

    private com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.BookmarkFolderContent bookmarkFolderContent;

    public BookmarkSubFolderContents() {
    }

    public BookmarkSubFolderContents(
           com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.BookmarkFolderContent[] subSubFolders,
           com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.BookmarkFolderContent bookmarkFolderContent) {
           this.subSubFolders = subSubFolders;
           this.bookmarkFolderContent = bookmarkFolderContent;
    }


    /**
     * Gets the subSubFolders value for this BookmarkSubFolderContents.
     * 
     * @return subSubFolders
     */
    public com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.BookmarkFolderContent[] getSubSubFolders() {
        return subSubFolders;
    }


    /**
     * Sets the subSubFolders value for this BookmarkSubFolderContents.
     * 
     * @param subSubFolders
     */
    public void setSubSubFolders(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.BookmarkFolderContent[] subSubFolders) {
        this.subSubFolders = subSubFolders;
    }


    /**
     * Gets the bookmarkFolderContent value for this BookmarkSubFolderContents.
     * 
     * @return bookmarkFolderContent
     */
    public com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.BookmarkFolderContent getBookmarkFolderContent() {
        return bookmarkFolderContent;
    }


    /**
     * Sets the bookmarkFolderContent value for this BookmarkSubFolderContents.
     * 
     * @param bookmarkFolderContent
     */
    public void setBookmarkFolderContent(com.kana.contactcentre.services.model.KMBookmarkServiceV2Service_wsdl.BookmarkFolderContent bookmarkFolderContent) {
        this.bookmarkFolderContent = bookmarkFolderContent;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BookmarkSubFolderContents)) return false;
        BookmarkSubFolderContents other = (BookmarkSubFolderContents) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.subSubFolders==null && other.getSubSubFolders()==null) || 
             (this.subSubFolders!=null &&
              java.util.Arrays.equals(this.subSubFolders, other.getSubSubFolders()))) &&
            ((this.bookmarkFolderContent==null && other.getBookmarkFolderContent()==null) || 
             (this.bookmarkFolderContent!=null &&
              this.bookmarkFolderContent.equals(other.getBookmarkFolderContent())));
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
        if (getSubSubFolders() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSubSubFolders());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSubSubFolders(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getBookmarkFolderContent() != null) {
            _hashCode += getBookmarkFolderContent().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BookmarkSubFolderContents.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "BookmarkSubFolderContents"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subSubFolders");
        elemField.setXmlName(new javax.xml.namespace.QName("", "subSubFolders"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "BookmarkFolderContent"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "BookmarkFolderContent"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bookmarkFolderContent");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bookmarkFolderContent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/KMBookmarkServiceV2Service.wsdl", "BookmarkFolderContent"));
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
