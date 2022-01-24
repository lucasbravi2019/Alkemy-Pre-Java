package com.alkemy.icons.continent.mapper;

import com.alkemy.icons.continent.dto.ContinentDTO;
import com.alkemy.icons.continent.entity.Continent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContinentMapper {

    public ContinentDTO continent2ContinentDTO(Continent continent) {
        ContinentDTO dto = new ContinentDTO();
        dto.setId(continent.getId());
        dto.setName(continent.getName());
        dto.setImage(continent.getImage());
        return dto;
    }

    public Continent continentDTO2Continent(ContinentDTO continentDTO) {
        Continent continent = new Continent();
        continent.setId(continentDTO.getId());
        continent.setName(continentDTO.getName());
        continent.setImage(continentDTO.getImage());
        return continent;
    }

    public List<ContinentDTO> continent2ContinentDTOList(List<Continent> continents) {
        List<ContinentDTO> continentsDTO = new ArrayList<>();
        for(Continent continent : continents) {
            continentsDTO.add(continent2ContinentDTO(continent));
        }
        return continentsDTO;
    }

}
