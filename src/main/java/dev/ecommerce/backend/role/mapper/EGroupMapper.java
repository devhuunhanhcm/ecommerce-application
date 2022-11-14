package dev.ecommerce.backend.role.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import dev.ecommerce.backend.role.dto.EGroupDTO;
import dev.ecommerce.backend.role.dto.EGroupWithRolesDTO;
import dev.ecommerce.backend.role.dto.EGroupWithUsersDTO;
import dev.ecommerce.backend.role.model.EGroup;

@Mapper
public interface EGroupMapper {
	EGroupMapper INSTANCE = Mappers.getMapper(EGroupMapper.class);
	EGroupDTO toDTO(EGroup entity);
	EGroup toEntity(EGroupDTO dto);
	EGroupWithRolesDTO toGroupWithRolesDTO(EGroup entity);
	EGroupWithUsersDTO toGroupWithUsersDTO(EGroup entity);
}
