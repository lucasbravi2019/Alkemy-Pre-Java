package com.alkemy.icons.country.dto;

import com.alkemy.icons.continent.dto.ContinentDetailedDTO;
import com.alkemy.icons.icon.dto.IconDetailedDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CountryDetailedDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long id;

    private String image;

    private String name;

    private Long population;

    private Long totalSurface;

    private LocalDate createdAt;

    private ContinentDetailedDTO continent;

    private List<IconDetailedDTO> icons;

}
