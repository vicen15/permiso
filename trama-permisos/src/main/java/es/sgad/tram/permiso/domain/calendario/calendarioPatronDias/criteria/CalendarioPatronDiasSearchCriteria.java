package es.sgad.trama.permiso.domain.calendario.calendarioPatronDias.criteria;

import java.util.Date;

import lombok.Data;

@Data
public class CalendarioPatronDiasSearchCriteria {

	private String id;

	private String idCalendPatron;

	private Long idTurno;

	private Date fecha;

//	TODO: añadir activo y fechaBaja a la tabla
//	private Boolean activo;
//
//	private Date fechaBaja;

	private int page = 0;
	private int size = 10;
}
