/**
 * BookmarkFolderContents.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.KMBookmarkServiceV2Service_wsdl;

public class BookmarkFolderContents  implements java.io.Serializable {
    private localhost.KMBookmarkServiceV2Service_wsdl.BookmarkFolderContents[][] subFolders;

    private localhost.KMBookmarkServiceV2Service_wsdl.BookmarkFolderContent bookmarkFolderContent;

    public BookmarkFolderContents() {
    }

    public BookmarkFolderContents(
           localhost.KMBookmarkServiceV2Service_wsdl.BookmarkFolderContents[][] subFolders,
           localhost.KMBookmarkServiceV2Service_wsdl.BookmarkFolderContent bookmarkFolderContent) {
           this.subFolders = subFolders;
           this.bookmarkFolderContent = bookmarkFolderContent;
    }


    /**
     * Gets the subFolders value for this BookmarkFolderContents.
     * 
     * @return subFolders
     */
    public localhost.KMBookmarkServiceV2Service_wsdl.BookmarkFolderContents[][] getSubFolders() {
        return subFolders;
    }


    /**
     * Sets the subFolders value for this BookmarkFolderContents.
     * 
     * @param subFolders
     */
    public void setSubFolders(localhost.KMBookmarkServiceV2Service_wsdl.BookmarkFolderContents[][] subFolders) {
        this.subFolders = subFolders;
    }


    /**
     * Gets the bookmarkFolderContent value for this BookmarkFolderContents.
     * 
     * @return bookmarkFolderContent
     */
    public localhost.KMBookmarkServiceV2Service_wsdl.BookmarkFolderContent getBookmarkFolderContent() {
        return bookmarkFolderContent;
    }


    /**
     * Sets the bookmarkFolderContent value for this BookmarkFolderContents.
     * 
     * @param bookmarkFolderContent
     */
    public void setBookmarkFolderContent(localhost.KMBookmarkServiceV2Service_wsdl.BookmarkFolderContent bookmarkFolderContent) {
        this.bookmarkFolderContent = bookmarkFolderContent;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BookmarkFolderContents)) return false;
        BookmarkFolderContents other = (BookmarkFolderContents) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.subFolders==null && other.getSubFolders()==null) || 
             (this.subFolders!=null &&
              java.util.Arrays.equals(this.subFolders, other.getSubFolders()))) &&
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
        if (getSubFolders() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSubFolders());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSubFolders(), i);
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
        new org.apache.axis.description.TypeDesc(BookmarkFolderContents.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "BookmarkFolderContents"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subFolders");
        elemField.setXmlName(new javax.xml.namespace.QName("", "subFolders"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "BookmarkFolderContentsList"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "BookmarkFolderContentsList"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bookmarkFolderContent");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bookmarkFolderContent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "BookmarkFolderContent"));
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
