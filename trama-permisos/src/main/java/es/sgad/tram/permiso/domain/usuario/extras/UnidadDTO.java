package es.sgad.trama.permiso.domain.usuario.extras;

import java.util.List;

import es.sgad.trama.permiso.domain.bandejaEnviados.UsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnidadDTO {

	private String id;

	private String nombre;

//	private UnidadDTO unidad;
	private String idUnidad;

//	private Boolean activo;

//	private UnidadTipoDTO unidadTipo;
	private Long idUnidadTipo;

//	private AmbitoDTO ambito;
	private String idAmbito;
	
	private List<UsuarioDTO> listaUsuario;
}