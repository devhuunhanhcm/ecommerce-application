package dev.ecommerce.backend.product.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.ecommerce.backend.product.dto.ECategoryDTO;
import dev.ecommerce.backend.product.dto.ECategoryWithProductDTO;
import dev.ecommerce.backend.product.mapper.ECategoryMapper;
import dev.ecommerce.backend.product.model.ECategory;
import dev.ecommerce.backend.product.model.EProduct;
import dev.ecommerce.backend.product.repository.ECategoryRepository;
import dev.ecommerce.backend.product.repository.EProductRepository;

@Service
public class ECategoryServiceImpl implements ECategoryService{
	@Autowired
	private ECategoryRepository repository;
	@Autowired
	private EProductRepository productRepository;
	
	@Override
	public List<ECategoryDTO> findAll() {
		return repository.findAll()
							.stream()
							.map(t -> ECategoryMapper.INSTANCE.toDTO(t))
							.collect(Collectors.toList());
	}

	@Override
	public ECategoryDTO create(ECategoryDTO dto) {
		ECategory categoryDto = ECategoryMapper.INSTANCE.toEntity(dto);
		ECategory newCategory = repository.save(categoryDto);
		return ECategoryMapper.INSTANCE.toDTO(newCategory);
	}

	@Override
	public ECategoryWithProductDTO addProductToCategory(String categoryId, String productId) {
		Optional<ECategory> categoryOpt;
		Optional<EProduct> productOpt;
		try {
			categoryOpt = repository.findById(UUID.fromString(categoryId));
			productOpt = productRepository.findById(UUID.fromString(productId));
		}catch(Exception e) {
			return null;
		}
		if(categoryOpt.isEmpty() || productOpt.isEmpty())
			return null;
		ECategory category = categoryOpt.get();
		category.addProduct(productOpt.get());
		repository.save(category);
		
		return ECategoryMapper.INSTANCE.toCategoryWithProductDTO(category);
	}

}
