package com.alkemy.icons.continent.service;

import com.alkemy.icons.continent.dto.ContinentBasicDTO;
import com.alkemy.icons.continent.dto.ContinentDTO;
import com.alkemy.icons.continent.dto.ContinentDetailedDTO;
import com.alkemy.icons.continent.entity.Continent;
import com.alkemy.icons.continent.mapper.ContinentMapper;
import com.alkemy.icons.continent.repo.ContinentRepo;
import com.alkemy.icons.general.service.CustomServiceImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ContinentService extends CustomServiceImpl<
        ContinentMapper,
        ContinentRepo,
        Continent,
        ContinentDTO,
        ContinentBasicDTO,
        ContinentDetailedDTO> {

    public ContinentService(ContinentMapper mapper, ContinentRepo repo) {
        this.mapper = mapper;
        this.repo = repo;
    }

}
