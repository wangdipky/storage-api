package com.vang.main.service.impl;

import com.vang.main.dto.CreateFolderDto;
import com.vang.main.service.FolderService;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * CreatedDate: 28/01/2025
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: FolderServiceImpl
 */
@Service
public class FolderServiceImpl implements FolderService {

    private final MinioClient minioClient;

    @Autowired
    public FolderServiceImpl(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
    public boolean createFolder(CreateFolderDto createFolderDto) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        if(!minioClient.bucketExists(BucketExistsArgs.builder().bucket(createFolderDto.getFolderName()).build())) {

            minioClient.makeBucket(
                    MakeBucketArgs.builder()
                            .bucket(createFolderDto.getFolderName())
                            .objectLock(false).build()
            );
        }
        return true;
    }

}