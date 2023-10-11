package dev.ecommerce.backend.security.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ecommerce.backend.common.helper.ResponseHelper;
import dev.ecommerce.backend.security.dto.LoginDTO;
import dev.ecommerce.backend.security.dto.RefreshTokenDTO;
import dev.ecommerce.backend.security.service.AuthService;
import dev.ecommerce.backend.security.service.RefreshTokenService;
import dev.ecommerce.backend.user.dto.EUserRegisterDTO;
import dev.ecommerce.backend.user.dto.EUserWithTokenDTO;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
	@Autowired
	private AuthService service;
	@Autowired
	private RefreshTokenService refreshTokenService;

	@Value("${app.refreshTokenDurationSeconds}")
	private int refreshTokenDurationSeconds;
	
	@PostMapping("/login")
	public Object login(@Valid @RequestBody LoginDTO dto,BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return ResponseHelper.getErrorResponse(bindingResult, HttpStatus.BAD_REQUEST);
		
		EUserWithTokenDTO userWithToken = service.login(dto);
		
		if(userWithToken == null)
			return ResponseHelper.getErrorResponse("Username or password is not correct.", HttpStatus.BAD_REQUEST);
		
		return ResponseHelper.getResponse(userWithToken,HttpStatus.OK);
	}
	@GetMapping("/register")
	public Object register(@Valid @RequestBody EUserRegisterDTO dto,BindingResult bindingResult ) {
		if(bindingResult.hasErrors())
			return ResponseHelper.getErrorResponse(bindingResult, HttpStatus.BAD_REQUEST);
		
		String result = service.register(dto);
		return ResponseHelper.getResponse(result, HttpStatus.OK);
	}
	
	@PostMapping("/logout/{user-id}")
	public Object logout(	@PathVariable(name="user-id") String userId,
							@RequestBody RefreshTokenDTO refreshToken) {
		
		boolean isLoggedOut = service.logout(userId,refreshToken.getToken());
		if(!isLoggedOut)
			return ResponseHelper.getErrorResponse("Log out Fail.User is not existed.", HttpStatus.BAD_REQUEST);
		
		return ResponseHelper.getResponse("Log out successfull.", HttpStatus.OK);
	}
	
	@PostMapping("/refresh-token")
	public Object refreshToken(@RequestBody RefreshTokenDTO refreshToken) {
		if(refreshToken == null)
			return ResponseHelper.getErrorResponse("You don't have refresh token key.Please login again.", HttpStatus.BAD_REQUEST);
		
		RefreshTokenDTO newRefreshToken = refreshTokenService.refreshToken(refreshToken);
		if(newRefreshToken == null)
			return ResponseHelper.getErrorResponse("Refresh token was expired. Please make a new signin request.", HttpStatus.BAD_REQUEST);
		return ResponseHelper.getResponse(newRefreshToken, HttpStatus.OK);
	}
	
}
