package dev.ecommerce.backend.product.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.ecommerce.backend.product.dto.EProductDTO;
import dev.ecommerce.backend.product.dto.EProductUpdateDTO;
import dev.ecommerce.backend.product.mapper.EProductMapper;
import dev.ecommerce.backend.product.model.EProduct;
import dev.ecommerce.backend.product.repository.EProductRepository;

@Service
public class EProductServiceImpl implements EProductService {
	@Autowired
	private EProductRepository productRepository;
	
	@Override
	public List<EProductDTO> findAll() {
		return productRepository.findAll()
								.stream()
								.map(t -> EProductMapper.INSTANCE.toDto(t))
								.collect(Collectors.toList());
	}

	@Override
	public EProductDTO create(EProductDTO dto) {
		EProduct product = EProductMapper.INSTANCE.toEntity(dto);
		return EProductMapper.INSTANCE.toDto(productRepository.save(product));
	}

	@Override
	public Boolean delete(String productId) {
		Optional<EProduct> productOpt;
		try {
			productOpt = productRepository.findById(UUID.fromString(productId));
		}catch(Exception e){
			return false;
		}
		if(productOpt.isEmpty()) return false;
		
		EProduct product = productOpt.get();
		productRepository.delete(product);
		return true;
	}

	@Override
	public EProductDTO updateProduct(String productId,EProductUpdateDTO dto) {
		Optional<EProduct> productOpt;
		try {
			productOpt = productRepository.findById(UUID.fromString(productId));
		}catch(Exception e){
			return null;
		}
		if(productOpt.isEmpty()) return null;
		EProduct product = productOpt.get();
		
		if(!dto.getName().equals(product.getName())) {
			Optional<EProduct> productByNameOpt = productRepository.findByName(dto.getName());
			if(productByNameOpt.isPresent())
				return null;
		}
		product.setName(dto.getName());
		product.setTitle(dto.getTitle());
		product.setSale(dto.getSale());
		product.setDescription(dto.getDescription());
		product.setQuantity(dto.getQuantity());
		product.setImage(dto.getImage());
		product.setPrice(dto.getPrice());
		
		return EProductMapper.INSTANCE.toDto(product);
	}

	

}
