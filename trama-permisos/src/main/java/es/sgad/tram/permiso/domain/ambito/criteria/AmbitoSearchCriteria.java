package es.sgad.trama.permiso.domain.ambito.criteria;

import java.sql.Blob;
import java.util.Date;

import lombok.Data;

@Data
public class AmbitoSearchCriteria {

	private Long id;

	private String nombre;

	private Date fechaAlta;

	private String usuarioWs;

	private String contraseniaWs;

	private Long firmaSolicitar;

	private Long firmaAutorizar;

	private Long firmaFichaje;

	private Date fechaLimiteVacaciones;

	private Long cortesiaEntrada;

	private Long cortesiaSalida;

	private Long opcionValidarSolicitud;

	private Long opcionValidarFechaLimiteVacaciones;

	private Long opcionLicenciaAAPP;

	private Date horaEmpiezaTarde;

	private Boolean avisarAusencias;

	private Boolean avisarMarcajesImpares;

	private Blob plantillaBolsaConciliacion;

	private Long numeroPersonasInformeAsincrono;

	private String observacion;
	
//	private Boolean activo;

	private Date fechaBaja;

	private int page = 0;
	private int size = 10;
}
