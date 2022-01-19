package com.alkemy.icons.icon.mapper;

import com.alkemy.icons.country.entity.Country;
import com.alkemy.icons.icon.dto.IconBasicDTO;
import com.alkemy.icons.icon.dto.IconDTO;
import com.alkemy.icons.icon.dto.IconDetailedDTO;
import com.alkemy.icons.icon.entity.Icon;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IconMapper {

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

    public List<IconBasicDTO> icon2IconBasicDTOList(List<Icon> icons) {
        List<IconBasicDTO> iconsDTO = new ArrayList<>();
        for(Icon icon : icons) {
            iconsDTO.add(icon2IconBasicDTO(icon));
        }
        return iconsDTO;
    }

    public IconDetailedDTO icon2IconDetailedDTO(Icon icon) {
        IconDetailedDTO dto = new IconDetailedDTO();
        dto.setId(icon.getId());
        dto.setCountries(icon.getCountries());
        dto.setHeight(icon.getHeight());
        dto.setHistory(icon.getHistory());
        dto.setCreatedAt(icon.getCreatedAt());
        dto.setImage(icon.getImage());
        dto.setName(icon.getName());
        return dto;
    }

    public List<IconDetailedDTO> icon2IconDetailedDTOList(List<Icon> icons) {
        List<IconDetailedDTO> iconsDTO = new ArrayList<>();
        for(Icon icon : icons) {
            iconsDTO.add(icon2IconDetailedDTO(icon));
        }
        return iconsDTO;
    }

}
