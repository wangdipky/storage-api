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
 * Name: AuthReq
 */
@Getter
@Setter
@NoArgsConstructor
public class AuthReq {

    @Schema(name = "email", description = "Email", example = "xxxx@gmail.com", required = true)
    @NotNull
    private String email;

    @Schema(name = "password", description = "Password", example = "xxx@123", required = true)
    @NotNull
    private String password;
}