package com.example.admin.controller.country;

import com.example.data.dto.country.CountryDTO;
import com.example.data.dto.country.CountryRequest;
import com.example.admin.mapper.CountryMapper;
import com.example.data.model.Country;
import com.example.admin.service.country.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @PostMapping
    CountryDTO createCountry(@RequestBody @Valid CountryRequest countryRequest) {
        return countryService.addCountry(countryRequest);
    }

    @GetMapping
    ResponseEntity<Page<CountryDTO>> getAllCountries(Pageable pageable) {
        Page<Country> allGenres = countryService.getAllCountries(pageable);
        if (allGenres.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allGenres.map(countryMapper::toDTO));
    }

    @GetMapping(value = "/{id}")
    CountryDTO getCountry(
            @PathVariable @Valid @Positive(message = "Value must be higher than 0") Long id
    ) {
        return countryService.findCountryById(id) ;
    }

    @PatchMapping(value = "/{id}")
    CountryDTO updateCountry(
            @PathVariable @Valid @Positive(message = "Value must be higher than 0") Long id,
            @RequestBody @Valid CountryRequest countryRequest
    ) {
        return countryService.updateCountry(id, countryRequest);
    }

    @DeleteMapping(value = "/{id}")
    void deleteCountry(
            @PathVariable @Valid @Positive(message = "Value must be higher than 0") Long id
    ) {
        countryService.deleteCountryById(id);
    }
}
