package es.sgad.trama.permiso.persistance.tipo;

import java.sql.Date;

//Hay que crear un filtro con cada uno de los campos que queremos poder buscar, suelen ser los mismos campos que hay en el DTO 

public record TipoPermisoFilter(Long id, String descripcion, Long idTipoDuracion, Integer orden, Boolean comprobarPlazo,
		Boolean esTiempoComputable, Boolean sePuedeSolicitar, Boolean debeTenerAdjunto, Boolean creadaPorOrganismo,
		Boolean crearEnAmbitoNuevo, Date fechaBaja, String codigo) {

}
