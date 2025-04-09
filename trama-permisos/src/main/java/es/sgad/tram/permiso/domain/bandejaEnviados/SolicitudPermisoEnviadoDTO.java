package es.sgad.trama.permiso.domain.bandejaEnviados;

import java.time.LocalDate;
import java.util.List;

import es.sgad.trama.permiso.domain.ambito.tipo.TipoPermisoAmbitoComboDTO;
import es.sgad.trama.permiso.domain.estadoTramite.AccionComboDTO;
import es.sgad.trama.permiso.domain.estadoTramite.EstadoTramiteComboDTO;
import es.sgad.trama.permiso.domain.tipoOperacion.TipoOperacionComboDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SolicitudPermisoEnviadoDTO {

	/** Identificador de la solicitud de permiso */
	private String idSolicitudPermiso;
	
	/** Tipo de operación (Sol-Anu) solicitada, solo identificador y descripción */
	private TipoOperacionComboDTO tipoOperacion;
	
	/** Identificador del tipo de permiso (Ambito) solicitada, solo identificador y descripción */
    private TipoPermisoAmbitoComboDTO tipoPermisoAmbito;

    /** Fecha en la que se solicita la permiso */
	private LocalDate fechaSolicitud;
	
	/** Estado de la solicitud de permiso, solo identificador y descripción */
	private EstadoTramiteComboDTO estado;
	
	/** Solicitante de la solicitud de permiso */
	private String nombreSolicitante;
	
	/** Validador de la solicitud de permiso */
	private String nombreValidador;
	
	/** Autorizador de la solicitud de permiso */
	private String nombreAutorizador;
	
	/** Accion de la solicitud de permiso, solo identificador y descripción */
	private AccionComboDTO accion;

	/** Detalles de la solicitud de permiso */
	private List<SolicitudPermisoDetalleDTO> listaSolicitudPermisoDetalle;

}
