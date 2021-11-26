package com.example.admin.service.country;

import com.example.root.dto.country.CountryDTO;
import com.example.root.dto.country.CountryRequest;
import com.example.root.model.Country;
import com.example.root.model.Director;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CountryService {

    CountryDTO addCountry(CountryRequest countryRequest);

    void deleteCountryById(long id);

    CountryDTO updateCountry(long id, CountryRequest countryRequest);

    CountryDTO findCountryById(long id);

    Page<Country> getAllCountries(Pageable pageable);
    List<Country> getAllByIds(List<Long> ids);

}
