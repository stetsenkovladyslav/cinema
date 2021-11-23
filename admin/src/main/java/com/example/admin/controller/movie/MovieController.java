package com.example.admin.controller.movie;

import com.example.admin.criteria.MovieCriteria;
import com.example.root.dto.movie.MovieDTO;
import com.example.root.dto.movie.MovieRequest;
import com.example.admin.mapper.MovieMapper;
import com.example.root.dto.movie.UpdateMovieRequest;
import com.example.root.model.Movie;
import com.example.admin.service.movie.MovieService;
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
@RequestMapping("/admin/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @PostMapping
    @Secured("ROLE_ADMIN")
    MovieDTO createMovie(@RequestBody @Valid MovieRequest movieRequest) {
        return movieService.addMovie(movieRequest);
    }

    @GetMapping
    @Secured("ROLE_ADMIN")
    ResponseEntity<Page<MovieDTO>> getAllMovies(Pageable pageable, MovieCriteria movieCriteria) {
        Page<Movie> allBooks = movieService.getAllMovies(pageable, movieCriteria);
        return ResponseEntity.ok(allBooks.map(movieMapper::toDTO));
    }

    @GetMapping(value = "/{id}")
    @Secured("ROLE_ADMIN")
    MovieDTO getMovie(
            @PathVariable @Valid @Positive(message = "Value must be higher than 0") Long id
    ) {
        return movieService.getMovieById(id);
    }

    @PatchMapping(value = "/{id}")
    @Secured("ROLE_ADMIN")
    MovieDTO updateMovie(
            @PathVariable @Valid @Positive(message = "Value must be higher than 0") Long id,
            @RequestBody @Valid UpdateMovieRequest updateMovieRequest
    ) {
        return movieService.updateMovieById(id, updateMovieRequest);
    }

    @DeleteMapping(value = "/{id}")
    @Secured("ROLE_ADMIN")
    void deleteMovie(@PathVariable @Valid @Positive(message = "Value must be higher than 0") Long id) {
        movieService.deleteMovieById(id);
    }


    @PostMapping(value = "/image/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Secured("ROLE_ADMIN")
    public Movie addImage(
            @PathVariable Long id,
            @RequestBody MultipartFile multipartFile
    ) throws IOException {
        return movieService.addImage(id, multipartFile);
    }

    @PostMapping(value = "/video/{movieId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Secured("ROLE_ADMIN")
    public Movie addVideo(
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