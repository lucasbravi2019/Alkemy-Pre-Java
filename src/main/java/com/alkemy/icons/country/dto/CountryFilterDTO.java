package com.alkemy.icons.country.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class CountryFilterDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private String name;

    private Long idContinent;

    private String order;

    public boolean isASC() {
        return this.order.compareToIgnoreCase("ASC") == 0;
    }


}
