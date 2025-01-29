package com.vang.main.rest;

import com.vang.main.req.CreateUserReq;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * CreatedDate: 28/01/2025
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: AdminRest
 */
public interface AdminRest {

    @Tag(name = "Create User", description = "This function using create new user")
    ResponseEntity<Object> createUser(@Parameter(name = "Request param") @RequestBody CreateUserReq createUserReq,
                                      @Parameter(hidden = true) BindingResult bindingResult,
                                      @Parameter(hidden = true) HttpServletRequest request,
                                      @Parameter(hidden = true) HttpServletResponse response);
}