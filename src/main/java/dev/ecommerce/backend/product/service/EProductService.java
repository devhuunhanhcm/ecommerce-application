package dev.ecommerce.backend.product.service;

import java.util.List;

import javax.validation.Valid;

import dev.ecommerce.backend.product.dto.EProductDTO;
import dev.ecommerce.backend.product.dto.EProductUpdateDTO;

public interface EProductService {
	List<EProductDTO> findAll();
	EProductDTO create(EProductDTO dto);
	Boolean delete(String productId);
	EProductDTO updateProduct(String productId, EProductUpdateDTO dto);
}
