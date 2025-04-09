package es.sgad.trama.permiso.domain.calendario.calendarioFestivo.criteria;

import java.util.Date;

import lombok.Data;

@Data
public class CalendarioFestivoSearchCriteria {

	private Long id;

	private Long idAmbito;

	private Long idEdificio;

	private String nombre;

	private Date anio;

	private String descripcion;

//	TODO: añadir activo y fechaBaja a la tabla
//	private Boolean activo;
//
//	private Date fechaBaja;

	private int page = 0;
	private int size = 10;
}
