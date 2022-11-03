package dev.ecommerce.backend.user.validtation;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import dev.ecommerce.backend.user.model.EUser;
import dev.ecommerce.backend.user.repository.EUserRepository;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
	private String message;
	
	@Autowired
	private EUserRepository repository;
	
	@Override
	public void initialize(UniqueEmail uniqueEmail) {
		message = uniqueEmail.message();
	}
	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		Optional<EUser> userOpt = repository.findByEmail(email);
		if(userOpt.isEmpty())
			return true;
		
		context.buildConstraintViolationWithTemplate(message)
				.addConstraintViolation()
				.disableDefaultConstraintViolation();
		
		return false;
	}

}
