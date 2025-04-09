package es.sgad.trama.permiso.domain.franja.criteria;

import java.util.Date;

import lombok.Data;

@Data
public class FranjaSearchCriteria {

//	private Long id;

	private Long idTurno;

	private Long idTipoHora;

	private Date horaInicio;

	private Boolean obligatorio;

//	private Boolean activo;
//
//	private Date fechaBaja;

	private int page = 0;
	private int size = 10;
}
