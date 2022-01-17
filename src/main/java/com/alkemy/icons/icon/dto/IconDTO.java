package com.alkemy.icons.icon.dto;

import com.alkemy.icons.country.entity.Country;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//@Getter
//@Setter
@Data
public class IconDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message = "Image is required")
    private String image;

    @NotEmpty(message = "Name is required")
    private String name;

    @PastOrPresent(message = "Date must be past or present")
    private LocalDate createdAt;

    @NotNull(message = "Height is required")
    @Min(value = 0, message = "Height must be 0 or positive number")
    private Double height;

    @NotEmpty(message = "History is required")
    private String history;

    private boolean deleted = Boolean.FALSE;

    private List<String> countryNames = new ArrayList<>();

    private List<Country> countries = new ArrayList<>();

}
