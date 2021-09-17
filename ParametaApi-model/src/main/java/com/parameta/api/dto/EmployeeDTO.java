package com.parameta.api.dto;

import com.parameta.api.dto.DocumentType;
import com.parameta.api.dto.PositionRoleType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    @ApiModelProperty(value = "Names", dataType = "String")
    private String names;

    @ApiModelProperty(value = "Last Names", dataType = "String")
    private String lastName;

    @ApiModelProperty(value = "Document Type", dataType = "String")
    private DocumentType documentType;

    @ApiModelProperty(value = "Document number", dataType = "String")
    private String documentNumber;

    @ApiModelProperty(value = "Date of birth", dataType = "String")
    private String dateOfBirth;

    @ApiModelProperty(value = "Hiring date", dataType = "String")
    private String hiringDate;

    @ApiModelProperty(value = "Hiring date", dataType = "com.parameta.api.dto.PositionRoleType")
    private PositionRoleType positionRoleType;

    @ApiModelProperty(value = "Salary", dataType = "Double")
    private Double salary;

}
