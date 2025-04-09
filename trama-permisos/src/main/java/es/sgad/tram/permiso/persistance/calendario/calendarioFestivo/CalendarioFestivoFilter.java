package es.sgad.trama.permiso.persistance.calendario.calendarioFestivo;

import java.sql.Date;

//Hay que crear un filtro con cada uno de los campos que queremos poder buscar, suelen ser los mismos campos que hay en el DTO 

public record CalendarioFestivoFilter(Long id, Long idAmbito, Long idEdificio, String nombre, Date anio,
		String descripcion /* , Boolean activo, Date fechaBaja */) {

}
