package com.example.admin.controller.country;

import com.example.admin.DTO.CountryDTO;
import com.example.admin.mapper.CountryMapper;
import com.example.admin.model.Country;
import com.example.admin.service.country.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;
    private final CountryMapper countryMapper;

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<CountryDTO> createCountry(@RequestBody @Valid CountryDTO countryDTO) {
        Country newCountry = countryService.addCountry(countryDTO);
        return ResponseEntity.ok(countryMapper.toDTO(newCountry));
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<Page<CountryDTO>> getAllCountries(Pageable pageable) {
        Page<Country> allGenres = countryService.getAllCountries(pageable);
        if (allGenres.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allGenres.map(countryMapper::toDTO));
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<CountryDTO> getCountry(
            @PathVariable @Valid @Positive(message = "Value must be higher than 0") Long id
    ) {
        Country genreById = countryService.getCountryById(id);
        return ResponseEntity.ok(countryMapper.toDTO(genreById));
    }

    @PatchMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> updateCountry(
            @PathVariable @Valid @Positive(message = "Value must be higher than 0") Long id,
            @RequestBody @Valid CountryDTO countryDTO
    ) {
        countryService.updateCountry(id, countryDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(
            value = "/{id}"
    )
    ResponseEntity<?> deleteCountry(
            @PathVariable @Valid @Positive(message = "Value must be higher than 0") Long id
    ) {
        countryService.deleteCountryById(id);
        return ResponseEntity.ok().build();
    }
}
