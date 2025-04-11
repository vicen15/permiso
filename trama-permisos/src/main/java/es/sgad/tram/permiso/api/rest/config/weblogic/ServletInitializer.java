package es.sgad.trama.permiso.api.rest.config.weblogic;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import es.sgad.trama.permiso.PermisoApplication;

//Clase necesaria para configurar arranque el arranque de la aplicacion en weblogic 
public class ServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PermisoApplication.class);
    }
}
