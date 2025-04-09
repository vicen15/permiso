package es.sgad.trama.permiso.persistance.turno;

import java.sql.Date;

//Hay que crear un filtro con cada uno de los campos que queremos poder buscar, suelen ser los mismos campos que hay en el DTO 

public record TurnoFilter(  String idAmbito,String codigo,String descripcion,Long turnoVeinticuatroHoras,Date tiempo,Integer festivo
		/* , Boolean activo ,Date fechaBaja */
) {

}
