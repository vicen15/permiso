package es.sgad.trama.permiso.domain.bandejaEnviados;

import java.time.LocalDate;
import java.time.LocalTime;

import es.sgad.trama.permiso.domain.ambito.tipo.TipoPermisoAmbitoComboDTO;
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
public class SolicitudPermisoDetalleEnviadoDTO {

	/** Identificador del detalle de la solicitud de permiso */
	private String idSolicitudPermisoDetalle;
	
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
	
	
	/** Validador de la solicitud de permiso */
	private UsuarioDTO validador;
	
	/** Fecha de inicio del permiso */
	private LocalDate fechaInicio;

	/** Fecha de fin del permiso */
	private LocalDate fechaFin;	
	
	/** Hora de inicio de la permiso */
	private LocalTime horaInicio;
	
	/** Hora en la que termina la permiso */
	private LocalTime horaFin;
	
	/** observaciones */
	private String observaciones;
	
}
