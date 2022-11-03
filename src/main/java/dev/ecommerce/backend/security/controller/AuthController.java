package dev.ecommerce.backend.security.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ecommerce.backend.common.helper.ResponseHelper;
import dev.ecommerce.backend.security.dto.LoginDTO;
import dev.ecommerce.backend.security.service.AuthService;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
	@Autowired
	private AuthService service;
	
	@PostMapping("/login")
	public Object login(@Valid @RequestBody LoginDTO dto,BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return ResponseHelper.getErrorResponse(bindingResult, HttpStatus.BAD_REQUEST);
		
		String token = service.login(dto);
		if(token == null)
			return ResponseHelper.getErrorResponse("Username or password is not correct.", HttpStatus.BAD_REQUEST);
		return ResponseHelper.getResponse(token, HttpStatus.OK);
	}
}
