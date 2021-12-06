package com.example.admin.controller.file;


import com.example.admin.service.movie.MovieService;
import com.example.root.dto.movie.MovieDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final MovieService movieService;

    @PostMapping(value = "{movieId}/image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Secured("ROLE_ADMIN")
    public MovieDTO addImage(
            @PathVariable Long movieId,
            @RequestBody MultipartFile multipartFile
    ) throws IOException {
        return movieService.addImage(movieId, multipartFile);
    }

    @PostMapping(value = "{movieId}/video",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Secured("ROLE_ADMIN")
    public MovieDTO addVideo(
            @PathVariable Long movieId,
            @RequestBody MultipartFile multipartFile
    ) throws IOException {
        return movieService.addVideo(movieId, multipartFile);
    }

    @GetMapping(value = "/image/{id}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<InputStreamResource> getImage(@PathVariable long id) {
        return ResponseEntity.ok()
                .header("Content-disposition", "attachment; fileName=" + id + ".jpg")
                .body(movieService.getImage(id));
    }

    @GetMapping(value = "/video/{id}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<InputStreamResource> getVideo(@PathVariable long id) {
        return ResponseEntity.ok()
                .header("Content-disposition", "attachment; fileName=" + id + ".mp4")
                .body(movieService.getVideo(id));
    }

    @DeleteMapping(value = "/image/{id}")
    @Secured("ROLE_ADMIN")
    public void deleteImage(@PathVariable Long id) {
        movieService.deleteImage(id);
    }

    @DeleteMapping(value = "/video/{id}")
    @Secured("ROLE_ADMIN")
    public void deleteVideo(@PathVariable Long id) {
        movieService.deleteVideo(id);
    }
}
