package es.sgad.trama.permiso.persistance.bandejaEnviados;

import java.time.LocalDate;

/**
 * Creamos un filtro con los campos por los que queremos buscar 
 */
public record FiltroBandejaEnviadosFilter(String idUsuario, String idAccion, String idAmbito, String idEstado, String idTipoPermisoAmbito, String idSolicitante, LocalDate fechaInicio, LocalDate fechaFin) {
	
}
