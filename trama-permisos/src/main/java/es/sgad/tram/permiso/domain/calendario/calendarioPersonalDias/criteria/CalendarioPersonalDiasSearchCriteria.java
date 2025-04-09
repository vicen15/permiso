package es.sgad.trama.permiso.domain.calendario.calendarioPersonalDias.criteria;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CalendarioPersonalDiasSearchCriteria {
	
	private String id;
	
	private String idUsuario;
	
	private String idTurno;
	
	private LocalDate fecha;
		
	private int page = 0;
	private int size = 10;
}