package com.alkemy.icons.icon.dto;

import com.alkemy.icons.country.dto.CountryDetailedDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class IconDetailedDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long id;

    private String image;

    private String name;

    private LocalDate createdAt;

    private Double height;

    private String history;

    private List<CountryDetailedDTO> countries = new ArrayList<>();

}
