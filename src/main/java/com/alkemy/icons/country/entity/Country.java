package com.alkemy.icons.country.entity;

import com.alkemy.icons.continent.entity.Continent;
import com.alkemy.icons.icon.entity.Icon;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE country SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    @Column(unique = true)
    private String name;

    private Long population;

    private boolean deleted = Boolean.FALSE;

    @Column(name = "total_surface")
    private Long totalSurface;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
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
    private List<Icon> icons;

    private LocalDate createdAt = LocalDate.now();

}
