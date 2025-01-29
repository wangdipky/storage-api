package com.vang.main.rest;

import com.vang.main.req.CreateFolderReq;
import io.minio.errors.*;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * CreatedDate: 28/01/2025
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: FolderRest
 */
public interface FolderRest {

    @Tag(name = "Create folder", description = "This function using create folder space for new user")
    public ResponseEntity<Object> createFolder(@Parameter(name = "Request param") @RequestBody CreateFolderReq createFolderReq,
                                               @Parameter(hidden = true) BindingResult bindingResult,
                                               @Parameter(hidden = true) HttpServletRequest request,
                                               @Parameter(hidden = true) HttpServletResponse response) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;
}