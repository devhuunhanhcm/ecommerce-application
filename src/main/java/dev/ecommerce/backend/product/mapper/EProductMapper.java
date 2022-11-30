package dev.ecommerce.backend.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import dev.ecommerce.backend.product.dto.EProductDTO;
import dev.ecommerce.backend.product.model.EProduct;

@Mapper
public interface EProductMapper {
	EProductMapper INSTANCE = Mappers.getMapper(EProductMapper.class);
	EProductDTO toDto(EProduct entity);
	EProduct toEntity(EProductDTO dto);
}
