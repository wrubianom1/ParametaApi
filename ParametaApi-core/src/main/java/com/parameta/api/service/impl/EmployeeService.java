package com.parameta.api.service.impl;


import com.parameta.api.dto.EmployeeDTO;
import com.parameta.api.exception.ParametaAppException;
import com.parameta.api.model.Employee;
import com.parameta.api.service.IEmployeeService;
import com.parameta.api.service.repository.EmployeeRepository;
import com.parameta.api.utils.UtilsEmployee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmployeeService implements IEmployeeService {

    /**
     * DAO repository employee
     */
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
            employee.setDocumentNumber(employeeDTO.getDocumentNumber());
            employee.setLastName(employeeDTO.getLastName());
            employee.setSalary(employeeDTO.getSalary());
            employee.setDateBirth(UtilsEmployee.generateDateFromStrDate(employeeDTO.getDateOfBirth()));
            employee.setDateHiring(UtilsEmployee.generateDateFromStrDate(employeeDTO.getHiringDate()));
            employee.setIdDocumentType(employeeDTO.getDocumentType().getType());
            employee.setIdPositionRole(employeeDTO.getPositionRoleType().getType());
            this.employeeRepository.save(employee);
        } catch (Exception e) {
            log.error("Se presento un error al persistir el nuevo empleado");
            throw new ParametaAppException("Se presento un error al crear el empleado");
        }
    }
}


