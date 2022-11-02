package dev.ecommerce.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {
	@Bean
	public OpenAPI getOpenApit() {
		final String securitySchemeName = "Authorization";
		return new OpenAPI()
				.addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
				.components(new Components()
								.addSecuritySchemes(securitySchemeName, new SecurityScheme()
																				.name(securitySchemeName)
																				.type(SecurityScheme.Type.APIKEY)
																				.in(SecurityScheme.In.HEADER)
																				.scheme("Bearer")
																				.bearerFormat("JWT")
										))
				        
                .info(new Info()
                        .title("Gira Application")
                        .description("Service for Education Purpose")
                        .version("v1.0")
                        .license(new License().name("NO LICENSE").url("http://huunhan.dev"))
                        .contact(new Contact()
                                .email("contact@huunhan.dev")
                                .name("TRAN HUU NHAN")
                                .url("https://huunhan.dev")))
                .externalDocs(new ExternalDocumentation()
                        .description("Spring Documentation")
                        .url("https://docs.spring.io/spring-framework/docs/current/reference/html/"));
	}
}
