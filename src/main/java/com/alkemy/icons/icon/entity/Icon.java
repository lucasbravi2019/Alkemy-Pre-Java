package com.alkemy.icons.icon.entity;

import com.alkemy.icons.country.entity.Country;
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
@SQLDelete(sql = "UPDATE icon SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Icon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    @Column(unique = true)
    private String name;

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate createdAt = LocalDate.now();

    private Double height;

    private String history;

    private boolean deleted = Boolean.FALSE;

    @ManyToMany(mappedBy = "icons", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private List<Country> countries = new ArrayList<>();

}
