package es.sgad.trama.permiso.persistance.calendario.calendarioPersonalDias;

import java.time.LocalDate;

//Hay que crear un filtro con cada uno de los campos que queremos poder buscar, suelen ser los mismos campos que hay en el DTO 

public record CalendarioPersonalDiasFilter(String id, String idUsuario, String idTurno,
		LocalDate fechaInicio, LocalDate fechaFin) {

}
