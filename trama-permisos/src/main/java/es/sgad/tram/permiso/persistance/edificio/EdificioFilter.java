package es.sgad.trama.permiso.persistance.edificio;

//Hay que crear un filtro con cada uno de los campos que queremos poder buscar, suelen ser los mismos campos que hay en el DTO 

public record EdificioFilter(String id, String idAmbito, String direccion, String descripcion,
		Long idProvincia, Long idMunicipio, Long idHusoHorario
/* ,Date fechaBaja */
) {

}
