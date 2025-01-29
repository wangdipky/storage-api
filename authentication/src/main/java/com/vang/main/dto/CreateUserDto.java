package com.vang.main.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * CreatedDate: 28/01/2025
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: CreateUserDto
 */
@Getter
@Setter
@NoArgsConstructor
public class CreateUserDto {

    private Integer id;

    private String email;

    private String password;

    private Boolean active;

    private String role;

    private String companyCode;

    private Date createdDate;

    private String deleted_date;

}