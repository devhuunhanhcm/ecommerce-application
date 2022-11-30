package dev.ecommerce.backend.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import dev.ecommerce.backend.product.dto.ECategoryDTO;
import dev.ecommerce.backend.product.dto.ECategoryWithProductDTO;
import dev.ecommerce.backend.product.model.ECategory;

@Mapper
public interface ECategoryMapper {
	ECategoryMapper INSTANCE = Mappers.getMapper(ECategoryMapper.class);
	ECategory toEntity(ECategoryDTO dto);
	ECategoryDTO toDTO(ECategory category);
	ECategoryWithProductDTO toCategoryWithProductDTO(ECategory category);
}
