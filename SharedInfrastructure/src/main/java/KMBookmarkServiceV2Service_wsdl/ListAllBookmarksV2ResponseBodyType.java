/**
 * ListAllBookmarksV2ResponseBodyType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package KMBookmarkServiceV2Service_wsdl;

public class ListAllBookmarksV2ResponseBodyType  implements java.io.Serializable {
    private KMBookmarkServiceV2Service_wsdl.BookmarkFolderContents[] contentAndFolderList;

    public ListAllBookmarksV2ResponseBodyType() {
    }

    public ListAllBookmarksV2ResponseBodyType(
           KMBookmarkServiceV2Service_wsdl.BookmarkFolderContents[] contentAndFolderList) {
           this.contentAndFolderList = contentAndFolderList;
    }


    /**
     * Gets the contentAndFolderList value for this ListAllBookmarksV2ResponseBodyType.
     * 
     * @return contentAndFolderList
     */
    public KMBookmarkServiceV2Service_wsdl.BookmarkFolderContents[] getContentAndFolderList() {
        return contentAndFolderList;
    }


    /**
     * Sets the contentAndFolderList value for this ListAllBookmarksV2ResponseBodyType.
     * 
     * @param contentAndFolderList
     */
    public void setContentAndFolderList(KMBookmarkServiceV2Service_wsdl.BookmarkFolderContents[] contentAndFolderList) {
        this.contentAndFolderList = contentAndFolderList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListAllBookmarksV2ResponseBodyType)) return false;
        ListAllBookmarksV2ResponseBodyType other = (ListAllBookmarksV2ResponseBodyType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.contentAndFolderList==null && other.getContentAndFolderList()==null) || 
             (this.contentAndFolderList!=null &&
              java.util.Arrays.equals(this.contentAndFolderList, other.getContentAndFolderList())));
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
        if (getContentAndFolderList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getContentAndFolderList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getContentAndFolderList(), i);
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
        new org.apache.axis.description.TypeDesc(ListAllBookmarksV2ResponseBodyType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "ListAllBookmarksV2ResponseBodyType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contentAndFolderList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contentAndFolderList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "BookmarkFolderContents"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "BookmarkFolderContents"));
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
