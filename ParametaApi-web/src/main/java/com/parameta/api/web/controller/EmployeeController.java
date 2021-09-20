package com.parameta.api.web.controller;

import com.parameta.api.dto.EmployeeDTO;
import com.parameta.api.exception.ParametaAppException;
import com.parameta.api.web.constants.EmployeeConstants;
import com.parameta.api.web.service.IIntegrationBridgeService;
import com.parameta.api.web.utils.ErrorMessage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.io.Serializable;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/employee/")
@Validated
public class EmployeeController implements Serializable {

    private IIntegrationBridgeService iIntegrationBridgeService;

    @Autowired
    public EmployeeController(IIntegrationBridgeService iIntegrationBridgeService) {
        this.iIntegrationBridgeService = iIntegrationBridgeService;
    }


    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create an Employee",
            notes = "Endpoint to create an employee")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Create employee ok", response = EmployeeDTO.class),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Error")}
    )
    public ResponseEntity<?> createEmployee(
            @Valid @RequestBody @ApiParam(name = "employeeDTO", type = "EmployeeDTO", required = true) EmployeeDTO employeeDTO) {
        try {
            iIntegrationBridgeService.createEmployee(employeeDTO);
            return new ResponseEntity<>("Register OK", HttpStatus.OK);
        } catch (ParametaAppException e) {
            log.error("Error createEmployee {} ", e);
            ErrorMessage errorMessage = EmployeeConstants.extractErrorException(e, "Create Employee");
            return new ResponseEntity<>(errorMessage.toString(), errorMessage.getHttpStatus());
        } catch (Exception e) {
            log.error("Error createEmployee {} ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        log.error("Se presento un error al validar el formulatio {} ", e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


}