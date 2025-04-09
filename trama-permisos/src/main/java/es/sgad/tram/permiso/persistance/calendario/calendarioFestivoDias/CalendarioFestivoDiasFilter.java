package es.sgad.trama.permiso.persistance.calendario.calendarioFestivoDias;

import java.sql.Date;

//Hay que crear un filtro con cada uno de los campos que queremos poder buscar, suelen ser los mismos campos que hay en el DTO 

public record CalendarioFestivoDiasFilter(String id, Long idTurno, Long idCalendarioFestivo,
		Date fecha /* , Boolean activo, Date fechaBaja */) {

}
