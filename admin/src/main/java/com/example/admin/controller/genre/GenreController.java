package com.example.admin.controller.genre;


import com.example.admin.DTO.GenreDTO;
import com.example.admin.mapper.GenreMapper;
import com.example.admin.model.Genre;
import com.example.admin.service.genre.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;
    private final GenreMapper genreMapper;

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<GenreDTO> createGenre(@RequestBody @Valid GenreDTO genreDTO) {
        Genre newGenre = genreService.addGenre(genreDTO);
        return ResponseEntity.ok(genreMapper.toDTO(newGenre));
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<Page<GenreDTO>> getAllGenres(Pageable pageable) {
        Page<Genre> allGenres = genreService.getAllGenres(pageable);
        if (allGenres.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allGenres.map(genreMapper::toDTO));
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<GenreDTO> getGenre(
            @PathVariable @Valid @Positive(message = "Value must be higher than 0") Long id
    ) {
        Genre genreById = genreService.getGenreById(id);
        return ResponseEntity.ok(genreMapper.toDTO(genreById));
    }

    @PatchMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> updateGenre(
            @PathVariable @Valid @Positive(message = "Value must be higher than 0") Long id,
            @RequestBody @Valid GenreDTO genreDTO
    ) {
        genreService.updateGenre(id, genreDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(
            value = "/{id}"
    )
    ResponseEntity<?> deleteGenre(
            @PathVariable @Valid @Positive(message = "Value must be higher than 0") Long id
    ) {
        genreService.deleteGenreById(id);
        return ResponseEntity.ok().build();
    }
}