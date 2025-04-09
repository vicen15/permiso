package es.sgad.trama.permiso.persistance.bandejaPendientes;

import java.time.LocalDate;
import java.util.List;

/**
 * Creamos un filtro con los campos por los que queremos buscar 
 */
public record FiltroBandejaPendientesFilter(String idUsuario, String idAccion, String idAmbito, String idTipoPermisoAmbito, String idSolicitante, 
		LocalDate fechaInicio, LocalDate fechaFin) {
	
}
