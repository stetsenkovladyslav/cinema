package com.example.admin.service.country;

import com.example.root.dto.country.CountryDTO;
import com.example.root.dto.country.CountryRequest;
import com.example.admin.mapper.CountryMapper;
import com.example.root.model.Country;
import com.example.admin.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService{
    private final CountryMapper countryMapper;
    private final CountryRepository countryRepository;

    @Override
    public CountryDTO addCountry(CountryRequest countryRequest) {
        Country country = countryMapper.create(countryRequest);
        return countryMapper.toDTO(countryRepository.save(country));
    }

    @Override
    public void deleteCountryById(long id) {
       countryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Country with id:{" + id + "} does not exist"));
       countryRepository.deleteById(id);
    }

    @Override
    public CountryDTO updateCountry(long id, CountryRequest countryRequest) {
        Country country = countryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Country with id:{" + id + "} does not exist"));
        return countryMapper.toDTO(countryRepository.save(countryMapper.update(country, countryRequest)));
    }

    @Override
    public CountryDTO findCountryById(long id) {
        countryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Country with id:{" + id + "} does not exist"));
        return countryMapper.toDTO(countryRepository.getById(id));
    }

    @Override
    public Page<Country> getAllCountries(Pageable pageable) {
        return countryRepository.findAll(pageable);
    }

    @Override
    public List<Country> getAllByIds(List<Long> ids) {
        return countryRepository.findAllById(ids);
    }
}
