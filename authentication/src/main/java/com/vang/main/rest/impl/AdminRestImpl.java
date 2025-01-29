package com.vang.main.rest.impl;

import com.vang.main.constant.BaseConstant;
import com.vang.main.dto.CreateUserDto;
import com.vang.main.req.CreateUserReq;
import com.vang.main.rest.AdminRest;
import com.vang.main.service.AdminService;
import com.vang.main.util.CoreRest;
import com.vang.main.validator.CreateUserValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CreatedDate: 28/01/2025
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: AuthenticationRestImpl
 */
@RestController
@RequestMapping(BaseConstant.URI_V1 + BaseConstant.URI_ADMIN)
public class AdminRestImpl extends CoreRest implements AdminRest {

    private final AdminService adminService;

    private final CreateUserValidator createUserValidator;

    public AdminRestImpl(AdminService adminService, CreateUserValidator createUserValidator) {
        this.adminService = adminService;
        this.createUserValidator = createUserValidator;
    }


    @PostMapping(BaseConstant.URI_CREATE_USER)
    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<Object> createUser(@Valid CreateUserReq createUserReq, BindingResult bindingResult, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        try {

            //validate
            if(bindingResult.hasErrors()) {

                return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
            }
            CreateUserDto createUserDto = objectMapper.convertValue(createUserReq, CreateUserDto.class);
            //check duplicate
            createUserValidator.validate(createUserDto, httpServletResponse);
            CreateUserDto response = adminService.createUser(createUserDto, httpServletRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}