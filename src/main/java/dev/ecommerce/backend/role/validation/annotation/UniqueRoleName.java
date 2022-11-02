package dev.ecommerce.backend.role.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import dev.ecommerce.backend.role.validation.validator.UniqueRoleNameValidator;

@Constraint(validatedBy = UniqueRoleNameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueRoleName {
	String message() default "Role name is existed.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
