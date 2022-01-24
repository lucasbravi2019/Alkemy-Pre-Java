package com.alkemy.icons.country.mapper;

import com.alkemy.icons.continent.dto.ContinentDTO;
import com.alkemy.icons.continent.entity.Continent;
import com.alkemy.icons.continent.mapper.ContinentMapper;
import com.alkemy.icons.country.dto.CountryBasicDTO;
import com.alkemy.icons.country.dto.CountryDTO;
import com.alkemy.icons.country.dto.CountryDetailedDTO;
import com.alkemy.icons.country.entity.Country;
import com.alkemy.icons.icon.dto.IconDetailedDTO;
import com.alkemy.icons.icon.mapper.IconMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class CountryMapper {

    @Autowired
    private ContinentMapper continentMapper;

    private IconMapper iconMapper = new IconMapper();

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
        if(country.getContinent() != null) {
            countryDTO.setContinent(country.getContinent());
        }
        countryDTO.setIcons(country.getIcons());
        countryDTO.setTotalSurface(country.getTotalSurface());
        countryDTO.setPopulation(country.getPopulation());
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

    public CountryDetailedDTO country2CountryDetailedDTO(Country country, boolean loadContinent) {
        CountryDetailedDTO dto = new CountryDetailedDTO();
        dto.setId(country.getId());
        dto.setName(country.getName());
        dto.setCreatedAt(country.getCreatedAt());
        Set<IconDetailedDTO> iconsDTO = iconMapper.icon2IconDetailedDTOList(country.getIcons(), false);
        dto.setIcons(iconsDTO);
        dto.setTotalSurface(country.getTotalSurface());
        dto.setImage(country.getImage());
        dto.setPopulation(country.getPopulation());
        if(loadContinent && country.getContinent() != null) {
            Continent continent = country.getContinent();
            ContinentDTO continentDTO = continentMapper.continent2ContinentDTO(country.getContinent());
            dto.setContinent(continentDTO);
        }
        return dto;
    }

    public List<CountryDetailedDTO> country2CountryDetailedDTOList(List<Country> countries, boolean loadContinent) {
        List<CountryDetailedDTO> countriesDTO = new ArrayList<>();
        for(Country country : countries) {
            countriesDTO.add(country2CountryDetailedDTO(country, loadContinent));
        }
        return countriesDTO;
    }

}
