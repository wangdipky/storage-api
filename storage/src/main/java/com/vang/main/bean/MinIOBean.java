//package com.vang.main.bean;
//
//import io.minio.MinioClient;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
///**
// * CreatedDate: 28/01/2025
// * UpdatedDate:
// * Version: 1.0
// * Author: Quang
// * Name: MinIOConfig
// */
//
//@Component
//public class MinIOBean {
//
//    @Value("${minio.secret_key}")
//    private String SECRET_KEY;
//
//    @Value("${minio.credential}")
//    private String CREDENTIAL;
//
//    @Value("${minio.endpoint}")
//    private String ENDPOINT;
//
//    @Bean
//    public MinioClient minioClient() {
//
//        return MinioClient
//                .builder()
//                .credentials(CREDENTIAL, SECRET_KEY)
//                .endpoint(ENDPOINT)
//                .build();
//    }
//}