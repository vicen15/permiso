package es.sgad.trama.permiso.api.rest.config;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import es.sgad.trama.common.idioma.IdiomaUtil;

/**
 * Configuracion Web.
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
	
	/** Rutas con los mensajes de i18n */
	private static final String[] PATH_I18N = { "classpath:i18n/messages", "classpath:i18n/errors" };
	
	/** Locale por defecto */
	private static final Locale DEFAULT_LOCALE = IdiomaUtil.getLocale("es");
	
	/** Parametro para modificar el Locale */
	private static final String LOCALE_PARAM = "lang";
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// Permite modificar el Locale
		registry.addInterceptor(this.localeChangeInterceptor());
	}

    /**
     * Idiomas.
     * 
     * @return
     */
    @Bean
    MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames(PATH_I18N);
		messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
		messageSource.setUseCodeAsDefaultMessage(true);
		
		// Inicializa la utilidad de idiomas
		IdiomaUtil.createInstance(messageSource);
		
		return messageSource;
	}
	
	/**
	 * Configuracion del Locale.
	 * 
	 * @return
	 */
	@Bean
	LocaleResolver localeResolver() {
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(DEFAULT_LOCALE);
		
		return resolver;
	}
	
	/**
	 * Permite modificar el Locale enviando el parametro ?lang=en.
	 * 
	 * @return
	 */
	@Bean
	LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName(LOCALE_PARAM);
		
		return interceptor;
	}

}
