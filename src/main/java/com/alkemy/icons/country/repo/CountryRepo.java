package com.alkemy.icons.country.repo;

import com.alkemy.icons.country.entity.Country;
import com.alkemy.icons.country.repo.specification.CountrySpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface CountryRepo extends JpaRepository<Country, Long>, JpaSpecificationExecutor<Country> {

    Optional<Country> findByName(String name);
    List<Country> findAll(Specification<Country> spec);

}
