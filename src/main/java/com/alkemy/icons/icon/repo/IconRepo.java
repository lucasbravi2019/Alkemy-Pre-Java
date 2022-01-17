package com.alkemy.icons.icon.repo;

import com.alkemy.icons.icon.entity.Icon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IconRepo extends JpaRepository<Icon, Long> {

    Optional<Icon> findByName(String name);

}
