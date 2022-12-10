package dev.ecommerce.backend.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.ecommerce.backend.common.helper.ResponseHelper;

@ControllerAdvice
public class TokenRefreshExceptionHandler {
	@ExceptionHandler(TokenRefreshException.class)
	public Object handleIdNull(TokenRefreshException ex) {
		return ResponseHelper.getErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
