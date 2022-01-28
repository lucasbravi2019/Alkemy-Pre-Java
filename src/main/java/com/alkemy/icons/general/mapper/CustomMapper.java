package com.alkemy.icons.general.mapper;

import java.util.List;

public interface CustomMapper<
        Entity,
        DTO,
        BasicDTO,
        DetailedDTO> {

    Entity toEntity(DTO dto);
    Entity toEntity(DTO dto, Long id);

    BasicDTO toBasicDTO(Entity entity);
    DetailedDTO toDetailedDTO(Entity entity, boolean loadRelationship);
    List<BasicDTO> toBasicDTOList(List<Entity> list);
    List<DetailedDTO> toDetailedDTOList(List<Entity> list, boolean loadRelationship);

}
