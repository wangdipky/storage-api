package com.vang.main.constant;

/**
 * CreatedDate: 28/01/2025
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: BaseConstant
 */
public class BaseConstant {

    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String HEADER_AUTHORIZATION_BEARER = "Bearer ";

    public static final String ROLE_USER = "USER";

    public static final String KEY_USERNAME = "username";
    public static final String KEY_CREATED = "created";
    public static final String KEY_ROLE = "role";

    public static final String URI_V1 = "/api/v1";
    public static final String URI_AUTH = "/auth";
    public static final String URI_USER = "/user";
    public static final String URI_ADMIN = "/admin";
    public static final String URI_LOGIN = "/login";
    public static final String URI_CREATE_USER = "/create-user";
    public static final String URI_GENERATE_SECRET_KEY = "/generate-secret-key";

    public static final String EXT_API_CREATE_FOLDER = "http://127.0.0.1:7000/api/v1/storage-server/create-folder";
}