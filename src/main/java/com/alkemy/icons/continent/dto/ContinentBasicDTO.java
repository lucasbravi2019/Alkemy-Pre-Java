package com.alkemy.icons.continent.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ContinentBasicDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private String image;
    private String name;

}
