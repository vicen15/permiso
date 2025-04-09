package es.sgad.trama.permiso.domain.tipoOperacion;

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
public class TipoOperacionDTO {
	
	private Long id;
	
	/** Valores: solicitud, anulación */
	private String descripcion;
	
//	private Boolean activo;
	
//	private Date fechaBaja;
	
	private List<SolicitudPermisoDTO> listaSolicitudPermisos;
}
