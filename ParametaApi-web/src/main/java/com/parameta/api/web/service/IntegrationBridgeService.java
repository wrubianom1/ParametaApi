package com.parameta.api.web.service;

import com.parameta.api.client.EmployeeRequest;
import com.parameta.api.client.EmployeeResponse;
import com.parameta.api.config.SOAPAdapterConnector;
import com.parameta.api.dto.EmployeeDTO;
import com.parameta.api.exception.ParametaAppException;
import com.parameta.api.utils.UtilsEmployee;
import com.parameta.api.web.constants.EmployeeConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Slf4j
@Service
public class IntegrationBridgeService implements IIntegrationBridgeService {

    /**
     * adapterSoap to call WS
     */
    private SOAPAdapterConnector soapAdapterConnector;

    @Value("${propertiesApi.endpointEmployee.create}")
    private String endpointEmployeeCreate;

    /**
     * @param soapAdapterConnector
     */
    @Autowired
    public IntegrationBridgeService(SOAPAdapterConnector soapAdapterConnector) {
        this.soapAdapterConnector = soapAdapterConnector;
    }


    /**
     * @param employeeDTO
     * @throws ParametaAppException
     */
    @Override
    public void createEmployee(EmployeeDTO employeeDTO) throws ParametaAppException {
        if (employeeDTO != null) {
            if (!UtilsEmployee.isValidDate(employeeDTO.getDateOfBirth())) {
                log.debug("validation error : fecha de nacimiento no valida");
                throw new ParametaAppException(EmployeeConstants.APP_ERROR_CODE_CREATE_EMPLOYEE_DATE_BIRTH,
                        EmployeeConstants.APP_ERROR_MSG_CREATE_EMPLOYEE_DATE_BIRTH, HttpStatus.BAD_REQUEST.value());
            }
            if (!UtilsEmployee.isValidDate(employeeDTO.getHiringDate())) {
                log.debug("validation error : fecha de contratación no valida");
                throw new ParametaAppException(EmployeeConstants.APP_ERROR_CODE_CREATE_EMPLOYEE_DATE_HIRING,
                        EmployeeConstants.APP_ERROR_MSG_CREATE_EMPLOYEE_DATE_HIRING, HttpStatus.BAD_REQUEST.value());
            }
            try {
                if (!UtilsEmployee.isLimitMayorAge(employeeDTO.getDateOfBirth())) {
                    throw new ParametaAppException(EmployeeConstants.APP_ERROR_CODE_CREATE_EMPLOYEE_LIMIT_MAYOR_AGE,
                            EmployeeConstants.APP_ERROR_MSG_CREATE_EMPLOYEE_LIMIT_MAYOR_AGE, HttpStatus.BAD_REQUEST.value());
                }
            } catch (ParseException e) {
                log.error("Error parsing date of birth to mayor age");
            }
            EmployeeRequest request = new EmployeeRequest();
            request.setNames(employeeDTO.getNames());
            request.setLastName(employeeDTO.getLastName());
            request.setDateOfBirth(employeeDTO.getDateOfBirth());
            request.setDocumentNumber(employeeDTO.getDocumentNumber());
            request.setHiringDate(employeeDTO.getHiringDate());
            request.setDocumentType(employeeDTO.getDocumentType().getType());
            request.setPositionRoleType(employeeDTO.getPositionRoleType().getType());
            request.setSalary(employeeDTO.getSalary());
            try {
                EmployeeResponse response = (EmployeeResponse) this.soapAdapterConnector.callWebService(this.endpointEmployeeCreate, request);
                if (response.getResultCode() == 2) {
                    // codigo de error en persistencia del empleado
                    throw new ParametaAppException(EmployeeConstants.APP_ERROR_CODE_CREATE_EMPLOYEE_CALL_ENDPOINT_EMP,
                            EmployeeConstants.APP_ERROR_MSG_CREATE_EMPLOYEE_CALL_ENDPOINT_EMP, HttpStatus.INTERNAL_SERVER_ERROR.value());
                }
            } catch (Exception e) {
                log.error("Se presento un error al llamar al endpoint WS");
                throw new ParametaAppException(EmployeeConstants.APP_ERROR_CODE_CREATE_EMPLOYEE_CALL_ENDPOINT_EMP,
                        EmployeeConstants.APP_ERROR_MSG_CREATE_EMPLOYEE_CALL_ENDPOINT_EMP, HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
        } else {
            throw new ParametaAppException(EmployeeConstants.APP_ERROR_CODE_CREATE_EMPLOYEE,
                    EmployeeConstants.APP_ERROR_MSG_CREATE_EMPLOYEE, HttpStatus.BAD_REQUEST.value());
        }
    }


}