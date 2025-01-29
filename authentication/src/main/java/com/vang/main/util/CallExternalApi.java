package com.vang.main.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * CreatedDate: 28/01/2025
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: JwtConfig
 */
@Component
public class CallExternalApi {

    public <T> T doPost(String url, Object requestParam, Class<T> responseType, HttpHeaders headers) {

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(requestParam, headers), responseType).getBody();
    }
}