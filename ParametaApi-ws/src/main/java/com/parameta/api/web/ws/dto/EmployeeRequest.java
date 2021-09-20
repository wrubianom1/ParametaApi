//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.7 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2021.09.18 a las 11:06:49 PM COT 
//


package com.parameta.api.web.ws.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="names" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="documentType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="documentNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dateOfBirth" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="hiringDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="positionRoleType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="salary" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "names",
    "lastName",
    "documentType",
    "documentNumber",
    "dateOfBirth",
    "hiringDate",
    "positionRoleType",
    "salary"
})
@XmlRootElement(name = "EmployeeRequest")
public class EmployeeRequest {

    @XmlElement(required = true)
    protected String names;
    @XmlElement(required = true)
    protected String lastName;
    @XmlElement(required = true)
    protected String documentType;
    @XmlElement(required = true)
    protected String documentNumber;
    @XmlElement(required = true)
    protected String dateOfBirth;
    @XmlElement(required = true)
    protected String hiringDate;
    @XmlElement(required = true)
    protected String positionRoleType;
    protected double salary;

    /**
     * Obtiene el valor de la propiedad names.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNames() {
        return names;
    }

    /**
     * Define el valor de la propiedad names.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNames(String value) {
        this.names = value;
    }

    /**
     * Obtiene el valor de la propiedad lastName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Define el valor de la propiedad lastName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Obtiene el valor de la propiedad documentType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentType() {
        return documentType;
    }

    /**
     * Define el valor de la propiedad documentType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentType(String value) {
        this.documentType = value;
    }

    /**
     * Obtiene el valor de la propiedad documentNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentNumber() {
        return documentNumber;
    }

    /**
     * Define el valor de la propiedad documentNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentNumber(String value) {
        this.documentNumber = value;
    }

    /**
     * Obtiene el valor de la propiedad dateOfBirth.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Define el valor de la propiedad dateOfBirth.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateOfBirth(String value) {
        this.dateOfBirth = value;
    }

    /**
     * Obtiene el valor de la propiedad hiringDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHiringDate() {
        return hiringDate;
    }

    /**
     * Define el valor de la propiedad hiringDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHiringDate(String value) {
        this.hiringDate = value;
    }

    /**
     * Obtiene el valor de la propiedad positionRoleType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPositionRoleType() {
        return positionRoleType;
    }

    /**
     * Define el valor de la propiedad positionRoleType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPositionRoleType(String value) {
        this.positionRoleType = value;
    }

    /**
     * Obtiene el valor de la propiedad salary.
     * 
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Define el valor de la propiedad salary.
     * 
     */
    public void setSalary(double value) {
        this.salary = value;
    }

}
