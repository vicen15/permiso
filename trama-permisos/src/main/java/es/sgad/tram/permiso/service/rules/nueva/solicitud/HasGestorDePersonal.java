package es.sgad.trama.permiso.service.rules.nueva.solicitud;

import org.jeasy.rules.annotation.Priority;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.sgad.trama.permiso.domain.ambito.tipo.TipoPermisoAmbitoDTO;
import es.sgad.trama.permiso.domain.solicitudPermiso.NuevaSolicitudPermisoDTO;
import es.sgad.trama.permiso.service.tipoPermisoAmbito.TipoPermisoAmbitoService;

@Component
@Rule(name = "HasGestorDePersonal", description = "chequea si el tipo de permiso de la solictud requiere de aprobacion de gestor de personal "
		+ "en caso afirmativo establece el estado a pendiente de gestor de personal, en caso contrario establece el estado a admitida")
public class HasGestorDePersonal implements org.jeasy.rules.api.Rule {
	
	@Autowired
	TipoPermisoAmbitoService tipoPermisoAmbitoService;
	
	
	@Priority
	public int getPriority() {
	    return 102; 
	} 
    
	@Override
	public boolean evaluate(Facts facts) {
		NuevaSolicitudPermisoDTO nuevaSolicitudPermisoDTO = facts.get("nuevaSolicitudPermisoDTO");
		if (nuevaSolicitudPermisoDTO.getIdEstadoTramite() != null
				&& (nuevaSolicitudPermisoDTO.getIdEstadoTramite().longValue() == EstadosTramite.PENDIENTE_DE_VALIDAR
						.getId()
				|| nuevaSolicitudPermisoDTO.getIdEstadoTramite().longValue() == EstadosTramite.PENDIENTE_DE_AUTORIZAR
						.getId())) {
			return false;
		}

		return true;

	}

	@Override
	public void execute(Facts facts) throws Exception {
		NuevaSolicitudPermisoDTO nuevaSolicitudPermisoDTO = facts.get("nuevaSolicitudPermisoDTO");

		TipoPermisoAmbitoDTO tipoPermisoAmbitoDTO = tipoPermisoAmbitoService.getTipoPermisoAmbitoById(
				nuevaSolicitudPermisoDTO.getIdTipoPermisoAmbito());
		if (tipoPermisoAmbitoDTO.getActivoGestorPersonal()) {
			nuevaSolicitudPermisoDTO.setIdEstadoTramite(EstadosTramite.PENDIENTE_DE_GESTOR_PERSONAL.getId());
		} else {
			nuevaSolicitudPermisoDTO.setIdEstadoTramite(EstadosTramite.ADMITIDA.getId());
		}

	}

    @Override
    public int compareTo(org.jeasy.rules.api.Rule o) {
        return Integer.compare(this.getPriority(), o.getPriority());
    }	

}