package es.sgad.trama.permiso.persistance.ambito;

import java.sql.Blob;
import java.sql.Date;

//Hay que crear un filtro con cada uno de los campos que queremos poder buscar, suelen ser los mismos campos que hay en el DTO 

public record AmbitoFilter(Long id, String nombre, Date fechaAlta, String usuarioWs, String contraseniaWs,
		Long firmaSolicitar, Long firmaAutorizar, Long firmaFichaje, Date fechaLimiteVacaciones, Long cortesiaEntrada,
		Long cortesiaSalida, Long opcionValidarSolicitud, Long opcionValidarFechaLimiteVacaciones, String observacion,
		Long opcionLicenciaAAPP, Date horaEmpiezaTarde, Boolean avisarAusencias, Boolean avisarMarcajesImpares,
		Blob plantillaBolsaConciliacion, Long numeroPersonasInformeAsincrono, Boolean activo, Date fechaBaja) {

}
