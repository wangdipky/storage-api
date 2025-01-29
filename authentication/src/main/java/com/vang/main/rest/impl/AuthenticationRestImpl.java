package com.vang.main.rest.impl;

import com.vang.main.config.JwtConfig;
import com.vang.main.constant.BaseConstant;
import com.vang.main.req.AuthReq;
import com.vang.main.rest.AuthenticationRest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * CreatedDate: 28/01/2025
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: AuthenticationRestImpl
 */
@RestController
@RequestMapping(BaseConstant.URI_V1 + BaseConstant.URI_AUTH)
public class AuthenticationRestImpl implements AuthenticationRest {

    private final AuthenticationManager authenticationManager;

    private final JwtConfig jwtConfig;

    @Autowired
    public AuthenticationRestImpl(AuthenticationManager authenticationManager, JwtConfig jwtConfig) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
    }

    @PostMapping(BaseConstant.URI_LOGIN)
    @Override
    public ResponseEntity<Object> authenticate(@Valid AuthReq authReq,
                                               BindingResult bindingResult,
                                               HttpServletRequest request,
                                               HttpServletResponse httpServletResponse) {

        //validate request param
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        //handling logic
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authReq.getEmail(), authReq.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if(authentication.isAuthenticated()) {

            return new ResponseEntity<>(jwtConfig.generateToken(authReq.getEmail()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}