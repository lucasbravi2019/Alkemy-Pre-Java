package com.alkemy.icons.continent.dto;

import com.alkemy.icons.country.entity.Country;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ContinentDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message = "Image is required")
    private String image;

    @NotEmpty(message = "Name is required")
    private String name;

    List<Country> countries = new ArrayList<>();

}
