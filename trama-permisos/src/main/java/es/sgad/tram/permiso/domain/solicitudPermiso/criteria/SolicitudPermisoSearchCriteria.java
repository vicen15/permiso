package es.sgad.trama.permiso.domain.solicitudPermiso.criteria;

import java.util.Date;

import lombok.Data;

@Data
public class SolicitudPermisoSearchCriteria {

	private Long idPermiso;

	private Long idSolicitante;

	private Long idValidador;

	private Long idAutorizador;

	private Long idGestorPersonal;

	private Long idGpAsignado;
	
	private Long idEstadoTramite;

	private String motivoDenegacion;

	private Date fechaSolicitud;

	private Date fechaValidacion;

	private Date fechaAutorizacion;

	private Date fechaGestorPersonal;

	private Date fechaPeticionDocumentacion;

	private Date fechaDesestimacion;
	
	private Date fechaDenegada;

	private Long idFirma;

	private String observacionesValidador;

	private String observacionesAutorizador;

	private Integer ejercicio;

	private String observacionesParaUsuario;

	private String observacionesGp;
	
	private Long validacionDelegada;
	
	private Long autorizacionDelegada;

	private String infoParaGp;

	private Long idAmbito;
	
	private Long idTipoOperacion;
	
	private Long idPermisoAnular;
	
	private int page = 0;
	 
	private int size = 10;
}
