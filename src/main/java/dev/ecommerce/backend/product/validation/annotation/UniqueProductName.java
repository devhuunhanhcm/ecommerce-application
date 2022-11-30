package dev.ecommerce.backend.product.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = UniqueProductNameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueProductName {
	public String message() default "Product name is existed.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
