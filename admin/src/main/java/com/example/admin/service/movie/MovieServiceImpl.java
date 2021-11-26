package com.example.admin.service.movie;

import com.example.admin.criteria.MovieCriteria;
import com.example.admin.mapper.MovieMapper;
import com.example.admin.repository.ImageRepository;
import com.example.admin.repository.MovieRepository;
import com.example.admin.repository.VideoRepository;
import com.example.root.aws.AwsFileService;
import com.example.root.dto.movie.MovieDTO;
import com.example.root.dto.movie.MovieRequest;
import com.example.root.dto.movie.UpdateMovieRequest;
import com.example.root.enums.ImageFormat;
import com.example.root.enums.VideoFormat;
import com.example.root.exception.FileFormatException;
import com.example.root.model.Image;
import com.example.root.model.Movie;
import com.example.root.model.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final AwsFileService awsFileService;
    private final ImageRepository imageRepository;
    private final VideoRepository videoRepository;

    @Override
    public MovieDTO addMovie(MovieRequest movieRequest) {
        return movieMapper.mapToDTO(movieRepository.save(movieMapper.create(movieRequest)));
    }

    @Override
    public void deleteMovieById(long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public MovieDTO updateMovieById(long id, UpdateMovieRequest updateMovieRequest) {
        var movie = movieRepository.
                findById(id).
                orElseThrow(() -> new EntityNotFoundException("Movie with id:{" + id + "} does not exist"));
        return movieMapper.mapToDTO(movieRepository.save(movieMapper.update(movie, updateMovieRequest)));
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
        var movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new EntityNotFoundException("Movie with id:{" + movieId + "} does not exist"));
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
        awsFileService.upload(image.getId().toString(), multipartFile.getInputStream());
        return movieRepository.save(movie);
    }

    @Override
    public InputStreamResource getImage(Long id) {
        return awsFileService.download(id.toString()).orElseThrow();
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
        var movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new EntityNotFoundException("Movie with id:{" + movieId + "} does not exist"));
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
        awsFileService.upload(video.getId().toString(), multipartFile.getInputStream());
        return movieRepository.save(movie);
    }

    @Override
    public InputStreamResource getVideo(Long videoId) {
        videoRepository.
                findById(videoId).
                orElseThrow(() -> new EntityNotFoundException("Video with id:{" + videoId + "} does not exist"));
        return awsFileService.download(videoId.toString()).orElseThrow();
    }

    @Override
    @Transactional
    public void deleteVideo(Long videoId) {
        if (!videoRepository.existsById(videoId)) {
            throw new NoSuchElementException();
        }
        videoRepository.deleteById(videoId);
        awsFileService.deleteAll(videoId.toString());
    }

}
