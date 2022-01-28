package com.alkemy.icons.country.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CountryDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message = "is required")
    private String image;

    @NotEmpty(message = "is required")
    private String name;

    @NotNull(message = "is required")
    @Min(value = 0, message = "Population minimum must be 0 or above")
    private Long population;

    @NotNull(message = "is required")
    @Min(value = 0, message = "Total Surface minimum must be 0 or above")
    private Long totalSurface;

    private LocalDate createdAt;

    @NotNull(message = "is required")
    private Long continentId;

    private Set<Long> iconsId = new HashSet<>();

}
