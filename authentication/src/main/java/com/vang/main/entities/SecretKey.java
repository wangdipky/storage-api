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
 * Name: SecretKey
 */
@Entity
@Table(name = "secret_key")
@Getter
@Setter
@NoArgsConstructor
public class SecretKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "AUTH_ID")
    private Integer authId;

    @Column(name = "SECRET_KEY")
    private String secretKey;

    @Column(name = "EXPIRED_DATE")
    private Date expiredDate;

    @Column(name = "CREATED_DATE")
    private Date createDate;

    @Column(name = "UPDATED_DATE")
    private Date updateDate;
}