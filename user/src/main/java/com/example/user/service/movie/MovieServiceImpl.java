package com.example.user.service.movie;

import com.example.root.aws.AwsFileService;
import com.example.root.dto.comment.CommentRequest;
import com.example.root.dto.movie.MovieDTO;
import com.example.root.exception.InvalidRatingValueException;
import com.example.root.model.Movie;
import com.example.root.model.Rate;
import com.example.user.criteria.MovieCriteria;
import com.example.user.mapper.MovieMapper;
import com.example.user.repository.MovieRepository;
import com.example.user.repository.RateRepository;
import com.example.user.service.comment.CommentService;
import com.example.user.service.rate.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final AwsFileService awsFileService;
    private final CommentService commentService;
    private final RateRepository rateRepository;
    private final RateService rateService;

    @Override
    public MovieDTO addComment(long id, CommentRequest commentRequest) {
        var movie = movieRepository.
                findById(id).
                orElseThrow(() -> new EntityNotFoundException("Movie with id:{" + id + "} does not exist"));
        var comment = commentService.createComment(commentRequest);
        var movieComments = movie.getComments();
        if (movieComments == null) {
            movie.setComments(new ArrayList<>());
        }
        movie.getComments().add(comment);
        return movieMapper.mapToDTO(movieRepository.save(movie));
    }

    @Override
    public MovieDTO getMovieById(long id) {
        movieRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Movie with id:{" + id + "} does not exist"));
        return movieMapper.mapToDTO(movieRepository.getById(id));
    }

    @Override
    public Page<Movie> getAllMovies(Pageable pageable, MovieCriteria movieCriteria) {
        return movieRepository.findAll(movieCriteria.buildCriteria(), pageable);
    }

    @Override
    public InputStreamResource getImage(Long id) {
        return awsFileService.download(id.toString()).orElseThrow();
    }


    @Override
    public InputStreamResource getVideo(Long videoId) {
        return awsFileService.download(videoId.toString()).orElseThrow();
    }

    @Override
    public Rate addRating(Long movieId, int rating) throws IllegalArgumentException, InvalidRatingValueException {
        rateService.validateRating(rating);
        var movie = movieRepository.
                findById(movieId).
                orElseThrow(() -> new EntityNotFoundException("Movie with id:{" + movieId + "} does not exist"));

        var optionalRate = rateRepository.findById(movieId);
        Rate rate;
        if (optionalRate.isEmpty()) {
            rate = new Rate(movie.getId(), rating, 0);
        } else {
            rate = optionalRate.get();
            rate.setValue(rateService.calculateNewRatingValue(rate.getRateCount(), rate.getValue(), rating));
            rate.setRateCount(rate.getRateCount() + 1);
        }
        rateRepository.save(rate);
        return rate;
    }
}
