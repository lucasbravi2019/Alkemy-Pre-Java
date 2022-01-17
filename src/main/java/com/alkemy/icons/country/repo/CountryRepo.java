package com.alkemy.icons.country.repo;

import com.alkemy.icons.country.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepo extends JpaRepository<Country, Long> {

    Optional<Country> findByName(String name);

}
