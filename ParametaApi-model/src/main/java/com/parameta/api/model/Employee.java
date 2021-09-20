package com.parameta.api.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Data
@Getter
@Setter
@ToString
@Entity
@Table(schema = "public", name = "employee")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_id_employee_seq")
    @SequenceGenerator(name = "employee_id_employee_seq", sequenceName = "employee_id_employee_seq", allocationSize = 1)
    private Integer idEmployee;

    private String names;

    private String lastName;

    private Integer idDocumentType;

    private String documentNumber;

    private Date dateBirth;

    private Date dateHiring;

    private Integer idPositionRole;

    private Double salary;

}
