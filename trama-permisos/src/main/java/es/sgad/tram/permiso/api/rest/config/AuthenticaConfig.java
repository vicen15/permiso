package es.sgad.trama.permiso.api.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.sgad.authentica.webclient.client.AuthenticaWebClient;
import es.sgad.trama.common.configuracion.ConfiguracionUtil;

@Configuration
public class AuthenticaConfig {

    @Bean
    public AuthenticaWebClient authenticaWebClient() throws Exception {
        return new AuthenticaWebClient(ConfiguracionUtil.getProperties());
    }
}
