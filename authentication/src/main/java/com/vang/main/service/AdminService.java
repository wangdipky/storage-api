package com.vang.main.service;

import com.vang.main.dto.CreateUserDto;
import com.vang.main.req.CreateUserReq;
import jakarta.servlet.http.HttpServletRequest;

/**
 * CreatedDate: 28/01/2025
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: AdminService
 */
public interface AdminService {

    CreateUserDto createUser(CreateUserDto createUserDto, HttpServletRequest request);

    int countByEmail(String email);

    int countByCompanyCode(String companyCode);
}