package com.example.user.service.user;

import com.example.root.aws.AwsFileService;
import com.example.root.dto.user.ProfileDto;
import com.example.root.dto.user.UpdateProfileRequest;
import com.example.root.enums.ImageFormat;
import com.example.root.exception.FileFormatException;
import com.example.root.model.Image;
import com.example.root.model.User;
import com.example.user.mapper.ProfileMapper;
import com.example.user.mapper.UserMapper;
import com.example.user.repository.ImageRepository;
import com.example.user.repository.UserRepository;
import com.example.user.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;
    private final AuthService authService;
    private final ProfileMapper profileMapper;
    private final AwsFileService awsFileService;
    private final ImageRepository imageRepository;
    private final UserMapper userMapper;


    @Override
    public ProfileDto getProfile() {
        Long id = authService.getAuthenticatedUser().getId();
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id:{" + id + "} does not exist"));
        return profileMapper.mapToDTO(user);
    }

    @Override
    public ProfileDto updateUserProfile(UpdateProfileRequest profileRequest) {
        Long id = authService.getAuthenticatedUser().getId();
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id:{" + id + "} does not exist"));
        return profileMapper.mapToDTO(userRepository.save(profileMapper.update(user, profileRequest)));
    }

    @Override
    @Transactional
    public ProfileDto addImage(MultipartFile multipartFile) throws IOException {
        Long id = authService.getAuthenticatedUser().getId();
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id:{" + id + "} does not exist"));

        var image = new Image();
        var originalFilename = multipartFile.getOriginalFilename();
        if (originalFilename != null) {
            var pointIndex = originalFilename.indexOf('.');
            image.setImageName(originalFilename.substring(0, pointIndex));
            image.setFormat(ImageFormat.valueOf(originalFilename.substring(pointIndex + 1).toUpperCase()));
            image = imageRepository.save(image);
        } else {
            throw new FileFormatException("Invalid file");
        }
        user.setImage(image);
        awsFileService.upload(image.getId().toString(), multipartFile.getInputStream());
        return profileMapper.mapToDTO(userRepository.save(user));
    }

    @Override
    public InputStreamResource getImage(Long imageId) {
        return awsFileService.download(imageId.toString()).orElseThrow();
    }


    @Override
    @Transactional
    public void deleteImage(Long imageId) {
        if (!imageRepository.existsById(imageId)) {
            throw new NoSuchElementException();
        }
        imageRepository.deleteById(imageId);
        awsFileService.deleteAll(imageId.toString());
    }
}
