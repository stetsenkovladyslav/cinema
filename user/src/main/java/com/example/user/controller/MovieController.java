package com.example.user.controller;

import com.example.root.dto.comment.CommentRequest;
import com.example.root.dto.movie.MovieDTO;
import com.example.root.dto.rate.AddRateDTO;
import com.example.root.exception.InvalidRatingValueException;
import com.example.root.model.Movie;
import com.example.root.model.Rate;
import com.example.user.criteria.MovieCriteria;
import com.example.user.page.MoviePage;
import com.example.user.criteria.MovieSearchQuery;
import com.example.user.mapper.MovieMapper;
import com.example.user.service.movie.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/users/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @PostMapping(value = "/{movieId}/comment")
    @Secured("ROLE_USER")
    MovieDTO addComment(
            @PathVariable @Valid @Positive(message = "Value must be higher than 0") Long movieId,
            @RequestBody @Valid CommentRequest commentRequest) {
        return movieService.addComment(movieId, commentRequest);
    }

    @PostMapping(path = "/{movieId}/rating",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @Secured("ROLE_USER")
    public ResponseEntity<Rate> addRatingToMovie(
            @PathVariable("movieId") Long movieId,
            @RequestBody AddRateDTO addRatingDto
    ) throws InvalidRatingValueException {
        Rate rate = movieService.addRating(movieId, addRatingDto.getRating());
        return new ResponseEntity<>(rate, HttpStatus.OK);
    }

    @GetMapping(value = "/{movieId}")
    @Secured("ROLE_USER")
    MovieDTO getMovie(
            @PathVariable @Valid @Positive(message = "Value must be higher than 0") Long movieId
    ) {
        return movieService.getMovieById(movieId);
    }

    @GetMapping
    @Secured("ROLE_USER")
    ResponseEntity<Page<MovieDTO>> getAllMovies(Pageable pageable, MovieCriteria movieCriteria) {
        Page<Movie> allMovies = movieService.getAllMovies(pageable, movieCriteria);
        return ResponseEntity.ok(allMovies.map(movieMapper::toDTO));
    }

    @GetMapping(value = "/image/{imageId}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @Secured("ROLE_USER")
    public ResponseEntity<InputStreamResource> getImage(@PathVariable long imageId) {
        return ResponseEntity.ok()
                .header("Content-disposition", "attachment; fileName=" + imageId + ".jpg")
                .body(movieService.getImage(imageId));
    }

    @Secured("ROLE_USER")
    @GetMapping(value = "/video/{videoId}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<InputStreamResource> getVideo(@PathVariable long videoId) {
        return ResponseEntity.ok()
                .header("Content-disposition", "attachment; fileName=" + videoId + ".mp4")
                .body(movieService.getVideo(videoId));
    }

    @PostMapping(value = "/favorites", params = {"userId", "movieId"})
    @Secured("ROLE_USER")
    void addToFavorite(
            @RequestParam @Valid @Positive(message = "Value must be higher than 0") Long movieId)
            {
        movieService.addToFavorite(movieId);
    }

    @GetMapping(value = "/favorites")
    @Secured("ROLE_USER")
    Page<Movie> getAllFavorites(
            Pageable pageable
    ) {
        return movieService.getAllFavorites(pageable);
    }

    @GetMapping(value = "/history")
    @Secured("ROLE_USER")
    Page<Movie> getHistory(Pageable pageable) {
        return movieService.getHistory(pageable);
    }


    @GetMapping(value = "/filter")
    @Secured("ROLE_USER")
    MoviePage getAll(MovieSearchQuery searchQuery, Pageable pageable) {
        return movieService.findAll(pageable, searchQuery);
    }

}