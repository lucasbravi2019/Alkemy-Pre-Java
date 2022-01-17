package com.alkemy.icons.country.mapper;

import com.alkemy.icons.country.dto.CountryBasicDTO;
import com.alkemy.icons.country.dto.CountryDTO;
import com.alkemy.icons.country.entity.Country;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CountryMapper {

    public Country countryDTO2Country(CountryDTO countryDTO) {
        Country country = new Country();
        country.setName(countryDTO.getName());
        country.setImage(countryDTO.getImage());
        country.setTotalSurface(countryDTO.getTotalSurface());
        country.setIcons(countryDTO.getIcons());
        country.setPopulation(countryDTO.getPopulation());
        return country;
    }

    public CountryDTO country2CountryDTO(Country country) {
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setId(country.getId());
        countryDTO.setName(country.getName());
        countryDTO.setImage(country.getImage());
        countryDTO.setContinent(country.getContinent());
        countryDTO.setIcons(country.getIcons());
        countryDTO.setTotalSurface(country.getTotalSurface());
        countryDTO.setPopulation(country.getPopulation());
        countryDTO.setContinent(country.getContinent());
        return countryDTO;
    }

    public List<CountryDTO> country2CountryDTOList(List<Country> countries) {
        List<CountryDTO> countriesDTO = new ArrayList<>();
        for(Country country : countries) {
            countriesDTO.add(country2CountryDTO(country));
        }
        return countriesDTO;
    }

    public CountryBasicDTO country2CountryBasicDTO(Country country) {
        CountryBasicDTO dto = new CountryBasicDTO();
        dto.setImage(country.getImage());
        dto.setPopulation(country.getPopulation());
        dto.setName(country.getName());
        return dto;
    }

    public List<CountryBasicDTO> country2CountryBasicDTOList(List<Country> countries) {
        List<CountryBasicDTO> countriesDTO = new ArrayList<>();
        for(Country country : countries) {
            countriesDTO.add(country2CountryBasicDTO(country));
        }
        return countriesDTO;
    }



}
