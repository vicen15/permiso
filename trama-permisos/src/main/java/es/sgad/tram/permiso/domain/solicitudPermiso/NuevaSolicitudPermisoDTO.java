package es.sgad.trama.permiso.domain.solicitudPermiso;

import java.util.List;

import es.sgad.trama.permiso.domain.solicitudPermisoDetalle.SolicitudPermisoDetalleDTO;
import es.sgad.trama.permiso.domain.solicitudPermisoDocumento.SolicitudPermisoDocumentoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NuevaSolicitudPermisoDTO{
	
	private String idTipoPermisoAmbito;
	
	private String idSolicitante;
	
	private Long idEstadoTramite;
	
	private String observacionesParaUsuario;
	
	private String firmaCriptoSolicitante;
	
	private String firmaNoCriptoSolicitante;
	
	private List<SolicitudPermisoDetalleDTO> listaSolicitudPermisoDetalle;
	
	private List<SolicitudPermisoDocumentoDTO> listaSolicitudPermisoDocumento;
}
