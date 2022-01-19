package com.alkemy.icons.country.service;

import com.alkemy.icons.country.dto.CountryBasicDTO;
import com.alkemy.icons.country.dto.CountryDTO;
import com.alkemy.icons.country.dto.CountryDetailedDTO;
import com.alkemy.icons.country.entity.Country;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface CountryService {

    List<CountryDetailedDTO> getAllDetailed() throws Exception;
    List<CountryBasicDTO> getAll() throws Exception;
    List<CountryDetailedDTO> getAllByFilters(String name, Long idContinent, String order) throws Exception;
    CountryDetailedDTO createCountry(CountryDTO countryDTO) throws Exception;
    CountryDetailedDTO getCountryById(Long id) throws NoSuchElementException;
    CountryDetailedDTO getCountryByName(String name) throws NoSuchElementException;
    CountryDetailedDTO updateCountry(Long id, CountryDTO countryDTO) throws NoSuchElementException;
    void deleteCountry(Long id) throws NoSuchElementException;

}
