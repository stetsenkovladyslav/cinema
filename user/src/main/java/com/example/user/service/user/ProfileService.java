package com.example.user.service.user;

import com.example.root.dto.user.ProfileDto;
import com.example.root.dto.user.UpdateProfileRequest;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProfileService {


    ProfileDto getProfile();

    ProfileDto updateUserProfile(UpdateProfileRequest profileRequest);
    ProfileDto addImage(MultipartFile multipartFile) throws IOException;

    InputStreamResource getImage(Long id);
    void deleteImage(Long imageId);

}
