package com.vang.main.validator;

import com.vang.main.dto.CreateUserDto;
import com.vang.main.service.AdminService;
import com.vang.main.util.CoreValidator;
import com.vang.main.util.DetailException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * CreatedDate: 28/01/2025
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: CreateUserValidator
 */
@Component
public class CreateUserValidator extends CoreValidator {

    private final AdminService adminService;

    @Autowired
    public CreateUserValidator(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void validate(Object target, HttpServletResponse httpServletResponse) throws DetailException {

        CreateUserDto createUserDto = (CreateUserDto) target;
        int checkDuplicateEmail = adminService.countByEmail(createUserDto.getEmail());
        int checkDuplicateCompanyCode = adminService.countByCompanyCode(createUserDto.getCompanyCode());
        if(checkDuplicateEmail > 0) {
            throw new DetailException("Email already exist");
        }
        if(checkDuplicateCompanyCode > 0) {
            throw new DetailException("Company code already exist");
        }
    }
}