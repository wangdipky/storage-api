package com.vang.main.rest.impl;

import com.vang.main.constant.BaseConstant;
import com.vang.main.req.GenerateTokenReq;
import com.vang.main.rest.UserRest;
import com.vang.main.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(BaseConstant.URI_V1 + BaseConstant.URI_USER)
public class UserRestImpl implements UserRest {

    private final UserService userService;

    @Autowired
    public UserRestImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @PostMapping(BaseConstant.URI_GENERATE_SECRET_KEY)
    public ResponseEntity<Object> generateSecretKey(GenerateTokenReq generateTokenReq, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {

        return new ResponseEntity<>(userService.generateSecretKey(generateTokenReq.getExpiredDate()), HttpStatus.OK);
    }
}