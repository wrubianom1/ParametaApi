package com.parameta.api.web.controller;

import com.parameta.api.dto.EmployeeDTO;
import com.parameta.api.exception.ParametaAppException;
import com.parameta.api.service.IEmployeeService;
import com.parameta.api.web.dto.UserSessionDTO;
import com.parameta.api.web.utils.ExceptionUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/employee/")
public class EmployeeController implements Serializable {

    private UserSessionDTO userSessionDTO;
    private IEmployeeService iEmployeeService;
    private ExceptionUtils exceptionUtils;

    @Autowired
    public EmployeeController(UserSessionDTO headerDTO, IEmployeeService iEmployeeService, ExceptionUtils exceptionUtils) {
        this.userSessionDTO = headerDTO;
        this.iEmployeeService = iEmployeeService;
        this.exceptionUtils = exceptionUtils;
    }


    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create an Employee",
            notes = "Endpoint to create an employee")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Create employee ok", response = EmployeeDTO.class),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Error")}
    )
    public ResponseEntity<Object> createEmployee(
            @RequestBody @ApiParam(name = "employeeDTO", type = "EmployeeDTO", required = true) EmployeeDTO employeeDTO) {
        try {
            this.iEmployeeService.createEmployee(employeeDTO);
            return new ResponseEntity<>("Register Employee OK", HttpStatus.OK);
        } catch (ParametaAppException e) {
            log.error("Error createEmployee {} ", e);
            return new ResponseEntity<>(this.exceptionUtils.extractErrorException(e, "Create an employee"), HttpStatus.resolve(e.getHttpStatusEx()));
        } catch (Exception e) {
            log.error("Error createEmployee {} ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}