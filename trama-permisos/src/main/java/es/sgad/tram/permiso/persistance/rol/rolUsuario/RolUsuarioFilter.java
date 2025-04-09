package es.sgad.trama.permiso.persistance.rol.rolUsuario;

//Hay que crear un filtro con cada uno de los campos que queremos poder buscar, suelen ser los mismos campos que hay en el DTO 

public record RolUsuarioFilter( Long idAmbito, Long idUsuario, Long idRol
		/* , Boolean activo ,Date fechaBaja */
) {

}
