package com.alkemy.icons.icon.entity;

import com.alkemy.icons.country.entity.Country;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "icon")
@SQLDelete(sql = "UPDATE icon SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Icon {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String image;

    private String name;

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate createdAt;

    private Double height;

    private String history;

    private boolean deleted = Boolean.FALSE;

    @JsonIgnore
    @ManyToMany(mappedBy = "icons", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private List<Country> countries = new ArrayList<>();

    public void addCountry(Country country) {
        this.countries.add(country);
    }

    public void removeCountry(Country country) {
        this.countries.remove(country);
    }

}
