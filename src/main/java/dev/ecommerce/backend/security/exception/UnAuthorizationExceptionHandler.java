package dev.ecommerce.backend.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.ecommerce.backend.common.helper.ResponseHelper;

@RestControllerAdvice
public class UnAuthorizationExceptionHandler {
	public static final String MESSAGE = "You do not have permission to call this operation. Please contact admin to get suitable permission.";
	@ExceptionHandler(UnAuthorizerException.class)
	public Object handlerUnauthorizedException() {
		return ResponseHelper.getErrorResponse(MESSAGE, HttpStatus.UNAUTHORIZED);
	}
}
