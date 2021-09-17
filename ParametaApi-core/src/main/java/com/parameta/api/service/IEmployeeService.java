package com.parameta.api.service;

import com.parameta.api.dto.EmployeeDTO;
import com.parameta.api.exception.ParametaAppException;

public interface IEmployeeService {

    /**
     * Create an Employee
     *
     * @param employeeDTO
     * @throws ParametaAppException
     */
    void createEmployee(EmployeeDTO employeeDTO) throws ParametaAppException;

}
