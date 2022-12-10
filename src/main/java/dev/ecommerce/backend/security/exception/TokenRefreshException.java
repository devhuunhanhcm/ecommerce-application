package dev.ecommerce.backend.security.exception;

public class TokenRefreshException extends RuntimeException{
	public TokenRefreshException(String message) {
		super(message);
	}
}
