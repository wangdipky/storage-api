package com.vang.main.util;

import org.apache.coyote.BadRequestException;

/**
 * CreatedDate: 28/01/2025
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: AuthenticationRestImpl
 */
public class DetailException extends BadRequestException {

    public DetailException(String message) {
        super(message);
    }
}