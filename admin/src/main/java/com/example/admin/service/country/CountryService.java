package com.example.admin.service.country;

import com.example.data.dto.country.CountryDTO;
import com.example.data.dto.country.CountryRequest;
import com.example.data.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CountryService {

    CountryDTO addCountry(CountryRequest countryRequest);

    void deleteCountryById(long id);

    CountryDTO updateCountry(long id, CountryRequest countryRequest);

    CountryDTO findCountryById(long id);

    Page<Country> getAllCountries(Pageable pageable);
}
