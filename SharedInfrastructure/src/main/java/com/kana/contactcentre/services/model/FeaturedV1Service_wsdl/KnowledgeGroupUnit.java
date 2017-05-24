/**
 * KnowledgeGroupUnit.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.FeaturedV1Service_wsdl;

public class KnowledgeGroupUnit  implements java.io.Serializable {
    private KnowledgeUnit[] knowledgeUnits;

    private java.lang.String contentID;

    private boolean isFeatured;

    private java.lang.String title;

    private RatingInformation ratingInformation;

    private java.lang.String locale;

    private java.lang.String contentType;

    private java.math.BigInteger viewCount;

    private java.lang.String contentSource;

    public KnowledgeGroupUnit() {
    }

    public KnowledgeGroupUnit(
           KnowledgeUnit[] knowledgeUnits,
           java.lang.String contentID,
           boolean isFeatured,
           java.lang.String title,
           RatingInformation ratingInformation,
           java.lang.String locale,
           java.lang.String contentType,
           java.math.BigInteger viewCount,
           java.lang.String contentSource) {
           this.knowledgeUnits = knowledgeUnits;
           this.contentID = contentID;
           this.isFeatured = isFeatured;
           this.title = title;
           this.ratingInformation = ratingInformation;
           this.locale = locale;
           this.contentType = contentType;
           this.viewCount = viewCount;
           this.contentSource = contentSource;
    }


    /**
     * Gets the knowledgeUnits value for this KnowledgeGroupUnit.
     * 
     * @return knowledgeUnits
     */
    public KnowledgeUnit[] getKnowledgeUnits() {
        return knowledgeUnits;
    }


    /**
     * Sets the knowledgeUnits value for this KnowledgeGroupUnit.
     * 
     * @param knowledgeUnits
     */
    public void setKnowledgeUnits(KnowledgeUnit[] knowledgeUnits) {
        this.knowledgeUnits = knowledgeUnits;
    }


    /**
     * Gets the contentID value for this KnowledgeGroupUnit.
     * 
     * @return contentID
     */
    public java.lang.String getContentID() {
        return contentID;
    }


    /**
     * Sets the contentID value for this KnowledgeGroupUnit.
     * 
     * @param contentID
     */
    public void setContentID(java.lang.String contentID) {
        this.contentID = contentID;
    }


    /**
     * Gets the isFeatured value for this KnowledgeGroupUnit.
     * 
     * @return isFeatured
     */
    public boolean isIsFeatured() {
        return isFeatured;
    }


    /**
     * Sets the isFeatured value for this KnowledgeGroupUnit.
     * 
     * @param isFeatured
     */
    public void setIsFeatured(boolean isFeatured) {
        this.isFeatured = isFeatured;
    }


    /**
     * Gets the title value for this KnowledgeGroupUnit.
     * 
     * @return title
     */
    public java.lang.String getTitle() {
        return title;
    }


    /**
     * Sets the title value for this KnowledgeGroupUnit.
     * 
     * @param title
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }


    /**
     * Gets the ratingInformation value for this KnowledgeGroupUnit.
     * 
     * @return ratingInformation
     */
    public RatingInformation getRatingInformation() {
        return ratingInformation;
    }


    /**
     * Sets the ratingInformation value for this KnowledgeGroupUnit.
     * 
     * @param ratingInformation
     */
    public void setRatingInformation(RatingInformation ratingInformation) {
        this.ratingInformation = ratingInformation;
    }


    /**
     * Gets the locale value for this KnowledgeGroupUnit.
     * 
     * @return locale
     */
    public java.lang.String getLocale() {
        return locale;
    }


    /**
     * Sets the locale value for this KnowledgeGroupUnit.
     * 
     * @param locale
     */
    public void setLocale(java.lang.String locale) {
        this.locale = locale;
    }


    /**
     * Gets the contentType value for this KnowledgeGroupUnit.
     * 
     * @return contentType
     */
    public java.lang.String getContentType() {
        return contentType;
    }


    /**
     * Sets the contentType value for this KnowledgeGroupUnit.
     * 
     * @param contentType
     */
    public void setContentType(java.lang.String contentType) {
        this.contentType = contentType;
    }


    /**
     * Gets the viewCount value for this KnowledgeGroupUnit.
     * 
     * @return viewCount
     */
    public java.math.BigInteger getViewCount() {
        return viewCount;
    }


    /**
     * Sets the viewCount value for this KnowledgeGroupUnit.
     * 
     * @param viewCount
     */
    public void setViewCount(java.math.BigInteger viewCount) {
        this.viewCount = viewCount;
    }


    /**
     * Gets the contentSource value for this KnowledgeGroupUnit.
     * 
     * @return contentSource
     */
    public java.lang.String getContentSource() {
        return contentSource;
    }


    /**
     * Sets the contentSource value for this KnowledgeGroupUnit.
     * 
     * @param contentSource
     */
    public void setContentSource(java.lang.String contentSource) {
        this.contentSource = contentSource;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof KnowledgeGroupUnit)) return false;
        KnowledgeGroupUnit other = (KnowledgeGroupUnit) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.knowledgeUnits==null && other.getKnowledgeUnits()==null) || 
             (this.knowledgeUnits!=null &&
              java.util.Arrays.equals(this.knowledgeUnits, other.getKnowledgeUnits()))) &&
            ((this.contentID==null && other.getContentID()==null) || 
             (this.contentID!=null &&
              this.contentID.equals(other.getContentID()))) &&
            this.isFeatured == other.isIsFeatured() &&
            ((this.title==null && other.getTitle()==null) || 
             (this.title!=null &&
              this.title.equals(other.getTitle()))) &&
            ((this.ratingInformation==null && other.getRatingInformation()==null) || 
             (this.ratingInformation!=null &&
              this.ratingInformation.equals(other.getRatingInformation()))) &&
            ((this.locale==null && other.getLocale()==null) || 
             (this.locale!=null &&
              this.locale.equals(other.getLocale()))) &&
            ((this.contentType==null && other.getContentType()==null) || 
             (this.contentType!=null &&
              this.contentType.equals(other.getContentType()))) &&
            ((this.viewCount==null && other.getViewCount()==null) || 
             (this.viewCount!=null &&
              this.viewCount.equals(other.getViewCount()))) &&
            ((this.contentSource==null && other.getContentSource()==null) || 
             (this.contentSource!=null &&
              this.contentSource.equals(other.getContentSource())));
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
        if (getKnowledgeUnits() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getKnowledgeUnits());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getKnowledgeUnits(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getContentID() != null) {
            _hashCode += getContentID().hashCode();
        }
        _hashCode += (isIsFeatured() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getTitle() != null) {
            _hashCode += getTitle().hashCode();
        }
        if (getRatingInformation() != null) {
            _hashCode += getRatingInformation().hashCode();
        }
        if (getLocale() != null) {
            _hashCode += getLocale().hashCode();
        }
        if (getContentType() != null) {
            _hashCode += getContentType().hashCode();
        }
        if (getViewCount() != null) {
            _hashCode += getViewCount().hashCode();
        }
        if (getContentSource() != null) {
            _hashCode += getContentSource().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(KnowledgeGroupUnit.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/FeaturedV1Service.wsdl", "KnowledgeGroupUnit"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("knowledgeUnits");
        elemField.setXmlName(new javax.xml.namespace.QName("", "knowledgeUnits"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/FeaturedV1Service.wsdl", "KnowledgeUnit"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "KnowledgeUnit"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contentID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contentID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isFeatured");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isFeatured"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("title");
        elemField.setXmlName(new javax.xml.namespace.QName("", "title"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ratingInformation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ratingInformation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/FeaturedV1Service.wsdl", "RatingInformation"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("locale");
        elemField.setXmlName(new javax.xml.namespace.QName("", "locale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contentType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contentType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("viewCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "viewCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contentSource");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contentSource"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
