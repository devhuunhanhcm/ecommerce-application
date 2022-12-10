package dev.ecommerce.backend.security.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@PostMapping("/login")
	public Object login(@Valid @RequestBody LoginDTO dto,BindingResult bindingResult,HttpServletResponse response) {
		if(bindingResult.hasErrors())
			return ResponseHelper.getErrorResponse(bindingResult, HttpStatus.BAD_REQUEST);
		
		EUserWithTokenDTO userWithToken = service.login(dto);
		
		if(userWithToken == null)
			return ResponseHelper.getErrorResponse("Username or password is not correct.", HttpStatus.BAD_REQUEST);
		Cookie refreshTokenCookie = new Cookie("refreshToken", userWithToken.getRefreshToken());
		refreshTokenCookie.setHttpOnly(true);
		refreshTokenCookie.setSecure(false);
		response.addCookie(refreshTokenCookie);
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
							@CookieValue(name="refreshToken",defaultValue = "") String refreshToken,
							HttpServletResponse response) {
		boolean isLoggedOut = service.logout(userId,refreshToken);
		if(!isLoggedOut)
			return ResponseHelper.getErrorResponse("Log out Fail.User is not existed.", HttpStatus.BAD_REQUEST);
		Cookie deleteTokenCookie = new Cookie("refreshToken",null);
		deleteTokenCookie.setMaxAge(0);
		response.addCookie(deleteTokenCookie);
		return ResponseHelper.getResponse("Log out successfull.", HttpStatus.OK);
	}
	
	@PostMapping("/refresh-token")
	public Object refreshToken(@CookieValue(name="refreshToken",defaultValue = "") String token) {
		if(token.equals(""))
			return ResponseHelper.getErrorResponse("You don't have refresh token key.Please login again.", HttpStatus.BAD_REQUEST);
		
		String newToken = refreshTokenService.refreshToken(token);
		if(newToken == null)
			return ResponseHelper.getErrorResponse("Refresh token was expired. Please make a new signin request.", HttpStatus.BAD_REQUEST);
		return ResponseHelper.getResponse(newToken, HttpStatus.OK);
	}
}
