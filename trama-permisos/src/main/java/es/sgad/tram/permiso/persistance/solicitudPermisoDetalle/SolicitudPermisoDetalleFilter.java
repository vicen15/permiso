package es.sgad.trama.permiso.persistance.solicitudPermisoDetalle;

import java.sql.Date;

//Hay que crear un filtro con cada uno de los campos que queremos poder buscar, suelen ser los mismos campos que hay en el DTO 

public record SolicitudPermisoDetalleFilter(Long idSolicitudPermiso, Date fechaInicio, Date fechaFin, Date horaInicio, Date horaFin, String observaciones) {

}
