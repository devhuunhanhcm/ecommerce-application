package dev.ecommerce.backend.common.helper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;

public class ErrorHelper {
	public static List<String> getAllError(BindingResult result){
		return result.getAllErrors().stream()
									.map(error -> error.getDefaultMessage())
									.collect(Collectors.toList());
	}
}
