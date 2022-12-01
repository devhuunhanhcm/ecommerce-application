package dev.ecommerce.backend.security.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import dev.ecommerce.backend.security.jwt.JwtAuthenrizationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,
							securedEnabled = true,
							jsr250Enabled =  true)
public class SecurityConfig {
	@Autowired
	private JwtAuthenrizationFilter jwtFilter;
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public AuthenticationManager authManager(HttpSecurity http,
											BCryptPasswordEncoder bCryptPasswordEncoder,
											UserDetailsService userDetailService) 
											throws Exception {
		
		return http.getSharedObject(AuthenticationManagerBuilder.class)
					.userDetailsService(userDetailService)
					.passwordEncoder(bCryptPasswordEncoder)
					.and()
					.build();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().configurationSource(request -> {
			var cors = new CorsConfiguration();
		      cors.setAllowedOrigins(List.of("http://localhost:3000","http://ecommerce-a-api-v1.up.railway.app"));
		      cors.setAllowedMethods(List.of("GET","POST", "PUT", "DELETE", "OPTIONS"));
		      cors.setAllowedHeaders(List.of("*"));
		      return cors;
		});
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		//JWT filter 
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		//API AUTHENTICATION 
		http.antMatcher("/api/v1/**").authorizeRequests()
			.antMatchers("/api/v1/auth/login").permitAll()
			.antMatchers("/products").permitAll()
			.antMatchers("/api/v1/auth/register").permitAll()
			.anyRequest().authenticated();
		return http.build();
	}
	
}
