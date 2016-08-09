/**
 * FeedbackServiceResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kana.contactcentre.services.model.FeedbackV1Service_wsdl;

public class FeedbackServiceResponse  implements java.io.Serializable {
    private java.lang.String optionalComments;

    private java.math.BigInteger feedbackCode;

    private java.lang.String notificationName;

    private boolean notification;

    private java.math.BigInteger outcomeCode;

    private java.lang.String notificationEmail;

    public FeedbackServiceResponse() {
    }

    public FeedbackServiceResponse(
           java.lang.String optionalComments,
           java.math.BigInteger feedbackCode,
           java.lang.String notificationName,
           boolean notification,
           java.math.BigInteger outcomeCode,
           java.lang.String notificationEmail) {
           this.optionalComments = optionalComments;
           this.feedbackCode = feedbackCode;
           this.notificationName = notificationName;
           this.notification = notification;
           this.outcomeCode = outcomeCode;
           this.notificationEmail = notificationEmail;
    }


    /**
     * Gets the optionalComments value for this FeedbackServiceResponse.
     * 
     * @return optionalComments
     */
    public java.lang.String getOptionalComments() {
        return optionalComments;
    }


    /**
     * Sets the optionalComments value for this FeedbackServiceResponse.
     * 
     * @param optionalComments
     */
    public void setOptionalComments(java.lang.String optionalComments) {
        this.optionalComments = optionalComments;
    }


    /**
     * Gets the feedbackCode value for this FeedbackServiceResponse.
     * 
     * @return feedbackCode
     */
    public java.math.BigInteger getFeedbackCode() {
        return feedbackCode;
    }


    /**
     * Sets the feedbackCode value for this FeedbackServiceResponse.
     * 
     * @param feedbackCode
     */
    public void setFeedbackCode(java.math.BigInteger feedbackCode) {
        this.feedbackCode = feedbackCode;
    }


    /**
     * Gets the notificationName value for this FeedbackServiceResponse.
     * 
     * @return notificationName
     */
    public java.lang.String getNotificationName() {
        return notificationName;
    }


    /**
     * Sets the notificationName value for this FeedbackServiceResponse.
     * 
     * @param notificationName
     */
    public void setNotificationName(java.lang.String notificationName) {
        this.notificationName = notificationName;
    }


    /**
     * Gets the notification value for this FeedbackServiceResponse.
     * 
     * @return notification
     */
    public boolean isNotification() {
        return notification;
    }


    /**
     * Sets the notification value for this FeedbackServiceResponse.
     * 
     * @param notification
     */
    public void setNotification(boolean notification) {
        this.notification = notification;
    }


    /**
     * Gets the outcomeCode value for this FeedbackServiceResponse.
     * 
     * @return outcomeCode
     */
    public java.math.BigInteger getOutcomeCode() {
        return outcomeCode;
    }


    /**
     * Sets the outcomeCode value for this FeedbackServiceResponse.
     * 
     * @param outcomeCode
     */
    public void setOutcomeCode(java.math.BigInteger outcomeCode) {
        this.outcomeCode = outcomeCode;
    }


    /**
     * Gets the notificationEmail value for this FeedbackServiceResponse.
     * 
     * @return notificationEmail
     */
    public java.lang.String getNotificationEmail() {
        return notificationEmail;
    }


    /**
     * Sets the notificationEmail value for this FeedbackServiceResponse.
     * 
     * @param notificationEmail
     */
    public void setNotificationEmail(java.lang.String notificationEmail) {
        this.notificationEmail = notificationEmail;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FeedbackServiceResponse)) return false;
        FeedbackServiceResponse other = (FeedbackServiceResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.optionalComments==null && other.getOptionalComments()==null) || 
             (this.optionalComments!=null &&
              this.optionalComments.equals(other.getOptionalComments()))) &&
            ((this.feedbackCode==null && other.getFeedbackCode()==null) || 
             (this.feedbackCode!=null &&
              this.feedbackCode.equals(other.getFeedbackCode()))) &&
            ((this.notificationName==null && other.getNotificationName()==null) || 
             (this.notificationName!=null &&
              this.notificationName.equals(other.getNotificationName()))) &&
            this.notification == other.isNotification() &&
            ((this.outcomeCode==null && other.getOutcomeCode()==null) || 
             (this.outcomeCode!=null &&
              this.outcomeCode.equals(other.getOutcomeCode()))) &&
            ((this.notificationEmail==null && other.getNotificationEmail()==null) || 
             (this.notificationEmail!=null &&
              this.notificationEmail.equals(other.getNotificationEmail())));
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
        if (getOptionalComments() != null) {
            _hashCode += getOptionalComments().hashCode();
        }
        if (getFeedbackCode() != null) {
            _hashCode += getFeedbackCode().hashCode();
        }
        if (getNotificationName() != null) {
            _hashCode += getNotificationName().hashCode();
        }
        _hashCode += (isNotification() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getOutcomeCode() != null) {
            _hashCode += getOutcomeCode().hashCode();
        }
        if (getNotificationEmail() != null) {
            _hashCode += getNotificationEmail().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FeedbackServiceResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model.services.contactcentre.kana.com/FeedbackV1Service.wsdl", "FeedbackServiceResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("optionalComments");
        elemField.setXmlName(new javax.xml.namespace.QName("", "optionalComments"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("feedbackCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "feedbackCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("notificationName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "notificationName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("notification");
        elemField.setXmlName(new javax.xml.namespace.QName("", "notification"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("outcomeCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "outcomeCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("notificationEmail");
        elemField.setXmlName(new javax.xml.namespace.QName("", "notificationEmail"));
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
