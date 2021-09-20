package com.parameta.api.service.impl;


import com.parameta.api.dto.EmployeeDTO;
import com.parameta.api.exception.ParametaAppException;
import com.parameta.api.model.Employee;
import com.parameta.api.service.IEmployeeService;
import com.parameta.api.service.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmployeeService implements IEmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Create an Employee
     *
     * @param employeeDTO
     * @throws ParametaAppException
     */
    @Override
    public void createEmployee(EmployeeDTO employeeDTO) throws ParametaAppException {
        try {
            Employee employee = new Employee();
            employee.setNames(employeeDTO.getNames());
            this.employeeRepository.save(employee);
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }
}


