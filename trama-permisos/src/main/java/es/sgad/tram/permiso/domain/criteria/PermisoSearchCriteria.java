package es.sgad.trama.permiso.domain.criteria;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class PermisoSearchCriteria {

	private String id_tipo_permiso;
	
	private String id_usuario_solicitante;
	
	private LocalDate fecha;

	private LocalTime horaInicio;

	private LocalTime horaFin;
	
	private Integer ejercicio;

	private int page = 0;
	
	private int size = 10;
}
