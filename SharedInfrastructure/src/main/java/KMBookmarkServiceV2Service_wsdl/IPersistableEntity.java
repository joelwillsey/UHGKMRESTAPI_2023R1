/**
 * IPersistableEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package KMBookmarkServiceV2Service_wsdl;

public class IPersistableEntity  implements java.io.Serializable {
    private KMBookmarkServiceV2Service_wsdl.IAssociationList associationList;

    private KMBookmarkServiceV2Service_wsdl.IEntityLock entityLock;

    public IPersistableEntity() {
    }

    public IPersistableEntity(
           KMBookmarkServiceV2Service_wsdl.IAssociationList associationList,
           KMBookmarkServiceV2Service_wsdl.IEntityLock entityLock) {
           this.associationList = associationList;
           this.entityLock = entityLock;
    }


    /**
     * Gets the associationList value for this IPersistableEntity.
     * 
     * @return associationList
     */
    public KMBookmarkServiceV2Service_wsdl.IAssociationList getAssociationList() {
        return associationList;
    }


    /**
     * Sets the associationList value for this IPersistableEntity.
     * 
     * @param associationList
     */
    public void setAssociationList(KMBookmarkServiceV2Service_wsdl.IAssociationList associationList) {
        this.associationList = associationList;
    }


    /**
     * Gets the entityLock value for this IPersistableEntity.
     * 
     * @return entityLock
     */
    public KMBookmarkServiceV2Service_wsdl.IEntityLock getEntityLock() {
        return entityLock;
    }


    /**
     * Sets the entityLock value for this IPersistableEntity.
     * 
     * @param entityLock
     */
    public void setEntityLock(KMBookmarkServiceV2Service_wsdl.IEntityLock entityLock) {
        this.entityLock = entityLock;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IPersistableEntity)) return false;
        IPersistableEntity other = (IPersistableEntity) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.associationList==null && other.getAssociationList()==null) || 
             (this.associationList!=null &&
              this.associationList.equals(other.getAssociationList()))) &&
            ((this.entityLock==null && other.getEntityLock()==null) || 
             (this.entityLock!=null &&
              this.entityLock.equals(other.getEntityLock())));
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
        if (getAssociationList() != null) {
            _hashCode += getAssociationList().hashCode();
        }
        if (getEntityLock() != null) {
            _hashCode += getEntityLock().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IPersistableEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "IPersistableEntity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("associationList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "associationList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "IAssociationList"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entityLock");
        elemField.setXmlName(new javax.xml.namespace.QName("", "entityLock"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost:80/KMBookmarkServiceV2Service.wsdl", "IEntityLock"));
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
