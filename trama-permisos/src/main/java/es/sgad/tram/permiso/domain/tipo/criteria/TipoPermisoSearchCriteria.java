package es.sgad.trama.permiso.domain.tipo.criteria;

import java.util.Date;

import lombok.Data;

@Data
public class TipoPermisoSearchCriteria {

	private String descripcion;

	private Long idTipoDuracion;

	private Integer orden;

	private Boolean comprobarPlazo;

	private Boolean esTiempoComputable;

	private Boolean sePuedeSolicitar;

	private Boolean debeTenerAdjunto;

	private Boolean creadaPorOrganismo;

	private Boolean crearEnAmbitoNuevo;

	private Date fechaBaja;
	
	private String codigo;

	private int page = 0;
	
	private int size = 10;
}
