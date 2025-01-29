package com.vang.main.service.impl;

import com.vang.main.callapi.CreateFolderDto;
import com.vang.main.constant.BaseConstant;
import com.vang.main.dto.CreateUserDto;
import com.vang.main.entities.AuthInfo;
import com.vang.main.repositories.AuthInfoRepository;
import com.vang.main.service.AdminService;
import com.vang.main.util.CallExternalApi;
import com.vang.main.util.CoreUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * CreatedDate: 28/01/2025
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: AdminServiceImpl
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class AdminServiceImpl implements AdminService {

    private final AuthInfoRepository authInfoRepository;

    private final CallExternalApi callExternalApi;

    @Autowired
    public AdminServiceImpl(AuthInfoRepository authInfoRepository, CallExternalApi callExternalApi) {
        this.authInfoRepository = authInfoRepository;
        this.callExternalApi = callExternalApi;
    }

    @Override
    public CreateUserDto createUser(CreateUserDto createUserDto, HttpServletRequest request) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        AuthInfo authInfo = new AuthInfo();
        CreateFolderDto createFolderDto = new CreateFolderDto();
        Map<String, Object> header = new HashMap<>();

        createUserDto.setActive(Boolean.TRUE);
        createUserDto.setRole(BaseConstant.ROLE_USER);
        createUserDto.setPassword(encoder.encode(createUserDto.getPassword()));
        createUserDto.setCreatedDate(CoreUtils.getSystemDateTime());
        //copy value
        BeanUtils.copyProperties(createUserDto, authInfo);
        authInfoRepository.save(authInfo);
        //create folder
        HttpHeaders headers = new HttpHeaders();
        headers.set(BaseConstant.HEADER_AUTHORIZATION, request.getHeader(BaseConstant.HEADER_AUTHORIZATION));
        createFolderDto.setFolderName(createUserDto.getCompanyCode().toLowerCase());
        Boolean isCreatedFolder = callExternalApi.doPost(BaseConstant.EXT_API_CREATE_FOLDER, createFolderDto,Boolean.class, headers);
        BeanUtils.copyProperties(authInfo, createUserDto);
        return createUserDto;
    }

    @Override
    public int countByEmail(String email) {
        return authInfoRepository.countByEmail(email);
    }

    @Override
    public int countByCompanyCode(String companyCode) {
        return 0;
    }
}
