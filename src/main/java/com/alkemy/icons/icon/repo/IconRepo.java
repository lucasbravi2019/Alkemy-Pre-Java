package com.alkemy.icons.icon.repo;

import com.alkemy.icons.icon.entity.Icon;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface IconRepo extends JpaRepository<Icon, Long>, JpaSpecificationExecutor<Icon> {

    Optional<Icon> findByName(String name);
    List<Icon> findAll(Specification<Icon> spec);

}
