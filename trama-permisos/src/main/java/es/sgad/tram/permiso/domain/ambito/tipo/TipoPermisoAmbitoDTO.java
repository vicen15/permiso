package es.sgad.trama.permiso.domain.ambito.tipo;

import java.time.LocalDate;
import java.util.List;

import es.sgad.trama.permiso.domain.solicitudPermiso.SolicitudPermisoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoPermisoAmbitoDTO{
	
	private String id;
	
	private Long idTipoPermiso;
	
	private String idAmbito;
	
	private String descripcion;
	
	private Long idTipoDuracion;
	
	private Integer orden;
	
	private Boolean comprobarPlazo;
	
	private Boolean esTiempoComputable;
	
	private Boolean sePuedeSolicitar;
	
	private Boolean debeTenerAdjunto;
	
	private Boolean creadaPorOrganismo;
	
	private Boolean crearEnAmbitoNuevo;
	
	private Boolean activoGestorPersonal;
	
	private LocalDate fechaBaja;
	
	private List<SolicitudPermisoDTO> listaSolicitudPermiso;
}

