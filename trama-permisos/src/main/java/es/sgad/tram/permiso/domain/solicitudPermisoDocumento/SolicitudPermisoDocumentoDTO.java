package es.sgad.trama.permiso.domain.solicitudPermisoDocumento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SolicitudPermisoDocumentoDTO {
	
	private String id;
	
	private String idSolicitudPermiso;
	
	private String descripcion;
	
	private String nombre;
	
	private String ruta;
	
	private String tipo;

	private Long peso;
	
}
