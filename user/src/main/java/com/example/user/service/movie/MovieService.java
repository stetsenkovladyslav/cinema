package com.example.user.service.movie;

import com.example.root.dto.comment.CommentRequest;
import com.example.root.dto.movie.MovieDTO;
import com.example.root.exception.InvalidRatingValueException;
import com.example.root.model.Movie;
import com.example.root.model.Rate;
import com.example.user.criteria.MovieCriteria;
import com.example.user.page.MoviePage;
import com.example.user.criteria.MovieSearchQuery;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface MovieService {

    MovieDTO getMovieById(long id);

    Page<MovieDTO> getAllMovies(Pageable pageable, MovieCriteria movieCriteria);
    InputStreamResource getImage(Long id);

    InputStreamResource getVideo(Long Id);

    MovieDTO addComment(long id, CommentRequest commentRequest);

    Rate addRating(Long movieId, int rating) throws InvalidRatingValueException;

    void addToFavorite(Long movieId);

    Page<Movie> getAllFavorites(Pageable pageable);

    Page<Movie> getHistory(Pageable pageable);

    MoviePage findAll(Pageable pageable, MovieSearchQuery searchQuery);

}
