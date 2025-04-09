package es.sgad.trama.permiso.api.rest.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

import es.sgad.trama.common.configuracion.ConfiguracionUtil;
import es.sgad.trama.common.configuracion.PropiedadSistema;
import es.sgad.trama.common.file.FileUtil;

/**
 * Carga las propiedades de configuracion desde el entorno.
 */
public class EnvironmentConfiguration implements EnvironmentPostProcessor {

	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
		String path = System.getProperty(PropiedadSistema.CONFIG_PATH.getNombre(), null);
		
		// Se comprueba si se ha indicado el directorio de configuracion
		if (StringUtils.isBlank(path))
			throw new RuntimeException(String.format("No se ha encontrado la propiedad '%s' con el directorio de configuracion", PropiedadSistema.CONFIG_PATH.getNombre()));
		
		// Se comprueba si existe la ruta
		if (!FileUtil.exists(path))
			throw new RuntimeException(String.format("No se ha encontrado el directorio %s indicado por la propiedad '%s'.", path, PropiedadSistema.CONFIG_PATH.getNombre()));
		
		// Se cargan las propiedades de configuracion
		ConfiguracionUtil.createInstance(environment);
	}

}
