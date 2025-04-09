package es.sgad.trama.permiso.api.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

/**
 * Configuracion de OpenApi para la documentacion.
 */
@Configuration
public class OpenApiConfiguration {
	
	@Bean
	OpenAPI openApi() {
		return new OpenAPI()
				.info(new Info()
						.title("Trama Permiso API")
						.version("0.0.1-SNAPSHOT")
						.description("REST API para la gestión de permiso")
						.contact(new Contact()
								.email("sgad.trama@gob.com")
								.name("trama"))
						.license(new License()
								.name("MIT License")
								.url("https://choosealicense.com/licenses/mit/")))
				.addSecurityItem(new SecurityRequirement()
						.addList("Bearer Authentication"))
				.components(new Components()
						.addSecuritySchemes("Bearer Authentication", new SecurityScheme()
								.name("Bearer Authentication")
								.type(SecurityScheme.Type.HTTP)
								.scheme("bearer")
								.bearerFormat("JWT")));
	}

}
