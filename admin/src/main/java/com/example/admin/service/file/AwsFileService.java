package com.example.admin.service.file;

import org.springframework.core.io.InputStreamResource;
import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public interface AwsFileService {
    void upload(String key, InputStream inputStream) throws IOException;

    Optional<InputStreamResource> download(String key);

    void deleteAll(String... key);


}