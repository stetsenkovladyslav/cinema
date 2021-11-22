package com.example.admin.service.genre;

import com.example.data.dto.genre.GenreDTO;
import com.example.data.dto.genre.GenreRequest;
import com.example.data.model.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenreService {

    GenreDTO addGenre(GenreRequest genreRequest);

    void deleteGenreById(long id);

    GenreDTO updateGenre(long id, GenreRequest genreRequest);

    GenreDTO getGenreById(long id);

    Page<Genre> getAllGenres(Pageable pageable);
}
