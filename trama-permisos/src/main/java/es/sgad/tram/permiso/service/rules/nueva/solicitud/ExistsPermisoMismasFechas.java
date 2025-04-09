package es.sgad.trama.permiso.service.rules.nueva.solicitud;

import org.jeasy.rules.annotation.Priority;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import es.sgad.trama.common.idioma.Mensaje;
import es.sgad.trama.permiso.domain.solicitudPermiso.NuevaSolicitudPermisoDTO;
import es.sgad.trama.permiso.service.PermisoService;

@Component
@Rule(name = "ExistsPermisoMismasFechas", description = "comprueba que las fechas de la solicitud no se solapen con las de algun permiso ya admitido")
public class ExistsPermisoMismasFechas implements org.jeasy.rules.api.Rule {

	//TODO ELIMINAR SERVICE DE PROYECTO PERMISO Y HACER LLAMADA HTTP AL SERVICIO QUE CORRESPONDA
	@Autowired
	PermisoService permisoService;
	
	@Priority
	public int getPriority() {
	    return 1; 
	} 

	@Override
	public boolean evaluate(Facts facts) {
	    NuevaSolicitudPermisoDTO nuevaSolicitudPermisoDTO = facts.get("nuevaSolicitudPermisoDTO");
	    return nuevaSolicitudPermisoDTO.getListaSolicitudPermisoDetalle().stream()
	        .anyMatch(solicitudPermisoDetalleDTO -> permisoService.existenPermisosEntreFechasPorUsuario(
	            solicitudPermisoDetalleDTO.getFechaFin() != null,
	            solicitudPermisoDetalleDTO.getFechaInicio(), 
	            solicitudPermisoDetalleDTO.getFechaFin(),
	            solicitudPermisoDetalleDTO.getHoraInicio(), 
	            solicitudPermisoDetalleDTO.getHoraFin(),
	            nuevaSolicitudPermisoDTO.getIdSolicitante()));
	}

	@Override
	public void execute(Facts facts) throws Exception {
		Errors errors = facts.get("errors");
		errors.rejectValue("listaSolicitudPermisoDetalle",  Mensaje.SOLICITUD_PERMISO_DETALLE_EXISTS.getCodigo(), "default No se pueden solicitar fechas previamente solicitadas");
	}

	@Override
    public int compareTo(org.jeasy.rules.api.Rule o) {
        return Integer.compare(this.getPriority(), o.getPriority());
    }

}

