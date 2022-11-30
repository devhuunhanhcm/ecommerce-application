package dev.ecommerce.backend.product.validation.annotation;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import dev.ecommerce.backend.product.model.EProduct;
import dev.ecommerce.backend.product.repository.EProductRepository;

public class UniqueProductNameValidator implements ConstraintValidator<UniqueProductName, String>{
	private String message;
	
	@Autowired
	private EProductRepository repository;
	
	@Override
	public void initialize(UniqueProductName uniqueProductName) {
		message = uniqueProductName.message();
	}
	
	@Override
	public boolean isValid(String productName, ConstraintValidatorContext context) {
		Optional<EProduct> productOpt = repository.findByName(productName);
		if(productOpt.isEmpty())
			return true;
		context.buildConstraintViolationWithTemplate(message)
		.addConstraintViolation()
		.disableDefaultConstraintViolation();
		return false;
	}
	
}
