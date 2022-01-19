package com.alkemy.icons.country.entity;

import com.alkemy.icons.continent.entity.Continent;
import com.alkemy.icons.icon.entity.Icon;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "country")
@Getter
@Setter
@SQLDelete(sql = "UPDATE country SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String image;

    @Column(unique = true)
    private String name;

    private Long population;

    private boolean deleted = Boolean.FALSE;

    @Column(name = "total_surface")
    private Long totalSurface;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "continent_id")
    private Continent continent;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "icon_country",
            joinColumns = @JoinColumn(name = "country_id"),
            inverseJoinColumns = @JoinColumn(name = "icon_id")
    )
    private Set<Icon> icons = new HashSet<>();

    private LocalDate createdAt = LocalDate.now();

    public void addIcon(Icon icon) {
        this.icons.add(icon);
    }

    public void removeIcon(Icon icon) {
        this.icons.remove(icon);
    }

}
