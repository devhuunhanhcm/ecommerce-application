package dev.ecommerce.backend.product.service;

import java.util.List;

import dev.ecommerce.backend.product.dto.ECategoryDTO;
import dev.ecommerce.backend.product.dto.ECategoryWithProductDTO;

public interface ECategoryService {
	List<ECategoryDTO> findAll();

	ECategoryDTO create(ECategoryDTO dto);

	ECategoryWithProductDTO addProductToCategory(String categoryId, String productId);
	
}
