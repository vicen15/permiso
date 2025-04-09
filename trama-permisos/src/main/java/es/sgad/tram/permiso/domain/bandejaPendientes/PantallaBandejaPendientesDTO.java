package es.sgad.trama.permiso.domain.bandejaPendientes;

import java.util.List;

import es.sgad.trama.permiso.domain.ambito.tipo.TipoPermisoAmbitoComboDTO;
import es.sgad.trama.permiso.domain.estadoTramite.AccionComboDTO;
import es.sgad.trama.permiso.domain.usuario.SolicitanteComboDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PantallaBandejaPendientesDTO {
	
	private List <TipoPermisoAmbitoComboDTO> tipoPermisoAmbitoCombo;
	
	//private List <TipoOperacionComboDTO> tipoOperacionCombo;
	
	private List <SolicitanteComboDTO> solicitanteCombo;	
	
	private List <AccionComboDTO> accion;
	
	private List <SolicitudPermisoPendienteDTO> solicitudPermisoPendiente;
	
}
