package dev.ecommerce.backend.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import dev.ecommerce.backend.user.dto.EUserDTO;
import dev.ecommerce.backend.user.dto.EUserDetailsDTO;
import dev.ecommerce.backend.user.dto.EUserLoginDTO;
import dev.ecommerce.backend.user.dto.EUserRegisterDTO;
import dev.ecommerce.backend.user.model.EUser;

@Mapper
public interface EUserMapper {
	EUserMapper INSTANCE = Mappers.getMapper(EUserMapper.class);
	EUserDTO toDTO(EUser entity);
	EUser toEntity(EUserDTO dto);
	EUser toLoginEntity(EUserLoginDTO dto);
	EUserLoginDTO toLoginDTO(EUser entity);
	EUserDetailsDTO toUserDetailsDTO(EUser entity);
}
