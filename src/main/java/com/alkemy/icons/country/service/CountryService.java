package com.alkemy.icons.country.service;

import com.alkemy.icons.country.dto.CountryBasicDTO;
import com.alkemy.icons.country.dto.CountryDTO;
import com.alkemy.icons.country.entity.Country;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface CountryService {

    List<CountryDTO> getAllDetailed() throws Exception;
    List<CountryBasicDTO> getAll() throws Exception;
    CountryDTO createCountry(CountryDTO countryDTO) throws Exception;
    CountryDTO getCountryById(Long id) throws NoSuchElementException;
    CountryDTO getCountryByName(String name) throws NoSuchElementException;
    CountryDTO updateCountry(Long id, CountryDTO countryDTO) throws NoSuchElementException;
    void deleteCountry(Long id) throws NoSuchElementException;

}
