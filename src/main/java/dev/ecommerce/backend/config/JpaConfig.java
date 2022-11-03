package dev.ecommerce.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import dev.ecommerce.backend.common.auditor.AuditorAwareImpl;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaConfig {
	@Autowired
	private AuditorAwareImpl auditorAwareImpl;
	
	@Bean
	public AuditorAware<String> auditorAware (){
		return auditorAwareImpl;
	}
}
