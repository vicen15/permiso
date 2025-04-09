package es.sgad.trama.permiso.domain.bandejaPendientes;

import java.time.LocalDate;
import java.util.List;

import es.sgad.trama.permiso.domain.estadoTramite.EstadoTramiteComboDTO;
import es.sgad.trama.permiso.domain.solicitudPermisoDetalle.SolicitudPermisoDetalleDTO;
import es.sgad.trama.permiso.domain.tipo.TipoPermisoComboDTO;
import es.sgad.trama.permiso.domain.tipoOperacion.TipoOperacionComboDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SolicitudPermisoPendienteDTO {

	/** Identificador de la solicitud de permiso */
	private String idSolicitudPermiso;
	
	/** Tipo de operación (Sol-Anu) solicitada, solo identificador y descripción */
	private TipoOperacionComboDTO tipoOperacion;

	/** Identificador del tipo de permiso solicitado, solo identificador y descripción */
    private TipoPermisoComboDTO tipoPermiso;	
	
    /** Fecha en la que se solicita el permiso */
	private LocalDate fechaSolicitud;	
	
	/** Estado de la solicitud de permiso, solo identificador y descripción */
	private EstadoTramiteComboDTO estado;

	/** Detalles de la solicitud de permiso */
	private List<SolicitudPermisoDetalleDTO> listaSolicitudPermisoDetalle;

}
