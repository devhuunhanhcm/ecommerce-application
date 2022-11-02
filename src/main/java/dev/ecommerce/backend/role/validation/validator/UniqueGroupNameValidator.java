package dev.ecommerce.backend.role.validation.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import dev.ecommerce.backend.role.model.EGroup;
import dev.ecommerce.backend.role.repository.EGroupRepository;
import dev.ecommerce.backend.role.validation.annotation.UniqueGroupName;

public class UniqueGroupNameValidator implements ConstraintValidator<UniqueGroupName, String> {
	@Autowired
	private EGroupRepository repository;
	private String message;
	@Override
	public void initialize(UniqueGroupName uniqueGroupName) {
		message = uniqueGroupName.message();
	}
	@Override
	public boolean isValid(String roleName, ConstraintValidatorContext context) {
		Optional<EGroup> groupOpt = repository.findByName(roleName);
		if(groupOpt.isEmpty())
			return true;
		context.buildConstraintViolationWithTemplate(message)
				.addConstraintViolation()
				.disableDefaultConstraintViolation();
		return false;
	}
}
