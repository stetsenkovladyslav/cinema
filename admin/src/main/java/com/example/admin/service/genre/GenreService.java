package com.example.admin.service.genre;

import com.example.admin.DTO.GenreDTO;
import com.example.admin.model.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenreService {

    Genre addGenre(GenreDTO genreDTO);

    void deleteGenreById(long id);

    void updateGenre(long id, GenreDTO genreDTO);

    Genre getGenreById(long id);

    Page<Genre> getAllGenres(Pageable pageable);
}
