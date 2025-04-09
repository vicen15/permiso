package es.sgad.trama.permiso.domain.turno.criteria;

import java.sql.Date;

import lombok.Data;

@Data
public class TurnoSearchCriteria {

	private Long idAmbito;

	private String codigo;

	private String descripcion;

	private Long turnoVeinticuatroHoras;

	private Date tiempo;

	private Integer festivo;

//	private Boolean activo;

//	private Date fechaBaja;

	private int page = 0;
	private int size = 10;
}
