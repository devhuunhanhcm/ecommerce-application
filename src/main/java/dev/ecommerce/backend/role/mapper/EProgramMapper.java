package dev.ecommerce.backend.role.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import dev.ecommerce.backend.role.dto.EProgramDTO;
import dev.ecommerce.backend.role.model.EProgram;

@Mapper
public interface EProgramMapper {
	EProgramMapper INSTANCE = Mappers.getMapper(EProgramMapper.class);
	EProgramDTO toDTO(EProgram e);
	EProgram toEntity(EProgramDTO dto);
}
