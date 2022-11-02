package dev.ecommerce.backend.role.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.ecommerce.backend.common.helper.ResponseHelper;

@ControllerAdvice
public class IdIsNullExceptionHandler {
	@ExceptionHandler(IdIsNullException.class)
	public Object handleIdNull(IdIsNullException ex) {
		return ResponseHelper.getErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
