package com.alkemy.icons.country.dto;

import com.alkemy.icons.continent.entity.Continent;
import com.alkemy.icons.icon.entity.Icon;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CountryDetailedDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long id;

    private String image;

    private String name;

    private Long population;

    private Long totalSurface;

    private Continent continent;

    private Set<Icon> icons = new HashSet<>();

}
