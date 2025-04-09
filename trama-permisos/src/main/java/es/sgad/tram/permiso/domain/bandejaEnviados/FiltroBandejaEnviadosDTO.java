package es.sgad.trama.permiso.domain.bandejaEnviados;

import java.time.LocalDate;

import es.sgad.trama.permiso.domain.ambito.tipo.TipoPermisoAmbitoComboDTO;
import es.sgad.trama.permiso.domain.estadoTramite.EstadoTramiteComboDTO;
import es.sgad.trama.permiso.domain.tipoOperacion.TipoOperacionComboDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
 * Representa las entidades del filtro  
 * de la bandeja de permisos enviadas 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FiltroBandejaEnviadosDTO {
	
	/** Tipo de operación (Sol-Anu) solicitada */
	private TipoOperacionComboDTO tipoOperacion;
	
	/** Estado de la solicitud de permiso */
	private EstadoTramiteComboDTO estado;
	
	/** Identificador del tipo de permiso (Ambito) solicitado */
    private TipoPermisoAmbitoComboDTO tipoPermisoAmbito;
    
    /** Identificador del usuario solicitante del permiso */
    private UsuarioDTO solicitante;
    
    /** Fecha de inicio del permiso solicitado. */
    private LocalDate fechaInicio;

    /** Fecha de fin del permiso solicitado. */
	private LocalDate fechaFin;
    
}
