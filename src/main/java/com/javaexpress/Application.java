package com.javaexpress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Product REST API Documentation",
				description = "Product REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Bhaskar",
						email = "javaexpresschannel@gmail.com",
						url = "https://youtube.com/c/javaexpress"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://youtube.com/c/javaexpress"
				)
		),
		externalDocs = @ExternalDocumentation(
				description =  "Product REST API Documentation",
				url = "http://localhost:8070/swagger-ui.html"
		)
)
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class Application implements WebServerFactoryCustomizer<ConfigurableWebServerFactory>{

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	public void customize(ConfigurableWebServerFactory factory) {
		factory.setPort(8070);
	}
	
	

}
