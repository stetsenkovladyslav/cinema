package com.example.user.controller;

import com.example.root.dto.movie.MovieDTO;
import com.example.root.dto.user.ProfileDto;
import com.example.root.dto.user.UpdateProfileRequest;
import com.example.root.model.Movie;
import com.example.user.service.movie.MovieService;
import com.example.user.service.user.ProfileService;
import com.example.user.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.io.IOException;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ProfileService profileService;
    private final MovieService movieService;

    @GetMapping(value = "/favorites_films")
    @Secured("ROLE_USER")
    Page<Movie> getAllFavorites(Pageable pageable) {
        return movieService.getAllFavorites(pageable);
    }

    @GetMapping(value = "/history")
    @Secured("ROLE_USER")
    Page<Movie> getHistory(Pageable pageable) {
        return movieService.getHistory(pageable);
    }

    @PatchMapping(value = "/profile")
    @Secured("ROLE_USER")
    ProfileDto update(
            @RequestBody @Valid UpdateProfileRequest profileRequest) {
        return profileService.updateUserProfile(profileRequest);
    }

    @DeleteMapping(value = "/photo/{id}")
    @Secured("ROLE_USER")
    public void deleteImage(@PathVariable Long id) {
        profileService.deleteImage(id);
    }

    @GetMapping(value = "/photo/{id}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @Secured("ROLE_USER")
    public ResponseEntity<InputStreamResource> getImage(@PathVariable long id) {
        return ResponseEntity.ok()
                .header("Content-disposition", "attachment; fileName=" + id + ".png")
                .body(profileService.getImage(id));
    }

    @PostMapping(value = "/photo",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Secured("ROLE_USER")
    public ProfileDto addPhoto(
            @RequestBody MultipartFile multipartFile
    ) throws IOException {
        return profileService.addImage(multipartFile);
    }

    @GetMapping(value = "/profile")
    @Secured("ROLE_USER")
    ProfileDto getProfile(
    ) {
        return profileService.getProfile();
    }

}