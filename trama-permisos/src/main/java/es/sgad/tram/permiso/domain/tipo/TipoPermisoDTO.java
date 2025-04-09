package es.sgad.trama.permiso.domain.tipo;

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
public class TipoPermisoDTO{
	
	private Long id;
	
	private String descripcion;
	
	private Long idTipoDuracion;
	
	private Integer orden;
	
	private Boolean comprobarPlazo;
	
	private Boolean esTiempoComputable;
	
	private Boolean sePuedeSolicitar;
	
	private Boolean debeTenerAdjunto;
	
	private Boolean creadaPorOrganismo;
	
	private Boolean crearEnAmbitoNuevo;
	
	private LocalDate fechaBaja;
	
	private String codigo;
	
	private List<SolicitudPermisoDTO> listaSolicitudPermiso;
}

