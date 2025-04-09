package es.sgad.trama.permiso.domain.edificio.criteria;

import java.sql.Date;

import lombok.Data;

@Data
public class EdificioSearchCriteria {

	private Long id;

	private String idAmbito;

	private String direccion;

	private String descripcion;

//	private Boolean activo;

	private Long idProvincia;

	private Long idMunicipio;

	private Long idHusoHorario;

	private Date fechaBaja;

	private int page = 0;
	private int size = 10;
}
