package com.alkemy.icons.general.service;

import com.alkemy.icons.general.mapper.CustomMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.NoSuchElementException;

public class CustomServiceImpl<
        Mapper extends CustomMapper<
                Entity,
                DTO,
                BasicDTO,
                DetailedDTO>,
        Repo extends JpaRepository<
                Entity,
                Long>,
        Entity,
        DTO,
        BasicDTO,
        DetailedDTO>
        implements CustomService<
        Mapper,
        Repo,
        DTO,
        BasicDTO,
        DetailedDTO> {

    protected Mapper mapper;
    protected Repo repo;

    @Override
    public List<BasicDTO> getAll() {
        return mapper.toBasicDTOList(repo.findAll());
    }

    @Override
    public DetailedDTO getDetailed(Long id, boolean loadRelationship) throws NoSuchElementException {
        return mapper.toDetailedDTO(repo.findById(id).orElseThrow(), loadRelationship);
    }

    @Override
    public DetailedDTO create(DTO dto, boolean loadRelationship) {
        Entity e = repo.save(mapper.toEntity(dto));
        return mapper.toDetailedDTO(e, loadRelationship);
    }

    @Override
    public DetailedDTO update(Long id, DTO dto, boolean loadRelationship) throws NoSuchElementException {
        if(!repo.existsById(id)) {
            throw new NoSuchElementException();
        }
        Entity e = mapper.toEntity(dto, id);
        repo.save(e);
        return mapper.toDetailedDTO(e, loadRelationship);
    }

    @Override
    public void delete(Long id) throws NoSuchElementException {
        if(!repo.existsById(id)) {
            throw new NoSuchElementException();
        }
        repo.deleteById(id);
    }
}
