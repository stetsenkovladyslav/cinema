package com.example.admin.controller.movie;

import com.example.admin.criteria.MovieCriteria;
import com.example.admin.dto.movie.MovieDTO;
import com.example.admin.dto.movie.MovieRequest;
import com.example.admin.mapper.MovieMapper;
import com.example.admin.model.Movie;
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
import java.util.Optional;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @PostMapping
    MovieDTO createMovie(@RequestBody @Valid MovieRequest movieRequest) {
        return movieService.addMovie(movieRequest);
    }

    @GetMapping
    ResponseEntity<Page<MovieDTO>> getAllMovies(Pageable pageable, MovieCriteria movieCriteria) {
        Page<Movie> allBooks = movieService.getAllMovies(pageable, movieCriteria);
        return ResponseEntity.ok(allBooks.map(movieMapper::toDTO));
    }

    @GetMapping(value = "/{id}")
    MovieDTO getMovie(
            @PathVariable @Valid @Positive(message = "Value must be higher than 0") Long id
    ) {
        return movieService.getMovieById(id);
    }

    @PatchMapping(value = "/{id}")
    MovieDTO updateMovie(
            @PathVariable @Valid @Positive(message = "Value must be higher than 0") Long id,
            @RequestBody @Valid MovieRequest movieRequest
    ) {
        return movieService.updateMovieById(id, movieRequest);
    }

    @DeleteMapping(value = "/{id}")
    void deleteMovie(@PathVariable @Valid @Positive(message = "Value must be higher than 0") Long id) {
        movieService.deleteMovieById(id);
    }


    @PostMapping(value = "/image/{movieId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Movie> addImage(
            @PathVariable Long movieId,
            @RequestBody MultipartFile multipartFile
    ) throws IOException {
        return ResponseEntity.ok(movieService.addImage(movieId, multipartFile));
    }

    @PostMapping(value = "/video/{movieId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Movie> addVideo(
            @PathVariable Long movieId,
            @RequestBody MultipartFile multipartFile
    ) throws IOException {
        return ResponseEntity.ok(movieService.addVideo(movieId, multipartFile));
    }

    @GetMapping(value = "/image/{id}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public ResponseEntity<Optional<InputStreamResource>> getImage(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getImage(id));
    }

    @GetMapping(
            value = "/video/{id}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public ResponseEntity<Optional<InputStreamResource>>
    getVideo(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getVideo(id));
    }

    @DeleteMapping(value = "/image/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        movieService.deleteImage(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/video/{id}")
    public ResponseEntity<Void> deleteVideo(@PathVariable Long id) {
        movieService.deleteVideo(id);
        return ResponseEntity.ok().build();
    }
}