package es.sgad.trama.permiso.domain.maestras.clasePersonal;

import java.util.List;

import es.sgad.trama.permiso.domain.bandejaEnviados.UsuarioDTO;
import es.sgad.trama.permiso.domain.usuario.extras.GrupoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClasePersonalDTO {

	private Long id;

	private String descripcion;
	
	private List<UsuarioDTO> listaUsuario;
	
	private List<GrupoDTO> listaGrupo;
}