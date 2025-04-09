package es.sgad.trama.permiso.persistance.calendario.calendarioPatronDias;

import java.sql.Date;

//Hay que crear un filtro con cada uno de los campos que queremos poder buscar, suelen ser los mismos campos que hay en el DTO 

public record CalendarioPatronDiasFilter(String id, Long idCalendPatron, Long idTurno,
		Date fecha /* , Boolean activo, Date fechaBaja */) {

}
