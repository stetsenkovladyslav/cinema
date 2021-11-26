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
import com.example.user.repository.UserRepository;
import com.example.user.service.auth.AuthService;
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
    private final UserRepository userRepository;
    private final AuthService authenticationService;

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
        movieRepository.addToHistory(authenticationService.getAuthenticatedUser().getId(), id);
        return movieMapper.mapToDTO(movieRepository.getById(id));
    }

    @Override
    public Page<Movie> getAllMovies(Pageable pageable, MovieCriteria movieCriteria) {
        return movieRepository.findAll(movieCriteria.buildCriteria(), pageable);
    }

    @Override
    public InputStreamResource getImage(Long imageId) {
        return awsFileService.download(imageId.toString()).
                orElseThrow(() -> new EntityNotFoundException("Image with id:{" + imageId + "} does not exist"));
    }

    @Override
    public InputStreamResource getVideo(Long videoId) {
        return awsFileService.download(videoId.toString()).
                orElseThrow(() -> new EntityNotFoundException("Video with id:{" + videoId + "} does not exist"));

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

    @Override
    public void addToFavorite(Long userId, Long movieId) {
        if (!movieRepository.existsById(movieId)) {
            throw new EntityNotFoundException("Movie with id:{" + movieId + "} does not exist");
        } else if(!userRepository.existsById(userId)){
            throw new EntityNotFoundException("User with id:{" + userId + "} does not exist");
        }
        movieRepository.addToFavorite(userId, movieId);
    }

    @Override
    public Page<Movie> getAllFavorites(Pageable pageable) {
        return movieRepository.findAllFavoriteByUserId(authenticationService.getAuthenticatedUser().getId(), pageable);
    }

    @Override
    public Page<Movie> getHistory(Pageable pageable) {
        return  movieRepository.findAllMoviesInHistoryByUserId(authenticationService.getAuthenticatedUser().getId(), pageable);
    }
}
