package com.alkemy.icons.continent.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
public class ContinentDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message = "Image is required")
    private String image;

    @NotEmpty(message = "Name is required")
    private String name;

}
