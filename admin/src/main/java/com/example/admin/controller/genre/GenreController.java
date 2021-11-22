package com.example.admin.controller.genre;


import com.example.data.dto.genre.GenreDTO;
import com.example.data.dto.genre.GenreRequest;
import com.example.admin.mapper.GenreMapper;
import com.example.data.model.Genre;
import com.example.admin.service.genre.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;
    private final GenreMapper genreMapper;

    @PostMapping
    GenreDTO createGenre(@RequestBody @Validated GenreRequest genreRequest) {
        return genreService.addGenre(genreRequest);
    }

    @GetMapping
    ResponseEntity<Page<GenreDTO>> getAllGenres(Pageable pageable) {
        Page<Genre> allGenres = genreService.getAllGenres(pageable);
        if (allGenres.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allGenres.map(genreMapper::toDTO));
    }

    @GetMapping(value = "/{id}")
    GenreDTO getGenre(
            @PathVariable @Valid @Positive(message = "Value must be higher than 0") Long id
    ) {
        return genreService.getGenreById(id);
    }

    @PatchMapping(value = "/{id}")
    GenreDTO updateGenre(
            @PathVariable @Valid @Positive(message = "Value must be higher than 0") Long id,
            @RequestBody @Valid GenreRequest genreRequest
    ) {
         return genreService.updateGenre(id, genreRequest);
    }

    @DeleteMapping(value = "/{id}")
    void deleteGenre(
            @PathVariable @Valid @Positive(message = "Value must be higher than 0") Long id
    ) {
        genreService.deleteGenreById(id);
    }
}