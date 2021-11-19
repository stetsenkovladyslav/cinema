package com.example.admin.controller.user;

import com.example.admin.criteria.MovieCriteria;
import com.example.admin.dto.comment.CommentRequest;
import com.example.admin.dto.movie.MovieDTO;
import com.example.admin.dto.rate.AddRateDTO;
import com.example.admin.exception.InvalidRatingValueException;
import com.example.admin.mapper.MovieMapper;
import com.example.admin.model.Movie;
import com.example.admin.model.Rate;
import com.example.admin.service.movie.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
@RestController
@RequestMapping("/users/movies")
@RequiredArgsConstructor
public class UserController {
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @PostMapping(value = "/{id}/comment")
    MovieDTO addComment(
            @PathVariable @Valid @Positive(message = "Value must be higher than 0") Long id,
            @RequestBody @Valid CommentRequest commentRequest) {
        return movieService.addComment(id, commentRequest);
    }

    @PostMapping(path = "/{id}/rating",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Rate> addRatingToMovie(
            @PathVariable("id") Long movieId,
            @RequestBody AddRateDTO addRatingDto
    ) throws InvalidRatingValueException {
        Rate rate = movieService.addRating(movieId, addRatingDto.getRating());
        return new ResponseEntity<>(rate, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    MovieDTO getMovie(
            @PathVariable @Valid @Positive(message = "Value must be higher than 0") Long id
    ) {
        return movieService.getMovieById(id);
    }

    @GetMapping
    ResponseEntity<Page<MovieDTO>> getAllMovies(Pageable pageable, MovieCriteria movieCriteria) {
        Page<Movie> allBooks = movieService.getAllMovies(pageable, movieCriteria);
        return ResponseEntity.ok(allBooks.map(movieMapper::toDTO));
    }

    @GetMapping(value = "/image/{id}")
    public ResponseEntity<InputStreamResource> getImage(@PathVariable long id) {
        return ResponseEntity.ok()
                .header("Content-disposition", "attachment; fileName=" + id + ".jpg")
                .body(movieService.getImage(id));
    }

    @GetMapping(value = "/video/{id}")
    public ResponseEntity<InputStreamResource> getVideo(@PathVariable long id) {
        return ResponseEntity.ok()
                .header("Content-disposition", "attachment; fileName=" + id + ".mp4")
                .body(movieService.getVideo(id));
    }

}
