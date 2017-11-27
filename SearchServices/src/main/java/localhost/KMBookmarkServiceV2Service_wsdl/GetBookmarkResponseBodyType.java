/**
 * GetBookmarkResponseBodyType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.KMBookmarkServiceV2Service_wsdl;

public class GetBookmarkResponseBodyType  implements java.io.Serializable {
    private localhost.KMBookmarkServiceV2Service_wsdl.BookmarkFolderContent returnFolder;

    public GetBookmarkResponseBodyType() {
    }

    public GetBookmarkResponseBodyType(
           localhost.KMBookmarkServiceV2Service_wsdl.BookmarkFolderContent returnFolder) {
           this.returnFolder = returnFolder;
    }


    /**
     * Gets the returnFolder value for this GetBookmarkResponseBodyType.
     * 
     * @return returnFolder
     */
    public localhost.KMBookmarkServiceV2Service_wsdl.BookmarkFolderContent getReturnFolder() {
        return returnFolder;
    }


    /**
     * Sets the returnFolder value for this GetBookmarkResponseBodyType.
     * 
     * @param returnFolder
     */
    public void setReturnFolder(localhost.KMBookmarkServiceV2Service_wsdl.BookmarkFolderContent returnFolder) {
        this.returnFolder = returnFolder;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetBookmarkResponseBodyType)) return false;
        GetBookmarkResponseBodyType other = (GetBookmarkResponseBodyType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.returnFolder==null && other.getReturnFolder()==null) || 
             (this.returnFolder!=null &&
              this.returnFolder.equals(other.getReturnFolder())));
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
        if (getReturnFolder() != null) {
            _hashCode += getReturnFolder().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetBookmarkResponseBodyType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "GetBookmarkResponseBodyType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnFolder");
        elemField.setXmlName(new javax.xml.namespace.QName("", "returnFolder"));
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
