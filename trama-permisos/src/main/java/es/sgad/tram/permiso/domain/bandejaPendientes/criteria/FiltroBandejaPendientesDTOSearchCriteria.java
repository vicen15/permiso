package es.sgad.trama.permiso.domain.bandejaPendientes.criteria;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

/** 
 * Clase en la que se definen los campos 
 * por los que se pueden filtrar en la búsqueda
 * 
 * Existe un SearchCriteria por cada DTO por 
 * el que se quiere buscar
 */
@Data
public class FiltroBandejaPendientesDTOSearchCriteria {
	
	/** Identificador del usuario logado (solicitante o validador o autorizador)*/
    private String idUsuario;
    
    /** Identificador de la acción que puedes ejecutar */
	private String idAccion;
	
	/** Identificador del ámbito de la solicitud del permiso */
	private String idAmbito;
	
	/** Identificador del tipo de permiso (Ambito) solicitada */
    private String idTipoPermisoAmbito;
    
    /** Identificador del usuario solicitante del permiso */
    private String idSolicitante;
    
    /** Fecha de inicio del rango de fechas de busqueda. */
    private LocalDate fechaInicio;

    /** Fecha de fin del rango de fechas de busqueda. */
	private LocalDate fechaFin;   
	
    /** Página en la que se encuetra */
	private int page = 0;
	
	/** permisos a mostrar por página */
	private int size = 10;
    
}
