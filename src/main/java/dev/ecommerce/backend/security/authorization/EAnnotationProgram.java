package dev.ecommerce.backend.security.authorization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EAnnotationProgram {
	String value() default "";
	Class<?>[] groups() default {};
}
