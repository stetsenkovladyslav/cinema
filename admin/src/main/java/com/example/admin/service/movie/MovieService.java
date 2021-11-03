package com.example.admin.service.movie;


import com.example.admin.DTO.MovieDTO;
import com.example.admin.criteria.MovieCriteria;
import com.example.admin.model.Movie;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


public interface MovieService {

    Movie addMovie(MovieDTO movieDTO);

    void deleteMovieById(long id);

    void updateMovieById(long id, MovieDTO movieDTO);

    Movie getMovieById(long id);

    Page<Movie> getAllMovies(Pageable pageable, MovieCriteria movieCriteria);

    Movie addImage(Long movieId, MultipartFile file) throws IOException;

    Optional<InputStreamResource> getImage(Long id);

    void deleteImage(Long imageId);

    Movie addVideo(Long movieId, MultipartFile file) throws IOException;

    Optional<InputStreamResource> getVideo(Long videoeId);

    void deleteVideo(Long videoId);

}
