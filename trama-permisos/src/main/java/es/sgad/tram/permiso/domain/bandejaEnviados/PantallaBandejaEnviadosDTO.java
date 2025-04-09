package es.sgad.trama.permiso.domain.bandejaEnviados;

import java.util.List;

import es.sgad.trama.permiso.domain.ambito.tipo.TipoPermisoAmbitoComboDTO;
import es.sgad.trama.permiso.domain.estadoTramite.EstadoTramiteComboDTO;
import es.sgad.trama.permiso.domain.usuario.SolicitanteComboDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PantallaBandejaEnviadosDTO {
	
	private List <EstadoTramiteComboDTO> estadoCombo;
	
	private List <SolicitudPermisoEnviadoDTO> solicitudPermisoEnviado;
	
	private List <SolicitanteComboDTO> solicitanteCombo;
	
	private List <TipoPermisoAmbitoComboDTO> tipoPermisoAmbitoCombo;
	
	private List <EstadoTramiteComboDTO> accionCombo;
}
