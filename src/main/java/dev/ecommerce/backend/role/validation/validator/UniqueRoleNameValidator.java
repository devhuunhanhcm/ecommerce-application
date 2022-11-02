package dev.ecommerce.backend.role.validation.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import dev.ecommerce.backend.role.model.ERole;
import dev.ecommerce.backend.role.repository.ERoleRepository;
import dev.ecommerce.backend.role.validation.annotation.UniqueRoleName;

public class UniqueRoleNameValidator implements ConstraintValidator<UniqueRoleName, String>{
	@Autowired
	private ERoleRepository repository;
	private String message;
	@Override
	public void initialize(UniqueRoleName uniqueRoleName) {
		message = uniqueRoleName.message();
	}
	@Override
	public boolean isValid(String roleName, ConstraintValidatorContext context) {
		Optional<ERole> roleOpt = repository.findByName(roleName);
		if(roleOpt.isEmpty())
			return true;
		context.buildConstraintViolationWithTemplate(message)
				.addConstraintViolation()
				.disableDefaultConstraintViolation();
		return false;
	}
		
}
