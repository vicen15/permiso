package es.sgad.trama.permiso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {PermisoApplication.BASE_PACKAGE, PermisoApplication.BASE_PACKAGE_AUTORFIMA_WEB_CLIENT})
public class PermisoApplication extends SpringBootServletInitializer {
	
	/** Paquete base de la aplicacion */
	static final String BASE_PACKAGE = "es.sgad.trama";
	/** Paquete base de la aplicacion web client afirma, permite que spring detecte los componentes @Service */
	static final String BASE_PACKAGE_AUTORFIMA_WEB_CLIENT = "es.sgad.afirma";

	public static void main(String[] args) {
		SpringApplication.run(PermisoApplication.class, args);
	}

}
