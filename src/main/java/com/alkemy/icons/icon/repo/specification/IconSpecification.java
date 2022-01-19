package com.alkemy.icons.icon.repo.specification;

import com.alkemy.icons.country.entity.Country;
import com.alkemy.icons.icon.dto.IconFiltersDTO;
import com.alkemy.icons.icon.entity.Icon;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
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
public class IconSpecification {

    public Specification<Icon> getByFilters(IconFiltersDTO filtersDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(StringUtils.hasLength(filtersDTO.getName())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + filtersDTO.getName() + "%"
                        )
                );
            }

            if(StringUtils.hasLength(filtersDTO.getDate())) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(filtersDTO.getDate(), formatter);
                predicates.add(
                        criteriaBuilder.equal(root.<LocalDate>get("createdAt"), date)
                );
            }

            if(!CollectionUtils.isEmpty(filtersDTO.getCities())) {
                Join<Country, Icon> join = root.join("countries", JoinType.INNER);
                Expression<String> citiesId = join.get("id");
                predicates.add(citiesId.in(filtersDTO.getCities()));
            }

            query.distinct(true);

            String orderByField = "name";
            query.orderBy(
                    filtersDTO.isASC()
                            ? criteriaBuilder.asc(root.get(orderByField))
                            : criteriaBuilder.desc(root.get(orderByField))
                    );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
