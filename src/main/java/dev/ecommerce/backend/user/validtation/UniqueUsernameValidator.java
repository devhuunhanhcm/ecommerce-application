package dev.ecommerce.backend.user.validtation;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import dev.ecommerce.backend.user.model.EUser;
import dev.ecommerce.backend.user.repository.EUserRepository;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
	private String message;
	@Autowired
	private EUserRepository repository;
	
	@Override
	public void initialize(UniqueUsername uniqueUsername) {
		message = uniqueUsername.message();
	}
	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		Optional<EUser> userOpt = repository.findByUsername(username);
		if(userOpt.isEmpty())
			return true;
		context.buildConstraintViolationWithTemplate(message)
				.addConstraintViolation()
				.disableDefaultConstraintViolation();
		return false;
	}

}
