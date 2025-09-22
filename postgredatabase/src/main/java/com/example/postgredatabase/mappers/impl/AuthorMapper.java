package com.example.postgredatabase.mappers.impl;

import com.example.postgredatabase.domain.dto.AuthorDTO;
import com.example.postgredatabase.domain.entities.AuthorEntity;
import com.example.postgredatabase.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper implements Mapper<AuthorEntity, AuthorDTO> {
    private ModelMapper modelMapper;

    public AuthorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AuthorDTO mapTo(AuthorEntity authorEntity) {
        return modelMapper.map(authorEntity, AuthorDTO.class);
    }

    @Override
    public AuthorEntity mapFrom(AuthorDTO authorDTO) {
        return modelMapper.map(authorDTO, AuthorEntity.class);
    }
}
