package com.vang.main.util;

import jakarta.servlet.http.HttpServletResponse;

/**
 * CreatedDate: 28/01/2025
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: CoreValidator
 */
public abstract class CoreValidator {

    public abstract void validate(Object target, HttpServletResponse httpServletResponse) throws DetailException;
}
