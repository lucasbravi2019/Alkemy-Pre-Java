package com.alkemy.icons.continent.mapper;

import com.alkemy.icons.continent.dto.ContinentBasicDTO;
import com.alkemy.icons.continent.dto.ContinentDTO;
import com.alkemy.icons.continent.dto.ContinentDetailedDTO;
import com.alkemy.icons.continent.entity.Continent;
import com.alkemy.icons.general.mapper.CustomMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContinentMapper implements CustomMapper<
        Continent,
        ContinentDTO,
        ContinentBasicDTO,
        ContinentDetailedDTO> {

    @Override
    public Continent toEntity(ContinentDTO continentDTO) {
        Continent continent = new Continent();
        continent.setId(continentDTO.getId());
        continent.setName(continentDTO.getName());
        continent.setImage(continentDTO.getImage());
        return continent;
    }

    @Override
    public Continent toEntity(ContinentDTO continentDTO, Long id) {
        Continent continent = new Continent();
        continent.setId(id);
        continent.setName(continentDTO.getName());
        continent.setImage(continentDTO.getImage());
        return continent;
    }

    @Override
    public ContinentBasicDTO toBasicDTO(Continent continent) {
        if(continent == null) return null;
        ContinentBasicDTO dto = new ContinentBasicDTO();
        dto.setName(continent.getName());
        dto.setImage(continent.getImage());
        return dto;
    }

    @Override
    public ContinentDetailedDTO toDetailedDTO(Continent continent, boolean loadRelationship) {
        if(continent == null) return null;
        ContinentDetailedDTO dto = new ContinentDetailedDTO();
        dto.setId(continent.getId());
        dto.setName(continent.getName());
        dto.setImage(continent.getImage());
        return dto;
    }

    @Override
    public List<ContinentBasicDTO> toBasicDTOList(List<Continent> list) {
        List<ContinentBasicDTO> dto = new ArrayList<>();
        for(Continent continent : list) {
            dto.add(toBasicDTO(continent));
        }
        return dto;
    }

    @Override
    public List<ContinentDetailedDTO> toDetailedDTOList(List<Continent> list, boolean loadRelationship) {
        List<ContinentDetailedDTO> dto = new ArrayList<>();
        for(Continent continent : list) {
            dto.add(toDetailedDTO(continent, false));
        }
        return dto;
    }
}
