package com.vang.main.rest;

import com.vang.main.req.GenerateTokenReq;
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
 * Name: UserRest
 */
public interface UserRest {

    @Tag(name = "Generate secret key", description = "This function using generate the secret key")
    ResponseEntity<Object> generateSecretKey(@Parameter(name = "Request param") @RequestBody GenerateTokenReq generateTokenReq,
                                                @Parameter(hidden = true) BindingResult bindingResult,
                                                @Parameter(hidden = true) HttpServletRequest request,
                                                @Parameter(hidden = true) HttpServletResponse response);
}
