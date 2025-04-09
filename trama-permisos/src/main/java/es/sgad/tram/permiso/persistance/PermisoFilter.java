package es.sgad.trama.permiso.persistance;

import java.time.LocalDate;
import java.time.LocalTime;

//Hay que crear un filtro con cada uno de los campos que queremos poder buscar, suelen ser los mismos campos que hay en el DTO 

public record PermisoFilter(String id, String idTipoPermiso, String idUsuarioSolicitante, 
		LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, Integer ejercicio) {

}
