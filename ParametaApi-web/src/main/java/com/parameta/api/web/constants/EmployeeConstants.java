package com.parameta.api.web.constants;

import com.parameta.api.exception.ParametaAppException;
import com.parameta.api.web.utils.ErrorMessage;
import org.springframework.http.HttpStatus;

import java.io.PrintWriter;
import java.io.StringWriter;

public class EmployeeConstants {

    public static final String APP_ERROR_MSG_CREATE_EMPLOYEE = "Se presento un error al crear el empleado";
    public static final Integer APP_ERROR_CODE_CREATE_EMPLOYEE = 1;

    public static final String APP_ERROR_MSG_CREATE_EMPLOYEE_DATE_BIRTH = "La fecha de nacimiento no es correcta, el formato es DD-MM-YYYY";
    public static final Integer APP_ERROR_CODE_CREATE_EMPLOYEE_DATE_BIRTH = 2;

    public static final String APP_ERROR_MSG_CREATE_EMPLOYEE_DATE_HIRING = "La fecha de contratación no es correcta, el formato es DD-MM-YYYY";
    public static final Integer APP_ERROR_CODE_CREATE_EMPLOYEE_DATE_HIRING = 3;

    public static final String APP_ERROR_MSG_CREATE_EMPLOYEE_CALL_ENDPOINT_EMP = "Ocurrio un error durante en el procesamiento de creación del empleado, contacte al administrador";
    public static final Integer APP_ERROR_CODE_CREATE_EMPLOYEE_CALL_ENDPOINT_EMP = 4;


    public static ErrorMessage extractErrorException(ParametaAppException ex, String URI) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setCode(ex.getErrorCodeEx());
        errorMessage.setMessageCode(ex.getMessageCodeEx());
        errorMessage.setMessage(ex.getMessage());
        errorMessage.setLink(URI);
        errorMessage.setHttpStatus(HttpStatus.resolve(ex.getErrorCodeEx()));
        StringWriter errorStackTrace = new StringWriter();
        ex.printStackTrace(new PrintWriter(errorStackTrace));
        return errorMessage;
    }
}
