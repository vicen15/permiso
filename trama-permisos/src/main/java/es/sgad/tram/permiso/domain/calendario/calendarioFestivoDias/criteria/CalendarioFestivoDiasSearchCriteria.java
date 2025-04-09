package es.sgad.trama.permiso.domain.calendario.calendarioFestivoDias.criteria;

import java.util.Date;

import lombok.Data;

@Data
public class CalendarioFestivoDiasSearchCriteria {

	private String id;

	private Long idTurno;

	private Long idCalendarioFestivo;

	private Date fecha;

//	TODO: añadir activo y fechaBaja a la tabla
//	private Boolean activo;
//
//	private Date fechaBaja;

	private int page = 0;
	private int size = 10;
}
