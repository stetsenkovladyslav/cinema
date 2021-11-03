package com.example.admin.service.country;

import com.example.admin.DTO.CountryDTO;
import com.example.admin.model.Country;
import com.example.admin.model.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CountryService {

    Country addCountry(CountryDTO countryDTO);

    void deleteCountryById(long id);

    void updateCountry(long id, CountryDTO countryDTO);

    Country getCountryById(long id);

    Page<Country> getAllCountries(Pageable pageable);
}
