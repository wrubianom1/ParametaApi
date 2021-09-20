package com.parameta.api.web.ws.endpoint;

import com.parameta.api.dto.DocumentType;
import com.parameta.api.dto.EmployeeDTO;
import com.parameta.api.dto.PositionRoleType;
import com.parameta.api.exception.ParametaAppException;
import com.parameta.api.service.IEmployeeService;
import com.parameta.api.web.ws.dto.EmployeeRequest;
import com.parameta.api.web.ws.dto.EmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
public class EmployeeEndpoint {

    private static final String NAMESPACE = "http://www.parameta.com/api/web/ws/dto";

    @Autowired
    private IEmployeeService iEmployeeService;

    /**
     * Endopoint para registrar un empleado
     *
     * @param request
     * @return
     */
    @PayloadRoot(namespace = NAMESPACE, localPart = "EmployeeRequest")
    @ResponsePayload
    public EmployeeResponse createEmployee(@RequestPayload EmployeeRequest request) {
        EmployeeResponse em = new EmployeeResponse();
        try {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setDateOfBirth(request.getDateOfBirth());
            employeeDTO.setDocumentNumber(request.getDocumentNumber());
            employeeDTO.setDocumentType(DocumentType.CEDULA);
            employeeDTO.setPositionRoleType(PositionRoleType.DEVELOPER);
            employeeDTO.setHiringDate(request.getHiringDate());
            employeeDTO.setLastName(request.getLastName());
            employeeDTO.setNames(request.getNames());
            employeeDTO.setSalary(request.getSalary());
            iEmployeeService.createEmployee(employeeDTO);
            em.setResultCode(1);
            em.setResultMsg("Se persistio el empleado correctamente");
        } catch (ParametaAppException e) {
            em.setResultCode(2);
            em.setResultMsg("Ocurrio un error persistiendo el empleado");
        }
        return em;
    }


}