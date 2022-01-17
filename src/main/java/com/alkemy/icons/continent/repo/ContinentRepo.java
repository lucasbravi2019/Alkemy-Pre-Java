package com.alkemy.icons.continent.repo;

import com.alkemy.icons.continent.entity.Continent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContinentRepo extends JpaRepository<Continent, Long> {

    Optional<Continent> findByName(String name);

}
