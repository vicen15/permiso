package es.sgad.trama.permiso.service.rules.engine;

import java.util.List;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import es.sgad.trama.permiso.domain.solicitudPermiso.NuevaSolicitudPermisoDTO;

@Primary
@Service
public class PermisoRulesEngine{
	
	private final List<Rule> nuevaSolicitudPermisoRules;
	private final RulesEngine rulesEngine;
	
	public PermisoRulesEngine(@Qualifier("nuevaSolicitudPermisoRules") List<Rule> nuevaSolicitudPermisoRules) {
		this.nuevaSolicitudPermisoRules = nuevaSolicitudPermisoRules;
        
//		RulesEngineParameters parameters = new RulesEngineParameters()
//                .skipOnFirstAppliedRule(true); 		
//		this.rulesEngine = new DefaultRulesEngine(parameters);
		this.rulesEngine = new DefaultRulesEngine();
	}
	
    public void executeNuevaSolicitudPermisoRules(NuevaSolicitudPermisoDTO nuevaSolicitudPermisoDTO, Errors errors) {
        Rules rules = new Rules();
        Facts facts = new Facts();
        
       	this.nuevaSolicitudPermisoRules.forEach(rules::register);
        
        facts.put("errors", errors);	
        facts.put("nuevaSolicitudPermisoDTO", nuevaSolicitudPermisoDTO);
        
        rulesEngine.fire(rules, facts);
    }
    
}
