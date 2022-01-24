package com.alkemy.icons.icon.mapper;

import com.alkemy.icons.country.dto.CountryDetailedDTO;
import com.alkemy.icons.country.mapper.CountryMapper;
import com.alkemy.icons.icon.dto.IconBasicDTO;
import com.alkemy.icons.icon.dto.IconDTO;
import com.alkemy.icons.icon.dto.IconDetailedDTO;
import com.alkemy.icons.icon.entity.Icon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class IconMapper {

    @Autowired
    private CountryMapper countryMapper;

    public IconDTO icon2IconDTO(Icon icon) {
        IconDTO iconDTO = new IconDTO();
        iconDTO.setId(icon.getId());
        iconDTO.setName(icon.getName());
        iconDTO.setImage(icon.getImage());
        iconDTO.setCountries(icon.getCountries());
        iconDTO.setHeight(icon.getHeight());
        iconDTO.setHistory(icon.getHistory());
        iconDTO.setCreatedAt(icon.getCreatedAt());
        return iconDTO;
    }

    public Icon iconDTO2Icon(IconDTO iconDTO) {
        Icon icon = new Icon();
        icon.setName(iconDTO.getName());
        icon.setImage(iconDTO.getImage());
        icon.setHeight(iconDTO.getHeight());
        icon.setHistory(iconDTO.getHistory());
        icon.setCreatedAt(iconDTO.getCreatedAt());
        return icon;
    }

    public List<IconDTO> icon2IconDTOList(List<Icon> icons) {
        List<IconDTO> iconsDTO = new ArrayList<>();
        for(Icon icon : icons) {
            iconsDTO.add(icon2IconDTO(icon));
        }
        return iconsDTO;
    }

    public IconBasicDTO icon2IconBasicDTO(Icon icon) {
        IconBasicDTO dto = new IconBasicDTO();
        dto.setImage(icon.getImage());
        dto.setName(icon.getName());
        return dto;
    }

    public Set<IconBasicDTO> icon2IconBasicDTOList(List<Icon> icons) {
        Set<IconBasicDTO> iconsDTO = new HashSet<>();
        for(Icon icon : icons) {
            iconsDTO.add(icon2IconBasicDTO(icon));
        }
        return iconsDTO;
    }

    public IconDetailedDTO icon2IconDetailedDTO(Icon icon, boolean loadCountries) {
        IconDetailedDTO dto = new IconDetailedDTO();
        dto.setId(icon.getId());
        if(loadCountries) {
            List<CountryDetailedDTO> countriesDTO = countryMapper.country2CountryDetailedDTOList(icon.getCountries(), true);
            dto.setCountries(countriesDTO);
        }
        dto.setHeight(icon.getHeight());
        dto.setHistory(icon.getHistory());
        dto.setCreatedAt(icon.getCreatedAt());
        dto.setImage(icon.getImage());
        dto.setName(icon.getName());
        return dto;
    }

    public Set<IconDetailedDTO> icon2IconDetailedDTOList(List<Icon> icons, boolean loadCountries) {
        Set<IconDetailedDTO> iconsDTO = new HashSet<>();
        for(Icon icon : icons) {
            iconsDTO.add(icon2IconDetailedDTO(icon, loadCountries));
        }
        return iconsDTO;
    }

    public Set<IconDetailedDTO> icon2IconDetailedDTOList(Set<Icon> icons, boolean loadCountries) {
        Set<IconDetailedDTO> iconsDTO = new HashSet<>();
        for(Icon icon : icons) {
            iconsDTO.add(icon2IconDetailedDTO(icon, loadCountries));
        }
        return iconsDTO;
    }

}
