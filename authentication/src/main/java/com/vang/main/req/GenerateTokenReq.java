package com.vang.main.req;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CreatedDate: 28/01/2025
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: GenerateTokenReq
 */
@Getter
@Setter
@NoArgsConstructor
public class GenerateTokenReq {

    private long expiredDate;
}