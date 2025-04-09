package es.sgad.trama.permiso.persistance.franja;

import java.sql.Date;

//Hay que crear un filtro con cada uno de los campos que queremos poder buscar, suelen ser los mismos campos que hay en el DTO 

public record FranjaFilter(Long idTurno,Long idTipoHora,Date horaInicio,Boolean obligatorio
		/* Boolean activo ,Date fechaBaja */
) {

}
