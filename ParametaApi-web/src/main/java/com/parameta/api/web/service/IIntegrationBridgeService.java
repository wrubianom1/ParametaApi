package com.parameta.api.web.service;

import com.parameta.api.dto.EmployeeDTO;
import com.parameta.api.exception.ParametaAppException;

public interface IIntegrationBridgeService {

    /**
     * Create an Employee
     *
     * @throws ParametaAppException
     */
    void createEmployee(EmployeeDTO employeeDTO) throws ParametaAppException;

}
