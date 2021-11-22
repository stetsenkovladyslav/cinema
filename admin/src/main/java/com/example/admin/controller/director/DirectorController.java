package com.example.admin.controller.director;

import com.example.root.dto.director.DirectorDTO;
import com.example.root.dto.director.DirectorRequest;
import com.example.admin.mapper.DirectorMapper;
import com.example.root.model.Director;
import com.example.admin.service.director.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/directors")
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorService directorService;
    private final DirectorMapper directorMapper;

    @PostMapping
    @Secured("ROLE_ADMIN")
    DirectorDTO createDirector(@RequestBody @Valid DirectorRequest directorRequest) {
        return directorService.addDirector(directorRequest);
    }

    @GetMapping
    @Secured("ROLE_ADMIN")
    ResponseEntity<Page<DirectorDTO>> getAllDirectors(Pageable pageable) {
        Page<Director> allDirectors = directorService.getAllDirectors(pageable);
        if (allDirectors.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allDirectors.map(directorMapper::toDTO));
    }

    @GetMapping(value = "/{id}")
    @Secured("ROLE_ADMIN")
    DirectorDTO getDirector(
            @PathVariable @Valid @Positive(message = "Value must be higher than 0") Long id) {
        return directorService.getDirectorById(id);
    }

    @PatchMapping(value = "/{id}")
    @Secured("ROLE_ADMIN")

    DirectorDTO updateDirector(
            @PathVariable @Valid @Positive(message = "Value must be higher than 0") Long id,
            @RequestBody @Valid DirectorRequest directorRequest) {
        return directorService.updateDirectorById(id, directorRequest);
    }

    @DeleteMapping(value = "/{id}")
    @Secured("ROLE_ADMIN")
    void deleteDirector(
            @PathVariable @Valid @Positive(message = "Value must be higher than 0") Long id) {
        directorService.deleteDirectorById(id);

    }
}
