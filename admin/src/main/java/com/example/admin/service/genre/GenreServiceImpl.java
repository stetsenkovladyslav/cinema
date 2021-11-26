package com.example.admin.service.genre;


import com.example.admin.repository.GenreRepository;
import com.example.root.dto.genre.GenreDTO;
import com.example.root.dto.genre.GenreRequest;
import com.example.admin.mapper.GenreMapper;
import com.example.root.model.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService{

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    public GenreDTO addGenre(GenreRequest genreRequest) {
        Genre genre = genreMapper.create(genreRequest);
        return genreMapper.mapToDTO(genreRepository.save(genre));
    }

    @Override
    public void deleteGenreById(long id) {
        genreRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Genre with id:{" + id + "} does not exist"));
        genreRepository.deleteById(id);
    }

    @Override
    public  GenreDTO updateGenre(long id, GenreRequest genreRequest) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Genre with id:{" + id + "} does not exist"));
        return genreMapper.mapToDTO(genreRepository.save(genreMapper.update(genre, genreRequest)));
    }

    @Override
    public GenreDTO getGenreById(long id) {
        genreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Genre with id:{" + id + "} does not exist"));
        return genreMapper.mapToDTO(genreRepository.getById(id));
    }

    @Override
    public Page<Genre> getAllGenres(Pageable pageable) {
        return genreRepository.findAll(pageable);
    }

    @Override
    public List<Genre> getAllByIds(List<Long> ids) {
        return genreRepository.findAllById(ids);
    }
}
