package es.sgad.trama.permiso.domain.firma;

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
public class SolicitudPermisoFirmaDTO {
	
	private String id;

	private String firmaCriptoSolicitante;

	private String firmaNoCriptoSolicitante;

	private String firmaCriptoAutorizador;

	private String firmaNoCriptoAutorizador;

//	private Boolean activo;

//	private LocalDateTime fechaBaja;
	
	private List<SolicitudPermisoDTO> listaSolicitudPermiso;
}
