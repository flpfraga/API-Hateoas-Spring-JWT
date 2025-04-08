package com.fraga.APIRest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI custoOpenAPI(){
		return new OpenAPI()
				.info(new Info()
				.title("API Hateoas com Swagger, JWT")
				.version("v1")
				.description("This api manager end points for movies")
				.termsOfService("...")
				.license(new License().name("Apache 2.0")
						.url("...")))
				;
	}
}