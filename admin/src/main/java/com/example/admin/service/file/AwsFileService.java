package com.example.admin.service.file;

import org.springframework.core.io.InputStreamResource;
import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface AwsFileService {
//   void upload(String key, InputStream inputStream, long size) throws IOException;
//    void upload(String key, InputStream inputStream) throws IOException;
//
//void upload(String bucketName, File file);
//    Optional<InputStreamResource> download(String key);
//
//    void deleteAll(String... key);


    boolean isExists(@NonNull String path);

    InputStream download(@NonNull String path);

    void upload(@NonNull String path, @NonNull InputStream in, long size);

//    List<String> upload(List<FileRequest> requests, PlanItemType planItemType);

//    Optional<InputStreamResource> download(String key);
    void delete(@NonNull String path);


}