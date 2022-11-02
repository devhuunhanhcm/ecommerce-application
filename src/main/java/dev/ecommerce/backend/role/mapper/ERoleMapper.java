package dev.ecommerce.backend.role.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import dev.ecommerce.backend.role.dto.ERoleDTO;
import dev.ecommerce.backend.role.model.ERole;

@Mapper
public interface ERoleMapper {
	ERoleMapper INSTANCE = Mappers.getMapper(ERoleMapper.class);
	ERoleDTO toDTO(ERole entity);
	ERole toEntity(ERoleDTO dto);
}
