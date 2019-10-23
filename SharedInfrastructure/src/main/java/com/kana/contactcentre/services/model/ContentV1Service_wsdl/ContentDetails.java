/**
 * ContentDetails.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.ContentV1Service_wsdl;

public class ContentDetails  implements java.io.Serializable {
    private java.lang.String id;

    private com.kana.contactcentre.services.model.ContentV1Service_wsdl.StringItem[] translatedTo;

    private java.lang.String language;

    private java.lang.String lastModifiedDate;

    private float averageRating;

    private java.lang.String body;

    private java.lang.String publishedId;

    private java.lang.String contentCategory;

    private boolean isFeatured;

    private java.lang.String version;

    private boolean isDeleted;

    private java.lang.String title;

    private java.lang.String locale;

    private java.math.BigInteger numberOfRatings;

    private java.lang.String contentType;

    private java.lang.String description;

    private java.math.BigInteger viewCount;

    public ContentDetails() {
    }

    public ContentDetails(
           java.lang.String id,
           com.kana.contactcentre.services.model.ContentV1Service_wsdl.StringItem[] translatedTo,
           java.lang.String language,
           java.lang.String lastModifiedDate,
           float averageRating,
           java.lang.String body,
           java.lang.String publishedId,
           java.lang.String contentCategory,
           boolean isFeatured,
           java.lang.String version,
           boolean isDeleted,
           java.lang.String title,
           java.lang.String locale,
           java.math.BigInteger numberOfRatings,
           java.lang.String contentType,
           java.lang.String description,
           java.math.BigInteger viewCount) {
           this.id = id;
           this.translatedTo = translatedTo;
           this.language = language;
           this.lastModifiedDate = lastModifiedDate;
           this.averageRating = averageRating;
           this.body = body;
           this.publishedId = publishedId;
           this.contentCategory = contentCategory;
           this.isFeatured = isFeatured;
           this.version = version;
           this.isDeleted = isDeleted;
           this.title = title;
           this.locale = locale;
           this.numberOfRatings = numberOfRatings;
           this.contentType = contentType;
           this.description = description;
           this.viewCount = viewCount;
    }


    /**
     * Gets the id value for this ContentDetails.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this ContentDetails.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the translatedTo value for this ContentDetails.
     * 
     * @return translatedTo
     */
    public com.kana.contactcentre.services.model.ContentV1Service_wsdl.StringItem[] getTranslatedTo() {
        return translatedTo;
    }


    /**
     * Sets the translatedTo value for this ContentDetails.
     * 
     * @param translatedTo
     */
    public void setTranslatedTo(com.kana.contactcentre.services.model.ContentV1Service_wsdl.StringItem[] translatedTo) {
        this.translatedTo = translatedTo;
    }


    /**
     * Gets the language value for this ContentDetails.
     * 
     * @return language
     */
    public java.lang.String getLanguage() {
        return language;
    }


    /**
     * Sets the language value for this ContentDetails.
     * 
     * @param language
     */
    public void setLanguage(java.lang.String language) {
        this.language = language;
    }


    /**
     * Gets the lastModifiedDate value for this ContentDetails.
     * 
     * @return lastModifiedDate
     */
    public java.lang.String getLastModifiedDate() {
        return lastModifiedDate;
    }


    /**
     * Sets the lastModifiedDate value for this ContentDetails.
     * 
     * @param lastModifiedDate
     */
    public void setLastModifiedDate(java.lang.String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }


    /**
     * Gets the averageRating value for this ContentDetails.
     * 
     * @return averageRating
     */
    public float getAverageRating() {
        return averageRating;
    }


    /**
     * Sets the averageRating value for this ContentDetails.
     * 
     * @param averageRating
     */
    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }


    /**
     * Gets the body value for this ContentDetails.
     * 
     * @return body
     */
    public java.lang.String getBody() {
        return body;
    }


    /**
     * Sets the body value for this ContentDetails.
     * 
     * @param body
     */
    public void setBody(java.lang.String body) {
        this.body = body;
    }


    /**
     * Gets the publishedId value for this ContentDetails.
     * 
     * @return publishedId
     */
    public java.lang.String getPublishedId() {
        return publishedId;
    }


    /**
     * Sets the publishedId value for this ContentDetails.
     * 
     * @param publishedId
     */
    public void setPublishedId(java.lang.String publishedId) {
        this.publishedId = publishedId;
    }


    /**
     * Gets the contentCategory value for this ContentDetails.
     * 
     * @return contentCategory
     */
    public java.lang.String getContentCategory() {
        return contentCategory;
    }


    /**
     * Sets the contentCategory value for this ContentDetails.
     * 
     * @param contentCategory
     */
    public void setContentCategory(java.lang.String contentCategory) {
        this.contentCategory = contentCategory;
    }


    /**
     * Gets the isFeatured value for this ContentDetails.
     * 
     * @return isFeatured
     */
    public boolean isIsFeatured() {
        return isFeatured;
    }


    /**
     * Sets the isFeatured value for this ContentDetails.
     * 
     * @param isFeatured
     */
    public void setIsFeatured(boolean isFeatured) {
        this.isFeatured = isFeatured;
    }


    /**
     * Gets the version value for this ContentDetails.
     * 
     * @return version
     */
    public java.lang.String getVersion() {
        return version;
    }


    /**
     * Sets the version value for this ContentDetails.
     * 
     * @param version
     */
    public void setVersion(java.lang.String version) {
        this.version = version;
    }


    /**
     * Gets the isDeleted value for this ContentDetails.
     * 
     * @return isDeleted
     */
    public boolean isIsDeleted() {
        return isDeleted;
    }


    /**
     * Sets the isDeleted value for this ContentDetails.
     * 
     * @param isDeleted
     */
    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }


    /**
     * Gets the title value for this ContentDetails.
     * 
     * @return title
     */
    public java.lang.String getTitle() {
        return title;
    }


    /**
     * Sets the title value for this ContentDetails.
     * 
     * @param title
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }


    /**
     * Gets the locale value for this ContentDetails.
     * 
     * @return locale
     */
    public java.lang.String getLocale() {
        return locale;
    }


    /**
     * Sets the locale value for this ContentDetails.
     * 
     * @param locale
     */
    public void setLocale(java.lang.String locale) {
        this.locale = locale;
    }


    /**
     * Gets the numberOfRatings value for this ContentDetails.
     * 
     * @return numberOfRatings
     */
    public java.math.BigInteger getNumberOfRatings() {
        return numberOfRatings;
    }


    /**
     * Sets the numberOfRatings value for this ContentDetails.
     * 
     * @param numberOfRatings
     */
    public void setNumberOfRatings(java.math.BigInteger numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }


    /**
     * Gets the contentType value for this ContentDetails.
     * 
     * @return contentType
     */
    public java.lang.String getContentType() {
        return contentType;
    }


    /**
     * Sets the contentType value for this ContentDetails.
     * 
     * @param contentType
     */
    public void setContentType(java.lang.String contentType) {
        this.contentType = contentType;
    }


    /**
     * Gets the description value for this ContentDetails.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this ContentDetails.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the viewCount value for this ContentDetails.
     * 
     * @return viewCount
     */
    public java.math.BigInteger getViewCount() {
        return viewCount;
    }


    /**
     * Sets the viewCount value for this ContentDetails.
     * 
     * @param viewCount
     */
    public void setViewCount(java.math.BigInteger viewCount) {
        this.viewCount = viewCount;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ContentDetails)) return false;
        ContentDetails other = (ContentDetails) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.translatedTo==null && other.getTranslatedTo()==null) || 
             (this.translatedTo!=null &&
              java.util.Arrays.equals(this.translatedTo, other.getTranslatedTo()))) &&
            ((this.language==null && other.getLanguage()==null) || 
             (this.language!=null &&
              this.language.equals(other.getLanguage()))) &&
            ((this.lastModifiedDate==null && other.getLastModifiedDate()==null) || 
             (this.lastModifiedDate!=null &&
              this.lastModifiedDate.equals(other.getLastModifiedDate()))) &&
            this.averageRating == other.getAverageRating() &&
            ((this.body==null && other.getBody()==null) || 
             (this.body!=null &&
              this.body.equals(other.getBody()))) &&
            ((this.publishedId==null && other.getPublishedId()==null) || 
             (this.publishedId!=null &&
              this.publishedId.equals(other.getPublishedId()))) &&
            ((this.contentCategory==null && other.getContentCategory()==null) || 
             (this.contentCategory!=null &&
              this.contentCategory.equals(other.getContentCategory()))) &&
            this.isFeatured == other.isIsFeatured() &&
            ((this.version==null && other.getVersion()==null) || 
             (this.version!=null &&
              this.version.equals(other.getVersion()))) &&
            this.isDeleted == other.isIsDeleted() &&
            ((this.title==null && other.getTitle()==null) || 
             (this.title!=null &&
              this.title.equals(other.getTitle()))) &&
            ((this.locale==null && other.getLocale()==null) || 
             (this.locale!=null &&
              this.locale.equals(other.getLocale()))) &&
            ((this.numberOfRatings==null && other.getNumberOfRatings()==null) || 
             (this.numberOfRatings!=null &&
              this.numberOfRatings.equals(other.getNumberOfRatings()))) &&
            ((this.contentType==null && other.getContentType()==null) || 
             (this.contentType!=null &&
              this.contentType.equals(other.getContentType()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.viewCount==null && other.getViewCount()==null) || 
             (this.viewCount!=null &&
              this.viewCount.equals(other.getViewCount())));
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
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getTranslatedTo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTranslatedTo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTranslatedTo(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getLanguage() != null) {
            _hashCode += getLanguage().hashCode();
        }
        if (getLastModifiedDate() != null) {
            _hashCode += getLastModifiedDate().hashCode();
        }
        _hashCode += new Float(getAverageRating()).hashCode();
        if (getBody() != null) {
            _hashCode += getBody().hashCode();
        }
        if (getPublishedId() != null) {
            _hashCode += getPublishedId().hashCode();
        }
        if (getContentCategory() != null) {
            _hashCode += getContentCategory().hashCode();
        }
        _hashCode += (isIsFeatured() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getVersion() != null) {
            _hashCode += getVersion().hashCode();
        }
        _hashCode += (isIsDeleted() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getTitle() != null) {
            _hashCode += getTitle().hashCode();
        }
        if (getLocale() != null) {
            _hashCode += getLocale().hashCode();
        }
        if (getNumberOfRatings() != null) {
            _hashCode += getNumberOfRatings().hashCode();
        }
        if (getContentType() != null) {
            _hashCode += getContentType().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getViewCount() != null) {
            _hashCode += getViewCount().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ContentDetails.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "ContentDetails"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("translatedTo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "translatedTo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/ContentV1Service.wsdl", "StringItem"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "StringItem"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("language");
        elemField.setXmlName(new javax.xml.namespace.QName("", "language"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastModifiedDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lastModifiedDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("averageRating");
        elemField.setXmlName(new javax.xml.namespace.QName("", "averageRating"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("body");
        elemField.setXmlName(new javax.xml.namespace.QName("", "body"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("publishedId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "publishedId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contentCategory");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contentCategory"));
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
        elemField.setFieldName("version");
        elemField.setXmlName(new javax.xml.namespace.QName("", "version"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isDeleted");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isDeleted"));
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
        elemField.setFieldName("locale");
        elemField.setXmlName(new javax.xml.namespace.QName("", "locale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberOfRatings");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numberOfRatings"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contentType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contentType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("", "description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("viewCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "viewCount"));
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
