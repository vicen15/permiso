package es.sgad.trama.permiso.persistance.usuario;

import java.sql.Date;

//Hay que crear un filtro con cada uno de los campos que queremos poder buscar, suelen ser los mismos campos que hay en el DTO 

public record UsuarioFilter(Long idTipoDocIdent, String docIdent, String tarjeta, String nombre, String apellido1,
		String apellido2, String email, Long idAmbito, Date fechaAlta, Date fechaBaja, Long idSexo, Long idUnidad,
		Long idClasePersonal, Long idEdificio, Long idGrupo, Boolean ficharObligatorio, Boolean ficharOrdenador,
		Boolean cortesiaActivo, Long idFlexibilidadTipo, Long idFlexibilidadSubtipo,
		Long idJornadaIntensivaAmpliadaTipo, Date fechaIniFlex, Date fechaIniIntensivaAmpliada, Date anioFinFlex,
		Date anioFinIntensivaAmpliada, Long idFiltroAdicional
/* , Boolean activo ,Date fechaBaja */
) {

}
