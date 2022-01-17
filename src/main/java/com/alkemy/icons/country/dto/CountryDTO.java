package com.alkemy.icons.country.dto;

import com.alkemy.icons.continent.entity.Continent;
import com.alkemy.icons.icon.entity.Icon;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CountryDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long id;

    @NotEmpty
    private String image;

    @NotEmpty
    private String name;

    @NotNull
    @Min(0)
    private Long population;

    @NotNull
    @Min(0)
    private Long totalSurface;

    private Continent continent;

    @NotNull
    private Long continentId;

    private Set<String> iconsNames = new HashSet<>();

    private Set<Icon> icons = new HashSet<>();

    public void addIcon(Icon icon) {
        this.icons.add(icon);
    }

    public void removeIcon(Icon icon) {
        this.icons.remove(icon);
    }

}
