/**
 * RatingInformation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.NewOrChangedV1Service_wsdl;

public class RatingInformation  implements java.io.Serializable {
    private float averageRating;

    private java.math.BigInteger numberOfRatings;

    public RatingInformation() {
    }

    public RatingInformation(
           float averageRating,
           java.math.BigInteger numberOfRatings) {
           this.averageRating = averageRating;
           this.numberOfRatings = numberOfRatings;
    }


    /**
     * Gets the averageRating value for this RatingInformation.
     * 
     * @return averageRating
     */
    public float getAverageRating() {
        return averageRating;
    }


    /**
     * Sets the averageRating value for this RatingInformation.
     * 
     * @param averageRating
     */
    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }


    /**
     * Gets the numberOfRatings value for this RatingInformation.
     * 
     * @return numberOfRatings
     */
    public java.math.BigInteger getNumberOfRatings() {
        return numberOfRatings;
    }


    /**
     * Sets the numberOfRatings value for this RatingInformation.
     * 
     * @param numberOfRatings
     */
    public void setNumberOfRatings(java.math.BigInteger numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RatingInformation)) return false;
        RatingInformation other = (RatingInformation) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.averageRating == other.getAverageRating() &&
            ((this.numberOfRatings==null && other.getNumberOfRatings()==null) || 
             (this.numberOfRatings!=null &&
              this.numberOfRatings.equals(other.getNumberOfRatings())));
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
        _hashCode += new Float(getAverageRating()).hashCode();
        if (getNumberOfRatings() != null) {
            _hashCode += getNumberOfRatings().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RatingInformation.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/NewOrChangedV1Service.wsdl", "RatingInformation"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("averageRating");
        elemField.setXmlName(new javax.xml.namespace.QName("", "averageRating"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberOfRatings");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numberOfRatings"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
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
