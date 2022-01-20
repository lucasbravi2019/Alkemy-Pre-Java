package com.alkemy.icons.continent.entity;

import com.alkemy.icons.country.entity.Country;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "continent")
@SQLDelete(sql = "UPDATE continent SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Continent {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String image;

    @Column(unique = true)
    private String name;

    private boolean deleted = Boolean.FALSE;

    @JsonIgnore
    @OneToMany(mappedBy = "continent", cascade = CascadeType.ALL)
    List<Country> countries = new ArrayList<>();

}
