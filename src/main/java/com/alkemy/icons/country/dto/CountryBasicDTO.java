package com.alkemy.icons.country.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CountryBasicDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private String image;

    private String name;

    private Long population;

}
