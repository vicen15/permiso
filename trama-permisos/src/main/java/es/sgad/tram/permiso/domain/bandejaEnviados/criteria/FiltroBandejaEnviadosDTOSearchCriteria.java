package es.sgad.trama.permiso.domain.bandejaEnviados.criteria;

import java.time.LocalDate;

import lombok.Data;

/** 
 * Clase en la que se definen los campos 
 * por los que se pueden filtrar en la búsqueda
 * 
 * Existe un SearchCriteria por cada DTO por 
 * el que se quiere buscar
 */
@Data
public class FiltroBandejaEnviadosDTOSearchCriteria {
	
	/** Identificador del usuario logado (solicitante o validador o autorizador)*/
    private String idUsuario;
	
	/** Identificador de la acción que puedes ejecutar */
	private String idAccion;
		
	/** Identificador del ámbito de la solicitud de permiso */
	private String idAmbito;
	
	/** Identificador del estado de la solicitud de permiso */
	private String idEstado;
	
	/** Identificador del tipo de permiso (Ambito) solicitado */
    private String idTipoPermisoAmbito;
    
    /** Identificador del usuario solicitante de la permiso */
    private String idSolicitante;
    
    /** Fecha de inicio del permiso solicitado. */
    private LocalDate fechaInicio;

    /** Fecha de inicio del permiso solicitado. */
    private LocalDate fechaFin;    
	
    /** Página en la que se encuetra */
	private int page = 0;
	
	/** Permisos a mostrar por página */
	private int size = 10;
    
}
