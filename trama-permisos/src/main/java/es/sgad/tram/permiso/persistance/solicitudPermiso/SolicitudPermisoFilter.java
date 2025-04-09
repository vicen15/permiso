package es.sgad.trama.permiso.persistance.solicitudPermiso;

import java.sql.Date;

public record SolicitudPermisoFilter(Long idTipoPermiso, Long idSolicitante, Long idValidador, Long idAutorizador, Long idGestorPersonal,
		Long idGPAsignado, Long idFirma, Long idEstadoTramite, String motivoDenegacion, Date fechaSolicitud, Date fechaValidacion, Date fechaAutorizacion,
		Date fechaGestorPersonal, Date fechaPeticionDocumentacion, Date fechaDesestimacion, Date fechaDenegacion,
		String observacionesValidador, String observacionesAutorizador, Long ejercicio, String observacionesParaUsuario,
		String observacionesGp, Long validacionDelegada, Long autorizacionDelegada, String infoParaGp, Long idTipoOperacion, Long idSolicitudAnular, Long idAmbito) {

}
