package com.vang.main.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
/**
 * CreatedDate: 28/01/2025
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: AuthInfo
 */
@Entity
@Table(name = "auth_info")
@Getter
@Setter
@NoArgsConstructor
public class AuthInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ACTIVE")
    private Boolean active;

    @Column(name = "ROLE")
    private String role;

    @Column(name = "COMPANY_CODE")
    private String companyCode;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "DELETED_DATE")
    private String deleted_date;
}