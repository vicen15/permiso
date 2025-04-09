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
public class GrupoDTO {

	private Long id;

//	private ClasePersonalDTO clasePersonal;
	private Long idClasePersonal;

	private String grupo;
	
	private List<UsuarioDTO> listaUsuario;
}