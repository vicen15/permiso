package es.sgad.trama.permiso.persistance.calendario.calendarioPatron;

import java.sql.Date;

//Hay que crear un filtro con cada uno de los campos que queremos poder buscar, suelen ser los mismos campos que hay en el DTO 

public record CalendarioPatronFilter(String id, Long idAmbito, Date anio, String nombre, String descripcion /* , Boolean activo, Date fechaBaja */) {

}
