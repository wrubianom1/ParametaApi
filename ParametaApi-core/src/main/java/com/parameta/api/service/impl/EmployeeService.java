package com.parameta.api.service.impl;


import com.parameta.api.dto.EmployeeDTO;
import com.parameta.api.exception.ParametaAppException;
import com.parameta.api.service.IEmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmployeeService implements IEmployeeService {


    @Autowired
    public EmployeeService() {

    }

    /**
     * Create an Employee
     *
     * @param employeeDTO
     * @throws ParametaAppException
     */
    @Override
    public void createEmployee(EmployeeDTO employeeDTO) throws ParametaAppException {

    }
}


