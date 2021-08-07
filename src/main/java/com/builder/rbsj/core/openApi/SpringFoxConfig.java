package com.builder.rbsj.core.openApi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig implements WebMvcConfigurer{
	
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.OAS_30)
				.select()
					.apis(RequestHandlerSelectors.basePackage("com.builder.rbsj.api"))
					.build()
				.apiInfo(apiInfo())
				.tags(new Tag("Clientes", "Gerenciador de clientes"));
	}
	
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Cadastro de Cliente API")
				.description("Desafio Platform Builders - API para Cadastro de Cliente")
				.version("1.0")
				.contact(new Contact("Rui Junior", "https://www.linkedin.com/in/ruijunior/", "ruibatistasilvajunior@gmail.com"))
				.build();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("index.html")
			.addResourceLocations("classpath:/META-INF/resources");
	}

}
