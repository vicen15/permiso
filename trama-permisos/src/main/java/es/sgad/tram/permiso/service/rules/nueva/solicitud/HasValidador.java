package es.sgad.trama.permiso.service.rules.nueva.solicitud;

import org.jeasy.rules.annotation.Priority;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.sgad.trama.permiso.domain.solicitudPermiso.NuevaSolicitudPermisoDTO;
import es.sgad.trama.permiso.service.superior.SuperiorService;


@Component
@Rule(name = "HasValidador", description = "chequea si el solicitante tiene validador, si lo tiene establece el estado a pendiente de valdar")
public class HasValidador implements org.jeasy.rules.api.Rule {

	
	//TODO ELIMINAR SERVICE DE PROYECTO PERMISO Y HACER LLAMADA HTTP AL SERVICIO QUE CORRESPONDA
	@Autowired
	SuperiorService superiorService;
	
    @Priority
    public int getPriority() {
        return 100;
    }

    @Override
    public boolean evaluate(Facts facts) {
    	NuevaSolicitudPermisoDTO nuevaSolicitudPermisoDTO = facts.get("nuevaSolicitudPermisoDTO");
		if(superiorService.getValidadorByIdUsuario(nuevaSolicitudPermisoDTO.getIdSolicitante()) != null) return true;
			
		return false;
	}

	@Override
	public void execute(Facts facts) throws Exception {
		NuevaSolicitudPermisoDTO nuevaSolicitudPermisoDTO = facts.get("nuevaSolicitudPermisoDTO");
		nuevaSolicitudPermisoDTO.setIdEstadoTramite(EstadosTramite.PENDIENTE_DE_VALIDAR.getId());	
	}

    @Override
    public int compareTo(org.jeasy.rules.api.Rule o) {
        return Integer.compare(this.getPriority(), o.getPriority());
    }
}

