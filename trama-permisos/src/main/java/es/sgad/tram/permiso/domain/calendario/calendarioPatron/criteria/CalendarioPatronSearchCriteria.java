package es.sgad.trama.permiso.domain.calendario.calendarioPatron.criteria;

import java.util.Date;

import lombok.Data;

@Data
public class CalendarioPatronSearchCriteria {

	private String id;
	
	private Long idAmbito;
	
	private Date anio;
	
	private String nombre;

	private String descripcion;

//	TODO: añadir activo y fechaBaja a la tabla
//	private Boolean activo;
//
//	private Date fechaBaja;

	private int page = 0;
	private int size = 10;
}
