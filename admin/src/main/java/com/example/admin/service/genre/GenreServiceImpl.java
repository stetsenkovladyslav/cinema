package com.example.admin.service.genre;

import com.example.admin.DTO.GenreDTO;
import com.example.admin.mapper.GenreMapper;
import com.example.admin.model.Genre;
import com.example.admin.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService{

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;


    @Override
    public Genre addGenre(GenreDTO genreDTO) {
        return genreRepository.save(new Genre(genreDTO.getGenreName()));
    }

    @Override
    public void deleteGenreById(long id) {
        genreRepository.deleteById(id);
    }

    @Override
    public void updateGenre(long id, GenreDTO genreDTO) {
        genreRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Genre with id:{" + id + "} does not exist"));
        Genre updatedGenre = genreMapper.dtoToGenre(genreDTO);
        genreRepository.save(updatedGenre);
    }

    @Override
    public Genre getGenreById(long id) {
        return genreRepository.getById(id);
    }

    @Override
    public Page<Genre> getAllGenres(Pageable pageable) {
        return genreRepository.findAll(pageable);
    }
}
