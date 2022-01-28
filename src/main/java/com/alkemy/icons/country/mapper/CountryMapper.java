package com.alkemy.icons.country.mapper;

import com.alkemy.icons.continent.mapper.ContinentMapper;
import com.alkemy.icons.continent.repo.ContinentRepo;
import com.alkemy.icons.country.dto.CountryBasicDTO;
import com.alkemy.icons.country.dto.CountryDTO;
import com.alkemy.icons.country.dto.CountryDetailedDTO;
import com.alkemy.icons.country.entity.Country;
import com.alkemy.icons.general.mapper.CustomMapper;
import com.alkemy.icons.icon.mapper.IconMapper;
import com.alkemy.icons.icon.repo.IconRepo;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CountryMapper implements CustomMapper<
        Country,
        CountryDTO,
        CountryBasicDTO,
        CountryDetailedDTO> {

    private ContinentRepo continentRepo;
    private IconRepo iconRepo;

    @Override
    public Country toEntity(CountryDTO countryDTO) {
        Country country = new Country();
        country.setName(countryDTO.getName());
        country.setImage(countryDTO.getImage());
        country.setCreatedAt(LocalDate.now());
        country.setPopulation(countryDTO.getPopulation());
        country.setTotalSurface(countryDTO.getTotalSurface());
        country.setContinent(continentRepo.getById(countryDTO.getContinentId()));
        country.setIcons(iconRepo.findAllById(countryDTO.getIconsId()));
        return country;
    }

    @Override
    public Country toEntity(CountryDTO countryDTO, Long id) {
        Country country = new Country();
        country.setId(id);
        country.setName(countryDTO.getName());
        country.setImage(countryDTO.getImage());
        country.setCreatedAt(LocalDate.now());
        country.setPopulation(countryDTO.getPopulation());
        country.setTotalSurface(countryDTO.getTotalSurface());
        country.setContinent(continentRepo.getById(countryDTO.getContinentId()));
        country.setIcons(iconRepo.findAllById(countryDTO.getIconsId()));
        return country;
    }

    @Override
    public CountryBasicDTO toBasicDTO(Country country) {
        CountryBasicDTO dto = new CountryBasicDTO();
        dto.setName(country.getName());
        dto.setImage(country.getName());
        dto.setPopulation(country.getPopulation());
        return dto;
    }

    @Override
    public CountryDetailedDTO toDetailedDTO(Country country, boolean loadRelationship) {
        ContinentMapper continentMapper = new ContinentMapper();
        IconMapper iconMapper = new IconMapper();
        CountryDetailedDTO dto = new CountryDetailedDTO();
        dto.setId(country.getId());
        dto.setName(country.getName());
        dto.setImage(country.getImage());
        dto.setCreatedAt(country.getCreatedAt());
        dto.setTotalSurface(country.getTotalSurface());
        dto.setPopulation(country.getPopulation());
        dto.setContinent(continentMapper.toDetailedDTO(country.getContinent() != null ? country.getContinent() : null, false));
        if(loadRelationship) {
            dto.setIcons(iconMapper.toDetailedDTOList(!country.getIcons().isEmpty() ? country.getIcons() : null, false));
        }
        return dto;
    }

    @Override
    public List<CountryBasicDTO> toBasicDTOList(List<Country> list) {
        List<CountryBasicDTO> dto = new ArrayList<>();
        for(Country country : list) {
            dto.add(toBasicDTO(country));
        }
        return dto;
    }

    @Override
    public List<CountryDetailedDTO> toDetailedDTOList(List<Country> list, boolean loadRelationship) {
        List<CountryDetailedDTO> dto = new ArrayList<>();
        for(Country country : list) {
            dto.add(toDetailedDTO(country, loadRelationship));
        }
        return dto;
    }



}
