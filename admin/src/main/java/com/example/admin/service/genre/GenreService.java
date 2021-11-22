package com.example.admin.service.genre;

import com.example.root.dto.genre.GenreDTO;
import com.example.root.dto.genre.GenreRequest;
import com.example.root.model.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenreService {

    GenreDTO addGenre(GenreRequest genreRequest);

    void deleteGenreById(long id);

    GenreDTO updateGenre(long id, GenreRequest genreRequest);

    GenreDTO getGenreById(long id);

    Page<Genre> getAllGenres(Pageable pageable);
}
