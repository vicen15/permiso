package es.sgad.trama.permiso.persistance.firma;

import java.sql.Date;

public record SolicitudPermisoFirmaFilter(String id, String firmaCriptoSolicitante, String firmaNoCriptoSolicitante,
		String firmaCriptoAutorizador, String firmaNoCriptoAutorizador, Boolean activo, Date fechaBaja) {
}
