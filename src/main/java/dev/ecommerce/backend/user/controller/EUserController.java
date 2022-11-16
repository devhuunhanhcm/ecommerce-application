package dev.ecommerce.backend.user.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ecommerce.backend.common.auditor.AuditorAwareImpl;
import dev.ecommerce.backend.common.helper.ResponseHelper;
import dev.ecommerce.backend.security.jwt.JwtHelper;
import dev.ecommerce.backend.user.dto.EUserDTO;
import dev.ecommerce.backend.user.dto.EUserDetailsDTO;
import dev.ecommerce.backend.user.dto.EUserLoginDTO;
import dev.ecommerce.backend.user.dto.EUserWithRolesDTO;
import dev.ecommerce.backend.user.service.EUserService;


@RestController
@RequestMapping("/api/v1/users")
public class EUserController {
	@Autowired
	private EUserService service;
	
	@Autowired
	private AuditorAwareImpl auditor;
	
	@GetMapping
	public Object findAllUser() {
		return ResponseHelper.getResponse(service.findAll(), HttpStatus.OK);
	}
	@GetMapping("/user")
	public Object findUserByToken() {
		Optional<String> username = auditor.getCurrentAuditor();
		EUserDetailsDTO dto = service.findUserByUsername(username.get());
		
		return ResponseHelper.getResponse(dto, HttpStatus.OK);
	}
	@GetMapping("/{username}/roles")
	public Object findUserWithRoles(@PathVariable(name="username") String username) {
		EUserWithRolesDTO dto = service.findUserWithRolesByUsername(username);
		if(dto == null)
			return ResponseHelper.getErrorResponse("User is not existed,", HttpStatus.BAD_REQUEST);
		
		return ResponseHelper.getResponse(dto, HttpStatus.OK);
	}
	@PostMapping
	public Object createUser(@Valid @RequestBody EUserLoginDTO dto,BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return ResponseHelper.getErrorResponse(bindingResult,HttpStatus.BAD_REQUEST);
		
		EUserLoginDTO newDTO = service.createUser(dto);
		
		return ResponseHelper.getResponse(newDTO, HttpStatus.CREATED);
	}
}
