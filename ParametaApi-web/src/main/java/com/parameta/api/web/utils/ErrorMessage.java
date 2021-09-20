package com.parameta.api.web.utils;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Setter
@Getter
@EqualsAndHashCode
@XmlRootElement
public class ErrorMessage {

    /**
     * contains the same HTTP Status code returned by the server
     */
    @XmlElement(name = "httpStatus")
    HttpStatus httpStatus;

    /**
     * application specific error code
     */
    @XmlElement(name = "code")
    int code;

    /**
     * message describing the error
     */
    @XmlElement(name = "message")
    String message;

    /**
     * link point to page where the error message is documented
     */
    @XmlElement(name = "link")
    String link;


    @XmlElement(name = "messageCode")
    String messageCode;

    /**
     * extra information that might useful for developers
     */
    @XmlElement(name = "developerMessage")
    String developerMessage;


    @Override
    public String toString() {
        return "{" +
                " \"httpStatus\"  : \"" + httpStatus + "\" , " +
                " \"code\"  : \"" + code + "\" , " +
                " \"message\"  : \"" + message + "\" , " +
                " \"link\"  : \"" + link + "\" , " +
                " \"messageCode\"  : \"" + messageCode + "\" , " +
                " \"developerMessage\"  : \"" + developerMessage + "\" " +
                '}';
    }
}
