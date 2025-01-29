package com.vang.main.rest.impl;

import com.vang.main.constant.BaseConstant;
import com.vang.main.dto.CreateFolderDto;
import com.vang.main.req.CreateFolderReq;
import com.vang.main.rest.FolderRest;
import com.vang.main.service.FolderService;
import io.minio.errors.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * CreatedDate: 28/01/2025
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: FolderRestImpl
 */
@RestController
@RequestMapping(BaseConstant.URI_V1 + BaseConstant.URI_STORAGE_SERVER)
public class FolderRestImpl implements FolderRest {

    private final FolderService folderService;

    @Autowired
    public FolderRestImpl(FolderService folderService) {
        this.folderService = folderService;
    }

    @PostMapping(BaseConstant.URI_CREATE_FOLDER)
    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<Object> createFolder(CreateFolderReq createFolderReq, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        CreateFolderDto createFolderDto = new CreateFolderDto();
        BeanUtils.copyProperties(createFolderReq, createFolderDto);
        return new ResponseEntity<>(folderService.createFolder(createFolderDto), HttpStatus.OK);
    }
}