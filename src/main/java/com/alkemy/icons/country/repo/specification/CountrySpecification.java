package com.alkemy.icons.country.repo.specification;

import com.alkemy.icons.continent.entity.Continent;
import com.alkemy.icons.country.dto.CountryFilterDTO;
import com.alkemy.icons.country.entity.Country;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class CountrySpecification {

    public Specification<Country> getByFilters(CountryFilterDTO filterDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(StringUtils.hasLength(filterDTO.getName())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                        "%" + filterDTO.getName() + "%"
                        )
                );
            }

            if(filterDTO.getIdContinent() != null) {
                Join<Continent, Country> join = root.join("continent", JoinType.INNER);
                Expression<String> continentId = join.get("id");
                predicates.add(continentId.in(filterDTO.getIdContinent()));
            }

            query.distinct(true);

            query.orderBy(
                filterDTO.isASC()
                    ? criteriaBuilder.asc(root.<LocalDate>get("createdAt"))
                    : criteriaBuilder.desc(root.<LocalDate>get("createdAt"))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
