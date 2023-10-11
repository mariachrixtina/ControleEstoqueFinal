package com.nassau.estoque;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	private static final String AUTHOR_MAIL = "mariachristina.arq02@gmail.com";
	private static final String API_TITLE = "Swagger API";
	private static final String API_DESCRIPTION = "Estoque";
	private static final String API_BASE_PACKAGE = "com.nassau.estoque";

	private ApiInfo getApiInfo() {
		return new ApiInfo(API_TITLE, API_DESCRIPTION, "V3", "", AUTHOR_MAIL, "", "");
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getApiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage(API_BASE_PACKAGE)).paths(PathSelectors.any()).build();
	}

}