package com.parameta.api.web.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@EqualsAndHashCode
@ToString
public class ErrorMessageWebDTO {

    /**
     * contains the same HTTP Status code returned by the server
     */
    HttpStatus httpStatus;

    /**
     * application specific error code
     */
    int code;

    /**
     * message describing the error
     */
    String message;

    /**
     * link point to page where the error message is documented
     */
    String link;

    String messageCode;

    /**
     * extra information that might useful for developers
     */
    String developerMessage;

}
