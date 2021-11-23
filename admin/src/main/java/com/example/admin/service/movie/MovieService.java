package com.example.admin.service.movie;


import com.example.root.dto.comment.CommentRequest;
import com.example.root.dto.movie.MovieDTO;
import com.example.admin.criteria.MovieCriteria;
import com.example.root.dto.movie.MovieRequest;
import com.example.root.dto.movie.UpdateMovieRequest;
import com.example.root.exception.InvalidRatingValueException;
import com.example.root.model.Movie;
import com.example.root.model.Rate;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface MovieService {

    MovieDTO addMovie(MovieRequest movieRequest);

    void deleteMovieById(long id);

    MovieDTO updateMovieById(long id, UpdateMovieRequest updateMovieRequest);

    MovieDTO getMovieById(long id);

    Page<Movie> getAllMovies(Pageable pageable, MovieCriteria movieCriteria);

    Movie addImage(Long id, MultipartFile file) throws IOException;

    InputStreamResource getImage(Long id);

    void deleteImage(Long imageId);

    Movie addVideo(Long movieId, MultipartFile file) throws IOException;

    InputStreamResource getVideo(Long Id);

    void deleteVideo(Long videoId);

}
