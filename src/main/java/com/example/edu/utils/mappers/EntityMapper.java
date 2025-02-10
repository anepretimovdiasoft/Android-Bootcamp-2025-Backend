package com.example.edu.utils.mappers;

public interface EntityMapper<EntityT, DtoT> {
    DtoT convertToDTO(EntityT entity);
}
