package es.sgad.trama.permiso.service.rules.engine.config;

import java.util.List;

import org.jeasy.rules.api.Rule;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import es.sgad.trama.permiso.service.rules.nueva.solicitud.ExistsIncidenciaMismasFechas;
import es.sgad.trama.permiso.service.rules.nueva.solicitud.ExistsPermisoMismasFechas;
import es.sgad.trama.permiso.service.rules.nueva.solicitud.HasAutorizador;
import es.sgad.trama.permiso.service.rules.nueva.solicitud.HasGestorDePersonal;
import es.sgad.trama.permiso.service.rules.nueva.solicitud.HasValidador;

@Configuration
@ComponentScan(basePackages = "es.sgad.trama.service.rules.engine.rules.permiso.nueva.solicitud") 
public class RulesConfig {

    @Bean
    @Qualifier("nuevaSolicitudPermisoRules")
    public List<Rule> nuevaSolicitudPermisoRules(List<Rule> allRules) {
        return allRules.stream()
                .filter(rule -> rule instanceof ExistsPermisoMismasFechas ||
                				rule instanceof ExistsIncidenciaMismasFechas ||
                				rule instanceof HasValidador ||
                                rule instanceof HasAutorizador ||
                                rule instanceof HasGestorDePersonal)
                .toList();
    }
    
}
