package com.example.admin.service.movie;


import com.example.admin.dto.movie.MovieDTO;
import com.example.admin.criteria.MovieCriteria;
import com.example.admin.dto.movie.MovieRequest;
import com.example.admin.enums.ImageFormat;
import com.example.admin.enums.VideoFormat;
import com.example.admin.exception.FileFormatException;
import com.example.admin.mapper.MovieMapper;
import com.example.admin.model.*;
import com.example.admin.repository.*;
import com.example.admin.service.file.AwsFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final CountryRepository countryRepository;
    private final MovieMapper movieMapper;
    private final AwsFileService awsFileService;
    private final ImageRepository imageRepository;
    private final VideoRepository videoRepository;
    private final DirectorRepository directorRepository;

    @Override
    public MovieDTO addMovie(MovieRequest movieRequest) {
        Movie newMovie = movieMapper.create(movieRequest);
        List<Genre> genreList = genreRepository.getGenreByIdIn(movieRequest.getGenresIds());
        List<Country> countryList = countryRepository.getCountryByIdIn(movieRequest.getCountriesIds());
        List<Director> directorList = directorRepository.getDirectorByIdIn(movieRequest.getDirectorsId());
        newMovie.setDirectors(directorList);
        newMovie.setGenres(genreList);
        newMovie.setCountries(countryList);
        return movieMapper.mapToDTO(movieRepository.save(newMovie));
    }

    @Override
    public void deleteMovieById(long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public MovieDTO updateMovieById(long id, MovieRequest movieRequest) {
        var movie = movieRepository.
                findById(id).
                orElseThrow(() -> new EntityNotFoundException("Movie with id:{" + id + "} does not exist"));
        movie.setCountries(countryRepository.getCountryByIdIn(movieRequest.getCountriesIds()));
        movie.setGenres(genreRepository.getGenreByIdIn(movieRequest.getGenresIds()));
        return movieMapper.mapToDTO(movieRepository.save(movieMapper.update(movie, movieRequest)));
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
    @Transactional
    public Movie addImage(Long movieId, MultipartFile multipartFile) throws IOException {
        var movie = movieRepository.findById(movieId).orElseThrow();
        var image = new Image();
        var originalFilename = multipartFile.getOriginalFilename();
        if (originalFilename != null) {
            var pointIndex = originalFilename.indexOf('.');
            image.setImageName(originalFilename.substring(0, pointIndex));
            image.setFormat(ImageFormat.valueOf(originalFilename.substring(pointIndex + 1).toUpperCase()));
            image = imageRepository.save(image);
        } else {
            throw new FileFormatException("Invalid file");
        }
        if (movie.getImages() != null) {
            movie.addImage(image);
        } else {
            var images = new ArrayList<Image>();
            images.add(image);
            movie.setImages(images);
        }
        awsFileService.upload(image.getId().toString(), multipartFile.getInputStream(), multipartFile.getSize());
        return movieRepository.save(movie);
    }

    @Override
    public Optional<InputStreamResource> getImage(Long imageId) {
        return awsFileService.download(imageId.toString());
    }

    @Override
    @Transactional
    public void deleteImage(Long imageId) {
        if (!imageRepository.existsById(imageId)) {
            throw new NoSuchElementException();
        }
        imageRepository.deleteById(imageId);
        awsFileService.deleteAll(imageId.toString());
    }

    @Override
    @Transactional
    public Movie addVideo(Long movieId, MultipartFile multipartFile) throws IOException {
        var movie = movieRepository.findById(movieId).orElseThrow();
        var video = new Video();
        var originalFilename = multipartFile.getOriginalFilename();
        if (originalFilename != null) {
            var pointIndex = originalFilename.indexOf('.');
            video.setVideoName(originalFilename.substring(0, pointIndex));
            video.setFormat(VideoFormat.valueOf(originalFilename.substring(pointIndex + 1).toUpperCase()));
            video = videoRepository.save(video);
        } else {
            throw new FileFormatException("Invalid file");
        }
        if (movie.getImages() != null) {
            movie.addVideo(video);
        } else {
            var videos = new ArrayList<Video>();
            videos.add(video);
            movie.setVideos(videos);
        }
        awsFileService.upload(video.getId().toString(), multipartFile.getInputStream(), multipartFile.getSize());
        return movieRepository.save(movie);
    }

    @Override
    public Optional<InputStreamResource> getVideo(Long videoId) {
        return awsFileService.download(videoId.toString());
    }

    @Override
    @Transactional
    public void deleteVideo(Long videoId) {
        if (!imageRepository.existsById(videoId)) {
            throw new NoSuchElementException();
        }
        imageRepository.deleteById(videoId);
        awsFileService.deleteAll(videoId.toString());
    }

}
