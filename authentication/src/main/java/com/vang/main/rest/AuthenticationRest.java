package com.vang.main.rest;

import com.vang.main.req.AuthReq;
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
 * Name: AuthenticationRest
 */
public interface AuthenticationRest {

    @Tag(name = "Authentication", description = "This function using authenticate account")
    ResponseEntity<Object> authenticate(@Parameter(name = "Request param") @RequestBody AuthReq authReq,
                                        @Parameter(hidden = true) BindingResult bindingResult,
                                        @Parameter(hidden = true) HttpServletRequest request,
                                        @Parameter(hidden = true) HttpServletResponse response);
}