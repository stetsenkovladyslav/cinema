package com.example.admin.service.movie;


import com.example.admin.dto.comment.CommentRequest;
import com.example.admin.dto.movie.MovieDTO;
import com.example.admin.criteria.MovieCriteria;
import com.example.admin.dto.movie.MovieRequest;
import com.example.admin.exception.InvalidRatingValueException;
import com.example.admin.model.Movie;
import com.example.admin.model.Rate;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;


public interface MovieService {

    MovieDTO addMovie(MovieRequest movieRequest);

    void deleteMovieById(long id);

    MovieDTO updateMovieById(long id, MovieRequest movieRequest);

    MovieDTO getMovieById(long id);

    Page<Movie> getAllMovies(Pageable pageable, MovieCriteria movieCriteria);

    Movie addImage(Long id, MultipartFile file) throws IOException;

    InputStreamResource getImage(Long id);

    void deleteImage(Long imageId);

    Movie addVideo(Long movieId, MultipartFile file) throws IOException;

    InputStreamResource getVideo(Long Id);

    void deleteVideo(Long videoId);

    MovieDTO addComment(long id, CommentRequest commentRequest);

    Rate addRating(Long movieId, int rating) throws InvalidRatingValueException;
}
