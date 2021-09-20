package com.parameta.api.web.service;

import com.parameta.api.client.EmployeeRequest;
import com.parameta.api.client.EmployeeResponse;
import com.parameta.api.config.SOAPAdapterConnector;
import com.parameta.api.dto.EmployeeDTO;
import com.parameta.api.exception.ParametaAppException;
import com.parameta.api.web.constants.EmployeeConstants;
import com.parameta.api.web.utils.UtilsEmployee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IntegrationBridgeService implements IIntegrationBridgeService {

    private SOAPAdapterConnector soapAdapterConnector;

    @Value("${propertiesApi.endpointEmployee.create}")
    private String endpointEmployeeCreate;

    @Autowired
    public IntegrationBridgeService(SOAPAdapterConnector soapAdapterConnector) {
        this.soapAdapterConnector = soapAdapterConnector;
    }


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
            EmployeeRequest request = new EmployeeRequest();
            request.setNames(employeeDTO.getNames());
            request.setLastName(employeeDTO.getLastName());
            request.setDateOfBirth(employeeDTO.getDateOfBirth());
            request.setDocumentNumber(employeeDTO.getDocumentNumber());
            request.setHiringDate(employeeDTO.getHiringDate());
            request.setDocumentType(employeeDTO.getDocumentType().name());
            request.setSalary(employeeDTO.getSalary());
            try {
                EmployeeResponse response = (EmployeeResponse) this.soapAdapterConnector.callWebService(this.endpointEmployeeCreate, request);
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