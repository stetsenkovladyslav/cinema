package com.example.admin.controller.director;

import com.example.admin.DTO.DirectorDTO;
import com.example.admin.mapper.DirectorMapper;
import com.example.admin.model.Director;
import com.example.admin.service.director.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/directors")
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorService directorService;
    private final DirectorMapper directorMapper;

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<DirectorDTO> createDirector(@RequestBody @Valid DirectorDTO directorDTO) {
        Director newDirector = directorService.addDirector(directorDTO);
        return ResponseEntity.ok(directorMapper.toDTO(newDirector));
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<Page<DirectorDTO>> getAllDirectors(Pageable pageable) {
        Page<Director> allDirectors = directorService.getAllDirectors(pageable);
        if (allDirectors.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allDirectors.map(directorMapper::toDTO));

    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<DirectorDTO> getDirectors(
            @PathVariable @Valid @Positive(message = "Value must be higher than 0") Long id
    ) {
        Director directorById = directorService.getDirectorById(id);
        return ResponseEntity.ok(directorMapper.toDTO(directorById));
    }

    @PatchMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> updateDirector(
            @PathVariable @Valid @Positive(message = "Value must be higher than 0") Long id,
            @RequestBody @Valid DirectorDTO directorDTO
    ) {
        directorService.updateDirectorById(id, directorDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(
            value = "/{id}"
    )
    ResponseEntity<?> deleteDirector(
            @PathVariable @Valid @Positive(message = "Value must be higher than 0") Long id
    ) {
        directorService.deleteDirectorById(id);
        return ResponseEntity.ok().build();
    }
}
