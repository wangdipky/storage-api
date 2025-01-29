package com.vang.main.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * CreatedDate: 28/01/2025
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: MinIOConfig
 */

@Configuration
public class MinIOConfig {

    @Value("${minio.secret_key}")
    private String SECRET_KEY;

    @Value("${minio.credential}")
    private String CREDENTIAL;

    @Value("${minio.endpoint}")
    private String ENDPOINT;

    @Bean
    public MinioClient minioClient() {

        return MinioClient
                .builder()
                .credentials(CREDENTIAL, SECRET_KEY)
                .endpoint(ENDPOINT)
                .build();
    }
}