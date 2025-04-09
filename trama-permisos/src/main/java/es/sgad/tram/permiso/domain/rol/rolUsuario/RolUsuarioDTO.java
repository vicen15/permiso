package es.sgad.trama.permiso.domain.rol.rolUsuario;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolUsuarioDTO {
	
	private String id;
	
	private String idAmbito;

	private String idUsuario;
	
	private Long idRol;
	
	
}