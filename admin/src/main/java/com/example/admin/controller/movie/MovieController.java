package com.example.admin.controller.movie;

import com.example.admin.DTO.MovieDTO;
import com.example.admin.criteria.MovieCriteria;
import com.example.admin.mapper.MovieMapper;
import com.example.admin.model.Movie;
import com.example.admin.service.file.AwsFileService;
import com.example.admin.service.movie.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;
    private final AwsFileService awsFileService;

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<MovieDTO> createMovie(@RequestBody @Valid MovieDTO movieDTO) {
        Movie newMovie = movieService.addMovie(movieDTO);
        return ResponseEntity.ok(movieMapper.toDTO(newMovie));
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<Page<MovieDTO>> getAllMovies(Pageable pageable, MovieCriteria movieCriteria) {
        Page<Movie> allBooks = movieService.getAllMovies(pageable, movieCriteria);
        return ResponseEntity.ok(allBooks.map(movieMapper::toDTO));

    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<MovieDTO> getMovie(
            @PathVariable @Valid @Positive(message = "Value must be higher than 0") Long id
    ) {
        Movie movieById = movieService.getMovieById(id);
        return ResponseEntity.ok(movieMapper.toDTO(movieById));
    }

    @PatchMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> updateMovie(
            @PathVariable @Valid @Positive(message = "Value must be higher than 0") Long id,
            @RequestBody @Valid MovieDTO movieDTO
    ) {
        movieService.updateMovieById(id, movieDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(
            value = "/{id}"
    )
    ResponseEntity<?> deleteMovie(
            @PathVariable @Valid @Positive(message = "Value must be higher than 0") Long id
    ) {
        movieService.deleteMovieById(id);
        return ResponseEntity.ok().build();
    }


    @PostMapping(
            value = "/image/{movieId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Movie> addImage(
            @PathVariable Long movieId,
            @RequestBody MultipartFile multipartFile
    ) throws IOException {
        return ResponseEntity.ok(movieService.addImage(movieId, multipartFile));
    }

    @PostMapping(
            value = "/video/{movieId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Movie> addVideo(
            @PathVariable Long movieId,
            @RequestBody MultipartFile multipartFile
    ) throws IOException {
        return ResponseEntity.ok(movieService.addVideo(movieId, multipartFile));
    }

    @GetMapping(
            value = "/image/{id}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public
    ResponseEntity
            <Optional<InputStreamResource>>
    getImage(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getImage(id));
    }

    @GetMapping(
            value = "/video/{id}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public
    ResponseEntity
            <Optional<InputStreamResource>>
    getVideo(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getVideo(id));
    }

    @DeleteMapping(
            value = "/image/{id}"
    )
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        movieService.deleteImage(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(
            value = "/video/{id}"
    )
    public ResponseEntity<Void> deleteVideo(@PathVariable Long id) {
        movieService.deleteVideo(id);
        return ResponseEntity.ok().build();
    }
}