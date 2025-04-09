package es.sgad.trama.permiso.domain.tipoPermisoAmbito.criteria;

import java.time.LocalDate;

import lombok.Data;
@Data
public class TipoPermisoAmbitoSearchCriteria {
	private String id;
	
	private Long idTipoPermiso;
	
	private String idAmbito;
	
	private String descripcion;
	
	private Integer ejercicio;
	
	private Boolean esTiempoComputable;
	
	private Boolean sePuedeSolicitar;
	
	private Boolean creadaPorOrganismo;
	
	private Boolean debeTenerAdjunto;
	
	private String informacion;

	private Boolean activoValidarAutorizar;
	
	private Boolean activoGestorPersonal;
	
	private Boolean aprobarEnAmbitoPadre;
	
	private LocalDate fechaBaja;
	
	private int page = 0;
    
	private int size = 10;
}
