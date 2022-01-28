package com.alkemy.icons.icon.mapper;

import com.alkemy.icons.country.mapper.CountryMapper;
import com.alkemy.icons.country.repo.CountryRepo;
import com.alkemy.icons.general.mapper.CustomMapper;
import com.alkemy.icons.icon.dto.IconBasicDTO;
import com.alkemy.icons.icon.dto.IconDTO;
import com.alkemy.icons.icon.dto.IconDetailedDTO;
import com.alkemy.icons.icon.entity.Icon;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IconMapper implements CustomMapper<
        Icon,
        IconDTO,
        IconBasicDTO,
        IconDetailedDTO> {

    private CountryRepo countryRepo;

    @Override
    public Icon toEntity(IconDTO iconDTO) {
        Icon icon = new Icon();
        icon.setName(iconDTO.getName());
        icon.setImage(iconDTO.getImage());
        icon.setHeight(iconDTO.getHeight());
        icon.setCreatedAt(iconDTO.getCreatedAt());
        icon.setHistory(iconDTO.getHistory());
        icon.setCountries(countryRepo.findAllById(iconDTO.getCountriesId()));
        return icon;
    }

    @Override
    public Icon toEntity(IconDTO iconDTO, Long id) {
        Icon icon = new Icon();
        icon.setId(id);
        icon.setName(iconDTO.getName());
        icon.setImage(iconDTO.getImage());
        icon.setHeight(iconDTO.getHeight());
        icon.setCreatedAt(iconDTO.getCreatedAt());
        icon.setHistory(iconDTO.getHistory());
        icon.setCountries(countryRepo.findAllById(iconDTO.getCountriesId()));
        return icon;
    }

    @Override
    public IconBasicDTO toBasicDTO(Icon icon) {
        IconBasicDTO dto = new IconBasicDTO();
        dto.setName(icon.getName());
        dto.setImage(icon.getImage());
        return dto;
    }

    @Override
    public IconDetailedDTO toDetailedDTO(Icon icon, boolean loadRelationship) {
        CountryMapper countryMapper = new CountryMapper();
        IconDetailedDTO dto = new IconDetailedDTO();
        dto.setId(icon.getId());
        dto.setName(icon.getName());
        dto.setImage(icon.getImage());
        dto.setHeight(icon.getHeight());
        dto.setCreatedAt(icon.getCreatedAt());
        dto.setHistory(icon.getHistory());
        if(loadRelationship) {
            dto.setCountries(countryMapper.toDetailedDTOList(icon.getCountries(), false));
        }
        return dto;
    }

    @Override
    public List<IconBasicDTO> toBasicDTOList(List<Icon> list) {
        List<IconBasicDTO> dto = new ArrayList<>();
        for(Icon icon : list) {
            dto.add(toBasicDTO(icon));
        }
        return dto;
    }

    @Override
    public List<IconDetailedDTO> toDetailedDTOList(List<Icon> list, boolean loadRelationship) {
        List<IconDetailedDTO> dto = new ArrayList<>();
        for(Icon icon : list) {
            dto.add(toDetailedDTO(icon, loadRelationship));
        }
        return dto;
    }
}
