package com.vang.main.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CreatedDate: 28/01/2025
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: CreateUserReq
 */
@Getter
@Setter
@NoArgsConstructor
public class CreateUserReq {

    @Schema(name = "email", description = "Email", example = "nanaanaana@gmail.com", required = true)
    @NotNull
    private String email;

    @Schema(name = "password", description = "Password", example = "Nanana@123", required = true)
    @NotNull
    private String password;

    @Schema(name = "companyCode", description = "Company Code", example = "UNIT2000", required = true)
    @NotNull
    private String companyCode;
}