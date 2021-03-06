package com.alkemy.icons.icon.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class IconDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message = "is required")
    private String image;

    @NotEmpty(message = "is required")
    private String name;

    @NotNull(message = "is required")
    @Min(value = 0, message = "Height must be 0 or positive number")
    private Double height;

    @NotEmpty(message = "is required")
    private String history;

    private List<Long> countriesId;

}
