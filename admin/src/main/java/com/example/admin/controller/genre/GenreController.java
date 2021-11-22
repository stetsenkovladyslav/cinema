package com.example.admin.controller.genre;


import com.example.root.dto.genre.GenreDTO;
import com.example.root.dto.genre.GenreRequest;
import com.example.admin.mapper.GenreMapper;
import com.example.root.model.Genre;
import com.example.admin.service.genre.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
    @Secured("ROLE_ADMIN")
    GenreDTO createGenre(@RequestBody @Validated GenreRequest genreRequest) {
        return genreService.addGenre(genreRequest);
    }

    @GetMapping
    @Secured("ROLE_ADMIN")
    ResponseEntity<Page<GenreDTO>> getAllGenres(Pageable pageable) {
        Page<Genre> allGenres = genreService.getAllGenres(pageable);
        if (allGenres.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allGenres.map(genreMapper::toDTO));
    }

    @GetMapping(value = "/{id}")
    @Secured("ROLE_ADMIN")
    GenreDTO getGenre(
            @PathVariable @Valid @Positive(message = "Value must be higher than 0") Long id)
    {
        return genreService.getGenreById(id);
    }

    @PatchMapping(value = "/{id}")
    @Secured("ROLE_ADMIN")
    GenreDTO updateGenre(
            @PathVariable @Valid @Positive(message = "Value must be higher than 0") Long id,
            @RequestBody @Valid GenreRequest genreRequest)
    {
         return genreService.updateGenre(id, genreRequest);
    }

    @DeleteMapping(value = "/{id}")
    @Secured("ROLE_ADMIN")
    void deleteGenre(
            @PathVariable @Valid @Positive(message = "Value must be higher than 0") Long id)
    {
        genreService.deleteGenreById(id);
    }
}